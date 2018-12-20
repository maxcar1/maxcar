package com.maxcar.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 静态常量
 * @author 罗顺锋
 *
 */
public interface Constants {
	/**
	 * ResponseContent 返回值
	 */
	final static int RESULT_CODE_ERROR_1 = 1;
	final static int RESULT_CODE_ERROR_2 = 2;
	final static int RESULT_CODE_SUCCESS = 0;


	final static String KAFKA_SASS = "SASS";//卡夫卡数据服务类型

	final static String STOCK_TIME = "stock_time";//库存周期
	final static String TYPE_ID = "type_id";//车辆使用性质
	final static String YEAR_ID = "year_id";//车辆年限
	final static String SOURCE_ID = "source_id";//车辆来源
	final static String CHANNEL_ID = "channel_id";//交易渠道
	final static String USE_ID = "use_id";//使用性质
	final static String BRAND_ID = "brand_id";

	final static String PROPERTY_DOC_URL = "property_doc_url";// 合同存放路径
	final static String PROPERTY_IMG_URL = "property_img_url";// 合同转image存放路径
	final static String PROPERTY_PDF_URL = "property_pdf_url";// 合同pdf存放路径


	//车型
	final static String CAR_LEVEL = "level";
	//排放标准
	final static String ENVIRONMENTAL_STANDARDS = "environmental_standards";
	//物业类型
	final static String PROPERTY_TYPE = "property_type";
	//商户类型
	final static String TENANT_TYPE = "tenant_type";

	final static String ADMINISTRATOR_ID = "100";//超级管理员菜单权限
	final static String OUT_ID = "outside";//虚拟外部用户id
	final static String DATA_ID = "-100";//虚拟数据权限id
	final static String DATA_NAME = "虚拟数据权限";//虚拟数据权限id
	//销售状态
	final static List<Integer> salesList = new ArrayList<>(Arrays.asList(4,5));
	//库存状态
	final static List<Integer> stockList = new ArrayList<>(Arrays.asList(1,2,3));
	final static Integer deleteType = -1;//删除
	final static Integer inType = 1;//在场
	final static Integer inInType = 2;//在nei 场
	final static Integer outType = 3;//出场
	final static Integer saleType = 4;//售出
	final static Integer saleOutType = 5;//售出已出场
	final static Integer zhiya = 1;//质押

	final static String[] outStatus = {"3"}; // 出场状态

	final static String[] delAndSoldStatus = {"-1","4","5"};// 删除和售出状态

	final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	final static long freeTime = 15 * 60 * 1000;

	/**
	 * 每个IP 5分钟内 限制发送20次
	 */
	int IP_SEND_TIMES = 20;

	/**
	 * staff_token生命周期 一周
	 */
	int STAFF_TOKEN_TIMEOUT = 7 * 24 * 3600;

	/**
	 * 会员免费停车时间毫秒值
	 */
	long VIP_PARK_TIMEOUT = 4*60*60*1000;


	//淘宝编号
	final String TAO_BAO_CHANNEL_NO = "002";
}
