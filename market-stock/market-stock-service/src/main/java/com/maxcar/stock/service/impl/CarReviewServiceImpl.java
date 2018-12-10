package com.maxcar.stock.service.impl;

import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.stock.entity.Response.CarRecordVo;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarReviewVo;
import com.maxcar.stock.vo.CarVo;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("carReviewService")
public class CarReviewServiceImpl implements CarReviewService {
    @Autowired
    private CarReviewMapper carReviewMapper;
    @Autowired
    private CarService carService;
    @Autowired
    private MessageProducerService messageProducerService;



    @Override
    public CarReview getCarReview(CarVo carVo) {
        CarReview carReview = carReviewMapper.selectByPrimaryKey(carVo.getReviewId());
        return carReview;
    }

    @Override
    public List<CarRecordVo> getCarRecord(CarReview carReview) {
        return carReviewMapper.getCarRecord(carReview);
    }

    /**
     * 查询到返场时间未返场的车辆
     * @return
     */
    @Override
    public List<CarReviewVo> selectAllCarReviewByIsPass() {
        List<CarReviewVo> carReviews = carReviewMapper.selectAllTimeoutNotreturnCarReview();
        return carReviews;
    }

    @Override
    public List<CarReview> selectAllCarReview() {
        return carReviewMapper.selectAllCarReview();
    }

    /**
     * 定时扫描出场未归的车辆改变状态为6,  到了申请记录的返场时间将该条申请记录改完以完成  代表此次申请已经结束
     */
    @Override
    public void updateTimeoutNotreturnCarStockStatus() throws Exception{

        // 返场时间到达以后将申请记录结束掉
        List<CarReview> carReviewList = selectAllCarReview();
        if (carReviewList != null && carReviewList.size() > 0){
            for (CarReview c: carReviewList) {
                if (c.getBackTime().getTime() < System.currentTimeMillis()){
                    c.setIsComplete(1);
                    carReviewMapper.updateByPrimaryKeySelective(c);
                    String topic = messageProducerService.getTopic(c.getMarketId());
                    //同步删除本地车辆状态
                    //组装云端参数
                    PostParam postParam = new PostParam();
                    postParam.setData(JsonTools.toJson(c));
                    postParam.setMarket(c.getMarketId());
                    postParam.setUrl("/barrier/carReview/saveOrUpdate");
                    postParam.setMethod("post");
                    postParam.setOnlySend(false);
                    postParam.setMessageTime(Constants.dateformat.format(new Date()));
                    messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
                }
            }
        }

        // 到达返场时间未归的的车辆,改为出场超时
        List<CarReviewVo> carReviews = selectAllCarReviewByIsPass();
        if (carReviews != null && carReviews.size() > 0) {
            for (CarReviewVo cr : carReviews) {
                Car car = new Car();
                car.setId(cr.getCarId());
                car.setMarketId(cr.getMarketId());
                car.setStockStatus(6);// 出场超时
                updateCarStockStatus(car);
            }
        }
    }


    private void updateCarStockStatus(Car car)throws Exception{
            carService.updateByPrimaryKeySelective(car);
            String topic = messageProducerService.getTopic(car.getMarketId());
            PostParam postParam = new PostParam();
            postParam.setData(JsonTools.toJson(car));
            postParam.setMarket(car.getMarketId());
            postParam.setUrl("/barrier/car/saveCar");
            postParam.setOnlySend(false);
            postParam.setMessageTime(Constants.dateformat.format(new Date()));
            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
        }
}
