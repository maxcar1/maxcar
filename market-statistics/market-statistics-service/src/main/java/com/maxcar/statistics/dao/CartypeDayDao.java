package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.CartypeDayProvider;
import com.maxcar.statistics.model.parameter.GroupCartypeDayParameter;
import com.maxcar.statistics.model.parameter.InsertCartypeDayParamter;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


public interface CartypeDayDao {

    /**
     * param:
     * describe: 批量插入车辆类型日表(处理好values再调用该方法)
     * create_date:  lxy   2018/11/22  11:03 
     **/
    @InsertProvider(type = CartypeDayProvider.class, method = "InsertCartypeDay")
    boolean InsertCartypeDay(InsertCartypeDayParamter parameter);

    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    @SelectProvider(type = CartypeDayProvider.class, method = "groupCartypeDay")
    List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayParameter parameter);

}
