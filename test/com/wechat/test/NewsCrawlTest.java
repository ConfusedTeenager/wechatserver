package com.wechat.test;

import java.util.HashMap;
import java.util.Map;

import com.wechat.common.utils.ConfigUtil;
import com.wechat.common.utils.HttpGet;

public class NewsCrawlTest {
	public static void main(String path[]) throws Exception {
		
		String appId = ConfigUtil.SHOWAPI_APPID;
		String secret = ConfigUtil.SHOWAPI_SECRET;
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("showapi_appid", appId);
		paramsMap.put("showapi_sign", secret);
		
		paramsMap.put("url", "http://news.163.com/16/1226/10/C972OCPO00018AOP.html");
		
		
		String text = HttpGet.get("http://route.showapi.com/883-1", paramsMap);
		System.out.println(text);
		
	}
}
