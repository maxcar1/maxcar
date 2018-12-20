package com.maxcar.review.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.entity.Response.*;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.*;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/auditing")
public class AuditingController extends BaseController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarReviewService carReviewService;
    @Autowired
    private CarBaseService carBaseService;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private ReviewStepService reviewStepService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationsService organizationsService;
    @Autowired
    private ReviewDetailService reviewDetailService;
    @Autowired
    private MessageProducerService messageProducerService;
    @Autowired
    private StaffService staffService;

    @RequestMapping("/checkPendinglist")
    @OperationAnnotation(title = "车辆出场审核待审核列表")
    public InterfaceResult getCarReview(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        User user = getCurrentUser(request);
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = null;
        carParams.setReviewPersonId(user.getUserId());
        ReviewStep reviewStep = new ReviewStep();
        reviewStep.setReviewPersonId(user.getUserId());
        reviewStep.setMarketId(user.getMarketId());
        reviewStep.setOrgld(user.getOrgId());
        List<ReviewStep> reviewStepList = reviewStepService.reviewStepList(reviewStep);
        //查询该用户是否在审核列表下
        carParams.setMarket(user.getMarketId());
        pageInfo = carService.listReview(carParams);
        logger.info("=============="+pageInfo.getList());
        //过滤相同人不同审核等级
        List<CarVo> list =  pageInfo.getList();

        if(reviewStepList!=null){
            //判断该用户属于第几级
            pageInfo = carService.listReview(carParams);

        }

        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @RequestMapping("/carReviewDetail/{reviewId}")
    @OperationAnnotation(title = "车辆出场审核详情")
    public InterfaceResult carReviewDetail(@PathVariable String reviewId,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = new HashMap<>();
        User user = getCurrentUser(request);
        CarVo c = new CarVo();
        c.setReviewId(reviewId);
        CarReview carReview = carReviewService.getCarReview(c);
        if(carReview != null){
            User u= userService.selectByPrimaryKey(carReview.getUserId());
            Staff staff = staffService.selectByPrimaryKey(u.getStaffId());
            if(staff != null){
                carReview.setUserName(staff.getStaffName());
            }
            map.put("carReview",carReview);
            List<CarDetails> list = carBaseService.getCarBaseById(carReview.getCarId());
            CarDetails carDetails = list.get(0);
            if(carDetails != null){
                UserTenant userTenant = userTenantService.selectByPrimaryKey(carDetails.getTenant());

                if(userTenant!=null){
                        carDetails.setTenantName(userTenant.getTenantName());
                }
                map.put("car",carDetails);
            }
            if(carDetails.getTenant() != null){
                CarDataStatistics carDataStatistics = carService.getCarDataStatistics(carDetails.getTenant(),user.getMarketId());
                CarDataStatistics dataStatistics = carService.carData(carDetails.getTenant(),user.getMarketId());
                CarDataStatistics statistics = new CarDataStatistics();
                statistics.setInventoryTotal(carDataStatistics.getInventoryTotal());//库存总数
                statistics.setInventoryValuation(carDataStatistics.getInventoryValuation());//库存估价
                statistics.setPledgeCarTotal(carDataStatistics.getPledgeCarTotal());//质押车数量
                statistics.setPledgeCarValuation(carDataStatistics.getPledgeCarValuation());//质押车估价
                statistics.setPresencePledgeCar(dataStatistics.getPresencePledgeCar());//在场质押车
                statistics.setPresencePledgeCarValuation(dataStatistics.getPresencePledgeCarValuation());//在场质押车估价
                map.put("carDataStatistics",statistics);
            }
        }
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }

    @RequestMapping("/reviewProcess/{reviewId}/{applyType}")
    @OperationAnnotation(title = "审核流程列表")
    public InterfaceResult getReviewProcess(@PathVariable Integer reviewId,@PathVariable Integer applyType,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        ReviewVo reviewVo = new ReviewVo();
        reviewVo.setReviewId(reviewId);
        reviewVo.setMarketId(user.getMarketId());
        reviewVo.setApplyType(applyType);
        List<ReviewVo> list = reviewStepService.reviewVolist(reviewVo);
        Integer [] arr = new Integer[]{1,2,3,4,5};
        List<Map> lists = new ArrayList<Map>();
        for(int i=0;i<arr.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            List l = null;
            for(ReviewVo r:list){
                Organizations organizations = organizationsService.selectByPrimaryKey(r.getOrgId());
                r.setOrgName(organizations.getOrgName());
                User u = new User();
                u.setUserId(r.getReviewPersonId());
                List<User> userList = userService.getUserList(u);
                r.setReviewPersonName(userList.get(0).getTrueName());
                if(r.getLevel() == arr[i]){
                    Integer level =(Integer) map.get("level");
                    if (null == level || r.getLevel() != level){
                        l = new ArrayList();
                    }
                    map.put("level",r.getLevel());
                    l.add(r);
                    map.put("list",l);

                }
            }
            lists.add(map);
        }
        List<Map> l = new ArrayList<Map>();
        for(Map map:lists){
            if(!map.isEmpty()){
                l.add(map);
            }
        }
        interfaceResult.InterfaceResult200(l);
        return interfaceResult;
    }

    @RequestMapping("/carList")
    @OperationAnnotation(title = "商户库存车信息列表")
    public InterfaceResult getcarList(@RequestBody CarVo carVo,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        carVo.setMarketId(user.getMarketId());
        PageInfo pageInfo = carService.listCarVo(carVo);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @RequestMapping("/saveReviewDetail")
    @OperationAnnotation(title = "提交审核结果")
    public InterfaceResult saveReviewDetail(@RequestBody ReviewDetail reviewDetail,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        CarReview carReviewNew = new CarReview();
        int level = reviewDetail.getLevel()+1;
        reviewDetail.setReviewPersonId(user.getUserId());
        reviewDetail.setInsertTime(new Date());
        reviewDetail.setLevel(level);
        carReviewNew.setStepLevel(level);
        boolean b = reviewDetailService.saveReviewDetail(reviewDetail);
        //如果审核不通过直接修改car_review状态
        if(reviewDetail.getReviewResult()==2){
            reviewDetail.setIsComplete(1);
            reviewDetailService.updateReviewStatus(reviewDetail);
            CarReview carReview = new CarReview();
            carReview.setId(reviewDetail.getReviewId());
            carReview.setIsPass(reviewDetail.getReviewResult());
            carReview.setStepLevel(level);
            String topic = topicService.getTopic(user.getMarketId());
            //同步删除本地车辆状态
            //组装云端参数
            PostParam postParam = new PostParam();
            postParam.setData(JsonTools.toJson(carReview));
            postParam.setMarket(user.getMarketId());
            postParam.setUrl("/barrier/carReview/saveOrUpdate");
            postParam.setMethod("post");
            postParam.setOnlySend(false);
            postParam.setMessageTime(Constants.dateformat.format(new Date()));
            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        }
        interfaceResult.InterfaceResult200(b);
        //修改car_review状态   判断会签或签

            ReviewStep reviewLevel = new ReviewStep();
            reviewLevel.setLevel(level);
            reviewLevel.setMarketId(user.getMarketId());
            reviewLevel.setReviewPersonId(user.getUserId());
            reviewLevel.setApplyType(reviewDetail.getCarStatus());
            List<ReviewStep> reviewStepList = reviewStepService.selectStepList(reviewLevel);
            //判断该车最多多少级审核
            int lastLevel = reviewStepService.selectLastStep(reviewLevel);

            //1或签     2会签
            if(reviewStepList.get(0).getType()==1){
                ReviewDetail review = new ReviewDetail();
                review.setReviewResult(1);
                review.setLevel(level);
                review.setReviewId(reviewDetail.getReviewId());
                ReviewDetail list = reviewDetailService.selectReviewDetail(reviewDetail);
                if(list!=null){
                    if(lastLevel==level){
                        reviewDetail.setReviewResult(1);
                        CarReview carReview = new CarReview();
                        carReview.setId(reviewDetail.getReviewId());
                        carReview.setIsPass(reviewDetail.getReviewResult());
                        carReview.setStepLevel(level);
                        String topic = topicService.getTopic(user.getMarketId());
                        //同步删除本地车辆状态
                        //组装云端参数
                        PostParam postParam = new PostParam();
                        postParam.setData(JsonTools.toJson(carReview));
                        postParam.setMarket(user.getMarketId());
                        postParam.setUrl("/barrier/carReview/saveOrUpdate");
                        postParam.setMethod("post");
                        postParam.setOnlySend(false);
                        postParam.setMessageTime(Constants.dateformat.format(new Date()));
                        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
                    }else{
                        reviewDetail.setReviewResult(null);
                    }
                    reviewDetailService.updateReviewStatus(reviewDetail);

                }
            }else{
                List<ReviewDetail> list = reviewDetailService.getReviewDetail(reviewDetail);
                if(reviewStepList.size()==list.size()){
                    ReviewDetail review = new ReviewDetail();
                    review.setLevel(level);
                    review.setReviewId(reviewDetail.getReviewId());
                    if(lastLevel==level){
                        review.setReviewResult(1);
                        CarReview carReview = new CarReview();
                        carReview.setId(review.getReviewId());
                        carReview.setIsPass(review.getReviewResult());
                        carReview.setStepLevel(review.getLevel());
                        String topic = topicService.getTopic(user.getMarketId());
                        //同步删除本地车辆状态
                        //组装云端参数
                        PostParam postParam = new PostParam();
                        postParam.setData(JsonTools.toJson(carReview));
                        postParam.setMarket(user.getMarketId());
                        postParam.setUrl("/barrier/carReview/saveOrUpdate");
                        postParam.setMethod("post");
                        postParam.setOnlySend(false);
                        postParam.setMessageTime(Constants.dateformat.format(new Date()));
                        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
                    }else{
                        review.setReviewResult(null);
                    }
                    reviewDetailService.updateReviewStatus(review);


                }
            }

        return interfaceResult;
    }

    @RequestMapping("/reviewDetailList")
    @OperationAnnotation(title = "车辆出场审核已审核列表")
    public InterfaceResult carReviewDetailList(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if (carParams.getMarket() == null){
            carParams.setMarket(user.getMarketId());
        }
        PageInfo pageInfo = carService.carReviewDetailList(carParams);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @RequestMapping("/export")
    @OperationAnnotation(title = "导出")
    public InterfaceResult export(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        List<CarVo> list = carService.exportList(carParams);
        List<ExportReviewResponse> exportList = new ArrayList<>();
        for(CarVo carVo:list){
            ExportReviewResponse exportReviewResponse = new ExportReviewResponse();
            exportReviewResponse.setBrandName(carVo.getBrandName() + "-" +carVo.getSeriesName());
            exportReviewResponse.setModelName(carVo.getModelName());
            exportReviewResponse.setTenantName(carVo.getTenantName());
            exportReviewResponse.setInsertTime(carVo.getApplicationTime()==null?"":carVo.getApplicationTime());
            exportReviewResponse.setCarStatus(carVo.getCarStatus()==1?"质押":"非质押");
            exportReviewResponse.setOutReason(carVo.getOutReason());
            if(carVo.getEvaluatePrice() == null){
                exportReviewResponse.setEvaluatePrice(0.00);
            }else{
                exportReviewResponse.setEvaluatePrice(carVo.getEvaluatePrice().doubleValue());
            }
            if(carVo.getIsPass()!=null){
                exportReviewResponse.setReviewResult(carVo.getIsPass()==1?"审核通过":"审核不通过");
            }
            if(carVo.getStockStatus()!=null){
                exportReviewResponse.setStockStatus(carVo.getStockStatus()==1?"在场":"出场");
            }

            exportReviewResponse.setVin(carVo.getVin());
            exportList.add(exportReviewResponse);
        }
        interfaceResult.InterfaceResult200(exportList);
        return interfaceResult;
    }


    @RequestMapping("/detail/{reviewId}/{carId}")
    @OperationAnnotation(title = "审核结果")
    public InterfaceResult carReviewDetailList(@PathVariable String reviewId,@PathVariable String carId, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User u = getCurrentUser(request);
        Map<String,Object> map = new HashMap<>();
        CarVo carVo = new CarVo();
        carVo.setReviewId(reviewId);
        CarReview carReview = carReviewService.getCarReview(carVo);
        if(carReview != null){
            User user = new User();
            user.setUserId(carReview.getUserId());
            List<User> userList = userService.getUserList(user);
            User user1= userService.selectByPrimaryKey(userList.get(0).getUserId());
            Staff staff = staffService.selectByPrimaryKey(user1.getStaffId());
            carReview.setUserName(staff.getStaffName());
            map.put("carReview",carReview);
        }
        ReviewDetail rd = reviewDetailService.reviewDetail(reviewId);
        map.put("reviewDetails",rd);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }


    @RequestMapping("/carRecord/{reviewId}/{carId}")
    @OperationAnnotation(title = "车辆出入场记录")
    public InterfaceResult carRecord(@PathVariable String reviewId,@PathVariable String carId,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        CarReview carReview =  new CarReview();
        carReview.setId(reviewId);
        carReview.setCarId(carId);
        List<CarRecordVo> list = carReviewService.getCarRecord(carReview);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }
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
        String reviewId = reviewStep.getReviewId();
        Integer level = reviewStepService.selectCarReview(reviewId);
        try{
            FlowStep step = reviewStepService.selectReviewManageByReviewStep(reviewStep);
            List<ReviewStep> reviewStepListLevel  = reviewStepService.selectStepListByLevel(reviewStep);
            logger.info("reviewStepListLevel==================="+reviewStepListLevel.size());
            for (ReviewStep reviewLevel:reviewStepListLevel) {

                List<ReviewStep> reviewStepList = reviewStepService.selectStepList(reviewLevel);
                logger.info("reviewStepListLevel==================="+reviewStepListLevel.size());
                List list = new ArrayList();
                for (ReviewStep review:reviewStepList) {
                    Map user = userService.getUserOrgByReview(review.getReviewPersonId(), review.getOrgId());
                    review.setReviewId(reviewId);
                    Integer reviewResult = reviewStepService.getReviewResult(review);
                    user.put("result",reviewResult);
                    list.add(user);
                }
                reviewLevel.setUserOrg(list);
            }
            map.put("list",reviewStepListLevel);

            map.put("step" ,step);
            map.put("level",level);

            result.setData(map);
            result.setCode("200");
        }catch (Exception e){
        }
        return result;
    }

}
