package com.maxcar.core.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 封装返回数据工具类
* @ClassName: ResultUtils 
* @author huangxu 
* @date 2018年1月16日 下午2:24:33 
*
 */
public class ResultUtils {
	/**
	 * 检查返回数据是否正确
	 * @param result
	 * @return
	 */
	public static boolean resultIsNotNull(Result result) {
		if(result != null && result.getResultCode() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 返回调用成功的数据
	 * @param <T>
	 * @param result
	 * @return
	 */
	public static Object getResultDatas(Result result) {
		if(resultIsNotNull(result)) {
			Object object = result.getDatas();
			return object;
		}
		return null;
	}
}
