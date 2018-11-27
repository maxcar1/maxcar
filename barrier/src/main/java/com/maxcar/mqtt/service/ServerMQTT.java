package com.maxcar.mqtt.service;

import com.maxcar.util.*;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title:Server 这是发送消息的服务端
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author luoshunfeng
 */

public class ServerMQTT extends Thread{

    static Logger logger = LoggerFactory.getLogger(ServerMQTT.class);
    private MqttClient client;
    private String mqttClientHost;
    private String clientId;
 //   private MqttTopic mqttTopic;
    private MqttMessage message;
    private String username;
    private String password;

    private byte[] data;
    private String topic;

    public volatile boolean exit = false;

    private static Lock lock = new ReentrantLock();

    public ServerMQTT(String topic,byte[] data)throws MqttException{
        this.topic = topic;
        this.data = data;
        long timestamps = System.currentTimeMillis();
        clientId = String.valueOf(timestamps)+UuidUtils.getRandByNum(4);
        mqttClientHost = LoadProperties.getProperties_3("../../../application.properties","mqtt.client.host");
        username = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.username");
        password = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.password");
        //host为主机名,test为clientid即连接MQTT的客户端ID,一般以客户端唯一标识符表示,MemoryPersistence设置clientid的保存形式,默认为以内存保存
        client = new MqttClient(mqttClientHost, clientId, new MemoryPersistence());
    }

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT()throws MqttException{
        /*clientId = LoadProperties.getProperties_3("../../../application.properties","serviceid");*/
        long timestamps = System.currentTimeMillis();
        clientId = String.valueOf(timestamps)+UuidUtils.getRandByNum(4);
        mqttClientHost = LoadProperties.getProperties_3("../../../application.properties","mqtt.client.host");
        username = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.username");
        password = LoadProperties.getProperties_3("../../../application.properties","mqtt.server.password");
        //host为主机名,test为clientid即连接MQTT的客户端ID,一般以客户端唯一标识符表示,MemoryPersistence设置clientid的保存形式,默认为以内存保存
        client = new MqttClient(mqttClientHost, clientId, new MemoryPersistence());

    }
    //初始化数据
    public void init(String topic){
        connect(topic);
    }

    /**
     *  用来连接服务器
     */
    private void connect(String topic) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录,这里设置为true表示每次连接到服务器都以新的身份连接
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(60*10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*10秒的时间向客户端发送个消息判断客户端是否在线,但这个方法并没有重连的机制
        options.setKeepAliveInterval(60*10);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
           /* mqttTopic = client.getTopic(topic);
            if (null == mqttTopic){
                ServerMQTT serverMQTT = new ServerMQTT();
                mqttTopic = serverMQTT.client.getTopic(topic);
            }*/
            logger.info("已建立客户端连接,准备发送消息");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttMessage message,String topic) throws MqttPersistenceException,
            MqttException {
        try {
            lock.lock();
            logger.info("发送消息主体!topic==>{},内容==>{}",topic,message);
            if (null == client){
                ServerMQTT se = new ServerMQTT();
                this.client = se.client;
            }
            client.publish(topic,message);
/*
            MqttDeliveryToken token = mqttTopic.publish(message);
            token.waitForCompletion();*/
            /*logger.info("服务端消息已经发送! "
                    + token.isComplete());*/
            logger.info("服务端消息已经发送!");
        }finally {
            lock.unlock();
        }
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
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
        send(outParam,"MQTT_YL_DZ0");
    }

    //主动触发开闸关闸
    public static void send(String outParam,String topic) {
        try {
            ServerMQTT server = new ServerMQTT();
            server.init(topic);
            server.message = new MqttMessage();
            server.message.setQos(Canstats.qos1);  //保证消息能到达一次
            server.message.setRetained(false);//是否保持连接，客户端会适时发送
            server.message.setPayload(PushCallback.toBytes(outParam));
            logger.info(outParam + "------发送数据");
            server.publish(server.message,topic);
            logger.info(server.message.isRetained() + "------ratained状态");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public void send(byte[] data,String topic) {
        try {
            lock.lock();
//            String TOPIC = (topic == null || topic.equals("")) ? TOPIC : topic;
//            topic11 = client.getTopic((topic == null || topic.equals("")) ? TOPIC : topic);
            this.init(topic);
            this.message = new MqttMessage();
            this.message.setQos(Canstats.qos1);  //保证消息能到达一次
            this.message.setRetained(false);//是否保持连接，客户端会适时发送
            this.message.setPayload(data);
            this.publish(this.message,topic);
            logger.info(this.message.isRetained() + "------ratained状态");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while(!exit){
            this.send(data,topic);
        }
    }
}