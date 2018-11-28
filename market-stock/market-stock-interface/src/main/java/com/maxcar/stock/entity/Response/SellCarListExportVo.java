package com.maxcar.stock.entity.Response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author sunyazhou
 * @Date 2018/11/26 13:53
 * @desc 售出管理导出车辆信息列表
 */
public class SellCarListExportVo implements Serializable {

    private String brandAndSeriesName;
    private String modelName;
    private String tenantName;
    private String carStatus;
    private String stockStatus;
    private String isNewCar;
    private String registerTime;
    private Double mileage;
    private Double evaluatePrice;
    private Double marketPrice;

    public Double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(Double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    private Double invoicePrice;
    private String vin;

    public String getBrandAndSeriesName() {
        return brandAndSeriesName;
    }

    public void setBrandAndSeriesName(String brandAndSeriesName) {
        this.brandAndSeriesName = brandAndSeriesName;
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

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(String isNewCar) {
        this.isNewCar = isNewCar;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
