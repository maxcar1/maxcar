package com.maxcar.customer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.stock.entity.Request.GetCarListByMarketIdAndTenantRequest;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.model.request.*;
import com.maxcar.tenant.app.service.ChargeOrderDetailService;
import com.maxcar.tenant.app.service.TransferCarService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 过户车辆信息接口
 * create_date: lxy 2018/10/15  14:53
 **/
@RequestMapping("/api/customer")
@RestController
public class TransferCarController extends BaseController {

    @Autowired
    private CarService carService;

    @Autowired
    private TransferCarService transferCarService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private ChargeOrderDetailService chargeOrderDetailService;

    /**
     * param:
     * describe: 根据市场ID和商户ID 查询 库存车 可过户 列表
     * create_date:  lxy   2018/10/15  13:56
     **/
    @RequestMapping("/getCarListByMarketIdAndTenant")
    public InterfaceResult getCarListByMarketIdAndTenant(@RequestBody @Valid GetCarListByMarketIdAndTenantRequest getCarListByMarketIdAndTenantRequest,
                                                         BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        Staff currentStaff = getCurrentStaff(request);

        if (null == currentStaff || tIsEmpty(currentStaff.getGroupId()) || tIsEmpty(currentStaff.getMarketId())) {
            return getInterfaceResult("600", "用户信息异常");
        }

        //获取登录用户信息  入参校验
        getCarListByMarketIdAndTenantRequest.setTenant(currentStaff.getGroupId());
        getCarListByMarketIdAndTenantRequest.setMarketId(currentStaff.getMarketId());

        return carService.getCarListByMarketIdAndTenant(getCarListByMarketIdAndTenantRequest);
    }

    /**
     * param:
     * describe: 查询过户车辆列表
     * create_date:  lxy   2018/10/15  15:40
     **/
    @RequestMapping("/getTransferCarList")
    public InterfaceResult getTransferCarList(@RequestBody @Valid GetTransferCarListRequest getTransferCarListRequest,
                                              BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        Staff currentStaff = getCurrentStaff(request);

        if (null == currentStaff || tIsEmpty(currentStaff.getGroupId()) || tIsEmpty(currentStaff.getMarketId())) {
            return getInterfaceResult("600", "用户信息异常");
        }
        // 获取用户信息
        getTransferCarListRequest.setMarketId(currentStaff.getMarketId());
        getTransferCarListRequest.setTenantId(currentStaff.getGroupId());

        return transferCarService.getTransferCarList(getTransferCarListRequest);
    }

    /**
     * param:
     * describe: 获取过户车辆详细信息
     * create_date:  lxy   2018/10/15  15:48
     **/
    @GetMapping("/{transferCarId}")
    public InterfaceResult getTransferCarDetailById(@PathVariable String transferCarId) {

        return transferCarService.getTransferCarDetailById(transferCarId);
    }

    /**
     * param:
     * describe: 添加车辆信息
     * create_date:  lxy   2018/10/15  15:59
     **/
    @RequestMapping("/saveTransferCar")
    public InterfaceResult saveTransferCar(@RequestBody @Valid SaveTransferCarRequest saveTransferCarRequest,
                                           BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        if (saveTransferCarRequest.getCarType() == 2) {
            Car car = carService.getStockCarByVin(saveTransferCarRequest.getVin());
            if (car != null) {
                saveTransferCarRequest.setCarType(Byte.parseByte("1"));
                saveTransferCarRequest.setCarId(car.getId());
            }
        }

        Staff currentStaff = getCurrentStaff(request);

        if (null == currentStaff || tIsEmpty(currentStaff.getGroupId()) || tIsEmpty(currentStaff.getMarketId())) {
            return getInterfaceResult("600", "用户信息异常");
        }
        // 获取用户信息
        saveTransferCarRequest.setMarketId(currentStaff.getMarketId());
        saveTransferCarRequest.setTenantId(currentStaff.getGroupId());

        return transferCarService.saveTransferCar(saveTransferCarRequest);
    }

