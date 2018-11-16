package com.maxcar.tenant.app.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.app.entity.TenantRole;
import com.maxcar.tenant.app.entity.TenantRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenantRoleMapper extends BaseDao<TenantRole, String> {
    int countByExample(TenantRoleExample example);

    int deleteByExample(TenantRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TenantRole record);

    int insertSelective(TenantRole record);

    List<TenantRole> selectByExample(TenantRoleExample example);

    TenantRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TenantRole record, @Param("example") TenantRoleExample example);

    int updateByExample(@Param("record") TenantRole record, @Param("example") TenantRoleExample example);

    int updateByPrimaryKeySelective(TenantRole record);

    int updateByPrimaryKey(TenantRole record);

    List<TenantRole> getRoleListByTenant(String tenantId);

    TenantRole getRoleAndStaffAndRes(String roleId);
}