package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInventoryReportParameter implements Serializable{

    private static final long serialVersionUID = -997928048057216043L;
    private String marketId;

    private String tenantId;

    private String selectColumns;
    // 查询条件
    private String selectCondition;

    private String groupByColumns;

    private String orderBy;

    private String startTime;

    private String endTime;


}
