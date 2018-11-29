package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCaryearInventoryMonthResponse implements Serializable {

    private static final long serialVersionUID = 4562769813153115353L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;
}
