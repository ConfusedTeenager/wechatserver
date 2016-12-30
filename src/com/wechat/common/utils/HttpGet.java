package com.wechat.common.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpGet {
	protected static final int SOCKET_TIMEOUT = 10000; // 10S
	protected static final String GET = "GET";

	public static String get(String host, Map<String, String> params) {
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { trustManager }, null);

			String sendUrl = getUrlWithQueryString(host, params);

			// System.out.println("URL:" + sendUrl);

			URL uri = new URL(sendUrl); // 创建URL对象
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			if (conn instanceof HttpsURLConnection) {
				((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
			}

			conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
			conn.setRequestMethod(GET);
			int statusCode = conn.getResponseCode();
			if (statusCode != HttpURLConnection.HTTP_OK) {
				System.out.println("Http错误码：" + statusCode);
			}

			// 读取服务器的数据
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}

			String text = builder.toString();

			close(bufferedReader); // 关闭数据流
			close(inputStreamReader); // 关闭数据流
			close(inputStream); // 关闭数据流
			conn.disconnect(); // 断开连接

			return text;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getUrlWithQueryString(String url,
			Map<String, String> params) {
		if (params == null) {
			return url;
		}

		StringBuilder builder = new StringBuilder(url);
		if (url.contains("?")) {
			builder.append("&");
		} else {
			builder.append("?");
		}

		int i = 0;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value == null) { // 过滤空的key
				continue;
			}

			if (i != 0) {
				builder.append('&');
			}

			builder.append(key);
			builder.append('=');
			builder.append(encode(value));

			i++;
		}

		return builder.toString();
	}

	protected static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对输入的字符串进行URL编码, 即转换为%20这种形式
	 * 
	 * @param input
	 *            原文
	 * @return URL编码. 如果编码失败, 则返回原文
	 */
	public static String encode(String input) {
		if (input == null) {
			return "";
		}

		try {
			return URLEncoder.encode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return input;
	}

	private static TrustManager trustManager = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}
	};

}
