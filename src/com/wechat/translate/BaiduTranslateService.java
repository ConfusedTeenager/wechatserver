package com.wechat.translate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.wechat.common.utils.ConfigUtil;
import com.wechat.common.utils.MessageUtil;
import com.wechat.message.response.TextMessage;

public class BaiduTranslateService {

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = ConfigUtil.BAIDU_TRANSLATE_APP_ID;
	private static final String SECURITY_KEY = ConfigUtil.BAIDU_TRANSLATE_SECURITY_KEY;

	/**
	 * 发起http请求获取返回结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @return
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();

			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
		}
		return buffer.toString();
	}

	/**
	 * utf编码
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String translate(String source) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String dst = null;
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			// System.out.println(api.getTransResult(source, "auto", "en"));
			String json = api.getTransResult(source, "auto", "auto");
			// 通过Gson工具将json转换成TranslateResult对象
			TranslateResult translateResult = new Gson().fromJson(json,
					TranslateResult.class);
			// 取出translateResult中的译文
			dst = translateResult.getTrans_result().get(0).getDst();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == dst) {
			dst = "翻译系统异常，请稍候尝试！";
		}
		return dst;
	}

	public static void main(String[] args) {
		// 翻译结果：The network really powerful
		System.out.println(translate("The network really powerful网络真强大"));
	}
	
	
	/**
	 * 把翻译之后的内容组装成一个文本消息并返回
	 * @param source  需要翻译的内容
	 * @return
	 */
	public TextMessage getTranslateMessage(String source) {
		TextMessage textMessage = new TextMessage();
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String dst = null;
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			// System.out.println(api.getTransResult(source, "auto", "en"));
			String json = api.getTransResult(source, "auto", "auto");
			// 通过Gson工具将json转换成TranslateResult对象
			TranslateResult translateResult = new Gson().fromJson(json,
					TranslateResult.class);
			// 取出translateResult中的译文
			dst = translateResult.getTrans_result().get(0).getDst();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == dst) {
			dst = "翻译系统异常，请稍候尝试！";
		}
		textMessage.setContent(dst);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}
}