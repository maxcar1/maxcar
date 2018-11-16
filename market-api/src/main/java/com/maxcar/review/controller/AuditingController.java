package com.maxcar.review.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.vo.CarVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auditing")
public class AuditingController extends BaseController {

    @RequestMapping("/list")
    public InterfaceResult getCarReview(@RequestBody CarVo carVo, HttpServletRequest request ) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

        return interfaceResult;
    }

}
