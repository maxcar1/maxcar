package com.maxcar.market.controller;

import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.*;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.model.response.InvoicePerson;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.TradeInformation;
import com.maxcar.market.service.InvoicePurchaseService;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.market.service.TransactionService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.service.TransferCarService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by chiyanlong on 2018/8/24.
 */
@RestController
public class InvoiceController extends BaseController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private InvoicePurchaseService invoicePurchaseService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CarBaseService carBaseService;

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private TransferCarService transferCarService;

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    /**
     * currentTime 默认传当日，当月，当年
     * status :1:按日查询，2：按月，3：按年
     * 查询统计商户发票 POST请求
     *
     * @return
     */
    @RequestMapping(value = "/invoice/getInvoiceCountList")
    @OperationAnnotation(title = "查询统计商户发票")
    public InterfaceResult getInvoiceCountList(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        PageInfo pageInfo = invoiceService.selectInvoiceCountList(invoice);
        Invoice totalCounts = invoiceService.selectInvoiceTotalCount();
        Map map = new HashMap();
        map.put("InvoiceList", pageInfo);
        map.put("totalCounts", totalCounts);
        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    /**
     * tenantId 商户id
     * currentTime 查询时间
     * status(Integer类型) :1:按日查询，2：按月，3：按年
     * 查询商户详情 POST请求
     *
     * @return
     */
    @RequestMapping(value = "/invoice/getInvoiceDetail")
    @OperationAnnotation(title = "查询商户详情")
    public InterfaceResult getInvoiceDetail(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        PageInfo pageInfo = invoiceService.selectInvoiceDetail(invoice);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    /**
     * @param invoice
     * @return 开票管理列表
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getInvoiceList")
    @OperationAnnotation(title = "开票管理列表")
    public InterfaceResult getInvoiceList(@RequestBody Invoice invoice, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User currentUser = getCurrentUser(request);
        String userId = currentUser.getUserId();
        if (null != currentUser.getMarketId() && currentUser.getMarketId() != "") {
            invoice.setMarketId(currentUser.getMarketId());
        }
        User user = userService.selectByPrimaryKey(userId);
        if(user.getManagerFlag() == 0){
            invoice.setMarketId(null);
        }
        invoice.setUserId(currentUser.getUserId());
        PageInfo pageInfo = invoiceService.getInvoiceList(invoice);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    /**
     * @param id 发票id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getInvoiceDetailById/{id}")
    @OperationAnnotation(title = "查询发票详情")
    public InterfaceResult getInvoiceDetailById(@PathVariable(value = "id") String id, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Invoice invoice = invoiceService.selectInvoiceDetailById(id);//开票信息
        Market market = new Market();
        String tenantName = "";
        if (invoice != null) {
            String userId = invoice.getUserId();
            User user = userService.selectByPrimaryKey(userId);
            if (user != null) {
                Staff staff = staffService.selectByPrimaryId(user.getStaffId());
                if (staff != null) {
                    String staffName = staff.getStaffName();
                    invoice.setOperatorName(staffName);
                }
            }
//            String carInvoiceType = invoice.getCarInvoiceType();
//            String type = getCarInvoiceType(carInvoiceType);
//            invoice.setCarInvoiceType(type);
            market = marketService.selectByPrimaryKey(invoice.getMarketId());//市场信息
            tenantName = userTenantService.selectByTenanId(invoice.getTenantId());
            invoice.setTenantName(tenantName);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("invoice", invoice);
        map.put("market", market);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }


    /**
     * 生成发票二维码
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getInvoiceDetailBuildQRCode/{id}")
    @OperationAnnotation(title = "生成发票二维码")
    public void getInvoiceDetailBuildQRCode(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int width = 312;    //二维码图片的宽
        int height = 313;   //二维码图片的长
        String format = "png";  //二维码图片的格式
        ServletOutputStream stream = null;

        Invoice invoice = invoiceService.selectInvoiceDetailById(id);//开票信息
        Market market = marketService.selectByPrimaryKey(invoice.getMarketId());//市场信息
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        String tenantName = userTenantService.selectByTenanId(invoice.getTenantId());
        invoice.setTenantName(tenantName);
        StringBuilder sb = new StringBuilder();
        sb.append(invoice.getPurchacerName()).append(",");
        sb.append(invoice.getPurchacerTaxNo()).append(",");
        sb.append(invoice.getPurchacerAddress()).append(",");
        sb.append(invoice.getPurchacerMobile()).append(",");
        sb.append(invoice.getPurchacerTaxNo()).append(",");
        sb.append(invoice.getSellerAddress()).append(",");
        sb.append(invoice.getSellerMobile()).append(",");
        sb.append(invoice.getSellerName()).append(",");
        sb.append(invoice.getSellerTaxNo()).append(",");
        sb.append(invoice.getSellerTaxNo()).append(",");
        sb.append(invoice.getCarNo()).append(",");
        sb.append(invoice.getCertificateNumber()).append(",");
        sb.append(invoice.getVin()).append(",");
        sb.append(getCarInvoiceType(invoice.getCarInvoiceType())).append(",");
        sb.append(invoice.getFactoryPlate()).append(",");
        sb.append(invoice.getOffice()).append(",");
        sb.append(MoneyUtils.arabNumToChineseRMB(invoice.getPrice())).append(",");
        sb.append(invoice.getPrice()).append(",");
        sb.append(market.getName()).append(",");
        sb.append(market.getAddress()).append(",");
        sb.append(market.getBank()).append(",");
        sb.append(market.getAccount()).append(",");
        sb.append(market.getPhone()).append(",");
        sb.append(market.getTaxno()).append(",");
        sb.append(invoice.getInvoiceCode()).append(",");
        sb.append(invoice.getInvoiceNo()).append(",");
        sb.append(DatePoor.getStringForDateByFormat(invoice.getBillTime(), "yyyy-MM-dd")).append(",");
        sb.append(DatePoor.getStringForDateByFormat(invoice.getInitialRegistrationDate(), "yyyy-MM-dd")).append("&");
        System.out.println(sb.toString());
        stream = response.getOutputStream();
        BitMatrix bitMatrix = new MultiFormatWriter().encode(sb.toString(), BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);

        if (stream != null) {
            stream.flush();
            stream.close();
        }
    }

    /**
     * @param vin
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getCar/{vin}")
    @OperationAnnotation(title = "根据vin查询车辆信息")
    public InterfaceResult getCar(@PathVariable(value = "vin") String vin, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        Car car = new Car();
        car.setVin(vin);
        car.setMarketId(user.getMarketId());
        Car cars = carService.getCar(car);
        if (null != cars) {
            String tenantName = userTenantService.selectByTenanId(cars.getTenant());
            cars.setTenantName(tenantName);
            interfaceResult.InterfaceResult200(cars);
        } else {
            interfaceResult.InterfaceResult600("查询无此车");
        }
        return interfaceResult;
    }

    /**
     * @param invoice
     * @return 作废发票 传id和invoiceStatus(1:待处理,2:已完成,0:作废)
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/modifyInvoice")
    @OperationAnnotation(title = "作废发票")
    public InterfaceResult modifyInvoice(@RequestBody Invoice invoice, HttpServletRequest request) throws Exception {
        User user = super.getCurrentUser(request);
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        count = invoiceService.updateByPrimaryKeySelective(invoice);
        if (count > 0) {
            if (invoice.getCarSources() == 1 && null != invoice.getTransferType() && invoice.getTransferType() == 1) {//1为库存车且是卖出过户
                Car car = new Car();
                car.setId(invoice.getCarId());
                car.setStockStatus(invoice.getCarStockStatus());//修改为开票之前的状态
                count = carService.updateByPrimaryKeySelective(car);
                String topic = super.getTopic(user.getMarketId());
                //同步删除本地车辆状态
                //组装云端参数
                PostParam postParam = new PostParam();
                postParam.setData(JsonTools.toJson(car));
                postParam.setMarket(user.getMarketId());
                postParam.setUrl("/barrier/car/update");
                postParam.setMethod("post");
                postParam.setOnlySend(false);
                postParam.setMessageTime(Constants.dateformat.format(new Date()));
                messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
            }
            interfaceResult.InterfaceResult200(count);
        } else {
            interfaceResult.InterfaceResult600("作废失败");
        }
        return interfaceResult;
    }

    /**
     * @param invoice
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/modifyInvoiceById")
    @OperationAnnotation(title = "修改发票详情")
    public InterfaceResult modifyInvoiceById(@RequestBody Invoice invoice, HttpServletRequest request) throws Exception {
        User user = super.getCurrentUser(request);
        invoice.setUserId(user.getUserId());
        invoice.setInvoiceStatus(2);
        invoice.setBillTime(new Date());
        invoice.setMarketId(user.getMarketId());
        if (invoice.getCarNo().length() == 7) {
            invoice.setIsNewEnergy(1);//不是新能源车
        } else {
            invoice.setIsNewEnergy(0);//是新能源车
        }
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        if (null != invoice.getRemark() && invoice.getRemark() == 2) {
//            interfaceResult = confirm(invoice);
//            if (!interfaceResult.getCode().equals("200")) {
//                return interfaceResult;
//            }
            List<InvoicePurchase> list = invoicePurchaseService.selectInvoicePurchase(invoice.getMarketId(), user.getUserId());
            if (null != list && list.size() > 0) {
                InvoicePurchase invoicePurchase = new InvoicePurchase();
                invoicePurchase = list.get(0);
                invoice.setInvoiceCode(invoicePurchase.getInvoiceCode());
                invoice.setInvoiceNo(invoicePurchase.getInvoiceNo());
                Map map = new HashMap();
                map.put("billTime", invoice.getBillTime());
                map.put("invoiceCode", invoicePurchase.getInvoiceCode());
                map.put("invoiceNo", invoicePurchase.getInvoiceNo());
                count = invoiceService.updateByPrimaryKeySelective(invoice);
                interfaceResult = confirm2(invoice, count, invoicePurchase);
                if (interfaceResult.getCode().equals("200")) {
                    interfaceResult.InterfaceResult200(map);
                }
            } else {
                interfaceResult.InterfaceResult600("发票已用完，请购票");
            }
        } else {
            count = invoiceService.updateByPrimaryKeySelective(invoice);
            if (count > 0) {
                interfaceResult.InterfaceResult200(count);
            } else {
                interfaceResult.InterfaceResult600("修改失败");
            }

        }
        return interfaceResult;

    }


    /**
     * @param invoice
     * @return 开票
     * @throws Exception
     */
//    @RequestMapping(value = "/invoice/addInvoice")
//    @OperationAnnotation(title = "新增开票")
//    public InterfaceResult addInvoice(HttpServletRequest request, @RequestBody Invoice invoice) throws Exception {
//        User user = super.getCurrentUser(request);
//        InterfaceResult interfaceResult = new InterfaceResult();
//        invoice.setId(UuidUtils.generateIdentifier());
//        invoice.setBillTime(new Date());
//        invoice.setUserId(user.getUserId());
//        if (invoice.getCarNo().length() == 7) {
//            invoice.setIsNewEnergy(1);//不是新能源车
//        } else {
//            invoice.setIsNewEnergy(0);//是新能源车
//        }
//        int count = 0;
//        count = invoiceService.insertSelective(invoice);
//        if (count == 0) {
//            interfaceResult.InterfaceResult600("新增失败");
//        } else {
//            InvoicePurchase invoicePurchase = new InvoicePurchase();
//            invoicePurchase.setId(invoice.getInvoicePurchaseId());
//            invoicePurchase = invoicePurchaseService.selectByPrimaryKey(invoice.getInvoicePurchaseId());
//            invoicePurchase.setPollResidue(invoicePurchase.getPollResidue() - 1);//剩余票号-1
//            if (Integer.parseInt(invoicePurchase.getInvoiceEndNo()) == Integer.parseInt(invoice.getInvoiceNo())) {
//                invoicePurchase.setStatus(2);//购票最后一张
//                invoicePurchase.setInvoiceNo(invoicePurchase.getInvoiceEndNo());
//            } else {
//                invoicePurchase.setInvoiceNo(StringUtils.leftPad(((Integer.parseInt(invoicePurchase.getInvoiceNo()) + 1) + ""), 8, "0"));//当前票号+1
//            }
//            invoicePurchaseService.updateByPrimaryKeySelective(invoicePurchase);
//            if (invoice.getCarSources() == 1 && null != invoice.getTransferType() && invoice.getTransferType() == 1) {//库存车
//                Car car = new Car();
//                car.setId(invoice.getCarId());
//                if (invoice.getCarStockStatus() == 3) {
//                    car.setStockStatus(5);//售出已出场状态
//                } else {
//                    car.setStockStatus(4);//售出未出场状态
//                }
//                carService.updateByPrimaryKeySelective(car);
//                String topic = super.getTopic(user.getMarketId());
//                //同步删除本地车辆状态
//                //组装云端参数
//                PostParam postParam = new PostParam();
//                postParam.setData(JsonTools.toJson(car));
//                postParam.setMarket(user.getMarketId());
//                postParam.setUrl("/barrier/car/update");
//                postParam.setMethod("post");
//                postParam.setOnlySend(false);
//                postParam.setMessageTime(Constants.dateformat.format(new Date()));
//                messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
//            }
//            interfaceResult.InterfaceResult200("新增成功");
//        }
//        return interfaceResult;
//    }


    /**
     * @param invoice
     * @return 开票
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/insertInvoice")
    @OperationAnnotation(title = "新增开票")
    public InterfaceResult insertInvoice(HttpServletRequest request, @RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        invoice.setId(UuidUtils.generateIdentifier());
        invoice.setBillTime(new Date());
        invoice.setUserId(user.getUserId());
        invoice.setMarketId(user.getMarketId());
        if (invoice.getCarNo().length() == 7) {
            invoice.setIsNewEnergy(1);//不是新能源车
        } else {
            invoice.setIsNewEnergy(0);//是新能源车
        }
        // 买入判断商户车 修改vin 相关的 商户ID
//        interfaceResult = confirm(invoice);
//        if (!interfaceResult.getCode().equals("200")) {
//            return interfaceResult;
//        }
//        if (1 == invoice.getCarSources() && 0 == invoice.getTransferType()) {
//
//            if (null == invoice.getCarId() || invoice.getCarId().isEmpty()) {
//                return getInterfaceResult("600", "请输入carId");
//            }
//
//            CarVo carVoById = carService.getCarVoById(invoice.getCarId());
//
//            if (null == carVoById) {
//                return getInterfaceResult("600", "该车不属于库存车，无法买入");
//            }
//
//            if (!carVoById.getTenant().equals(invoice.getTenantId())) {
//                // 判断要存入的车商车位是否足够
//                GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest getCarSpaceAndOfficeByMarketIdOrAreaIdRequest = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
//                getCarSpaceAndOfficeByMarketIdOrAreaIdRequest.setMarketId(user.getMarketId());
//                getCarSpaceAndOfficeByMarketIdOrAreaIdRequest.setTenantId(invoice.getTenantId());
//
//                GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responseCount = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(getCarSpaceAndOfficeByMarketIdOrAreaIdRequest);
//
//                if (null == responseCount) {
//                    return getInterfaceResult("600", "请配置展厅转换比例");
//                }
//                Integer inventoryNumber = responseCount.getCarTotal();
//
//                Configuration c = new Configuration();
//                c.setMarketId(user.getMarketId());
//                c.setConfigurationKey("car_num");
//                List<Configuration> list = configurationService.searchConfiguration(c);
//                if (list != null && list.size() > 0) {
//                    for (Configuration configuration : list) {
//                        String value = configuration.getConfigurationValue();
//                        if (value.substring(0, 1).equals("+")) {
//                            inventoryNumber = inventoryNumber + Integer.parseInt(value.substring(1));
//                        }
//                    }
//                }
//
//                Car tenantIdCar = new Car();
//                tenantIdCar.setTenant(invoice.getTenantId());
//                tenantIdCar.setMarketId(user.getMarketId());
//                tenantIdCar.setCarType(1);
//                Integer counts = 0;
//                counts = carService.selectCountsByCar(tenantIdCar);
//                Integer upCarnum = (counts == null ? 0 : counts);
//                if (inventoryNumber <= upCarnum) {
//                    return getInterfaceResult("600", "车位总数" + inventoryNumber + ", 已经全部使用，不允许买入");
//                }
//            }
//
//        }

        int count = 0;
        List<InvoicePurchase> list = invoicePurchaseService.selectInvoicePurchase(invoice.getMarketId(), user.getUserId());
        if (null != list && list.size() > 0) {
            InvoicePurchase invoicePurchase = new InvoicePurchase();
            invoicePurchase = list.get(0);
            invoice.setInvoiceCode(invoicePurchase.getInvoiceCode());
            invoice.setInvoiceNo(invoicePurchase.getInvoiceNo());
            Map map = new HashMap();
            map.put("billTime", invoice.getBillTime());
            map.put("invoiceCode", invoicePurchase.getInvoiceCode());
            map.put("invoiceNo", invoicePurchase.getInvoiceNo());
            count = invoiceService.insertSelective(invoice);
            interfaceResult = confirm2(invoice, count, invoicePurchase);
            interfaceResult.InterfaceResult200(map);
//            if (count == 0) {
//                interfaceResult.InterfaceResult600("新增失败");
//            } else {
//                invoicePurchase.setPollResidue(invoicePurchase.getPollResidue() - 1);//剩余票号-1
//                if (Integer.parseInt(invoicePurchase.getInvoiceEndNo()) == Integer.parseInt(invoice.getInvoiceNo())) {
//                    invoicePurchase.setStatus(2);//购票最后一张
//                    invoicePurchase.setInvoiceNo(invoicePurchase.getInvoiceEndNo());
//                } else {
//                    invoicePurchase.setInvoiceNo(StringUtils.leftPad(((Integer.parseInt(invoicePurchase.getInvoiceNo()) + 1) + ""), 8, "0"));//当前票号+1
//                }
//                counts = invoicePurchaseService.updateByIdAndVersion(invoicePurchase);
//                if (counts == 0) {
//                    interfaceResult.InterfaceResult600("购票异常，请重新购票!");
//                    return interfaceResult;
//                }
//                if (invoice.getCarSources() == 1 && null != invoice.getTransferType() && invoice.getTransferType() == 1) {
//                    //库存车且是卖出过户
//                    Car car = new Car();
//                    car.setId(invoice.getCarId());
//                    if (invoice.getCarStockStatus() == 3) {
//                        car.setStockStatus(5);//售出已出场状态
//                    } else {
//                        car.setStockStatus(4);//售出未出场状态
//                    }
//                    if (null != invoice.getTenantId() && invoice.getTenantId() != "") {
//                        car.setTenant(invoice.getTenantId());
//                    }
//                    carService.updateByPrimaryKeySelective(car);
//                    String topic = super.getTopic(user.getMarketId());
//                    //同步删除本地车辆状态
//                    //组装云端参数
//                    PostParam postParam = new PostParam();
//                    postParam.setData(JsonTools.toJson(car));
//                    postParam.setMarket(user.getMarketId());
//                    postParam.setUrl("/barrier/car/updateCar");
//                    postParam.setMethod("post");
//                    postParam.setOnlySend(false);
//                    postParam.setMessageTime(Constants.dateformat.format(new Date()));
//                    messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
//                } else if (1 == invoice.getCarSources() && 0 == invoice.getTransferType()) {
//                    //库存车且是买入过户  修改库存车所属商户
//                    Car car = new Car();
//                    car.setId(invoice.getCarId());
//                    car.setTenant(invoice.getTenantId());
//                    carService.updateByPrimaryKeySelective(car);
//
//                    String topic = super.getTopic(user.getMarketId());
//                    //同步删除本地车辆状态
//                    //组装云端参数
//                    PostParam postParam = new PostParam();
//                    postParam.setData(JsonTools.toJson(car));
//                    postParam.setMarket(user.getMarketId());
//                    postParam.setUrl("/barrier/car/updateCar");
//                    postParam.setMethod("post");
//                    postParam.setOnlySend(false);
//                    postParam.setMessageTime(Constants.dateformat.format(new Date()));
//                    messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
//                }
//
//                interfaceResult.InterfaceResult200(map);
//            }
        } else {
            interfaceResult.InterfaceResult600("发票已用完，请购票");
        }
        return interfaceResult;
    }

    @PostMapping(value = "/invoice/getInvoicePerson")
    public InterfaceResult getInvoicePerson(@RequestBody Map<String, String> map, HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            String marketId = getCurrentUser(request).getMarketId();
            String idCard = map.get("idCard");
            String purchacerIdCard = map.get("purchacerIdCard");
            if (StringUtil.isNotEmpty(purchacerIdCard)) {
                idCard = purchacerIdCard;
            }
            String sellerIdCard = map.get("sellerIdCard");
            if (StringUtil.isNotEmpty(sellerIdCard)) {
                idCard = sellerIdCard;
            }
            interfaceResult.InterfaceResult600("无查询记录");
            List<InvoicePerson> invoicePersonList = invoiceService.getInvoicePerson(idCard, marketId);
            if (null != invoicePersonList && invoicePersonList.size() > 0) {
                for (int i = 0 ; i < invoicePersonList.size() ; i ++) {
                    InvoicePerson invoice = invoicePersonList.get(i);
                    String purchacerIdCardBack = invoice.getPurchacerIdCard();
                    String sellerIdCardBack = invoice.getSellerIdCard();
                    if(purchacerIdCardBack != null && idCard.equals(purchacerIdCardBack)){
                        interfaceResult.InterfaceResult200(invoicePersonList.get(i));
                        break;
                    }
                    if(sellerIdCardBack != null && idCard.equals(sellerIdCardBack)){
                        interfaceResult.InterfaceResult200(invoicePersonList.get(i));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
        }

        return interfaceResult;
    }

    @RequestMapping(value = "/invoice/getAllTransaction")
    @OperationAnnotation(title = "查询交易列表")
    public InterfaceResult getAllTransaction(@RequestBody GetAllTransactionRequest request, HttpServletRequest servletRequest) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(servletRequest).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了
        request.setMarketId(marketId);

        PageInfo pageInfo = transactionService.getAllTransaction(request);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    /**
     * param:
     * describe: 通过业务编号获取商户APP交易合同html地址
     * create_date:  lxy   2018/11/12  13:55
     **/
    @GetMapping(value = "/invoice/getHtml/{currentNo}")
    @OperationAnnotation(title = "获取交易合同的html文件地址")
    public InterfaceResult getAllTransaction(@PathVariable(value = "currentNo") String currentNo) throws Exception {

        TransferCar transferCarByNo = transferCarService.getTransferCarByNo(currentNo);
        if (null == transferCarByNo) {
            return getInterfaceResult("600", "查询失败");
        }
        //return getInterfaceResult("200", transferCarByNo.getContractUrl());
        return getInterfaceResult("200", transferCarByNo);
    }

    @RequestMapping(value = "/invoice/getAllTransactionExcel")
    @OperationAnnotation(title = "查询交易列表")
    public InterfaceResult getAllTransactionExcel(@RequestBody GetAllTransactionRequest request, HttpServletRequest servletRequest) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(servletRequest).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了\

        request.setMarketId(marketId);

        List<Map> invoices = transactionService.getAllTransactionExcel(request);
        interfaceResult.InterfaceResult200(invoices);

        return interfaceResult;
    }

    @GetMapping("/invoice/getTransactionDetails/{transactionId}")
//    @OperationAnnotation(title = "查询交易详情")
    public InterfaceResult getAllTransaction(@PathVariable("transactionId") String transactionId, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(request).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了

        Invoice invoice = transactionService.getTransactionDetails(marketId, transactionId);
        if (invoice != null) {
//            Date billTime = invoice.getBillTime();
//            String substring = billTime.toString().substring(0, 10);
//            String dateFormatDateonly = DateUtils.DATE_FORMAT_DATEONLY;
//            Date date = DateUtils.parseDate(substring, dateFormatDateonly);
            invoice.setBillTime(invoice.getBillTime());
            invoice.setDealTime(invoice.getBillTime());
//            String s = invoice.getInitialRegistrationDate().toString().substring(0,10);
//            Date date1 = DateUtils.parseDate(s, DateUtils.DATE_FORMAT_DATEONLY);
            invoice.setInitialRegistrationDate(invoice.getInitialRegistrationDate());
        }

        Market market = marketService.getMarket(marketId);

        CarBase carBase = null;
        if (invoice != null) {
            String carId = invoice.getCarId();
            carBase = carBaseService.selectByPrimaryKey(carId);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("market", market);
        map.put("invoice", invoice);
        map.put("carBase", carBase);

        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    @RequestMapping("/invoice/detailsExcel")
    @OperationAnnotation(title = "开票导出Excel")
    public InterfaceResult detailsExcel(@RequestBody Invoice invoice, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        invoice.setUserId(user.getUserId());
        invoice.setMarketId(user.getMarketId());
        List<TradeInformation> tradeInformations = invoiceService.detailsExcel(invoice);

        interfaceResult.InterfaceResult200(tradeInformations);

        return interfaceResult;
    }


    private String getCarInvoiceType(String type) {
        String carInvoiceType = "小型轿车";
        switch (type) {
            case "1":
                carInvoiceType = "小型轿车";
                break;
            case "2":
                carInvoiceType = "小型普通客车";
                break;
            case "3":
                carInvoiceType = "中型普通货车";
                break;
            case "4":
                carInvoiceType = "大型普通客车";
                break;
            case "5":
                carInvoiceType = "轻型普通货车";
                break;
            case "6":
                carInvoiceType = "小型越野客车";
                break;
            case "7":
                carInvoiceType = "小型面包车";
                break;
            case "8":
                carInvoiceType = "微型轿车";
                break;
            case "9":
                carInvoiceType = "微型普通客车";
                break;
            case "10":
                carInvoiceType = "微型普通货车";
                break;
            case "11":
                carInvoiceType = "小型专用客车";
                break;
            case "12":
                carInvoiceType = "重型半挂牵引车";
                break;
            case "13":
                carInvoiceType = "普通二轮摩托车";
                break;
            case "14":
                carInvoiceType = "中型普通货车";
                break;
        }
        return carInvoiceType;
    }


    public InterfaceResult confirm(Invoice invoice) throws Exception {
        // 买入判断商户车 修改vin 相关的 商户ID
        if (1 == invoice.getCarSources() && 0 == invoice.getTransferType()) {

            if (null == invoice.getCarId() || invoice.getCarId().isEmpty()) {
                return getInterfaceResult("600", "请输入carId");
            }

            CarVo carVoById = carService.getCarVoById(invoice.getCarId());

            if (null == carVoById) {
                return getInterfaceResult("600", "该车不属于库存车，无法买入");
            }

            if (!carVoById.getTenant().equals(invoice.getTenantId())) {
                // 判断要存入的车商车位是否足够
                GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest getCarSpaceAndOfficeByMarketIdOrAreaIdRequest = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
                getCarSpaceAndOfficeByMarketIdOrAreaIdRequest.setMarketId(invoice.getMarketId());
                getCarSpaceAndOfficeByMarketIdOrAreaIdRequest.setTenantId(invoice.getTenantId());

                GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responseCount = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(getCarSpaceAndOfficeByMarketIdOrAreaIdRequest);

                if (null == responseCount) {
                    return getInterfaceResult("600", "请配置展厅转换比例");
                }
                Integer inventoryNumber = responseCount.getCarTotal();

                Configuration c = new Configuration();
                c.setMarketId(invoice.getMarketId());
                c.setConfigurationKey("car_num");
                List<Configuration> list = configurationService.searchConfiguration(c);
                if (list != null && list.size() > 0) {
                    for (Configuration configuration : list) {
                        String value = configuration.getConfigurationValue();
                        if (value.substring(0, 1).equals("+")) {
                            inventoryNumber = inventoryNumber + Integer.parseInt(value.substring(1));
                        }
                    }
                }

                Car tenantIdCar = new Car();
                tenantIdCar.setTenant(invoice.getTenantId());
                tenantIdCar.setMarketId(invoice.getMarketId());
                tenantIdCar.setCarType(1);
                Integer counts = 0;
                counts = carService.selectCountsByCar(tenantIdCar);
                Integer upCarnum = (counts == null ? 0 : counts);
                if (inventoryNumber <= upCarnum) {
                    return getInterfaceResult("600", "车位总数" + inventoryNumber + ", 已经全部使用，不允许买入");
                }
            }

        }
        return getInterfaceResult("200", "成功");
    }

    public InterfaceResult confirm2(Invoice invoice, Integer count, InvoicePurchase invoicePurchase) {

        InterfaceResult interfaceResult = new InterfaceResult();
        Integer counts = 0;
        if (count == 0) {
            interfaceResult.InterfaceResult600("新增失败");
        } else {
            invoicePurchase.setPollResidue(invoicePurchase.getPollResidue() - 1);//剩余票号-1
            if (Integer.parseInt(invoicePurchase.getInvoiceEndNo()) == Integer.parseInt(invoice.getInvoiceNo())) {
                invoicePurchase.setStatus(2);//购票最后一张
                invoicePurchase.setInvoiceNo(invoicePurchase.getInvoiceEndNo());
            } else {
                invoicePurchase.setInvoiceNo(StringUtils.leftPad(((Integer.parseInt(invoicePurchase.getInvoiceNo()) + 1) + ""), 8, "0"));//当前票号+1
            }
            counts = invoicePurchaseService.updateByIdAndVersion(invoicePurchase);
            if (counts == 0) {
                interfaceResult.InterfaceResult600("购票异常，请重新购票!");
                return interfaceResult;
            }
            if (invoice.getCarSources() == 1 && null != invoice.getTransferType() && invoice.getTransferType() == 1) {
                //库存车且是卖出过户
                Car car = new Car();
                car.setId(invoice.getCarId());
                if (invoice.getCarStockStatus() == 3) {
                    car.setStockStatus(5);//售出已出场状态
                } else {
                    car.setStockStatus(4);//售出未出场状态
                }
                if (null != invoice.getTenantId() && invoice.getTenantId() != "") {
                    car.setTenant(invoice.getTenantId());
                }
                carService.updateByPrimaryKeySelective(car);
                String topic = super.getTopic(invoice.getMarketId());
                //同步删除本地车辆状态
                //组装云端参数
                PostParam postParam = new PostParam();
                postParam.setData(JsonTools.toJson(car));
                postParam.setMarket(invoice.getMarketId());
                postParam.setUrl("/barrier/car/updateCar");
                postParam.setMethod("post");
                postParam.setOnlySend(false);
                postParam.setMessageTime(Constants.dateformat.format(new Date()));
                messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
            } else if (1 == invoice.getCarSources() && 0 == invoice.getTransferType()) {
                //库存车且是买入过户  修改库存车所属商户
                Car car = new Car();
                car.setId(invoice.getCarId());
                car.setTenant(invoice.getTenantId());
                carService.updateByPrimaryKeySelective(car);

                String topic = super.getTopic(invoice.getMarketId());
                //同步删除本地车辆状态
                //组装云端参数
                PostParam postParam = new PostParam();
                postParam.setData(JsonTools.toJson(car));
                postParam.setMarket(invoice.getMarketId());
                postParam.setUrl("/barrier/car/updateCar");
                postParam.setMethod("post");
                postParam.setOnlySend(false);
                postParam.setMessageTime(Constants.dateformat.format(new Date()));
                messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
            }
        }
        return getInterfaceResult("200", "ok");

    }


}
