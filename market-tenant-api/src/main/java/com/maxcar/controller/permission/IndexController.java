package com.maxcar.controller.permission;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.constant.Constants;
import com.maxcar.redis.service.RedisService;
import com.maxcar.redis.util.CacheKey;
import com.maxcar.tenant.app.bean.StaffLoginBean;
import com.maxcar.tenant.app.bean.SwitchTenantBean;
import com.maxcar.tenant.app.entity.TenantRes;
import com.maxcar.tenant.app.entity.TenantRole;
import com.maxcar.tenant.app.service.StaffLoginService;
import com.maxcar.tenant.app.service.StaffRoleService;
import com.maxcar.tenant.app.service.TenantResService;
import com.maxcar.tenant.app.service.TenantRoleResService;
import com.maxcar.tenant.app.service.TenantRoleService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.vo.MarketTenantRoleVo;
import com.maxcar.vo.MarketVo;
import com.maxcar.vo.RoleVo;
import com.maxcar.vo.TenantResVo;
import com.maxcar.vo.TenantVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class IndexController extends BaseController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private StaffLoginService staffLoginService;

    @Autowired
    private StaffRoleService staffRoleService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private TenantRoleService tenantRoleService;

    @Autowired
    private TenantRoleResService tenantRoleResService;

    @Autowired
    private TenantResService tenantResService;

    /**ve
     * ios审核版本
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/version")
    public InterfaceResult version(HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<>(2);
        map.put("pt", 0);
        String version = request.getHeader("version");
        if ("1.0".equals(version)) {
            map.put("pt", 0);
        }
        return getInterfaceResult("200", map);
    }

    /**
     * 登录
     *
     * @param staffLoginBean
     * @param result
     * @return
     */
    @PostMapping(value = "/login")
    public InterfaceResult login(@RequestBody @Valid StaffLoginBean staffLoginBean, BindingResult result) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {

            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    return getInterfaceResult("600", error.getDefaultMessage());
                }
            }

            // 苹果审核账号
            String phoneNum = staffLoginBean.getPhoneNum();
            if (("18669981100".equals(phoneNum) || "18669981122".equals(phoneNum))
                    && "1256".equals(staffLoginBean.getVcode())) {
                return staffLoginService.login(phoneNum);
            }

            String cacheCode = redisService.get(MessageFormat.format(CacheKey.LOGIN_PHONE_CODE, staffLoginBean.getPhoneNum()));

            if (StringUtils.isBlank(cacheCode)) {
                interfaceResult.InterfaceResult600("验证码过期，请重新获取");
                return interfaceResult;
            }

            if (!staffLoginBean.getVcode().equals(cacheCode)) {
                interfaceResult.InterfaceResult600("请输入正确验证码");
                return interfaceResult;
            }

            return staffLoginService.login(staffLoginBean.getPhoneNum());
        } catch (Exception ex) {
            logger.error("staff login error. phoneNum:{} error:", staffLoginBean.getPhoneNum(), ex);
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    /**
     * 首页
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/index")
    public InterfaceResult index(HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        List<TenantResVo> resVos = new ArrayList<>();

        Staff staff = getCurrentStaff(request);
        if (staff.getIsAdmin() == 1) {
            List<TenantRes> list = tenantResService.findAll();
            for (TenantRes tenantRes : list) {
                TenantResVo tenantResVo = new TenantResVo();
                BeanUtils.copyProperties(tenantRes, tenantResVo);
                resVos.add(tenantResVo);
            }

        } else {
            String roleId = staffRoleService.getRoleIdByStaffId(staff.getId());
            List<String> resIds = tenantRoleResService.getResIdListByRoleId(roleId);
            for (String resId : resIds) {
                TenantRes tenantRes = tenantResService.getTenantResById(resId);
                TenantResVo tenantResVo = new TenantResVo();
                BeanUtils.copyProperties(tenantRes, tenantResVo);
                resVos.add(tenantResVo);
            }
        }

        List<TenantResVo> collect = resVos.stream().sorted(Comparator.comparing(TenantResVo::getId)).collect(Collectors.toList());
        interfaceResult.InterfaceResult200(collect);
        return interfaceResult;
    }

    /**
     * 切换商户
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/switch/tenant")
    public InterfaceResult switchTenant(@RequestBody @Valid SwitchTenantBean bean, BindingResult result, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        String token = request.getHeader(Constants.STAFF_TOKEN);
        String staffId = redisService.get(MessageFormat.format(CacheKey.STAFF_TOKEN_UID, token));
        if (StringUtils.isBlank(staffId)) {

            String phone = redisService.get(MessageFormat.format(CacheKey.STAFF_TOKEN_PHONE, token));
            if (StringUtils.isBlank(phone)) {
                interfaceResult.InterfaceResult600("请重新登录");
                return interfaceResult;
            }

            Staff staff = staffService.getStaffByPhoneTenant(phone, bean.getTenantId());
            if (staff == null) {
                interfaceResult.InterfaceResult600("该用户对应的商户不存在");
                return interfaceResult;
            }
            cacheToken(token, staff.getId(), phone);
        } else {

            Staff staff = staffService.selectByPrimaryId(staffId);
            Staff staff1 = staffService.getStaffByPhoneTenant(staff.getStaffPhone(), bean.getTenantId());
            cacheToken(token, staff1.getId(), staff.getStaffPhone());
        }

        return interfaceResult;
    }

    private void cacheToken(String token, String staffId, String phoneNum) {
        String staffToken = redisService.get(MessageFormat.format(CacheKey.PHONE_STAFF_TOKEN, phoneNum));
        if (StringUtils.isNotBlank(staffToken) && !staffToken.equals(token)) {
            redisService.del(MessageFormat.format(CacheKey.STAFF_TOKEN_UID, staffToken));
            redisService.set(MessageFormat.format(CacheKey.STAFF_TOKEN_MARK, staffToken), "1", com.maxcar.base.util.Constants.STAFF_TOKEN_TIMEOUT);
        }

        redisService.set(MessageFormat.format(CacheKey.PHONE_STAFF_TOKEN, phoneNum), token);
        redisService.set(MessageFormat.format(CacheKey.STAFF_TOKEN_UID, token), staffId, com.maxcar.base.util.Constants.STAFF_TOKEN_TIMEOUT);
    }

    /**
     * 根据管理员手机查询市场商户岗位信息
     *
     * @param phoneNum 手机号码
     * @return
     */
    @GetMapping(value = "/market/tenant/role")
    public InterfaceResult getMarketTenant(String phoneNum) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        List<Staff> list = staffService.getTenantAdminByPhone(phoneNum);

        if (CollectionUtils.isEmpty(list)) {
            interfaceResult.InterfaceResult600("系统内无该管理员，请核实后重新输入。");
            return interfaceResult;
        }

        Set<String> marketIds = list.parallelStream().map(Staff::getMarketId).collect(Collectors.toSet());
        MarketTenantRoleVo marketTenantRoleVo = new MarketTenantRoleVo();
        List<MarketVo> marketVos = new ArrayList<>();
        for (String marketId : marketIds) {
            MarketVo marketVo = new MarketVo();
            Market market = marketService.getMarketById(marketId);
            marketVo.setMarketId(marketId);
            marketVo.setMarketName(market.getName());

            List<TenantVo> tenantVos = new ArrayList<>();
            for (Staff staff : list) {
                if (marketId.equals(staff.getMarketId())) {
                    TenantVo tenantVo = new TenantVo();
                    String tenantName = userTenantService.selectByTenanId(staff.getGroupId());
                    tenantVo.setTenantName(tenantName);
                    tenantVo.setTenantId(staff.getGroupId());

                    // 查询商户岗位
                    List<TenantRole> roleList = tenantRoleService.getRoleListByTenant(staff.getGroupId());
                    List<RoleVo> roleVos = new ArrayList<>();
                    for (TenantRole role : roleList) {
                        RoleVo roleVo = new RoleVo();
                        roleVo.setRoleId(role.getId());
                        roleVo.setRoleName(role.getRoleName());
                        roleVos.add(roleVo);
                    }
                    tenantVo.setRoleVoList(roleVos);
                    tenantVos.add(tenantVo);
                }
            }

            marketVo.setTenantVoList(tenantVos);
            marketVos.add(marketVo);
        }

        marketTenantRoleVo.setMarketVoList(marketVos);
        interfaceResult.InterfaceResult200(marketTenantRoleVo);
        return interfaceResult;
    }

}