package com.maxcar.statistics.service;

import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.response.StockResponse;

import java.util.List;
import java.util.Map;

public interface StockService {
    /**
     *   库存总量与库存价值  环比和同比
     * @param stockRequest
     */
    List<StockResponse> getCountAndValue(StockRequest stockRequest);

    /**
     * 车位饱和度
     * @param stockRequest
     */
    Map<String, Object> getParkingSaturability(StockRequest stockRequest);
}
