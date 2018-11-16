package com.maxcar.controller.setting;

import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.MatrixToImageWriter;
import com.maxcar.tenant.app.bean.StaffBindRoleBean;
import com.maxcar.tenant.app.bean.StaffBindTenantBean;
import com.maxcar.tenant.app.bean.StaffRoleCheckBean;
import com.maxcar.tenant.app.entity.StaffCheck;
import com.maxcar.tenant.app.entity.TenantRole;
import com.maxcar.tenant.app.service.StaffCheckService;
import com.maxcar.tenant.app.service.StaffRoleService;
import com.maxcar.tenant.app.service.TenantRoleService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.vo.StaffVo;
import com.maxcar.web.aop.OperationAnnotation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author songxuefeng
 * @create 2018-10-11 17:11
 * @description: ${description}
 **/
@RestController
@RequestMapping("/api/staff")
public class StaffController extends BaseController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRoleService staffRoleService;

    @Autowired
    private StaffCheckService staffCheckService;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private TenantRoleService tenantRoleService;

    @Autowired
    private MarketService marketService;

    @GetMapping ("/info")
    @OperationAnnotation(title = "获取登录员工信息")
    public InterfaceResult getStaff(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        com.maxcar.vo.StaffVo staffVo = new com.maxcar.vo.StaffVo();

        staffVo.setStaffId(staff.getId());
        staffVo.setStaffName(staff.getStaffName());
        staffVo.setTenantName(userTenantService.selectByTenanId(staff.getGroupId()));
        staffVo.setIsAdmin(staff.getIsAdmin());
        staffVo.setPhoneNum(staff.getStaffPhone());
        Market market = marketService.selectByPrimaryKey(staff.getMarketId());
        if (market != null) {
            staffVo.setMarketName(market.getName());
            staffVo.setCarNumber(market.getCarNumber());
            staffVo.setCarManager(market.getCarManageName() == null ? "" : market.getCarManageName());
        }

        if (staffVo.getIsAdmin() == 1) {
            staffVo.setRoleName("管理员");
            List<Staff> staffList = staffService.getStaffByPhone(staff.getStaffPhone(), 2);
            if (staffList.size() > 1) {
                staffVo.setSwitchTenant(1);
            }
        } else {

            String roleId = staffRoleService.getRoleIdByStaffId(staff.getId());
            if (!StringUtils.isBlank(roleId)) {
                TenantRole tenantRole = tenantRoleService.selectByPrimaryKey(roleId);
                staffVo.setRoleName(tenantRole.getRoleName());
            }

            StaffCheck staffCheck = staffCheckService.getStaffCheckByStaffId(staff.getId());
            if (staffCheck == null) {
                staffVo.setChecked(2);
            } else {
                staffVo.setChecked(staffCheck.getState());
            }
        }

        result.InterfaceResult200(staffVo);
        return result;
    }

    @PostMapping("/list")
    @OperationAnnotation(title = "获取某市场某商户下员工列表")
    public InterfaceResult getStafflist(@RequestBody StaffVo staffVo,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff=getCurrentStaff(request);
        staffVo.setMarketId(staff.getMarketId());
        staffVo.setTenantId(staff.getGroupId());
        PageInfo<StaffVo> pageInfo=staffService.getStafflist(staffVo);
        if (staffVo.getCurrentPage()>pageInfo.getLastPage()){
            pageInfo.setList(null);
            result.InterfaceResult200(pageInfo);
            return result;
        }

        for (StaffVo s:pageInfo.getList()){
            //出去登录者本身的信息
            if (s.getId().equals(staff.getId())){
                pageInfo.getList().remove(s);
                pageInfo.setTotal(pageInfo.getTotal()-1);
                break;
            }
        }

        result.InterfaceResult200(pageInfo);
        return result;
    }

    @DeleteMapping("/{staffId}")
    @OperationAnnotation(title = "删除员工")
    public InterfaceResult delStaff(@PathVariable String staffId,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff = staffService.selectByPrimaryId(staffId);
        if (staff!=null){
            //管理员不能被删除
            if (staff.getIsAdmin()==1){
                result.InterfaceResult600("管理员员工不能删除");
            }else {
                //删除员工
                staffService.deleteByPrimaryKey(staffId);
                //删除角色关系
                staffRoleService.deleteByStaffId(staffId);
            }
        }

        return result;
    }

    @PostMapping("/relation")
    @OperationAnnotation(title = "员工关联角色或解除")
    public InterfaceResult staffRelRole(@RequestBody @Valid StaffBindRoleBean bean,BindingResult result,  HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return staffRoleService.staffRelRole(bean);
    }

    @PostMapping("/check/list")
    @OperationAnnotation(title = "员工审批岗位列表")
    public InterfaceResult staffRoleChecklist(@RequestBody StaffVo staffVo, HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        Staff staff=getCurrentStaff(request);
        staffVo.setMarketId(staff.getMarketId());
        staffVo.setTenantId(staff.getGroupId());
        PageInfo<StaffVo> pageInfo =staffService.getCheckStaffRoleList(staffVo);
        if (staffVo.getCurrentPage()>pageInfo.getLastPage()){
            pageInfo.setList(null);
        }
        result.InterfaceResult200(pageInfo);
        return result;
    }

    @PostMapping("/check")
    @OperationAnnotation(title = "员工审批岗位")
    public InterfaceResult staffRoleCheck(@RequestBody @Valid StaffRoleCheckBean bean,BindingResult result, HttpServletRequest request) throws Exception{

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return staffRoleService.staffRoleCheck(bean);
    }

    /**
     * 员工绑定商户
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/bind/tenant")
    public InterfaceResult bindTenant(@RequestBody @Valid StaffBindTenantBean bean, BindingResult result, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        if (bean.getStaffName().length() > 10) {
            return getInterfaceResult("600", "姓名过长");
        }

        staffCheckService.insert(bean, getStaffId(request));
        return interfaceResult;
    }

    /**
     * 生成商户二维码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/tenant/buildQRCode")
    @OperationAnnotation(title = "商户生成二维码")
    public void buildQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int width = 400;    //二维码图片的宽
        int height = 400;   //二维码图片的高
        String format = "png";  //二维码图片的格式
        ServletOutputStream stream = null;

        Staff staff = getCurrentStaff(request);
        UserTenant userTenant = userTenantService.selectByPrimaryKey(staff.getGroupId());

        Map<EncodeHintType, Object> hints = new HashMap<>(2);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userTenant.getId());
        jsonObject.put("no", userTenant.getTenantNo());
        jsonObject.put("name", userTenant.getTenantName());
        jsonObject.put("phone", userTenant.getContactMobile());
        jsonObject.put("address", (null == userTenant.getTenantAddress()) ? "空地址" : userTenant.getTenantAddress());
        jsonObject.put("type", userTenant.getTenantType());
        stream = response.getOutputStream();
        BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonObject.toString(), BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);

        if (stream != null) {
            stream.flush();
            stream.close();
        }
    }

}
