package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInventoryReportResponse implements Serializable {

    private static final long serialVersionUID = -4627478156736168770L;
    private String brandName;

    private String inventoryCount;

    private String inventoryPrice;

}
