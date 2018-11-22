package com.maxcar.stock.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.stock.dao.ReviewStepMapper;
import com.maxcar.stock.dao.WishListMapper;
import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.pojo.WishList;
import com.maxcar.stock.pojo.WxWishListCar;
import com.maxcar.stock.service.ReviewStepService;
import com.maxcar.stock.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("reviewStepService")
public class ReviewStepServiceImpl extends BaseServiceImpl<ReviewStep,String> implements ReviewStepService {
    @Autowired
    ReviewStepMapper reviewStepMapper ;

    @Override
    public BaseDao<ReviewStep, String> getBaseMapper() {
        return null;
    }

    @Override
    public int saveReviewStep(ReviewStep reviewStep) {
        int insertFlag = 0;
        insertFlag =  reviewStepMapper.insert(reviewStep);
       return insertFlag;
    }

    @Override
    public int deleteByReview(ReviewStep reviewStep) {
        int delFlag = 0;
        delFlag= reviewStepMapper.deleteByReview(reviewStep);
        return delFlag;
    }

    @Override
    public List<ReviewStep> selectStepList(ReviewStep reviewStep) {
        return reviewStepMapper.selectStepList(reviewStep);
    }

    @Override
    public List<FlowStep> selectReviewManage(String marketId) {
        return reviewStepMapper.selectReviewManage(marketId);
    }

    @Override
    public int insertFlowStep(FlowStep flowStep) {
        return reviewStepMapper.insertFlowStep(flowStep);
    }

    @Override
    public int deleteReviewManage(String id) {
        return reviewStepMapper.deleteReviewManage(id);
    }

    @Override
    public int updateFlowStep(FlowStep flowStep) {
        return reviewStepMapper.updateFlowStep(flowStep);
    }

    @Override
    public int selectMarketCode(FlowStep flowStep) {
        return reviewStepMapper.selectMarketCode(flowStep);
    }


}
