package com.maxcar.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.dao.*;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.ReviewListService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.ConfigurationExample;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("reviewListService")
public class ReviewListServiceImpl implements ReviewListService {


    @Autowired
    private ReviewListMapper reviewListMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarBaseMapper carBaseMapper;
    @Autowired
    private CarReviewMapper carReviewMapper;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private CarRecordMapper carRecordMapper;


    @Override
    public InterfaceResult getHisWarningList(HisWarning warning) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        PageHelper.startPage(warning.getCurrentPage(), warning.getPageSize());
        List<HisWarning> list = carReviewMapper.selectByHisWarning(warning);
        for (HisWarning hisWarning : list) {
            Car car = carMapper.selectByPrimaryKey(hisWarning.getCarId());
            if (null != car) {
                CarBase carBase = carBaseMapper.selectByPrimaryKey(hisWarning.getCarId());
                if (carBase != null && null != car.getTenant()) {
                    UserTenant userTenant = userTenantService.selectByPrimaryKey(car.getTenant());
                    CarRecord carRecords = null;
                    if (userTenant != null) {
                        carRecords = carRecordMapper.selectPlayingTime(car.getRfid(), car.getVin());
                    }
                    if (StringUtils.isNotBlank(carBase.getEvaluatePrice())) {
                        hisWarning.setInsertTime(carRecords.getInsertTime());
                    }
                    hisWarning.setTenantName(userTenant.getTenantName());
                    hisWarning.setTenant(car.getTenant());
                    hisWarning.setModleName(carBase.getModelName());
                    hisWarning.setBrandCode(carBase.getBrandCode());
                    hisWarning.setBrandName(carBase.getBrandName());
                    hisWarning.setCarStatus(car.getCarStatus());
                    hisWarning.setSeriesCode(carBase.getSeriesCode());
                    hisWarning.setSeriesName(carBase.getSeriesName());
                    if (StringUtils.isNotBlank(carBase.getEvaluatePrice())) {
                        hisWarning.setEvaluatePrice(carBase.getEvaluatePrice());
                    }
                    hisWarning.setVin(car.getVin());
                }
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @Override
    public InterfaceResult carWarningExcel(HisWarning warning) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<HisWarning> list = carReviewMapper.selectByHisWarning(warning);
        List<CarWarningExcel> carWarningExcelList= new LinkedList<>();
        for (HisWarning hisWarning : list) {
            CarWarningExcel carWarningExcel=new CarWarningExcel();
            Car car = carMapper.selectByPrimaryKey(hisWarning.getCarId());
            if (null != car) {
                CarBase carBase = carBaseMapper.selectByPrimaryKey(hisWarning.getCarId());
                carWarningExcel.setBrandName(hisWarning.getBrandName()+"-"+hisWarning.getSeriesName());
                carWarningExcel.setModelName(carBase.getModelName());
                if (carBase != null && null != car.getTenant()) {
                    UserTenant userTenant = userTenantService.selectByPrimaryKey(car.getTenant());
                    carWarningExcel.setTenantName(userTenant.getTenantName());
                    if (userTenant != null) {
                        CarRecord carRecords = carRecordMapper.selectPlayingTime(car.getRfid(), car.getVin());
                    }

                }
            }
            if (hisWarning.getCarStatus() == 1){
                carWarningExcel.setCarStatus("质押车" );
            }else {
                carWarningExcel.setCarStatus("非质押车" );
            }
            carWarningExcel.setOutReason(hisWarning.getOutReason());
            carWarningExcel.setEvaluateRrice(hisWarning.getEvaluatePrice());
            carWarningExcel.setInsertTime(hisWarning.getInsertTime());
            carWarningExcel.setBackTime(hisWarning.getBackTime());
            carWarningExcel.setVin(hisWarning.getVin());
            carWarningExcelList.add(carWarningExcel);
        }

        interfaceResult.InterfaceResult200(carWarningExcelList);
        return interfaceResult;
    }
}
