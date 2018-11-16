package com.maxcar.tenant.app.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class BuySellInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;

    private Integer sellerType;

    private String sellerName;

    private String sellerIdcard;

    private String sellerIdcardAddress;

    private String sellerPhone;

    private String sellerIdcardFront;

    private String sellerIdcardReverse;

    private String sellerAgency;

    private String sellerCreditCode;

    private String sellerAddress;

    private String sellerBusinessLicense;

    private Byte buyerType;

    private String buyerName;

    private String buyerIdcard;

    private String buyerIdcardAddress;

    private String buyerPhone;

    private String buyerIdcardFront;

    private String buyerIdcardReverse;

    private String buyerAgency;

    private String buyerCreditCode;

    private String buyerBusinessLicense;

    private Date insertTime;

    private Date updateTime;

    private String remarks;

    private Byte isvalid;

    private String buyerAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getSellerIdcard() {
        return sellerIdcard;
    }

    public void setSellerIdcard(String sellerIdcard) {
        this.sellerIdcard = sellerIdcard == null ? null : sellerIdcard.trim();
    }

    public String getSellerIdcardAddress() {
        return sellerIdcardAddress;
    }

    public void setSellerIdcardAddress(String sellerIdcardAddress) {
        this.sellerIdcardAddress = sellerIdcardAddress == null ? null : sellerIdcardAddress.trim();
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone == null ? null : sellerPhone.trim();
    }

    public String getSellerIdcardFront() {
        return sellerIdcardFront;
    }

    public void setSellerIdcardFront(String sellerIdcardFront) {
        this.sellerIdcardFront = sellerIdcardFront == null ? null : sellerIdcardFront.trim();
    }

    public String getSellerIdcardReverse() {
        return sellerIdcardReverse;
    }

    public void setSellerIdcardReverse(String sellerIdcardReverse) {
        this.sellerIdcardReverse = sellerIdcardReverse == null ? null : sellerIdcardReverse.trim();
    }

    public String getSellerAgency() {
        return sellerAgency;
    }

    public void setSellerAgency(String sellerAgency) {
        this.sellerAgency = sellerAgency == null ? null : sellerAgency.trim();
    }

    public String getSellerCreditCode() {
        return sellerCreditCode;
    }

    public void setSellerCreditCode(String sellerCreditCode) {
        this.sellerCreditCode = sellerCreditCode == null ? null : sellerCreditCode.trim();
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress == null ? null : sellerAddress.trim();
    }

    public String getSellerBusinessLicense() {
        return sellerBusinessLicense;
    }

    public void setSellerBusinessLicense(String sellerBusinessLicense) {
        this.sellerBusinessLicense = sellerBusinessLicense == null ? null : sellerBusinessLicense.trim();
    }

    public Byte getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(Byte buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerIdcard() {
        return buyerIdcard;
    }

    public void setBuyerIdcard(String buyerIdcard) {
        this.buyerIdcard = buyerIdcard == null ? null : buyerIdcard.trim();
    }

    public String getBuyerIdcardAddress() {
        return buyerIdcardAddress;
    }

    public void setBuyerIdcardAddress(String buyerIdcardAddress) {
        this.buyerIdcardAddress = buyerIdcardAddress == null ? null : buyerIdcardAddress.trim();
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
    }

    public String getBuyerIdcardFront() {
        return buyerIdcardFront;
    }

    public void setBuyerIdcardFront(String buyerIdcardFront) {
        this.buyerIdcardFront = buyerIdcardFront == null ? null : buyerIdcardFront.trim();
    }

    public String getBuyerIdcardReverse() {
        return buyerIdcardReverse;
    }

    public void setBuyerIdcardReverse(String buyerIdcardReverse) {
        this.buyerIdcardReverse = buyerIdcardReverse == null ? null : buyerIdcardReverse.trim();
    }

    public String getBuyerAgency() {
        return buyerAgency;
    }

    public void setBuyerAgency(String buyerAgency) {
        this.buyerAgency = buyerAgency == null ? null : buyerAgency.trim();
    }

    public String getBuyerCreditCode() {
        return buyerCreditCode;
    }

    public void setBuyerCreditCode(String buyerCreditCode) {
        this.buyerCreditCode = buyerCreditCode == null ? null : buyerCreditCode.trim();
    }

    public String getBuyerBusinessLicense() {
        return buyerBusinessLicense;
    }

    public void setBuyerBusinessLicense(String buyerBusinessLicense) {
        this.buyerBusinessLicense = buyerBusinessLicense == null ? null : buyerBusinessLicense.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getFieldValue(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            Object obj = field.get(this);

            if ("sellerBusinessLicense".equals(fieldName)) {
                if (sellerType == 2) {
                    return "<div class='downLeft' id='yYZZ1'>" +
                            "<span class='picDown'>卖方营业执照照片</span>" +
                            "<img class='imgDownLeft' alt='' src='" + sellerBusinessLicense + "'/></div>";
                }
                return "";
            }

            if ("buyerBusinessLicense".equals(fieldName)) {
                if (sellerType == 2) {
                    return "<div class='downLeft' id='yYZZ2'>" +
                            "<span class='picDown'>买方营业执照照片</span>" +
                            "<img class='imgDownLeft' alt='' src='" + buyerBusinessLicense + "'/></div>";
                }
                return "";
            }

            if (obj == null) {
                return "";
            }
            return field.get(this).toString();
        } catch (Exception e) {
            return "";
        }
    }
}