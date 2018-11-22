package com.maxcar.stock.service.impl;

import com.maxcar.stock.dao.ReviewStepMapper;
import com.maxcar.stock.entity.Response.ReviewVo;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.pojo.ReviewStepExample;
import com.maxcar.stock.service.ReviewStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewStepService")
public class ReviewStepServiceImpl implements ReviewStepService {
    @Autowired
    private ReviewStepMapper reviewStepMapper;

    @Override
    public List<ReviewStep> getReviewStep(ReviewStep reviewStep) {
        ReviewStepExample example = new ReviewStepExample();
        example.createCriteria().andApplyTypeEqualTo(1).andMarketIdEqualTo(reviewStep.getMarketId());
        List<ReviewStep> list = reviewStepMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ReviewVo> reviewVolist(ReviewVo reviewVo) {
        return reviewStepMapper.reviewVolist( reviewVo);
    }
}
