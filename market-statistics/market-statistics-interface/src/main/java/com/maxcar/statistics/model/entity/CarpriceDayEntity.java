package com.maxcar.statistics.model.entity;

import java.io.Serializable;
import java.util.Date;

public class CarpriceDayEntity implements Serializable {
    private static final long serialVersionUID = 9133127207008317840L;
    private Integer id;

    private String marketId;

    private String tenantId;

    private String reportTime;

    private String invoicePriceId;

    private String invoicePriceName;

    private Integer salesCount;

    private Integer stockAvgStocktime;

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

    public String getInvoicePriceId() {
        return invoicePriceId;
    }

    public void setInvoicePriceId(String invoicePriceId) {
        this.invoicePriceId = invoicePriceId == null ? null : invoicePriceId.trim();
    }

    public String getInvoicePriceName() {
        return invoicePriceName;
    }

    public void setInvoicePriceName(String invoicePriceName) {
        this.invoicePriceName = invoicePriceName == null ? null : invoicePriceName.trim();
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getStockAvgStocktime() {
        return stockAvgStocktime;
    }

    public void setStockAvgStocktime(Integer stockAvgStocktime) {
        this.stockAvgStocktime = stockAvgStocktime;
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