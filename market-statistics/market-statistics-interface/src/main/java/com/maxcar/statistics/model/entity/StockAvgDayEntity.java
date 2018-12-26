package com.maxcar.statistics.model.entity;

import java.io.Serializable;
import java.util.Date;

public class StockAvgDayEntity implements Serializable {
    private static final long serialVersionUID = 4909607417098225784L;
    private Integer id;

    private String reportTime;

    private String marketId;

    private Integer stockDealAvgId;

    private Integer stockDealAvgNum;

    private String stockDealAvgName;

    private Integer isvalid;

    private Integer version;

    private Date registerTime;

    private Double stockCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getStockCount() {
        return stockCount;
    }

    public void setStockCount(Double stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public Integer getStockDealAvgId() {
        return stockDealAvgId;
    }

    public void setStockDealAvgId(Integer stockDealAvgId) {
        this.stockDealAvgId = stockDealAvgId;
    }

    public Integer getStockDealAvgNum() {
        return stockDealAvgNum;
    }

    public void setStockDealAvgNum(Integer stockDealAvgNum) {
        this.stockDealAvgNum = stockDealAvgNum;
    }

    public String getStockDealAvgName() {
        return stockDealAvgName;
    }

    public void setStockDealAvgName(String stockDealAvgName) {
        this.stockDealAvgName = stockDealAvgName == null ? null : stockDealAvgName.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}