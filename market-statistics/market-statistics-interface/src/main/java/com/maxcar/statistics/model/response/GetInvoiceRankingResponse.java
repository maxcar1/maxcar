package com.maxcar.statistics.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvoiceRankingResponse implements Serializable{

    private static final long serialVersionUID = -5007838399096687638L;
    private String marketId;

    private String marketName;

    private String tenantId;

    private String tenantName;

    private String invoiceCount;

    private String invoicePrice;


}
