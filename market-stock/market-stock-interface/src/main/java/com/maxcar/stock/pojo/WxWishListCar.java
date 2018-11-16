package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class WxWishListCar implements Serializable{

    private String id;
    private String modelName;
    private String initial;
    private String mileage;
    private String marketPrice;
    private String newPrice;
    private String marketId;
    private String tenantName;
    private String src;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getInitial() {
        return initial;
    }
    public void setInitial(String initial) {
        this.initial = initial;
    }
    public String getMileage() {
        return mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }
    public String getNewPrice() {
        return newPrice;
    }
    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }
    public String getMarketId() {
        return marketId;
    }
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    public String getTenantName() {
        return tenantName;
    }
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
}
