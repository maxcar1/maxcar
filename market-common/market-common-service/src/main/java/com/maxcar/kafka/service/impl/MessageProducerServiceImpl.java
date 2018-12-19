package com.maxcar.kafka.service.impl;

import com.maxcar.base.util.dasouche.Result;
import com.maxcar.kafka.service.MessageProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author 罗顺峰
 * @create 2018-07-14 16:10
 * @desc 卡夫卡消息提供者实现类
 **/
@Service("messageProducerService")
public class MessageProducerServiceImpl implements MessageProducerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private KafkaTemplate kafkaTemplate;


    @Value("${kafka.producer.topic}")
    private String upTopic;


    @Value("${kafka.producer.topic}")
    public String producerTopic;
    @Value("${kafka.consumer.topic006}")
    public String consumerTopic6;
    @Value("${kafka.consumer.topic007}")
    public String consumerTopic7;
    @Value("${kafka.consumer.topic008}")
    public String consumerTopic8;
    @Value("${kafka.consumer.topic009}")
    public String consumerTopic9;
    @Value("${kafka.consumer.topic010}")
    public String consumerTopic10;
    @Value("${kafka.consumer.topic011}")
    public String consumerTopic11;
    @Value("${kafka.consumer.topic012}")
    public String consumerTopic12;
    @Value("${kafka.consumer.topic013}")
    public String consumerTopic13;
    @Value("${kafka.consumer.topic014}")
    public String consumerTopic14;
    @Value("${kafka.consumer.topic015}")
    public String consumerTopic15;
    @Value("${kafka.consumer.topic016}")
    public String consumerTopic16;
    @Value("${kafka.consumer.topic017}")
    public String consumerTopic17;
    @Value("${kafka.consumer.topic018}")
    public String consumerTopic18;
    @Value("${kafka.consumer.topic019}")
    public String consumerTopic19;
    @Value("${kafka.consumer.topic020}")
    public String consumerTopic20;
    @Value("${kafka.consumer.topic021}")
    public String consumerTopic21;
    @Value("${kafka.consumer.topic022}")
    public String consumerTopic22;
    @Value("${kafka.consumer.topic023}")
    public String consumerTopic23;
    @Value("${kafka.consumer.topic024}")
    public String consumerTopic24;
    @Value("${kafka.consumer.topic025}")
    public String consumerTopic25;

    @Value("${kafka.consumer.topic026}")
    public String consumerTopic26;
    @Value("${kafka.consumer.topic027}")
    public String consumerTopic27;
    @Value("${kafka.consumer.topic028}")
    public String consumerTopic28;
    @Value("${kafka.consumer.topic029}")
    public String consumerTopic29;
    @Value("${kafka.consumer.topic030}")
    public String consumerTopic30;
    @Value("${kafka.consumer.topic031}")
    public String consumerTopic31;
    @Value("${kafka.consumer.topic032}")
    public String consumerTopic32;
    @Value("${kafka.consumer.topic033}")
    public String consumerTopic33;
    @Value("${kafka.consumer.topic034}")
    public String consumerTopic34;
    @Value("${kafka.consumer.topic035}")
    public String consumerTopic35;
    @Value("${kafka.consumer.topic036}")
    public String consumerTopic36;
    @Value("${kafka.consumer.topic037}")
    public String consumerTopic37;
    @Value("${kafka.consumer.topic038}")
    public String consumerTopic38;
    @Value("${kafka.consumer.topic039}")
    public String consumerTopic39;
    @Value("${kafka.consumer.topic040}")
    public String consumerTopic40;
    @Value("${kafka.consumer.topic041}")
    public String consumerTopic41;
    @Value("${kafka.consumer.topic042}")
    public String consumerTopic42;
    @Value("${kafka.consumer.topic043}")
    public String consumerTopic43;
    @Value("${kafka.consumer.topic044}")
    public String consumerTopic44;
    @Value("${kafka.consumer.topic045}")
    public String consumerTopic45;
    @Value("${kafka.consumer.topic046}")
    public String consumerTopic46;
    @Value("${kafka.consumer.topic047}")
    public String consumerTopic47;
    @Value("${kafka.consumer.topic048}")
    public String consumerTopic48;
    @Value("${kafka.consumer.topic049}")
    public String consumerTopic49;
    @Value("${kafka.consumer.topic050}")
    public String consumerTopic50;
    @Value("${kafka.consumer.topic051}")
    public String consumerTopic51;
    @Value("${kafka.consumer.topic052}")
    public String consumerTopic52;
    @Value("${kafka.consumer.topic053}")
    public String consumerTopic53;
    @Value("${kafka.consumer.topic054}")
    public String consumerTopic54;
    @Value("${kafka.consumer.topic055}")
    public String consumerTopic55;

    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param data    数据
     * @param ifPartition 是否使用分区 0是\1不是
     * @param partitionNum 分区数 如果否使用分区为0,分区数必须大于0
     * @param role 角色用你来生产动态的key:bbc app erp..sass.
     */
    public Result sendMessage(String topic, Object data, Boolean ifPartition, Integer partitionNum, String role){
        topic = topic.equals("-1")?upTopic:topic;
        String key = role+"-"+data.hashCode();
//        String valueString = JSON.toJSONString(data);
        if(ifPartition){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, data.toString());
            Result res = checkProRecord(result);
            return res;
        }else{
//            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            kafkaTemplate.send(topic, key, data.toString());
//            Result res = checkProRecord(result);
            return Result.ok();
        }
    }

    @Override
    public String getTopic(String marketId) throws Exception {
        String topic = consumerTopic7;
        switch (marketId) {
            case "006":
                topic = consumerTopic6;
                break;
            case "007":
                topic = consumerTopic7;
                break;
            case "008":
                topic = consumerTopic8;
                break;
            case "009":
                topic = consumerTopic9;
                break;
            case "010":
                topic = consumerTopic10;
                break;
            case "011":
                topic = consumerTopic11;
                break;
            case "012":
                topic = consumerTopic12;
                break;
            case "013":
                topic = consumerTopic13;
                break;
            case "014":
                topic = consumerTopic14;
                break;
            case "015":
                topic = consumerTopic15;
                break;
            case "016":
                topic = consumerTopic16;
                break;
            case "017":
                topic = consumerTopic17;
                break;
            case "018":
                topic = consumerTopic18;
                break;
            case "019":
                topic = consumerTopic19;
                break;
            case "020":
                topic = consumerTopic20;
                break;
            case "021":
                topic = consumerTopic21;
                break;
            case "022":
                topic = consumerTopic22;
                break;
            case "023":
                topic = consumerTopic23;
                break;
            case "024":
                topic = consumerTopic24;
                break;
            case "025":
                topic = consumerTopic25;
                break;
            case "026":
                topic = consumerTopic26;
                break;
            case "027":
                topic = consumerTopic27;
                break;
            case "028":
                topic = consumerTopic28;
                break;
            case "029":
                topic = consumerTopic29;
                break;
            case "030":
                topic = consumerTopic30;
                break;
            case "031":
                topic = consumerTopic31;
                break;
            case "032":
                topic = consumerTopic32;
                break;
            case "033":
                topic = consumerTopic33;
                break;
            case "034":
                topic = consumerTopic34;
                break;
            case "035":
                topic = consumerTopic35;
                break;
            case "036":
                topic = consumerTopic36;
                break;
            case "037":
                topic = consumerTopic37;
                break;
            case "038":
                topic = consumerTopic38;
                break;
            case "039":
                topic = consumerTopic39;
                break;
            case "040":
                topic = consumerTopic40;
                break;
            case "041":
                topic = consumerTopic41;
                break;
            case "042":
                topic = consumerTopic42;
                break;
            case "043":
                topic = consumerTopic43;
                break;
            case "044":
                topic = consumerTopic44;
                break;
            case "045":
                topic = consumerTopic45;
                break;
            case "046":
                topic = consumerTopic46;
                break;
            case "047":
                topic = consumerTopic47;
                break;
            case "048":
                topic = consumerTopic48;
                break;
            case "049":
                topic = consumerTopic49;
                break;
            case "050":
                topic = consumerTopic50;
                break;
            case "051":
                topic = consumerTopic51;
                break;
            case "052":
                topic = consumerTopic52;
                break;
            case "053":
                topic = consumerTopic53;
                break;
            case "054":
                topic = consumerTopic54;
                break;
            case "055":
                topic = consumerTopic55;
                break;
        }
        return topic;
    }

    /**
     * 根据key值获取分区索引
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum){
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        }
        else {
            int result = Math.abs(key.hashCode())%partitionNum;
            return result;
        }
    }

    /**
     * 检查发送返回结果record
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Result checkProRecord(ListenableFuture<SendResult<String, String>> res){
        if(res!=null){
            try {
                SendResult r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                Long offsetIndex = r.getRecordMetadata().offset();
                if(offsetIndex!=null && offsetIndex>=0){
                    return Result.ok();
                }else{
                    return Result.error("未查到返回数据的offset");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.error("发送消息超时");
            } catch (ExecutionException e) {
                e.printStackTrace();
                return Result.error("发送消息超时");
            }
        }else{
            return Result.error("未查询到返回结果");
        }
    }
}
