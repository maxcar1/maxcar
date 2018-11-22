package com.maxcar.statistics.service;

import com.maxcar.statistics.model.parameter.GroupCartypeDayParameter;
import com.maxcar.statistics.model.request.GetCarInvoiceTypeInvoiceRankingRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;

import java.util.List;

public interface ReportGroupCartypeDayService {


    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayParameter parameter);


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReportMonth(GetInvoiceByCarInvoiceTypeReportRequest request);
}
