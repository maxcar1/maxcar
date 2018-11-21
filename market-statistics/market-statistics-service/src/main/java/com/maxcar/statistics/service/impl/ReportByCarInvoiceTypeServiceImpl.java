package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceByCarInvoiceTypeReportParameter;
import com.maxcar.statistics.model.request.GetCarInvoiceTypeInvoiceRankingRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import com.maxcar.statistics.service.ReportByCarInvoiceTypeService;
import com.maxcar.statistics.service.impl.mapperService.ReportMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 统计报表相关 -- 车辆类型统计
 * create_date: lxy 2018/11/19  11:56
 **/
@Service("reportByCarInvoiceTypeService")
public class ReportByCarInvoiceTypeServiceImpl  implements ReportByCarInvoiceTypeService {

    @Autowired
    private ReportMapperService reportMapperService;

    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    @Override
    public List<GetCarInvoiceTypeInvoiceReportResponse> getInvoiceByCarInvoiceTypeReport(GetCarInvoiceTypeInvoiceRankingRequest request) {

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();

        parameter.setMarketId(request.getMarketId());
        parameter.setTenantId(request.getTenantId());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());
        parameter.setOrderBy(request.getOrderBy());
        parameter.setGroupByColumns("carInvoiceType");

        return reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);
    }


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    @Override
    public  List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReportMonth(GetInvoiceByCarInvoiceTypeReportRequest request){

        GetInvoiceByCarInvoiceTypeReportParameter parameter =new GetInvoiceByCarInvoiceTypeReportParameter();


        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" WHERE 1=1 ");

        if (StringUtil.isNotEmpty(request.getCarInvoiceType())){

            stringBuffer.append(" AND  car_invoice_type = #{carInvoiceType} ");
            parameter.setCarInvoiceType(request.getCarInvoiceType());
        }

        if (StringUtil.isNotEmpty(request.getMarketId())){

            stringBuffer.append(" AND  market_id = #{marketId} ");
            parameter.setMarketId(request.getMarketId());
        }

        if (StringUtil.isNotEmpty(request.getTenantId())){

            stringBuffer.append(" AND  tenant_id = #{tenantId} ");
            parameter.setMarketId(request.getMarketId());
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return  reportMapperService.getInvoiceByCarInvoiceTypeReport(parameter);
    }


}
