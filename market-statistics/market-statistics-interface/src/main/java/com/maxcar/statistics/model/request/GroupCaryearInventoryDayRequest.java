package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GroupCaryearInventoryDayRequest implements Serializable {

    private static final long serialVersionUID = 4267061470950995145L;
    private String orderBy;

    @NotNull(message = "结束时间不能为null")
    private String endTime;

    private String marketId;

    private String tenantId;

    /*// 车辆年限
    private String yearId;*/
}
