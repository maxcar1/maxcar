package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.ReportDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceMapperService {

    @Autowired
    private ReportDao reportDao;

    /**
     * param:
     * describe: 实时查询__统计报表——车辆类型统计  --> 交易
     * create_date:  lxy   2018/11/19  10:18
     **/
    public List<GetCarInvoiceTypeInvoiceReportResponse> getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter){

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return reportDao.getCarInvoiceTypeInvoiceReport(parameter);
    }
}
