package com.maxcar.review.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.service.ReviewStepService;
import com.maxcar.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewStepManageController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewStepService reviewStepService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 审批步骤列表
     *
     * @param reviewStep
     * @return
     */
    @RequestMapping(value = "/reviewStepList", method = RequestMethod.POST)
    public InterfaceResult reviewStepList( @RequestBody ReviewStep reviewStep) {
        InterfaceResult result = new InterfaceResult();
        try{
            List<ReviewStep> reviewStepList  = reviewStepService.selectStepList(reviewStep);
            for (ReviewStep review:reviewStepList ) {
                List<Map> list = userService.getUserOrgByReview(review);
                review.setUserOrg(list);
            }
            result.setData(reviewStepList);
            result.setCode("200");
        }catch (Exception e){
            result.setMsg("服务器错误");
            result.setCode("500");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询code是否重复
     * @param flowStep
     * @return
     */
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public InterfaceResult checkCode( @RequestBody  FlowStep flowStep) {
        InterfaceResult result = new InterfaceResult();
        logger.info("======================"+flowStep.getCode());
        if(flowStep.getMarketId()==null||flowStep.getCode()==null){
            result.setCode("600");
            result.setMsg("请核对参数");
            return result;
        }
        int flag = reviewStepService.selectMarketCode(flowStep);
        if(flag>0){
            result.setMsg("该code重复");
            result.setCode("600");
        }else{
            result.setCode("200");
        }
        return result;
    }
    /**
     * 审批设置列表
     *
     * @param marketId
     * @return
     */
    @RequestMapping(value = "/reviewManage", method = RequestMethod.GET)
    public InterfaceResult reviewManage( String marketId) {
        InterfaceResult result = new InterfaceResult();
        try {
            List<FlowStep> list = reviewStepService.selectReviewManage(marketId);
            result.setData(list);
            result.setCode("200");
        }catch (Exception e){
            result.setMsg("服务器错误");
            result.setCode("500");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 删除审批方式列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteReviewManage", method = RequestMethod.GET)
    public InterfaceResult deleteReviewManage(String id) {
        InterfaceResult result = new InterfaceResult();
        int flag = reviewStepService.deleteReviewManage(id);
        if(flag>0){
            result.setCode("200");
        }else{
            result.setCode("600");
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 新增审批类型
     * @param flowStep
     * @return
     */
    @RequestMapping(value = "/newReviewType", method = RequestMethod.POST)
    public InterfaceResult newReviewType(@RequestBody FlowStep flowStep) {
        InterfaceResult result = new InterfaceResult();
        flowStep.setInsertTime(new Date());
        flowStep.setIsValid(1);
        int flag  = reviewStepService.insertFlowStep(flowStep);
        if(flag>0){
            result.setCode("200");
            result.setMsg("增加成功");
        }else{
            result.setCode("600");
            result.setMsg("增加失败");
        }
        return  result;
    }
    /**
     * 获取审核人列表
     *
     * @param marketId
     * @return
     */
    @RequestMapping(value = "/reviewPersonList", method = RequestMethod.GET)
    public InterfaceResult reviewPersonList(String marketId) {
        InterfaceResult result = new InterfaceResult();
        try {
            List<Map> list = userService.getUserAndOrgByMarketId(marketId);
            result.setData(list);
            result.setCode("200");
        } catch (Exception e) {
            result.setCode("500");
            result.setMsg("服务器错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/saveReviewStep", method = RequestMethod.POST)
    public InterfaceResult saveReviewStep(@RequestBody ReviewStep reviewStep) {
        InterfaceResult result = new InterfaceResult();
        int flag = 0;
        try {
            if (reviewStep.getApply() != null) {
                List<String> list = Arrays.asList(reviewStep.getApply().split(","));
                if (reviewStep.getId() != null) {
                    reviewStepService.deleteByReview(reviewStep);
                }
                for (String orgUser : list) {
                    String[] ou = orgUser.split(":");
                    reviewStep.setOrgld(ou[0].toString());
                    reviewStep.setReviewPersonId(ou[1].toString());
                    flag = reviewStepService.saveReviewStep(reviewStep);
                    logger.info("编辑返回=====" + flag);
                    if (flag > 0) {
                        result.setCode("200");
                    } else {
                        result.setCode("600");
                        result.setMsg("请核对参数");
                    }
                }
            }
        } catch (Exception e) {
            result.setCode("500");
            result.setMsg("服务器错误");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除步骤
     * @param reviewStep
     * @return
     */
    @RequestMapping(value = "/deleteStep", method = RequestMethod.GET)
    public InterfaceResult deleteStep(ReviewStep reviewStep) {
        InterfaceResult result = new InterfaceResult();
        int delFlag = 0;
        try {
            delFlag = reviewStepService.deleteByReview(reviewStep);
            if(delFlag>0){
                result.setCode("200");
                result.setMsg("删除成功");
            }else{
                result.setCode("600");
                result.setMsg("删除失败");
            }
        } catch (Exception e) {
            result.setCode("500");
            result.setMsg("服务器错误");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存步骤信息
     * @param reviewSteps
     * @return
     */
    @RequestMapping(value = "/saveStepList", method = RequestMethod.GET)
    public InterfaceResult saveStepList(List<ReviewStep> reviewSteps) {
        InterfaceResult result = new InterfaceResult();
        int flag = 0;
        if(reviewSteps==null&&reviewSteps.isEmpty()){
            result.setCode("600");
            result.setMsg("请核对参数");
            return result;
        }
        //根据申请类型删除
        reviewStepService.deleteByReview(reviewSteps.get(0));
        //更新flow_step
        FlowStep flowStep = new FlowStep();
        flowStep.setCode(reviewSteps.get(0).getApplyType());
        flowStep.setIsNeedReview(reviewSteps.get(0).getIsNeedReview());
        flowStep.setReviewType(reviewSteps.get(0).getReviewType());
        flowStep.setMarketId(reviewSteps.get(0).getMarketId());
        reviewStepService.updateFlowStep(flowStep);
        for (ReviewStep reviewStep:reviewSteps ) {
            List<String> list = Arrays.asList(reviewStep.getApply().split(","));
            for (String orgUser : list) {
                String[] ou = orgUser.split(":");
                reviewStep.setOrgld(ou[0].toString());
                reviewStep.setOrgld(ou[1].toString());
                flag = reviewStepService.saveReviewStep(reviewStep);
                logger.info("编辑返回=====" + flag);
                if (flag > 0) {
                    result.setCode("200");
                } else {
                    result.setCode("600");
                    result.setMsg("请核对参数");
                }
            }
        }
        return result;
    }
    @RequestMapping(value = "/editReviewStep", method = RequestMethod.GET)
    public InterfaceResult editReviewStep(Integer id) {
        InterfaceResult result = new InterfaceResult();
        ReviewStep reviewStep = new ReviewStep();
        reviewStep.setId(id);
        try{
            List<ReviewStep> reviewStepList  = reviewStepService.selectStepList(reviewStep);
            for (ReviewStep review:reviewStepList ) {
                List<Map> list = userService.getUserOrgByReview(review);
                review.setUserOrg(list);
            }
            result.setData(reviewStepList);
            result.setCode("200");
        }catch (Exception e){
            result.setMsg("服务器错误");
            result.setCode("500");
            e.printStackTrace();
        }
        return result;
    }
}