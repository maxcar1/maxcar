package com.maxcar.vo;

import lombok.Data;

import java.util.List;

@Data
public class TenantVo {

    private String tenantId;

    private String tenantName;

    private List<RoleVo> roleVoList;

}