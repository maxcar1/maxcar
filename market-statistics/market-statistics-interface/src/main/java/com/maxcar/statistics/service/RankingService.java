package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.GetInventoryRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetYesterdayInventoryRankingRequest;
import com.maxcar.statistics.model.request.GetYesterdayInvoiceRankingRequest;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;

import java.util.List;

public interface RankingService {

    /**
     * param:
     * describe:  实时查询__总览——获取昨日市场排行  商户排行 --> 交易
     * create_date:  lxy   2018/11/14  18:03
     **/
    List<GetInvoiceRankingResponse> getYesterdayInvoiceRanking(GetYesterdayInvoiceRankingRequest request);

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
    List<GetInventoryRankingResponse> getYesterdayInventoryRanking(GetYesterdayInventoryRankingRequest request);

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    List<GetInventoryRankingResponse> getInventoryRankingByCondition(GetInventoryRankingByConditionRequest request);

}
