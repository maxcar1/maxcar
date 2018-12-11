package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.statistics.model.parameter.GetInventoryRankingParameter;
import com.maxcar.statistics.model.request.GetInventoryRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetYesterdayInvoiceRankingRequest;
import com.maxcar.statistics.model.request.RankingRequest;
import com.maxcar.statistics.service.RankingService;
import com.maxcar.statistics.service.StockService;
import com.maxcar.stock.service.CarService;
import com.maxcar.user.entity.User;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/ranking/test")
    public InterfaceResult getPropertyContractAll() throws Exception {
        rankingService.getYesterdayInvoiceRanking(null);
        return getInterfaceResult("200",null);
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
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = carService.nowRanking(marketId, tenantId);

        GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
        if (StringUtil.isNotEmpty(marketId)) {
            requests.setMarketId(marketId);
        }
        if (StringUtil.isNotEmpty(tenantId)) {
            requests.setTenantId(tenantId);
        }
        GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
        if (responses == null) {
            interfaceResult.InterfaceResult600("请联系管理员，配置相关参数！");
            return interfaceResult;
        }
        int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal());

        Integer count = Integer.parseInt(map.get("inMarketCarCount").toString());

        double saturability = Math.round((parkCount - count) * 100) / 100.0;
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
        Map<String , Object> map = invoiceService.nowDeal(marketId,tenantId);

        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

}
