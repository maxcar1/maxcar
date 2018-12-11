package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.statistics.model.request.RankingRequest;
import com.maxcar.statistics.service.RankingService;
import com.maxcar.stock.service.CarService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * describe: 总览及排行统计相关
 * create_date: lxy 2018/11/19  14:22
 **/
@RestController
public class RankingController extends BaseController {

    @Autowired
    private RankingService rankingService;

    @Autowired
    private CarService carService;

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private InvoiceService invoiceService;

    /**
     * param:
     * describe: 排名统计——获取指定条件市场排行  商户排行 --> 交易 condition
     * create_date:  lxy   2018/12/11  11:52
     **/
    @RequestMapping("/ranking/getInvoiceRankingByCondition")
    public InterfaceResult getInvoiceRankingByCondition(@RequestBody @Valid GetInvoiceRankingByConditionRequest getInvoiceRankingByConditionRequest,
                                                        BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        if (null == user) {
            return getInterfaceResult("200","用户过期");
        }

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            getInvoiceRankingByConditionRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", rankingService.getInvoiceRankingByCondition(getInvoiceRankingByConditionRequest));
    }


    /**
     * param:
     * describe: 排名统计——获取指定条件市场排行  商户排行 --> 库存 condition
     * create_date:  lxy   2018/12/11  11:52
     **/
    @RequestMapping("/ranking/getInventoryRankingByCondition")
    public InterfaceResult getInventoryRankingByCondition(@RequestBody @Valid GetInventoryRankingByConditionRequest getInventoryRankingByConditionRequest,
                                                        BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        if (null == user) {
            return getInterfaceResult("200","用户过期");
        }

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            getInventoryRankingByConditionRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", rankingService.getInventoryRankingByCondition(getInventoryRankingByConditionRequest));
    }


    @Autowired
    private MarketService marketService;

    @RequestMapping("/ranking/test")
    public InterfaceResult getPropertyContractAll() throws Exception {
        rankingService.getYesterdayInvoiceRanking(null);
        return getInterfaceResult("200", null);
    }

    @PostMapping("/ranking/stock")
    public InterfaceResult getNowStock(@RequestBody RankingRequest rankingRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        Short managerFlag = currentUser.getManagerFlag();
        String marketId = rankingRequest.getMarketId();
        String tenantId = rankingRequest.getTenantId();

        if (managerFlag != 0) {
            marketId = currentUser.getMarketId();
        }
        if(managerFlag == 0){
            marketId = null;
        }
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = carService.nowRanking(marketId, tenantId);
        if(managerFlag == 0 && !StringUtil.isNotEmpty(marketId) && !StringUtil.isNotEmpty(tenantId)){
            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            List<Market> marketList = marketService.selectAll();
            int parkCount = 0;
           for(Market market : marketList){
               String name = market.getName();
               String marketNo = market.getMarketNo();
               if(marketNo.equals("001")){
                   continue;
               }
               requests.setMarketId(marketNo);
               GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
               if (responses == null ) {
                   interfaceResult.InterfaceResult600(name+"，没有配置车位浮动数！");
                   return interfaceResult;
               }
            parkCount += (responses.getCarTotal() == null ? 0 : responses.getCarTotal());
           }

            Integer count = Integer.parseInt(map.get("inMarketCarCount").toString());

            double saturability = Math.round(count / parkCount * 100) / 100.0;
            map.put("saturability", saturability);

            interfaceResult.InterfaceResult200(map);
        } else {
            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
            if (StringUtil.isNotEmpty(marketId)) {
                requests.setMarketId(marketId);
            }
            if (StringUtil.isNotEmpty(tenantId)) {
                requests.setTenantId(tenantId);
            }
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            if (responses == null ) {
                interfaceResult.InterfaceResult600("请联系管理员，配置相关参数！");
                return interfaceResult;
            }
            int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal());

            Integer count = Integer.parseInt(map.get("inMarketCarCount").toString());

            double saturability = Math.round(count / parkCount * 100) / 100.0;
            map.put("saturability", saturability);

        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    @PostMapping("/ranking/deal")
    public InterfaceResult getNowDeal(@RequestBody RankingRequest rankingRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        Short managerFlag = currentUser.getManagerFlag();
        String marketId = rankingRequest.getMarketId();
        String tenantId = rankingRequest.getTenantId();
        if (managerFlag != 0) {
            marketId = currentUser.getMarketId();
        }
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = invoiceService.nowDeal(marketId, tenantId);

        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

}
