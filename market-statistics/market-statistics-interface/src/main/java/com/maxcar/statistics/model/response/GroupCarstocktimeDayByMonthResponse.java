package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarstocktimeDayByMonthResponse implements Serializable {

    private static final long serialVersionUID = -6717629867757852882L;
    private String marketId;

    private String tenantId;

    private Integer stocktimeId;

    private Integer invoiceCount;

    private Double invoicePrice;

    private String inventoryCount;

    private String inventoryPrice;
}
