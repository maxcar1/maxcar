package com.maxcar.permission;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/market-api/api")
@RestController
public class WeiXinController {

    @Autowired
    private DaSouCheService daSouCheService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/models/{scModelCode}", method = RequestMethod.GET)
    @OperationAnnotation(title = " 根据车型码获取车辆详细配置信息")
    public InterfaceResult detailInfo(@PathVariable("scModelCode") String scModelCode) {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult = daSouCheService.getDetailInfo(scModelCode);
        return interfaceResult;
    }

    @RequestMapping(value="/getCarDetail")
    @OperationAnnotation(title = " 车辆详情")
    public  InterfaceResult getCarDetail(@RequestBody Car car , HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String,Object> map = carService.carDetail(car);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }
}
