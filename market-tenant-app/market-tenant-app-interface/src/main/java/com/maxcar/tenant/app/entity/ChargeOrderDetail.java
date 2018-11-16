package com.maxcar.tenant.app.entity;

import com.maxcar.tenant.app.enums.ChargeStateEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值详情表
 */
public class ChargeOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String id;

    private String staffId;

    /**
     * 充值类型 {@link com.maxcar.tenant.app.enums.ChargeTypeEnum}
     */
    private int chargeType;

    /**
     * 充值金额 分
     */
    private int chargeMoney;

    /**
     * 充值状态 {@link ChargeStateEnum}
     */
    private int state;

    /**
     * 微信 & 支付宝支付订单号
     */
    private String supplierBizId;

    private String prepayId = "";

    /**
     * 费用类型：1.交易过户费 2.质保服务费
     */
    private int feeType = 1;

    /**
     * 车辆交易过户业务编号
     */
    private String transferCarNo;

    private Date gmtCreate;

    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getChargeType() {
        return chargeType;
    }

    public void setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }

    public int getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(int chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSupplierBizId() {
        return supplierBizId;
    }

    public void setSupplierBizId(String supplierBizId) {
        this.supplierBizId = supplierBizId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public int getFeeType() {
        return feeType;
    }

    public void setFeeType(int feeType) {
        this.feeType = feeType;
    }

    public String getTransferCarNo() {
        return transferCarNo;
    }

    public void setTransferCarNo(String transferCarNo) {
        this.transferCarNo = transferCarNo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
