package com.maxcar.service;

import java.util.List;

import com.maxcar.core.utils.Result;
import com.maxcar.entity.TaobaoCar;

public interface TaobaoCarService {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoCar record);

    int insertSelective(TaobaoCar record);

    TaobaoCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoCar record);

    int updateByPrimaryKey(TaobaoCar record);
    
    List<TaobaoCar> getTaobaoCar(TaobaoCar car);

    Object selectPackageid(String string);
}