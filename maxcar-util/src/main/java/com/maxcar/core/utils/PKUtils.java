package com.maxcar.core.utils;

import com.maxcar.core.utils.redis.RedisUtil;
/**
 * 主键生产工具
* @ClassName: PKUtils 
* @author huangxu 
* @date 2017年11月13日 下午2:19:48 
*
 */
public class PKUtils {
	/**
	 * 生产数据库表主键
	 * @param tableName
	 * @return
	 */
	public static Long getId(String tableName) {
		return RedisUtil.getInstance().keys().incr("table_" + tableName);
	}
}
