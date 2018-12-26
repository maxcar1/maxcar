package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCarstocktimeInvoiceMonthResponse;

import java.util.List;

public interface ReportCarstocktimeService {

    /**
     * param:
     * describe: 统计当前月的车辆库存天数日表集合 交易
     * create_date:  lxy   2018/11/28  10:24
     **/
    List<GroupCarstocktimeInvoiceDayResponse> groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter);

    /**
     * param:
     * describe: 统计当前的车辆库存天数日表库存集合
     * create_date:  lxy   2018/11/26  14:50
     **/
    List<GroupCarstocktimeInventoryDayResponse> groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆库存时长月表 交易
     * create_date:  lxy   2018/11/26  15:29
     **/
    List<GroupCarstocktimeInvoiceMonthResponse> groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter);

    /**
     * param:
     * describe: 分组查询车辆库存时长月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    List<GroupCarstocktimeInventoryMonthResponse> groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter);

}
