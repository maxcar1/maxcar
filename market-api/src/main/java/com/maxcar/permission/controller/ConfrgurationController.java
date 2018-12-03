package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.OutMarketManageService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 参数设置接口
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/5/14
 */
@RestController
@RequestMapping("/api/conf")
public class ConfrgurationController extends BaseController {
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private CarBaseService carBaseService;
    @Autowired
    private OutMarketManageService outMarketManageService;

    /**
     * @Description: 参数设置列表, 根据市场查询
     * @Param: [configuration, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/14
     */
    @RequestMapping(value = "/list")
    @OperationAnnotation(title = "查看参数列表")
    public InterfaceResult list(@RequestBody Configuration configuration, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        configuration.setMarketId(user.getMarketId());
        configuration.setManagerFlag(user.getManagerFlag());
        List<Configuration> configurations = configurationService.searchConfiguration(configuration);
        interfaceResult.InterfaceResult200(configurations);
        return interfaceResult;
    }

    /**
     * @Description: 参数设置详情
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/14
     */
    @RequestMapping(value = "/{id}")
    @OperationAnnotation(title = "查询参数详细信息")
    public InterfaceResult id(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(configurationService.selectByPrimaryKey(id));
        return interfaceResult;
    }

    /**
     * @Description: 参数设置详情
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/14
     */
    @RequestMapping(value = "/list/{key}")
    @OperationAnnotation(title = "查询参数详细信息")
    public InterfaceResult market(@PathVariable("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = super.getCurrentUser(request);

        InterfaceResult interfaceResult = new InterfaceResult();
        Configuration configurationTenantType = new Configuration();
        switch (key) {//车型
            case Constants.CAR_LEVEL:
                interfaceResult.InterfaceResult200(carBaseService.carLevelGroup(user.getMarketId(), key));
                break;
            case Constants.ENVIRONMENTAL_STANDARDS://国标
                interfaceResult.InterfaceResult200(carBaseService.carLevelGroup(user.getMarketId(), key));
                break;
            case Constants.PROPERTY_TYPE://物业类型

                configurationTenantType.setMarketId(user.getMarketId());
                configurationTenantType.setManagerFlag(user.getManagerFlag());
                configurationTenantType.setConfigurationKey(Constants.PROPERTY_TYPE);

                interfaceResult.InterfaceResult200(configurationService.searchConfiguration(configurationTenantType));
                break;
            case Constants.TENANT_TYPE://商户类型
                configurationTenantType.setMarketId(user.getMarketId());
                configurationTenantType.setManagerFlag(user.getManagerFlag());
                configurationTenantType.setConfigurationKey(Constants.TENANT_TYPE);

                interfaceResult.InterfaceResult200(configurationService.searchConfiguration(configurationTenantType));
                break;
            default:
                interfaceResult.InterfaceResult200(configurationService.searchConfigurationByKey(key));
        }
        return interfaceResult;
    }

    /**
     * @Description: 修改参数
     * @Param: [configuration, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/14
     */
    @RequestMapping(value = "/update")
    @OperationAnnotation(type = "U", title = "修改参数设置")
    public InterfaceResult update(@RequestBody Configuration configuration, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if (configuration.getMarketId() == null) {
            configuration.setMarketId(user.getMarketId());
        }
        if (null != configuration.getUnit() && !configuration.getUnit().isEmpty()) {
            String unit = configuration.getUnit().trim();
            configuration.setUnit(unit);
        }
//        interfaceResult.InterfaceResult200(configurationService.selectByPrimaryKey(id));

        if (configurationService.updateConfiguration(configuration)) {
            interfaceResult.InterfaceResult200(true);
        } else {
            interfaceResult.InterfaceResult600("修改失败");
        }

        return interfaceResult;
    }

    /**
     * @Description: 出场时间管理配置参数   因为需要管理出场管理表所以另外写开   以供初始化调用
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: syz
     * @Date: 2018/10/17
     */
    @RequestMapping(value = "/outMarket/{key}")
    @OperationAnnotation(title = "查询参数详细信息")
    public InterfaceResult getAllOutMarketConf(@PathVariable("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        Map map = new HashMap();
        List<Configuration> configurations = configurationService.searchConfigurationByKey(key);
        OutMarketManage outMarketManage = outMarketManageService.selectByMarket(user.getMarketId());
        map.put("configurations", configurations);
        if (outMarketManage == null) {
            OutMarketManage outMarket = new OutMarketManage();
            outMarket.setOutMarketType("0");
            map.put("outMarketManage", outMarket);
        } else {
            map.put("outMarketManage", outMarketManage);
        }
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }
}
