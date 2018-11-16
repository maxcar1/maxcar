package com.maxcar.mqtt.service;

/**
 * mqtt发送者
 * Created by Administrator on 2018/8/16.
 */
public interface MqttProducerService {
    /**
     * 发送指定topic以及内容
     * @param topic
     * @param data
     */
    void send(String topic,String data);
}
