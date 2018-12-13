package com.maxcar.stock.entity.Response;

import java.io.Serializable;

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
    private double mileage;
    private double evaluatePrice;
    private double marketPrice;
    private double invoicePrice;
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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
