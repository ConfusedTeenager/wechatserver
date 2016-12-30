package com.wechat.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GsonUtil {

	public static <T> T jsonToObject(String json, String nodeName, Class<T> classOfT) {
		Gson gson = new Gson();
		if (nodeName != null && !nodeName.isEmpty()) {
			JsonParser parser = new JsonParser();//创建JSON解析器
			JsonObject jsonObject = (JsonObject) parser.parse(json);
			JsonObject nodeObject = jsonObject.get(nodeName).getAsJsonObject();
			return gson.fromJson(nodeObject, classOfT);
		} else {
			return gson.fromJson(json, classOfT);
		}
	}
	
	public static <T> T jsonToObject(String json, Class<T> classOfT) {
		return jsonToObject(json, null, classOfT);
	}
	
}
