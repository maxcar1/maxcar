package com.maxcar.web;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.JsonTools;
import com.maxcar.constant.Constants;
import com.maxcar.redis.service.RedisService;
import com.maxcar.redis.util.CacheKey;
import com.maxcar.tenant.app.entity.TenantRes;
import com.maxcar.tenant.app.service.StaffLoginService;
import com.maxcar.tenant.app.service.StaffRoleService;
import com.maxcar.tenant.app.service.TenantResService;
import com.maxcar.tenant.app.service.TenantRoleResService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.List;

/**
 * 罗顺锋
 * 拦截器实例,方法调用前中后
 */
@Component
public class SystemInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);

    @Autowired
    private StaffLoginService staffLoginService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private TenantResService tenantResService;

    @Autowired
    private StaffRoleService staffRoleService;

    @Autowired
    private TenantRoleResService tenantRoleResService;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        logger.info("用户认证方法调用之前");
        InterfaceResult interfaceResult = new InterfaceResult();

        //设置返回中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String staffToken = request.getHeader(Constants.STAFF_TOKEN);
        if (StringUtils.isNotEmpty(staffToken)) {

            String mark = redisService.get(MessageFormat.format(CacheKey.STAFF_TOKEN_MARK, staffToken));
            if (StringUtils.isNotBlank(mark)) {
                interfaceResult.setCode("60002");
                interfaceResult.setMsg("您的账号在其他设备上登录。");
                response.getWriter().write(JsonTools.toJson(interfaceResult));
                return false;
            }

            InterfaceResult result = staffLoginService.getStaffIdByToken(staffToken);
            if (!result.getCode().equals("200")) {
                response.getWriter().write(JsonTools.toJson(result));
                return false;
            }

            String staffId = result.getData().toString();
            Staff staff = staffService.selectByPrimaryId(staffId);
            if (staff != null) {

                if (staff.getIsAdmin() == 1) {
                    return true;
                }

                String uri = request.getRequestURI();
                // 未审批通过的员工
                if (staff.getGroupId() == null && !uri.equals("/api/staff/bind/tenant") && !uri.equals("/api/staff/info")) {
                    interfaceResult.InterfaceResult401();
                    response.getWriter().write(JsonTools.toJson(interfaceResult));
                    return false;
                }

                // 控制资源请求权限
                boolean flag = false;
                List<TenantRes> tenantResList = tenantResService.findAll();
                for (TenantRes res : tenantResList) {
                    if (uri.startsWith(res.getResUrl())) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    return true;
                }

                String roleId = staffRoleService.getRoleIdByStaffId(staffId);
                if (StringUtils.isNotBlank(roleId)) {
                    List<String> list = tenantRoleResService.getResIdListByRoleId(roleId);
                    for (String resId : list) {
                        String resUrl = getResUrl(resId, tenantResList);
                        if (uri.startsWith(resUrl)) {
                            return true;
                        }
                    }
                }
            } else {
                interfaceResult.setCode("60001");
                interfaceResult.setMsg("账号被删除");
                response.getWriter().write(JsonTools.toJson(interfaceResult));
                return false;
            }
        }
        interfaceResult.InterfaceResult401();
        response.getWriter().write(JsonTools.toJson(interfaceResult));
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("用户认证方法调用之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        logger.info("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        logger.info("用户认证方法执行视图渲染完成以后");
    }

    private String getResUrl(String resId, List<TenantRes> list) {
        for (TenantRes res : list) {
            if (res.getId().equals(resId)) {
                return res.getResUrl();
            }
        }
        return null;
    }

}