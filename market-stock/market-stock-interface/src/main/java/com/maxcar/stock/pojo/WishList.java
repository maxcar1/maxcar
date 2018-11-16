package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;

public class WishList implements Serializable {

    private String id;
    private String carId;
    private String ticket;
    private Date insertTime;
    private String marketId;
    private String userId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCarId() {
        return carId;
    }
    public void setCarId(String carId) {
        this.carId = carId;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public String getMarketId() {
        return marketId;
    }
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
