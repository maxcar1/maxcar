package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CarbrandMonthProvider;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarbrandInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarbrandInvoiceMonthResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CarbrandMonthDao extends BaseDao {

    /**
     * param:
     * describe: 分组查询车辆品牌月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CarbrandMonthProvider.class, method = "groupCarbrandInvoiceMonth")
    List<GroupCarbrandInvoiceMonthResponse> groupCarbrandInvoiceMonth(GroupCarbrandInvoiceMonthRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆品牌月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @SelectProvider(type = CarbrandMonthProvider.class, method = "groupCarbrandInventoryMonth")
    List<GroupCarbrandInventoryMonthResponse> groupCarbrandInventoryMonth(GroupCarbrandInventoryMonthRequest parameter);


}
