package com.maxcar.mqtt.service;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.Closeable;

/**
 * Created by 罗顺锋 on 2018/11/31.
 * 客户端接口
 */
public interface RemoteClient extends Closeable {
    /**
     * @throws MqttException 异常
     */
    void init() throws MqttException;

    /**
     * 发布消息
     *
     * @param topic   主题
     * @param message 消息
     * @param qos     传输等级
     * @throws MqttException 异常
     */
    void publish(String topic, String message, int qos) throws MqttException;

    /**
     * 订阅消息
     *
     * @param topic    主题
     * @param listener 监听器
     * @throws MqttException 异常
     */
    void subscribe(String topic, OnMessageListener listener) throws MqttException;

    /**
     * 取消订阅
     *
     * @param topic 主题
     * @throws MqttException 异常
     */
    void unSubscribe(String topic) throws MqttException;
}
