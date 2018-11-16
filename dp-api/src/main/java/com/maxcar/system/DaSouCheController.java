package com.maxcar.system;

import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/market-api/api")
@RestController
public class DaSouCheController {

    @Autowired
    private DaSouCheService daSouCheService;

    @Autowired
    private CityService cityService;


    /**
     * 查询所有品牌车系
     *
     * @return
     */
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public InterfaceResult listBrands() {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarBrand> list = daSouCheService.getAllBrand();
            String[] str = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R"
                    ,"S","T","U","V","W","X","Y","Z"};
            List<Map> lists = new ArrayList<Map>();
            for(int i=0;i<str.length;i++){
                Map<String,Object> map = new HashMap<String, Object>();
                List l = null;
                for(CarBrand c:list){
                    if(c.getCarIndex().equals(str[i])){
                        String index = (String)map.get("index");
                        if (null == index || !StringUtils.equals(index,c.getCarIndex())){
                            l = new ArrayList();
                        }
                        map.put("index",c.getCarIndex());
                        l.add(c);
                        map.put("list",l);
                    }
                }
                lists.add(map);
            }

            List<Map> l = new ArrayList<Map>();
            for(Map map:lists){
                if(!map.isEmpty()){
                    l.add(map);
                }
            }

            return isNULL(l);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 通过车系查车型
     *
     * @param seriesId
     * @return
     */
    @RequestMapping(value = "/series/{seriesId}/models", method = RequestMethod.GET)
    public InterfaceResult listModelBySeries(@PathVariable("seriesId") String seriesId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarModel> list = daSouCheService.getAllModel(seriesId);
            return isNULL(list);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 根据品牌查询所有车系
     *
     * @return
     */
    @RequestMapping(value = "/brands/{scBrandId}/series", method = RequestMethod.GET)
    public InterfaceResult listSeriesByBrand(@PathVariable("scBrandId") String scBrandId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            CarBrand carBrand = daSouCheService.getCarBrand(scBrandId);
            if(carBrand == null){
                interfaceResult.InterfaceResult600("该品牌不存在");
            }
            List<CarSeries> carList = daSouCheService.getAllSeries(carBrand.getId());
            return isNULL(carList);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 判断是查出的数据是否为空
     *
     * @param object
     * @return
     */
    public InterfaceResult isNULL(Object object) {
        InterfaceResult interfaceResult = new InterfaceResult();
        if (null == object || "".equals(object)) {
            interfaceResult.InterfaceResult600("无查询记录");
        } else {
            interfaceResult.InterfaceResult200(object);
        }
        return interfaceResult;
    }
}
