package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetInventoryRankingByConditionRequest implements Serializable {

    private static final long serialVersionUID = -7650748245757452644L;
    private String marketId;

    @NotNull(message = "排序字段不能为null")
    private String orderBy;

    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "终止时间不能为null")
    private String endTime;

    //车辆品牌
    private String brandName;


}
