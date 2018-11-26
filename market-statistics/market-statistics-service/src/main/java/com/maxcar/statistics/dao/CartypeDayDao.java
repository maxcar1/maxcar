package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CartypeDayProvider;
import com.maxcar.statistics.model.request.GroupCartypeDayRequest;
import com.maxcar.statistics.model.response.GroupCartypeDayByMonthResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


public interface CartypeDayDao extends BaseDao {


    /**
     * param:
     * describe: 统计当前月的车辆类型日表集合
     * create_date:  lxy   2018/11/23  11:34
     **/
    @SelectProvider(type = CartypeDayProvider.class, method = "groupCartypeDayByMonth")
    List<GroupCartypeDayByMonthResponse> groupCartypeDayByMonth(String timeByMonth);

    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    @SelectProvider(type = CartypeDayProvider.class, method = "groupCartypeDay")
    List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayRequest parameter);


}
