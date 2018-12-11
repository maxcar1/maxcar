package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GroupCartypeDayRequest;
import org.apache.ibatis.jdbc.SQL;

public class CartypeDayProvider {


    public String groupCartypeDayByMonth(String timeByMonth) {
        return new SQL() {{
            SELECT("market_id AS marketId,tenant_id AS tenantId,type_id AS typeId,IFNULL(SUM(sales_count), 0) AS invoiceCount,IFNULL(SUM(sales_price), 0) AS invoicePrice");
            FROM(" `maxcar_statistics_l`.`cartype_day` ");
            WHERE(" DATE_FORMAT(report_time, '%Y-%m') = DATE_FORMAT(#{timeByMonth}, '%Y-%m')");
            GROUP_BY(" market_id,tenant_id,type_id");
        }}.toString();
    }


    public String groupCartypeDay(GroupCartypeDayRequest parameter) {
        return new SQL() {{
            SELECT("type_id AS typeId,IFNULL(SUM(sales_count),0) AS 'invoiceCount',IFNULL(SUM(sales_price),0) AS 'invoicePrice'");
            FROM("`maxcar_statistics_l`.`cartype_day`");

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

            if (StringUtil.isNotEmpty(parameter.getTypeId())) {
                WHERE("type_id = #{typeId}");
            }

            GROUP_BY("type_id");
            ORDER_BY(parameter.getOrderBy());
        }}.toString();
    }

    public String groupCartypeDayRanking(GetInvoiceRankingByConditionRequest parameter) {
        return new SQL() {{
            SELECT("scd.market_id as 'marketId'," +
                    "       m.name as 'marketName'," +
                    "       IFNULL(SUM(sales_count),0) AS 'invoiceCount'," +
                    "       IFNULL(SUM(sales_price),0) AS 'invoicePrice'");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                SELECT("scd.tenant_id as 'tenantId',ut.tenant_name as 'tenantName' ");
            }

            FROM("`maxcar_statistics_l`.`cartype_day` as scd " +
                    "LEFT JOIN maxcar_user_l.`market` AS m " +
                    "ON scd.market_id = m.id " +
                    "LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut " +
                    "ON scd.tenant_id = ut.id ");

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                WHERE("scd.market_id = #{marketId}");
            }

            if (StringUtil.isNotEmpty(parameter.getStartTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D') ");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("DATE_FORMAT(scd.report_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");
            }

            if (StringUtil.isNotEmpty(parameter.getTypeId())) {
                WHERE("type_id = #{typeId}");
            }

            if (StringUtil.isNotEmpty(parameter.getMarketId())) {
                GROUP_BY("scd.market_id,scd.tenant_id");
            }else {
                GROUP_BY("scd.market_id");
            }

            ORDER_BY(parameter.getOrderBy());

        }}.toString()+ " limit 10 ";
    }

}
