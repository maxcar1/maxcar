package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarstocktimeInvoiceMonthResponse implements Serializable {

    private static final long serialVersionUID = 3401257614137002885L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;
}
