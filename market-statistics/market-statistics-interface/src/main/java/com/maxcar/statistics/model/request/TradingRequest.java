package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class TradingRequest implements Serializable {

    private static final long serialVersionUID = -746273977542930866L;

    private String marketId;
    private String tenantId;
    private String timeStart;
    private String timeEnd;
    private String selectColumns;
    private String selectCondition;
    private String groupByColumns;
}
