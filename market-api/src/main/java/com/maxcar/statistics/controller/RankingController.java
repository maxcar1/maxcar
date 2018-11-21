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
 * describe: 总览及排行统计相关
 * create_date: lxy 2018/11/19  14:22
 **/
@RestController
public class RankingController extends BaseController {

    @Autowired
    private RankingService rankingService;

    @RequestMapping("/ranking/test")
    public InterfaceResult getPropertyContractAll() throws Exception {

       return null;
    }

}
