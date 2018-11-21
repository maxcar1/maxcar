package com.maxcar.tenant.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.XmlUtil;
import com.maxcar.base.util.dasouche.HttpClientUtil;
import com.maxcar.base.util.pay.PayUtil;
import com.maxcar.tenant.app.bean.WxPayCreateBean;
import com.maxcar.tenant.app.dao.ChargeOrderDetailMapper;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.enums.ChargeStateEnum;
import com.maxcar.tenant.app.enums.ChargeTypeEnum;
import com.maxcar.tenant.app.service.WxPayService;
import com.maxcar.tenant.app.vo.WxPreChargeResultVo;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Service("wxPayService")
@Slf4j
@SuppressWarnings("all")
public class WxPayServiceImpl implements WxPayService {

    private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String PACKAGE_VALUE = "Sign=WXPay";

    private static final String APP_ID = "wxbd0775e6a6430ef0";
    private static final String MCH_ID = "1516912091";
    public static final String PAY_KEY = "665e86c853014554b5772768f7924f71";

    @Value("${wx.pay.app.notify.url}")
    public String NOTIFY_URL;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private ChargeOrderDetailMapper chargeOrderDetailMapper;

    public InterfaceResult wxCharge(String staffId, String ip, int feeType, String transferCarNo) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        String appId = APP_ID;
        String mchId = MCH_ID;
        String payKey = PAY_KEY;

        Staff staff = staffService.selectByPrimaryId(staffId);
        Market market = marketService.getMarketById(staff.getMarketId());
        int chargeMoney = market.getQualityServiceFee() * 100;
        if (feeType == 1) {

            if (StringUtils.isBlank(market.getPayWechatAppId())
                    || StringUtils.isBlank(market.getPayWechatMchId())
                    || StringUtils.isBlank(market.getPayWeChatKey())) {
                interfaceResult.InterfaceResult600("市场方不支持在线过户");
                return interfaceResult;
            }

            appId = market.getPayWechatAppId();
            mchId = market.getPayWechatMchId();
            payKey = market.getPayWeChatKey();
            chargeMoney = market.getChangeTheNamePrice() * 100;
        }

        String chargeOderId = UuidUtils.generateIdentifier();

        // 调用微信支付生成预支付订单号
        WxPayCreateBean params = new WxPayCreateBean(chargeOderId, ip, chargeMoney, feeType);

        String precharge = preCharge(params, appId, mchId, payKey);
        if (StringUtils.isNotBlank(precharge)) {

            if ("FAIL".equals(precharge)) {
                interfaceResult.InterfaceResult600("微信签名失败");
                return interfaceResult;
            }

            ChargeOrderDetail chargeOrderDetail = new ChargeOrderDetail();
            chargeOrderDetail.setId(chargeOderId);
            chargeOrderDetail.setChargeMoney(chargeMoney);
            chargeOrderDetail.setChargeType(ChargeTypeEnum.WXPAY_APP.value());
            chargeOrderDetail.setFeeType(feeType);
            chargeOrderDetail.setState(ChargeStateEnum.CREATE.value());
            chargeOrderDetail.setStaffId(staffId);
            chargeOrderDetail.setPrepayId(precharge);
            chargeOrderDetail.setTransferCarNo(transferCarNo);
            chargeOrderDetailMapper.insert(chargeOrderDetail);

            WxPreChargeResultVo result = new WxPreChargeResultVo(precharge);
            TreeMap<String, String> paramsMap = getParamsMap(result, appId, mchId);
            String sign = buildSign(paramsMap, payKey);
            result.setSign(sign);
            Map<String, String> map = toParamsMap(result, mchId);
            map.put("chargeOrderId", chargeOderId);
            interfaceResult.InterfaceResult200(map);
        }

        return interfaceResult;
    }

    private String preCharge(WxPayCreateBean payBean, String appId, String mchId, String payKey) throws Exception {

        payBean.setAppid(appId);
        payBean.setMch_id(mchId);
        payBean.setNotify_url(NOTIFY_URL);

        Map<String, String> map = (Map) JSON.toJSON(payBean);
        String sign = this.buildSign(new TreeMap<String, String>(map), payKey);
        payBean.setSign(sign);

        String xml = XmlUtil.beanToXml(payBean, WxPayCreateBean.class);
        xml = xml.substring(xml.indexOf('\n') + 1, xml.length());
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(UNIFIED_ORDER_URL);
        HttpEntity requestEntity = new StringEntity(xml, "UTF-8");
        post.setEntity(requestEntity);
        post.setConfig(HttpClientUtil.getTimeoutConfig());
        CloseableHttpResponse response = client.execute(post);
        try {
            String body = EntityUtils.toString(response.getEntity(), "utf-8");
            log.debug("wx.preCharge body:{}", body);
            if (StringUtils.isNotBlank(body)) {
                Element dom = DocumentHelper.parseText(body).getRootElement();
                if (dom.elementTextTrim("return_code").equals("FAIL")) {
                    return "FAIL";
                }
                return dom.elementTextTrim("prepay_id");
            }
        } finally {
            response.close();
        }
        return null;
    }

    @Override
    public TreeMap<String, String> parseNotify(String xml) throws Exception {
        TreeMap<String, String> params = new TreeMap<>();
        if (StringUtils.isNotBlank(xml)) {
            Element dom = DocumentHelper.parseText(xml).getRootElement();
            Iterator<Element> iterator = dom.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                params.put(element.getName(), element.getStringValue());
            }
        }
        return params;
    }

    @Override
    public String buildSign(TreeMap<String, String> params, String payKey) {

        StringBuilder builder = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(value) && !"sign".equals(key)) {
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                    builder.append("&");
                }
            }
        }
        builder.append("key=" + payKey);
        try {
            return PayUtil.toHexValue(PayUtil.encryptMD5(builder.toString().getBytes(Charset.forName("utf-8")))).toUpperCase();
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new RuntimeException("md5 error");
        }
    }

    TreeMap<String, String> getParamsMap(WxPreChargeResultVo result, String appId, String mchId) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", appId);
        params.put("partnerid", mchId);
        params.put("prepayid", result.getPrepayid());
        params.put("package", PACKAGE_VALUE);
        params.put("noncestr", result.getNoncestr());
        params.put("timestamp", result.getTimestamp());
        return params;
    }

    Map<String, String> toParamsMap(WxPreChargeResultVo result, String mchId) {
        Map<String, String> params = new HashMap<>();
        params.put("partnerId", mchId);
        params.put("prepayId", result.getPrepayid());
        params.put("package", PACKAGE_VALUE);
        params.put("nonceStr", result.getNoncestr());
        params.put("timestamp", result.getTimestamp());
        params.put("sign", result.getSign());
        return params;
    }

}
