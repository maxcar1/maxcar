package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class getCarInvoiceTypeInvoiceRankingParameter  implements Serializable{

    private static final long serialVersionUID = 284967010579700456L;
    private String marketId;

    private String tenantId;

    // 查询的条件
    private String selectCondition;

    // 排序的条件
    private String orderBy;

    // 车辆类型
    private String carInvoiceType;

    private String startTime;

    private String endTime;

}
