package com.maxcar.stock.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.CarReviewExample;
import com.maxcar.stock.service.CarReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("carReviewService")
public class CarReviewServiceImpl extends BaseServiceImpl<CarReview,String> implements CarReviewService {
    @Autowired
    private CarReviewMapper carReviewMapper;

    @Override
    public BaseDao<CarReview, String> getBaseMapper() {
        return carReviewMapper;
    }

    @Override
    public Boolean selectCarReviewByCarId(String carId) {
        CarReviewExample example = new CarReviewExample();
        //审批流程走完1，整个流程走完1，流程走完不能出场
        example.createCriteria().andIsValidEqualTo(1).andCarIdEqualTo(carId).andIsPassEqualTo(1).andIsCompleteEqualTo(0);
        List<CarReview> carReviews = carReviewMapper.selectByExample(example);
        if(carReviews != null && carReviews.size()>=1)
            return true;//允许出
        else
            return false;//禁止出
    }
}
