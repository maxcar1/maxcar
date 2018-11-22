package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.parameter.GroupCartypeDayParameter;
import com.maxcar.statistics.model.parameter.InsertCartypeDayParamter;
import org.apache.ibatis.jdbc.SQL;

public class CartypeDayProvider {

    public String InsertCartypeDay(InsertCartypeDayParamter parameter) {

        return new SQL() {{
            INSERT_INTO("`maxcar_statistics_l`.`cartype_day`");
            VALUES(parameter.getColumns(), parameter.getValues());
        }}.toString();
    }


    public String groupCartypeDay(GroupCartypeDayParameter parameter) {
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

            if (StringUtil.isNotEmpty(parameter.getType_id())) {
                WHERE("type_id <= #{typeId}");
            }

            GROUP_BY("type_id");
            ORDER_BY(parameter.getOrderBy());
        }}.toString();
    }

}
