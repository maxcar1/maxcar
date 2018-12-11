package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetInvoiceRankingByConditionRequest implements Serializable {

    private static final long serialVersionUID = -6886694238780315239L;
    private String marketId;

    private String orderBy;

    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "终止时间不能为null")
    private String endTime;


    private String type;

    private String parameter;

    // 车辆类型
    private String typeId;

    //车辆品牌
    private String brandName;

    // 库存周期
    private String stocktimeId;

    // 车辆年限
    private String yearId;

}
