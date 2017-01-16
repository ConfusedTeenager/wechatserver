package com.wechat.wechatarticle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wechat.common.model.ShowApiBean;
import com.wechat.common.utils.ConfigUtil;
import com.wechat.common.utils.GsonUtil;
import com.wechat.common.utils.HttpsGetUtil;
import com.wechat.common.utils.MessageUtil;
import com.wechat.message.response.Article;
import com.wechat.message.response.NewsMessage;
import com.wechat.wechatarticle.model.ArticleItem;
import com.wechat.wechatarticle.model.PageBean;

/**
 * 微信精选文章服务类
 * @author yinsaiyu
 *
 */
@Service("wechatArticleService")
public class WechatArticleService {

	public NewsMessage getMessage() {

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("typeId", "");
		paramsMap.put("key", "");
		paramsMap.put("page", "");
		paramsMap.put("needContent", "");
		String result = null;
		try {
			result = HttpsGetUtil.getHttpsReplayString(ConfigUtil.WECHAT_ARTICLE_URL, paramsMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowApiBean showApiBean = GsonUtil.jsonToObject(result, ShowApiBean.class);
		PageBean pageBean = GsonUtil.jsonToObject(showApiBean.getShowapi_res_body().toString(), "pagebean",PageBean.class);
		List<ArticleItem> articleItems = pageBean.getContentlist();
		int count = articleItems.size() > 5 ? 5 : articleItems.size();
		List<Article> articles = new ArrayList<Article>();
		for (int i = 0; i < count; i++) {
			ArticleItem articleItem = articleItems.get(i);
			Article article = new Article();
			article.setTitle(articleItem.getTitle());
			article.setUrl(articleItem.getUrl());
			article.setPicUrl(articleItem.getContentImg()== null ? "" : articleItem.getContentImg());
			article.setDescription(articleItem.getContent() == null ? "" : articleItem.getContent());
			articles.add(article);
		}
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setArticleCount(articles.size());
		newsMessage.setArticles(articles);
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		return newsMessage;
	}

}
