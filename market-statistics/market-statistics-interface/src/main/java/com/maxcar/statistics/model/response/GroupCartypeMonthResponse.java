package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCartypeMonthResponse implements Serializable {

    private static final long serialVersionUID = 8232659855718994295L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;

}
