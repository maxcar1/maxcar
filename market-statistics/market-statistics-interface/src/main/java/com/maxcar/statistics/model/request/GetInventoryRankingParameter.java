package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInventoryRankingParameter implements Serializable {
    private static final long serialVersionUID = -1604185110102951083L;
    private String marketId;

    private String orderBy;

    private String selectColumns;

    private String selectCondition;

    private String groupByColumns;

    private String startTime;

    private String endTime;

    private String brandName;

}
