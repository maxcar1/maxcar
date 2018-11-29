package com.maxcar.core.utils;
/**
 * 静态常量
 * @author huangxu
 *
 */
public interface Constants {
	/**
	 * ResponseContent 返回值
	 */
	final static int RESULT_CODE_ERROR_1 = 1;
	final static int RESULT_CODE_ERROR_2 = 2;
	final static int RESULT_CODE_SUCCESS = 0;

	final static String STOCK_TIME = "stock_time";//库存周期
	final static String TYPE_ID = "type_id";//车辆使用性质
	final static String YEAR_ID = "year_id";//车辆年限
	final static String SOURCE_ID = "source_id";//车辆来源
	final static String CHANNEL_ID = "channel_id";//交易渠道
	final static String USE_ID = "use_id";//使用性质
	final static String BRAND_ID = "brand_id";



	final static String ADMINISTRATOR_ID = "100";//超级管理员菜单权限
	final static String OUT_ID = "outside";//虚拟外部用户id
	final static String DATA_ID = "-100";//虚拟数据权限id
	final static String DATA_NAME = "虚拟数据权限";//虚拟数据权限id
}
