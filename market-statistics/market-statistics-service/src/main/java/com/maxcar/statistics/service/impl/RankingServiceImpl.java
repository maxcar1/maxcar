package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.service.RankingService;
import com.maxcar.statistics.service.impl.mapperService.RankingMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rankingService")
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapperService rankingMapperService;

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/14  18:03
     **/
    @Override
    public List<GetInvoiceRankingResponse> getYesterdayInvoiceRanking(GetYesterdayInvoiceRankingRequest request) {

        String selectCondition = " DATE_FORMAT(i.bill_time, '%Y-%m-%D') = DATE_FORMAT(CURDATE() - 1, '%Y-%m-%D')  ";

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND i.market_id = #{marketId} ";
            selectCondition += " AND i.tenant_id != '' ";
        }

        GetInvoiceRankingRequest getInvoiceRankingRequest = new GetInvoiceRankingRequest();

        getInvoiceRankingRequest.setMarketId(request.getMarketId());
        getInvoiceRankingRequest.setOrderBy(request.getOrderBy());

        getInvoiceRankingRequest.setSelectCondition(selectCondition);

        return rankingMapperService.getInvoiceRanking(getInvoiceRankingRequest);
    }


    /**
     * param: 起止时间  车辆类型（开票类型）
     * describe: 实时查询__排名统计——获取指定条件市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/15  15:47
     **/
    @Override
    public List<GetInvoiceRankingResponse> getInvoiceRankingByCondition(GetInvoiceRankingByConditionRequest request) {

        GetInvoiceRankingRequest getInvoiceRankingRequest = new GetInvoiceRankingRequest();

        String selectCondition = " DATE_FORMAT(i.bill_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')  " +
                " AND DATE_FORMAT(i.bill_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D')  ";

        if (StringUtil.isNotEmpty(request.getCarInvoiceType())) {
            selectCondition += " AND i.car_invoice_type = #{carInvoiceType} ";
            getInvoiceRankingRequest.setCarInvoiceType(request.getCarInvoiceType().trim());
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND i.market_id = #{marketId} ";
            selectCondition += " AND i.tenant_id != '' ";
        }


        getInvoiceRankingRequest.setMarketId(request.getMarketId());
        getInvoiceRankingRequest.setOrderBy(request.getOrderBy());
        getInvoiceRankingRequest.setStartTime(request.getStartTime());
        getInvoiceRankingRequest.setEndTime(request.getEndTime());


        getInvoiceRankingRequest.setSelectCondition(selectCondition);

        return rankingMapperService.getInvoiceRanking(getInvoiceRankingRequest);

    }


    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    @Override
    public List<GetInventoryRankingResponse> getYesterdayInventoryRanking(GetYesterdayInventoryRankingRequest request) {
        String selectCondition = " DATE_FORMAT(c.insert_time, '%Y-%m-%D') < DATE_FORMAT(CURDATE(), '%Y-%m-%D')  ";

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND c.market_id = #{marketId}  ";
            selectCondition += " AND c.tenant != '' ";
        }

        GetInventoryRankingRequest getInventoryRankingRequest = new GetInventoryRankingRequest();
        getInventoryRankingRequest.setMarketId(request.getMarketId());
        getInventoryRankingRequest.setOrderBy(request.getOrderBy());

        getInventoryRankingRequest.setSelectCondition(selectCondition);

        return rankingMapperService.getInventoryRanking(getInventoryRankingRequest);
    }


    /**
     * param:
     * describe: 实时查询__总览——获取指定条件 市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    public List<GetInventoryRankingResponse> getInventoryRankingByCondition(GetInventoryRankingByConditionRequest request) {

        GetInventoryRankingRequest getInventoryRankingRequest = new GetInventoryRankingRequest();

        String selectCondition = " DATE_FORMAT(c.insert_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')" +
                " AND   DATE_FORMAT(c.insert_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ";

        if (StringUtil.isNotEmpty(request.getBrandName())) {
            selectCondition += " AND cb.brand_name = #{brandName}";
            getInventoryRankingRequest.setBrandName(request.getBrandName().trim());
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND c.market_id = #{marketId}  ";
            selectCondition += " AND c.tenant != '' ";
        }

        getInventoryRankingRequest.setMarketId(request.getMarketId());
        getInventoryRankingRequest.setOrderBy(request.getOrderBy());
        getInventoryRankingRequest.setStartTime(request.getStartTime());
        getInventoryRankingRequest.setEndTime(request.getEndTime());

        getInventoryRankingRequest.setSelectCondition(selectCondition);

        return rankingMapperService.getInventoryRanking(getInventoryRankingRequest);
    }

}
