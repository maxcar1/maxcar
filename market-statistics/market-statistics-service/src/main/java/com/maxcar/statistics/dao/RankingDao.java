package com.maxcar.statistics.dao;

import com.maxcar.statistics.dao.provider.RankingProvider;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RankingDao {

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/14  18:03
     **/
    @SelectProvider(type = RankingProvider.class, method = "getInvoiceRanking")
    List<GetInvoiceRankingResponse> getInvoiceRanking(GetInvoiceRankingParameter request);


    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    @SelectProvider(type = RankingProvider.class, method = "getInventoryRanking")
    List<GetInventoryRankingResponse> getInventoryRanking(GetInventoryRankingParameter request);
}
