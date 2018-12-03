package com.maxcar.common.gecco;


import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;
/**
 * 江苏省解析对象
 */
public class JiangsuResult implements JsonBean {
    private static final long serialVersionUID = -5696033709028657709L;

    public String getCORP_NAME() {
        return CORP_NAME;
    }

    public void setCORP_NAME(String CORP_NAME) {
        this.CORP_NAME = CORP_NAME;
    }

    @JSONPath("CORP_NAME")
    private String CORP_NAME;//公司名称

    @JSONPath("ADDR")
    private String ADDR;//注册地址

    @JSONPath("REG_NO")
    private String REG_NO;//组织结构代码

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getREG_NO() {
        return REG_NO;
    }

    public void setREG_NO(String REG_NO) {
        this.REG_NO = REG_NO;
    }
}
