package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.CartypeDayProvider;
import com.maxcar.statistics.model.parameter.InsertCartypeDayParamter;
import org.apache.ibatis.annotations.InsertProvider;


public interface CartypeDayDao {

    /**
     * param:
     * describe: 批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03 
     **/
    @InsertProvider(type = CartypeDayProvider.class, method = "InsertCartypeDay")
    boolean InsertCartypeDay(InsertCartypeDayParamter parameter);


}
