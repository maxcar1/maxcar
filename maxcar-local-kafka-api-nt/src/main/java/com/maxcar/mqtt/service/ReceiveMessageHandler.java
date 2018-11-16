package com.maxcar.mqtt.service;

import com.maxcar.util.CRC16M;
import com.maxcar.util.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;


/**
 * 后台消费者 监听
 * Created by Administrator on 2018/8/16.
 */
public class ReceiveMessageHandler implements MessageHandler {
    protected static Logger logger = (Logger) LoggerFactory.getLogger(ReceiveMessageHandler.class);


    private String bytesToHex(byte[] bytes, int begin, int end) {
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(' '); // 加一个空格将每个字节分隔开
        }
        return hexBuilder.toString().toUpperCase();
    }

    public static String bytesToHexString(byte[] src){
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

    public String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        String str = (String) message.getPayload();
        if(!str.equals("10 18 00 04 4D 51 54 54 04 C2 00 3C 00 0C 05 D6 FF 37 34 38 59 4D 43 06 24 46")) {
            try {
                String strs = new String((str).getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            convertStringToHex("MC'\u0001\u0001\f\u0005Ñÿ748YMC\u0003R3[j4h\u0001\u0001ÿÿ\u0006\u0001\u0004# )úý§");
            String clientData = bytesToHexString(str.getBytes());
//                    bytesToHex(str.getBytes(), 0,  str.length());
//                    .replaceAll(" ", "");//去空格
            String hex = CRC16M.GetModBusCRC(clientData.substring(0, clientData.length() - 4));

            String check = clientData.substring(clientData.length() - 4);
            //校验数据是否正确
//            4D43002701010C05EFBFBDEFBFBD373438594D430352335B4DEFBFBDEFBFBD00010001EFBFBDEF
            if (hex.equalsIgnoreCase(check)) {//校验数据准确性
                if(clientData.length()==78){
                    String ID = clientData.substring(30, 38);//获取道闸唯一ID号
                    System.out.println("道闸ID号" + ID);
                    String valueTen = Integer.parseInt(ID, 16) + "";
                    Barrier barrier = null;
//                    Barrier barrier = barrierService.selectByBarrierId(valueTen.substring(valueTen.length() - 6, valueTen.length()));//查询欢迎词
                    System.out.println("接收到的byte数组的十六进制：" + clientData);
                    String value1 = clientData.substring(0, 4);
                    String value2 = "002C";//44字节
                    String value3 = clientData.substring(8, 10);
                    String value4 = "81";//下发数据
                    String value5 = clientData.substring(12, 58);
                    String value6 = "000B81";
                    String value7 = "";
                    String value8 = "";//欢迎词


                    String clientDatas = clientData.substring(66, 74);
                    int valueTens = Integer.parseInt(clientDatas, 16);
                    String values = "" + valueTens;
                    while (values.length() < 10) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("0").append(values);// 左补0
                        values = sb.toString();
                    }
                    String outParam = "";
                    if (barrier == null) {
                        value7 = "FF08";
                        value8 = "配置有误";
                        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//配置有误
                        String outHex = CRC16M.GetModBusCRC(outParam);
                        outParam = outParam + outHex;
                    } else if (barrier.getInOutType() == 0) {//道闸不限制
//                        String marketId = barrier.getMarketId();
//                        StockCarInfo stockCarInfo = barrierService.selectByRFID(marketId + between + values);//查询是否允许开闸
//                        if (stockCarInfo != null) {
//                            value7 = "8008";//允许开闸
//                            value8 = barrier.getStaticSpeech();
//                            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
//                            String outHex = CRC16M.GetModBusCRC(outParam);
//                            outParam = outParam + outHex;
//                            System.out.print("+++++++++" + outParam);
//                        } else {
//                            value7 = "FF08";
//                            value8 = "无录入车";
//                            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
//                            String outHex = CRC16M.GetModBusCRC(outParam);
//                            outParam = outParam + outHex;
//                        }
                    } else if (barrier.getInOutType() == 1) {//道闸限制车辆出入

                    }
                }
            }

            System.out.println("接收到消息：" + clientData);
        }
    }




    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String(("MC'\u0001\u0001\f\u0005Ñÿ748YMC\u0003R3[j4h\u0001\u0001ÿÿ\u0006\u0001\u0004# )úý§").getBytes("ASCII")));

    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
