package com.maxcar.market.model.response;

import java.io.Serializable;

/**
 * Created by chiyanlong  on 2018/11/7.
 * 身份证信息模糊匹配返回
 */
public class InvoicePerson implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sellerTaxNo;

    private String sellerIdCard;

    private String sellerName;

    private String sellerAddress;

    private String sellerMobile;

    private String purchacerTaxNo;

    private String purchacerName;

    private String purchacerIdCard;

    private String purchacerAddress;

    private String purchacerMobile;

    public String getSellerTaxNo() {
        return sellerTaxNo;
    }

    public void setSellerTaxNo(String sellerTaxNo) {
        this.sellerTaxNo = sellerTaxNo;
    }

    public String getSellerIdCard() {
        return sellerIdCard;
    }

    public void setSellerIdCard(String sellerIdCard) {
        this.sellerIdCard = sellerIdCard;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getPurchacerTaxNo() {
        return purchacerTaxNo;
    }

    public void setPurchacerTaxNo(String purchacerTaxNo) {
        this.purchacerTaxNo = purchacerTaxNo;
    }

    public String getPurchacerName() {
        return purchacerName;
    }

    public void setPurchacerName(String purchacerName) {
        this.purchacerName = purchacerName;
    }

    public String getPurchacerIdCard() {
        return purchacerIdCard;
    }

    public void setPurchacerIdCard(String purchacerIdCard) {
        this.purchacerIdCard = purchacerIdCard;
    }

    public String getPurchacerAddress() {
        return purchacerAddress;
    }

    public void setPurchacerAddress(String purchacerAddress) {
        this.purchacerAddress = purchacerAddress;
    }

    public String getPurchacerMobile() {
        return purchacerMobile;
    }

    public void setPurchacerMobile(String purchacerMobile) {
        this.purchacerMobile = purchacerMobile;
    }
}
