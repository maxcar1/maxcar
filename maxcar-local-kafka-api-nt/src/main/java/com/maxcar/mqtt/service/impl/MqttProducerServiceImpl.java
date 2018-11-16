package com.maxcar.mqtt.service.impl;

import com.maxcar.mqtt.service.MqttMessageGateway;
import com.maxcar.mqtt.service.MqttProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * mqtt发送者
 * Created by Administrator on 2018/8/16.
 */
@Service
public class MqttProducerServiceImpl implements MqttProducerService{
    @Autowired
    private MqttMessageGateway gateway;
    @Override
    public void send(String topic,String data) {
        Message<String> message = MessageBuilder.withPayload(data)
                .setHeader(MqttHeaders.TOPIC, topic).build();
        gateway.sendMessage(message);
    }
}
