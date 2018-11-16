package com.maxcar.tenant.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.HanyuPinyinHelper;
import com.maxcar.base.util.HtmlTemplateUtil;
import com.maxcar.base.util.ToolDataUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.oss.OSSManageUtil;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.app.dao.AddDealInfoMapper;
import com.maxcar.tenant.app.dao.BuySellInfoMapper;
import com.maxcar.tenant.app.dao.TransferCarMapper;
import com.maxcar.tenant.app.entity.AddDealInfo;
import com.maxcar.tenant.app.entity.BuySellInfo;
import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.entity.TransferCarExample;
import com.maxcar.tenant.app.model.request.GetTransferCarListRequest;
import com.maxcar.tenant.app.model.request.SaveAddDealInfoRequest;
import com.maxcar.tenant.app.model.request.SaveBuySellInfoBuyerRequest;
import com.maxcar.tenant.app.model.request.SaveBuySellInfoSellerRequest;
import com.maxcar.tenant.app.model.request.SaveSignUrlRequest;
import com.maxcar.tenant.app.model.request.SaveTransferCarRequest;
import com.maxcar.tenant.app.model.response.GetTransferCarDetailByIdResponse;
import com.maxcar.tenant.app.model.response.TransferCarResponse;
import com.maxcar.tenant.app.service.TransferCarService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.Market;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.MarketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("transferCarService")
public class TransferCarServiceImpl implements TransferCarService {

    @Autowired
    private TransferCarMapper transferCarMapper;

    @Autowired
    private BuySellInfoMapper buySellInfoMapper;

    @Autowired
    private AddDealInfoMapper addDealInfoMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MarketService marketService;


    @Override
    public TransferCar getTransferCarByNo(String transferCarNo) {
        return transferCarMapper.getTransferCarByNo(transferCarNo);
    }

    /**
     * param:
     * describe: 获取过户车辆信息
     * create_date:  lxy   2018/10/12  14:43
     **/
    @Override
    public TransferCar getTransferCarById(String transferCarId) {
        return transferCarMapper.selectByPrimaryKey(transferCarId);
    }

