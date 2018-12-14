package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.service.StockService;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * shenzhongzong
 * 2018-11-28
 */
@RestController
public class StockController extends BaseController {

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    /**
     * 库存总量与库存价值
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/countAndValue")
    public InterfaceResult getCountAndValue(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        List<StockResponse> countAndValue = stockService.getCountAndValue(stockRequest);

        interfaceResult.InterfaceResult200(countAndValue);
        return interfaceResult;
    }

    /**
     * 增长率统计
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/rateOfIncrease")
    public InterfaceResult getRateOfIncrease(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        List<StockResponse> countAndValue = stockService.getCountAndValue(stockRequest);

        interfaceResult.InterfaceResult200(countAndValue);
        return interfaceResult;
    }

    /**
     * 车位饱和度
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/parkingSaturability")
    public InterfaceResult getParkingSaturability(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Object> parkingSaturability = stockService.getParkingSaturability(stockRequest);

        interfaceResult.InterfaceResult200(parkingSaturability);
        return interfaceResult;
    }

    /**
     * 按库存天数分布的车辆
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/stockDayCar")
    public InterfaceResult getStockDayCar(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        List<StockResponse> stockDayCar = stockService.getStockDayCar(stockRequest);

        interfaceResult.InterfaceResult200(stockDayCar);
        return interfaceResult;
    }

    /**
     * 车辆平均库存天数发展趋势
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/stockAvgDayCar")
    public InterfaceResult getStockAvgDayCar(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        String timeStart = stockRequest.getTimeStart();
        String timeEnd = stockRequest.getTimeEnd();
        stockRequest.setTimeStart(timeStart.substring(0,7));
        stockRequest.setTimeEnd(timeEnd.substring(0,7));
        List<StockResponse> stockAvgDayCar = stockService.getStockAvgDayCar(stockRequest);

        interfaceResult.InterfaceResult200(stockAvgDayCar);
        return interfaceResult;
    }

    /**
     * 车商库存量分布
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/tenantStock")
    public InterfaceResult getTenantStock(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Double> tenantStock = stockService.getTenantStock(stockRequest);

        interfaceResult.InterfaceResult200(tenantStock);
        return interfaceResult;
    }

    /**
     * 车商平均库存量发展趋势
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/tenantAvgStock")
    public InterfaceResult getTenantAvgStock(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        String timeStart = stockRequest.getTimeStart();
        String timeEnd = stockRequest.getTimeEnd();
        stockRequest.setTimeStart(timeStart.substring(0,7));
        stockRequest.setTimeEnd(timeEnd.substring(0,7));

        List<Map<String, Object>> tenantAvgStock = stockService.getTenantAvgStock(stockRequest);

        interfaceResult.InterfaceResult200(tenantAvgStock);
        return interfaceResult;
    }

    /**
     *
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/tenantStockBranch")
    public InterfaceResult getTenantStockBranch(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Object> tenantStockBranch = stockService.getTenantStockBranch(stockRequest);

        interfaceResult.InterfaceResult200(tenantStockBranch);
        return interfaceResult;
    }

    /**
     *  车商平均库存量发展趋势
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/tenantAvgStockTrend")
    public InterfaceResult getTenantAvgStockTrend(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        List<Map<String, Object>> tenantAvgStockTrend = stockService.getTenantAvgStockTrend(stockRequest);

        interfaceResult.InterfaceResult200(tenantAvgStockTrend);
        return interfaceResult;
    }

    /**
     *  库存价值分布（台）:
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/stockPrice")
    public InterfaceResult getStockPrice(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Object> stockPrice = stockService.getStockPrice(stockRequest);

        interfaceResult.InterfaceResult200(stockPrice);
        return interfaceResult;
    }

    /**
     *  库存价值趋势
     *
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/stockPriceTrend")
    public InterfaceResult getStockPriceTrend(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

        List<Map<String, Object>> stockPriceTrend = stockService.getStockPriceTrend(stockRequest);

        interfaceResult.InterfaceResult200(stockPriceTrend);
        return interfaceResult;
    }


    private void getUserMarketId(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        String marketId = currentUser.getMarketId();
        String userId = currentUser.getUserId();
        User user = userService.selectByPrimaryKey(userId);
        if (user.getManagerFlag() == 0) {
            stockRequest.setMarketId(null);
        } else {
            stockRequest.setMarketId(marketId);
        }
    }

}
