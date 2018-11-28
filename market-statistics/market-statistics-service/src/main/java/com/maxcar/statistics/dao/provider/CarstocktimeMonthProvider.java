package com.maxcar.statistics.dao.provider;

import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryMonthRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceMonthRequest;
import org.apache.ibatis.jdbc.SQL;

public class CarstocktimeMonthProvider {

    public String  groupCarstocktimeInvoiceMonth(GroupCarstocktimeInvoiceMonthRequest parameter){
        return new SQL() {{
            SELECT(" numTime AS 'numTime',IFNULL(invoiceCount,0) AS 'invoiceCount' ,IFNULL(invoicePrice,0) AS 'invoicePrice' ");
            FROM(" (SELECT COUNT(*),\n" +
                    "    DATE_FORMAT(num_time, '%Y-%m') AS 'numTime' \n" +
                    "  FROM\n" +
                    "    maxcar_statistics_l.date_time_num \n" +
                    "  WHERE DATE_FORMAT(num_time, '%Y-%m') >= DATE_FORMAT(#{startTime}, '%Y-%m') \n" +
                    "    AND DATE_FORMAT(num_time, '%Y-%m') <= DATE_FORMAT(#{endTime}, '%Y-%m') \n" +
                    "  GROUP BY DATE_FORMAT(num_time, '%Y-%m')) AS num   LEFT JOIN " +
                    " (SELECT \n" +
                    "      report_time AS 'reportTime',\n" +
                    "      IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "      IFNULL(SUM(sales_price), 0) AS 'invoicePrice' \n" +
                    "    FROM\n" +
                    "      `maxcar_statistics_l`.`carstocktime_month` " + parameter.getSelectCondition() +
                    " GROUP BY report_time) AS cm   " +
                    "ON num.numTime = cm.reportTime  ");

            ORDER_BY("numTime");
        }}.toString();
    }

    public String  groupCarstocktimeInventoryMonth(GroupCarstocktimeInventoryMonthRequest parameter){
        return new SQL() {{
            SELECT(" numTime AS 'numTime',IFNULL(inventoryCount,0) AS 'inventoryCount' ,IFNULL(inventoryPrice,0) AS 'inventoryPrice' ");
            FROM(" (SELECT COUNT(*),\n" +
                    "    DATE_FORMAT(num_time, '%Y-%m') AS 'numTime' \n" +
                    "  FROM\n" +
                    "    maxcar_statistics_l.date_time_num \n" +
                    "  WHERE DATE_FORMAT(num_time, '%Y-%m') >= DATE_FORMAT(#{startTime}, '%Y-%m') \n" +
                    "    AND DATE_FORMAT(num_time, '%Y-%m') <= DATE_FORMAT(#{endTime}, '%Y-%m') \n" +
                    "  GROUP BY DATE_FORMAT(num_time, '%Y-%m')) AS num   LEFT JOIN " +
                    " (SELECT \n" +
                    "      report_time AS 'reportTime',\n" +
                    "      IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "      IFNULL(SUM(stock_price), 0) AS 'inventoryPrice' \n" +
                    "    FROM\n" +
                    "      `maxcar_statistics_l`.`carstocktime_month` " + parameter.getSelectCondition() +
                    " GROUP BY report_time) AS cm   " +
                    "ON num.numTime = cm.reportTime  ");

            ORDER_BY("numTime");
        }}.toString();
    }
}
