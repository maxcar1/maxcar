package com.maxcar.base.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTemplateUtil {

    public static final String DEF_REGEX = "\\{(.+?)\\}";

    public static String readFile(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            return IOUtils.toString(is,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String replaceVar(String template, Map<String, String> data) {
        return replaceVar(template, data, DEF_REGEX);
    }

    public static String replaceVar(String template, Map<String, String> data, String regex) {
        if (StringUtils.isBlank(template)) {
            return "";
        }
        if (StringUtils.isBlank(regex)) {
            return template;
        }
        if (data == null || data.size() == 0) {
            return template;
        }
        try {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                String name = matcher.group(1);
                String value = data.get(name);
                if (value == null) {
                    value = "";
                }
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;

    }

    public static void main(String args[]) {
        String html = HttpClientUtils.sendGet("https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/010/transfer/18103017232348261390/maxcar_1540891403499.html");
        if (StringUtils.isNotBlank(html)) {
            Map<String, String> data = new HashMap<>(4);
            data.put("name", "全车通");
            data.put("car", "二手车");
            System.out.println(replaceVar(html, data));
        }
    }
}
