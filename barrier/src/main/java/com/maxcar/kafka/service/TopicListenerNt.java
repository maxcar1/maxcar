package com.maxcar.kafka.service;

/**
 * 监听云端服务
 * Created by Administrator on 2018/7/12.
 */

//import com.alibaba.fastjson.JSONObject;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.util.HttpClientUtil;
import com.maxcar.util.HttpClientUtils;
import com.maxcar.util.JsonTools;
import com.maxcar.util.PostParam;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;


public class TopicListenerNt {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${kafka.producer.topic}")
    private String topic;
    @Value("${local.servers.address}")
    private String address;

    //南通监听
    @KafkaListener(topics = {"${kafka.consumer.topic007}"})
    public void listener(ConsumerRecord<?, ?> record) {
        try {

            logger.info("南通市场接收的key: " + record.key());
            logger.info("南通市场接收的value: " + record.value().toString());
            DownloadData.processData(record.value()+"",address);
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }
}