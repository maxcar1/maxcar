package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GroupCarstocktimeInvoiceMonthRequest implements Serializable{


    private static final long serialVersionUID = 8886985881583991309L;
    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "结束时间不能为null")
    private String endTime;

    private String marketId;

    private String tenantId;

    // 车辆类型
    private String stocktimeId;

    private String selectCondition;
}
