package com.maxcar.statistics.controller;


import com.maxcar.BaseController;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.model.request.GetCarInvoiceTypeInvoiceRankingRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.service.ReportByCarInvoiceTypeService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ReportController extends BaseController {

    @Autowired
    private ReportByCarInvoiceTypeService reportByCarInvoiceTypeService;


    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    @RequestMapping("/report/getInvoiceByCarInvoiceTypeReport")
    public InterfaceResult getInvoiceByCarInvoiceTypeReport(@RequestBody @Valid GetCarInvoiceTypeInvoiceRankingRequest getCarInvoiceTypeInvoiceRankingRequest,
                                                          BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "账号异常");
        }

        if (!"001".equals(user.getMarketId())) {
            getCarInvoiceTypeInvoiceRankingRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCarInvoiceTypeService.getInvoiceByCarInvoiceTypeReport(getCarInvoiceTypeInvoiceRankingRequest));
    }



    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    @RequestMapping("/report/getInvoiceByCarInvoiceTypeReportMonth")
    public InterfaceResult getInvoiceByCarInvoiceTypeReportMonth(@RequestBody @Valid GetInvoiceByCarInvoiceTypeReportRequest getInvoiceByCarInvoiceTypeReportRequest,
                                                          BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "账号异常");
        }

        if (!"001".equals(user.getMarketId())) {
            getInvoiceByCarInvoiceTypeReportRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportByCarInvoiceTypeService.getInvoiceByCarInvoiceTypeReportMonth(getInvoiceByCarInvoiceTypeReportRequest));
    }



}