    /**
     * param:
     * describe: 完善卖家信息
     * create_date:  lxy   2018/10/15  16:06
     **/
    @RequestMapping("/saveBuySellInfoSeller")
    public InterfaceResult saveBuySellInfoSeller(@RequestBody @Valid SaveBuySellInfoSellerRequest saveBuySellInfoSellerRequest, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        if (Magic.SELLER_TYPE_2.equals(saveBuySellInfoSellerRequest.getSellerType())) {
            // 判断 卖家为机构是 机构信息等 是否填入
            if (tIsEmpty(saveBuySellInfoSellerRequest.getSellerAgency()) || tIsEmpty(saveBuySellInfoSellerRequest.getSellerCreditCode()) ||
                    tIsEmpty(saveBuySellInfoSellerRequest.getSellerAddress()) || tIsEmpty(saveBuySellInfoSellerRequest.getSellerBusinessLicense())) {
                return getInterfaceResult("600", "请先完善卖家机构相关信息");
            }
        }

        return transferCarService.saveBuySellInfoSeller(saveBuySellInfoSellerRequest);
    }

    /**
     * param:
     * describe: 完善买家信息
     * create_date:  lxy   2018/10/15  16:19
     **/
    @RequestMapping("/saveBuySellInfoBuyer")
    public InterfaceResult saveBuySellInfoBuyer(@RequestBody @Valid SaveBuySellInfoBuyerRequest saveBuySellInfoBuyerRequest, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        if (Magic.BUYER_TYPE_2.equals(saveBuySellInfoBuyerRequest.getBuyerType())) {
            // 判断 买家为机构是 机构信息等 是否填入
            if (tIsEmpty(saveBuySellInfoBuyerRequest.getBuyerAgency()) || tIsEmpty(saveBuySellInfoBuyerRequest.getBuyerCreditCode()) ||
                    tIsEmpty(saveBuySellInfoBuyerRequest.getBuyerAddress()) || tIsEmpty(saveBuySellInfoBuyerRequest.getBuyerBusinessLicense())) {
                return getInterfaceResult("600", "请先完善买家机构相关信息");
            }
        }

        return transferCarService.saveBuySellInfoBuyer(saveBuySellInfoBuyerRequest);
    }

    /**
     * param:
     * describe: 完善交易信息
     * create_date:  lxy   2018/10/15  16:20
     **/
    @RequestMapping("/saveAddDealInfo")
    public InterfaceResult saveAddDealInfo(@RequestBody @Valid SaveAddDealInfoRequest dealInfoRequest,
                                           BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        Staff staff = getCurrentStaff(request);
        Market market = marketService.getMarketById(staff.getMarketId());
        String contractTrading = market.getContractTrading();
        if (StringUtils.isBlank(contractTrading)) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }
        JSONObject jsonObject = JSONObject.parseObject(contractTrading);

        if (dealInfoRequest.getTradingType() > 2 || dealInfoRequest.getTradingType() < 1) {
            return getInterfaceResult("600", "交易类型值无效");
        }

