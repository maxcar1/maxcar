package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.GetCarInvoiceTypeInvoiceRankingRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;

import java.util.List;

public interface ReportByCarInvoiceTypeService {


    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    List<GetCarInvoiceTypeInvoiceReportResponse> getInvoiceByCarInvoiceTypeReport(GetCarInvoiceTypeInvoiceRankingRequest request);


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReportMonth(GetInvoiceByCarInvoiceTypeReportRequest request);
}
