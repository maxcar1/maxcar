package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCarbrandInvoiceDayRankingRequest implements Serializable {

    private static final long serialVersionUID = -5552787085403642881L;
    private String marketId;

    private String brandName;

    private String startTime;

    private String endTime;

    private String orderBy;

}
