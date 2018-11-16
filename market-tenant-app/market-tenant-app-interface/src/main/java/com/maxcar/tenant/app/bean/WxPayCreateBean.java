package com.maxcar.tenant.app.bean;


import org.apache.commons.lang.RandomStringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 因为要转换为xml,并且和腾讯的接口对应,所以会出现下划线命名的字段
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxPayCreateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信应用appId
     */
    private String appid;

    /**
     * 商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称
     * 1.全车通商户版-过户费 2.全车通商户版-质保服务费
     */
    private String body;

    /**
     * 微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 通知url
     */
    private String notify_url;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    private String sign;

    private String spbill_create_ip;

    /**
     * 总金额
     */
    private String total_fee;

    private String trade_type = "APP";

    public WxPayCreateBean() {
    }

    public WxPayCreateBean(String outTradeNo, String spbillCreateIp, int totalFee, int feeType) {
        this.nonce_str = RandomStringUtils.random(32, true, true);
        this.out_trade_no = outTradeNo;
        this.spbill_create_ip = spbillCreateIp;
        this.total_fee = String.valueOf(totalFee);
        if (feeType == 1) {
            this.body = "全车通商户版-过户费";
        } else {
            this.body = "全车通商户版-质保服务费";
        }
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
