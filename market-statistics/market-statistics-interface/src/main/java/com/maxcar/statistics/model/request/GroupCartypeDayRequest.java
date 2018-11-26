package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCartypeDayRequest implements Serializable {

    private String orderBy;

    private String startTime;

    private String endTime;

    private String marketId;

    private String tenantId;

    // 车辆类型
    private String typeId;


}
