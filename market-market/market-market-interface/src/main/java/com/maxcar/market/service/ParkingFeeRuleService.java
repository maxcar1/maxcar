package com.maxcar.market.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.pojo.ParkingFee;
import com.maxcar.market.pojo.ParkingFeeTotal;

import java.math.BigDecimal;
import java.util.Date;

public interface ParkingFeeRuleService {
    /**
     * 通用计算停车收费接口
     * @param begin
     * @param end
     * @param marketId
     * @param carType
     * @return
     * @throws Exception
     */
    public BigDecimal figureOutParkingFee(Date begin,Date end,String marketId,Integer carType)  throws Exception;

    /**
     * 南通二次缴费逻辑
     * @param begin
     * @param payTime
     * @param marketId
     * @param carType
     * @param num 2表示第二阶段产生的费用
     * @return
     * @throws Exception
     */
    public BigDecimal figureOutParkingFee(Date begin,Date payTime,String marketId,Integer carType,int num)  throws Exception;

    InterfaceResult getTotalRule(String marketId,String userId);

    InterfaceResult addFeePeriodTime(JSONObject params) throws Exception;

    InterfaceResult deleteFeePeriodTime(String marketId,String feePeriodTimeId,String userId);

    InterfaceResult getFeePeriodTime(String marketId,String feePeriodTimeId,String userId) throws Exception;

    InterfaceResult editFeePeriodTime(JSONObject params) throws Exception;

    InterfaceResult editTotalRule(JSONObject params);

    /**
     * 查询当前市场收费规则
     * @param marketId
     * @return
     */
    ParkingFeeTotal getParkingFeeRuleByMarketId(String marketId);
}
