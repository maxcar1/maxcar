package com.maxcar.core.utils.check;


import com.maxcar.core.exception.CheckDataMyException;
import com.maxcar.core.utils.ValidateUtils;

/**
 * 公共调用验证
 * @author huangxu
 *
 */
public class CheckCore {
	/**
	 * 对象验证
	 * @param t 对象
	 * @param msg  抛出的错误信息
	 * @return
	 * @throws CheckDataMyException
	 */
	public static <T> String checkObjIsNull(T t,String msg) throws CheckDataMyException {
		if(t ==null || t.equals("")) {
			throw new CheckDataMyException(msg);
		}
		return null;
	}
	/**
	 * 检查对象是否不等于null，用于检查对象是否重复等功能
	 * @param t
	 * @param msg
	 * @return
	 * @throws CheckDataMyException
	 */
	public static <T> String checkObjIsNutNull(T t,String msg) throws CheckDataMyException {
		if(t !=null && !t.equals("")) {
			throw new CheckDataMyException(msg);
		}
		return null;
	}
	/**
	 * 检查数据有效性
	 * @param str
	 * @param msg
	 * @return
	 * @throws CheckDataMyException
	 */
	public static String valiDateIsChar(String str,String msg) throws CheckDataMyException {
		if(!(ValidateUtils.isChar(str))){
			throw new CheckDataMyException(msg);
		}
		return null;
	}
	public static String valiDateIsDate(String str,String msg) throws CheckDataMyException {
		if(!(ValidateUtils.isDate(str))){
			throw new CheckDataMyException(msg);
		}
		return null;
	}
}
