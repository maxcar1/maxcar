package com.maxcar.core.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***
 * 分页计算
 * @author Administrator
 *
 */
public class PageUtils {
	/**
	 * 根据页码计算数据查询起始位置
	 * @return
	 */
	
	public static Integer validatePage(Integer curPage,Integer pageSize){
		if(null == curPage | "".equals(curPage) || curPage.intValue()<=1){
			return 0;
		}
		curPage = (curPage-1)*pageSize;
		return curPage;
	}
	
	/**
	 * 对数据进行分页处理
	 */
	public static Result savePage(Integer curPage,Integer pageSize,Integer total,List<?> object){
		Result result = new Result();
		result.setCurPage((null == curPage || 0 == curPage) ? 1 : curPage);
		result.setPageSize((pageSize.intValue() < 1 ? 10 : pageSize.intValue()));
		result.setTotal(total);
		result.setList(object);
		return result;
		
	}

}
