package com.maxcar.common.controller;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/7/11.
 */
@RestController
public class CommonController {

    Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Value("${OSS_ENDPOINT}")
    private String OSS_ENDPOINT;

    @Value("${OSS_ACCESSKEYID}")
    private String OSS_ACCESSKEYID;

    @Value("${OSS_ACCESSKEYSECRET}")
    private String OSS_ACCESSKEYSECRET;

    @Value("${OSS_OBJECTNAME}")
    private String OSS_OBJECTNAME;

    @Value("${OSS_REGION}")
    private String OSS_REGION;

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    /**
     * @Description: 获取上传凭证
     * @Param: [request]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/8
     */
    @RequestMapping(value={"/ossInfo"})
    public InterfaceResult ossInfo(HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        OssBean ossBean = new OssBean();
        ossBean.setOssObjectName(OSS_OBJECTNAME);
        ossBean.setOssAccesskeyId(OSS_ACCESSKEYID);
        ossBean.setOssAccesskeySecret(OSS_ACCESSKEYSECRET);
        ossBean.setOssEndpoint(OSS_ENDPOINT);
        ossBean.setOssRegion(OSS_REGION);
        interfaceResult.InterfaceResult200(ossBean);
        return interfaceResult;
    }
    /**
     * @Description: 获取省集合
     * @Param: [request]
     * @return: com.maxcar.core.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/16
     */
    @RequestMapping(value={"/province"})
    public InterfaceResult province(HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Province> provinceList = provinceService.getProvince();
        for (Province province:provinceList){
            province.setLabel(province.getName());
            List<City> cities = cityService.getCity(province.getId());
            for (City city:cities){
                city.setLabel(city.getName());
            }
            province.setChildren(cities);
        }
        interfaceResult.InterfaceResult200(provinceList);
        return interfaceResult;
    }

    /**
     * @Description: 根据省获取市
     * @Param: [province, request]
     * @return: com.maxcar.core.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/16
     */
    @RequestMapping(value={"/city/province/{province}"})
    public InterfaceResult city(@PathVariable("province")Integer province,HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(cityService.getCity(province));
        return interfaceResult;
    }
    /**
     * @Description: 根究市获取省
     * @Param: [id, request]
     * @return: com.maxcar.core.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/16
     */
    @RequestMapping(value={"/city/{id}"})
    public InterfaceResult cityid(@PathVariable("id")Integer id,HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.InterfaceResult200(cityService.getCityById(id));
        return interfaceResult;
    }
    class OssBean{

        private String ossEndpoint;
        private String ossAccesskeyId;
        private String ossAccesskeySecret;
        private String ossObjectName;

        public String getOssEndpoint() {
            return ossEndpoint;
        }

        public void setOssEndpoint(String ossEndpoint) {
            this.ossEndpoint = ossEndpoint;
        }

        public String getOssAccesskeyId() {
            return ossAccesskeyId;
        }

        public void setOssAccesskeyId(String ossAccesskeyId) {
            this.ossAccesskeyId = ossAccesskeyId;
        }

        public String getOssAccesskeySecret() {
            return ossAccesskeySecret;
        }

        public void setOssAccesskeySecret(String ossAccesskeySecret) {
            this.ossAccesskeySecret = ossAccesskeySecret;
        }

        public String getOssObjectName() {
            return ossObjectName;
        }

        public void setOssObjectName(String ossObjectName) {
            this.ossObjectName = ossObjectName;
        }

        public String getOssRegion() {
            return ossRegion;
        }

        public void setOssRegion(String ossRegion) {
            this.ossRegion = ossRegion;
        }

        private String ossRegion;
    }
}
