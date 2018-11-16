package com.maxcar.kafka.util;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * impl层统一返回类型
 * @author ldc
 *
 */
public class Result implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resultCode;
	private String message;
	private Integer curPage;
	private Integer pageSize;
	private Integer total;
	private Object datas;
	
	public Result() {}


	public static Result ok() {		
		return ok(ResultCode.CREATE_SUCCESS.getCode(), "修改成功", null);
	}
	
	public static Result ok(Object datas) {		
		return ok(ResultCode.GET_SUCCESS.getCode(), "成功", datas);
	}

	public static Result ok(String message, Object datas) {	
		return ok(ResultCode.CREATE_SUCCESS.getCode(), message, datas);
	}

	public static Result ok(int resultCode, String message, Object datas) {
		String msg = message;
		if (StringUtils.isBlank(message)) {
			msg = "ok";
		}
		
		Result r = new Result();
		r.setResultCode(resultCode);
		r.setMessage(msg);
		r.setDatas(datas);
		return r;
	}

	public static Result error(String message) {			
		return error(ResultCode.ERROR.getCode(), message);
	}
	
	public static Result error(int resultCode, String message) {
		String msg = message;
		if (StringUtils.isBlank(message)) {
			msg = "error";
		}
		
		Result r = new Result();
		r.setResultCode(resultCode);
		r.setMessage(msg);
		return r;
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "Result [resultCode=" + resultCode + ", message=" + message + "]";
	}
	
	
}
