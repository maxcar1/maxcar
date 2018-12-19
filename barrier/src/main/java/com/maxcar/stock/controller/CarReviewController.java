package com.maxcar.stock.controller;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.barrier.service.BarrierCameraService;
import com.maxcar.barrier.service.BarrierControlCarService;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.barrier.service.CarService;
import com.maxcar.base.util.StringUtil;
import com.maxcar.mqtt.service.BasicRemoteClient;
import com.maxcar.mqtt.service.PushCallback;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 出场审批结果记录
 * Created by Administrator on 2018/11/27.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/barrier")
public class CarReviewController {
    Logger logger = LoggerFactory.getLogger(CarReviewController.class);
    @Autowired
    private CarReviewService carReviewService;

    /**
     * 保存或者修改无锡出场审批
     * @param carReview
     * @param request
     * @return
     */
    @PostMapping("/carReview/saveOrUpdate")
    public InterfaceResult saveOrUpdate(@RequestBody CarReview carReview, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        CarReview carReviews = carReviewService.selectByPrimaryKey(carReview.getId());
        if(carReviews!=null)
            carReviewService.updateByPrimaryKeySelective(carReview);
        else
            carReviewService.insertSelective(carReview);
        return interfaceResult;
    }
}
