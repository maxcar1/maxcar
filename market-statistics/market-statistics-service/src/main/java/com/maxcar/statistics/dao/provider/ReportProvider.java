package com.maxcar.statistics.dao.provider;


import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import org.apache.ibatis.jdbc.SQL;

/**
 * param:
 * describe: 排名统计相关
 * create_date:  lxy   2018/11/14  16:30
 **/
public class ReportProvider {

    public String getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter) {
        return new SQL() {{
            SELECT(" car_invoice_type AS carInvoiceType, IFNULL(COUNT(*), 0) AS invoiceCount, IFNULL(SUM(price), 0) AS invoicePrice ");
            FROM(" maxcar_market_l.invoice ");
            WHERE(parameter.getSelectCondition());
            GROUP_BY("car_invoice_type");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


}
