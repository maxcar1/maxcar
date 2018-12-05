package com.maxcar.review.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.FlowStep;
import com.maxcar.stock.pojo.ReviewStep;
import com.maxcar.stock.service.ReviewStepService;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.UserService;
import org.json.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ReviewStepManageController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageProducerService messageProducerService;

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
        Map map =new HashMap();
        try{
            FlowStep step = reviewStepService.selectReviewManageByReviewStep(reviewStep);
            List<ReviewStep> reviewStepListLevel  = reviewStepService.selectStepListByLevel(reviewStep);
            logger.info("reviewStepListLevel==================="+reviewStepListLevel.size());
            for (ReviewStep reviewLevel:reviewStepListLevel) {
                String apply ="";
                List<ReviewStep> reviewStepList = reviewStepService.selectStepList(reviewLevel);
                logger.info("reviewStepListLevel==================="+reviewStepListLevel.size());
                List list = new ArrayList();
                for (ReviewStep review:reviewStepList) {
                    Map user = userService.getUserOrgByReview(review.getReviewPersonId(), review.getOrgId());
                    apply += user.get("true_name")+"("+user.get("org_name")+") ";
                    list.add(user);
                }
                reviewLevel.setApply(apply);
                reviewLevel.setUserOrg(list);
            }
            map.put("list",reviewStepListLevel);
            map.put("step" ,step);
            result.setData(map);
            result.setCode("200");
        }catch (Exception e){
            e.printStackTrace();
            result.InterfaceResult500("服务器异常");
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
        FlowStep flowStep = reviewStepService.selectFlowStepByPrimarykey(id);
        int flag = 0;
        if (flowStep != null){
            reviewStepService.deleteReviewStep(flowStep);
            flag = reviewStepService.deleteReviewManage(id);
        }
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
                List<ReviewStep> reviewSteps = reviewStepService.selectStepListBySomeParams(reviewStep);
                if (reviewSteps != null && reviewSteps.size() > 0) {
                    reviewStepService.deleteByReview(reviewStep);
                }
                for (String orgUser : list) {
                    String[] ou = orgUser.split(":");
                    reviewStep.setOrgld(ou[0].toString());
                    reviewStep.setApplyType(reviewStep.getApplyType());
                    reviewStep.setMarketId(reviewStep.getMarketId());
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
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除步骤
     * @param reviewStep
     * @return
     */
    @RequestMapping(value = "/deleteStep", method = RequestMethod.POST)
    public InterfaceResult deleteStep(@RequestBody  ReviewStep reviewStep) {
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

        }
        return result;
    }

    /**
     * 保存步骤信息
     * @param json
     * @return
     */
    @RequestMapping(value = "/saveStepList", method = RequestMethod.POST)
    public InterfaceResult saveStepList(@RequestBody net.sf.json.JSONObject json) {
        logger.info("保存步骤=========="+json);
        InterfaceResult result = new InterfaceResult();
        FlowStep flowStep = new FlowStep();
        int flag = 0;
        try {

            flowStep.setCode(json.getInt("code"));
            flowStep.setMarketId(json.getString("marketId"));
            flowStep.setReviewType(json.getInt("reviewType"));
            flowStep.setIsNeedReview(json.getInt("isNeedReview"));
            flag = reviewStepService.updateFlowStep(flowStep);
            reviewStepService.deleteReviewStep(flowStep);
            String jsonStr = json.getString("tableData");
            logger.info("========"+jsonStr);
            JSONArray jsonArray = JSONArray.parseArray(jsonStr);
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
                ReviewStep  review= new ReviewStep();
                JSONObject jsonObject = (JSONObject) iterator.next();
                review.setStepName(jsonObject.getString("stepName"));
                review.setMarketId(jsonObject.getString("marketId"));
                review.setApplyType(jsonObject.getInteger("applyType"));
                review.setType(jsonObject.getInteger("type"));
                review.setLevel(jsonObject.getInteger("level"));
                JSONArray jsonAr = JSONArray.parseArray(jsonObject.getString("userOrg"));
                for (Iterator iter = jsonAr.iterator(); iter.hasNext();) {
                    JSONObject jsonOb = (JSONObject) iter.next();
                    review.setOrgld(jsonOb.get("org_id").toString());
                    review.setReviewPersonId(jsonOb.get("user_id").toString());
                     flag = reviewStepService.saveReviewStep(review);
                }
            }


        if(flag>0){
            result.setCode("200");
        }else {
            result.setCode("600");
            result.setMsg("请检查参数");
        }
        }catch (Exception e){

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
                Map list = userService.getUserOrgByReview(review.getReviewPersonId(), review.getOrgId());
                //review.setUserOrg(list);
            }
            result.setData(reviewStepList);
            result.setCode("200");
        }catch (Exception e){

        }
        return result;
    }

    /**
     * 提交出厂申请
     * @param carReview
     * @return
     */
    @RequestMapping(value = "/carOutApply", method = RequestMethod.POST)
    public InterfaceResult carOutApply(@RequestBody CarReview carReview, HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        User user = super.getCurrentUser(request);
        int flag = 0;
        carReview.setIsPass(0);
        flag  = reviewStepService.checkCarReview(carReview);
        if(flag>0){
            result.setCode("600");
            result.setMsg("该车已经提交过申请，不能重复提交");
            return result;
        }
        carReview.setInsertTime(new Date());
        carReview.setIsValid(1);
        flag = reviewStepService.carOutApply(carReview);
        String topic = super.getTopic(user.getMarketId());
        //同步删除本地车辆状态
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(JsonTools.toJson(carReview));
        postParam.setMarket(user.getMarketId());
        postParam.setUrl("");
        postParam.setMethod("post");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        if(flag>0){
            result.setCode("200");
        }else {
            result.setCode("600");
            result.setMsg("请检查参数");
        }
        return  result;
    }

}