package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandInvoiceMonthResponse implements Serializable {
    private static final long serialVersionUID = -5053087130204466064L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;


}
