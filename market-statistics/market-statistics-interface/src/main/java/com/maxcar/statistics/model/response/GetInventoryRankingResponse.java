package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInventoryRankingResponse implements Serializable {

    private static final long serialVersionUID = -154713295007515748L;
    private String marketId;

    private String marketName;

    private String tenantId;

    private String tenantName;

    private String inventoryCount;

    private String inventoryPrice;
}
