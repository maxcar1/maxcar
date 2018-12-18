package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GetInventoryRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarstocktimeInvoiceDayRequest;
import org.apache.ibatis.jdbc.SQL;

public class CarstocktimeDayProvider {


    public String groupCarstocktimeInvoiceDay(GroupCarstocktimeInvoiceDayRequest parameter) {
        return new SQL() {{
           /* SELECT(" stocktime_id AS 'stocktimeId',\n" +
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
                    "  IFNULL(SUM(age50_count), 0) AS 'age50Count'");*/

            SELECT(" stocktime_id AS 'stocktimeId',\n" +
                    "  IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "  IFNULL(SUM(sales_price), 0) AS 'invoicePrice'");

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
                WHERE("stocktime_id = #{stocktimeId}");
            }


            GROUP_BY("stocktime_id");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }

    public String groupCarstocktimeInvoiceDayRanking(GetInvoiceRankingByConditionRequest parameter) {
        return new SQL() {{

            SELECT("scd.market_id as 'marketId'," +
                    "       m.name as 'marketName', " +
                    "       IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "       IFNULL(SUM(sales_price), 0) AS 'invoicePrice'");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                SELECT("scd.tenant_id as 'tenantId'," +
                        "ut.tenant_name as 'tenantName' ");
            }

            FROM("`maxcar_statistics_l`.`carstocktime_day` as scd " +
                    "  LEFT JOIN maxcar_user_l.`market` AS m " +
                    "   ON scd.market_id = m.id " +
                    "   LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut " +
                    "   ON scd.tenant_id = ut.id ");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("scd.market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%d') >= DATE_FORMAT(#{startTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%d') <= DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getStocktimeId())) {
                WHERE("scd.stocktime_id = #{stocktimeId}");
            }


            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                GROUP_BY("scd.market_id,scd.tenant_id");
            }else {
                GROUP_BY("scd.market_id");
            }

            ORDER_BY(parameter.getOrderBy());

        }}.toString() + " limit 10 ";
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


    public String groupCarstocktimeInventoryDayRanking(GetInventoryRankingByConditionRequest parameter) {
        return new SQL() {{
            SELECT("scd.market_id as 'marketId'," +
                    "        m.name as 'marketName', " +
                    "       IFNULL(SUM(stock_count), 0) AS 'inventoryCount'," +
                    "       IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                SELECT("scd.tenant_id as 'tenantId'," +
                        "ut.tenant_name as 'tenantName' ");
            }

            FROM("`maxcar_statistics_l`.`carstocktime_day` as scd " +
                    "     LEFT JOIN maxcar_user_l.`market` AS m " +
                    "     ON scd.market_id = m.id" +
                    "     LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut " +
                    "     ON scd.tenant_id = ut.id ");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("scd.market_id = #{marketId}");
            }


            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%d') <= DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getStocktimeId())) {
                WHERE("scd.stocktime_id = #{stocktimeId}");
            }


            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                GROUP_BY("scd.market_id,scd.tenant_id");
            }else {
                GROUP_BY("scd.market_id");
            }

            ORDER_BY(parameter.getOrderBy());

        }}.toString() + " limit 10 ";
    }


    public String groupCarstocktimeInvoiceDayByMonth(String timeByMonth) {
        return new SQL() {{

            SELECT(" market_id AS marketId,tenant_id AS tenantId," +
                    "stocktime_id AS 'stocktimeId',\n" +
                    "  IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "  IFNULL(SUM(sales_price), 0) AS 'invoicePrice'");

            FROM("`maxcar_statistics_l`.`carstocktime_day`");
            WHERE(" DATE_FORMAT(report_time, '%Y-%m') = DATE_FORMAT(#{timeByMonth}, '%Y-%m')");
            GROUP_BY(" market_id,tenant_id,stocktime_id;");

        }}.toString();
    }


    public String groupCarstocktimeInventoryDayByMonth(String timeByMonth) {
        return new SQL() {{
            SELECT(" market_id AS marketId,tenant_id AS tenantId," +
                    " stocktime_id AS 'stocktimeId',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`carstocktime_day`");

            WHERE(" DATE_FORMAT(report_time, '%Y-%m-%d') = DATE_FORMAT(#{timeByMonth}, '%Y-%m-%d')");
            GROUP_BY(" market_id,tenant_id,stocktime_id;");

        }}.toString();
    }

}
