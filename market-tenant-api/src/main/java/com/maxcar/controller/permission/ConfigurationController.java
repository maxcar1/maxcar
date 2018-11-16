package com.maxcar.controller.permission;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
* @Description: 参数设置接口
* @Param:
* @return:
* @Author: 罗顺锋
* @Date: 2018/5/14
*/
@RestController
@RequestMapping("/api/conf")
public class ConfigurationController extends BaseController {
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private CarBaseService carBaseService;

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
        InterfaceResult interfaceResult = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        switch (key) {//车型
            case Constants.CAR_LEVEL:
                interfaceResult.InterfaceResult200(carBaseService.carLevelGroup(staff.getMarketId(),key));
                break;
            case Constants.ENVIRONMENTAL_STANDARDS://国标
                interfaceResult.InterfaceResult200(carBaseService.carLevelGroup(staff.getMarketId(),key));
                break;

            default:
                interfaceResult.InterfaceResult200(configurationService.searchConfigurationByKey(key));
        }
        return interfaceResult;
    }

}
