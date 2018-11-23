package com.maxcar.statistics.controller;


import com.maxcar.BaseController;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.statistics.model.request.GroupCartypeDayRequest;
import com.maxcar.statistics.model.request.GetInvoiceByCarInvoiceTypeReportRequest;
import com.maxcar.statistics.model.request.GroupCartypeMonthRequest;
import com.maxcar.statistics.service.ReportCartypeService;
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
    private ReportCartypeService reportCartypeService;


    /**
     * param:
     * describe: 车辆类型统计 根据车辆类型划分交易量与占比 --> 交易量 交易价值
     * create_date:  lxy   2018/11/19  10:18
     **/
    @RequestMapping("/report/groupCartypeDay")
    public InterfaceResult groupCartypeDay(@RequestBody @Valid GroupCartypeDayRequest groupCartypeDayRequest,
                                           BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCartypeDayRequest.setMarketId(user.getMarketId());
        }


        return getInterfaceResult("200", reportCartypeService.groupCartypeDay(groupCartypeDayRequest));
    }


    /**
     * param:
     * describe:  某一类型 交易量与交易价值 按月分组
     * create_date:  lxy   2018/11/19  13:39
     **/
    @RequestMapping("/report/groupCartypeMonth")
    public InterfaceResult groupCartypeMonth(@RequestBody @Valid GroupCartypeMonthRequest groupCartypeMonthRequest,
                                             BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        if (!isManagerFlag(request)) {

            if (null == user.getMarketId()) {
                return getInterfaceResult("600", "账号异常");
            }

            groupCartypeMonthRequest.setMarketId(user.getMarketId());
        }

        return getInterfaceResult("200", reportCartypeService.groupCartypeMonth(groupCartypeMonthRequest));
    }


}
