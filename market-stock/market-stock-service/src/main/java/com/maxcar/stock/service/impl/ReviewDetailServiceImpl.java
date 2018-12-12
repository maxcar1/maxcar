package com.maxcar.stock.service.impl;

import com.maxcar.stock.dao.ReviewDetailMapper;
import com.maxcar.stock.pojo.ReviewDetail;
import com.maxcar.stock.pojo.ReviewDetailExample;
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
        /*ReviewDetailExample example = new ReviewDetailExample();
        example.createCriteria().andReviewIdEqualTo(reviewDetail.getLevel());
        example.createCriteria().andReviewIdEqualTo(reviewDetail.getReviewId());*/
        List<ReviewDetail> list = reviewDetailMapper.selReviewDetail(reviewDetail);
        return list;
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

    @Override
    public int updateReviewStatus(ReviewDetail reviewDetail) {
        return reviewDetailMapper.updateReviewStatus(reviewDetail);
    }

    @Override
    public ReviewDetail selectReviewDetail(ReviewDetail reviewDetail) {
        return reviewDetailMapper.selectReviewDetail(reviewDetail);
    }

    @Override
    public ReviewDetail reviewDetail(String reviewId) {
        return reviewDetailMapper.reviewDetail(reviewId);
    }
}
