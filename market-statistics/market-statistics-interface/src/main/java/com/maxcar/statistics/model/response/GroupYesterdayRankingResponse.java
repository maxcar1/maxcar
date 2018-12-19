package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupYesterdayRankingResponse implements Serializable {

    private static final long serialVersionUID = 1768008949745839547L;
    private String marketId;

    private String marketName;

    private String tenantId;

    private String tenantName;

    private String invoiceCount;

    private Double invoicePrice;

    private String inventoryCount;

    private Double inventoryPrice;


}
