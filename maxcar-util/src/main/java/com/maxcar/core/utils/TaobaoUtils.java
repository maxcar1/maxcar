package com.maxcar.core.utils;

import java.io.File;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.PictureUploadRequest;
import com.taobao.api.response.PictureUploadResponse;

import net.sf.json.JSONObject;

public class TaobaoUtils {
	private static String APP_KEY = "24812065";
	private static String SECRET = "7cf5fb32a4e8a3ffc3cd339d9baedd4a";
	private static String API_URL = "http://gw.api.taobao.com/router/rest";
	
	public static JSONObject updateImp(String path,String fileName) throws ApiException  {
		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
		PictureUploadRequest req = new PictureUploadRequest();
		req.setPictureCategoryId(0L);
		File f = new File(path);
		System.out.println(new FileItem(f).getFileName());
		req.setImg(new FileItem(f));
		req.setImageInputTitle(fileName);
		req.setTitle(fileName);
		req.setClientType("client:computer");
		req.setIsHttps(true);
		PictureUploadResponse rsp = client.execute(req, "620120152ZZee33dbfde6e3f7ea8a9664aef035dc921a113817124563");
		System.out.println(rsp.getBody());
		JSONObject json =JSONObject.fromObject(rsp.getBody());
		
		return json;
		
//		TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
//		PaimaiTbcarUploadPicRequest req = new PaimaiTbcarUploadPicRequest();
//		File f = new File(path);
//		req.setData(new FileItem(f));
//		req.setFileName(fileName);
//		PaimaiTbcarUploadPicResponse rsp = client.execute(req);
//		JSONObject json =JSONObject.fromObject(rsp.getBody());
//		return json;
	}
}
