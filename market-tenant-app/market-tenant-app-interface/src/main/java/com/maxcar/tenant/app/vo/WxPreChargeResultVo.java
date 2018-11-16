package com.maxcar.tenant.app.vo;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 微信预支付结果返回，因为有关键字冲突，签名以及返回给客户端时先转成map
 */
public class WxPreChargeResultVo {

    private String appid;

    /**
     * MCH_ID
     */
    private String partnerid;

    private String prepayid;

    private String packageValue = "Sign=WXPay";

    private String noncestr;

    private String timestamp;

    private String sign;

    public WxPreChargeResultVo() {
    }

    public WxPreChargeResultVo(String prepayid) {
        this.prepayid = prepayid;
        this.noncestr = RandomStringUtils.random(32, true, true);
        this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
