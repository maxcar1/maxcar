package com.maxcar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 罗顺锋
 * springmvc 资源过滤器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SystemInterceptor systemInterceptor;

    /**
     * @Description: addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     * @Param: [registry]
     * @return: void
     * @Author: 罗顺锋
     * @Date: 2018/5/18
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/login", "/error", "/api/send/sms-vcode",
                        "/api/market/tenant/role", "/api/switch/tenant", "/api/notify/alipay",
                        "/api/notify/wx-pay", "/api/version");
    }
}