package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarpriceDayEntity;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarpriceDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarpriceDayEntity record);

    int insertSelective(CarpriceDayEntity record);

    CarpriceDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarpriceDayEntity record);

    int updateByPrimaryKey(CarpriceDayEntity record);

    List<TradingResponse> transactionLevel(TradingRequest tradingRequest);

    List<TradingResponse> stockAvgDay(TradingRequest tradingRequest);

    List<CarpriceDayEntity> selectCarpriceDay(@Param("priceStart") int priceStart , @Param("priceEnd") int priceEnd);
}