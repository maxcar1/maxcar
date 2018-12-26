package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.model.response.GroupYesterdayRankingResponse;

import java.util.List;

public interface RankingService {

    void  test();

    /**
     * param:
     * describe:  实时查询__总览——获取昨日市场排行  商户排行 --> 交易
     * create_date:  lxy   2018/11/14  18:03
     **/
    List<GroupYesterdayRankingResponse> getYesterdayRanking(GroupYesterdayRankingRequest request);

    /**
     * param:
     * describe: 实时查询__总览——获取指定条件市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    List<GetInvoiceRankingResponse> getInvoiceRankingByCondition(GetInvoiceRankingByConditionRequest request);


    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
   // List<GetInventoryRankingResponse> getYesterdayInventoryRanking(GetYesterdayInventoryRankingRequest request);

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    List<GetInventoryRankingResponse> getInventoryRankingByCondition(GetInventoryRankingByConditionRequest request);

}
