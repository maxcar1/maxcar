package com.maxcar.service;

import com.maxcar.entity.TaobaoItemValues;
import com.maxcar.entity.TaobaoItemValuesKey;

public interface TaobaoItemValuesService {
    int deleteByPrimaryKey(TaobaoItemValuesKey key);

    int insert(TaobaoItemValues record);

    int insertSelective(TaobaoItemValues record);

    TaobaoItemValues selectByPrimaryKey(TaobaoItemValuesKey key);

    int updateByPrimaryKeySelective(TaobaoItemValues record);

    int updateByPrimaryKey(TaobaoItemValues record);
    
    TaobaoItemValues getTaobaoItemValues(TaobaoItemValues record);
}