        String template = HttpClientUtils.sendGet(jsonObject.getString("contractTradingUrl"));
        return transferCarService.saveAddDealInfo(template, jsonObject.getJSONArray("contractTradingMap"), dealInfoRequest);
    }


    @PostMapping("/delete/{transferCarId}")
    public InterfaceResult deleteTransferCar(@PathVariable String transferCarId) {
        return transferCarService.deleteTransferCar(transferCarId);
    }

    /**
     * 返回需要填写的变量 key
     *
     * @param request
     * @return
     */
    @GetMapping("/vars")
    public InterfaceResult getVars(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = getContractInfo(request);
        if (jsonObject == null) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }

        JSONArray array = jsonObject.getJSONArray("contractTradingMap");
        if (array == null) {
            return getInterfaceResult("600", "请联系系统管理员上传正确的合同模板。");
        }
        array.add("carManager");

        return getInterfaceResult("200", array);
    }

    /**
     * 质保服务-确认交易价格
     *
     * @return
     */
    @PostMapping("/confirm/deal-price")
    public InterfaceResult confirmDealPrice(@RequestBody @Valid ConfirmDealPriceRequest confirmDealPrice,
                                            BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        JSONObject jsonObject = getContractInfo(request);
        if (jsonObject == null) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }
        String template = HttpClientUtils.sendGet(jsonObject.getString("contractTradingUrl"));
        JSONArray varArray = jsonObject.getJSONArray("contractTradingMap");
        String transferCarId = confirmDealPrice.getTransferCarId();

        return transferCarService.confirmDealPrice(transferCarId, template, varArray, confirmDealPrice.getDealPrice());
    }

    /**
     * 保存签名地址
     *
     * @param request
     * @return
     */
    @PostMapping("/save/sign-url")
    public InterfaceResult saveSignUrl(@RequestBody @Valid SaveSignUrlRequest signUrlRequest,
                                       BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        JSONObject jsonObject = getContractInfo(request);
        if (jsonObject == null) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }

        String template = HttpClientUtils.sendGet(jsonObject.getString("contractTradingUrl"));
        return transferCarService.saveSignUrl(template, jsonObject.getJSONArray("contractTradingMap"), signUrlRequest);
    }

    /**
     * 合同html
     *
     * @param request
     * @return
     */
    @GetMapping("/contract/html")
    public InterfaceResult getContractHtml(String transferCarId, HttpServletRequest request) throws Exception {

        JSONObject jsonObject = getContractInfo(request);
        if (jsonObject == null) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }

        String template = HttpClientUtils.sendGet(jsonObject.getString("contractTradingUrl"));
        Map<String, String> map = transferCarService.replaceVars(template, jsonObject.getJSONArray("contractTradingMap"), transferCarId);
        return getInterfaceResult("200", map);
    }

    /**
     * 确认交易合同
     *
     * @param request
     * @return
     */
    @PostMapping("/confirm/contract")
    public InterfaceResult confirmContract(@RequestBody @Valid ConfirmContractRequest confirmContract,
                                           BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        JSONObject jsonObject = getContractInfo(request);
        if (jsonObject == null) {
            return getInterfaceResult("600", "没有车辆交易模板");
        }

        String template = HttpClientUtils.sendGet(jsonObject.getString("contractTradingUrl"));
        return transferCarService.confirmContract(template, jsonObject.getJSONArray("contractTradingMap"), confirmContract.getTransferCarId());
    }

    /**
     * 获取支付费用
     *
     * @param transferCarNo
     * @param request
     * @return
     */
    @GetMapping("/fee")
    public InterfaceResult getPayFee(String transferCarNo, HttpServletRequest request) throws Exception {

        Map<String, Object> map = new HashMap<>(8);
        Staff staff = getCurrentStaff(request);
        Market market = marketService.selectByPrimaryKey(staff.getMarketId());

        boolean alipay = false;
        if (StringUtils.isNotBlank(market.getAlipayAppid())
                && StringUtils.isNotBlank(market.getAlipayPrivateKey())
                && StringUtils.isNotBlank(market.getAlipayPublicKey())) {
            alipay = true;
        }

        boolean wxPay = false;
        if (StringUtils.isNotBlank(market.getPayWeChatKey())
                && StringUtils.isNotBlank(market.getPayWechatAppId())
                && StringUtils.isNotBlank(market.getPayWechatMchId())) {
            wxPay = true;
        }

        if (alipay && wxPay) {
            map.put("payType", 2);
        } else if (alipay) {
            map.put("payType", 0);
        } else if (wxPay) {
            map.put("payType", 1);
        } else {
            map.put("payType", 3);
        }

        ChargeOrderDetail order = chargeOrderDetailService.getChargeOrderByTransferCarNo(transferCarNo, 2);

        map.put("qualityServiceSwitch", market.getQualityServiceSwitch());
        if (order != null) {
            map.put("enableModifyPrice", 0);
            map.put("qualityServiceSwitch", 0);
        } else {
            map.put("enableModifyPrice", 1);
        }

        map.put("carManager", market.getCarManageName() == null ? "" : market.getCarManageName());
        map.put("qualityServiceFee", market.getQualityServiceFee());
        map.put("transferFee", market.getChangeTheNamePrice());
        return getInterfaceResult("200", map);
    }

    private JSONObject getContractInfo(HttpServletRequest request) throws Exception {
        Staff staff = getCurrentStaff(request);
        Market market = marketService.getMarketById(staff.getMarketId());
        String contractTrading = market.getContractTrading();
        if (StringUtils.isNotBlank(contractTrading)) {
            return JSONObject.parseObject(contractTrading);
        }
        return null;
    }
}
