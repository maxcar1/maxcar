package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarInvoiceTypeInvoiceReportResponse implements Serializable {

    private static final long serialVersionUID = -4781696025890093933L;

    private String brandName;

    private String carInvoiceType;

    private String marketId;

    private String tenantId;

    private Integer invoiceCount;

    private Double invoicePrice;

}
