package com.wechat.music;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wechat.common.model.ShowApiBean;
import com.wechat.music.model.PageBean;
import com.wechat.music.model.Song;


public class YiTingMusicService {
	public static void main(String path[]) throws Exception {
		String queryWord = "kiss";
		String appid = "28768";
		String secret = "ec7500c8fe814117b9c795c6e03b5bb1";
		String url = "http://route.showapi.com/928-3?showapi_appid={appid}&showapi_sign={secret}&q=";
		url = url.replace("{appid}", appid);
		url = url.replace("{secret}", secret);
		url = url + queryWord;
		//System.out.println(url);
		URL u = new URL(url);
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
		//System.out.println();
		String result = new String(b, "utf-8");
		//System.out.println(result);
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();//创建JSON解析器
		JsonObject json = (JsonObject) parser.parse(new String(b, "utf-8"));
		JsonObject showapi_res_code = json.get("showapi_res_body").getAsJsonObject();
		JsonObject pagebean = showapi_res_code.get("pagebean").getAsJsonObject();
		
		ShowApiBean showApiBean = gson.fromJson(result, ShowApiBean.class);
		System.out.println(showApiBean.getShowapi_res_body().getClass());
		
		
		
		PageBean pageBean = gson.fromJson(pagebean, PageBean.class);
		//System.out.println(pageBean.getAllNum());
		//System.out.println(pageBean.getAllPages());
		List<Song> songs = pageBean.getContentlist();
		if (songs != null) {
			for (Iterator<Song> iterator = songs.iterator(); iterator.hasNext();) {
				//Song song = (Song) iterator.next();
				//System.out.println(song.getTitle() + " : " + song.getLink());
			}
		}
		//System.out.println(pageBean.getAllNum());
	}

}
