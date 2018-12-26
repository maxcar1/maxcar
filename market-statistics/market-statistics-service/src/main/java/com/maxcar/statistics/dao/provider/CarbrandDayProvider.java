package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.*;
import org.apache.ibatis.jdbc.SQL;

public class CarbrandDayProvider {

    public String groupCarbrandInvoiceDay(GroupCarbrandInvoiceDayRequest parameter) {
        return new SQL() {{
            SELECT(" brand_name AS 'brandName',\n" +
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
            FROM("`maxcar_statistics_l`.`carbrand_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("DATE_FORMAT(report_time, '%Y-%m-%d') >= DATE_FORMAT(#{startTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(report_time, '%Y-%m-%d') <= DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("brand_name = #{brandName}");
            }

            GROUP_BY("brand_name");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String groupCarbrandInvoiceDayRanking(GetInvoiceRankingByConditionRequest parameter) {
        return new SQL() {{
            SELECT("scd.market_id as 'marketId'," +
                    "  m.name as 'marketName'," +
                    "  IFNULL(SUM(sales_count), 0) AS 'invoiceCount',\n" +
                    "  IFNULL(SUM(sales_price), 0) AS 'invoicePrice'");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                SELECT("scd.tenant_id as 'tenantId',ut.tenant_name as 'tenantName' ");
            }

            FROM("`maxcar_statistics_l`.`carbrand_day` as scd " +
                    "  LEFT JOIN maxcar_user_l.`market` AS m " +
                    "  ON scd.market_id = m.id " +
                    "  LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut " +
                    "  ON scd.tenant_id = ut.id ");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("scd.market_id = #{marketId}");
            }


            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%d') >= DATE_FORMAT(#{startTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%d') <= DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("scd.brand_name = #{brandName}");
            }


            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                GROUP_BY("scd.market_id,scd.tenant_id");
            }else {
                GROUP_BY("scd.market_id");
            }

            ORDER_BY(parameter.getOrderBy());

        }}.toString() + " limit 10 ";
    }

    public String groupCarbrandInventoryDay(GroupCarbrandInventoryDayRequest parameter) {
        return new SQL() {{
            SELECT("  brand_name AS 'brandName',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`carbrand_day`");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getTenantId())) {
                WHERE("tenant_id = #{tenantId}");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE(" DATE_FORMAT(report_time, '%Y-%m-%d') = DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("brand_name = #{brandName}");
            }

            GROUP_BY("brand_name");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }


    public String groupCarbrandInventoryDayRanking(GetInventoryRankingByConditionRequest parameter) {
        return new SQL() {{
            SELECT(" scd.market_id as 'marketId'," +
                    "         m.name as 'marketName'," +
                    "         IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "         IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                SELECT("scd.tenant_id as 'tenantId'," +
                        "       ut.tenant_name as 'tenantName' ");
            }

            FROM("`maxcar_statistics_l`.`carbrand_day` as scd " +
                    "    LEFT JOIN maxcar_user_l.`market` AS m " +
                    "    ON scd.market_id = m.id " +
                    "    LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut" +
                    "    ON scd.tenant_id = ut.id");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("scd.market_id = #{marketId}");
            }


            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE(" DATE_FORMAT(scd.report_time, '%Y-%m-%d') = DATE_FORMAT(#{endTime}, '%Y-%m-%d') ");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("scd.brand_name = #{brandName}");
            }


            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                GROUP_BY("scd.market_id,scd.tenant_id");
            }else {
                GROUP_BY("scd.market_id");
            }

            ORDER_BY(parameter.getOrderBy());

        }}.toString()+ " limit 10 ";
    }


    public String getAllBrandName(GetAllBrandNameRequest request) {
        return new SQL() {{
            SELECT("brand_name");
            FROM("`maxcar_stock_l`.`car_base` AS cb   LEFT JOIN  `maxcar_stock_l`.`car` c ON cb.id = c.id ");
            if (StringUtil.isNotEmpty(request.getMarketId())) {
                WHERE("c.market_id =#{marketId}");
            }
            if (StringUtil.isNotEmpty(request.getTenantId())) {
                WHERE("c.tenant =#{tenantId}");
            }
            GROUP_BY(" cb.brand_name;");
        }}.toString();
    }

    /**
     * param:
     * describe: 统计指定月 日表交易数据 统计数据
     * create_date:  lxy   2018/12/6  15:00
     **/
    public String groupCarbrandInvoiceDayByMonth(String timeByMonth) {
        return new SQL() {{
            SELECT("market_id AS marketId,tenant_id AS tenantId," +
                    "   brand_name AS 'brandName',\n" +
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
            FROM("`maxcar_statistics_l`.`carbrand_day`");

            WHERE(" DATE_FORMAT(report_time, '%Y-%m') = DATE_FORMAT(#{timeByMonth}, '%Y-%m')");
            GROUP_BY(" market_id,tenant_id,brand_name;");

        }}.toString();
    }

    /**
     * param:
     * describe: 统计指定月 日表库存数据 统计数据
     * create_date:  lxy   2018/12/6  15:00
     **/
    public String groupCarbrandInventoryDayByMonth(String timeByMonth) {
        return new SQL() {{
            SELECT(" market_id AS marketId,tenant_id AS tenantId," +
                    "  brand_name AS 'brandName',\n" +
                    "  IFNULL(SUM(stock_count), 0) AS 'inventoryCount',\n" +
                    "  IFNULL(SUM(stock_price), 0) AS 'inventoryPrice'");

            FROM("`maxcar_statistics_l`.`carbrand_day`");
            WHERE(" DATE_FORMAT(report_time, '%Y-%m-%d') = DATE_FORMAT(#{timeByMonth}, '%Y-%m-%d')");
            GROUP_BY(" market_id,tenant_id,brand_name;");


        }}.toString();
    }


}
