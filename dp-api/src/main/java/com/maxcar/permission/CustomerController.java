package com.maxcar.permission;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.Customer;
import com.maxcar.user.entity.SearchCar;
import com.maxcar.user.service.CustomerService;
import com.maxcar.user.service.SearchCarService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/market-api/api")
@RestController
public class CustomerController {

    @Autowired
    private SearchCarService searchCarService;

    @RequestMapping(value="/saveOrUpdateSearchCar",method = RequestMethod.POST)
    @OperationAnnotation(title = "新增或修改消费者信息")
    public InterfaceResult saveOrUpdateSearchCar(@RequestBody SearchCar searchCar, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = searchCarService.saveOrUpdateSearchCar(searchCar);
        return interfaceResult;
    }
}
