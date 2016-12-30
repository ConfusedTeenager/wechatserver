package com.wechat.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpsGetUtil {

	/**
	 * 通过HTTP发送get请求并获取返回的结果
	 * 
	 * @param host
	 *            请求的URL地址
	 * @param paramsMap
	 *            URL后面附带的参数
	 * @return
	 * @throws IOException
	 */
	public static String getHttpReplayString(String host, Map<String, String> paramsMap) throws IOException {
		if (paramsMap == null) {
			paramsMap = new HashMap<String, String>();
		}
		String appId = ConfigUtil.SHOWAPI_APPID; // showapi的appid
		String secret = ConfigUtil.SHOWAPI_SECRET; // showapi的SECRET，和上面的appid作为调用showapi的凭证
		paramsMap.put("showapi_appid", appId);
		paramsMap.put("showapi_sign", secret);
		String urlString = HttpGet.getUrlWithQueryString(host, paramsMap);
		URL url = new URL(urlString);
		InputStream in = url.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		String result = new String(b, "UTF-8");
		return result;
	}
	
	/**
	 * 通过HTTPS发送get请求并获取返回的结果
	 * 
	 * @param host
	 *            请求的URL地址
	 * @param paramsMap
	 *            URL后面附带的参数
	 * @return
	 * @throws IOException
	 */
	public static String getHttpsReplayString(String host, Map<String, String> paramsMap) throws IOException {
		if (paramsMap == null) {
			paramsMap = new HashMap<String, String>();
		}
		String appId = ConfigUtil.SHOWAPI_APPID; // showapi的appid
		String secret = ConfigUtil.SHOWAPI_SECRET; // showapi的SECRET，和上面的appid作为调用showapi的凭证
		paramsMap.put("showapi_appid", appId);
		paramsMap.put("showapi_sign", secret);
		String result = HttpGet.get(host, paramsMap);
		return result;
	}

}
