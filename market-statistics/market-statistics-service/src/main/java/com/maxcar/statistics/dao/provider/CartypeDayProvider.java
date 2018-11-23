package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
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

}
