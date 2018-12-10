package com.maxcar.mqtt.service;

import com.maxcar.util.*;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 罗顺锋 on 2018/11/31.
 * MQTT客户端的简单实现
 */
public class BasicRemoteClient implements RemoteClient, IMqttMessageListener,MqttCallback {

    static Logger logger = LoggerFactory.getLogger(BasicRemoteClient.class);

    private MqttClient client;
    private String clientId;
    private String broker;
    private String username;
    private String password;

    private Map<String, OnMessageListener> subscribeInfo;

    public BasicRemoteClient() throws MqttException {
        this(UUID.randomUUID().toString());
    }

    private BasicRemoteClient(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void init() throws MqttException {

        clientId = UuidUtils.getUUID();
        broker = LoadProperties.getProperties_3("../../../application.properties","mqtt.client.host");
        username = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.username");
        password = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.password");
        //host为主机名,test为clientid即连接MQTT的客户端ID,一般以客户端唯一标识符表示,MemoryPersistence设置clientid的保存形式,默认为以内存保存
        client = new MqttClient(broker, clientId, new MemoryPersistence());
        client.setCallback(this);//设置回调
        subscribeInfo = new HashMap<>();
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        client.connect(options);
    }

    @Override
    public void publish(String topic, String message, int qos) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(PushCallback.toBytes(message));
        mqttMessage.setQos(qos);
        logger.info("开始发布"+topic);
        client.publish(topic, mqttMessage);
    }

    @Override
    public void subscribe(String topic, OnMessageListener listener) throws MqttException {
        try {
            client.subscribe(topic, this);
            logger.info("开始订阅"+topic);
            subscribeInfo.put(topic, listener);
        } catch (MqttException e) {
            subscribeInfo.remove(topic);
            throw e;
        }
    }

    @Override
    public void unSubscribe(String topic) throws MqttException {
        client.unsubscribe(topic);
        logger.info("取消订阅"+topic);
        subscribeInfo.remove(topic);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //获取监听器
        OnMessageListener listener = subscribeInfo.get(topic);
        if (null != listener) {
            logger.info("监听信息："+mqttMessage.toString());
            listener.handleMessage(topic, mqttMessage.toString());
        }
    }
    //消息发送成功方法
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
    ///////////////////////////////////开始回调//////////////////////////////////////////////////
    @Override
    public void close() throws IOException {
        try {
            logger.info("断开客户端");
            client.disconnect();
        } catch (MqttException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("客户端已经断开");
    }


    public static String getTestString(){
        String dzId = "05D4FF373438594D43035359";
        String outParam = "";
        String value1 = Canstats.headerBody;
        //字符串长度/2
        String value2 = "leng";//44字节
        //协议版本
        String value3 = Canstats.headerVersion;
        String value4 = Canstats.dzML;//下发数据
        int time = (int)(System.currentTimeMillis()/1000);
        String timeStamp = PushCallback.toHexString(time);
        //id长度+id号+时间戳+设备类型+程序版本+设备电量
        String value5 = PushCallback.toHexString(dzId.length()/2)+dzId+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
        String value6 = "000B81";
        String value7 = "";
        String value8 = "";//欢迎词
        value7 = Canstats.yxcc;//允许开闸
        value8 = "欢迎光临";
        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));

        logger.info("{}---未CRC校验----发送数据",outParam);

        String outHex = CRC16M.GetModBusCRC(outParam);


        outParam = outParam + outHex;
        logger.info( "{}---CRC校验完成----发送数据2",outParam);
        return outParam;
    }

    public static void sendMsg(String msg,String topic)throws Exception{
        RemoteClient remoteClient = new BasicRemoteClient();
        remoteClient.init();
        remoteClient.subscribe(topic, (t, m) -> {
            logger.info(t + "|" + m);
        });
        remoteClient.publish(topic, msg, Canstats.qos1);

        Thread.sleep(5000);//停3秒
        remoteClient.unSubscribe(topic);
        remoteClient.close();
    }

    public static  void main(String[] args){
        try {
            sendMsg(getTestString(),"MQTT_LF_DZ0");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
