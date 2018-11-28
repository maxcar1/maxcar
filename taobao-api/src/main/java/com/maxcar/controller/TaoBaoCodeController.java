package com.maxcar.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maxcar.core.exception.ResultCode;
import com.maxcar.core.utils.Result;
import com.maxcar.core.utils.StringUtils;
import com.maxcar.service.TaoBaoService;

import net.sf.json.JSONObject;
/**
 * 获取回调地址的参数
* @ClassName: TaoBaoCodeController 
* @author huangxu 
* @date 2018年4月9日 下午2:40:59 
*
 */
@RestController
@RequestMapping("/tbapi")
public class TaoBaoCodeController {
	private static Logger logger = LoggerFactory.getLogger(TaoBaoCodeController.class);
	
	@Autowired
	private TaoBaoService taoBaoService;
	/**
	 * 根据返回的code获取sessionkey，保存到缓存中
	 * 为了避免客户多次请求，第一次请求成功之后就不允许再次提交
	 * 缓存命名规则
	 * taobao_市场编号_sessionkey:sessionkey

	 * @return
	 */
	@RequestMapping(value="/codes",method=RequestMethod.GET)
	public Result getSessionKeyByCode(HttpServletRequest req) {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		Result result = new Result();
		if(null != req.getParameter("error_description")){
			result.setMessage("全车通服务已到期，请续费!");
			result.setResultCode(ResultCode.ERROR.getCode());
		}
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(state)) {
			if (!RedisUtil.getInstance().keys().exists("taobao_" + state + "_sessionkey")) {
				result = taoBaoService.getOpenOauth(code);
				if (result.getResultCode() == ResultCode.GET_SUCCESS.getCode()) {
					JSONObject json = (JSONObject) result.getItem();
					if (!json.isEmpty() && !json.get("access_token").equals("")) {
						RedisUtil.getInstance().strings().set("taobao_" + state + "_sessionkey",json.getString("access_token"));
					}
				}
			} else {
				result.setMessage("已经生产sessionKey，不要多次申请");
				result.setResultCode(ResultCode.NOT_ACCEPTABLE.getCode());
			}
		} else {
			result.setMessage("参数有遗漏，请联系管理员核实url");
			result.setResultCode(ResultCode.NOT_ACCEPTABLE.getCode());
		}
		return result;
	}
}
