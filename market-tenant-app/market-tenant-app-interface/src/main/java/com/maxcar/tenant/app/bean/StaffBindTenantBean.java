package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class StaffBindTenantBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "员工姓名不能为空")
    private String staffName;

    @NotBlank(message = "市场id不能为空")
    private String marketId;

    @NotBlank(message = "商户id不能为空")
    private String tenantId;

    @NotBlank(message = "岗位id不能为空")
    private String roleId;


}
