package com.maxcar.mqtt.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.CarRecord;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.barrier.service.ApplicationContextHolder;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.barrier.service.CarRecordService;
import com.maxcar.barrier.service.CarService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import com.maxcar.util.PostParam;
import com.maxcar.util.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发布消息的回调类
 * <p>
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 * <p>
 * public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 * <p>
 * public void connectionLost(Throwable cause)在断开连接时调用。
 * <p>
 * public void deliveryComplete(MqttDeliveryToken token))
 * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 * 由 MqttClient.connect 激活此回调。
 */
public class PushCallback implements MqttCallback {

    SimpleDateFormat fmt1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public PushCallback() {

    }
    //客户端监听类
    ClientMQTT service;

    public PushCallback(ClientMQTT service) {
        this.service = service;
    }

    Logger logger = LoggerFactory.getLogger(PushCallback.class);

    //断线处理方法
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("客户机和broker已经断开");
        while (true) {
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(1000);
                service.init();
                break;
            } catch (Exception e) {
                continue;
            }
        }
    }
    //
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("deliveryComplete---------" + token.isComplete());
    }
    //处理方法主体
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            // subscribe后得到的消息会执行到这里面
            logger.info("接收消息主题 : " + topic);
//            logger.info("接收消息Qos : " + message.getQos());
 //           logger.info("接收消息message : " + String.valueOf(message.getPayload()));
            String str = new String(message.getPayload(),"gbk");
            logger.info("接收消息转换前内容 : " + str);
            //第一步过滤链接请求
            if (str.replace(" ", "").indexOf("101800044D51545") == -1) {
                String data = bytesToHexString(message.getPayload());
                logger.info("转换后消息内容 : " + data);
                String clientData = data.substring(6,data.length());
                String hex = CRC16M.GetModBusCRC(clientData.substring(0, clientData.length() - 4));
                String check = clientData.substring(clientData.length() - 4);
                //校验数据是否正确
                logger.info("签名前：" + check+"签名后:"+hex);
                if (hex.equalsIgnoreCase(check)) {//校验数据准确性
                    String outParam = "";
                    //请求初始化配置参数,获取请求类型01rfid，02请求参数配置
                    String codeType = clientData.substring(10, 12);
                    logger.info("查询配置开始时间：" + fmt1.format(new Date()));
                    BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
                    String barrierId = clientData.substring(14, 38);
                    Barrier barrier = barrierService.selectByBarrierId(barrierId.toUpperCase());
                    logger.info(barrierId.toUpperCase()+"是否查到配置"+barrier+"查询配置结束时间：" + fmt1.format(new Date()));
                    if (barrier == null) {//配置有误
                        outParam = failDz(clientData);
                    } else {
                        if (codeType.equals("02")) {//初始配置参数
                            outParam = initDz(clientData, barrier);
                            if (barrier != null && barrier.getMqttTopic() != null) {
                                byte b[] = toBytes(outParam);
                            //    ServerMQTT serverMQTT = new ServerMQTT();
                                ServerMQTT serverMQTT = new ServerMQTT(barrier.getMqttTopic(),b);
                                serverMQTT.start();
                                logger.info(barrier.getMqttTopic() + "huifu消息内容：" + outParam);
                             //   serverMQTT.send(b, barrier.getMqttTopic());
                            }
                        } else if (codeType.equals("01")) {//请求开闸
                            BarrierValid barrierValid = new BarrierValid();
                            Map map = barrierValid.openDz(clientData, barrier);
                            outParam = map.get("outParam") + "";
                            if (barrier != null && barrier.getMqttTopic() != null) {
                                byte b[] = toBytes(outParam);
                                if(!barrier.getStatus().equals("4")){
                                    logger.info(barrier.getMqttTopic() + "huifu消息内容：" + outParam);
                                    ServerMQTT serverMQTT = new ServerMQTT(barrier.getMqttTopic(),b);
                                    serverMQTT.start();
                                //    serverMQTT.send(b, barrier.getMqttTopic());
                                }
                            }
                            if (map.get("stockCarInfo") != null) {
                                Car car = (Car) map.get("stockCarInfo");
                                uploadData(car);//请求云端
                            }
                        } else if (codeType.equals("07")) {//ic卡处理
                            //截取卡号10位数
                            //4d43002701 070c05d4ff 373438594d 430353595b 851b5e0001 0001ffff00 060704 006f 1e23 7327
                            //4d43002801070c05d4ff373438594d430353595ba1b84200010001ffff00060705 0007275536 3139
                            //05d4ff3734
                            String cardNo16 = clientData.substring(66, 76);
                            //    Integer cardNo = Integer.valueOf(cardNo16, 16);
                            //    String cardNoStr = String.format("%0" + 10 + "d", cardNo);
                            logger.info("道闸发送的卡号:{}", cardNo16);
                            uploadRequestCloud(barrier, cardNo16);
                            /*outParam = openDzByCardNo(clientData,result);
                            byte b[] = toBytes(outParam);
                            ServerMQTT serverMQTT = new ServerMQTT();
                            logger.info(barrier.getMqttTopic() + "huifu消息内容：" + outParam);
                            serverMQTT.send(b, barrier.getMqttTopic());*/
                        }else if(codeType.equals("0b")){
                            if (barrier != null && barrier.getMqttTopic() != null) {
                                if (barrier.getStatus().equals("4")) {
                                    String outParam1 = "";
                                    String value1 = Canstats.headerBody;
                                    //字符串长度/2
                                    String value2 = "leng";//44字节
                                    //协议版本
                                    String value3 = Canstats.headerVersion;
                                    String value4 = Canstats.first_kz;//下发数据
                                    int time = (int) (System.currentTimeMillis() / 1000);
                                    String timeStamp = PushCallback.toHexString(time);
                                    //id长度+id号+时间戳+设备类型+程序版本+设备电量
                                    //12位数
                                    String value5 = PushCallback.toHexString(barrier.getBarrierId().length() / 2) + barrier.getBarrierId() + timeStamp + Canstats.dzType + Canstats.dzVersion + Canstats.dzPower;
                                    String value6 = "000B8B";
                                    String value7 = "";
                                    value7 = Canstats.yxcc;//允许开闸
                                    outParam1 = value1 + value2 + value3 + value4 + value5 + value6 + value7;
                                    outParam1 = outParam1.replaceAll("leng", PushCallback.toHexStringBy0(outParam1.length() / 2 + 2));
                                    logger.info("数据初始化，先开闸，服务器发送消息：{}", outParam1);
                                    String outHex = CRC16M.GetModBusCRC(outParam1);

                                    outParam1 = outParam1 + outHex;
                                    logger.info("数据初始化，先开闸，服务器发送完整消息:{}", outParam1);
                                    ServerMQTT.send(outParam1, barrier.getMqttTopic());
                                }
                            }
                        }
                    }
                }else {
                    logger.info("=====签名错误处理开始,消息二次发送====");
                    //如果签名错误,取data前六位
                    String barrierId = data.substring(0, 6);
                    //拆分topic
                    String[] topics = topic.split("_");
                    if (topic.length() > 3) {
                        String t = topics[0] + "_" + topics[1];
                        BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
                        Barrier barrier = barrierService.getBarrierInfoLike(barrierId, t);
                        String outParam = "";
                        String value1 = Canstats.headerBody;
                        //字符串长度/2
                        String value2 = "leng";//44字节
                        //协议版本
                        String value3 = Canstats.headerVersion;
                        String value4 = Canstats.second_send;//下发数据
                        int time = (int) (System.currentTimeMillis() / 1000);
                        String timeStamp = PushCallback.toHexString(time);
                        //id长度+id号+时间戳+设备类型+程序版本+设备电量
                        //12位数
                        String value5 = PushCallback.toHexString(barrier.getBarrierId().length() / 2) + barrier.getBarrierId() + timeStamp + Canstats.dzType + Canstats.dzVersion + Canstats.dzPower;
                        String value6 = "000B8A";
                        String value7 = "";
                        value7 = Canstats.yxcc;//允许开闸
                        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7;
                        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length() / 2 + 2));
                        logger.info("签名错误处理，服务器发送消息：{}", outParam);
                        String outHex = CRC16M.GetModBusCRC(outParam);

                        outParam = outParam + outHex;
                        logger.info("签名错误，服务器发送完整消息:{}", outParam);
                        ServerMQTT.send(outParam, barrier.getMqttTopic());
                    }
                }
            }else{
                logger.info("错误的请求");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //刷卡开闸
    private String openDzByCardNo(String clientData,InterfaceResult result) throws Exception {
        //拼接欢迎词
        logger.info("道闸验证消息 : " + clientData);
        String outParam = "";
        String value1 = clientData.substring(0, 4);
        //   String value2 = "002C";//44字节
        String value2 = "leng";
        String value3 = clientData.substring(8, 10);
        String value4 = "";//下发数据
        String value5 = clientData.substring(12, 58);
        String value6 = "000B";
        String value7 = "";// 81语音并显示  87只语音不显示
        String value8 = "";
        String value9 = "";//欢迎词
        if (StringUtils.equals(result.getCode(),"200")) {
            JSONObject json = (JSONObject) JSONObject.toJSON(result.getData());
            if (StringUtils.equals(json.getString("code"), "200")) {
                value4 = "81";
                value7 = "81";
                value8 = Canstats.yxcc;//允许开闸
                value9 = String.valueOf(result.getData());
            }else if (StringUtils.equals(json.getString("code"),"600")){
                value4 = "87";
                value7 = "87";
                value9 = result.getMsg();//12字节
                value8 = "FF0C";//禁止重复入场
            }else if(StringUtils.equals(json.getString("code"),"500")){
                value4 = "81";
                value7 = "81";
                value9 = result.getMsg();//8字节
                value8 = "FF08";//进场失败
            }
        }
        /*
        value7 = "87";
        value9 = "禁止重复入场";
        byte[] b = value9.getBytes("gbk");
        value8 = "FF0C";*/

        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + value8 + HexUtils.getHexResult(value9);
        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
        String outHex = CRC16M.GetModBusCRC(outParam);
        outParam = outParam + outHex;
        return outParam;
    }
    //上传数据到云端
    private void uploadRequestCloud(Barrier barrier, String cardNo) {
        MessageProducerService messageProducerService = ApplicationContextHolder.getBean("messageProducerService");
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setMarket(barrier.getMarketId());
        StringBuilder url = new StringBuilder();
        url.append("/api-p/wx/");
        url.append(barrier.getMarketId());
        url.append("/");
        url.append(cardNo);
        url.append("/");
        url.append(barrier.getBarrierId());
        postParam.setUrl(url.toString());
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Canstats.dateformat.format(new Date()));
        logger.info("道闸开始发送上行消息至停车收费系统：{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage("-2",
                JsonTools.toJson(postParam), false, 0, Canstats.KAFKA_SASS);
    }

    //同步数据到云端
    public void uploadData(Car car) {
        MessageProducerService messageProducerService = ApplicationContextHolder.getBean("messageProducerService");
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(JsonTools.toJson(car));
        postParam.setMarket(car.getMarketId());
        postParam.setUrl("/common/car/updateStatus");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Canstats.dateformat.format(new Date()));
        //发送数据
        logger.info("道闸开始发送消息到云端：");
        messageProducerService.sendMessage("-1", JsonTools.toJson(postParam), false, 0, Canstats.KAFKA_SASS);
    }
    //找不到道闸配置，由于找不到主板id因此无法发送消息
    public String failDz(String clientData) {
        String outParam = "";
        String value1 = clientData.substring(0, 4);
        String value2 = "002C";//44字节
        String value3 = clientData.substring(8, 10);
        String value4 = "81";//下发数据
        String value5 = clientData.substring(12, 58);
        String value6 = "000B81";
        String value7 = Canstats.jzcc;
        String value8 = "系统错误";
        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
        String outHex = CRC16M.GetModBusCRC(outParam);
        outParam = outParam + outHex;
        return outParam;
    }
    //配置失败，中途道闸id被删除等原因,只给时间
    public String initDz(String clientData, Barrier barrier) {
        String outParam = "";
        //查出的十进制转为十六进制
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        Integer years = c.get(Calendar.YEAR);
        String year = "0" + years.toHexString(years);
        Integer months = c.get(Calendar.MONTH);
        String month = "0" + months.toHexString(months);
        String date = toHexString(c.get(Calendar.DATE));
        String hour = toHexString(c.get(Calendar.HOUR_OF_DAY));
        String minute = toHexString(c.get(Calendar.MINUTE));
        String second = toHexString(c.get(Calendar.SECOND));
        String week = getWeekOfDate();

        String gateWapIp = barrier.getGatewayIp();//网关
        String subNetMask = barrier.getSubnetMask();//子网掩码
        String mscAddress = barrier.getMacAddress();//物理地址十六进制
        String clientIp = barrier.getClientIp();//道闸ip
        String clientPort = barrier.getClientPort();//道闸端口
        String serverIp = barrier.getServerIp();//服务端ip
        String serverPort = barrier.getServerPort();//服务器端口
        String[] array = gateWapIp.split("\\.");
        gateWapIp = "";
        for (int i = 0; i < array.length; i++) {
            gateWapIp += toHexString(Integer.parseInt(array[i]));
        }

        String[] array1 = subNetMask.split("\\.");
        subNetMask = "";
        for (int i = 0; i < array1.length; i++) {
            subNetMask += toHexString(Integer.parseInt(array1[i]));
        }

        String mscAdd = mscAddress.replaceAll(":", "");

        String[] array2 = clientIp.split("\\.");
        clientIp = "";
        for (int i = 0; i < array2.length; i++) {
            clientIp += toHexString(Integer.parseInt(array2[i]));
        }

        Integer port = (Integer.parseInt(clientPort));
        clientPort = toHexStringByPort(port);

        String[] array3 = serverIp.split("\\.");
        serverIp = "";
        for (int i = 0; i < array3.length; i++) {
            serverIp += toHexString(Integer.parseInt(array3[i]));
        }
        Integer port1 = (Integer.parseInt(serverPort));
        serverPort = toHexStringByPort(port1);


        outParam = clientData.substring(0, 4) + "0051" + clientData.substring(8, 10) + "82" + clientData.substring(12, 58) + "003082" + year + month + date + hour + minute + second + week + clientData.substring(12, 38) + gateWapIp + subNetMask + mscAdd + clientIp + clientPort + serverIp + serverPort;
        logger.info(outParam);
        String outHex = CRC16M.GetModBusCRC(outParam);
        outParam = outParam + outHex;
        return outParam;
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate() {
        Date date = new Date();
        String[] weekDays = {"07", "01", "02", "03", "04", "05", "06"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    //端口号补零
    public static String toHexStringByPort(Integer str) {
        String returnStr = str + "";
        if (str < 4096) {
            returnStr = "0" + str.toHexString(str);
        } else {
            returnStr = str.toHexString(str);
        }
        return returnStr;
    }

    private String bytesToHex(byte[] bytes, int begin, int end) {
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(' '); // 加一个空格将每个字节分隔开
        }
        return hexBuilder.toString().toUpperCase();
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /*
    十进制转十六进制前面补0
     */
    public static String toHexString(Integer times) {
        String time;
        if (times < 16) {
            time = "0" + times.toHexString(times);
        } else {
            time = times.toHexString(times);
        }
        return time;
    }

    /*
    十进制转十六进制前面补0
     */
    public static String toHexStringBy0(Integer times) {
        String time;
        if (times <= 99) {
            time = "00" + times.toHexString(times);
        } else {
            time = times.toHexString(times);
        }
        return time;
    }

    //将指定byte数组以16进制的形式打印到控制台
    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
//            logger.info(hex.toUpperCase() );
        }

    }

    public static void main(String[] args) {

//        String outHex = CRC16M.GetModBusCRC("2201");
        System.out.println("05d1ff373438594d43035233".toUpperCase());
    }
}