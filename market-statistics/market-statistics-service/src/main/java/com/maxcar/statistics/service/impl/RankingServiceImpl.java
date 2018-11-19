package com.maxcar.statistics.service.impl;

import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.parameter.GetInventoryRankingParameter;
import com.maxcar.statistics.model.parameter.GetInvoiceRankingParameter;
import com.maxcar.statistics.model.request.*;
import com.maxcar.statistics.model.response.GetInvoiceRankingResponse;
import com.maxcar.statistics.model.response.GetInventoryRankingResponse;
import com.maxcar.statistics.service.RankingService;
import com.maxcar.statistics.service.impl.mapperService.RankingMapperService;
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

        GetInvoiceRankingParameter parameter = new GetInvoiceRankingParameter();

        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());

        parameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInvoiceRanking(parameter);
    }


    /**
     * param: 起止时间  车辆类型（开票类型）
     * describe: 实时查询__排名统计——获取指定条件市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/11/15  15:47
     **/
    @Override
    public List<GetInvoiceRankingResponse> getInvoiceRankingByCondition(GetInvoiceRankingByConditionRequest request) {

        GetInvoiceRankingParameter parameter = new GetInvoiceRankingParameter();

        String selectCondition = " DATE_FORMAT(i.bill_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')  " +
                " AND DATE_FORMAT(i.bill_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D')  ";

        if (StringUtil.isNotEmpty(request.getCarInvoiceType())) {
            selectCondition += " AND i.car_invoice_type = #{carInvoiceType} ";
            parameter.setCarInvoiceType(request.getCarInvoiceType().trim());
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND i.market_id = #{marketId} ";
            selectCondition += " AND i.tenant_id != '' ";
        }


        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());


        parameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInvoiceRanking(parameter);

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

        GetInventoryRankingParameter getInventoryRankingParameter = new GetInventoryRankingParameter();
        getInventoryRankingParameter.setMarketId(request.getMarketId());
        getInventoryRankingParameter.setOrderBy(request.getOrderBy());

        getInventoryRankingParameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInventoryRanking(getInventoryRankingParameter);
    }


    /**
     * param:
     * describe: 实时查询__总览——获取指定条件 市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/11/14  18:07
     **/
    @Override
    public List<GetInventoryRankingResponse> getInventoryRankingByCondition(GetInventoryRankingByConditionRequest request) {

        GetInventoryRankingParameter parameter = new GetInventoryRankingParameter();

        String selectCondition = " DATE_FORMAT(c.insert_time, '%Y-%m-%D') >= DATE_FORMAT(#{startTime}, '%Y-%m-%D')" +
                " AND   DATE_FORMAT(c.insert_time, '%Y-%m-%D') <= DATE_FORMAT(#{endTime}, '%Y-%m-%D') ";

        // 按车辆品牌查询
        if (StringUtil.isNotEmpty(request.getBrandName())) {
            selectCondition += " AND cb.brand_name = #{brandName}";
            parameter.setBrandName(request.getBrandName().trim());
        }
        // 按库存周期查询
        if (null != request.getInventoryCycle()) {

            String inventoryCycleString = getInventoryCycleString(request.getInventoryCycle());

            selectCondition += (null == inventoryCycleString ? " " : inventoryCycleString);
        }

        // 按车辆年限查询
        if (null != request.getAgeByCar()) {

            String AgeByCarString = getAgeByCarString(request.getAgeByCar());

            selectCondition += (null == AgeByCarString ? " " : AgeByCarString);
        }

        if (StringUtil.isNotEmpty(request.getMarketId())) {
            selectCondition += " AND c.market_id = #{marketId}  ";
            selectCondition += " AND c.tenant != '' ";
        }

        parameter.setMarketId(request.getMarketId());
        parameter.setOrderBy(request.getOrderBy());
        parameter.setStartTime(request.getStartTime());
        parameter.setEndTime(request.getEndTime());

        parameter.setSelectCondition(selectCondition);

        return rankingMapperService.getInventoryRanking(parameter);
    }


    /**
     * param:
     * describe: 生成计算库存周期的sql 包前不包后
     * create_date:  lxy   2018/11/16  15:26
     **/
    private String getInventoryCycleString(Integer inventoryCycle) {

        String inventoryCycleString = null;

        switch (inventoryCycle) {
            case 1:
                //10天以内
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) < 10 ";
                break;
            case 2:
                //10-20天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 10 and DATEDIFF(now(),c.register_time) < 20  ";
                break;
            case 3:
                //20-30天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 20 and DATEDIFF(now(),c.register_time) < 30  ";
                break;
            case 4:
                //30-40天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 30 and DATEDIFF(now(),c.register_time) < 40  ";
                break;
            case 5:
                //40-50天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 40 and DATEDIFF(now(),c.register_time) < 50  ";
                break;
            case 6:
                //50-60天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 50 and DATEDIFF(now(),c.register_time) < 60  ";
                break;
            case 7:
                //60-70天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 60 and DATEDIFF(now(),c.register_time) < 70  ";
                break;
            case 8:
                //70-80天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 70 and DATEDIFF(now(),c.register_time) < 80  ";
                break;
            case 9:
                //80-90天
                inventoryCycleString = " and DATEDIFF(now(),c.register_time) >= 80 and DATEDIFF(now(),c.register_time) < 90  ";
                break;
            case 10:
                //90天以上
                break;
            default:
                break;
        }

        return inventoryCycleString;
    }

    /**
     * param:
     * describe: 生成计算车辆年限的sql 包前不包后
     * create_date:  lxy   2018/11/16  16:00
     **/
    private String getAgeByCarString(Integer ageByCar) {

        String AgeByCarString = null;

        switch (ageByCar) {
            case 1:
                //一年以内
                AgeByCarString = " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 1 YEAR)  ";
                break;
            case 2:
                //一 - 三年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 1 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 3 YEAR) ";
                break;
            case 3:
                //三 - 五年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 3 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 5 YEAR) ";
                break;
            case 4:
                //五 - 八年
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 5 YEAR)" +
                        " and c.initial_licence_time > DATE_SUB(now(), INTERVAL 8 YEAR) ";
                break;
            case 5:
                //八年以上
                AgeByCarString = " and c.initial_licence_time < DATE_SUB(now(), INTERVAL 8 YEAR)";
                break;
            default:
                break;
        }

        return AgeByCarString;
    }
}
