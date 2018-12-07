package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarpriceDayEntity;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;

import java.util.List;

public interface CarpriceDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarpriceDayEntity record);

    int insertSelective(CarpriceDayEntity record);

    CarpriceDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarpriceDayEntity record);

    int updateByPrimaryKey(CarpriceDayEntity record);

    List<TradingResponse> transactionLevel(TradingRequest tradingRequest);

    List<TradingResponse> stockAvgDay(TradingRequest tradingRequest);

    /*int InsertCarpriceDay();*/
}