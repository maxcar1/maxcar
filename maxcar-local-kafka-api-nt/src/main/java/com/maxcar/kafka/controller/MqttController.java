package com.maxcar.kafka.controller;

import com.maxcar.mqtt.service.MqttProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/7/11.
 */
@RestController
@RequestMapping(value={"/mqtt"})
public class MqttController {

    Logger logger = LoggerFactory.getLogger(MqttController.class);

    @Autowired
    private MqttProducerService mqttProducerService;

    @RequestMapping(value={"/","/index"})
    public String index(HttpServletRequest request) {
        try {
            System.out.print(111);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Hello World!";
    }

    @RequestMapping(value={"/send/{topic}/{data}"})
    public String send(HttpServletRequest request, @PathVariable("topic")String topic, @PathVariable("data")String data) {
        try {
            mqttProducerService.send(topic,data);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Hello World!";
    }


}
