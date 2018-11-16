package com.maxcar.tenant.app.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-17 11:39
 * @description: ${description}
 **/
@Data
public class RoleResBean implements Serializable {
    private static final long serialVersionUID = 3514916947662727442L;
    @NotNull(message = "角色id不能为空")
    private String roleId;
    @NotNull(message = "权限id不能为空")
    private List<String> resIds;
//    @NotNull(message = "关联状态不能为空")
//    private Integer relation;//0 解除关联 1 关联
}
