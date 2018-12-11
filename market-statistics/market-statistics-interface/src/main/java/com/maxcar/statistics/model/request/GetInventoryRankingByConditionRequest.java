package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetInventoryRankingByConditionRequest implements Serializable {

    private static final long serialVersionUID = -7650748245757452644L;
    private String marketId;

    private String orderBy;

    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "终止时间不能为null")
    private String endTime;


    private String type;

    // 车辆类型
    private String typeId;

    //车辆品牌
    private String brandName;

    // 库存周期
    private String stocktimeId;

    // 车辆年限
    private String yearId;



}
