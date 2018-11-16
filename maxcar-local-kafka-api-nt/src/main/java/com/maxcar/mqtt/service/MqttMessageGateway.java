package com.maxcar.mqtt.service;

/**
 * 消息发送接口
 * Created by Administrator on 2018/8/16.
 */

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * 消息发送接口，不需要实现，spring会通过代理的方式实现
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {
    void sendMessage(Message<?> message);
}