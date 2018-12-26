package com.maxcar.barrier.controller;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.barrier.service.BarrierCameraService;
import com.maxcar.barrier.service.BarrierControlCarService;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.barrier.service.CarService;
import com.maxcar.jdbc.JdbcCurd;
import com.maxcar.mqtt.service.BasicRemoteClient;
import com.maxcar.mqtt.service.PushCallback;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import com.maxcar.util.JsonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * 开闸接口
 * Created by Administrator on 2018/7/27.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dz")
public class OpenBarrierController {
    Logger logger = LoggerFactory.getLogger(OpenBarrierController.class);
    @Autowired
    private BarrierService barrierService;
    @Autowired
    private CarService carService;

    @Autowired
    private BarrierControlCarService barrierControlCarService;
    @Autowired
    private BarrierCameraService barrierCameraService;

    /**
     * 是否开闸
     *
     * @param type      0初始化设置，1rfid，2id卡
     * @param ip
     * @param barrierId
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/isOpen/{type}/{rfid}/{barrierId}")
    public InterfaceResult isOpen(@PathVariable("type") String type, @PathVariable("rfid") String ip, @PathVariable("barrierId") String barrierId, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
//            String barrierId = clientData.substring(14, 38);
//            Barrier barrier = JdbcCurd.selectByBarrierId(barrierId.toUpperCase());
            String s = interfaceResult.getMsg();
            s = "欢迎管理";
            interfaceResult.setMsg(URLEncoder.encode(s, "gbk").replaceAll("%",""));
            interfaceResult.setData(Canstats.dzOpen);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult600("获取失败");
        }

        response.setHeader("Content-Length", JsonTools.toJson(interfaceResult).getBytes().length + "");

        return interfaceResult;
    }
}