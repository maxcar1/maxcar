package com.maxcar.statistics.model.entity;

import java.util.Date;

public class InventoryInvoiceDayEntity {
    private Integer id;

    private String marketId;

    private String tenantId;

    private String reportTime;

    private Integer stockCount;

    private Double stockPrice;

    private Integer stockDayAvg;

    private Integer outLibraryCount;

    private Integer tenantSpace;

    private Integer salesCount;

    private Double salesPrice;

    private Double salesAvgPrice;

    private Integer version;

    private Integer isvalid;

    private Date registerTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Integer getStockDayAvg() {
        return stockDayAvg;
    }

    public void setStockDayAvg(Integer stockDayAvg) {
        this.stockDayAvg = stockDayAvg;
    }

    public Integer getOutLibraryCount() {
        return outLibraryCount;
    }

    public void setOutLibraryCount(Integer outLibraryCount) {
        this.outLibraryCount = outLibraryCount;
    }

    public Integer getTenantSpace() {
        return tenantSpace;
    }

    public void setTenantSpace(Integer tenantSpace) {
        this.tenantSpace = tenantSpace;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getSalesAvgPrice() {
        return salesAvgPrice;
    }

    public void setSalesAvgPrice(Double salesAvgPrice) {
        this.salesAvgPrice = salesAvgPrice;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}