package com.maxcar.statistics;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.model.request.GetInventoryRankingRequest;
import com.maxcar.statistics.model.request.GetInvoiceRankingByConditionRequest;
import com.maxcar.statistics.model.request.GetYesterdayInvoiceRankingRequest;
import com.maxcar.statistics.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingController extends BaseController {

    @Autowired
    private RankingService rankingService;

    @RequestMapping("/ranking/test")
    public InterfaceResult getPropertyContractAll() throws Exception {

        GetInventoryRankingRequest reques =new GetInventoryRankingRequest();

       reques.setMarketId("007");

        GetYesterdayInvoiceRankingRequest request =new GetYesterdayInvoiceRankingRequest();
        request.setMarketId("007");

        GetInvoiceRankingByConditionRequest request1 = new GetInvoiceRankingByConditionRequest();
        request1.setStartTime("2017-11-11");
        request1.setEndTime("2018-11-15");
        request1.setMarketId("007");
        request1.setCarInvoiceType("1");
        return getInterfaceResult("200", rankingService.getInvoiceRankingByCondition(request1));
    }

}
