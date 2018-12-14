package com.maxcar.mqtt.service;

import com.maxcar.barrier.pojo.*;
import com.maxcar.barrier.service.*;
import com.maxcar.jdbc.JdbcCurd;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用道闸验证类
 */
public class BarrierValid {
    Logger logger = LoggerFactory.getLogger(BarrierValid.class);

    //道闸验证
    public Map openDz(String clientData, Barrier barrier) throws Exception {
        Map map = new HashMap();
        logger.info("道闸验证消息 : " + clientData);
        String outParam = "";
        String value1 = clientData.substring(0, 4);
        String value2 = "002C";//44字节
        String value3 = clientData.substring(8, 10);
        String value4 = "81";//下发数据
        String value5 = clientData.substring(12, 58);
        String value6 = "000B81";
        String value7 = "";
        String value8 = "";//欢迎词
        String clientDatas = clientData.substring(66, 74);
        long valueTens = Long.parseLong(clientDatas, 16);
        String values = "" + valueTens;
        while (values.length() < 10) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(values);// 左补0
            values = sb.toString();
        }
        String marketId = barrier.getMarketId();
        logger.info("rfid" + marketId + Canstats.between + values);
        logger.info("道闸是否受限制：" + !barrier.getStatus().equals("0"));

//        Car stockCarInfo = barrierService.selectByRFID(marketId + Canstats.between + values,marketId);//查询是否允许开闸
        Car stockCarInfo = JdbcCurd.selectCarByRfid(marketId,marketId + Canstats.between + values);
        if(stockCarInfo==null){
            value7 = Canstats.jzcc;
            value8 = "查无此车";
            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
            String outHex = CRC16M.GetModBusCRC(outParam);
            outParam = outParam + outHex;
            map.put("outParam", outParam);
            return map;
        }
        String outHex = CRC16M.GetModBusCRC(outParam);
        switch (barrier.getStatus()){
            case "0"://不限制
                value7 = Canstats.yxcc;//允许开闸
                value8 = barrier.getStaticSpeech();
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
                logger.info("车辆进场记录已生成");
                if(barrier.getInOutType() == 0) {//入口
                    switch (stockCarInfo.getStockStatus()){
                        case -1://删除车
                            break;
                        case 5://售出车
                            break;
                        case 4://售出未出厂
                            break;
                        case 6://出场超时、进场不改变状态
                            break;
                        default:
                            stockCarInfo.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                            break;
                    }
                }else{
                    if (stockCarInfo.getStockStatus() == Canstats.saleType)//售出未出场，把状态改为已出场
                        stockCarInfo.setStockStatus(Canstats.saleOutType);
                    else{
                        stockCarInfo.setStockStatus((stockCarInfo.getStockStatus()==Canstats.deleteType || stockCarInfo.getStockStatus()==Canstats.saleOutType)?stockCarInfo.getStockStatus():Canstats.outType);
                    }
                }
                initCarStatus(stockCarInfo, barrier);
                map.put("stockCarInfo", stockCarInfo);
                break;
            case "4"://不需要硬件回复，走不限制逻辑
                value7 = Canstats.yxcc;//允许开闸
                value8 = barrier.getStaticSpeech();
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
                logger.info("车辆进场记录已生成");
                if(barrier.getInOutType() == 0) {//入口
                    switch (stockCarInfo.getStockStatus()){
                        case -1://删除车
                            break;
                        case 5://售出车
                            break;
                        case 4://售出未出厂
                            break;
                        case 6://出场超时、进场不改变状态
                            break;
                        default:
                            stockCarInfo.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                            break;
                    }
//                    stockCarInfo.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                }else{
                    if (stockCarInfo.getStockStatus() == Canstats.saleType)//售出未出场，把状态改为已出场
                        stockCarInfo.setStockStatus(Canstats.saleOutType);
                    else{
                        stockCarInfo.setStockStatus((stockCarInfo.getStockStatus()==Canstats.deleteType || stockCarInfo.getStockStatus()==Canstats.saleOutType)?stockCarInfo.getStockStatus():Canstats.outType);
                    }
                }
                initCarStatus(stockCarInfo, barrier);
                map.put("stockCarInfo", stockCarInfo);
                break;
            default://限制
                Map remap = valid(stockCarInfo, barrier);
                value8 = remap.get("title") + "";
                if (remap.get("flag").equals("true")) {
                    value7 = Canstats.jzcc;//禁止开闸
                }else{//允许开闸，再验证黑白名单情况
                    value7 = Canstats.yxcc;//允许开闸
//                    if(barrier.getInOutType() == 0) {//入口做验证
                        if (blackOrWhite(stockCarInfo, barrier)) {
                            value7 = Canstats.jzcc;//禁止开闸
                            value8 = "禁止通行";
                        }
                }
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
                if(value7.equals(Canstats.yxcc)){
                    initCarStatus(stockCarInfo, barrier);
                    map.put("stockCarInfo", stockCarInfo);
                }
                logger.info("车辆进场记录已生成:" + outParam);
                break;
        }
        map.put("outParam", outParam);
        return map;
    }

    //验证黑白名单
    public boolean blackOrWhite(Car car, Barrier barrier){
//        BarrierControlCarService barrierControlCarService = ApplicationContextHolder.getBean("barrierControlCarService");
//        BarrierControlCarExample example = new BarrierControlCarExample();
//        BarrierControlCarExample.Criteria criteria =  example.createCriteria();
//        criteria.andIsvalidEqualTo(1).andBarrierIdEqualTo(barrier.getBarrierId());
//        criteria.andCarIdEqualTo(car.getId()).andMarketIdEqualTo(barrier.getMarketId());
//
//
//        BarrierControlCarExample.Criteria criteria2 =  example.createCriteria();
//        criteria2.andIsvalidEqualTo(1).andBarrierIdEqualTo(barrier.getBarrierId());
//        if(car.getTenant()!=null) {
//            criteria2.andTenantIdEqualTo(car.getTenant());
//        }
//        criteria2.andMarketIdEqualTo(car.getMarketId());
//        example.or(criteria2);
        List<BarrierControlCar> barrierControlCarList = null;
        if(barrier.getStatus().equals("2")){//白名单
            barrierControlCarList = JdbcCurd.selectBarrierControlCar(car,barrier);
            if(barrierControlCarList!=null && barrierControlCarList.size()>0)
                return false;
            else
                return true;
        }else  if(barrier.getStatus().equals("3")){//黑名单
            barrierControlCarList = JdbcCurd.selectBarrierControlCar(car,barrier);
            if(barrierControlCarList!=null && barrierControlCarList.size()>0)
                return true;
            else
                return false;
        }
        return false;

    }

    //验证车辆是否可以入场，其中库存车能正常出入，售出已出场和删除车不能入，售出未出场只能出
    public Map valid(Car car, Barrier barrier) {
        Map map = new HashMap();
        String title = barrier.getStaticSpeech();
        map.put("flag", "false");
        if(barrier.getInOutType() == 0) {//入口
            switch (car.getStockStatus()){
                case -1://删除车
                    title = "车已删除";
                    map.put("flag", "true");
                    break;
                case 5://售出车
                    title = "车已售出";
                    map.put("flag", "true");
                    break;
                case 4://售出未出厂
                    title = "车已售出";
                    map.put("flag", "true");
                    break;
                case 6://出场超时、进场不改变状态
                    break;
                default:
                    car.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                    break;
            }
        }else{//出口，未来质押车做验证
            if(car.getMarketId().equals("016")){
                CarReviewService carReviewService = ApplicationContextHolder.getBean("carReviewService");
                Boolean canPass = carReviewService.selectCarReviewByCarId(car.getId());
                if(!canPass){//禁止通行,不改变车辆状态
                    title = "禁止通行";
                    map.put("flag", "true");
                }else{
                    if(car.getStockStatus() == Canstats.saleType) {//售出未出场，把状态改为已出场
                        car.setStockStatus(Canstats.saleOutType);
                    }else{
                        car.setStockStatus((car.getStockStatus() == Canstats.deleteType || car.getStockStatus() == Canstats.saleOutType || car.getStockStatus() == Canstats.OutTimeType) ? car.getStockStatus() : Canstats.outType);
                    }
                }
            }else {
                if (car.getStockStatus() == Canstats.saleType)//售出未出场，把状态改为已出场
                    car.setStockStatus(Canstats.saleOutType);
                else
                    car.setStockStatus((car.getStockStatus() == Canstats.deleteType || car.getStockStatus() == Canstats.saleOutType) ? car.getStockStatus() : Canstats.outType);
            }
        }
        logger.info(title + car.getStockStatus() + "==" + barrier.getInOutType() + "车已删除");
        map.put("title", title);
        map.put("car", JsonTools.toJson(car));//设置车辆最新状态
        return map;
    }


    //修改车状态，
    public Car initCarStatus(Car car, Barrier barrier) {
        CarRecordService carRecordService = ApplicationContextHolder.getBean("carRecordService");
        CarRecord carRecord = new CarRecord();
        carRecord.setChannel(barrier.getBarrierId());
        carRecord.setChannelName(barrier.getBarrierPosition());
        carRecord.setId(UuidUtils.generateIdentifier());
        carRecord.setMarketId(car.getMarketId());
        carRecord.setCarNo(car.getId());
        carRecord.setRfid(car.getRfid());
        carRecord.setType(barrier.getInOutType());
        carRecord.setVin(car.getVin());
        carRecordService.saveCarRecord(carRecord);//插入出场入场记录
        CarService carService = ApplicationContextHolder.getBean("carService");
        car.setCarRecord(carRecord);
        carService.updateCarStatus(car);//更新车状态
        logger.info("车辆状态已更新:" + car.getStockStatus());
        return car;
    }
}
