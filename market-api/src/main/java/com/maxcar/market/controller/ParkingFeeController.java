package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.model.request.AllParkingFeeDetailRequest;
import com.maxcar.market.model.request.AllParkingFeeRequest;
import com.maxcar.market.service.ParkingFeeRuleService;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ParkingFeeController extends BaseController {

    @Autowired
    private ParkingFeeService parkingFeeService;

    @Autowired
    private ParkingFeeRuleService parkingFeeRuleService;

    @OperationAnnotation(title = "获取停车收费列表")
    @RequestMapping("/parkingFee/allParkingFee")
    public InterfaceResult allParkingFee(@RequestBody @Valid AllParkingFeeRequest allParkingFeeRequest, BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }
        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "市场不确定,无法查询");
        }
        allParkingFeeRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", parkingFeeService.allParkingFee(allParkingFeeRequest));
    }

    @OperationAnnotation(title = "查看停车收费详情列表")
    @RequestMapping("/parkingFee/allParkingFeeDetail")
    public InterfaceResult allParkingFeeDetail(@RequestBody @Valid AllParkingFeeDetailRequest allParkingFeeDetailRequest, BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }
        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "市场不确定,无法查询");
        }
        allParkingFeeDetailRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", parkingFeeService.allParkingFeeDetail(allParkingFeeDetailRequest));
    }

    /**
     * 获取当前市场的总规则配置以及当前收费时段配置
     *
     * @return
     */
    @OperationAnnotation(title = "查看当前市场的总规则配置以及收费时段配置")
    @GetMapping("/total/rule")
    public Object getTotalRule(HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.getTotalRule(user.getMarketId(), user.getUserId());
        return result;
    }

    /**
     * 修改当前市场的总规则配置以及当前收费时段配置
     *
     * @return
     */
    @OperationAnnotation(title = "修改当前市场的总规则配置以及收费时段配置")
    @PutMapping("/total/rule")
    public Object editTotalRule(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        InterfaceResult result = parkingFeeRuleService.editTotalRule(params);
        return result;
    }

    /**
     * 新增收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "新增收费时段")
    @PostMapping("/fee/time")
    public Object addFeePeriodTime(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        params.put("marketId", user.getMarketId());
        InterfaceResult result = parkingFeeRuleService.addFeePeriodTime(params);
        return result;
    }

    /**
     * 删除收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "删除收费时段")
    @DeleteMapping("/fee/time/{feePeriodTimeId}")
    public Object deleteFeePeriodTime(HttpServletRequest request
            , @PathVariable("feePeriodTimeId") String feePeriodTimeId) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.deleteFeePeriodTime(user.getMarketId(), feePeriodTimeId, user.getUserId());
        return result;
    }

    /**
     * 修改收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "修改收费时段")
    @PutMapping("/fee/time")
    public Object editFeePeriodTime(HttpServletRequest request
            , @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        params.put("marketId", user.getMarketId());
        InterfaceResult result = parkingFeeRuleService.editFeePeriodTime(params);
        return result;
    }

    /**
     * 编辑查看收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "编辑查看收费时段")
    @GetMapping("/fee/time/{feePeriodTimeId}")
    public Object getFeePeriodTime(HttpServletRequest request
            , @PathVariable("feePeriodTimeId") String feePeriodTimeId) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.getFeePeriodTime(user.getMarketId(), feePeriodTimeId, user.getUserId());
        return result;
    }

}
