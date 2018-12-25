package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.StockAvgDayEntity;
import com.maxcar.statistics.model.request.StockRequest;

import java.util.List;
import java.util.Map;

public interface StockAvgDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockAvgDayEntity record);

    int insertSelective(StockAvgDayEntity record);

    StockAvgDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockAvgDayEntity record);

    int updateByPrimaryKey(StockAvgDayEntity record);

    List<StockAvgDayEntity> getStockDayCar(StockRequest stockRequest);

    Double sumDealNum(Map map);

    Double sumStockNum(Map map);
}