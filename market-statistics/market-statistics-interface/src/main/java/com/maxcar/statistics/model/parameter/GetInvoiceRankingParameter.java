package com.maxcar.statistics.model.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvoiceRankingParameter implements Serializable{

    private static final long serialVersionUID = 8110312147381121675L;
    private String marketId;
    // 查询的列
    private String selectColumns;
    // 查询的条件
    private String selectCondition;
    // 分组的条件
    private String groupByColumns;
    // 排序的条件
    private String orderBy;

    private String startTime;

    private String endTime;

    // 车辆类型
    private String carInvoiceType;

    //车辆品牌
    private String brandName;

    // 库存周期
    private Integer inventoryCycle;

    // 车辆年限
    private Integer AgeByCar;

}
