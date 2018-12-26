package com.maxcar.market.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.*;
import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.oss.OSSManageUtil;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.*;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.AreaService;
import com.maxcar.market.service.PropertyContractPayService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class PropertyContractController extends BaseController {

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private PropertyContractPayService propertyContractPayService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UserTenantService userTenantService;

    @RequestMapping("/test")
    public InterfaceResult getPropertyContractAll(@RequestBody @Valid GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request) throws Exception {

        GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse response = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(request);
        String html = HttpClientUtils.sendGet("https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/010/contract/18110114380554311697/maxcar_1541054285584.html");
        if (StringUtils.isNotBlank(html)) {
            // String regex = "\\d{4}[ /-]{1}\\d{2}([ /-]{1}\\d{2})?";^\{(.*)\}+$
            //String regex = "\\{$1$2\\}"; //正则表达式

            String regex = "\\{.*?\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(html);
            JSONArray matchRegexList = new JSONArray();
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            while (m.find()) {
                String str = m.group();
                matchRegexList.add(str.substring(1, str.length() - 1));
            }

            matchRegexList.forEach(x -> {
                System.out.println(x.toString());
            });
            jsonObject.put("contractTradingUrl", "https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/010/contract/18110114380554311697/maxcar_1541054285584.html");
            jsonObject.put("contractTradingName", "ylMarket(2).html");
            jsonObject.put("contractTradingMap", matchRegexList.toJSONString());
            System.out.println(jsonObject.toString());
           /* Map<String, String> data = new HashMap<>(4);
            data.put("name", "全车通");
            data.put("car", "二手车");*/
           /* System.out.println(replaceVar(html, data));*/
        }

        return getInterfaceResult("200", response);
    }

    @GetMapping("/property/getPropertyContractHtml/{propertyContractId}")
    public InterfaceResult getPropertyContractHtml(@PathVariable String propertyContractId, HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        if (null == user || null == user.getMarketId()) {
            return getInterfaceResult("600", "请登录市场账号");
        }

        GetPropertyContractRequest getPropertyContractRequest = new GetPropertyContractRequest();
        getPropertyContractRequest.setPropertyContractId(propertyContractId);
        GetPropertyContractResponse propertyContract = propertyContractService.getPropertyContract(getPropertyContractRequest);

        if (null == propertyContract || null == propertyContract.getPropertyContract()) {
            return getInterfaceResult("600", "合同不存在");
        }

        if (null != propertyContract.getPropertyContract().getRemark() && !propertyContract.getPropertyContract().getRemark().isEmpty()) {
            // 之前生成过合同HTML文件
            return getInterfaceResult("200", propertyContract.getPropertyContract().getRemark());
        }

        Market market = marketService.selectByPrimaryKey(user.getMarketId());

        if (null == market) {
            return getInterfaceResult("600", "市场不存在");
        }

        if (null == market.getContractProperty() || market.getContractProperty().isEmpty()) {
            return getInterfaceResult("600", "请先上传物业合同模板");
        }

        String docPath;
        List<Configuration> pdfPathConfigurations = configurationService.searchConfigurationByKey(Constants.PROPERTY_DOC_URL);
        if (pdfPathConfigurations != null && pdfPathConfigurations.size() == 1) {
            docPath = pdfPathConfigurations.get(0).getConfigurationValue() + "/" + propertyContractId + ".html";

        } else {
            return getInterfaceResult("600", "请配置合同pdf配置路径");
        }

        //docPath = "v:\\pdf\\html\\" + propertyContractId + ".html";
        Map<String, String> params = getPropertyMap(propertyContract);

        String htmlUrl = JSON.parseObject(market.getContractProperty()).get("contractPropertyUrl").toString();
        //String htmlUrl = "https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/010/contract/18103111132664029369/maxcar_1540955606654.html";
        String htmlString = HttpClientUtils.sendGet(htmlUrl);
        String replaceVarString = HtmlTemplateUtil.replaceVar(htmlString, params);

        if (!FileUtilS.insertHtmlFile(replaceVarString, docPath)) {
            return getInterfaceResult("200", "生成HTML文件错误");
        }

        // 上传成功
        FilePojo filePojo = new FilePojo();
        filePojo.setPath(docPath);
        filePojo.setName(propertyContractId + ".html");
        filePojo.setOssPath("html/contract");
        FilePojo filePojo1 = OSSManageUtil.uploadFile(filePojo);

        //保存 合同照片
        UpdatePropertyContractRequest updatePropertyContractRequest = new UpdatePropertyContractRequest();
        updatePropertyContractRequest.setPropertyContractId(propertyContractId);
        updatePropertyContractRequest.setRemark(filePojo1.getOssPath());
        propertyContractService.updatePropertyContract(updatePropertyContractRequest);

        return getInterfaceResult("200", filePojo1.getOssPath());
    }

    /**
     * param:
     * describe: 生成物业合同需要的map
     * create_date:  lxy   2018/10/30  20:22
     **/
    private Map<String, String> getPropertyMap(GetPropertyContractResponse request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#0.00");

        Map<String, String> params = new HashMap<String, String>();
        // 乙方市场ID
        params.put("market", null == request.getPropertyContract().getMarketId()
                ? " " : request.getPropertyContract().getMarketId());
        // 乙方
        params.put("theBuyer", null == request.getUserTenantPack().getTenantName()
                ? " " : request.getUserTenantPack().getTenantName());
        // 法定代表人（乙方） 身份证
        params.put("idCard", null == request.getUserTenantPack().getCorporateIdCard()
                ? " " : request.getUserTenantPack().getCorporateIdCard());
        // 乙方住址
        params.put("address", null == request.getUserTenantPack().getTenantAddres()
                ? " " : request.getUserTenantPack().getTenantAddres());
        // 法定代表人（乙方）
        params.put("corporateName", null == request.getUserTenantPack().getCorporateName()
                ? " " : request.getUserTenantPack().getCorporateName());
        // 乙方负责人联系电话
        params.put("phoneNumber", null == request.getUserTenantPack().getTenantPhone()
                ? " " : request.getUserTenantPack().getTenantPhone());
        //
        //params.put("legalRepresentativeOfBuyer", " ");

        //保证金
        params.put("margin", null == request.getPropertyContract().getMargin()
                ? " " : request.getPropertyContract().getMargin().toString());
        // 备注
        params.put("contractRemark", null == request.getPropertyContract().getContractRemark()
                ? " " : request.getPropertyContract().getContractRemark().toString());

        params.put("table", "");

        Double subtotal = 0.0;
        //  计算 租赁物业各种数据
        List<PropertyContractDetail> list = request.getPropertyContractDetailList();
        if (null != list && !list.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(" <table border=\"1\" cellspacing=\"0\" width=\"100%\" height=\"150\" style=\"margin: 0 auto;text-align: center\">");

            stringBuffer.append(" <thead> <tr><th >物业类型</th><th >区域</th><th >编号</th> <th >数量</th> <th >单价</th><th >优惠后单价</th><th >年费用</th>  <th >期限</th></tr></thead>");

            Configuration configurationTenantType = new Configuration();
            configurationTenantType.setMarketId(request.getPropertyContract().getMarketId());
            configurationTenantType.setConfigurationKey(Magic.PROPERTY_TYPE);
            List<Configuration> propertyType = configurationService.searchConfiguration(configurationTenantType);

            for (PropertyContractDetail propertyContractDetail : list) {
                stringBuffer.append("<tr><td>");
                if (null != propertyType && !propertyType.isEmpty()) {

                    for (Configuration configuration : propertyType) {

                        if ((propertyContractDetail.getContractCategory().toString()).equals(configuration.getConfigurationValue())) {

                            stringBuffer.append(configuration.getConfigurationName());

                        }
                    }
                }

                stringBuffer.append("</td><td>");
                Area area = areaService.getAreaById(propertyContractDetail.getArea());
                stringBuffer.append(null == area ? "" : area.getName());

                stringBuffer.append("</td><td>");
                stringBuffer.append(propertyContractDetail.getAreaName());

                stringBuffer.append("</td><td>");
                stringBuffer.append(df.format(propertyContractDetail.getAreaTotal()));

                stringBuffer.append("</td><td>");
                stringBuffer.append(df.format(propertyContractDetail.getPrice()));

                stringBuffer.append("</td><td>");
                stringBuffer.append(df.format(propertyContractDetail.getPreferentialPrice()));
                stringBuffer.append("</td>");
                //年费用计算 优惠后单价x数量或者面积 X 12 X 4 X1
                // 小计 年费用相加
                // 总计
                Double yearsprice = propertyContractDetail.getPreferentialPrice() * propertyContractDetail.getAreaTotal();
                if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetail.getType())) {
                    // 月付  X12
                    yearsprice = yearsprice * 12;
                } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetail.getType())) {
                    // 季付 X4
                    yearsprice = yearsprice * 4;
                }
                stringBuffer.append("<td>");
                stringBuffer.append(df.format(yearsprice));
                stringBuffer.append("</td>");

                Calendar cal = Calendar.getInstance();

                cal.setTime(propertyContractDetail.getStatusTme());
                LocalDate beforeTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));

                cal.setTime(propertyContractDetail.getEndTime());
                LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));

                subtotal += yearsprice / 365 * getIntervalDays(beforeTime, afterTime);

              /*  params.put("date" + i, sdf.format(propertyContractDetail.getStatusTme()) + "__" + sdf.format(propertyContractDetail.getEndTime()));
                i++;*/
                stringBuffer.append("<td>");
                stringBuffer.append(sdf.format(propertyContractDetail.getStatusTme()));
                stringBuffer.append("__");
                stringBuffer.append(sdf.format(propertyContractDetail.getEndTime()));
                stringBuffer.append("</td>");

                stringBuffer.append("  <tr>");
            }

            stringBuffer.append("   <tr><td style=\"font-weight:600;\">小计</td><td></td><td></td><td></td><td></td><td>");

            stringBuffer.append(df.format(subtotal));

            stringBuffer.append("</td><td></td></tr><tr><td style=\"font-weight:600;\">总计</td><td>人民币</td><td colspan=\"3\">");
            stringBuffer.append(MoneyUtil.toChinese(df.format(subtotal)));

            stringBuffer.append("</td> <td>");
            stringBuffer.append(df.format(subtotal));
            stringBuffer.append("</td><td></td></tr> <tr> <td style=\"height:120px\" >备注</td><td colspan=\"7\">");
            stringBuffer.append(params.get("contractRemark").toString());
            stringBuffer.append(" </td></tr>  </tbody>  </table> ");

            params.put("table", stringBuffer.toString());

        }

        // 小计
        params.put("subtotal", df.format(subtotal));
        // 总计（数字）
        params.put("total", df.format(subtotal));
        // 总计（大写）
        params.put("totalCapitalized", MoneyUtil.toChinese(df.format(subtotal)));

        return params;
    }

    /**
     * param:
     * describe: 生成物业合同照片  弃用
     * create_date:  lxy   2018/10/24  18:44
     **/
    @GetMapping("/property/getPropertyContractImage/{propertyContractId}")
    public InterfaceResult getPropertyContractImage(@PathVariable String propertyContractId, HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);
        if (null == user || null == user.getMarketId()) {
            return getInterfaceResult("600", "请登录市场账号");
        }


        GetPropertyContractRequest getPropertyContractRequest = new GetPropertyContractRequest();
        getPropertyContractRequest.setPropertyContractId(propertyContractId);
        GetPropertyContractResponse propertyContract = propertyContractService.getPropertyContract(getPropertyContractRequest);

        if (null == propertyContract || null == propertyContract.getPropertyContract()) {
            return getInterfaceResult("600", "合同不存在");
        }

        JSONObject json = new JSONObject();
        Market market = marketService.selectByPrimaryKey(user.getMarketId());

        if (null == market) {
            return getInterfaceResult("600", "市场不存在");
        }

        List<String> responsibility = new ArrayList<>(1);

        List<String> supplementary = new ArrayList<>(1);

        String propertyContractPath;

        if (null != market.getResponsibility() && !market.getResponsibility().isEmpty()) {
            JSONObject responsibilityJson = JSON.parseObject(market.getResponsibility());
            responsibility.add(responsibilityJson.getString("responsibilityUrl"));
        }

        if (null != market.getSupplementary() && !market.getSupplementary().isEmpty()) {
            JSONObject supplementaryJson = JSON.parseObject(market.getSupplementary());
            supplementary.add(supplementaryJson.getString("supplementaryUrl"));
        }

        if (null == market.getContractProperty() || market.getContractProperty().isEmpty()) {
            return getInterfaceResult("600", "请先上传合同物业模板");
        }

        propertyContractPath = JSON.parseObject(market.getContractProperty()).get("contractPropertyUrl").toString();

        if (!files(propertyContractPath)) {
            return getInterfaceResult("600", "请检查合同模板是否存在");
        }

        List<String> listImage = new ArrayList<>(8);


        json.put("responsibility", responsibility);
        json.put("supplementary", supplementary);

        if (null != propertyContract.getPropertyContract().getRemark() &&
                !propertyContract.getPropertyContract().getRemark().isEmpty()) {

            String remark = propertyContract.getPropertyContract().getRemark();
            String str[] = remark.split(",");

            listImage = Arrays.asList(str);

            json.put("list", listImage);

            return getInterfaceResult("200", json);
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#0.00");

        Map<String, String> params = new HashMap<String, String>();

        // 乙方市场ID
        params.put("market", null == propertyContract.getPropertyContract().getMarketId()
                ? " " : propertyContract.getPropertyContract().getMarketId());
        // 乙方
        params.put("theBuyer", null == propertyContract.getUserTenantPack().getTenantName()
                ? " " : propertyContract.getUserTenantPack().getTenantName());
        // 法定代表人（乙方） 身份证
        params.put("idCard", null == propertyContract.getUserTenantPack().getCorporateIdCard()
                ? " " : propertyContract.getUserTenantPack().getCorporateIdCard());
        // 乙方住址
        params.put("address", null == propertyContract.getUserTenantPack().getTenantAddres()
                ? " " : propertyContract.getUserTenantPack().getTenantAddres());
        // 法定代表人（乙方）
        params.put("corporateName", null == propertyContract.getUserTenantPack().getCorporateName()
                ? " " : propertyContract.getUserTenantPack().getCorporateName());
        // 乙方负责人联系电话
        params.put("phoneNumber", null == propertyContract.getUserTenantPack().getTenantPhone()
                ? " " : propertyContract.getUserTenantPack().getTenantPhone());
        //
        params.put("legalRepresentativeOfBuyer", " ");

        // 备注
        params.put("note", null == propertyContract.getPropertyContract().getRemark()
                ? " " : propertyContract.getPropertyContract().getRemark());
        //保证金
        params.put("margin", null == propertyContract.getPropertyContract().getMargin()
                ? " " : propertyContract.getPropertyContract().getMargin().toString());
        params.put("contractRemark", null == propertyContract.getPropertyContract().getContractRemark()
                ? " " : propertyContract.getPropertyContract().getContractRemark().toString());


        Double subtotal = 0.0;
        //  计算 租赁物业各种数据
        if (null != propertyContract.getPropertyContractDetailList() && !propertyContract.getPropertyContractDetailList().isEmpty()) {
            List<PropertyContractDetail> list = propertyContract.getPropertyContractDetailList();

            Configuration configurationTenantType = new Configuration();
            configurationTenantType.setMarketId(propertyContract.getPropertyContract().getMarketId());
            configurationTenantType.setConfigurationKey(Magic.PROPERTY_TYPE);
            List<Configuration> propertyType = configurationService.searchConfiguration(configurationTenantType);

            params.put("listSize", String.valueOf(list.size()));

            int i = 1;
            for (PropertyContractDetail propertyContractDetail : list) {
                if (null != propertyType && !propertyType.isEmpty()) {
                    for (Configuration configuration : propertyType) {

                        if ((propertyContractDetail.getContractCategory().toString()).equals(configuration.getConfigurationValue())) {
                            params.put("car" + i, configuration.getConfigurationName());
                        }
                    }
                }
                params.put("number" + i, df.format(propertyContractDetail.getAreaTotal()));
                params.put("no" + i, propertyContractDetail.getAreaName());
                params.put("price" + i, df.format(propertyContractDetail.getPrice()));
                params.put("preferential" + i, df.format(propertyContractDetail.getPreferentialPrice()));
                //年费用计算 优惠后单价x数量或者面积 X 12 X 4 X1
                // 小计 年费用相加
                // 总计
                Double yearsprice = propertyContractDetail.getPreferentialPrice() * propertyContractDetail.getAreaTotal();
                if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetail.getType())) {
                    // 月付  X12
                    yearsprice = yearsprice * 12;
                } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetail.getType())) {
                    // 季付 X4
                    yearsprice = yearsprice * 4;
                }

                params.put("years" + i, df.format(yearsprice));
                Calendar cal = Calendar.getInstance();

                cal.setTime(propertyContractDetail.getStatusTme());
                LocalDate beforeTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));

                cal.setTime(propertyContractDetail.getEndTime());
                LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));

                subtotal += yearsprice / 365 * getIntervalDays(beforeTime, afterTime);

                params.put("date" + i, sdf.format(propertyContractDetail.getStatusTme()) + "__" + sdf.format(propertyContractDetail.getEndTime()));
                i++;
            }
        }

        // 小计
        params.put("subtotal", df.format(subtotal));
        // 总计（数字）
        params.put("total", df.format(subtotal));
        // 总计（大写）
        params.put("totalCapitalized", MoneyUtil.toChinese(df.format(subtotal)));

        String pdfPath;
        List<Configuration> pdfPathConfigurations = configurationService.searchConfigurationByKey(Constants.PROPERTY_PDF_URL);
        if (pdfPathConfigurations != null && pdfPathConfigurations.size() == 1) {
            pdfPath = pdfPathConfigurations.get(0).getConfigurationValue() + "/" + propertyContractId + ".pdf";
        } else {
            return getInterfaceResult("600", "请配置合同pdf配置路径");
        }

        String imagePath;
        List<Configuration> imagePathConfigurations = configurationService.searchConfigurationByKey(Constants.PROPERTY_IMG_URL);
        if (imagePathConfigurations != null && imagePathConfigurations.size() == 1) {
            imagePath = imagePathConfigurations.get(0).getConfigurationValue();
        } else {
            return getInterfaceResult("600", "请配置合同image配置路径");
        }


        listImage = WordToPdfToImageUtils.WordToPdfToImage(propertyContractPath, pdfPath, imagePath, params);
        json.put("list", listImage);
        json.put("responsibility", responsibility);
        json.put("supplementary", supplementary);


        if (null == listImage || listImage.isEmpty()) {
            return getInterfaceResult("200", json);
        }
        StringBuilder stringBuilder = new StringBuilder(128);
        listImage.forEach(x -> {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append(x);
        });

        //保存 合同照片
        UpdatePropertyContractRequest updatePropertyContractRequest = new UpdatePropertyContractRequest();
        updatePropertyContractRequest.setPropertyContractId(propertyContractId);
        updatePropertyContractRequest.setRemark(stringBuilder.toString());

        propertyContractService.updatePropertyContract(updatePropertyContractRequest);

        return getInterfaceResult("200", json);
    }

    /**
     * param:
     * describe: 获取合同列表
     * create_date:  lxy   2018/8/16  16:31
     **/
    @OperationAnnotation(title = "获取合同列表")
    @RequestMapping("/property/getPropertyContractAll")
    public InterfaceResult getPropertyContractAll(@RequestBody @Valid GetPropertyContractAllRequest getPropertyContractAllRequest, BindingResult result,
                                                  HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        getPropertyContractAllRequest.setMarketId(user.getMarketId());


        // 导出合同
        if (getPropertyContractAllRequest.isExport()) {
            List<GetPropertyContractAllPack> getPropertyContractAllPacks = propertyContractService.pageInfoGetPropertyContractAllResponse(getPropertyContractAllRequest);
            return getInterfaceResult("200", getPropertyContractAllPacks);
        }

        PageInfo<GetPropertyContractAllResponse> pageInfo = propertyContractService.getPropertyContractAll(getPropertyContractAllRequest);
        return getInterfaceResult("200", pageInfo);
    }

    /**
     * param:
     * describe: 新增合同
     * create_date:  lxy   2018/8/16  16:30
     **/

    @RequestMapping("/property/addPropertyContract")
    @OperationAnnotation(title = "新增合同")
    public InterfaceResult addPropertyContract(@RequestBody @Valid AddContractRequest addContractRequest, BindingResult result,
                                               HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        addContractRequest.getPropertyContract().setMarketId(user.getMarketId());
        addContractRequest.getPropertyContract().setInsertOperator(user.getUserId());
        addContractRequest.getPropertyContract().setUpdateOperator(user.getUserId());
        addContractRequest.getPropertyContract().setUpdateTime(new Date());

        InterfaceResult interfaceResult = isAddContractRequest(addContractRequest);

        if ("600".equals(interfaceResult.getCode())) {
            return interfaceResult;
        }
        if (null != addContractRequest.getUserTenantPack().getContactMobile() &&
                !addContractRequest.getUserTenantPack().getContactMobile().isEmpty()) {
            List<Staff> staffList = staffService.getStaffByStaffTypeAndPhone(addContractRequest.getUserTenantPack().getContactMobile());
            if (null != staffList && !staffList.isEmpty()) {
                // 判断该手机号的所有车商用户是否都是管理员
                for (Staff x : staffList) {
                    if (x.getIsAdmin() == 0) {
                        // 有账号不是管理员 返回该手机号已经注册过
                        return getInterfaceResult("600", "手机号已注册车商员工,无法创建车商管理员!新增失败");
                    }
                }

              /*  UserTenant userTenantRequest = new UserTenant();

                userTenantRequest.setTenantName(addContractRequest.getUserTenantPack().getTenantName());
                userTenantRequest.setMarketId(addContractRequest.getPropertyContract().getMarketId());

                UserTenant userTenant = userTenantService.selectUserTenant(userTenantRequest);

                if (null == userTenant) {
                    // 判断是否是新增 新增的话 提示不能新增 手机号重复
                    return getInterfaceResult("600", "手机号已存在,无法创建车商管理员!新增失败");
                } else {
                    if (!staff.getGroupId().equals(userTenant.getId())) {
                        //手机号不属于该商户
                        return getInterfaceResult("600", "手机号已存在,无法创建车商管理员!新增失败");
                    }
                }*/
            }
        }

        String propertyContractId = propertyContractService.addPropertyContract(addContractRequest);
        if (null != propertyContractId)

        {
            // 生成合同缴费记录
            propertyContractPayService.addPropertyContractPay();
            return getInterfaceResult("200", propertyContractId);
        }

        return

                getInterfaceResult("600", "添加失败");

    }

    /**
     * param:
     * describe: 终止合同
     * create_date:  lxy   2018/8/16  19:33
     **/
    @OperationAnnotation(title = "终止合同")
    @RequestMapping("/property/endPropertyContract")
    public InterfaceResult endPropertyContract(@RequestBody @Valid UpdatePropertyContractRequest updatePropertyContractRequest, BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        // 入参判断
       /* if (tIsEmpty(updatePropertyContractRequest.getTerminationMessage()) || null == updatePropertyContractRequest.getTerminationTime()) {
            return getInterfaceResult("406", null);
        }*/

        User user = getCurrentUser(request);

        updatePropertyContractRequest.setUpdateOperator(user.getUserId());
        updatePropertyContractRequest.setPropertyContractStatus(Magic.CONTRACT_STATUS_END);

        if (propertyContractService.updatePropertyContract(updatePropertyContractRequest)) {
            return getInterfaceResult("200", null);
        }

        return getInterfaceResult("600", "终止合同失败,请检查合同是否正常或参数是否传入");
    }

    /**
     * param:
     * describe: 修改合同信息  上传相关照片
     * create_date:  lxy   2018/8/17  11:30
     **/
    @OperationAnnotation(title = "修改合同信息  上传相关照片")
    @RequestMapping("/property/updatePropertyContract")
    public InterfaceResult updatePropertyContract(@RequestBody @Valid UpdatePropertyContractRequest updatePropertyContractRequest,
                                                  BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        updatePropertyContractRequest.setUpdateOperator(user.getUserId());
        updatePropertyContractRequest.setPropertyContractStatus(null);

        if (propertyContractService.updatePropertyContract(updatePropertyContractRequest)) {
            return getInterfaceResult("200", null);
        }

        return getInterfaceResult("600", "修改合同失败,请检查合同是否正常");
    }

    /**
     * param:
     * describe: 查看合同详情
     * create_date:  lxy   2018/8/17  14:57
     **/
    @OperationAnnotation(title = "查看合同详情")
    @RequestMapping("/property/getPropertyContract")
    public InterfaceResult getPropertyContract(@RequestBody @Valid GetPropertyContractRequest request, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return getInterfaceResult("200", propertyContractService.getPropertyContract(request));
    }

    /**
     * param:
     * describe: 查看合同缴费列表
     * create_date:  lxy   2018/8/21  14:15
     **/
    @OperationAnnotation(title = "查看合同缴费列表")
    @RequestMapping("/property/getPropertyContractPayAll")
    public InterfaceResult getPropertyContractPayAll(@RequestBody @Valid GetProPerContractPayAllRequest getProPerContractPayAllRequest, BindingResult result,
                                                     HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        getProPerContractPayAllRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", propertyContractPayService.getPropertyContractPayAll(getProPerContractPayAllRequest));
    }

    /**
     * param:
     * describe: 查看合同缴费记录和需要缴费的列表
     * create_date:  lxy   2018/8/21  14:18
     **/
    @OperationAnnotation(title = "查看合同缴费记录和需要缴费的列表")
    @RequestMapping("/property/getAllPayDetail")
    public InterfaceResult getAllPayDetail(@RequestBody @Valid GetAllPayDetailRequest request, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return getInterfaceResult("200", propertyContractPayService.getAllPayDetail(request));
    }

    /**
     * param:
     * describe: 合同缴费
     * create_date:  lxy   2018/8/21  14:34
     **/
    @OperationAnnotation(title = " 合同缴费")
    @RequestMapping("/property/updatePayDetail")
    public InterfaceResult updatePayDetail(@RequestBody @Valid UpdatePayDetailRequest updatePayDetailRequest, BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        updatePayDetailRequest.setUserId(user.getUserId());

        if (propertyContractPayService.updatePayDetail(updatePayDetailRequest)) {
            return getInterfaceResult("200", null);
        } else {
            return getInterfaceResult("600", "缴费失败");
        }
    }


    /**
     * param:
     * describe: 统计合同缴费
     * create_date:  lxy   2018/8/30  18:03
     **/
    @OperationAnnotation(title = " 统计合同缴费")
    @GetMapping("/property/sumContractPayPrice")
    public InterfaceResult sumContractPayPrice(HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);


        SumContractPayPriceResponse response = propertyContractPayService.sumContractPayPrice(user.getMarketId());
        if (null == response) {
            getInterfaceResult("600", "查无数据");
        }
        return getInterfaceResult("200", response);
    }
}

