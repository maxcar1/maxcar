package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.TenantRoleRes;
import com.maxcar.tenant.app.entity.TenantRoleResExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenantRoleResMapper {
    int countByExample(TenantRoleResExample example);

    int deleteByExample(TenantRoleResExample example);

    int deleteByPrimaryKey(String id);

    int insert(TenantRoleRes record);

    int insertSelective(TenantRoleRes record);

    List<TenantRoleRes> selectByExample(TenantRoleResExample example);

    TenantRoleRes selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TenantRoleRes record, @Param("example") TenantRoleResExample example);

    int updateByExample(@Param("record") TenantRoleRes record, @Param("example") TenantRoleResExample example);

    int updateByPrimaryKeySelective(TenantRoleRes record);

    int updateByPrimaryKey(TenantRoleRes record);

    List<String> getResIdListByRoleId(String roleId);
}