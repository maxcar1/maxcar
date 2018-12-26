package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCaryearInvoiceMonthResponse  implements Serializable{

    private static final long serialVersionUID = -7136492224527894540L;
    private String numTime;

    private Integer invoiceCount;

    private Double invoicePrice;
}
