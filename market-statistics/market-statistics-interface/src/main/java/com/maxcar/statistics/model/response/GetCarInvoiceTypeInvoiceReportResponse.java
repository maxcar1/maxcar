package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarInvoiceTypeInvoiceReportResponse implements Serializable{

    private static final long serialVersionUID = -4781696025890093933L;
    private String carInvoiceType;

    private String invoiceCount;

    private String invoicePrice;

}
