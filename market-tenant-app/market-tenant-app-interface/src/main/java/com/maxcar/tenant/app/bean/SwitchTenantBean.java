package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class SwitchTenantBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商户id不能为空")
    private String tenantId;

}
