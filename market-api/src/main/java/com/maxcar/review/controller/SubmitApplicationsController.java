package com.maxcar.review.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.stock.service.SubmitApplicationsService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/submit")
public class SubmitApplicationsController extends BaseController {

    @Autowired
    private SubmitApplicationsService submitApplicationsService;

    @RequestMapping("insertRecord")
    @OperationAnnotation(title = "出場申請")
    public InterfaceResult insertRecord(@RequestBody CarReview record, HttpServletRequest request)throws Exception{
        User user = getCurrentUser(request);
        record.setMarketId(user.getMarketId());
        InterfaceResult interfaceResult = submitApplicationsService.insertRecord(record);
        return interfaceResult;
    }
}
