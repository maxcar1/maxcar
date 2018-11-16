package com.maxcar.tenant.app.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StaffLoginVo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 是否商户管理员(0:员工,1:管理员)
     */
    private int isAdmin = 0;

    /**
     * 是否需要切换商户 1.需要切换 0.无需切换
     */
    private int switchTenant = 0;

    /**
     * 是否已经审核 -1.已拒绝 0.待审核 1.审核通过 2.未提交 3.账户被禁用
     */
    private int checked = 1;

    private String staffToken;
}
