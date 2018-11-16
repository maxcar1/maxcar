package com.maxcar.tenant.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.app.dao.AddDealInfoMapper;
import com.maxcar.tenant.app.dao.BuySellInfoMapper;
import com.maxcar.tenant.app.dao.ChargeNotifyDetailMapper;
import com.maxcar.tenant.app.dao.ChargeOrderDetailMapper;
import com.maxcar.tenant.app.dao.TransferCarMapper;
import com.maxcar.tenant.app.entity.AddDealInfo;
import com.maxcar.tenant.app.entity.BuySellInfo;
import com.maxcar.tenant.app.entity.ChargeNotifyDetail;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.enums.ChargeStateEnum;
import com.maxcar.tenant.app.service.AliPayService;
import com.maxcar.tenant.app.service.PayNotifyService;
import com.maxcar.tenant.app.service.WxPayService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Service("payNotifyService")
public class PayNotifyServiceImpl implements PayNotifyService {

    private Logger logger = LoggerFactory.getLogger(PayNotifyServiceImpl.class);

    private static final String ALIPAY_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    private static final String ALIPAY_TRADE_CLOSED = "TRADE_CLOSED";
    private static final String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS";
    private static final String ALIPAY_TRADE_FINISHED = "TRADE_FINISHED";

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private ChargeOrderDetailMapper chargeOrderDetailMapper;

    @Autowired
    private ChargeNotifyDetailMapper chargeNotifyDetailMapper;

    @Autowired
    private TransferCarMapper transferCarMapper;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private AddDealInfoMapper addDealInfoMapper;

    @Autowired
    private BuySellInfoMapper buySellInfoMapper;

    @Autowired
    private CarBaseService carBaseService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private CarService carService;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public boolean handleWxPayNotify(String xml) {
        try {
            TreeMap<String, String> params = wxPayService.parseNotify(xml);
            if (CollectionUtils.isEmpty(params)) {
                return false;
            }

            String outTradeNo = params.get("out_trade_no");
            if (StringUtils.isBlank(outTradeNo)) {
                return false;
            }

            ChargeOrderDetail chargeOrderDetail = chargeOrderDetailMapper.find(outTradeNo);
            if (chargeOrderDetail == null) {
                logger.error("查询不到订单:" + xml);
                return false;
            }

            String sign = params.get("sign");
            String payKey = WxPayServiceImpl.PAY_KEY;
            if (chargeOrderDetail.getFeeType() == 1) {
                Staff staff = staffService.selectByPrimaryId(chargeOrderDetail.getStaffId());
                Market market = marketService.getMarketById(staff.getMarketId());
                payKey = market.getPayWeChatKey();
            }
            if (StringUtils.isBlank(sign) || !sign.equals(wxPayService.buildSign(params, payKey))) {
                logger.error("签名不对:" + xml);
                return false;
            }

            insertNotifyDetail(outTradeNo, xml);

            if (ChargeStateEnum.SUCCESS.value() == chargeOrderDetail.getState() || ChargeStateEnum.FAIL.value() == chargeOrderDetail.getState()) {
                logger.warn("订单已是终止状态:" + xml);
                return false;
            }

            String resultCode = params.get("return_code");
            if ("SUCCESS".equals(resultCode)) {
                chargeOrderDetail.setState(ChargeStateEnum.SUCCESS.value());
            } else {
                chargeOrderDetail.setState(ChargeStateEnum.FAIL.value());
            }

            String orderNum = params.get("transaction_id");
            if (StringUtils.isNotBlank(orderNum)) {
                chargeOrderDetail.setSupplierBizId(orderNum);
            }

            chargeOrderDetailMapper.updateState(chargeOrderDetail);

            if (chargeOrderDetail.getFeeType() == 1) {
                // 更新状态
                transferCarMapper.updateStatus(chargeOrderDetail.getTransferCarNo(), 5);

                // invoice 新增记录
                insertInvoice(chargeOrderDetail);
            }
            return true;
        } catch (Exception e) {
            logger.error("处理微信通知失败", e);
            return false;
        }

    }


    @Override
    public boolean handleAlipayNotify(Map<String, String> data) {
        try {

            String outTradeNo = data.get("out_trade_no");
            insertNotifyDetail(outTradeNo, JSONObject.toJSONString(data));

            ChargeOrderDetail chargeOrderDetail = chargeOrderDetailMapper.find(outTradeNo);

            if (chargeOrderDetail == null) {
                logger.error("查询不到订单:" + outTradeNo);
                return false;
            }

            if (!aliPayService.checkSign(data, chargeOrderDetail.getStaffId(), chargeOrderDetail.getFeeType())) {
                logger.error("签名失败:" + outTradeNo);
                return false;
            }

            String tradeStatus = data.get("trade_status");
            if (StringUtils.isBlank(tradeStatus) || ALIPAY_TRADE_FINISHED.equals(tradeStatus) || ALIPAY_WAIT_BUYER_PAY.equals(tradeStatus)) {
                //待支付和支付完成两种状态暂时不需要处理
                return true;
            }

            if (ChargeStateEnum.SUCCESS.value() == chargeOrderDetail.getState() || ChargeStateEnum.FAIL.value() == chargeOrderDetail.getState()) {
                logger.warn("订单已是终止状态:" + outTradeNo);
                return false;
            }

            if (ALIPAY_TRADE_SUCCESS.equals(tradeStatus)) {
                chargeOrderDetail.setState(ChargeStateEnum.SUCCESS.value());
            } else if (ALIPAY_TRADE_CLOSED.equals(tradeStatus)) {
                chargeOrderDetail.setState(ChargeStateEnum.FAIL.value());
            }
            String bizId = data.get("trade_no");
            if (StringUtils.isNotBlank(bizId)) {
                chargeOrderDetail.setSupplierBizId(bizId);
            }

            chargeOrderDetailMapper.updateState(chargeOrderDetail);

            if (chargeOrderDetail.getFeeType() == 1) {
                // 更新状态
                transferCarMapper.updateStatus(chargeOrderDetail.getTransferCarNo(), 5);

                // invoice 新增记录
                insertInvoice(chargeOrderDetail);
            }

        } catch (Exception e) {
            logger.error("处理支付宝通知失败", e);
            return false;
        }
        return true;
    }

