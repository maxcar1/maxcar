package com.maxcar.kafka.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 罗顺峰
 * @create 2018-07-18 10:03
 * @desc json复杂对象转换
 **/
public class JsonUtils {
    /**
     * 把Java对象转换成json字符串
     *
     * @param object 待转化为JSON字符串的Java对象
     * @return json 串 or null
     */
    public static String parseObjToJson(Object object) {
        String string = null;
        try {
            //string = JSON.toJSONString(object);
            string = JSONObject.toJSONString(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return string;
    }

    /**
     * 将Json字符串信息转换成对应的Java对象
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            //两个都是可行的，起码我测试的时候是没问题的。
            //JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject jsonObject = JSON.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
