package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.ReportProvider;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * describe: 统计
 * create_date: lxy 2018/11/19  10:39
 **/
public interface ReportDao {

    /**
     * param:
     * describe: 实时查询__统计报表——车辆类型统计  --> 交易
     * create_date:  lxy   2018/11/16  18:32
     **/
    @SelectProvider(type = ReportProvider.class, method = "getCarInvoiceTypeInvoiceReport")
    List<GetCarInvoiceTypeInvoiceReportResponse> getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter);

}
