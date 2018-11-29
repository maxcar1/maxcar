package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.GroupCartypeDayRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.request.GroupCartypeMonthRequest;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;
import com.maxcar.statistics.model.response.GroupCartypeMonthResponse;

import java.util.List;

public interface ReportByCartypeService {


    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayRequest parameter);


    /**
     * param:
     * describe: 分组查询车辆类型月表
     * create_date:  lxy   2018/11/22  17:13
     **/
    List<GroupCartypeMonthResponse> groupCartypeMonth(GroupCartypeMonthRequest request);


 }
