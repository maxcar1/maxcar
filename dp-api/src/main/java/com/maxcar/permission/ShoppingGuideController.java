package com.maxcar.permission;


import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.market.pojo.ShoppingGuideDetail;
import com.maxcar.market.service.ShoppingGuideDetailService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.MarketService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/market-api/api")
@RestController
public class ShoppingGuideController {

    @Autowired
    private ShoppingGuideDetailService shoppingGuideDetailService;

    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "/getShoppingGuideDetail")
    @OperationAnnotation(title = " 查询导购信息")
    public InterfaceResult getShoppingGuideDetail(@RequestBody ShoppingGuideDetail shoppingGuideDetail, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        shoppingGuideDetail.setMarketId(shoppingGuideDetail.getMarketId());
        List<ShoppingGuideDetail> list =  shoppingGuideDetailService.getShoppingGuideDetail(shoppingGuideDetail);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }

    @RequestMapping(value="/marketlist")
    @OperationAnnotation(title = " 查询市场列表")
    public InterfaceResult marketList(@RequestBody Market market, HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Market> users = marketService.searchMarket(market);
        List<Map<String,String>> list = new ArrayList<>();
        for(Market m:users){
            Map<String,String> map = new HashMap<>();
            map.put("id",m.getId());
            map.put("name",m.getName());
            list.add(map);
        }
        interfaceResult.InterfaceResult200( list);
        return interfaceResult;
    }


}
