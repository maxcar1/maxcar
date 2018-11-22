package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.CartypeDayDao;
import com.maxcar.statistics.model.parameter.GetInvoiceByCarInvoiceTypeReportParameter;
import com.maxcar.statistics.model.parameter.GroupCartypeDayParameter;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import com.maxcar.statistics.model.response.GroupCartypeDayResponse;
import com.maxcar.statistics.service.ReportGroupCartypeDayService;
import com.maxcar.statistics.service.impl.mapperService.CartypeMapperService;
import com.maxcar.statistics.service.impl.mapperService.ReportMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 统计报表相关 -- 车辆类型统计
 * create_date: lxy 2018/11/19  11:56
 **/
@Service("reportByCarInvoiceTypeService")
public class ReportGroupCartypeDayServiceImpl implements ReportGroupCartypeDayService {

    @Autowired
    private ReportMapperService reportMapperService;

    @Autowired
    private CartypeMapperService cartypeMapperService;

    @Autowired
    private CartypeDayDao cartypeDayDao;

    /**
     * param:
     * describe: 分组查询车辆类型日表
     * create_date:  lxy   2018/11/22  17:13
     **/
    @Override
    public List<GroupCartypeDayResponse> groupCartypeDay(GroupCartypeDayParameter parameter) {

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return cartypeDayDao.groupCartypeDay(parameter);
    }


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    @Override
    public List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReportMonth(GetInvoiceByCarInvoiceTypeReportRequest request) {

        GetInvoiceByCarInvoiceTypeReportParameter parameter = new GetInvoiceByCarInvoiceTypeReportParameter();


        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" WHERE 1=1 ");

        if (StringUtil.isNotEmpty(request.getCarInvoiceType())) {

            stringBuffer.append(" AND  car_invoice_type = #{carInvoiceType} ");
            parameter.setCarInvoiceType(request.getCarInvoiceType());
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {

            stringBuffer.append(" AND  market_id = #{marketId} ");
            parameter.setMarketId(request.getMarketId());
        }

        if (StringUtil.isNotEmpty(request.getTenantId())) {

            stringBuffer.append(" AND  tenant_id = #{tenantId} ");
            parameter.setMarketId(request.getMarketId());
        }

        parameter.setSelectCondition(stringBuffer.toString());

        return reportMapperService.getInvoiceByCarInvoiceTypeReport(parameter);
    }


}
