package com.maxcar.service;
import java.util.List;

import com.maxcar.core.utils.Result;

import net.sf.json.JSONObject;

public interface DaSouCheService {
	Result getAllService(int type, JSONObject params, List<String> listKey);
}
