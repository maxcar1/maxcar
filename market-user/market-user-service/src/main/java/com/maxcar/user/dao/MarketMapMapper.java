package com.maxcar.user.dao;

import com.maxcar.user.entity.MarketMap;
import com.maxcar.user.entity.MarketMapExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketMapMapper {
    int countByExample(MarketMapExample example);

    int deleteByExample(MarketMapExample example);

    int deleteByPrimaryKey(String id);

    int insert(MarketMap record);

    int insertSelective(MarketMap record);

    List<MarketMap> selectByExample(MarketMapExample example);

    MarketMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MarketMap record, @Param("example") MarketMapExample example);

    int updateByExample(@Param("record") MarketMap record, @Param("example") MarketMapExample example);

    int updateByPrimaryKeySelective(MarketMap record);

    int updateByPrimaryKey(MarketMap record);
}