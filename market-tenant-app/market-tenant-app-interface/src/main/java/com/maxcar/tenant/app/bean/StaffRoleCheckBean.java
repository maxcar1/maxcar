package com.maxcar.tenant.app.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author songxuefeng
 * @create 2018-10-17 9:41
 * @description: ${description}
 **/
@Data
public class StaffRoleCheckBean implements Serializable {
    private static final long serialVersionUID = -3978369276855087296L;
    @NotNull(message = "员工审批id不能为空")
    private String staffCheckId;
    @NotNull(message = "审批状态不能为空")
    private Integer isCheck;//-1 拒绝 1 同意
    private String refuseReason;//拒绝理由
}
