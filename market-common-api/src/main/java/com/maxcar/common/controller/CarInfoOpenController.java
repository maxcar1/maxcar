package com.maxcar.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.model.ChooseInfoRequest;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * openApi对外调用
 * yangsj
 */
@RestController
@RequestMapping("/api")
public class CarInfoOpenController {
    @Autowired
    private DaSouCheService daSouCheService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarBaseService carBaseService;

    @PostMapping("/carlist")
    public InterfaceResult getcarList(@RequestBody CarVo carVo){
        InterfaceResult result = new InterfaceResult();
//        carBaseVo.setIsPublish(0);//发布
        PageInfo<CarVo> pageInfo=carService.getCarList2(carVo);
        result.InterfaceResult200(pageInfo);
        return result;
    }

    /**
     * 根据vin调用车辆信息
     * @param vin
     * @return
     */
    @GetMapping(value = "/carinfo/{vin}")
    public Object getCarListByVin(@PathVariable("vin") String vin) {
        InterfaceResult result = new InterfaceResult();
        if (StringUtils.isNotBlank(vin)){
//            List<Map<String,Object>> carBase = null;
            CarBase carBase = null;
            try {
                carBase = carBaseService.getCarBaseByVin(vin);
                if (null != carBase){
                    result.InterfaceResult200(carBase);
                }else{
                    result.InterfaceResult600("没有车架号["+vin+"]的车辆信息!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.InterfaceResult600("服务器发生错误");
            }
        }else{
            result.InterfaceResult600("参数不能为null");
        }
        return result;
    }

    /**
     * 根据品牌取车系
     * @param brand
     * @return
     */
    @GetMapping(value = "/series/{brand}")
    public InterfaceResult listSeriesByBrand(@PathVariable("brand") String brand) {
        InterfaceResult result = new InterfaceResult();
        if (StringUtils.isNotBlank(brand)){
            List<Map<String,Object>> resultList = null;
            try {
                resultList = daSouCheService.getCarSeriesByBrandCode(brand);
                result = checkResult(resultList,result);
            } catch (Exception e) {
                e.printStackTrace();
                result.InterfaceResult600("服务器发生错误");
            }
        }else {
            result.InterfaceResult600("参数不能为null");
        }
        return result;
    }

    /**
     * 根据车系取车型
     * @param series
     * @return
     */
    @GetMapping(value = "/model/{series}")
    public InterfaceResult listModelBySeries(@PathVariable("series") String series) {
        InterfaceResult result = new InterfaceResult();
        if (StringUtils.isNotBlank(series)){
            List<Map<String,Object>> resultList = null;
            try {
                resultList = daSouCheService.getCarModelBySeriesCode(series);
                result = checkResult(resultList,result);
            } catch (Exception e) {
                e.printStackTrace();
                result.InterfaceResult600("服务器发生错误");
            }
        }else {
            result.InterfaceResult600("参数不能为null");
        }
        return result;
    }
    @GetMapping(value = "/model/all")
    public InterfaceResult listAllModel() {
        InterfaceResult result = new InterfaceResult();
        List<Map<String,Object>> resultList = null;
        try {
            resultList = daSouCheService.getCarModelBySeriesCode(null);
            result = checkResult(resultList,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.InterfaceResult600("服务器发生错误");
        }
        return result;
    }

    public static InterfaceResult checkResult(List<Map<String,Object>> resultList,InterfaceResult result){
        if (null != resultList){
            result.InterfaceResult200(resultList);
        }else{
            result.InterfaceResult600("数据不存在");
        }
        return result;
    }

    /**
     * maxcar系统需求，自适应接口
     */
    @GetMapping("/choose")
    public Object hierarchyInfo(){
        InterfaceResult result = new InterfaceResult();
        List<ChooseInfoRequest> request = new ArrayList<>();
        List<CarBrand> carBrandList = daSouCheService.getAllBrand();
        for(CarBrand carBrand:carBrandList){
            ChooseInfoRequest chooseInfoRequest = new ChooseInfoRequest();
            chooseInfoRequest.setLabel(carBrand.getBrandName());
            chooseInfoRequest.setValue(carBrand.getBrandCode());
            List<CarSeries> carSeriesList = daSouCheService.getAllSeries(carBrand.getId());
            List<ChooseInfoRequest> chooseList = new ArrayList<>();
            for(CarSeries carSeries:carSeriesList){
                ChooseInfoRequest choose = new ChooseInfoRequest();
                choose.setLabel(carSeries.getSeriesName());
                choose.setValue(carSeries.getSeriesCode());
                chooseList.add(choose);
                //三层遍历所有车型，本地系统如果需要，放行下方注释即可
               /* List<CarModel> carModelList = daSouCheService.getAllModel(carSeries.getId());
                List<ChooseInfoRequest> chooseResultList = new ArrayList<>();
                for(CarModel carModel:carModelList){
                    ChooseInfoRequest chooseResult = new ChooseInfoRequest();
                    chooseResult.setValue(carModel.getModelCode());
                    chooseResult.setLabel(carModel.getModelName());
                    chooseResultList.add(chooseResult);
                };
                choose.setChildren(chooseResultList);*/
            };
            chooseInfoRequest.setChildren(chooseList);
            request.add(chooseInfoRequest);
        };
        result.InterfaceResult200(request);
        return result;
    }

}
