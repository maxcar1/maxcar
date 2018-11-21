package com.maxcar.kafka.service;

import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.util.HttpClientUtil;
import com.maxcar.util.HttpClientUtils;
import com.maxcar.util.JsonTools;
import com.maxcar.util.PostParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.spring.web.json.Json;

//统一数据处理类
public class DownloadData {

    protected static final Logger logger = LoggerFactory.getLogger(DownloadData.class);
    //预处理数据
    public static InterfaceResult processData(String json,String address) {
        InterfaceResult result = new InterfaceResult();
        logger.info("开始处理数据-->{}", json);
        json.replace("\\","");
        try {

            PostParam postParam = JsonTools.toObj(json, PostParam.class);
            result = processData(postParam,address);//本地执行
            postParam.setInterfaceResult(result);//云端回执信息
            if (result != null && result.getCode().equals("200")) {
                logger.info(json + "数据处理成功-->{}", result.getMsg());
            } else {
                logger.info(json + "数据处理失败--{}", result.getMsg());
            }
        } catch (Exception e) {
            result.InterfaceResult500("处理失败");
        }
        return result;
    }
    //处理请求
    private static InterfaceResult processData(PostParam postParam,String address) {
        InterfaceResult result = new InterfaceResult();
        try {
            String method = postParam.getMethod();
            String url = address+postParam.getUrl();
            if (method !=null && method.equals("get")) {
                String doPutJson = HttpClientUtil.get(url, null,null);
//                result = JsonTools.toObj(doPutJson,InterfaceResult.class);
            } else{
                String doPostJson = HttpClientUtils.doPostJson(url, postParam.getData());
                logger.info("服务器返回结果--{}", doPostJson);
                result = JsonTools.toObj(doPostJson.toString(),InterfaceResult.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.InterfaceResult500("处理失败");
        }
        return result;
    }
}
