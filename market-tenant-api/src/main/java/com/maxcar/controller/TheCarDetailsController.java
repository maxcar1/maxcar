package com.maxcar.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.model.VehicleBrand;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.HanyuPinyinHelper;
import com.maxcar.stock.entity.Request.SearchCarRequest;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.VehicleBrandVo;
import com.maxcar.user.entity.Staff;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/find-car")
public class TheCarDetailsController extends BaseController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarBaseService carBaseService;

    @Autowired
    private DaSouCheService daSouCheService;


    @RequestMapping(value = "/getCarDetail/{id}")
    @OperationAnnotation(title = "查看车辆详情")
    public InterfaceResult getCarDetail(@PathVariable(value = "id") String id) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = carService.findingCarDetails(id);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }
    @GetMapping("/choose")
    public Object hierarchyInfo(){
        InterfaceResult result = new InterfaceResult();
        List<VehicleBrand> request = new ArrayList<>();
        List<CarBrand> carBrandList = daSouCheService.getAllBrand();
        for(CarBrand carBrand:carBrandList){
            VehicleBrand vehicleBrand = new VehicleBrand();
            vehicleBrand.setLogoUrl(carBrand.getLogoUrl());
            vehicleBrand.setName(carBrand.getBrandName());
            vehicleBrand.setCode(carBrand.getBrandCode());
            List<CarSeries> carSeriesList = daSouCheService.getAllSeries(carBrand.getId());

            VehicleBrand vehicleBrand1 = new VehicleBrand();
            vehicleBrand1.setCode(carBrand.getBrandCode());

            CarSeries carSerie = new CarSeries();
            carSerie.setSeriesCode(carBrand.getBrandCode());
            carSerie.setSeriesName("全部");
            carSeriesList.add(0,carSerie);

            List<VehicleBrand> chooseList = new ArrayList<>();
            for(CarSeries carSeries:carSeriesList){
                VehicleBrand choose = new VehicleBrand();
                choose.setName(carSeries.getSeriesName());
                choose.setCode(carSeries.getSeriesCode());
                chooseList.add(choose);
            }
            vehicleBrand.setChildren(chooseList);
            request.add(vehicleBrand);
        }

        result.InterfaceResult200(request);
        return result;
    }


    @GetMapping(value = "/list")
    @OperationAnnotation(title = "车辆列表")
    public InterfaceResult queryList(SearchCarRequest carRequest, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        carRequest.setMarketId(staff.getMarketId());
        PageInfo pageInfo = carBaseService.selectCarList(carRequest);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @GetMapping(value = "/count")
    @OperationAnnotation(title = "车辆列表数量")
    public InterfaceResult count(SearchCarRequest carRequest, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Staff staff = getCurrentStaff(request);
        carRequest.setMarketId(staff.getMarketId());
        int count = carBaseService.countCarList(carRequest);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", count);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }
    @GetMapping("/choose1")
    public Object hierarchyIn(){
        InterfaceResult result = new InterfaceResult();
        List<VehicleBrand> request = new ArrayList<>();
        List<CarBrand> carBrandList = daSouCheService.getAllBrand();

        for(CarBrand carBrand:carBrandList){
            VehicleBrand vehicleBrand = new VehicleBrand();
            vehicleBrand.setLogoUrl(carBrand.getLogoUrl());
            vehicleBrand.setName(carBrand.getBrandName());
            vehicleBrand.setCode(carBrand.getBrandCode());
            List<CarSeries> carSeriesList = daSouCheService.getAllSeries(carBrand.getId());
            List<VehicleBrand> chooseList = new ArrayList<>();
            for(CarSeries carSeries:carSeriesList){
                VehicleBrand choose = new VehicleBrand();
                choose.setName(carSeries.getSeriesName());
                choose.setCode(carSeries.getSeriesCode());
                chooseList.add(choose);
            }
            vehicleBrand.setChildren(chooseList);
            request.add(vehicleBrand);
        }
        String[] letters = {"A", "B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Map<String, ArrayList> map = new HashMap<>(32);
        for (String letter : letters) {
            map.put(letter, new ArrayList<>());
        }
        for (VehicleBrand res:request){
            for (String key : map.keySet()) {
                if (HanyuPinyinHelper.getFirstLetter(res.getName()).equals(key)){
                    map.get(key).add(res);
                }
            }
        }
        result.InterfaceResult200(map);
        return result;
    }
    @GetMapping("/choose3")
    public InterfaceResult hierarchyInfo1(CarBase carBase,HttpServletRequest request){
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Staff staff = getCurrentStaff(request);
            List<VehicleBrandVo> vehicleBrandList = carBaseService.getBrandTop10(staff.getMarketId());
            interfaceResult.InterfaceResult200(vehicleBrandList);
        }catch (Exception e  ){
          e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
        }
        return interfaceResult;
    }
}

