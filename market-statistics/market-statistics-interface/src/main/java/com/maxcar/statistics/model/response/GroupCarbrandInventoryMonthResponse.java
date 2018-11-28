package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandInventoryMonthResponse implements Serializable {

    private static final long serialVersionUID = 4736235451032453254L;
    private String numTime;

    private String inventoryCount;

    private String inventoryPrice;
}
