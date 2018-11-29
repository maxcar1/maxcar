package com.maxcar.stock.entity.Request;

import java.io.Serializable;

public class InventoryStatisticalRequest implements Serializable {

    private static final long serialVersionUID = 1754501620737133392L;
    private String marketId;

    private String tenantId;

    private String registerTimeStart;

    private String registerTimeEnd;

    private Integer stockStatus;

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
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
}
