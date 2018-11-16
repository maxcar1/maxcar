package com.maxcar.permission;


import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.MarketMap;
import com.maxcar.user.service.MarketMapService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/market-api/api")
@RestController
public class MarketMapController {
    @Autowired
    private MarketMapService marketMapService;

    @RequestMapping(value="/getMarketMapList",method = RequestMethod.POST)
    @OperationAnnotation(title = "市场地图信息")
    public InterfaceResult getMarketMapList(@RequestBody MarketMap marketMap, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        List<MarketMap> marketMapList = marketMapService.getMarketMapList(marketMap);
        interfaceResult.InterfaceResult200(marketMapList);
        return interfaceResult;
    }
}
