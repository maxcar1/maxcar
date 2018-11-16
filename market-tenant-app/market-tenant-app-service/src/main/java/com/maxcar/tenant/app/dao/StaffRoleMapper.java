package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.StaffRole;
import com.maxcar.tenant.app.entity.StaffRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffRoleMapper {
    int countByExample(StaffRoleExample example);

    int deleteByExample(StaffRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(StaffRole record);

    int insertSelective(StaffRole record);

    List<StaffRole> selectByExample(StaffRoleExample example);

    StaffRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StaffRole record, @Param("example") StaffRoleExample example);

    int updateByExample(@Param("record") StaffRole record, @Param("example") StaffRoleExample example);

    int updateByPrimaryKeySelective(StaffRole record);

    int updateByPrimaryKey(StaffRole record);

    String getRoleIdByStaffId(String staffId);

}