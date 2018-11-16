package com.maxcar;

import com.maxcar.constant.Constants;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.app.service.StaffLoginService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 通用控制器，获取用户等
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/4/26
 */
@RequestMapping("/api")
public class BaseController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserService userService;

    @Autowired
    private StaffLoginService staffLoginService;
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Staff getCurrentStaff(HttpServletRequest request) throws Exception {
        return staffService.selectByPrimaryId(getStaffId(request));
    }

    /**
     * 获取当前登录 staffId
     *
     * @param request
     * @return
     * @throws Exception
     */
    public String getStaffId(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Constants.STAFF_TOKEN);
        InterfaceResult result = staffLoginService.getStaffIdByToken(token);
        return result.getData().toString();
    }

    /**
     * @Description: 获取当前网络ip
     * @Param: [request]
     * @return: java.lang.String
     * @Author: 罗顺锋
     * @Date: 2018/4/28
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * param:
     * describe: 封装返回参数
     * create_date:  lxy   2018/8/16  12:06
     **/
    public InterfaceResult getInterfaceResult(String code, Object data) {
        InterfaceResult interfaceResult = new InterfaceResult();
        if ("200".equals(code)) {
            interfaceResult.InterfaceResult200(data);
        } else if ("406".equals(code)) {
            // 参数格式错误
            interfaceResult.InterfaceResult406();
        } else if ("600".equals(code)) {
            //自定义异常
            interfaceResult.InterfaceResult600(data.toString());
        } else {
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }


    /**
     * param:
     * describe: 数据不为null
     * create_date:  lxy   2018/8/17  9:39
     **/
    public static final <T> boolean tIsNotEmpty(T t) {
        return !tIsEmpty(t);
    }


    /**
     * param:
     * describe: 数据为null
     * create_date:  lxy   2018/8/17  9:39
     **/
    public static final <T> boolean tIsEmpty(T t) {
        if (null == t) {
            return true;
        }

        if (t instanceof Number) {
            return false;
        }

        if (t instanceof String) {
            return ((String) t).trim().isEmpty();
        }

        if (t instanceof Collection) {
            return ((Collection) t).isEmpty();
        }

        if (t.getClass().isArray()) {
            return Arrays.stream((Object[]) t).filter(x -> null != x).count() == 0L;
        }

        if (t instanceof Map) {
            return ((Map) t).isEmpty();
        }

        return true;
    }


}
