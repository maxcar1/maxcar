package com.maxcar.controller.pay;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.app.bean.ChargeBean;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.service.AliPayService;
import com.maxcar.tenant.app.service.ChargeOrderDetailService;
import com.maxcar.tenant.app.service.TransferCarService;
import com.maxcar.tenant.app.service.WxPayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PayController extends BaseController{

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private TransferCarService transferCarService;

    @Autowired
    private ChargeOrderDetailService chargeOrderDetailService;

    @Autowired
    private WxPayService wxPayService;

    /**
     * 支付宝充值
     *
     * @param request
     * @return
     */
    @PostMapping("/charge/alipay")
    public InterfaceResult alipayCharge(@RequestBody ChargeBean chargeBean, HttpServletRequest request) {
        try {
            int feeType = chargeBean.getFeeType();
            if (feeType > 2 || feeType < 1) {
                return getInterfaceResult("600", "不支持该类型充值");
            }

            String transferCarNo = chargeBean.getTransferCarNo();
            if (StringUtils.isBlank(transferCarNo)) {
                return getInterfaceResult("600", "请输入交易过户流水号");
            }

            TransferCar transferCar = transferCarService.getTransferCarByNo(transferCarNo);
            if (transferCar == null) {
                return getInterfaceResult("600", "查无此交易过户");
            }

            ChargeOrderDetail order = chargeOrderDetailService.getChargeOrderByTransferCarNo(transferCarNo, feeType);
            if (order != null) {
                return getInterfaceResult("600", "您已支付");
            }

            String staffId = getStaffId(request);
            return aliPayService.alipayCharge(staffId, getIpAddr(request), feeType, chargeBean.getTransferCarNo());
        } catch (Exception e) {
            logger.error("支付宝支付失败", e);
            return getInterfaceResult("600", "支付宝支付失败");
        }
    }

    /**
     * 查询支付状态
     *
     * @param request
     * @return
     */
    @GetMapping("/charge/pay/state")
    public InterfaceResult alipayCharge(String chargeOrderId, HttpServletRequest request) throws Exception{

        ChargeOrderDetail chargeOrder = chargeOrderDetailService.getChargeOrder(chargeOrderId);
        if (chargeOrder == null) {
            return getInterfaceResult("600", "未找到数据");
        }

        String staffId = getStaffId(request);
        if (!chargeOrder.getStaffId().equals(staffId)) {
            return getInterfaceResult("600", "你没有权限查询该订单");
        }

        Map<String, Integer> map = new HashMap<>(2);
        map.put("state", chargeOrder.getState());
        return getInterfaceResult("200", map);
    }


    /**
     * 微信支付
     *
     * @param request
     * @return
     */
    @PostMapping("/charge/wx")
    public InterfaceResult wxCharge(@RequestBody ChargeBean chargeBean, HttpServletRequest request) {
        try {
            int feeType = chargeBean.getFeeType();
            if (feeType > 2 || feeType < 1) {
                return getInterfaceResult("600", "不支持该类型充值");
            }

            String transferCarNo = chargeBean.getTransferCarNo();
            if (StringUtils.isBlank(transferCarNo)) {
                return getInterfaceResult("600", "请输入交易过户流水号");
            }

            TransferCar transferCar = transferCarService.getTransferCarByNo(transferCarNo);
            if (transferCar == null) {
                return getInterfaceResult("600", "查无此交易过户");
            }

            ChargeOrderDetail order = chargeOrderDetailService.getChargeOrderByTransferCarNo(transferCarNo, feeType);
            if (order != null) {
                return getInterfaceResult("600", "您已支付");
            }

            String staffId = getStaffId(request);
            return wxPayService.wxCharge(staffId, getIpAddr(request), feeType, transferCarNo);
        } catch (Exception e) {
            logger.error("微信支付失败", e);
            return getInterfaceResult("600", "微信支付失败");
        }
    }
}
