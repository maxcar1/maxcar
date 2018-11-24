package com.maxcar.stock.vo;

import java.io.Serializable;

/**
 * @Author sunyazhou
 * @Date 2018/11/24 13:51
 * @desc
 */
public class CarSellVo implements Serializable {

    private String car_id;
    private String market_id;
    private String tenant_id;
    private String vin;
    private Double price;
    private Integer downTaoBao; // 1下架  2不下架
    private Integer stock_status;
    private Integer car_type;
    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
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

    public Integer getStock_status() {
        return stock_status;
    }

    public void setStock_status(Integer stock_status) {
        this.stock_status = stock_status;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }
}
