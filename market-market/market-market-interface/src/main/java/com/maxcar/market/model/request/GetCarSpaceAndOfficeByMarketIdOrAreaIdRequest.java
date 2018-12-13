package com.maxcar.market.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest implements Serializable {

    private static final long serialVersionUID = -8188535155933155449L;
    private String marketId;

    private String tenantId;

    private String areaId;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
