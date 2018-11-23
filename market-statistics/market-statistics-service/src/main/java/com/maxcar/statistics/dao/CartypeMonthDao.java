package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CartypeMonthProvider;
import com.maxcar.statistics.model.request.GroupCartypeMonthRequest;
import com.maxcar.statistics.model.response.GroupCartypeMonthResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CartypeMonthDao extends BaseDao {



    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    @SelectProvider(type = CartypeMonthProvider.class, method = "groupCartypeMonth")
    List<GroupCartypeMonthResponse> groupCartypeMonth(GroupCartypeMonthRequest parameter);


}
