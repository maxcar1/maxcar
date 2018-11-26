package com.maxcar.statistics.service;

import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TradingService {
    /**
     *交易总量与交易价值
     * @param tradingRequest
     * @return
     */
    List<InventoryInvoiceMonthEntity> getVolumeAndValue(TradingRequest tradingRequest);

    /**
     *   交易增长率
     * @param tradingRequest
     */
    List<TradingResponse> getIncreaseRate(TradingRequest tradingRequest) throws ParseException;

    Map<String , Object> getAvgPrice(TradingRequest tradingRequest);

    List<TradingResponse> getAvgPriceRate(TradingRequest tradingRequest);

    Map<String,Double> getTenantCount(TradingRequest tradingRequest);

    List<TradingResponse> getTenantDeal(TradingRequest tradingRequest);
}
