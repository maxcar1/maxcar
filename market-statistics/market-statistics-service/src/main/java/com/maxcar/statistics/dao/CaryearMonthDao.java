package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CaryearMonthProvider;
import com.maxcar.statistics.model.request.GroupCaryearInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCaryearInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceMonthResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CaryearMonthDao extends BaseDao {


    /**
     * param:
     * describe: 分组查询车辆年限月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CaryearMonthProvider.class, method = "groupCaryearInvoiceMonth")
    List<GroupCaryearInvoiceMonthResponse> groupCaryearInvoiceMonth(GroupCaryearInvoiceMonthRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆年限月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CaryearMonthProvider.class, method = "groupCaryearInventoryMonth")
    List<GroupCaryearInventoryMonthResponse> groupCaryearInventoryMonth(GroupCaryearInventoryMonthRequest parameter);

}
