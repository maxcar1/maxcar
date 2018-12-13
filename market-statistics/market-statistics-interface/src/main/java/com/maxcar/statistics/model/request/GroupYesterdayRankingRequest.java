package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupYesterdayRankingRequest implements Serializable {

    private static final long serialVersionUID = -3441899370116534382L;
    private String marketId;

    private String orderBy;

    private String tenantId;

}
