package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GroupCaryearInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCaryearInvoiceDayRequest;
import org.apache.ibatis.jdbc.SQL;

public class CaryearDayProvider {

    public String groupCaryearInvoiceDay(GroupCaryearInvoiceDayRequest parameter){
        return new SQL() {{
            SELECT(" year_id AS 'yearId',\n" +
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
            FROM("`maxcar_statistics_l`.`caryear_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("DATE_FORMAT(report_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D') ");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(report_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");
            }

            if (StringUtil.isNotEmpty(parameter.getYearId())) {
                WHERE("year_id = #{yearId}");
            }

            GROUP_BY("year_id");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String groupCaryearInventoryDay(GroupCaryearInventoryDayRequest parameter) {
        return new SQL() {{
            SELECT("  year_id AS 'yearId',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`caryear_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE(" DATE_FORMAT(report_time, '%Y-%m-%D') = DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");
            }

            GROUP_BY("year_id");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String groupCaryearInvoiceDayByMOnth(String timeByMonth){
        return new SQL() {{
            SELECT(" market_id AS marketId,tenant_id AS tenantId," +
                    "  year_id AS 'yearId',\n" +
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
            FROM("`maxcar_statistics_l`.`caryear_day`");
            WHERE(" DATE_FORMAT(report_time, '%Y-%m') = DATE_FORMAT(#{timeByMonth}, '%Y-%m')");
            GROUP_BY(" market_id,tenant_id,year_id;");

        }}.toString();
    }


    public String groupCaryearInventoryDayByMonth(String timeByMonth) {
        return new SQL() {{
            SELECT(" market_id AS marketId,tenant_id AS tenantId," +
                    "  year_id AS 'yearId',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`caryear_day`");

            WHERE(" DATE_FORMAT(report_time, '%Y-%m-%D') = DATE_FORMAT(#{timeByMonth}, '%Y-%m-%D')");
            GROUP_BY(" market_id,tenant_id,year_id;");

        }}.toString();
    }



}
