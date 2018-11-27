package com.maxcar.stock.service.impl;

import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.stock.entity.Response.CarRecordVo;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.service.CarReviewService;
import com.maxcar.stock.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carReviewService")
public class CarReviewServiceImpl implements CarReviewService {
    @Autowired
    private CarReviewMapper carReviewMapper;

    @Override
    public CarReview getCarReview(CarVo carVo) {
        CarReview carReview = carReviewMapper.selectByPrimaryKey(carVo.getReviewId());
        return carReview;
    }

    @Override
    public List<CarRecordVo> getCarRecord(CarReview carReview) {
        return carReviewMapper.getCarRecord(carReview);
    }
}
