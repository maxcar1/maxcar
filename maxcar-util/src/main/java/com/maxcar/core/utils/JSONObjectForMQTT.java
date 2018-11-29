package com.maxcar.core.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONObjectForMQTT {
	
	/**
	 * MQTT数据发送json体
	 */
	public static JSONObject getJsonObject(){
		JSONObject obj = new JSONObject();
		JSONObject head = new JSONObject();
		JSONObject body = new JSONObject();
		JSONObject err = new JSONObject();
		JSONArray arr = new JSONArray();
		//head数据组装
		head.put("topic", "");
		head.put("publishTime", "");
		head.put("type", "");
		head.put("status", "");
		err.put("code", "");
		err.put("message", "");
		arr.add(err);
		head.put("errors", arr);
		obj.put("head", head);
		obj.put("body", body);
		return obj;
	}

}
