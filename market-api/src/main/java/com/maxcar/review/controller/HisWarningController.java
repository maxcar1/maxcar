package com.maxcar.review.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.HisWarning;
import com.maxcar.stock.service.ReviewListService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/auditing")
public class HisWarningController  extends BaseController {

    @Autowired
    private ReviewListService reviewListService;

    @RequestMapping("hisWarningList")
    @OperationAnnotation(title = "出场告警列表")
    public InterfaceResult getHisWarningList(@RequestBody HisWarning hisWarning , HttpServletRequest request)throws Exception {
        User user = getCurrentUser(request);
        hisWarning.setMarketId(user.getMarketId());
        InterfaceResult interfaceResult = reviewListService.getHisWarningList(hisWarning);
        return interfaceResult;
    }
}
