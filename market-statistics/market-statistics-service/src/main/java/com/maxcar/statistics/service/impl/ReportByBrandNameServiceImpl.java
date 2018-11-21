package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.request.GetInventoryByBrandNameReportRequest;
import com.maxcar.statistics.model.request.GetInvoiceByBrandNameReportRequest;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInventoryReportResponse;
import com.maxcar.statistics.service.impl.mapperService.ReportMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 车辆品牌统计
 * create_date: lxy 2018/11/20  14:35
 **/

@Service
public class ReportByBrandNameServiceImpl {

    @Autowired
    private ReportMapperService reportMapperService;

    /**
     * param:
     * describe: 根据车辆品牌统计车辆库存量与库存价值
     * create_date:  lxy   2018/11/20  14:10
     **/
    public List<GetInventoryReportResponse> getInventoryByBrandNameReport(GetInventoryByBrandNameReportRequest request) {

        GetInventoryReportParameter parameter = new GetInventoryReportParameter();


        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());
        parameter.setMarketId(request.getMarketId());
        parameter.setTenantId(request.getTenantId());
        parameter.setOrderBy(request.getOrderBy());

        parameter.setGroupByColumns("brandName");

        return reportMapperService.getInventoryReport(parameter);
    }

    /**
     * param:  
     * describe: 根据车辆品牌统计车辆交易量与交易价值
     * create_date:  lxy   2018/11/20  15:07 
     **/
    public List<GetCarInvoiceTypeInvoiceReportResponse> getInvoiceByBrandNameReport(GetInvoiceByBrandNameReportRequest request){

        GetCarInvoiceTypeInvoiceReportParameter parameter = new GetCarInvoiceTypeInvoiceReportParameter();

        parameter.setMarketId(request.getMarketId());
        parameter.setTenantId(request.getTenantId());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());
        parameter.setOrderBy(request.getOrderBy());

        parameter.setGroupByColumns("brandName");

        return reportMapperService.getCarInvoiceTypeInvoiceReport(parameter);
    }
    
    
}
