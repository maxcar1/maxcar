package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;

import java.util.List;
import java.util.Map;

public interface TradingService {
    /**
     *交易总量与交易价值
     * @param tradingRequest
     * @return
     */
    List getVolumeAndValue(TradingRequest tradingRequest);

    /**
     * 增长率统计
     * @param tradingRequest
     * @return
     */
    List getIncreaseRate(TradingRequest tradingRequest);

    /**
     *   平均交易价格
     * @param tradingRequest
     * @return
     */
    Map<String, Object> getAvgPrice(TradingRequest tradingRequest);

    /**
     *     平均交易价格增长率
     * @param tradingRequest
     */
    List<TradingResponse> getAvgPriceRate(TradingRequest tradingRequest) throws Exception;
}
