package com.maxcar.core.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * 市场数据同步到云端时将jsonarray转换为list
 * @author ldc
 *
 */
public class SyncDataConvert {
	
	/**
	 * 将jsonarray转换为list
	 * @param array
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String,Object>> dataConvert(JSONArray array){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0;i<array.size();i++){
			list.add((Map)JSONObject.toBean(JSONObject.fromObject(array.get(i)),Map.class));
			//处理下Date 和  timestamp类型的字段
			for(Map.Entry<String,Object> m:list.get(i).entrySet()){
				try {
					if(null == m.getValue() || "".equals(m.getValue().toString().trim()) || "null".equals(m.getValue().toString().trim())){
						m.setValue(null);
					}
					else{
						//m.setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(JSONObject.fromObject(m.getValue()).getLong("time"))));
						m.setValue(new Date(JSONObject.fromObject(m.getValue()).getLong("time")));
					}
				} catch (Exception e) {
				}
			}
		}
		return list;
	}
	
	public static List<Map<String,String>> convertToMap(JSONArray array){		
        if (array == null || array.isEmpty()) {
            return null;
        }
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            if (jsonObject == null || jsonObject.isEmpty()) {
                break;
            }
            Iterator iterator = jsonObject.keys();
            Map<String, String> map = new HashMap<>();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    JSONObject ja = (JSONObject)value;
                    Object time = ja.get("time");
                    if (time != null) {
                        String format = DateUtil.format(DateUtil.DATE_FORMAT_YMDHMS, new Date(Long.parseLong(time.toString())));
                        map.put(key, format);
                    }
                } else if (value instanceof JSONNull && !key.contains("Time")) {
                    map.put(key, null);
                } else if(key.contains("Time") ){
                    if((value+"").indexOf("-")==-1) {
                        if (StringUtils.isNotBlank(value) && (!value.equals("") && !value.equals("null"))) {
                            String format = DateUtil.format(DateUtil.DATE_FORMAT_YMDHMS, new Date(Long.valueOf(value + "")));
                            map.put(key, format);
                        } else {
                            String format = DateUtil.format(DateUtil.DATE_FORMAT_YMDHMS, new Date());
                            map.put(key, format);
                        }
                    }else{
                        map.put(key, value.toString());
                    }
                }else {
                    map.put(key, value.toString());
                }
            }
            list.add(map);
        }
		return list;
	}

	public static  void main(String[] args){
//	    if("2018-07-31" instanceof Long){
//
//        }
        JSONArray jsonObject = new JSONArray();
        Map map = new HashMap();
        map.put("saleTime","2018-07-31 00:00:00");
        jsonObject.add(map);
        System.out.println(convertToMap(jsonObject));
//        String format = DateUtil.format(DateUtil.DATE_FORMAT_YMDHMS, new Date(Long.valueOf()));
    }

}
