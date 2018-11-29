package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.ReportDao;
import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceByCarInvoiceTypeReportParameter;
import com.maxcar.statistics.model.response.GetCarInvoiceTypeInvoiceReportResponse;
import com.maxcar.statistics.model.response.GetInventoryReportResponse;
import com.maxcar.statistics.model.response.GetInvoiceByCarInvoiceTypeReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportMapperService {

    @Autowired
    private ReportDao reportDao;

    /**
     * param:
     * describe: 实时查询__统计报表——车辆类型统计  --> 交易
     * create_date:  lxy   2018/11/19  10:18
     **/
    public List<GetCarInvoiceTypeInvoiceReportResponse> getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter) {

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" DATE_FORMAT(i.bill_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')  ");
        stringBuffer.append(" AND DATE_FORMAT(i.bill_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");


        if ("carInvoiceType".equals(parameter.getGroupByColumns().trim())) {

            parameter.setSelectColumns("i.market_id as 'marketId',i.tenant_id as 'tenantId', i.car_invoice_type AS carInvoiceType, IFNULL(COUNT(*), 0) AS invoiceCount, IFNULL(SUM(i.price), 0) AS invoicePrice ");
            //stringBuffer.append(" AND i.car_invoice_type != '' ");
        } else if ("brandName".equals(parameter.getGroupByColumns().trim())) {

            parameter.setSelectColumns("i.market_id as 'marketId',i.tenant_id as 'tenantId',cb.brand_name AS 'brandName', IFNULL(COUNT(*), 0) AS invoiceCount, IFNULL(SUM(i.price), 0) AS invoicePrice ");
            //stringBuffer.append(" AND cb.brand_name != '' ");
        }

        parameter.setGroupByColumns("i.market_id,i.tenant_id," + parameter.getGroupByColumns());

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" AND i.market_id = #{marketId} ");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" AND i.tenant_id = #{tenantId} ");
        }

        if (StringUtil.isNotEmpty(parameter.getCarInvoiceType())) {

            stringBuffer.append(" AND i.car_invoice_type = #{carInvoiceType} ");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount DESC ");
        } else {
            parameter.setOrderBy(parameter.getOrderBy() + " DESC ");
        }

        return reportDao.getCarInvoiceTypeInvoiceReport(parameter);
    }

    /**
     * param:
     * describe: 实时查询__统计报表——车辆类型统计_  --> 按月按车辆类型 统计 交易量与交易价值
     * create_date:  lxy   2018/11/19  16:00
     **/
    public List<GetInvoiceByCarInvoiceTypeReportResponse> getInvoiceByCarInvoiceTypeReport(GetInvoiceByCarInvoiceTypeReportParameter parameter) {

        return reportDao.getInvoiceByCarInvoiceTypeReport(parameter);
    }


    /**
     * param:
     * describe: 统计报表——库存量与库存价值统计 -->
     * create_date:  lxy   2018/11/20  14:00
     **/
    public List<GetInventoryReportResponse> getInventoryReport(GetInventoryReportParameter parameter) {
        StringBuffer stringBuffer = new StringBuffer(128);

        stringBuffer.append(" DATE_FORMAT(c.insert_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D') ");
        stringBuffer.append(" AND   DATE_FORMAT(c.insert_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");
        stringBuffer.append(" AND  c.stock_status in ('1','2','3') ");

        if ("brandName".equals(parameter.getGroupByColumns().trim())) {

            parameter.setSelectColumns("cb.brand_name AS brandName,IFNULL(COUNT(*), 0) AS 'inventoryCount', IFNULL(SUM(cb.evaluate_price), 0) AS 'inventoryPrice' ");
            stringBuffer.append("cb.brand_name !=''  ");
        }

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {

            stringBuffer.append(" AND c.market_id = #{marketId} ");
        }

        if (StringUtil.isNotEmpty(parameter.getTenantId())) {

            stringBuffer.append(" AND c.tenant = #{tenantId} ");
        }

        parameter.setSelectCondition(stringBuffer.toString());

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy(" inventoryCount desc ");
        } else {
            parameter.setOrderBy(parameter.getOrderBy() + "  desc ");
        }

        return reportDao.getInventoryReport(parameter);
    }
}
