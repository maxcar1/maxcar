package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.service.TradingService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public class DealController extends BaseController {

    @Autowired
    private TradingService tradingService;

    @GetMapping("/trading/trading")
    public InterfaceResult getPropertyContractAll(HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        String marketId = null;
        if(currentUser != null){
            marketId = currentUser.getMarketId();
        }
        tradingService.getVolumeAndValue();

        return null;
    }
}
