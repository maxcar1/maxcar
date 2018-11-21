package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvoiceByCarInvoiceTypeReportResponse implements Serializable{

    private static final long serialVersionUID = -2582522750031425864L;
    private String numTime;

    private String invoiceCount;

    private String invoicePrice;

}
