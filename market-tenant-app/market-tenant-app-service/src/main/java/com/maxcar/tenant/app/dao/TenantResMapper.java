package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.TenantRes;
import com.maxcar.tenant.app.entity.TenantResExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenantResMapper {
    int countByExample(TenantResExample example);

    int deleteByExample(TenantResExample example);

    int deleteByPrimaryKey(String id);

    int insert(TenantRes record);

    int insertSelective(TenantRes record);

    List<TenantRes> selectByExample(TenantResExample example);

    TenantRes selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TenantRes record, @Param("example") TenantResExample example);

    int updateByExample(@Param("record") TenantRes record, @Param("example") TenantResExample example);

    int updateByPrimaryKeySelective(TenantRes record);

    int updateByPrimaryKey(TenantRes record);

    TenantRes getTenantResById(String id);

    List<TenantRes> findAll();

}