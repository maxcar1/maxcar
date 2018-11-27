package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;

public class CarWarningExcel implements Serializable {
    private String brandName;//品牌

//    private String seriesName;//车型

    private String modelName;

    private String tenantName;//商户

    private int carStatus;//车辆状态 1质押 2 非质押',

    private String outReason;//出场原因

    private Double evaluateRrice;//估价

    private Date insertTime;//出场时间

    private Date backTime;//申请返厂时间

    private String vin;//vin

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public Double getEvaluateRrice() {
        return evaluateRrice;
    }

    public void setEvaluateRrice(Double evaluateRrice) {
        this.evaluateRrice = evaluateRrice;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
