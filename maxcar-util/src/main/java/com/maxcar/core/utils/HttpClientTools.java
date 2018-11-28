package com.maxcar.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * http请求工具类
 */
public class HttpClientTools {
    public static final CloseableHttpClient httpClient;
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static final int CONNECT_TIME_OUT = 5000 * 2;
    private static final int SOCKET_TIME_OUT = 2 * 60 *1000;//会话时间2分钟

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * json参数的post请求
     * @param url 请求url
     * @param jsonStr 请求json
     * @return return message
     */
    public static String doPostWithJson(String url, String jsonStr) {
        return doPostWithJson(null, url, jsonStr, CHARSET_UTF8);
    }

    /**
     * json参数的post请求
     * @param client CloseableHttpClient
     * @param url 请求url
     * @param jsonStr 请求json
     * @param charset 字符集
     * @return return message
     */
    public static String doPostWithJson(CloseableHttpClient client, String url, String jsonStr, String charset) {
        if (client == null) client = httpClient;
        Logger.getRootLogger().info("执行 Post httpClient请求-->"+url);
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if (StringUtils.isNotBlank(jsonStr)) {
                StringEntity se = new StringEntity(jsonStr, charset);
                se.setContentType(CONTENT_TYPE_TEXT_JSON);
                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
                httpPost.setEntity(se);
            }
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HTTP错误响应码[" + statusCode + "]");
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("HTTP JSON POST请求失败", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (Exception ex) {
                	Logger.getRootLogger().error("response关闭异常", ex);
                }
            }
        }
    }

}
