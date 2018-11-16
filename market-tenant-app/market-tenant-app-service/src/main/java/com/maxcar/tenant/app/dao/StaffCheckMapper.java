package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.StaffCheck;
import com.maxcar.tenant.app.entity.StaffCheckExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffCheckMapper {
    int countByExample(StaffCheckExample example);

    int deleteByExample(StaffCheckExample example);

    int deleteByPrimaryKey(String id);

    int insert(StaffCheck record);

    int insertSelective(StaffCheck record);

    List<StaffCheck> selectByExample(StaffCheckExample example);

    StaffCheck selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StaffCheck record, @Param("example") StaffCheckExample example);

    int updateByExample(@Param("record") StaffCheck record, @Param("example") StaffCheckExample example);

    int updateByPrimaryKeySelective(StaffCheck record);

    int updateByPrimaryKey(StaffCheck record);

    StaffCheck getStaffCheckByStaffId(String staffId);
}