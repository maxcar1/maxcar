package com.maxcar.stock.entity.Response;

import java.io.Serializable;

public class ExportReviewResponse implements Serializable {

    private String brandName;//品牌名称

    private String modelName;//车型名称

    private String tenantName;//商户名称

    private String insertTime;//申请时间

    private String carStatus;//车辆状态 1质押 2 非质押

    private String outReason;//出厂原因

    private Double evaluatePrice;//估价

    private String reviewResult;//审核结果 0未审核   1审核通过   2审核不通过

    private String stockStatus;//库存状态 -1 删除 1:在场 2:在内场 3:出场 4:售出未出场 5 售出已出场

    private String vin;//vin码

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
