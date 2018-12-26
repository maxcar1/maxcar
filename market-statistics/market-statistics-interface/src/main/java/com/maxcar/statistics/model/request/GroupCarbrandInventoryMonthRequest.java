package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GroupCarbrandInventoryMonthRequest implements Serializable {

    private static final long serialVersionUID = 1986915506299834074L;
    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "结束时间不能为null")
    private String endTime;

    private String marketId;

    private String tenantId;

    // 车辆类型
    //  @NotNull(message = "车辆类型不能为null")
    private String brandName;

    private String selectCondition;


}
