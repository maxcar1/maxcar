package com.maxcar.stock.vo;

import java.io.Serializable;

/**
 * @Author sunyazhou
 * @Date 2018/11/24 13:51
 * @desc
 */
public class CarSellVo implements Serializable {

    private String carId;
    private String marketId;
    private String tenantId;
    private String vin;
    private Double price;
    private Integer downTaoBao; // 1下架  2不下架
    private Integer stockStatus;
    private Integer carType;
    private String modelName;
    private String taobaoId;

    public String getTaobaoId() {
        return taobaoId;
    }

    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDownTaoBao() {
        return downTaoBao;
    }

    public void setDownTaoBao(Integer downTaoBao) {
        this.downTaoBao = downTaoBao;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
