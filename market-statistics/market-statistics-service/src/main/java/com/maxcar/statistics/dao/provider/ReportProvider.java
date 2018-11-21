package com.maxcar.statistics.dao.provider;


import com.maxcar.statistics.model.parameter.GetCarInvoiceTypeInvoiceReportParameter;
import com.maxcar.statistics.model.parameter.GetInventoryReportParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceByCarInvoiceTypeReportParameter;
import org.apache.ibatis.jdbc.SQL;

/**
 * param:
 * describe:  统计报表相关
 * create_date:  lxy   2018/11/14  16:30
 **/
public class ReportProvider {

    public String getCarInvoiceTypeInvoiceReport(GetCarInvoiceTypeInvoiceReportParameter parameter) {
        return new SQL() {{
            SELECT(parameter.getSelectColumns());
            FROM(" `maxcar_market_l`.`invoice` AS i LEFT JOIN `maxcar_stock_l`.`car` AS c ON i.`car_id` = c.`id` LEFT JOIN `maxcar_stock_l`.`car_base` AS cb  ON i.`car_id` = cb.`id` ");
            WHERE(parameter.getSelectCondition());
            GROUP_BY(parameter.getGroupByColumns());
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String getInvoiceByCarInvoiceTypeReport(GetInvoiceByCarInvoiceTypeReportParameter parameter) {

        return new SQL() {{
            SELECT(" num.num_time AS 'numTime', IFNULL(i.invoiceCount,0) AS 'invoiceCount',IFNULL(i.invoicePrice,0)  AS 'invoicePrice'");
            FROM("(SELECT \n" +
                    "    COUNT(*),\n" +
                    "    DATE_FORMAT(num_time, '%Y-%m') AS 'num_time' \n" +
                    "  FROM\n" +
                    "    maxcar_statistics_l.date_time_num \n" +
                    "  WHERE DATE_FORMAT(num_time, '%Y-%m') >= DATE_FORMAT(#{startTime}, '%Y-%m') \n" +
                    "  AND DATE_FORMAT(num_time, '%Y-%m') <= DATE_FORMAT(#{endTime}, '%Y-%m') \n" +
                    "  GROUP BY DATE_FORMAT(num_time, '%Y-%m')) AS num   " +
                    "LEFT JOIN \n" +
                    "    (SELECT \n" +
                    "      DATE_FORMAT(bill_time, '%Y-%m') AS 'bill_time',\n" +
                    "      IFNULL(COUNT(*), 0) AS invoiceCount,\n" +
                    "      IFNULL(SUM(price), 0) AS invoicePrice \n" +
                    "    FROM\n" +
                    "      maxcar_market_l.invoice \n"
                    + parameter.getSelectCondition() +
                    "    GROUP BY DATE_FORMAT(bill_time, '%Y-%m')) AS i \n" +
                    "    ON num.num_time = i.bill_time ");

        }}.toString();
    }


    public String getInventoryReport(GetInventoryReportParameter parameter) {
        return new SQL() {{
            SELECT(parameter.getSelectColumns());
            FROM(" `maxcar_stock_l`.`car` AS c LEFT JOIN `maxcar_stock_l`.`car_base` AS cb    ON c.id = cb.id ");
            WHERE(parameter.getSelectCondition());
            GROUP_BY(parameter.getGroupByColumns());
            ORDER_BY(parameter.getOrderBy());
        }}.toString();
    }


}
