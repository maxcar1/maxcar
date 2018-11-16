package com.maxcar.tenant.app.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.tenant.app.bean.RoleResBean;
import com.maxcar.tenant.app.entity.TenantRole;

import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-15 11:37
 * @description: ${description}
 **/
public interface TenantRoleService extends BaseService<TenantRole,String> {

    List<TenantRole> selectRoleByTenantId(String groupId);

    InterfaceResult addRoleAndRes(TenantRole role);

    List<TenantRole> getRoleListByTenant(String tenantId);

    TenantRole getRoleAndStaffAndRes(String roleId);

    InterfaceResult roleResRelation(RoleResBean bean);

    InterfaceResult updateRole(TenantRole role);

    InterfaceResult delRole(String roleId);
}
