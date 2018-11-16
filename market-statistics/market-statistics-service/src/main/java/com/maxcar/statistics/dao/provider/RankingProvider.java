package com.maxcar.statistics.dao.provider;


import com.maxcar.statistics.model.request.GetInvoiceRankingRequest;
import com.maxcar.statistics.model.request.GetInventoryRankingRequest;
import org.apache.ibatis.jdbc.SQL;

/**
 * param:
 * describe: 排名统计相关
 * create_date:  lxy   2018/11/14  16:30
 **/
public class RankingProvider {

    public String getInvoiceRanking(GetInvoiceRankingRequest request) {
        return new SQL() {{
            SELECT(request.getSelectColumns());
            FROM(" maxcar_market_l.invoice AS i \n" +
                    "  LEFT JOIN maxcar_user_l.`market` AS m \n" +
                    "    ON i.market_id = m.id \n" +
                    "  LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut \n" +
                    "    ON i.tenant_id = ut.id ");
            WHERE(request.getSelectCondition());

            GROUP_BY(request.getGroupByColumns());

            ORDER_BY(request.getOrderBy() + " DESC ");

        }}.toString() + "  limit 10 ";
    }

    public String getInventoryRanking(GetInventoryRankingRequest request) {
        return new SQL() {{
            SELECT(request.getSelectColumns());
            FROM("  maxcar_stock_l.car AS c\n" +
                    "  LEFT JOIN maxcar_stock_l.car_base AS cb\n" +
                    "    ON cb.id = c.id \n" +
                    "  LEFT JOIN maxcar_user_l.`market` AS m \n" +
                    "    ON c.market_id = m.id \n" +
                    "  LEFT JOIN `maxcar_tenant_l`.`user_tenant` AS ut \n" +
                    "    ON c.tenant = ut.id  ");
            WHERE(request.getSelectCondition());

            GROUP_BY(request.getGroupByColumns());

            ORDER_BY(request.getOrderBy() + " DESC ");

        }}.toString() + "  limit 10 ";
    }
}
