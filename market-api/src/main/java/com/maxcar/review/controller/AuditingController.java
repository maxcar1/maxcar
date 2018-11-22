package com.maxcar.review.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.entity.Response.CarDataStatistics;
import com.maxcar.stock.entity.Response.CarDetails;
import com.maxcar.stock.entity.Response.ExportReviewResponse;
import com.maxcar.stock.entity.Response.ReviewVo;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.pojo.CarReview;
import com.maxcar.stock.pojo.ReviewDetail;
import com.maxcar.stock.service.*;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/checkPendinglist")
    @OperationAnnotation(title = "车辆出场审核待审核列表")
    public InterfaceResult getCarReview(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = carService.listReview(carParams);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }


    @RequestMapping("/carReviewDetail/{reviewId}")
    @OperationAnnotation(title = "车辆出场审核详情")
    public InterfaceResult carReviewDetail(@PathVariable Integer reviewId,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String, Object> map = new HashMap<>();
        CarVo c = new CarVo();
        c.setReviewId(reviewId);
        CarReview carReview = carReviewService.getCarReview(c);
        if(carReview != null){
            User user = new User();
            user.setUserId(carReview.getUserId());
            List<User> userList = userService.getUserList(user);
            carReview.setUserName(userList.get(0).getTrueName());
            map.put("carReview",carReview);
            List<CarDetails> list = carBaseService.getCarBaseById(carReview.getCarId());
            CarDetails carDetails = list.get(0);
            if(carDetails != null){
                map.put("car",carDetails);
            }
            if(carDetails.getTenant() != null){
                CarDataStatistics carDataStatistics = carService.getCarDataStatistics(carDetails.getTenant());
                CarDataStatistics dataStatistics = carService.carData(carDetails.getTenant());
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

    @RequestMapping("/reviewProcess/{reviewId}")
    @OperationAnnotation(title = "审核流程列表")
    public InterfaceResult getReviewProcess(@PathVariable Integer reviewId,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        ReviewVo reviewVo = new ReviewVo();
        reviewVo.setReviewId(reviewId);
        reviewVo.setMarketId(user.getMarketId());
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
                user.setUserId(r.getReviewPersonId());
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
        PageInfo pageInfo = carService.listCarVo(carVo);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @RequestMapping("/saveReviewDetail")
    @OperationAnnotation(title = "提交审核结果")
    public InterfaceResult saveReviewDetail(@RequestBody ReviewDetail reviewDetail,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        reviewDetail.setReviewPersonId(user.getUserId());
        boolean b = reviewDetailService.saveReviewDetail(reviewDetail);
        interfaceResult.InterfaceResult200(b);
        return interfaceResult;
    }

    @RequestMapping("/reviewDetailList")
    @OperationAnnotation(title = "车辆出场审核已审核列表")
    public InterfaceResult carReviewDetailList(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = carService.carReviewDetailList(carParams);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }


    @RequestMapping("/export")
    @OperationAnnotation(title = "导出")
    public InterfaceResult export(@RequestBody CarParams carParams, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        List<CarVo> list = carService.exportList(carParams);
        List<ExportReviewResponse> exportList = new ArrayList<>();;
        for(CarVo carVo:list){
            ExportReviewResponse exportReviewResponse = new ExportReviewResponse();
            exportReviewResponse.setBrandName(carVo.getBrandName() + "-" +carVo.getSeriesName());
            exportReviewResponse.setModelName(carVo.getModelName());
            exportReviewResponse.setTenantName(carVo.getTenantName());
            exportReviewResponse.setInsertTime(carVo.getReviewInsertTime()==null?"":carVo.getReviewInsertTime());
            exportReviewResponse.setCarStatus(carVo.getCarStatus()==1?"质押":"非质押");
            exportReviewResponse.setOutReason(carVo.getOutReason());
            if(carVo.getEvaluatePrice() == null){
                exportReviewResponse.setEvaluatePrice(0.00);
            }else{
                exportReviewResponse.setEvaluatePrice(carVo.getEvaluatePrice().doubleValue());
            }
            exportReviewResponse.setReviewResult(carVo.getReviewResult()==1?"审核通过":"审核不通过");
            exportReviewResponse.setStockStatus(carVo.getStockStatus()==1?"在场":"出场");
            exportReviewResponse.setVin(carVo.getVin());
            exportList.add(exportReviewResponse);
        }
        interfaceResult.InterfaceResult200(exportList);
        return interfaceResult;
    }


    @RequestMapping("/detail/{reviewId}")
    @OperationAnnotation(title = "审核结果")
    public InterfaceResult carReviewDetailList(@PathVariable Integer reviewId,HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

        return interfaceResult;
    }



}
