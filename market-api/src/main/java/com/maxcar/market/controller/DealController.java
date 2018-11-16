package com.maxcar.market.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.service.DealService;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.TenantInvoiceService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/api/deal")
public class DealController extends BaseController {

    @Autowired
    DealService dealService;

    @Autowired
    UserTenantService userTenantService;

    @Autowired
    CarBaseService carBaseService;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/list")
    //@OperationAnnotation(title = "查询交易记录")
    public InterfaceResult listDeal(@RequestBody DealRequest deal, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            deal.setMarketId(user.getMarketId());
        }
        Map<String, Object> map = dealService.selectDeal(deal);
        interfaceResult.InterfaceResult200(map);
        interfaceResult.setMsg("查询交易记录成功");

        return interfaceResult;
    }

    /**
     * 交易记录  导出
     *
     * @param deal
     * @return
     */
    @PostMapping("/listExport")
    //@OperationAnnotation(title = "查询交易记录  导出表格")
    public InterfaceResult listDealExport(@RequestBody DealRequest deal, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        if (user != null) {
            deal.setMarketId(user.getMarketId());
        }

        List<DealResponse> list = dealService.selectDealExport(deal);

        List<Map> deals = new ArrayList<>();
        HashMap<String, Object> dealMap = null;

        for (DealResponse d : list) {
            dealMap = new LinkedHashMap();
            //  开票时间        1
            String billTime = d.getBillTime();
            if (StringUtil.isNotEmpty(billTime)) {
                dealMap.put("billTime", billTime);
            } else {
                dealMap.put("billTime", "");
            }

            //  vin         2
            String vin = d.getIvin();
            if (StringUtil.isNotEmpty(vin)) {
                dealMap.put("vin", vin);
            } else {
                dealMap.put("vin", "");
            }
            //  品牌车系     3
            String seriesName = d.getSeriesName();
            if (StringUtil.isNotEmpty(seriesName)) {
                dealMap.put("seriesName", seriesName);
            } else {
                dealMap.put("seriesName", "");
            }
            //  商户      4
            String tenantName = d.getTenantName();
            if (StringUtil.isNotEmpty(tenantName)) {
                dealMap.put("tenantName", tenantName);
            } else {
                dealMap.put("tenantName", "");
            }
            //  负责人         5
            String contactName = d.getContactName();
            if (StringUtil.isNotEmpty(contactName)) {
                dealMap.put("contactName", contactName);
            } else {
                dealMap.put("contactName", "");
            }
            //  入库时间            6
            String registerTime = d.getRegisterTime();
            if (StringUtil.isNotEmpty(registerTime)) {
                dealMap.put("registerTime", registerTime);
            } else {
                dealMap.put("registerTime", "");
            }
            //  库存时间                7
            String registerDay = d.getRegisterDay();
            if (StringUtil.isNotEmpty(registerDay)) {
                dealMap.put("registerDay", registerDay);
            } else {
                dealMap.put("registerDay", "");
            }
            //  售价（万元）               8
            Double marketPrice = d.getMarketPrice();
            if (marketPrice != null) {
                dealMap.put("marketPrice", marketPrice);
            } else {
                dealMap.put("marketPrice", "");
            }
            //  估价（万元）              9
            Double evaluatePrice = d.getEvaluatePrice();
            if (evaluatePrice != null) {
                dealMap.put("evaluatePrice", evaluatePrice);
            } else {
                dealMap.put("evaluatePrice", "");
            }
            //  交易价格（万元）              10
            Double price = d.getPrice();
            if (price != null) {
                BigDecimal bd = new BigDecimal(price / 10000);
                double v = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
                dealMap.put("price", v);
            } else {
                dealMap.put("price", "");
            }
            //  车辆来源  11
            String carStatus = d.getCarStatus();
            if ("1".equals(carStatus)) {
                dealMap.put("status", "商品车");
            } else if ("2".equals(carStatus)) {
                dealMap.put("status", "挂靠车");
            } else if ("3".equals(carStatus)) {
                dealMap.put("status", "代办");
            } else if ("4".equals(carStatus)) {
                dealMap.put("status", "散户");
            } else {
                dealMap.put("status", "");
            }
            //  交易类型    12
            Integer tradingType = d.getTradingType();
            if (tradingType != null) {
                if (1 == tradingType) {
                    dealMap.put("tradingType", "本地");
                } else if (2 == tradingType) {
                    dealMap.put("tradingType", "外迁");
                } else {
                    dealMap.put("tradingType", "");
                }
            }
            //  过户类型
            String transferType = d.getTransferType();
            if (transferType != null) {
                if ("0".equals(transferType)) {
                    dealMap.put("transferType", "买入过户");
                } else if ("1".equals(transferType)) {
                    dealMap.put("transferType", "卖出过户");
                }
                deals.add(dealMap);
            }
        }

//        for (DealResponse backDeal : list) {
//            double price = backDeal.getPrice();
//            backDeal.setPrice(price / 10000);
//            double evaluatePrice = backDeal.getEvaluatePrice();
////            backDeal.setEvaluatePrice(evaluatePrice / 10000);
//            dealMap = new LinkedHashMap<>();
//
//            String id = backDeal.getId();
////                if(id != null && (!id.equals(""))){
////                    dealMap.put("carNo", backDeal.getCarNo());  // 1
////                }else {
////                    dealMap.put("carNo", "");
////                }
//            dealMap.put("vin", backDeal.getIvin());   //  2
//
//
//            //  挂靠车商
//            String tenantId = backDeal.getTenantId();
//            if (tenantId != null && (!tenantId.equals(""))) {
//                UserTenant userTenant = userTenantService.selectByPrimaryKey(tenantId);
//                if (userTenant != null) {
//                    String tenantName = userTenant.getTenantName();
//                    dealMap.put("tenantName", tenantName);  // 3
//                } else {
//                    dealMap.put("tenantName", "");
//                }
//            } else {
//                dealMap.put("tenantName", "");
//            }
//            if (id != null && (!id.equals(""))) {
//                String isNewCar = backDeal.getIsNewCar();
//                if (isNewCar != null && (!"".equals(isNewCar))) {
//                    switch (isNewCar) {
//                        case "0":
//                            dealMap.put("isNewCar", "新车");  //  4
//                            break;
//                        case "1":
//                            dealMap.put("isNewCar", "二手车");  //  4
//                            break;
//                    }
//                } else {
//                    dealMap.put("isNewCar", "");
//                }
//
//            } else {
//                dealMap.put("isNewCar", "");
//            }
//
//            String stockStatus = backDeal.getStockStatus();
//            if (StringUtil.isNotEmpty(stockStatus)) {
//                if("-1".equals(stockStatus)){
//                    dealMap.put("stockStatus", "");  //  5
//                }else if("1".equals(stockStatus)){
//                    dealMap.put("stockStatus", "");  //  5
//                }else if("2".equals(stockStatus)){
//                    dealMap.put("stockStatus", "");  //  5
//                }else if("3".equals(stockStatus)){
//                    dealMap.put("stockStatus", "");  //  5
//                }else if("4".equals(stockStatus)){
//                    dealMap.put("stockStatus", "售出未出场");  //  5
//                }else if("5".equals(stockStatus)){
//                    dealMap.put("stockStatus", "售出已出场");  //  5
//                }
//            } else {
//                dealMap.put("stockStatus", "");  //  5
//            }
//            dealMap.put("billTime", backDeal.getBillTime());  // 6
//
//            if (id != null && (!id.equals(""))) {
//                String carStatus = backDeal.getCarStatus();
//                if ("1".equals(carStatus)) {
//                    dealMap.put("carStatus", "库存车");  // 7
//                } else if ("2".equals(carStatus)) {
//                    dealMap.put("carStatus", "挂靠车");  // 7
//                } else if ("3".equals(carStatus)) {
//                    dealMap.put("carStatus", "非车商（其他）");  // 7
//                }
//                dealMap.put("registerDay", backDeal.getRegisterDay());  // 8
//                dealMap.put("registerTime", backDeal.getRegisterTime());  //  9
//            } else {
//                dealMap.put("carStatus", "非车商（其他）");
//                dealMap.put("registerDay", "");
//                dealMap.put("registerTime", "");
//            }
//
//            if (id != null && (!id.equals(""))) {
//                CarBase carBase = carBaseService.selectByPrimaryKey(id);
//                if (carBase != null) {
//                    String brandName = carBase.getBrandName();
//                    String seriesName = carBase.getSeriesName();
//                    if (StringUtil.isNotEmpty(brandName)) {
//                        dealMap.put("series", brandName + "-" + seriesName); //10
//                    } else {
//                        dealMap.put("series", ""); //10
//                    }
//                    String modelName = carBase.getModelName();
//                    if (StringUtil.isNotEmpty(modelName)) {
//                        dealMap.put("seriesName", modelName);  //11
//                    } else {
//                        dealMap.put("seriesName", "");  //11
//                    }
//                } else {
//                    dealMap.put("series", "");
//                    dealMap.put("seriesName", "");
//                }
//            } else {
//                dealMap.put("series", "");
//                dealMap.put("seriesName", "");
//            }
//
//            if (id != null && (!id.equals(""))) {
//                dealMap.put("initialLicenceTime", backDeal.getInitialLicenceTime());  // 12
//                dealMap.put("mileage", backDeal.getMileage());   //  13
//                dealMap.put("evaluatePrice", backDeal.getEvaluatePrice()); //  14
//            } else {
//                dealMap.put("initialLicenceTime", "");
//                dealMap.put("mileage", "");
//                dealMap.put("evaluatePrice", "");
//            }
//            dealMap.put("price", backDeal.getPrice());  //15
//            deals.add(dealMap);
//        }

        interfaceResult.InterfaceResult200(deals);
        interfaceResult.setMsg("导出交易记录成功");

        return interfaceResult;

    }


    @GetMapping("/dealDetails/{vin}/{invoiceId}")
    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult dealDetails(@PathVariable String vin, @PathVariable String invoiceId) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Object> map = new HashMap<>();
        map.put("invoiceId", invoiceId);
        map.put("vin", vin);
