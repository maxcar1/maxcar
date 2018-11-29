package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarstocktimeInventoryMonthResponse implements Serializable {

    private static final long serialVersionUID = 8264849408278645945L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;
}
