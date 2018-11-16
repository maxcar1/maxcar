package com.maxcar.controller.setting;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.app.bean.RoleResBean;
import com.maxcar.tenant.app.entity.TenantRole;
import com.maxcar.tenant.app.service.StaffRoleService;
import com.maxcar.tenant.app.service.TenantRoleService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author songxuefeng
 * @create 2018-10-12 17:03
 * @description: ${description}
 **/
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {
    @Autowired
    private TenantRoleService tenantRoleService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private StaffRoleService staffRoleService;


    @GetMapping("/list")
    @OperationAnnotation(title = "获取该商户下的所有角色")
    public InterfaceResult getRole(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        List<TenantRole> roles=tenantRoleService.selectRoleByTenantId(staff.getGroupId());
        result.InterfaceResult200(roles);
        return result;
    }

    @GetMapping("/roles")
    @OperationAnnotation(title = "获取该商户下的所有角色 员工数量及市场信息")
    public InterfaceResult getRoleAndMarket(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        Market market = marketService.selectByPrimaryKey(staff.getMarketId());
        List<TenantRole> roles=tenantRoleService.selectRoleByTenantId(staff.getGroupId());
        roles.forEach(role->{
            role.setCount(staffRoleService.selectStaffCountByRole(role.getId()));
        });

        Map<String,Object> map=new HashMap<>();
        map.put("market",market);
        map.put("roles",roles);
        result.InterfaceResult200(map);
        return result;
    }

    @PostMapping("/add")
    @OperationAnnotation(title = "新增角色及权限关联")
    public InterfaceResult addRoleAndRes(@RequestBody TenantRole role,HttpServletRequest request) throws Exception{

        Staff staff = getCurrentStaff(request);
        role.setTenantId(staff.getGroupId());
        role.setId(UuidUtils.generateIdentifier());

        return tenantRoleService.addRoleAndRes(role);
    }

    @GetMapping("/{roleId}")
    @OperationAnnotation(title = "获取该商户角色下所有员工及权限")
    public InterfaceResult getRoleAndStaffAndRes(@PathVariable String roleId,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        TenantRole role = tenantRoleService.getRoleAndStaffAndRes(roleId);
        result.InterfaceResult200(role);
        return result;
    }

    @PostMapping("/resreset")
    @OperationAnnotation(title = "角色与权限关联重置")
    public InterfaceResult roleResRelation(@RequestBody @Valid RoleResBean bean, BindingResult result, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return tenantRoleService.roleResRelation(bean);
    }

    @PostMapping("/update")
    @OperationAnnotation(title = "角色信息更新")
    public InterfaceResult updateRole(@RequestBody TenantRole role, HttpServletRequest request) throws Exception{
        return tenantRoleService.updateRole(role);
    }

    @DeleteMapping("/del/{roleId}")
    @OperationAnnotation(title = "删除角色")
    public InterfaceResult delRole(@PathVariable String roleId, HttpServletRequest request) throws Exception{
        return tenantRoleService.delRole(roleId);
    }
}
