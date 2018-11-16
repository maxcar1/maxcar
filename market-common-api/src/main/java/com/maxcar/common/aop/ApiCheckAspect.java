package com.maxcar.common.aop;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.OpenApiConfig;
import com.maxcar.base.service.OpenApiConfigService;
import com.maxcar.base.util.NetworkUtil;
import com.maxcar.base.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 对外接口拦截aop
 * yangsj
 */
@Component
@Aspect
@Configuration
@Order(1)
public class ApiCheckAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCheckAspect.class);
    @Autowired
    private OpenApiConfigService openApiConfigService;

    @Pointcut("target(com.maxcar.common.controller.CarInfoOpenController) || target(com.maxcar.common.controller.ParkingFeeController)")
    public void check(){}

    /**
     * 校验ip，appKey 和 appSecret
     * @param joinPoint
     */
    @Before("check()")
    public void doBefore(JoinPoint joinPoint) { }

    /**
     * 环绕通知处理校验
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("check()")
    public Object trackInfo(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("进入环绕通知,请求参数==>{}",pjp.getArgs());
        InterfaceResult result = checkParam(request);
        InterfaceResult openApiResult = null;
        if (result.getCode().equals("200")){
            openApiResult = (InterfaceResult)pjp.proceed();
        }else{
            return result;
        }
       return openApiResult;
    }

    private InterfaceResult checkParam(HttpServletRequest request) throws IOException{
//        System.out.println(request.getRemoteAddr()+"=================");
        String  ip = NetworkUtil.getIpAddress(request);
        InterfaceResult result = new InterfaceResult();
        String appKey = request.getParameter("appKey");
        String appSecret = request.getParameter("appSecret");
        String method = request.getMethod();
        LOGGER.info("ip===>>{}",ip);
        LOGGER.info("method===>>{}",method);
        LOGGER.info("appKey===>>{}",appKey);
        LOGGER.info("appSecret===>>{}",appSecret);
        if (StringUtils.isBlank(appKey)){
            result.InterfaceResult600("Invalid appKey,Please check");
        }else{
            OpenApiConfig openApiConfig = openApiConfigService.checkSecret(appKey);
            if (null == openApiConfig){
                result.InterfaceResult600("Invalid appKey,Please check");
            }else{
                if (!StringUtils.equals(openApiConfig.getAppSecret(),appSecret)){
                    result.InterfaceResult600("Invalid appSecret,Please check");
                }else if(openApiConfig.getIsIp() == 1){
                    //有ip校验
                    if (!openApiConfig.getIp().contains(ip)){
                        result.InterfaceResult600("Ip Illegal,Please check");
                    }else{
                        result.InterfaceResult200("ok");
                    }
                }else{
                    result.InterfaceResult200("ok");
                }
            }
        }

        return result;
    }
}
