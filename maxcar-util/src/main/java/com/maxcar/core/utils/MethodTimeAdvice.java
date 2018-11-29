package com.maxcar.core.utils;

import org.aopalliance.intercept.MethodInterceptor;  
import org.aopalliance.intercept.MethodInvocation;  
import org.apache.commons.lang.time.StopWatch;  
  
public class MethodTimeAdvice implements MethodInterceptor {  
  
   /* private static final Logger Log = LoggerFactory  
            .getLogger(MethodTimeAdvice.class);  */
  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        // 用 commons-lang 提供的 StopWatch 计时  
        StopWatch clock = new StopWatch();
        clock.start(); // 计时开始  
        Object result = invocation.proceed();  
        clock.stop(); // 计时结束  
  
       /* // 方法参数类型，转换成简单类型  
        Class[] params = invocation.getMethod().getParameterTypes();  
        String[] simpleParams = new String[params.length];  
        for (int i = 0; i < params.length; i++) {  
            simpleParams[i] = params[i].getSimpleName();  
        }  
        Object[] args = invocation.getArguments();  */
  
       //将响应时间,请求接口名计算  存入数据库中 待实现
       /* Log.info("Takes:" + clock.getTime() + " ms ["  
                + invocation.getThis().getClass().getName() + "."  
                + invocation.getMethod().getName() + "("  s
                + StringUtils.join(simpleParams, ",") + ")("  
                + StringUtils.join(args, ",") + ")] ");*/
       
        
        return result;
    }  
  
}  