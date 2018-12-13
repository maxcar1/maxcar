package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandInventoryDayResponse implements Serializable {

    private static final long serialVersionUID = -2705152203114914658L;
    private String brandName;

    private Integer inventoryCount;

    private Double inventoryPrice;


}
