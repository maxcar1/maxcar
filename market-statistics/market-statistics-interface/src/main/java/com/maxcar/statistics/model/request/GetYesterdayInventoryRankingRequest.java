package com.maxcar.statistics.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetYesterdayInventoryRankingRequest implements Serializable{

    private static final long serialVersionUID = -695089516924403627L;
    private String marketId;

    @NotNull(message = "排序字段不能为null")
    private String orderBy;

}
