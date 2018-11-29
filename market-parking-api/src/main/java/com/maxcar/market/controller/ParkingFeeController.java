package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.user.entity.User;
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
    @GetMapping("/barriers")
    public Object getBarriers(HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        List<Barrier> barriers = barrierService.selectBarrierByMarketId(user.getMarketId());
        result.InterfaceResult200(barriers);
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
        //1  在线支付 2 现金支付 3公众号支付
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
        params.put("marketId",user.getMarketId());
        InterfaceResult result = weiXinService.escapeHatch(params);
        return  result;
    }
}
