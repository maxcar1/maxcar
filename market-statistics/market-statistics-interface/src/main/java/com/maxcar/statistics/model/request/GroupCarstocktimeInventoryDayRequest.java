package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GroupCarstocktimeInventoryDayRequest implements Serializable {

    private String orderBy;

    @NotNull(message = "结束时间不能为null")
    private String endTime;

    private String marketId;

    private String tenantId;



}
