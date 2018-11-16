package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.service.BarrierControlCarService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author sunyazhou
 * @Date 2018/10/24 14:24
 * @desc
 */
@RestController
@RequestMapping("/api/barrier")
public class BarrierControlCarController extends BaseController {

    @Autowired
    private BarrierControlCarService barrierControlCarService;
    @Autowired
    private MessageProducerService messageProducerService;

    @RequestMapping(value = "/addBarrierControlCar")
    @OperationAnnotation(title = "道闸黑白名单添加车辆")
    public InterfaceResult addBarrierControlCar(@RequestBody BarrierControlCar controlCar, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if (controlCar.getMarketId() == null){
            controlCar.setMarketId(user.getMarketId());
            controlCar.setInsertOperator(user.getUserId());
        }
        controlCar.setId(UuidUtils.getUUID());
        int i = barrierControlCarService.insertSelective(controlCar);
        if (i>0){
            interfaceResult.setMsg("添加成功");
            String topic = super.getTopic(user.getMarketId());
            //同步删除本地车辆状态
            //组装云端参数
            PostParam postParam = new PostParam();
            postParam.setData(JsonTools.toJson(controlCar));
            postParam.setMarket(user.getMarketId());
            postParam.setUrl("/barrier/addBarrierControlCar");
            postParam.setMethod("post");
            postParam.setOnlySend(false);
            postParam.setMessageTime(Constants.dateformat.format(new Date()));
            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        }else {
            interfaceResult.setMsg("添加失败");
        }
        return interfaceResult;
    }


    @RequestMapping(value = "/selectAllByListType")
    @OperationAnnotation(title = "查询道闸黑白名单配置")
    public InterfaceResult selectAllByListType(@RequestBody BarrierControlCar controlCar, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if (controlCar.getMarketId() == null){
            controlCar.setMarketId(user.getMarketId());
        }
        PageInfo pageInfo = barrierControlCarService.selectAllByListType(controlCar);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @RequestMapping(value = "/delBarrierControlCar/{id}")
    @OperationAnnotation(title = "删除黑白名单中的车辆或者商户")
    public InterfaceResult delete(@PathVariable("id") String id, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        int i = barrierControlCarService.deleteByPrimaryKey(id);
        if (i>0){
            interfaceResult.InterfaceResult200("删除成功");
            String topic = super.getTopic(user.getMarketId());
            //同步删除本地车辆状态
            //组装云端参数
            PostParam postParam = new PostParam();
            postParam.setData(null);
            postParam.setMarket(user.getMarketId());
            postParam.setUrl("/barrier/delBarrierControlCar/"+id);
            postParam.setMethod("get");
            postParam.setOnlySend(false);
            postParam.setMessageTime(Constants.dateformat.format(new Date()));
            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        }else {
            interfaceResult.InterfaceResult600("删除失败");
        }
        return interfaceResult;
    }


    }
