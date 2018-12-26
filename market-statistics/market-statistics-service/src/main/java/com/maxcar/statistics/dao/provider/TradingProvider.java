package com.maxcar.statistics.dao.provider;

import com.maxcar.base.util.StringUtils;
import com.maxcar.statistics.model.request.TradingRequest;
import org.apache.ibatis.jdbc.SQL;


/**
 *
 *  市场交易统计  交易总量和交易价值
 */
public class TradingProvider {

    public String getVolumeAndValue(TradingRequest request){
        return new SQL() {{
            SELECT(request.getSelectColumns());
            FROM("maxcar_market_l.invoice i");
            WHERE(request.getSelectCondition());
            GROUP_BY(request.getGroupByColumns());
        }}.toString();
    }

    public String countCarNum(TradingRequest request){
        return new SQL() {{
            SELECT(request.getSelectColumns());
            FROM(request.getSelectFrom());
            WHERE(request.getSelectCondition());
            GROUP_BY(request.getGroupByColumns());
        }}.toString();
    }

    public String getTenantMonthNum(TradingRequest request){
        return new SQL() {{
            SELECT(request.getSelectColumns());
            FROM("  (SELECT *,COUNT(*) AS num FROM maxcar_market_l.`invoice` GROUP BY tenant_id) i  ");
            WHERE(request.getSelectCondition());
            GROUP_BY(request.getGroupByColumns());
        }}.toString();
    }

}
