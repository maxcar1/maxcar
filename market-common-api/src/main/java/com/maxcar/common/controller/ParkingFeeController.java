package com.maxcar.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DatePoor;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.pojo.ParkingFeeIntegral;
import com.maxcar.market.service.ParkingFeeIntegralService;
import com.maxcar.market.service.ParkingFeeRuleService;
import com.maxcar.market.service.ParkingFeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author sunyazhou
 * @Date 2018/11/6 14:29
 * @desc 针对南通停车收费  对外提供的接口
 */
@RestController
@RequestMapping("/api/park")
public class ParkingFeeController {



    final Logger logger = LoggerFactory.getLogger(ParkingFeeController.class);

    @Autowired
    public ParkingFeeService parkingFeeService;

    @Autowired
    public ParkingFeeRuleService parkingFeeRuleService;

    @Autowired
    public ParkingFeeIntegralService parkingFeeIntegralService;


    /**
     * 收取费用接口
     * @param cardNo
     * @return
     */
    @RequestMapping(value="/parkingFee", method = RequestMethod.GET )
    public InterfaceResult getFeeByCardNo(String cardNo) throws Exception {
        logger.info("停车卡号>>>>>>>>>>"+cardNo);
        InterfaceResult interfaceResult = new InterfaceResult();
        if (cardNo != null && !"".equals(cardNo)){
            interfaceResult = parkingFeeService.getParkingFeeDetail(cardNo);
        }else {
            interfaceResult.setCode("406");
            interfaceResult.setMsg("参数格式错误");
        }
        return interfaceResult;
    }



    @RequestMapping(value="/parkingFee/payResult")
    public InterfaceResult updateParkingFee(@RequestBody @Valid ParkingFeeIntegral parkingFeeIntegral, BindingResult result) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //参数验证
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                interfaceResult.setCode("406");
                interfaceResult.setMsg(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        if (parkingFeeIntegral.getStatus() != 1){
            interfaceResult.InterfaceResult200("支付失败");
            return interfaceResult;
        }

        interfaceResult = parkingFeeService.updateParkingFeeIntegralAndDetail(parkingFeeIntegral);
        return interfaceResult;
    }





}
