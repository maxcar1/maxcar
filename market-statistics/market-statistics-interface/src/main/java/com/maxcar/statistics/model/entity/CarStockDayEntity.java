package com.maxcar.statistics.model.entity;

import java.util.Date;

public class CarStockDayEntity {
    private Integer id;

    private String marketId;

    private String tenantId;

    private String reportTime;

    private String invoiceStockId;

    private String invoiceStockName;

    private Integer stockCount;

    private Integer isvalid;

    private Integer version;

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

    public String getInvoiceStockId() {
        return invoiceStockId;
    }

    public void setInvoiceStockId(String invoiceStockId) {
        this.invoiceStockId = invoiceStockId == null ? null : invoiceStockId.trim();
    }

    public String getInvoiceStockName() {
        return invoiceStockName;
    }

    public void setInvoiceStockName(String invoiceStockName) {
        this.invoiceStockName = invoiceStockName == null ? null : invoiceStockName.trim();
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
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