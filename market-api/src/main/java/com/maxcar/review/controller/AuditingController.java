package com.maxcar.review.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auditing")
public class AuditingController extends BaseController {

    @Autowired
    private CarService carService;

    @RequestMapping("/checkPendinglist")
    @OperationAnnotation(title = "车辆出场审核待审核列表")
    public InterfaceResult getCarReview(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = carService.listReview(carParams);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }




}
