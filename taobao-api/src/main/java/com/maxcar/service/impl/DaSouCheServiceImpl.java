package com.maxcar.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.maxcar.core.exception.ResultCode;
import com.maxcar.core.utils.BaseEncodeUtil;
import com.maxcar.core.utils.HttpClientUtil;
import com.maxcar.core.utils.Result;
import com.maxcar.core.utils.SHAUtil;
import com.maxcar.core.utils.StringUtils;
import com.maxcar.service.DaSouCheService;

import net.sf.json.JSONObject;
@Service("daSouCheService")
public class DaSouCheServiceImpl implements DaSouCheService{
	private static final Logger logger = LoggerFactory.getLogger(DaSouCheServiceImpl.class);
		// 定义常量, appId、appKey、masterSecret
		@Value("#{fileProperties['appKey']}")
		private String appKey;
		@Value("#{fileProperties['appSecret']}")
		private String appSecret;
		@Value("#{fileProperties['baseurl']}")
		private String url;
		@Value("#{fileProperties['detailurl']}")
		private String url2;
		@Value("#{fileProperties['brandsurl']}")
		private String url3;
		@Value("#{fileProperties['scBrandCodeurl']}")
		private String url4;
		@Value("#{fileProperties['scSeriesCodeurl']}")
		private String url5;
		@Value("#{fileProperties['scModelCodeurl']}")
		private String url6;
		@Value("#{fileProperties['priceurl']}")
		private String url7;
		@Value("#{fileProperties['brand_dictionariesurl']}")
		private String url8;
		@Value("#{fileProperties['used_car_price']}")
		private String url9;
		@Value("#{fileProperties['areas']}")
		private String url10;
		//维珍验车
		@Value("#{fileProperties['wzurl']}")
		private String wzurl;
		@Value("#{fileProperties['wzmcname']}")
		private String wzmcname;
		@Value("#{fileProperties['wzpicurl']}")
		private String wzpicurl;
		@Value("#{fileProperties['wzappid']}")
		private String wzappid;
		@Value("#{fileProperties['wzappkey']}")
		private String wzappkey;
		/**
		 * 获取大搜车服务
		 * @param params
		 * @return
		 * @throws Exception 
		 * @throws UnsupportedEncodingException 
		 */
		public Result getAllService(int type, JSONObject params, List<String> listKey)  {
			Result result = new Result();
			result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
			String newUrl = "";
			//根据type 判断是哪个服务
			switch(type) 
			{ 
			case 1: 
				newUrl = url;
			break; 
			case 2: 
				newUrl = url2; 
			break;
			case 3: 
				newUrl = url3; 
			break;
			case 4: 
				newUrl = url4;
				newUrl = newUrl.replaceAll("xxx",params.get("scBrandCode").toString());
			break;
			case 5: 
				newUrl = url5;
				newUrl = newUrl.replaceAll("xxx",params.get("scSeriesCode").toString());
			break;
			case 6: 
				newUrl = url6;
				newUrl = newUrl.replaceAll("xxx",params.get("scBrandCode").toString());
			break;
			case 7: 
				newUrl = url7; 
			break;
			case 8: 
				newUrl = url8; 
			break;
			case 9: 
				newUrl = url9; 
			break;
			}
			if(null == newUrl || "".equals(newUrl)){
				return null;
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("appKey=");
			sb.append(appKey);
			//循环遍历塞入查询条件
			for (String string : listKey) {
				sb.append("&");
				sb.append(string);
				sb.append("=");
				sb.append(params.get(string));
			}
			String s = "";
			try {
				String signStringBase64 = BaseEncodeUtil.encodeBase64(sb.toString().getBytes("UTF-8"));
				String sign = appSecret+":"+signStringBase64;
				// sha1 算法计算出最终 sign
				sign = SHAUtil.getSha1(sign);
				s = HttpClientUtil.get(newUrl+sb, "utf-8",sign);
				logger.info("#####s"+s);//{"code":40300001,"status":403,"message":"ip permission denied","raw":""}
			}catch (Exception e) {
			}
		if (StringUtils.isNotBlank(s)) {
			
			JSONObject oo = JSONObject.fromObject(s);
			if (oo.getString("status").equals("200")) {
				result.setResultCode(0);
				if(type == 8) {
					List<Map> list = new ArrayList<Map>();
					JSONObject j = oo.getJSONObject("data");
					 Iterator keys = j.keys();  
			         while(keys.hasNext()){
			        	 Map map = new HashMap();
			        	 String key = keys.next().toString();  
			             map.put("index", key);
			             map.put("list", j.get(key));
			             list.add(map);
			         }
			         result.setList(list);
				
				}else if ( type == 4 || type == 5) {
					result.setList(oo.get("data"));
					return result;
				} else {
					result.setItem(oo.get("data"));
				}
				result.setResultCode(ResultCode.GET_SUCCESS.getCode());
				return result;
			} 
		}
			return result;
		}

}
