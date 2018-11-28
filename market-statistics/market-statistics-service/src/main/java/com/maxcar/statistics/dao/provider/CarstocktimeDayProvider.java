package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import org.apache.ibatis.jdbc.SQL;

public class CarstocktimeDayProvider {


    public String groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter) {
        return new SQL() {{
            SELECT(" stocktime_id AS 'stocktimeId',\n" +
                    "  IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "  IFNULL(SUM(sales_price), 0) AS 'invoicePrice',\n" +
                    "  IFNULL(SUM(male_count), 0) AS 'maleCount',\n" +
                    "  IFNULL(SUM(female_count), 0) AS 'femaleCount',\n" +
                    "  IFNULL(SUM(age20_count), 0) AS 'age20Count',\n" +
                    "  IFNULL(SUM(age25_count), 0) AS 'age25Count',\n" +
                    "  IFNULL(SUM(age30_count), 0) AS 'age30Count',\n" +
                    "  IFNULL(SUM(age35_count), 0) AS 'age35Count',\n" +
                    "  IFNULL(SUM(age40_count), 0) AS 'age40Count',\n" +
                    "  IFNULL(SUM(age45_count), 0) AS 'age45Count',\n" +
                    "  IFNULL(SUM(age50_count), 0) AS 'age50Count'");
            FROM("`maxcar_statistics_l`.`carstocktime_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("report_time >= #{startTime}");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("report_time <= #{endTime}");
            }

            if (StringUtil.isNotEmpty(parameter.getStocktimeId())) {
                WHERE("stocktime_id <= #{stocktimeId}");
            }


            GROUP_BY("stocktime_id");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String groupCarstocktimeInventoryDay(GroupCarstocktimeInventoryDayRequest parameter) {
        return new SQL() {{
            SELECT("  stocktime_id AS 'stocktimeId',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`carstocktime_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("report_time = #{endTime}");
            }

            GROUP_BY("stocktime_id");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }

}
