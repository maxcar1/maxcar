package com.maxcar.vo;

import lombok.Data;

import java.util.List;

@Data
public class MarketVo {

    private String marketId;

    private String marketName;

    private List<TenantVo> tenantVoList;

}