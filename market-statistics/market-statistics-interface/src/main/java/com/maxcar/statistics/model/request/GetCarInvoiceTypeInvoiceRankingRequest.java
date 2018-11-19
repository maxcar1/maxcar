package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetCarInvoiceTypeInvoiceRankingRequest implements Serializable {

    private String marketId;

    private String tenantId;

    @NotNull(message = "起始时间不能为null")
    private String startTime;

    @NotNull(message = "终止时间不能为null")
    private String endTime;


}
