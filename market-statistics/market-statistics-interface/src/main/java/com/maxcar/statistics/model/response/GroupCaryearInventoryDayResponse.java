package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCaryearInventoryDayResponse  implements Serializable{

    private static final long serialVersionUID = -3813994946534408025L;
    private Integer yearId;

    private String inventoryCount;

    private String inventoryPrice;


}
