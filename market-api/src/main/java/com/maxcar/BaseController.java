package com.maxcar;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.market.model.request.AddContractRequest;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserRoleService;
import com.maxcar.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

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
    private SsoService ssoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private StaffService staffService;

    // double 保留两位小数
    public DecimalFormat df = new DecimalFormat("######0.00");
    public DecimalFormat df4 = new DecimalFormat("######0.0000");

    @Value("${kafka.producer.topic}")
    public String producerTopic;
    @Value("${kafka.consumer.topic006}")
    public String consumerTopic6;
    @Value("${kafka.consumer.topic007}")
    public String consumerTopic7;
    @Value("${kafka.consumer.topic008}")
    public String consumerTopic8;
    @Value("${kafka.consumer.topic009}")
    public String consumerTopic9;
    @Value("${kafka.consumer.topic010}")
    public String consumerTopic10;
    @Value("${kafka.consumer.topic011}")
    public String consumerTopic11;
    @Value("${kafka.consumer.topic012}")
    public String consumerTopic12;
    @Value("${kafka.consumer.topic013}")
    public String consumerTopic13;
    @Value("${kafka.consumer.topic014}")
    public String consumerTopic14;
    @Value("${kafka.consumer.topic015}")
    public String consumerTopic15;
    @Value("${kafka.consumer.topic016}")
    public String consumerTopic16;
    @Value("${kafka.consumer.topic017}")
    public String consumerTopic17;
    @Value("${kafka.consumer.topic018}")
    public String consumerTopic18;
    @Value("${kafka.consumer.topic019}")
    public String consumerTopic19;
    @Value("${kafka.consumer.topic020}")
    public String consumerTopic20;
    @Value("${kafka.consumer.topic021}")
    public String consumerTopic21;
    @Value("${kafka.consumer.topic022}")
    public String consumerTopic22;
    @Value("${kafka.consumer.topic023}")
    public String consumerTopic23;
    @Value("${kafka.consumer.topic024}")
    public String consumerTopic24;
    @Value("${kafka.consumer.topic025}")
    public String consumerTopic25;

    @Value("${kafka.consumer.topic026}")
    public String consumerTopic26;
    @Value("${kafka.consumer.topic027}")
    public String consumerTopic27;
    @Value("${kafka.consumer.topic028}")
    public String consumerTopic28;
    @Value("${kafka.consumer.topic029}")
    public String consumerTopic29;
    @Value("${kafka.consumer.topic030}")
    public String consumerTopic30;
    @Value("${kafka.consumer.topic031}")
    public String consumerTopic31;
    @Value("${kafka.consumer.topic032}")
    public String consumerTopic32;
    @Value("${kafka.consumer.topic033}")
    public String consumerTopic33;
    @Value("${kafka.consumer.topic034}")
    public String consumerTopic34;
    @Value("${kafka.consumer.topic035}")
    public String consumerTopic35;
    @Value("${kafka.consumer.topic036}")
    public String consumerTopic36;
    @Value("${kafka.consumer.topic037}")
    public String consumerTopic37;
    @Value("${kafka.consumer.topic038}")
    public String consumerTopic38;
    @Value("${kafka.consumer.topic039}")
    public String consumerTopic39;
    @Value("${kafka.consumer.topic040}")
    public String consumerTopic40;
    @Value("${kafka.consumer.topic041}")
    public String consumerTopic41;
    @Value("${kafka.consumer.topic042}")
    public String consumerTopic42;
    @Value("${kafka.consumer.topic043}")
    public String consumerTopic43;
    @Value("${kafka.consumer.topic044}")
    public String consumerTopic44;
    @Value("${kafka.consumer.topic045}")
    public String consumerTopic45;
    @Value("${kafka.consumer.topic046}")
    public String consumerTopic46;
    @Value("${kafka.consumer.topic047}")
    public String consumerTopic47;
    @Value("${kafka.consumer.topic048}")
    public String consumerTopic48;
    @Value("${kafka.consumer.topic049}")
    public String consumerTopic49;
    @Value("${kafka.consumer.topic050}")
    public String consumerTopic50;
    @Value("${kafka.consumer.topic051}")
    public String consumerTopic51;
    @Value("${kafka.consumer.topic052}")
    public String consumerTopic52;
    @Value("${kafka.consumer.topic053}")
    public String consumerTopic53;
    @Value("${kafka.consumer.topic054}")
    public String consumerTopic54;
    @Value("${kafka.consumer.topic055}")
    public String consumerTopic55;


    /**
     * @Description：获取cookie中的token
     * @Author:罗顺锋
     * @Create:2017/3/29 11:17
     */
    public User getCurrentUser(HttpServletRequest request) throws Exception {
        User user = null;
        //第二步从头部获取token信息，验证用户
        String userToken = request.getHeader("userToken");
        if (StringUtils.isNotEmpty(userToken)) {
            InterfaceResult result = ssoService.getUserByToken(userToken);
            if (result != null && result.getCode().equals("200")) {
                String res = result.getData().toString();
                String userId = null;
                userId = res;
                if (null == userId || userId.isEmpty()) {
                    logger.error("userToken===" + userToken);
                    logger.error("result===" + result.toString());
                }
                user = userService.selectByPrimaryKey(userId);
                if (null == user) {
                    logger.error("user:null");
                }
                //根据登录的员工id查询员工姓名

                List<Staff> staffList = staffService.selectByStaffId(user.getStaffId());
//                            trueUser.setStaffName(staffList.get(0).getStaffName());
                if (null != staffList && !staffList.isEmpty()) {
                    user.setStaffName(staffList.get(0).getStaffName());
                }
                user.setRoles(userRoleService.selectRoleByUserId(userId));

            }
        }
        return user;
    }

    /**
     * param:
     * describe: 判断是否是管理员账户
     * create_date:  lxy   2018/11/22  17:19
     **/
    public boolean isManagerFlag(HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getManagerFlag())) {
            return false;
        }

        return 0 == user.getManagerFlag();
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

        if (t instanceof Double) {
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


    /**
     * param:
     * describe: 新增合同入参判断 是否不合法
     * create_date:  lxy   2018/10/18  11:27
     **/
    public InterfaceResult isAddContractRequest(AddContractRequest request) {
        // 商户数据不存在 参数不合法
        if (null == request.getUserTenantPack() || null == request.getPropertyContract()) {
            return getInterfaceResult("600", "请填写合同信息和商户信息");
        }
        // 商户名称和商户类型必传
        if (tIsEmpty(request.getUserTenantPack().getTenantName()) || tIsEmpty(request.getUserTenantPack().getTenantType())) {
            return getInterfaceResult("600", "请填写商户名称和商户类型");
        }

        // 根据类型判断参数 1  经纪人2 经纪公司  3 经销公司4 新车经销商  5 代理人 6 餐饮店  7 金融公司 8 其他
        // 1 2 3 4 6 需要物业  需要缴费(强制)  // 查看是否提交了物业信息
        if (Magic.TENANT_TYPE_1.equals(request.getUserTenantPack().getTenantType()) || Magic.TENANT_TYPE_2.equals(request.getUserTenantPack().getTenantType()) ||
                Magic.TENANT_TYPE_3.equals(request.getUserTenantPack().getTenantType()) || Magic.TENANT_TYPE_4.equals(request.getUserTenantPack().getTenantType()) ||
                Magic.TENANT_TYPE_6.equals(request.getUserTenantPack().getTenantType())) {

            if (tIsEmpty(request.getPropertyContractDetailList())) {
                return getInterfaceResult("600", "请填写物业类型");
            }
        }

        return getInterfaceResult("200", "请填写物业类型");
    }

    /**
     * param:
     * describe: 计算间隔天数
     * create_date:  lxy   2018/10/25  14:51
     **/
    public static int getIntervalDays(LocalDate beforeTime, LocalDate afterTime) {
        // Period p = Period.between(beforeTime, afterTime);
        //  System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        return (int) (afterTime.toEpochDay() - beforeTime.toEpochDay());
    }

    public static int getIntervalYears(LocalDate beforeTime, LocalDate afterTime) {
        // Period p = Period.between(beforeTime, afterTime);
        //  System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        int intervalDays = getIntervalDays(beforeTime, afterTime);

        return intervalDays / 360;
    }

    public static void main(String[] args) throws Exception {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate beforeTime = LocalDate.now();

        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse("2019-12-5"));
        LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));
        System.out.println(afterTime);
        System.out.println(getIntervalYears(beforeTime, afterTime));*/
      /*  DecimalFormat df4 = new DecimalFormat("######0.0000");
        Double x = null;
        double y = null;
        System.out.println(x/y);
        System.out.println(df4.format(5009/10000));*/
    }


    public String getTopic(String marketId) {
        String topic = consumerTopic6;
        switch (marketId) {
            case "006":
                topic = consumerTopic6;
                break;
            case "007":
                topic = consumerTopic7;
                break;
            case "008":
                topic = consumerTopic8;
                break;
            case "009":
                topic = consumerTopic9;
                break;
            case "010":
                topic = consumerTopic10;
                break;
            case "011":
                topic = consumerTopic11;
                break;
            case "012":
                topic = consumerTopic12;
                break;
            case "013":
                topic = consumerTopic13;
                break;
            case "014":
                topic = consumerTopic14;
                break;
            case "015":
                topic = consumerTopic15;
                break;
            case "016":
                topic = consumerTopic16;
                break;
            case "017":
                topic = consumerTopic17;
                break;
            case "018":
                topic = consumerTopic18;
                break;
            case "019":
                topic = consumerTopic19;
                break;
            case "020":
                topic = consumerTopic20;
                break;
            case "021":
                topic = consumerTopic21;
                break;
            case "022":
                topic = consumerTopic22;
                break;
            case "023":
                topic = consumerTopic23;
                break;
            case "024":
                topic = consumerTopic24;
                break;
            case "025":
                topic = consumerTopic25;
                break;
            case "026":
                topic = consumerTopic26;
                break;
            case "027":
                topic = consumerTopic27;
                break;
            case "028":
                topic = consumerTopic28;
                break;
            case "029":
                topic = consumerTopic29;
                break;
            case "030":
                topic = consumerTopic30;
                break;
            case "031":
                topic = consumerTopic31;
                break;
            case "032":
                topic = consumerTopic32;
                break;
            case "033":
                topic = consumerTopic33;
                break;
            case "034":
                topic = consumerTopic34;
                break;
            case "035":
                topic = consumerTopic35;
                break;
            case "036":
                topic = consumerTopic36;
                break;
            case "037":
                topic = consumerTopic37;
                break;
            case "038":
                topic = consumerTopic38;
                break;
            case "039":
                topic = consumerTopic39;
                break;
            case "040":
                topic = consumerTopic40;
                break;
            case "041":
                topic = consumerTopic41;
                break;
            case "042":
                topic = consumerTopic42;
                break;
            case "043":
                topic = consumerTopic43;
                break;
            case "044":
                topic = consumerTopic44;
                break;
            case "045":
                topic = consumerTopic45;
                break;
            case "046":
                topic = consumerTopic46;
                break;
            case "047":
                topic = consumerTopic47;
                break;
            case "048":
                topic = consumerTopic48;
                break;
            case "049":
                topic = consumerTopic49;
                break;
            case "050":
                topic = consumerTopic50;
                break;
            case "051":
                topic = consumerTopic51;
                break;
            case "052":
                topic = consumerTopic52;
                break;
            case "053":
                topic = consumerTopic53;
                break;
            case "054":
                topic = consumerTopic54;
                break;
            case "055":
                topic = consumerTopic55;
                break;

        }
        return topic;
    }


    /**
     * lxy
     * 判断文件是否存在  true 存在  false 不存在
     *
     * @param pathurl
     * @return
     */
    public static boolean files(String pathurl) {
        File file = new File(pathurl);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
