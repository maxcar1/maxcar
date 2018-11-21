package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvoiceByCarInvoiceTypeReportParameter implements Serializable {

    private static final long serialVersionUID = -9072366919513187910L;

    private String marketId;

    private String tenantId;

    private String startTime;

    private String endTime;

    private String carInvoiceType;

    private String selectCondition;

}
