package com.maxcar.stock.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.stock.pojo.CarReview;
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
}
