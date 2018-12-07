package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;

public interface CarStockMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockMonthEntity record);

    int insertSelective(CarStockMonthEntity record);

    CarStockMonthEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockMonthEntity record);

    int updateByPrimaryKey(CarStockMonthEntity record);

   Integer getStockPrice(StockRequest stockRequest);

  /*  int InsertCarstockMonth();*/
}