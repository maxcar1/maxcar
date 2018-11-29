package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.StockRequest;
import com.maxcar.statistics.model.response.StockResponse;
import com.maxcar.statistics.service.StockService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *   shenzhongzong
 *   2018-11-28
 */
@RestController
public class StockController extends BaseController {

    @Autowired
    private StockService stockService;

    /**
     * 库存总量与库存价值
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
     * 库存总量同比环比
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
     * @param stockRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/stock/stockDayCar")
    public InterfaceResult getStockDayCar(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        getUserMarketId(stockRequest, request);
        InterfaceResult interfaceResult = new InterfaceResult();

//        stockService.getStockDayCar(stockRequest);
//
//        interfaceResult.InterfaceResult200(parkingSaturability);
        return interfaceResult;
    }

    private void getUserMarketId(@RequestBody StockRequest stockRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        String marketId = currentUser.getMarketId();
        if(!"001".equals(marketId)){
            stockRequest.setMarketId(marketId);
        }
    }

}
