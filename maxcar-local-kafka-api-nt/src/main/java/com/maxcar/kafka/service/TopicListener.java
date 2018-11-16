package com.maxcar.kafka.service;

/**
 * Created by Administrator on 2018/7/12.
 */
import com.maxcar.kafka.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Date;


public class TopicListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${local.servers.address}")
    private String address;
    //游客
    @Value("${local.customer.servers.address}")
    private String customerAddress;


    @Value("${kafka.producer.topic}")
    private String topic;

    @Autowired
    private MessageProducerService messageProducerService;

    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void listener(ConsumerRecord<?, ?> record) {
        try {
            logger.info("kafka的key: " + record.key());
            logger.info("kafka的value: " + record.value().toString());
            processData(record.value()+"");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    /**
     * 反射执行对应请求
     * @param json
     * @return
     */
    public ResponseContent processData(String json) {
        logger.info("开始处理数据-->{}", json);

        json.replace("\\","");
        logger.info("处理后数据-->{}", json);
        try {
//            MessagePojo messagePojo = JsonUtils.parseJsonToObj(json, MessagePojo.class);
            ResponseContent result = null;
            com.alibaba.fastjson.JSONObject.parse(json);
//            JSONObject jsonObject = JSONObject.fromObject(json);
            String jsons = com.alibaba.fastjson.JSONObject.parse(json).toString();
            JSONObject jsonObject = JsonTools.toObj(jsons,JSONObject.class);
            if(jsonObject.get("type").equals("reflect")){//本地请求
                String results = null;
                if(jsonObject.get("serviceName").equals("customerService")){//走未授权接口
                    results = HttpClientUtils.doPostJson(customerAddress,jsons);
                }else{
                    results = HttpClientUtils.doPostJson(address,jsons);
                }
                logger.info("结果-->{}", results);
                result = JsonTools.toObj(results,ResponseContent.class);

            }else if(jsonObject.get("type").equals("url")){//其他服务请求
                result = processData(jsonObject);
            }

            JSONObject sendObj = new JSONObject();
            sendObj.put("market", jsonObject.get("market"));
            sendObj.put("serviceName", jsonObject.getString("serviceName"));
            sendObj.put("methodName", jsonObject.getString("serviceMethod"));
            sendObj.put("messageTime", jsonObject.get("messageTime"));
//            sendObj.put("responseContent", result);
//            JSONObject sendObj = new JSONObject();
            sendObj.put("responseContent", result);//云端回执信息
            messageProducerService.sendMessage(topic,sendObj,false,0,Constants.KAFKA_SASS);

            if (result != null && result.getResultCode() == 0) {

                logger.info("数据处理成功-->{}", json);
            } else {
                logger.info("数据处理失败--{}", json);
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseContent(3, "处理失败");
        }
    }

    private ResponseContent processData(JSONObject obj) {
        try {
            String url = obj.getString("url");
            JSONObject data = obj.getJSONObject("data");
//            if (!data.containsKey("method") || StringUtils.isBlank(data.getString("method"))) {
//                return ResponseContent.error(3, "缺少请求方式");
//            }
//            String method = data.getString("method")!=null?data.getString("method"):"";
            if (data.containsKey("method") && data.getString("method").equals("get")) {
                String doPutJson = HttpClientUtils.doPutJson(url, data.toString());
                com.alibaba.fastjson.JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPutJson);
                ResponseContent result = (ResponseContent) com.alibaba.fastjson.JSONObject.toJavaObject(fromObject, ResponseContent.class);
                return result;
            } else {
                String doPostJson = HttpClientUtils.doPostJson(url, data.toString());
                com.alibaba.fastjson.JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPostJson);
                ResponseContent result = (ResponseContent) com.alibaba.fastjson.JSONObject.toJavaObject(fromObject, ResponseContent.class);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseContent.error(3, "处理失败");
        }
    }

    public static void main(String[] args){
        String json = "{\"serviceMethod\":\"invalidApply\",\"serviceName\":\"customerService\",\"serviceParams\":{\"carId\":\"001105\",\"token\":\"c47acb7f8b70336a19c7abad129939d8c716bbe9\",\"user\":\"0012\",\"market\":\"001\"},\"type\":\"reflect\"}";
//        JSONObject jsonObject = JSONObject.fromObject(json);
        System.out.print(json.replace("\\",""));
        String results = HttpClientUtils.doPostJson("http://47.96.29.224:2222/maxcar/customer/service",json);
        ResponseContent result = JsonTools.toObj(results,ResponseContent.class);
    }
}