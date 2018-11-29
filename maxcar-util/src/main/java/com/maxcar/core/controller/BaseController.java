package com.maxcar.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.maxcar.core.utils.Result;
/**
 * 父类controller
* @ClassName: BaseController 
* @author huangxu 
* @date 2018年1月16日 下午5:07:48 
*
 */
public class BaseController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	public String userLoginId;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取项目部署的URI基地址
	 * 
	 * @author boansen
	 * @return
	 */
	protected String getBasePath() {
		String path = request.getContextPath();
		int port = request.getServerPort();
		String basepath = "";
		if (port == 80) {
			basepath = request.getScheme() + "://" + request.getServerName() + path;
		} else {
			basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		}
		return basepath;
	}

	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	protected Result renturnError() {
		Result result = new Result();
		result.setMessage("success");
		return result;
	}

	/**
	 * 渲染失败数据（带消息）
	 *
	 * @param msg
	 *            需要返回的消息
	 * @return result
	 */
	protected Result renderError(String msg, Integer code) {
		Result result = renturnError();
		result.setMessage(msg);
		result.setResultCode(code);
		return result;
	}

	/**
	 * 渲染成功数据（带信息）
	 *
	 * @param msg
	 *            需要返回的信息
	 * @return result
	 */
	protected Result renderSuccess(int resultCode) {
		Result result = new Result();
		result.setResultCode(resultCode);
		return result;
	}

	/**
	 * 渲染成功数据（带数据）
	 *
	 * @param obj
	 *            需要返回的对象
	 * @return result
	 */
	protected Result renderSuccessData(Object obj, String msg,int code) {
		Result result = returnSuccess(code);
		result.setMessage(msg);
		result.setDatas(obj);
		return result;
	}
	protected Result renderSuccessList(Object obj, String msg,int code) {
		Result result = returnSuccess(code);
		result.setMessage(msg);
		result.setList(obj);
		return result;
	}
	protected Result renderSuccessItem(Object obj, String msg,int code) {
		Result result = returnSuccess(code);
		result.setMessage(msg);
		result.setItem(obj);
		return result;
	}
	public Result returnSuccess(int code) {
		Result result = new Result();
		result.setResultCode(code);
		return result;
	}
	/**
	 *
	 * 返回 JSON 格式对象
	 *
	 * @param object
	 *            转换对象
	 * @param format
	 *            序列化特点
	 * @return
	 */
	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 *
	 * 返回 JSON 格式对象
	 *
	 * @param object
	 *            转换对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	protected String booleanToString(boolean rlt) {
		return rlt ? "true" : "false";
	}

}
