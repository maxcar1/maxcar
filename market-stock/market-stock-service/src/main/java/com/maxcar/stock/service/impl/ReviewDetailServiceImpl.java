package com.maxcar.stock.service.impl;

import com.maxcar.stock.dao.ReviewDetailMapper;
import com.maxcar.stock.pojo.ReviewDetail;
import com.maxcar.stock.pojo.ReviewStepExample;
import com.maxcar.stock.service.ReviewDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewDetailService")
public class ReviewDetailServiceImpl implements ReviewDetailService {

    @Autowired
    private ReviewDetailMapper reviewDetailMapper;

    @Override
    public List<ReviewDetail> getReviewDetail(ReviewDetail reviewDetail) {
        List<ReviewDetail> list = reviewDetailMapper.getReviewDetail(reviewDetail);
        return null;
    }

    @Override
    public boolean saveReviewDetail(ReviewDetail reviewDetail) {
        ReviewStepExample example = new ReviewStepExample();
        int count = 0;
        count = reviewDetailMapper.insertSelective(reviewDetail);
        if(count>0){
            return true;
        }
        return false;
    }
}
