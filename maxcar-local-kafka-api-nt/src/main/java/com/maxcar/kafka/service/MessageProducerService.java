package com.maxcar.kafka.service;

import com.maxcar.kafka.pojo.MessagePojo;
import com.maxcar.kafka.util.Result;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * @author 罗顺峰
 * @create 2018-07-14 16:09
 * @desc 卡夫卡，消息生产者
 **/
public interface MessageProducerService {
    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param data    数据
     * @param ifPartition 是否使用分区 true是\false不是
     * @param partitionNum 分区数 如果否使用分区为0,分区数必须大于0
     * @param role 角色用你来生产动态的key:bbc app erp.sass..
     */
    Result sendMessage(String topic, Object data, Boolean ifPartition,
                       Integer partitionNum, String role);
}
