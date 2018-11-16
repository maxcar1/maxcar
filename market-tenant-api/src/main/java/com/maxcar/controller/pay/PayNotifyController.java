package com.maxcar.controller.pay;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.maxcar.base.util.XmlUtil;
import com.maxcar.tenant.app.bean.WxPayResponseBean;
import com.maxcar.tenant.app.service.PayNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/notify")
public class PayNotifyController {

    private static Logger logger = LoggerFactory.getLogger(PayNotifyController.class);

    @Autowired
    private PayNotifyService payNotifyService;

    /**
     * 微信支付回调通知
     *
     * @param xml
     * @return
     */
    @PostMapping("/wx-pay")
    public String wxPayNotify(@RequestBody String xml) {
        try {
            Preconditions.checkNotNull(xml);
            Boolean result = payNotifyService.handleWxPayNotify(xml);
            if (result) {
                return XmlUtil.beanToXml(WxPayResponseBean.successResult(), WxPayResponseBean.class);
            }
        } catch (Exception e) {
            logger.error("处理回调异常", e);
        }
        return XmlUtil.beanToXml(WxPayResponseBean.failResult("通知回调处理失败"), WxPayResponseBean.class);
    }

    /**
     * 支付宝支付回调通知
     *
     * @param request
     * @return
     */
    @PostMapping("/alipay")
    public String alipayNotify(HttpServletRequest request) {
        try {

            Map<String, String> data = new TreeMap<>();
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                data.put(key, request.getParameter(key));
            }

            logger.info("alipay.notify.params:{}", JSONObject.toJSONString(data));

            boolean result = payNotifyService.handleAlipayNotify(data);

            if (result) {
                return "success";
            }
        } catch (Exception e) {
            logger.error("处理支付宝回调异常", e);
        }
        return "fail";
    }

}
