package com.maxcar.core.utils.api;

import com.maxcar.core.utils.MD5Util;
import com.maxcar.core.utils.SHAUtil;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生成appKey和appSecret
 * yangsj
 */
public class ApiUtils {

    private static ApiUtils instance = null;

    private ApiUtils(){}

    public static ApiUtils getInstance(){
        synchronized (ApiUtils.class){
            if (instance == null){
                return new ApiUtils();
            }
        }
        return null;
    }

    public String generorAppKey(String generStr){
        long timestamp = System.currentTimeMillis();
        StringBuffer key = new StringBuffer(generStr);
        key.append("&timestamp=").append(timestamp).append(UUID.randomUUID());
        String str = MD5Util.MD5(String.valueOf(key));
        String appKey = str.substring(str.length()-20,str.length());
        return appKey;
    }

    public String generorAppSecret(String key){
        String str = MD5Util.MD5(String.valueOf(key));
        String appSecret = SHAUtil.getSha1(str);
        String secret = appSecret.substring(0,appSecret.length()-8);
        return secret;
    }

    /**
     * 校验ip
     * @param addr
     * @return
     */
    public boolean isIP(String addr){
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
        {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;
    }
    public static void main(String[] args){
        System.out.println(ApiUtils.getInstance().isIP("127.0."));
    }
}
