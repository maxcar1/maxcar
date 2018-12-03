package com.maxcar.statistics.service;

import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
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

    /**
     * 按库存天数分布的车辆
     * @param stockRequest
     */
    List<StockResponse> getStockDayCar(StockRequest stockRequest);
    /**
     * 车辆平均库存天数发展趋势
     * @param stockRequest
     */
    List<StockResponse> getStockAvgDayCar(StockRequest stockRequest);
    /**
     * 车商库存量分布
     * @param stockRequest
     */
    Map<String , Double> getTenantStock(StockRequest stockRequest);
    /**
     * 车商平均库存量发展趋势
     * @param stockRequest
     */
    List<Map<String, Object>> getTenantAvgStock(StockRequest stockRequest);
    /**
     * 按交易价格分布的车辆（台）
     * @param stockRequest
     */
    Map<String , Object> getTenantStockBranch(StockRequest stockRequest);
    /**
     * 车商平均库存量发展趋势
     * @param stockRequest
     */
    List<Map<String , Object>> getTenantAvgStockTrend(StockRequest stockRequest);

    Map<String, Object> getStockPrice(StockRequest stockRequest);

    List<Map<String , Object>> getStockPriceTrend(StockRequest stockRequest);
}
