package com.maxcar.statistics.dao;
import com.maxcar.statistics.model.entity.InventoryInvoiceDayEntity;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.model.response.TradingResponse;

import java.util.List;
import java.util.Map;

public interface InventoryInvoiceDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryInvoiceDayEntity record);

    int insertSelective(InventoryInvoiceDayEntity record);

    InventoryInvoiceDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryInvoiceDayEntity record);

    int updateByPrimaryKey(InventoryInvoiceDayEntity record);

    InventoryInvoiceDayEntity sumMonth(TradingRequest tradingRequest);

    Map tenantCarNum(TradingRequest tradingRequest);

    List<TradingResponse> getTenantDealDay(TradingRequest tradingRequest);

    StockResponse getCountAndValue(StockRequest stockRequest);
}