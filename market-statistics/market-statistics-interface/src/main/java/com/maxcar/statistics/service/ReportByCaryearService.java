package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCaryearInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCaryearInvoiceMonthResponse;

import java.util.List;

public interface ReportByCaryearService {

    /**
     * param:
     * describe: 统计当前月的车辆年限日表集合 交易
     * create_date:  lxy   2018/11/28  10:23
     **/
    List<GroupCaryearInvoiceDayResponse> groupCaryearInvoiceDay(GroupCaryearInvoiceDayRequest parameter);

    /**
     * param:
     * describe: 统计当前的车辆年限日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    List<GroupCaryearInventoryDayResponse> groupCaryearInventoryDay(GroupCaryearInventoryDayRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆年限月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    List<GroupCaryearInvoiceMonthResponse> groupCaryearInvoiceMonth(GroupCaryearInvoiceMonthRequest parameter);

    /**
     * param:
     * describe: 分组查询车辆年限月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    List<GroupCaryearInventoryMonthResponse> groupCaryearInventoryMonth(GroupCaryearInventoryMonthRequest parameter);

}
