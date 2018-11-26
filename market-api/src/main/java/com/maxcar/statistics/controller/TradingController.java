package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.statistics.model.entity.InventoryInvoiceMonthEntity;
import com.maxcar.statistics.model.request.TradingRequest;
import com.maxcar.statistics.model.response.TradingResponse;
import com.maxcar.statistics.service.TradingService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TradingController extends BaseController {

    @Autowired
    private TradingService tradingService;

    /**
     * 交易总量与交易价值
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    /*@PostMapping("/trading/trading")
    public InterfaceResult getPropertyContractAll(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
        getUserMarketAndSetTime(tradingRequest, request);

        List<TradingResponse> responses = tradingService.getVolumeAndValue(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(responses);
        return interfaceResult;
    }*/
    @PostMapping("/trading/trading")
    public InterfaceResult getPropertyContractAll(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        List<InventoryInvoiceMonthEntity> responses = tradingService.getVolumeAndValue(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(responses);
        return interfaceResult;
    }

    /**
     * 增长率统计
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/increase")
    public InterfaceResult getIncreaseRate(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        List<TradingResponse> increaseRate = tradingService.getIncreaseRate(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(increaseRate);
        return interfaceResult;
    }

    /**
     * 平均交易价格
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/avgPrice")
    public InterfaceResult getAvgPrice(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        Map<String, Object> avgPrice = tradingService.getAvgPrice(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(avgPrice);
        return interfaceResult;
    }

    /**
     * 平均交易价格增长率
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/avgPriceRate")
    public InterfaceResult getAvgPriceRate(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        List<TradingResponse> avgPriceRate = tradingService.getAvgPriceRate(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(avgPriceRate);
        return interfaceResult;
    }

    /**
     * 车商交易量分布
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/tenantCount")
    public InterfaceResult getTenantCount(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        String tenantTimeEnd = tradingRequest.getTenantTimeEnd();
        Date date = DateUtils.parseDate(tenantTimeEnd, DateUtils.DATE_FORMAT_DATEONLY);
        Date dayEnd = DateUtils.getDayEnd(date);
        tenantTimeEnd = DateUtils.formatDate(dayEnd, DateUtils.DATE_FORMAT_DATEONLY);
        tradingRequest.setTimeEnd(tenantTimeEnd);

        Map<String, Double> tenantCount = tradingService.getTenantCount(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(tenantCount);
        return interfaceResult;
    }


    /**
     * 车商交易量发展趋势
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/tenantDeal")
    public InterfaceResult getTenantDeal(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
//        getUserMarketAndSetTime(tradingRequest, request);
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }

        List<TradingResponse> tenantDeal = tradingService.getTenantDeal(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(tenantDeal);
        return interfaceResult;
    }

    /**
     * 按交易价格分布的车辆
     *
     * @param tradingRequest
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/trading/carPrice")
    public InterfaceResult getCarPrice(@RequestBody TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
        getUserMarketAndSetTime(tradingRequest, request);

//        tradingService.getCarPrice(tradingRequest);

        InterfaceResult interfaceResult = new InterfaceResult();
//        interfaceResult.InterfaceResult200(tenantDeal);
        return interfaceResult;
    }


    /**
     * 设置登入人的市场id和设置查询时间
     *
     * @param tradingRequest
     * @param request
     * @throws Exception
     */
    private void getUserMarketAndSetTime(TradingRequest tradingRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        if (!currentUser.equals("001")) {
            String marketId = currentUser.getMarketId();
            tradingRequest.setUserMaketId(marketId);
        }
        //  设置查询开始时间的
        String timeStart = tradingRequest.getTimeStart();
        String timeEnd = tradingRequest.getTimeEnd();
        if (StringUtil.isNotEmpty(timeStart)) {
            timeStart += "-01";
            tradingRequest.setTimeStart(timeStart);
        }
        //  获取查询结束时间月的最后一天 并设置为当月一天最后的时间
        if (StringUtil.isNotEmpty(timeEnd)) {
            timeEnd += "-01";
            Date date = DateUtils.parseDate(timeEnd, DateUtils.DATE_FORMAT_DATEONLY);
            Date maxTime = DateUtils.getMaxTime(date);
            Date lastDayOfMonth = DateUtils.getLastDayOfMonth(maxTime);
            String s = DateUtils.getSecondStr(lastDayOfMonth);
            tradingRequest.setTimeEnd(s);
        }
    }
}
