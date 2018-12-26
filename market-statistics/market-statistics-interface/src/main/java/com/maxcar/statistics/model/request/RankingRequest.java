package com.maxcar.statistics.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RankingRequest  implements Serializable {
    private static final long serialVersionUID = 5747846709418378173L;
    private String marketId;
    private String tenantId;
}
