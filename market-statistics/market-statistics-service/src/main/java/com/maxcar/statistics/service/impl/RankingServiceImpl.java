package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.service.RankingService;
import com.maxcar.statistics.service.impl.mapperService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe: 排名统计相关
 * create_date: lxy 2018/11/16  11:54
 **/
@Service("rankingService")
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapperService rankingMapperService;


    @Autowired
    private CartypeMapperService cartypeMapperService;

    @Autowired
    private CarbrandMapperService carbrandMapperService;

    @Autowired
    private CaryearMapperService caryearMapperService;

    @Autowired
    private CarstocktimeMapperService carstocktimeMapperService;

    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/14  18:03
     **/
    @Override
    public List<GetInvoiceRankingResponse> getYesterdayInvoiceRanking(GetYesterdayInvoiceRankingRequest request) {

        try {

            cartypeMapperService.InsertCartype();
            carbrandMapperService.InserteCarbrand();
            caryearMapperService.InsertCaryear();
            carstocktimeMapperService.InsertCarstocktime();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        /*
        String selectCondition = " DATE_FORMAT(i.bill_time, '%Y-%m-%D') = DATE_FORMAT(CURDATE() - 1, '%Y-%m-%D')  ";

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND i.market_id = #{marketId} ";
            selectCondition += " AND i.tenant_id != '' ";
        }

        GetInvoiceRankingParameter parameter = new GetInvoiceRankingParameter();

        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());

        parameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInvoiceRanking(parameter);*/
        return null;
    }


    /**
     * param:
     * describe: 实时查询__总览——获取昨日市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    @Override
    public List<GetInventoryRankingResponse> getYesterdayInventoryRanking(GetYesterdayInventoryRankingRequest request) {
       /* String selectCondition = " DATE_FORMAT(c.insert_time, '%Y-%m-%D') < DATE_FORMAT(CURDATE(), '%Y-%m-%D')  ";
        selectCondition += " AND  c.stock_status in ('1','2','3') ";

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND c.market_id = #{marketId}  ";
            selectCondition += " AND c.tenant != '' ";
        }

        GetInventoryRankingParameter getInventoryRankingParameter = new GetInventoryRankingParameter();
        getInventoryRankingParameter.setMarketId(request.getMarketId());
        getInventoryRankingParameter.setOrderBy(request.getOrderBy());

        getInventoryRankingParameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInventoryRanking(getInventoryRankingParameter);*/
        return null;
    }


    /**
     * param: 起止时间
     * describe: 排名统计——获取指定条件市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/15  15:47
     **/
    @Override
    public List<GetInvoiceRankingResponse> getInvoiceRankingByCondition(GetInvoiceRankingByConditionRequest request) {

        if ("brandName".equals(request.getType())) {

            return carbrandMapperService.groupCarbrandInvoiceDayRanking(request);

        } else if ("carType".equals(request.getType())) {

            return cartypeMapperService.groupCartypeDayRanking(request);

        } else if ("carYear".equals(request.getType())) {

            return caryearMapperService.groupCaryearInvoiceDayRanking(request);

        } else if ("carStocktime".equals(request.getType())) {

            return carstocktimeMapperService.groupCarstocktimeInvoiceDayRanking(request);
        }

        return null;

       /* GetInvoiceRankingParameter parameter = new GetInvoiceRankingParameter();

        StringBuffer stringBuffer = new StringBuffer(128);

        stringBuffer.append(" DATE_FORMAT(i.bill_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')  ");
        stringBuffer.append(" AND DATE_FORMAT(i.bill_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            stringBuffer.append(" AND i.market_id = #{marketId} ");
            stringBuffer.append(" AND i.tenant_id != '' ");
        }

        // 按车辆类型查询
        if (StringUtil.isNotEmpty(request.getCarInvoiceType())) {
            stringBuffer.append(" AND i.car_invoice_type = #{carInvoiceType} ");
            parameter.setCarInvoiceType(request.getCarInvoiceType().trim());
        }

        // 按车辆品牌查询
        if (StringUtil.isNotEmpty(request.getBrandName())) {
            stringBuffer.append(" AND cb.brand_name = #{brandName} ");
            parameter.setCarInvoiceType(request.getBrandName().trim());
        }

        // 按库存周期查询
        if (null != request.getInventoryCycle()) {

            String inventoryCycleString = BaseMapperService.getInventoryCycleString(request.getInventoryCycle());

            stringBuffer.append(null == inventoryCycleString ? " " : inventoryCycleString);
        }

        // 按车辆年限查询
        if (null != request.getAgeByCar()) {

            String AgeByCarString = BaseMapperService.getAgeByCarString(request.getAgeByCar());

            stringBuffer.append(null == AgeByCarString ? " " : AgeByCarString);
        }


        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        parameter.setSelectCondition(stringBuffer.toString());

        return rankingMapperService.getInvoiceRanking(parameter);*/
    }


    /**
     * param:
     * describe:获取指定条件 市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    @Override
    public List<GetInventoryRankingResponse> getInventoryRankingByCondition(GetInventoryRankingByConditionRequest request) {
        if ("brandName".equals(request.getType())) {

            return carbrandMapperService.groupCarbrandInventoryDayRanking(request);

        } else if ("carYear".equals(request.getType())) {

            return caryearMapperService.groupCaryearInventoryDayRaning(request);

        } else if ("carStocktime".equals(request.getType())) {

            return carstocktimeMapperService.groupCarstocktimeInventoryDayRanking(request);
        }

        return null;

     /*   GetInventoryRankingParameter parameter = new GetInventoryRankingParameter();

        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(" DATE_FORMAT(c.insert_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D') ");
        stringBuffer.append(" AND   DATE_FORMAT(c.insert_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ");
        stringBuffer.append(" AND  c.stock_status in ('1','2','3') ");


        // 按车辆品牌查询
        if (StringUtil.isNotEmpty(request.getBrandName())) {
            stringBuffer.append(" AND cb.brand_name = #{brandName}");
            parameter.setBrandName(request.getBrandName().trim());
        }

        // 按库存周期查询
        if (null != request.getInventoryCycle()) {

            String inventoryCycleString = BaseMapperService.getInventoryCycleString(request.getInventoryCycle());

            stringBuffer.append(null == inventoryCycleString ? " " : inventoryCycleString);
        }

        // 按车辆年限查询
        if (null != request.getAgeByCar()) {

            String AgeByCarString = BaseMapperService.getAgeByCarString(request.getAgeByCar());

            stringBuffer.append(null == AgeByCarString ? " " : AgeByCarString);
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            stringBuffer.append(" AND c.market_id = #{marketId}  ");
            stringBuffer.append(" AND c.tenant != '' ");
        }

        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        parameter.setSelectCondition(stringBuffer.toString());

        return rankingMapperService.getInventoryRanking(parameter);*/
    }


}
