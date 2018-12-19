package com.maxcar.market.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.AllParkingFeeResponse;
import com.maxcar.market.model.response.SumByParkingFeeDetailIdResponse;
import com.maxcar.market.pojo.ParkingFee;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.pojo.ParkingFeeIntegral;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ParkingFeeService extends BaseService<ParkingFee, String> {


    /**
     * param:
     * describe: 车辆入库
     * create_date:  lxy   2018/8/30  10:36
     **/
    boolean addParkingFeeDetail(AddParkingFeeDetailRequest request);

    /**
     * param:
     * describe: 车辆出库
     * create_date:  lxy   2018/8/30  10:45
     **/
    boolean updateParkingFeeDetail(ParkingFeeDetail parkingFeeDetail);

    /**
     * param:
     * describe: 更换班次
     * create_date:  lxy   2018/8/28  19:19
     **/
    ParkingFee updateParkingFee(UpdateParkingFeeRequest request);

    /**
     * param:
     * describe: 新建班次
     * create_date:  lxy   2018/8/29  10:32
     **/
    ParkingFee addParkingFee(AddParkingFeeRequest request);

    /**
     * param:
     * describe: 查看指定班次收费详情
     * create_date:  lxy   2018/8/29  10:23
     **/
    SumByParkingFeeDetailIdResponse getSumByParkingFeeDetailId(String parkingFeeId);

    /**
     * param:
     * describe: 查看停车收费列表
     * create_date:  lxy   2018/8/28  14:54
     **/
    AllParkingFeeResponse allParkingFee(AllParkingFeeRequest request);


    /**
     * param:
     * describe: 查看停车收费详情列表
     * create_date:  lxy   2018/8/28  19:00
     **/
    PageInfo<ParkingFeeDetail> allParkingFeeDetail(AllParkingFeeDetailRequest request);

    ParkingFeeDetail selectParkingFeeDetail(ParkingFeeDetail parkingFeeDetail);

    InterfaceResult getShift(String barrierId,String marketId,String userId,Integer pageNum,Integer pageSize) throws Exception;

    InterfaceResult getAllOrgByBaoAn(String marketId);

    InterfaceResult changeSure(JSONObject params) throws Exception;

    InterfaceResult getRecordByCardNo(JSONObject params) throws Exception;

    ParkingFeeDetail selectInParkingRecord(ParkingFeeDetail detail);

    int updateAfterTime(ParkingFeeDetail detail);

    InterfaceResult charge(JSONObject params)  throws Exception;

    InterfaceResult saveInParking(JSONObject params) throws Exception;

    ParkingFeeDetail selectDetail(ParkingFeeDetail detail);

    public List<ParkingFeeDetail> selectParkingFee(ParkingFeeDetail feeDetail);


    /**
     * 根据停车卡号获取该停车卡最后一条记录
     * @param parkingFeeDetail
     * @return
     */
    ParkingFeeDetail selectOneByCardNo(ParkingFeeDetail parkingFeeDetail);


    /**
     * 将此条记录设置为南通公众号会员停车记录
     * @param detail
     * @return
     */
    int updateByPrimaryKeySelective(ParkingFeeDetail detail);

    /**
     * 公众号对接获取停车收费信息
     * @param cardNo
     * @return
     */
    InterfaceResult getParkingFeeDetail(String cardNo) throws Exception;

    InterfaceResult testPrice(JSONObject params) throws Exception;

    /**
     * 获取当前时间下.每小时收费的金额
     * @param beforeDate 入场时间
     * @param currDate 当前时间
     * @param marketId 市场id
     * @return
     */
    int getCurrDateParkingFee(Date beforeDate,Date currDate,String marketId);


    /**
     * 支付成功之后更新积分表和停车记录详情表
     * @param parkingFeeIntegral
     * @return
     */
    InterfaceResult updateParkingFeeIntegralAndDetail(ParkingFeeIntegral parkingFeeIntegral) throws ParseException, Exception;

    InterfaceResult updateParkingDetail(JSONObject params) throws Exception;

    InterfaceResult goToWork(JSONObject params)  throws Exception;

    InterfaceResult goOffWork(JSONObject params)  throws Exception;

    InterfaceResult workDetail(JSONObject params)  throws Exception;

    InterfaceResult getWorkingTimeAndPrice(String marketId,String parkingFeeId) throws Exception;

    InterfaceResult getParkingFeeDetails(String id) throws Exception;

    InterfaceResult getEmergencyRelease(String marketId) throws Exception;
}
