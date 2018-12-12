package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.user.entity.User;
import com.maxcar.websocket.server.WebSocketServer;
import com.maxcar.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api-p/parking")
public class ParkingFeeController extends BaseController {

    @Autowired
    private BarrierService barrierService;
    @Autowired
    private ParkingFeeService parkingFeeService;
    @Autowired
    private WeiXinService weiXinService;

    /**
     * 道闸列表接口
     *
     * @return
     */
    @Deprecated
    @GetMapping("/barriers")
    public Object getBarriers(HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        List<Barrier> barriers = barrierService.selectBarrierByMarketId(user.getMarketId());
        result.InterfaceResult200(barriers);
        return result;
    }

    /**
     * 根据mac查询道闸信息
     * @param request
     * @param barrierMac
     * @return
     * @throws Exception
     */
    @GetMapping("/barrier/{barrierMac}")
    public Object getBarrier(HttpServletRequest request,@PathVariable("barrierMac")String barrierMac) throws Exception {
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        Barrier barrier = new Barrier();
        barrier.setMarketId(user.getMarketId());
        barrier.setBarrierMac(barrierMac);
        Barrier bar = barrierService.selectBarrierByBarrierMac(barrier);
        result.InterfaceResult200(bar);
        return result;
    }

    /**
     * 根据卡号读取费用
     *
     * @param request
     * @param cardNo
     * @return
     */
    @GetMapping("/{cardNo}/{barrierId}")
    public Object getRecord(HttpServletRequest request, @PathVariable("cardNo") String cardNo,
                            @PathVariable("barrierId") String barrierId) throws Exception {
        JSONObject json = new JSONObject();
        User user = getCurrentUser(request);
        json.put("marketId", user.getMarketId());
        json.put("cardNo", cardNo);
        json.put("barrierId",barrierId);
        InterfaceResult result = parkingFeeService.getRecordByCardNo(json);
        return result;
    }

    /**
     * 轮班接口
     *
     * @return
     */
    @GetMapping("/shift/{barrierId}/{pageNum}/{pageSize}")
    @Deprecated
    public Object getShift(HttpServletRequest request, @PathVariable("barrierId") String barrierId,
                           @PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeService.getShift(barrierId, user.getMarketId(),
                user.getUserId(), pageNum, pageSize);
        return result;
    }

    /**
     * 确认换班接口
     *
     * @return
     */
    @PostMapping("/sure")
    @Deprecated
    public Object changeSure(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        params.put("userId", user.getUserId());
        InterfaceResult result = parkingFeeService.changeSure(params);
        return result;
    }

    /**
     * 岗亭人员接口
     *
     * @return
     */
    @PostMapping("/baoan")
    @Deprecated
    public Object getBaoAn(HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeService.getAllOrgByBaoAn(user.getMarketId());
        return result;
    }

    /**
     * 在线支付，现金支付
     * @param params
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping("/fee")
    public Object charge(@RequestBody JSONObject params,HttpServletRequest request) throws Exception{
        User user = getCurrentUser(request);
        //0支付宝支付  1  微信支付 2 现金支付 3公众号支付
        params.put("userId",user.getUserId());
        InterfaceResult result = parkingFeeService.charge(params);
        return result;
    }

    /**
     * 应急开闸
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/kz")
    public Object escapeHatch(HttpServletRequest request,@RequestBody JSONObject params)
            throws Exception{
        User user = getCurrentUser(request);
        JSONObject json = new JSONObject();
        if (null != user){
            json = (JSONObject) JSONObject.toJSON(user);
        }
        json.put("reason",params.getString("reason"));
        json.put("barrierId",params.getString("barrierId"));
        InterfaceResult result = weiXinService.escapeHatch(json);
        return  result;
    }

    /**
     * 上班登录
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/work/in/{barrierId}")
    public InterfaceResult goToWork(HttpServletRequest request,@PathVariable String barrierId)
            throws Exception{
        User user = getCurrentUser(request);
        JSONObject params = new JSONObject();

        if (null != user){
            params = (JSONObject) JSONObject.toJSON(user);
            params.put("barrierId",barrierId);
        }
        InterfaceResult result = parkingFeeService.goToWork(params);
        return result;
    }

    /**
     * 下班详情
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/work/detail")
    public InterfaceResult workDetail(HttpServletRequest request,@RequestBody JSONObject params)throws Exception{
        User user = getCurrentUser(request);
        if (null != user){
            params.put("marketId",user.getMarketId());
            params.put("userId",user.getUserId());
        }
        InterfaceResult result = parkingFeeService.workDetail(params);
        return result;
    }

    /**
     * 下班接口
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/work/off/{barrierId}")
    public InterfaceResult goOffWork(HttpServletRequest request,@PathVariable String barrierId) throws Exception{
        User user = getCurrentUser(request);
        JSONObject params = new JSONObject();
        if (null != user){
            params = (JSONObject) JSONObject.toJSON(user);
            params.put("barrierId",barrierId);
        }
        InterfaceResult result = parkingFeeService.goOffWork(params);
        return result;
    }

    @GetMapping("/working/{parkingId}")
    public InterfaceResult getWorkingTimeAndPrice(HttpServletRequest request,
                                                  @PathVariable("parkingId") String parkingId) throws Exception{
        User user = getCurrentUser(request);
        InterfaceResult result =  null;
        if (null != user){
             result = parkingFeeService.getWorkingTimeAndPrice(user.getMarketId(),parkingId);
        }
        return result;
    }
}