    /**
     * param:
     * describe: 查询过户车辆列表
     * create_date:  lxy   2018/10/15  11:34
     **/
    @Override
    public InterfaceResult getTransferCarList(GetTransferCarListRequest request) {

        PageHelper.startPage(request.getCurPage(), request.getPageSize());

        InterfaceResult interfaceResult = new InterfaceResult();

        TransferCarExample transferCarExample = new TransferCarExample();
        transferCarExample.setOrderByClause(" insert_time desc");

        TransferCarExample.Criteria criteria = transferCarExample.createCriteria();

        criteria.andTypeEqualTo(request.getType()).andMarketIdEqualTo(request.getMarketId()).
                andTenantIdEqualTo(request.getTenantId()).andIsvalidEqualTo((byte) 1);

        List<TransferCar> list = transferCarMapper.selectByExample(transferCarExample);

        PageInfo<TransferCar> pageInfo = new PageInfo<>(list);

        if (request.getCurPage() > pageInfo.getPages()) {
            pageInfo.setList(null);
        }

        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    /**
     * param:
     * describe: 获取过户车辆详细信息
     * create_date:  lxy   2018/10/15  11:15
     **/
    @Override
    public InterfaceResult getTransferCarDetailById(String transferCarId) {
        InterfaceResult interfaceResult = new InterfaceResult();

        TransferCar transferCarById = getTransferCarById(transferCarId);

        if (null == transferCarById) {
            interfaceResult.InterfaceResult600("查无此车");
            return interfaceResult;
        }

        GetTransferCarDetailByIdResponse response = new GetTransferCarDetailByIdResponse();
        TransferCarResponse transferCarResponse = new TransferCarResponse();
        BeanUtils.copyProperties(transferCarById, transferCarResponse);
        Configuration configuration = configurationService.selectByPrimaryKey(transferCarById.getConfigurationId());
        if (configuration != null) {
            transferCarResponse.setConfigurationName(configuration.getConfigurationName());
        }
        response.setTransferCar(transferCarResponse);

        // 查询车辆买卖方信息
        if (transferCarById.getByeSellInfo() != null && !transferCarById.getByeSellInfo().isEmpty()) {
            BuySellInfo buySellInfo = buySellInfoMapper.selectByPrimaryKey(transferCarById.getByeSellInfo());
            response.setBuySellInfo(buySellInfo);
        }

        // 查询车辆交易信息
        if (transferCarById.getAddDealInfo() != null && !transferCarById.getAddDealInfo().isEmpty()) {
            AddDealInfo addDealInfo = addDealInfoMapper.selectByPrimaryKey(transferCarById.getAddDealInfo());
            response.setAddDealInfo(addDealInfo);
        }

        interfaceResult.InterfaceResult200(response);
        return interfaceResult;
    }


    /**
     * param:
     * describe: 添加车辆信息
     * create_date:  lxy   2018/10/12  10:48
     **/
    @Override
    public InterfaceResult saveTransferCar(SaveTransferCarRequest request) {

        InterfaceResult interfaceResult = new InterfaceResult();

        if (Magic.CAR_TYPE_INVENTORY.equals(request.getCarType())) {
            // 校验 车辆信息
            CarVo carVoById = carService.getCarVoById(request.getCarId());
            if (null == carVoById || Magic.IS_VALID_FAIL.equals(carVoById.getIsvalid())) {

                interfaceResult.InterfaceResult600("查无此车");
                return interfaceResult;
            }

            if (!Magic.CAR_TYPE_MERCHANDISE_CAR.equals(carVoById.getCarType())) {

                interfaceResult.InterfaceResult600("该车不属于库存车");
                return interfaceResult;
            }

            if (!Magic.STOCK_STATUS_1.equals(carVoById.getStockStatus()) &&
                    !Magic.STOCK_STATUS_1.equals(carVoById.getStockStatus()) &&
                    !Magic.STOCK_STATUS_1.equals(carVoById.getStockStatus())) {

                interfaceResult.InterfaceResult600("车辆库存状态异常");
                return interfaceResult;
            }

            if (!carVoById.getVin().equals(request.getVin())) {
                interfaceResult.InterfaceResult600("vin错误");
                return interfaceResult;
            }

            if (!carVoById.getMarketId().equals(request.getMarketId())) {
                interfaceResult.InterfaceResult600("该车不属于当前市场");
                return interfaceResult;
            }
            if (!carVoById.getTenant().equals(request.getTenantId())) {
                interfaceResult.InterfaceResult600("该车不属于当前商户");
                return interfaceResult;
            }

        }

        if (Magic.CAR_TYPE_INVENTORY.equals(request.getCarType()) || Magic.CAR_TYPE_AFFILIATED.equals(request.getCarType())) {

            TransferCar transferCar = new TransferCar();
            transferCar.setId(UuidUtils.generateIdentifier());
            transferCar.setTransferCarNo(getTransferCarNo(request.getMarketId()));
            transferCar.setVin(request.getVin());
            transferCar.setBrandModel(request.getBrandModel());
            transferCar.setConfigurationId(request.getConfigurationId());
            transferCar.setRegisterCode(request.getRegisterCode());
            transferCar.setPlateNum(request.getPlateNum());
            transferCar.setCheckOutPhoto(request.getCheckOutPhoto());
            transferCar.setCarType(request.getCarType());
            transferCar.setCarId(request.getCarId());
            transferCar.setMarketId(request.getMarketId());
            transferCar.setTenantId(request.getTenantId());

            transferCar.setStatus(Magic.TRANSFER_CAR_STATUS_1);

            boolean transferCarResponse = false;
            if (null == request.getTransferCarId() || request.getTransferCarId().isEmpty()) {
                if (StringUtils.isNotBlank(request.getCarId())) {
                    TransferCar inProgressCar = transferCarMapper.getTransferCarByCarId(request.getCarId());
                    if (inProgressCar != null) {
                        interfaceResult.InterfaceResult600("该车已经在过户中。");
                        return interfaceResult;
                    }
                }
                transferCar.setStartTime(new Date());
                transferCarResponse = ToolDataUtils.isOperationSuccess(transferCarMapper.insertSelective(transferCar));
            } else {
                transferCar.setId(request.getTransferCarId());
                transferCar.setUpdateTime(new Date());
                transferCarResponse = ToolDataUtils.isOperationSuccess(transferCarMapper.updateByPrimaryKeySelective(transferCar));
            }

            if (transferCarResponse) {
                // 添加车辆信息成功
                Map<String, String> map = new HashMap<>(4);
                map.put("transferCarNo", transferCar.getTransferCarNo());
                map.put("transferCarId", transferCar.getId());
                interfaceResult.InterfaceResult200(map);
            } else {
                interfaceResult.InterfaceResult600("保存车辆信息失败");
            }

        } else {
            interfaceResult.InterfaceResult406();
        }

        return interfaceResult;
    }

    /**
     * param:
     * describe: 完善卖家信息
     * create_date:  lxy   2018/10/12  14:56
     **/
    @Override
    public InterfaceResult saveBuySellInfoSeller(SaveBuySellInfoSellerRequest request) {

        InterfaceResult InterfaceResult = new InterfaceResult();

        TransferCar transferCarById = getTransferCarById(request.getTransferCarId());

        if (null == transferCarById) {

            InterfaceResult.InterfaceResult600("没有查询到过户车辆信息");
            return InterfaceResult;
        }

        BuySellInfo buySellInfo = new BuySellInfo();

        buySellInfo.setSellerType(request.getSellerType());
        buySellInfo.setSellerName(request.getSellerName());
        buySellInfo.setSellerIdcard(request.getSellerIdcard());
        buySellInfo.setSellerIdcardAddress(request.getSellerIdcardAddress());
        buySellInfo.setSellerPhone(request.getSellerPhone());
        buySellInfo.setSellerIdcardFront(request.getSellerIdcardFront());
        buySellInfo.setSellerIdcardReverse(request.getSellerIdcardReverse());
        buySellInfo.setSellerAgency(request.getSellerAgency());
        buySellInfo.setSellerCreditCode(request.getSellerCreditCode());
        buySellInfo.setSellerAddress(request.getSellerAddress());
        buySellInfo.setSellerBusinessLicense(request.getSellerBusinessLicense());

        boolean buySellInfoResponse;
        Byte status = Magic.TRANSFER_CAR_STATUS_2;

        if (transferCarById.getByeSellInfo() == null || transferCarById.getByeSellInfo().isEmpty()) {
            buySellInfo.setId(UuidUtils.generateIdentifier());
            buySellInfoResponse = ToolDataUtils.isOperationSuccess(buySellInfoMapper.insertSelective(buySellInfo));
        } else {
            buySellInfo.setId(transferCarById.getByeSellInfo());

            if (transferCarById.getStatus() > status) {
                status = transferCarById.getStatus();
            }
            buySellInfo.setUpdateTime(new Date());
            buySellInfoResponse = ToolDataUtils.isOperationSuccess(buySellInfoMapper.updateByPrimaryKeySelective(buySellInfo));
        }


        if (buySellInfoResponse) {
            // 更新 过车车辆表信息
            TransferCar transferCar = new TransferCar();
            transferCar.setId(transferCarById.getId());
            transferCar.setByeSellInfo(buySellInfo.getId());
            transferCar.setStatus(status);
            transferCar.setUpdateTime(new Date());

            if (ToolDataUtils.isOperationFail(transferCarMapper.updateByPrimaryKeySelective(transferCar))) {
                // 修改过户车辆信息失败  此处应该有回滚
                InterfaceResult.InterfaceResult600("更新过户车辆信息失败");
                return InterfaceResult;
            }

            InterfaceResult.InterfaceResult200("保存卖家信息成功");
            return InterfaceResult;
        }

        InterfaceResult.InterfaceResult600("保存卖家信息失败");
        return InterfaceResult;
    }

    /**
     * param:
     * describe: 完善买家信息
     * create_date:  lxy   2018/10/12  14:56
     **/
    @Override
    public InterfaceResult saveBuySellInfoBuyer(SaveBuySellInfoBuyerRequest request) {

        InterfaceResult InterfaceResult = new InterfaceResult();

        TransferCar transferCarById = getTransferCarById(request.getTransferCarId());

        if (null == transferCarById) {

            InterfaceResult.InterfaceResult600("没有查询到过户车辆信息");
            return InterfaceResult;
        }

        BuySellInfo buySellInfo = buySellInfoMapper.selectByPrimaryKey(transferCarById.getByeSellInfo());

        if (null == buySellInfo) {
            InterfaceResult.InterfaceResult600("请先完善卖家信息");
            return InterfaceResult;
        }

        BuySellInfo updateBuySellInfo = new BuySellInfo();

        updateBuySellInfo.setId(buySellInfo.getId());
        updateBuySellInfo.setBuyerType(request.getBuyerType());
        updateBuySellInfo.setBuyerName(request.getBuyerName());
        updateBuySellInfo.setBuyerIdcard(request.getBuyerIdcard());
        updateBuySellInfo.setBuyerIdcardAddress(request.getBuyerIdcardAddress());
        updateBuySellInfo.setBuyerPhone(request.getBuyerPhone());
        updateBuySellInfo.setBuyerIdcardFront(request.getBuyerIdcardFront());
        updateBuySellInfo.setBuyerIdcardReverse(request.getBuyerIdcardReverse());
        updateBuySellInfo.setBuyerAgency(request.getBuyerAgency());
        updateBuySellInfo.setBuyerCreditCode(request.getBuyerCreditCode());
        updateBuySellInfo.setBuyerBusinessLicense(request.getBuyerBusinessLicense());
        updateBuySellInfo.setBuyerAddress(request.getBuyerAddress());

        Byte status = Magic.TRANSFER_CAR_STATUS_3;

        if (transferCarById.getStatus() > status) {
            status = transferCarById.getStatus();
        }

        updateBuySellInfo.setUpdateTime(new Date());

        if (ToolDataUtils.isOperationSuccess(buySellInfoMapper.updateByPrimaryKeySelective(updateBuySellInfo))) {
            // 更新 过车车辆表信息
            TransferCar transferCar = new TransferCar();
            transferCar.setId(transferCarById.getId());
            transferCar.setByeSellInfo(buySellInfo.getId());
            transferCar.setStatus(status);
            transferCar.setUpdateTime(new Date());

            if (ToolDataUtils.isOperationFail(transferCarMapper.updateByPrimaryKeySelective(transferCar))) {
                // 修改过户车辆信息失败  此处应该有回滚
                InterfaceResult.InterfaceResult600("更新过户车辆信息失败");
                return InterfaceResult;
            }

            InterfaceResult.InterfaceResult200("保存买家信息成功");
            return InterfaceResult;
        }

        InterfaceResult.InterfaceResult600("保存买家信息失败");
        return InterfaceResult;
    }


    /**
     * param:
     * describe: 完善交易信息
     * create_date:  lxy   2018/10/12  14:57
     **/
    @Override
    public InterfaceResult saveAddDealInfo(String template, JSONArray varNames, SaveAddDealInfoRequest request) throws Exception{

        InterfaceResult InterfaceResult = new InterfaceResult();

        TransferCar transferCarById = getTransferCarById(request.getTransferCarId());

        if (null == transferCarById) {
            InterfaceResult.InterfaceResult600("没有查询到过户车辆信息");
            return InterfaceResult;
        }
        //判断是否录入过车辆买卖方信息
        if (Magic.TRANSFER_CAR_STATUS_3 > transferCarById.getStatus()) {
            InterfaceResult.InterfaceResult600("请先完善车辆卖买方信息");
            return InterfaceResult;
        }

        AddDealInfo addDealInfo = new AddDealInfo();
        addDealInfo.setCarManager(request.getCarManager());
        addDealInfo.setDealPrice(request.getDealPrice());
        addDealInfo.setBurdenOwner(request.getBurdenOwner());
        addDealInfo.setEngineNo(request.getEngineNo());
        addDealInfo.setMileage(request.getMileage());
        addDealInfo.setEnvironmentalStandards(request.getEnvironmentalStandards());
        addDealInfo.setTradingType(request.getTradingType());

        boolean addDealInfoResponse;

        if (transferCarById.getAddDealInfo() == null || transferCarById.getAddDealInfo().isEmpty()) {
            addDealInfo.setId(UuidUtils.generateIdentifier());
            addDealInfoResponse = ToolDataUtils.isOperationSuccess(addDealInfoMapper.insert(addDealInfo));
        } else {
            addDealInfo.setId(transferCarById.getAddDealInfo());
            addDealInfoResponse = ToolDataUtils.isOperationSuccess(addDealInfoMapper.updateByPrimaryKeySelective(addDealInfo));
        }

        if (addDealInfoResponse) {
            // 更新 过车车辆表信息
            TransferCar transferCar = new TransferCar();
            transferCar.setId(transferCarById.getId());
            transferCar.setAddDealInfo(addDealInfo.getId());

            if (ToolDataUtils.isOperationFail(transferCarMapper.updateByPrimaryKeySelective(transferCar))) {
                // 修改过户车辆信息失败  此处应该有回滚
                InterfaceResult.InterfaceResult600("更新过户车辆信息失败");
                return InterfaceResult;
            }

            Map<String, String> map = replaceVars(template, varNames, request.getTransferCarId());

            InterfaceResult.InterfaceResult200(map);
            return InterfaceResult;
        }

        InterfaceResult.InterfaceResult600("保存交易信息失败");
        return InterfaceResult;
    }

    @Override
    public Map<String, String> replaceVars(String template, JSONArray varNames, String transferCarId) throws Exception {

        // 更改样式
        template = template.replaceFirst("<style>", "<!-- -<style>")
                .replaceFirst("</style>", "</style>- -->")
                .replaceFirst("<!-- <style>", "<style>")
                .replaceFirst("</style> -->", "</style>");

        return replaceVarsFinish(template, varNames, transferCarId);
    }

    Map<String, String> replaceVarsFinish(String template, JSONArray varNames, String transferCarId) throws Exception {

        TransferCar transferCar = getTransferCarById(transferCarId);
        AddDealInfo addDealInfo = addDealInfoMapper.selectByPrimaryKey(transferCar.getAddDealInfo());
        BuySellInfo buySellInfo = buySellInfoMapper.selectByPrimaryKey(transferCar.getByeSellInfo());
        Map<String, String> vars = new HashMap<>();
        for (int i = 0; i < varNames.size(); i++) {
            String varName = varNames.getString(i);
            String fieldValue = transferCar.getFieldValue(varName);
            if (StringUtils.isBlank(fieldValue)) {
                fieldValue = addDealInfo.getFieldValue(varName);
                if (StringUtils.isBlank(fieldValue)) {
                    fieldValue = buySellInfo.getFieldValue(varName);
                }
            }
            vars.put(varName, fieldValue);
        }
        vars.put("date", DateUtils.today());

        Map<String, String> map = new HashMap<>(2);
        map.put("html", HtmlTemplateUtil.replaceVar(template, vars));
        return map;
    }

    @Override
    public InterfaceResult deleteTransferCar(String transferCarId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        int row = transferCarMapper.updateIsvalid(transferCarId);
        if (row == 1) {
            return interfaceResult;
        }
        interfaceResult.InterfaceResult600("查无此车");
        return interfaceResult;
    }

    @Override
    public InterfaceResult confirmDealPrice(String transferCarId, String template, JSONArray varNames, Double dealPrice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        TransferCar transferCar = transferCarMapper.selectByPrimaryKey(transferCarId);
        if (transferCar == null) {
            interfaceResult.InterfaceResult600("查无此车");
            return interfaceResult;
        }

        AddDealInfo addDealInfo = addDealInfoMapper.selectByPrimaryKey(transferCar.getAddDealInfo());
        if (!dealPrice.equals(addDealInfo.getDealPrice())) {
            addDealInfoMapper.updateDealPrice(dealPrice, transferCar.getAddDealInfo());
        }

        return interfaceResult;
    }

    @Override
    public InterfaceResult saveSignUrl(String template, JSONArray varNames, SaveSignUrlRequest signUrlRequest) throws Exception{
        InterfaceResult InterfaceResult = new InterfaceResult();

        TransferCar transferCar = getTransferCarById(signUrlRequest.getTransferCarId());

        if (null == transferCar) {
            InterfaceResult.InterfaceResult600("没有查询到过户车辆信息");
            return InterfaceResult;
        }

        //判断是否录入过车辆买卖方信息
        if (Magic.TRANSFER_CAR_STATUS_3 > transferCar.getStatus()) {
            InterfaceResult.InterfaceResult600("请先完善车辆卖买方信息");
            return InterfaceResult;
        }

        // 更新 签名
        int row = transferCarMapper.updateSign(signUrlRequest.getSellerSign(), signUrlRequest.getBuyerSign(), signUrlRequest.getTransferCarId());
        if (row == 1) {
            Map<String, String> map = replaceVars(template, varNames, signUrlRequest.getTransferCarId());
            InterfaceResult.InterfaceResult200(map);
            return InterfaceResult;
        }

        InterfaceResult.InterfaceResult600("保存签名失败");
        return InterfaceResult;
    }

    @Override
    public InterfaceResult confirmContract(String template, JSONArray varNames, String transferCarId) throws Exception {

        TransferCar transferCar = getTransferCarById(transferCarId);
        if (transferCar != null) {
            if (uploadContract(template, varNames, transferCar)) {
                transferCarMapper.updateStatus(transferCar.getTransferCarNo(), Magic.TRANSFER_CAR_STATUS_4);
            }
        }

        return new InterfaceResult();
    }

    private boolean uploadContract(String template, JSONArray varNames, TransferCar transferCar) throws Exception {

        Map<String, String> map = replaceVarsFinish(template, varNames, transferCar.getId());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(map.get("html").getBytes("utf-8"));
        String remotePath = transferCar.getMarketId() + "/transfer/" + UuidUtils.generateIdentifier();

        String path = OSSManageUtil.uploadFile(inputStream, remotePath, ".html");
        if (StringUtils.isNotBlank(path)) {
            int row = transferCarMapper.updateContractUrl(transferCar.getId(), path);
            if (row == 1) {
                return true;
            }
        }
        return false;
    }

    String getTransferCarNo(String marketId) {
        Market market = marketService.selectByPrimaryKey(marketId);
        String transferCarNo = "";
        String name = market.getName();
        if (name.length() > 0) {
            transferCarNo = HanyuPinyinHelper.getFirstLetter(name.substring(0, 1));
            if (name.length() > 1) {
                transferCarNo += HanyuPinyinHelper.getFirstLetter(name.substring(1, 2));
            }
        }

        return transferCarNo + DateUtils.currentTime();
    }

}
