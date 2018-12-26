package com.maxcar.statistics.service.impl;

import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.*;
import com.maxcar.statistics.service.ReportByCarbrandService;
import com.maxcar.statistics.service.impl.mapperService.CarbrandMapperService;
import com.maxcar.statistics.service.impl.mapperService.ReportMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 车辆品牌统计
 * create_date: lxy 2018/11/20  14:35
 **/

@Service("reportByCarbrandService")
public class ReportByCarbrandServiceImpl implements ReportByCarbrandService {

    @Autowired
    private ReportMapperService reportMapperService;

    @Autowired
    private CarbrandMapperService carbrandMapperService;

    /**
     * param:
     * describe: 查询市场 或者 商户 车辆品牌集合
     * create_date:  lxy   2018/12/1  11:15
     **/
    @Override
    public List<String> getAllBrandName(GetAllBrandNameRequest request) {
        return carbrandMapperService.getAllBrandName(request);
    }

    /**
     * param:
     * describe: 分组查询车辆品牌日表 交易
     * create_date:  lxy   2018/11/22  17:13
     **/
    @Override
    public List<GroupCarbrandInvoiceDayResponse> groupCarbrandInvoiceDay(GroupCarbrandInvoiceDayRequest parameter) {

        return carbrandMapperService.groupCarbrandInvoiceDay(parameter);
    }


    /**
     * param:
     * describe: 根据结束日期查询 库存量与库存价值
     * create_date:  lxy   2018/11/26  14:28
     **/
    @Override
    public List<GroupCarbrandInventoryDayResponse> groupCarbrandInventoryDay(GroupCarbrandInventoryDayRequest parameter) {

        return carbrandMapperService.groupCarbrandInventoryDay(parameter);
    }


    /**
     * param:
     * describe: 分组查询车辆品牌月表 交易
     * create_date:  lxy   2018/11/26  15:04
     **/
    @Override
    public List<GroupCarbrandInvoiceMonthResponse> groupCarbrandInvoiceMonth(GroupCarbrandInvoiceMonthRequest parameter){

        return carbrandMapperService.groupCarbrandInvoiceMonth(parameter);
    }

    /**
     * param:
     * describe: 分组查询车辆品牌月表 库存
     * create_date:  lxy   2018/11/26  15:29
     **/
    @Override
    public List<GroupCarbrandInventoryMonthResponse>  groupCarbrandInventoryMonth(GroupCarbrandInventoryMonthRequest parameter){

        return carbrandMapperService.groupCarbrandInventoryMonth(parameter);
    }



// 以下待删除

/*    *//**
     * param:
     * describe: 根据车辆品牌统计车辆库存量与库存价值
     * create_date:  lxy   2018/11/20  14:10
     **//*
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

    *//**
     * param:  
     * describe: 根据车辆品牌统计车辆交易量与交易价值
     * create_date:  lxy   2018/11/20  15:07 
     **//*
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
    
    */
}
