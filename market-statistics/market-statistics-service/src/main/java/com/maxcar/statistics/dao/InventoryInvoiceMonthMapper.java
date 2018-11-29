package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.model.response.TradingResponse;

import java.util.List;
import java.util.Map;

public interface InventoryInvoiceMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryInvoiceMonthEntity record);

    int insertSelective(InventoryInvoiceMonthEntity record);

    InventoryInvoiceMonthEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryInvoiceMonthEntity record);

    int updateByPrimaryKey(InventoryInvoiceMonthEntity record);

    List<InventoryInvoiceMonthEntity> selectAllMonth(TradingRequest tradingRequest);

    List<TradingResponse> getTenantDeal(TradingRequest tradingRequest);

    Map<String,Object> countCarPriceDistribution(TradingRequest tradingRequest);

    List<StockResponse> getCountAndValue(StockRequest stockRequest);

    List<StockResponse> getParkingSaturability(StockRequest stockRequest);
}