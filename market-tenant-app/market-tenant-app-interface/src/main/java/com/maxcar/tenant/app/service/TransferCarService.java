package com.maxcar.tenant.app.service;

import com.alibaba.fastjson.JSONArray;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.app.entity.TransferCar;
import com.maxcar.tenant.app.model.request.GetTransferCarListRequest;
import com.maxcar.tenant.app.model.request.SaveAddDealInfoRequest;
import com.maxcar.tenant.app.model.request.SaveBuySellInfoBuyerRequest;
import com.maxcar.tenant.app.model.request.SaveBuySellInfoSellerRequest;
import com.maxcar.tenant.app.model.request.SaveSignUrlRequest;
import com.maxcar.tenant.app.model.request.SaveTransferCarRequest;

import java.util.Map;

public interface TransferCarService {

    Map<String, String> replaceVars(String template, JSONArray varNames, String transferCarId) throws Exception;

    TransferCar getTransferCarByNo(String transferCarNo);

    TransferCar getTransferCarById(String transferCarId);

    /**
     * param:
     * describe: 查询过户车辆列表 分页
     * create_date:  lxy   2018/10/15  14:32
     **/
    InterfaceResult getTransferCarList(GetTransferCarListRequest request);

    /**
     * param:
     * describe: 获取过户车辆详细信息
     * create_date:  lxy   2018/10/15  14:34
     **/
    InterfaceResult getTransferCarDetailById(String transferCarId);

    /**
     * param:  
     * describe: 添加车辆信息
     * create_date:  lxy   2018/10/15  14:34 
     **/
    InterfaceResult saveTransferCar(SaveTransferCarRequest request);

    /**
     * param:  
     * describe:  完善卖家信息
     * create_date:  lxy   2018/10/15  14:34 
     **/
    InterfaceResult saveBuySellInfoSeller(SaveBuySellInfoSellerRequest request);

    /**
     * param:
     * describe: 完善买家信息
     * create_date:  lxy   2018/10/15  14:35 
     **/
    InterfaceResult saveBuySellInfoBuyer(SaveBuySellInfoBuyerRequest request);

    /**
     * param:
     * describe: 完善交易信息
     * create_date:  lxy   2018/10/15  14:36
     **/
    InterfaceResult saveAddDealInfo(String template, JSONArray varNames, SaveAddDealInfoRequest request) throws Exception;


    InterfaceResult deleteTransferCar(String transferCarId);

    InterfaceResult confirmDealPrice(String transferCarId, String template, JSONArray varNames, Double dealPrice) throws Exception;

    InterfaceResult saveSignUrl(String template, JSONArray varNames, SaveSignUrlRequest signUrlRequest) throws Exception;

    InterfaceResult confirmContract(String template, JSONArray varNames, String transferCarId) throws Exception;
}
