package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GroupCartypeDayRequest implements Serializable {

    private String orderBy;
    @NotNull(message = "起始时间不能为null")
    private String startTime;
    @NotNull(message = "结束时间不能为null")
    private String endTime;

    private String marketId;

    private String tenantId;

    // 车辆类型
    private String typeId;


}
