package com.wechat.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wechat.common.utils.HttpsGetUtil;

public class CommonAPITest {

	public static void main(String[] args) {
		String url = "http://route.showapi.com/582-2";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("typeId", "");
		paramsMap.put("key", "");
		paramsMap.put("page", "");
		paramsMap.put("needContent", "");
		String result = null;
		try {
			result = HttpsGetUtil.getHttpsReplayString(url, paramsMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(result);
	}
	
}
