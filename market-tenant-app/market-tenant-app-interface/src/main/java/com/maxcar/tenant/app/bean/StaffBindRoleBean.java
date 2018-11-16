package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author songxuefeng
 * @create 2018-10-16 16:07
 * @description: ${description}
 **/
@Data
public class StaffBindRoleBean implements Serializable {
    private static final long serialVersionUID = 8531587965411153019L;
    @NotBlank(message = "员工id不能为空")
    private String staffId;
    @NotBlank(message = "角色id不能为空")
    private String roleId;
    @NotNull(message = "是否关联 不能为空")
    private Integer relation;//是否关联 0 解除 1 关联
}
