package com.wechat.music;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.wechat.common.utils.ConfigUtil;
import com.wechat.common.utils.HttpGet;
import com.wechat.common.utils.MessageUtil;
import com.wechat.message.response.Music;
import com.wechat.message.response.MusicMessage;

/**
 * 
 * QQ音乐搜索API操作类
 * 
 * @date 2016-12-12 16:41:24
 * @author yaoy
 * 
 */
public class QQMusicService {
	/**
	 * 随机播放一首华语内地排行榜的歌曲 topid 榜行榜id 3=欧美 5=内地 6=港台 16=韩国 17=日本 18=民谣 19=摇滚 23=销量
	 * 26=热歌
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static MusicMessage randomPlayMusic(String toUserName,
			String fromUserName, String content) throws Exception {
		String appId = ConfigUtil.SHOWAPI_APPID;
		String secret = ConfigUtil.SHOWAPI_SECRET;
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("showapi_appid", appId);
		paramsMap.put("showapi_sign", secret);
		paramsMap.put("topid", "5");
		String url = HttpGet.getUrlWithQueryString(ConfigUtil.QQMUSIC_API_URL, paramsMap);
		URL u = new URL(url);// topid  "http://route.showapi.com/213-4?showapi_appid=28764&topid=5&showapi_sign=b796f101c0df4a69a534179dfbb19f92" 原来的URL
		InputStream in = u.openStream();
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
		
		System.out.println(new String(b, "utf-8"));

		MusicMessage musicMessage = parserQQMusic(toUserName, fromUserName, b);
		return musicMessage;

	}

	public static MusicMessage parserQQMusic(String toUserName,
			String fromUserName, byte[] b) {
		JsonParser parser = new JsonParser();// 创建JSON解析器
		String title = null;
		String description = null;
		String musicUrl = null;
		try {
			JsonObject json = (JsonObject) parser.parse(new String(b, "utf-8"));

			JsonObject showapi_res_code = json.get("showapi_res_body")
					.getAsJsonObject();
			JsonObject pagebean = showapi_res_code.get("pagebean")
					.getAsJsonObject();
			JsonArray songlist = pagebean.get("songlist").getAsJsonArray();
			int i = (int) (Math.random() * songlist.size());// 取随机值0-99
			JsonObject subObject = songlist.get(i).getAsJsonObject();
			title = subObject.get("songname").getAsString();
			description = subObject.get("singername").getAsString();
			musicUrl = subObject.get("url").getAsString();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		MusicMessage musicMessage = new MusicMessage();

		musicMessage.setToUserName(toUserName);
		musicMessage.setFromUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);

		Music music = new Music();
		music.setTitle(title);
		music.setDescription(description);
		music.setMusicUrl(musicUrl);
		music.setHQMusicUrl(musicUrl);

		musicMessage.setMusic(music);

		return musicMessage;

	}
}
