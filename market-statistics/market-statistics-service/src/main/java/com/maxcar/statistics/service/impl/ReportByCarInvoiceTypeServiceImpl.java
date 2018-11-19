package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.request.GetCarInvoiceTypeInvoiceRankingRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.service.impl.mapperService.ReportServiceMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 统计报表相关 -- 车辆类型统计
 * create_date: lxy 2018/11/19  11:56
 **/
@Service
public class ReportByCarInvoiceTypeServiceImpl {

    @Autowired
    private ReportServiceMapperService reportServiceMapperService;

    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    public List<GetCarInvoiceTypeInvoiceReportResponse> getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceRankingRequest request) {

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" DATE_FORMAT(bill_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')  ");
        stringBuffer.append(" AND DATE_FORMAT(bill_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");

        if (StringUtil.isNotEmpty(request.getMarketId())) {

            stringBuffer.append(" AND market_id = #{marketId} ");
            parameter.setMarketId(request.getMarketId());
        }

        if (StringUtil.isNotEmpty(request.getTenantId())){

            stringBuffer.append(" AND tenant_id = #{tenantId} ");
            parameter.setTenantId(request.getTenantId());
        }

        parameter.setSelectCondition(stringBuffer.toString());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        return reportServiceMapperService.getCarInvoiceTypeInvoiceReport(parameter);
    }


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/


}
