package com.maxcar.base.service;


import com.maxcar.base.pojo.taobao.TaobaoItemValues;
import com.maxcar.base.pojo.taobao.TaobaoItemValuesKey;

public interface TaobaoItemValuesService {
    int deleteByPrimaryKey(TaobaoItemValuesKey key);

    int insert(TaobaoItemValues record);

    int insertSelective(TaobaoItemValues record);

    TaobaoItemValues selectByPrimaryKey(TaobaoItemValuesKey key);

    int updateByPrimaryKeySelective(TaobaoItemValues record);

    int updateByPrimaryKey(TaobaoItemValues record);
    
    TaobaoItemValues getTaobaoItemValues(TaobaoItemValues record);
}