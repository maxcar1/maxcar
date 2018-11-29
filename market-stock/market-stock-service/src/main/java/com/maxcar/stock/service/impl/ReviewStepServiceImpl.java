package com.maxcar.stock.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.stock.dao.CarReviewMapper;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.dao.ReviewStepMapper;
import com.maxcar.stock.dao.WishListMapper;
import com.maxcar.stock.entity.Response.ReviewVo;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.ReviewStepService;
import com.maxcar.stock.service.WishListService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("reviewStepService")
public class ReviewStepServiceImpl extends BaseServiceImpl<ReviewStep,String> implements ReviewStepService {
    @Autowired
    ReviewStepMapper reviewStepMapper ;
    @Autowired
    private CarReviewMapper carReviewMapper;
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

    @Override
    public List<ReviewStep> selectStepListByLevel(ReviewStep reviewStep) {
        return reviewStepMapper.selectStepListByLevel(reviewStep);
    }

    @Override
    public FlowStep selectReviewManageByReviewStep(ReviewStep reviewStep) {
        return reviewStepMapper.selectReviewManageByReviewStep(reviewStep);
    }

    @Override
    public int carOutApply(CarReview carReview) {
        return reviewStepMapper.insertCarReview(carReview);
    }

    @Override
    public int checkCarReview(CarReview carReview) {
        return reviewStepMapper.checkCarReview(carReview);
    }

    @Override
    public List<ReviewStep> getReviewStep(ReviewStep reviewStep) {
        ReviewStepExample example = new ReviewStepExample();
        example.createCriteria().andApplyTypeEqualTo(1).andMarketIdEqualTo(reviewStep.getMarketId());
        List<ReviewStep> list = reviewStepMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ReviewStep> reviewStepList(ReviewStep reviewStep) {
        ReviewStepExample example = new ReviewStepExample();
        ReviewStepExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(reviewStep.getMarketId())){
            criteria.andMarketIdEqualTo(reviewStep.getMarketId());
        }
        if(StringUtils.isNotBlank(reviewStep.getOrgId())){
            criteria.andOrgIdEqualTo(reviewStep.getOrgId());
        }
        List<ReviewStep> list = reviewStepMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ReviewVo> reviewVolist(ReviewVo reviewVo) {
        return reviewStepMapper.reviewVolist( reviewVo);
    }


}
