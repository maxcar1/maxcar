package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarStockMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockMonthEntity record);

    int insertSelective(CarStockMonthEntity record);

    CarStockMonthEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockMonthEntity record);

    int updateByPrimaryKey(CarStockMonthEntity record);

   Integer getStockPrice(StockRequest stockRequest);

    List<CarStockMonthEntity> selectCarstockMonth(@Param("priceStart") int priceStart , @Param("priceEnd") int priceEnd);
}