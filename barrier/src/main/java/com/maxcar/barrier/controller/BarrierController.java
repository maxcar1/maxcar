package com.maxcar.barrier.controller;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.barrier.service.BarrierControlCarService;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.barrier.service.CarService;
import com.maxcar.mqtt.service.PushCallback;
import com.maxcar.mqtt.service.ServerMQTT;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/barrier")
public class BarrierController {
    public BarrierController() {
        System.out.print(111);
    }
    Logger logger = LoggerFactory.getLogger(BarrierController.class);
    @Autowired
    private BarrierService barrierService;
    @Autowired
    private CarService carService;

    @Autowired
    private BarrierControlCarService barrierControlCarService;


    @GetMapping("/car/delete/{id}")
    public InterfaceResult listCar(@PathVariable("id") String id,HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try{
            barrierService.deleteCarById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        interfaceResult.InterfaceResult200("处理成功");
        return interfaceResult;
    }
    @PostMapping("/car/update")
    public InterfaceResult upload(@RequestBody Car car,HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try{
            carService.updateCarStatus(car);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        interfaceResult.InterfaceResult200("处理成功");
        return interfaceResult;
    }
    @PostMapping("/car/updateCar")
    public InterfaceResult updateCar(@RequestBody Car car,HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try{
            carService.updateByExampleSelective(car);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        interfaceResult.InterfaceResult200("处理成功");
        return interfaceResult;
    }
    @RequestMapping(value = "/getByRfid", method = RequestMethod.POST)
    public Car getByRfid(@RequestBody Car stockCarInfo, HttpServletRequest request, HttpServletResponse response) {
        String rfid = stockCarInfo.getRfid();
        return barrierService.selectByRFID(rfid,stockCarInfo.getMarketId());
    }

    /**
     * 根据道闸ID查询详情
     *
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectByBarrierId", method = RequestMethod.POST)
    public InterfaceResult selectByBarrierId(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Barrier barriers = barrierService.selectByBarrierId(barrier.getBarrierId());
            interfaceResult.InterfaceResult200(barriers);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }

    /**
     * 查询道闸列表
     *
     * @return
     */
    @RequestMapping(value = "/selectBarrierList", method = RequestMethod.POST)
    public InterfaceResult selectBarrierList() {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<Barrier> list = barrierService.selectBarrierList();
            interfaceResult.InterfaceResult200(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }


    /**
     * 修改或删除道闸配置（status 1为删除 0为修改）
     *
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateOrDelBarrier", method = RequestMethod.POST)
    public InterfaceResult updateOrDelBarrier(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            interfaceResult = barrierService.updateOrDelBarrier(barrier);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }
    //更新标签
    @RequestMapping(value = "car/updateCarRfid", method = RequestMethod.POST)
    public InterfaceResult updateCarRfid(@RequestBody Car car, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Car newCar = new Car();
            newCar.setId(car.getId());
            newCar.setRfid(car.getRfid());
            carService.updateByExampleSelective(newCar);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }


    /**
     * 保存车辆信息
     *
     * @param car
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/car/saveCar", method = RequestMethod.POST)
    public InterfaceResult saveCar(@RequestBody Car car, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Car newCar = carService.selectByPrimaryKey(car.getId());
            if(newCar!=null)
                carService.updateByExampleSelective(car);
            else
                carService.insertSelective(car);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }


    /**
     * 开闸
     * @param topic
     * @param dzId
     * @param type   0进场  -1应急出场 其他传的是金额,普通出场
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/open/{topic}/{dzId}/{type}", method = RequestMethod.GET)
    public InterfaceResult saveBarrier(@PathVariable("topic")String topic,
                                       @PathVariable("dzId")String dzId,
                                       @PathVariable("type")Integer type,
                                       HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
//            String dzId = "05D1FF373438594D43035233";
            String outParam = "";
            String value1 = Canstats.headerBody;
            //字符串长度/2
            String value2 = "leng";//44字节
            //协议版本
            String value3 = Canstats.headerVersion;
            String value4 = Canstats.dzML;//下发数据
            int time = (int)(System.currentTimeMillis()/1000);
            String timeStamp = PushCallback.toHexString(time);
            //id长度+id号+时间戳+设备类型+程序版本+设备电量
            String value5 = PushCallback.toHexString(dzId.length()/2)+dzId+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
            String value6 = "000B81";
            String value7 = "";
            String value8 = "";//欢迎词
            value7 = Canstats.yxcc;//允许开闸
            if(type == -3){
                value8 = "禁止重复入场";
                value6 = "000B87";
                value4 = "87";
                value7 = "FF0C";
            }else if (type == -2){
                value8 = "欢迎光临";
            }else if(type == -1){
                value8 = "一路顺风";
            }else{
                value8 = "金额"+type+"元";
                byte[] b = value8.getBytes("gbk");
                //最小7字节,最大9字节,直接补0
                value7 = "FF0"+b.length;
            }
            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
            outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
            System.out.println(outParam + "------发送数据");
            String outHex = CRC16M.GetModBusCRC(outParam);

            outParam = outParam + outHex;
            System.out.println(outParam + "------发送数据2");
            ServerMQTT.send(outParam,topic);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("保存失败");
        }
        return interfaceResult;
    }

    //道闸黑白名单配置
    @RequestMapping(value = "/addBarrierControlCar")
    public InterfaceResult addBarrierControlCar(@RequestBody BarrierControlCar controlCar, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            int i = barrierControlCarService.insertSelective(controlCar);
            if (i > 0) {
                interfaceResult.InterfaceResult200("添加成功");
            } else {
                interfaceResult.InterfaceResult200("添加失败");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return interfaceResult;
    }


    @RequestMapping(value = "/delBarrierControlCar/{id}")
    public InterfaceResult delete(@PathVariable("id") String id, HttpServletRequest request){
        InterfaceResult interfaceResult = new InterfaceResult();
        int i = barrierControlCarService.deleteByPrimaryKey(id);
        try{
            if(i>0){
                interfaceResult.InterfaceResult200("删除成功");
            }else {
                interfaceResult.InterfaceResult600("删除失败");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return interfaceResult;
    }

}
