package com.maxcar.controller.setting;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.app.entity.TenantRes;
import com.maxcar.tenant.app.service.TenantResService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-15 16:08
 * @description: ${description}
 **/
@RestController
@RequestMapping("/api/res")
public class ResourceController extends BaseController {
    @Autowired
    private TenantResService tenantResService;

    @GetMapping("/list")
    @OperationAnnotation(title = "获取所有资源")
    public InterfaceResult getRole(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        List<TenantRes> res=tenantResService.findAll();
        result.InterfaceResult200(res);
        return result;
    }
}
