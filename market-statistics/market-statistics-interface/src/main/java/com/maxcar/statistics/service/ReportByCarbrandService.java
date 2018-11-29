package com.maxcar.statistics.service;


import com.maxcar.statistics.model.request.GroupCarbrandInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceMonthRequest;
import com.maxcar.statistics.model.response.GroupCarbrandInventoryDayResponse;
import com.maxcar.statistics.model.response.GroupCarbrandInventoryMonthResponse;
import com.maxcar.statistics.model.response.GroupCarbrandInvoiceDayResponse;
import com.maxcar.statistics.model.response.GroupCarbrandInvoiceMonthResponse;

import java.util.List;

public interface ReportByCarbrandService {


    /**
     * param:
     * describe: 分组查询车辆品牌日表 交易
     * create_date:  lxy   2018/11/22  17:13
     **/
    List<GroupCarbrandInvoiceDayResponse> groupCarbrandInvoiceDay(GroupCarbrandInvoiceDayRequest parameter);


    /**
     * param:
     * describe: 根据结束日期查询 库存量与库存价值
     * create_date:  lxy   2018/11/26  14:28
     **/
    List<GroupCarbrandInventoryDayResponse> groupCarbrandInventoryDay(GroupCarbrandInventoryDayRequest parameter);

    /**
     * param:
     * describe: 分组查询车辆品牌月表 交易
     * create_date:  lxy   2018/11/26  15:04
     **/
    List<GroupCarbrandInvoiceMonthResponse> groupCarbrandInvoiceMonth(GroupCarbrandInvoiceMonthRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆品牌月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    List<GroupCarbrandInventoryMonthResponse>  groupCarbrandInventoryMonth(GroupCarbrandInventoryMonthRequest parameter);
}
