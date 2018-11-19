package com.maxcar.statistics.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.model.parameter.GetInventoryRankingParameter;
import com.maxcar.statistics.model.request.GetInventoryRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetYesterdayInvoiceRankingRequest;
import com.maxcar.statistics.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe: 排行统计相关
 * create_date: lxy 2018/11/19  14:22
 **/
@RestController
public class RankingController extends BaseController {

    @Autowired
    private RankingService rankingService;

    @RequestMapping("/ranking/test")
    public InterfaceResult getPropertyContractAll() throws Exception {

        GetInventoryRankingParameter reques =new GetInventoryRankingParameter();

       reques.setMarketId("007");

        GetYesterdayInvoiceRankingRequest request =new GetYesterdayInvoiceRankingRequest();
        request.setMarketId("007");

        GetInvoiceRankingByConditionRequest request1 = new GetInvoiceRankingByConditionRequest();
        request1.setStartTime("2017-11-11");
        request1.setEndTime("2018-11-15");
        request1.setMarketId("007");
        request1.setCarInvoiceType("1");

        GetInventoryRankingByConditionRequest request2 = new GetInventoryRankingByConditionRequest();

        request2.setStartTime("2017-11-11");
        request2.setEndTime("2018-11-15");
        request2.setMarketId("007");
        request2.setInventoryCycle(1);

        return getInterfaceResult("200", rankingService.getInventoryRankingByCondition(request2));
    }

}
