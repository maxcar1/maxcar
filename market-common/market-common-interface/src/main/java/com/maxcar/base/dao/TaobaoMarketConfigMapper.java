package com.maxcar.base.dao;

import com.maxcar.base.pojo.taobao.TaobaoMarketConfig;
import com.maxcar.base.pojo.taobao.TaobaoMarketConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaobaoMarketConfigMapper {
    int countByExample(TaobaoMarketConfigExample example);

    int deleteByExample(TaobaoMarketConfigExample example);

    int deleteByPrimaryKey(String marketId);

    int insert(TaobaoMarketConfig record);

    int insertSelective(TaobaoMarketConfig record);

    List<TaobaoMarketConfig> selectByExample(TaobaoMarketConfigExample example);

    TaobaoMarketConfig selectByPrimaryKey(String marketId);

    int updateByExampleSelective(@Param("record") TaobaoMarketConfig record, @Param("example") TaobaoMarketConfigExample example);

    int updateByExample(@Param("record") TaobaoMarketConfig record, @Param("example") TaobaoMarketConfigExample example);

    int updateByPrimaryKeySelective(TaobaoMarketConfig record);

    int updateByPrimaryKey(TaobaoMarketConfig record);
}