package com.maxcar.market.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.pojo.ShoppingGuideDetail;
import com.maxcar.market.service.ShoppingGuideDetailService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController extends BaseController  {

    @Autowired
    private ShoppingGuideDetailService shoppingGuideDetailService;

    @RequestMapping(value = "/getShoppingGuideDetail")
    @OperationAnnotation(title = " 查询导购信息")
    public InterfaceResult getShoppingGuideDetail(@RequestBody ShoppingGuideDetail shoppingGuideDetail, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        shoppingGuideDetail.setMarketId(user.getMarketId());
        List<ShoppingGuideDetail> list =  shoppingGuideDetailService.getShoppingGuideDetail(shoppingGuideDetail);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }

    @RequestMapping(value = "/addOrUpdateShoppingGuideDetail")
    @OperationAnnotation(title = "新增或修改导购信息")
    public InterfaceResult addOrUpdateShoppingGuideDetail(@RequestBody ShoppingGuideDetail shoppingGuideDetail, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        shoppingGuideDetail.setMarketId(user.getMarketId());
        boolean b = shoppingGuideDetailService.addOrUpdateShoppingGuideDetail(shoppingGuideDetail);
        interfaceResult.InterfaceResult200(b);
        return interfaceResult;
    }
}
