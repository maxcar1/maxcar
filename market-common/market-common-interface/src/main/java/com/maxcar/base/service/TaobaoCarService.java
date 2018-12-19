package com.maxcar.base.service;


import com.maxcar.base.pojo.taobao.TaobaoCar;

import java.util.List;

public interface TaobaoCarService {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoCar record);

    int insertSelective(TaobaoCar record);

    TaobaoCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoCar record);

    int updateByPrimaryKey(TaobaoCar record);
    
    List<TaobaoCar> getTaobaoCar(TaobaoCar car);

//    Object selectPackageid(String string);
}