package com.maxcar.tenant.app.dao;

import com.maxcar.tenant.app.entity.AddDealInfo;
import org.apache.ibatis.annotations.Param;

public interface AddDealInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(AddDealInfo record);

    AddDealInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AddDealInfo record);

    int updateByPrimaryKey(AddDealInfo record);

    int updateDealPrice(@Param("dealPrice") Double dealPrice, @Param("id") String id);
}