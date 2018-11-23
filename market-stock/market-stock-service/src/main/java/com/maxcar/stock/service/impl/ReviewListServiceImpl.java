package com.maxcar.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.dao.*;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.ReviewListService;
import com.maxcar.tenant.dao.UserTenantMapper;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<HisWarning> list = carReviewMapper.selectByMarketid(warning.getMarketId());
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
                    hisWarning.setInsertTime(carRecords.getInsertTime());
                    hisWarning.setTenantName(userTenant.getTenantName());
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

        if (StringUtils.isNotBlank(warning.getBrandCode()) || StringUtils.isNotBlank(warning.getTenantName()) || StringUtils.isNotBlank(warning.getVin()) || StringUtils.isNotBlank(warning.getSeriesCode())){
            for (int i = 0; i < list.size(); i++) {
                HisWarning hisWarning = list.get(i);
                String brandCode = warning.getBrandCode();
                String vin = warning.getVin();
                String tenantName = warning.getTenantName();
                String seriesCode = hisWarning.getSeriesCode();
                String backBrandCode = hisWarning.getBrandCode();
                String backTenantName = hisWarning.getTenantName();
                String backVin = hisWarning.getVin();
                String backSeriesCode = hisWarning.getSeriesCode();
                if (brandCode != null && backBrandCode != null) {
                    if (!(backBrandCode.equals(brandCode))) {
                        list.remove(i);
                        i--;
                        continue;
                    }
                }
                if (seriesCode != null && backSeriesCode != null) {
                    if (!(backSeriesCode.equals(seriesCode))) {
                        list.remove(i);
                        i--;
                        continue;
                    }
                }
                if (tenantName != null && backTenantName != null) {
                    if (!(backTenantName.equals(tenantName))) {
                        list.remove(i);
                        i--;
                        continue;
                    }
                }
                if (vin != null && backVin != null) {
                    String trim = vin.trim();
                    if (!(backVin.contains(trim))) {
                        list.remove(i);
                        i--;
                        continue;
                    }
                }

            }
        }
        PageInfo pageInfo = new PageInfo(list);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }
}