    private void insertInvoice(ChargeOrderDetail chargeOrderDetail) {
        TransferCar transferCar = transferCarMapper.getTransferCarByNo(chargeOrderDetail.getTransferCarNo());
        Staff staff = staffService.selectByPrimaryId(chargeOrderDetail.getStaffId());
        AddDealInfo addDealInfo = addDealInfoMapper.selectByPrimaryKey(transferCar.getAddDealInfo());
        BuySellInfo buySellInfo = buySellInfoMapper.selectByPrimaryKey(transferCar.getByeSellInfo());
        Invoice invoice = new Invoice();
        invoice.setBillTime(new Date());
        invoice.setCurrentNo(transferCar.getTransferCarNo());
        invoice.setId(UuidUtils.generateIdentifier());
        invoice.setMarketId(staff.getMarketId());
        invoice.setTenantId(staff.getGroupId());
        invoice.setCertificateNumber(transferCar.getRegisterCode());
        if (buySellInfo.getBuyerType() == 2) {
            invoice.setPurchacerTaxNo(buySellInfo.getBuyerCreditCode());
            invoice.setPurchacerIdCard(buySellInfo.getBuyerCreditCode());
            invoice.setPurchacerName(buySellInfo.getBuyerAgency());
            invoice.setPurchacerType(1);
        } else {
            invoice.setPurchacerTaxNo(buySellInfo.getBuyerIdcard());
            invoice.setPurchacerName(buySellInfo.getBuyerName());
            invoice.setPurchacerIdCard(buySellInfo.getBuyerIdcard());
            invoice.setPurchacerType(0);
        }
        invoice.setPurchacerAddress(buySellInfo.getBuyerIdcardAddress());
        invoice.setPurchacerMobile(buySellInfo.getBuyerPhone());

        if (buySellInfo.getSellerType() == 2) {
            invoice.setSellerTaxNo(buySellInfo.getSellerCreditCode());
            invoice.setSellerIdCard(buySellInfo.getSellerCreditCode());
            invoice.setSellerName(buySellInfo.getSellerAgency());
            invoice.setSellerType(1);
        } else {
            invoice.setSellerTaxNo(buySellInfo.getSellerIdcard());
            invoice.setSellerName(buySellInfo.getSellerName());
            invoice.setSellerIdCard(buySellInfo.getSellerIdcard());
            invoice.setSellerType(0);
        }
        invoice.setSellerAddress(buySellInfo.getSellerIdcardAddress());
        invoice.setSellerMobile(buySellInfo.getSellerPhone());

        invoice.setTransferType(1);
        invoice.setFactoryPlate(transferCar.getBrandModel());
        invoice.setInvoicePortof(1);
        invoice.setCarSources(transferCar.getCarType().intValue());
        invoice.setCarNo(transferCar.getPlateNum());
        invoice.setPrice(addDealInfo.getDealPrice());
        invoice.setTradingType(addDealInfo.getTradingType());
        invoice.setVin(transferCar.getVin());

        if (transferCar.getCarId() != null) {
            CarBase carBase = carBaseService.selectByPrimaryKey(transferCar.getCarId());
            invoice.setSeriesName(carBase.getSeriesName());
            invoice.setSeries(carBase.getSeriesCode());
            invoice.setCarId(transferCar.getCarId());
            invoice.setInitialRegistrationDate(carBase.getInitialLicenceTime());

            Car car = carService.selectByPrimaryKey(transferCar.getCarId());
            invoice.setCarStockStatus(car.getStockStatus());
        }

        invoice.setOffice(addDealInfo.getCarManager());
        Configuration configuration = configurationService.selectByPrimaryKey(transferCar.getConfigurationId());
        invoice.setCarInvoiceType(configuration.getConfigurationValue());
        invoiceService.insertSelective(invoice);
    }

    private void insertNotifyDetail(String orderId, String detail) {
        if (StringUtils.isBlank(detail)) {
            return;
        }

        ChargeNotifyDetail chargeNotifyDetail = new ChargeNotifyDetail();
        chargeNotifyDetail.setDetail(detail);
        chargeNotifyDetail.setChargeOrderId(orderId);

        chargeNotifyDetailMapper.insert(chargeNotifyDetail);
    }

}
