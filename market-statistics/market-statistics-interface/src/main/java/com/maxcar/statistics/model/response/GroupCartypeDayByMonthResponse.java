package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCartypeDayByMonthResponse implements Serializable {

    private static final long serialVersionUID = -2600886703121101944L;
    private String marketId;

    private String tenantId;

    private String typeId;

    private Integer  invoiceCount;

    private Double  invoicePrice;
}