//        map.put("curPage", curPage);
//        map.put("pageSize", pageSize);
//        map.put("tenantId", tenantId);

        List<DealResponse> list = dealService.selectByVin(map);
        int count = dealService.countByVin(invoiceId);
        interfaceResult.setTotal(count);
        interfaceResult.InterfaceResult200(list);
        interfaceResult.setMsg(" 查询交易记录成功");

        return interfaceResult;
    }

    /**
     * 查询开票记录
     *
     * @param invoice 车架号  商户  开票时间开始到结束  开票号
     * @return
     */
    @PostMapping("/selectInvoice")
//    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult selectInvoice(@RequestBody SelectInvoice invoice, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            invoice.setMarketId(user.getMarketId());
        }
        PageInfo pageInfo = dealService.selectInvoice(invoice);
//        int total = dealService.countInvoice(invoice);
//        interfaceResult.setTotal(total);
        interfaceResult.InterfaceResult200(pageInfo);
        interfaceResult.setMsg("开票查询成功");

        return interfaceResult;
    }

    /**
     * 查询开票记录  导出
     *
     * @param invoice 车架号  商户  开票时间开始到结束  开票号
     * @return
     */
    @PostMapping("/selectInvoiceExport")
//    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult selectInvoiceExport(@RequestBody SelectInvoice invoice, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            invoice.setMarketId(user.getMarketId());
        }
        List<Map> list = dealService.selectInvoiceExport(invoice);
        interfaceResult.InterfaceResult200(list);
        interfaceResult.setMsg("开票查询成功");

        return interfaceResult;
    }


    @GetMapping("/invoiceIdDetails/{invoiceId}")
    @OperationAnnotation(title = "根据开票id查询车辆交易记录")
    public InterfaceResult dealDetails(@PathVariable String invoiceId) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        Invoice invoice = invoiceService.selectByPrimaryKey(invoiceId);

        interfaceResult.InterfaceResult200(invoice);

        return interfaceResult;
    }

    @GetMapping("/getContactName/{tenantId}")
    @OperationAnnotation(title = "获取开票负责人列表")
    public InterfaceResult getContactName(@PathVariable(value = "tenantId") String tenantId, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);

        if("0".equals(tenantId)){
            tenantId = null;
        }

        List<String> list = userTenantService.getContactName(user.getMarketId(),tenantId);

        interfaceResult.InterfaceResult200(list);

        return interfaceResult;
    }

}
