package com.maxcar.mqtt.service;


//定义一个监听器
@FunctionalInterface
public interface OnMessageListener {
    void handleMessage(String topic, String message);
}