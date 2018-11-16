package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetYesterdayInvoiceRankingRequest implements Serializable {

    private static final long serialVersionUID = 4011827502507788509L;

    private String marketId;

    @NotNull(message = "排序字段不能为null")
    private String orderBy;

}
