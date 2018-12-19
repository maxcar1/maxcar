package com.maxcar.base.dao;

import com.maxcar.base.pojo.taobao.TaobaoItemValues;
import com.maxcar.base.pojo.taobao.TaobaoItemValuesExample;
import com.maxcar.base.pojo.taobao.TaobaoItemValuesKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaoItemValuesMapper {
    int countByExample(TaobaoItemValuesExample example);

    int deleteByExample(TaobaoItemValuesExample example);

    int deleteByPrimaryKey(TaobaoItemValuesKey key);

    int insert(TaobaoItemValues record);

    int insertSelective(TaobaoItemValues record);

    List<TaobaoItemValues> selectByExample(TaobaoItemValuesExample example);

    TaobaoItemValues selectByPrimaryKey(TaobaoItemValuesKey key);

    int updateByExampleSelective(@Param("record") TaobaoItemValues record, @Param("example") TaobaoItemValuesExample example);

    int updateByExample(@Param("record") TaobaoItemValues record, @Param("example") TaobaoItemValuesExample example);

    int updateByPrimaryKeySelective(TaobaoItemValues record);

    int updateByPrimaryKey(TaobaoItemValues record);
}