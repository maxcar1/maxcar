package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CarbrandDayProvider;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceDayRequest;
import com.maxcar.statistics.model.response.GroupCarbrandInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCarbrandInvoiceDayResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CarbrandDayDao extends BaseDao {

    /**
     * param:
     * describe: 统计当前的车辆品牌日表交易集合
     * create_date:  lxy   2018/11/26  11:46
     **/

    @SelectProvider(type = CarbrandDayProvider.class, method = "groupCarbrandInvoiceDay")
    List<GroupCarbrandInvoiceDayResponse> groupCarbrandInvoiceDay(GroupCarbrandInvoiceDayRequest parameter);

    /**
     * param:
     * describe: 统计当前的车辆品牌日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @SelectProvider(type = CarbrandDayProvider.class, method = "groupCarbrandInventoryDay")
    List<GroupCarbrandInventoryDayResponse> groupCarbrandInventoryDay(GroupCarbrandInventoryDayRequest parameter);

}
