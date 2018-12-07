package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.base.BaseDao;
import com.maxcar.statistics.dao.provider.CaryearDayProvider;
import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import com.maxcar.statistics.model.response.GroupCaryearInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearDayByMOnthResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceDayResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CaryearDayDao extends BaseDao {

    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合 交易
     * create_date:  lxy   2018/11/28  10:23
     **/
    @SelectProvider(type = CaryearDayProvider.class, method = "groupCaryearInvoiceDay")
    List<GroupCaryearInvoiceDayResponse> groupCaryearInvoiceDay(GroupCaryearInvoiceDayRequest parameter);

    /**
     * param:
     * describe: 统计当前的车辆年限日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    @SelectProvider(type = CaryearDayProvider.class, method = "groupCaryearInventoryDay")
    List<GroupCaryearInventoryDayResponse> groupCaryearInventoryDay(GroupCaryearInventoryDayRequest parameter);

    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合  交易
     * create_date:  lxy   2018/11/23  11:34
     **/
    @SelectProvider(type = CaryearDayProvider.class, method = "groupCaryearInvoiceDayByMOnth")
    List<GroupCaryearDayByMOnthResponse> groupCaryearInvoiceDayByMOnth(String timeByMonth);

    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合  库存
     * create_date:  lxy   2018/11/23  11:34
     **/
    @SelectProvider(type = CaryearDayProvider.class, method = "groupCaryearInventoryDayByMonth")
    List<GroupCaryearDayByMOnthResponse> groupCaryearInventoryDayByMonth(String timeByMonth);

}
