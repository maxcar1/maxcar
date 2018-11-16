package com.maxcar.statistics.service.impl.mapperService;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.dao.RankingDao;
import com.maxcar.statistics.model.parameter.GetInventoryRankingParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceRankingParameter;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingMapperService {

    @Autowired
    private RankingDao rankingDao;

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/14  18:03
     **/
    public List<GetInvoiceRankingResponse> getInvoiceRanking(GetInvoiceRankingParameter parameter) {

        String selectColumns = " i.market_id AS marketId,m.name AS marketName, IFNULL(COUNT(*), 0) AS invoiceCount,IFNULL(SUM(i.price), 0) AS invoicePrice ";
        String groupByColumns = " i.market_id ";

        if (StringUtil.isNotEmpty(parameter.getMarketId())) {
            selectColumns = " i.tenant_id AS tenantId,ut.tenant_name AS tenantName,IFNULL(COUNT(*), 0) AS invoiceCount,IFNULL(SUM(i.price), 0) AS invoicePrice ";
            groupByColumns = " i.tenant_id";
        }

        parameter.setSelectColumns(selectColumns);
        parameter.setGroupByColumns(groupByColumns);

        if (StringUtil.isEmpty(parameter.getOrderBy())) {
            parameter.setOrderBy("invoiceCount");
        }

        return rankingDao.getInvoiceRanking(parameter);
    }

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    public List<GetInventoryRankingResponse> getInventoryRanking(GetInventoryRankingParameter request) {
        String selectColumns = " c.market_id AS marketId, m.name AS marketName, IFNULL(COUNT(*),0) AS 'inventoryCount',IFNULL(SUM(cb.evaluate_price),0) AS 'inventoryPrice' ";
        String groupByColumns = "c.market_id ";

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectColumns = "c.tenant AS tenantId,ut.tenant_name AS tenantName,IFNULL(COUNT(*),0) AS 'inventoryCount',IFNULL(SUM(cb.evaluate_price),0) AS 'inventoryPrice' ";
            groupByColumns = "c.tenant";
        }

        request.setSelectColumns(selectColumns);
        request.setGroupByColumns(groupByColumns);

        if (StringUtil.isEmpty(request.getOrderBy())) {
            request.setOrderBy("inventoryCount");
        }

        return rankingDao.getInventoryRanking(request);
    }

}
