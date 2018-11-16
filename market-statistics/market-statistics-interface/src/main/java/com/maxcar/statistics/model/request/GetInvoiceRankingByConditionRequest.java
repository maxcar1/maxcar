package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetInvoiceRankingByConditionRequest implements Serializable {

    private static final long serialVersionUID = -6886694238780315239L;
    private String marketId;

    @NotNull(message = "排序字段不能为null")
    private String orderBy;

    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "终止时间不能为null")
    private String endTime;

    private String carInvoiceType;

}
