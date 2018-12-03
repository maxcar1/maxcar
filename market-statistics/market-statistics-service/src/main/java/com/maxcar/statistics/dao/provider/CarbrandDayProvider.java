package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.GetAllBrandNameRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInventoryDayRequest;
import com.maxcar.statistics.model.request.GroupCarbrandInvoiceDayRequest;
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
                WHERE("report_time >= #{startTime}");
            }

            if (StringUtil.isNotEmpty(parameter.getEndTime())) {
                WHERE("report_time <= #{endTime}");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("brand_name = #{brandName}");
            }

            GROUP_BY("brand_name");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
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
                WHERE("report_time = #{endTime}");
            }

            if (StringUtil.isNotEmpty(parameter.getBrandName())) {
                WHERE("brand_name = #{brandName}");
            }

            GROUP_BY("brand_name");
            ORDER_BY(parameter.getOrderBy());

        }}.toString();
    }



    public String getAllBrandName(GetAllBrandNameRequest request){
        return new SQL() {{
            SELECT("brand_name");
            FROM("`maxcar_stock_l`.`car_base` AS cb   LEFT JOIN  `maxcar_stock_l`.`car` c ON cb.id = c.id ");
            if (StringUtil.isNotEmpty(request.getMarketId())){
                WHERE("c.market_id =#{marketId}");
            }
            if (StringUtil.isNotEmpty(request.getTenantId())){
                WHERE("c.tenant =#{tenantId}");
            }
            GROUP_BY(" cb.brand_name");
        }}.toString();
    }

}
