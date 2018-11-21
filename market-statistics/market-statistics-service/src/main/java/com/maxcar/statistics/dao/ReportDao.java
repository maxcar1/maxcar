package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.ReportProvider;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceByCarInvoiceTypeReportParameter;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInventoryReportResponse;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * describe: 统计报表相关
 * create_date: lxy 2018/11/19  10:39
 **/
public interface ReportDao {

    /**
     * param:
     * describe: _统计报表——车辆类型统计  --> 交易量与交易价值
     * create_date:  lxy   2018/11/16  18:32
     **/
    @SelectProvider(type = ReportProvider.class, method = "getCarInvoiceTypeInvoiceReport")
    List<GetCarInvoiceTypeInvoiceReportResponse> getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter);

    /**
     * param:
     * describe: 统计报表——车辆类型统计_  --> 按月按车辆类型 统计 交易量与交易价值
     * create_date:  lxy   2018/11/19  15:58
     **/
    @SelectProvider(type = ReportProvider.class, method = "getInvoiceByCarInvoiceTypeReport")
    List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReport(GetInvoiceByCarInvoiceTypeReportParameter parameter);

    /**
     * param:
     * describe: 统计报表——库存量与库存价值统计 -->
     * create_date:  lxy   2018/11/20  14:00
     **/
    @SelectProvider(type = ReportProvider.class, method = "getInventoryReport")
    List<GetInventoryReportResponse> getInventoryReport(GetInventoryReportParameter parameter);


}
