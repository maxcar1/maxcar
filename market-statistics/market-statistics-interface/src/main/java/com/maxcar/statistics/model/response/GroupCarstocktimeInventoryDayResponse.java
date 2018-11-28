package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarstocktimeInventoryDayResponse implements Serializable {

    private static final long serialVersionUID = 6975898029012052984L;
    private String stocktimeId;

    private String inventoryCount;

    private String inventoryPrice;

}
