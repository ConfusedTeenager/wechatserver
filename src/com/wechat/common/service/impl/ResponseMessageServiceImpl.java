package com.wechat.common.service.impl;

import java.util.Date;
import java.util.Map;

import com.wechat.common.service.ResponseMessageService;
import com.wechat.common.utils.HelpUtil;
import com.wechat.common.utils.MessageUtil;
import com.wechat.common.utils.SpringContextUtil;
import com.wechat.message.response.ResponseBaseMessage;
import com.wechat.message.response.TextMessage;
import com.wechat.music.QQMusicService;
import com.wechat.robot.SmalliRobotService;
import com.wechat.translate.BaiduTranslateService;
import com.wechat.wechatarticle.WechatArticleService;
/**
 * 处理返回给微信的消息
 * @author yinsaiyu
 *
 */
public class ResponseMessageServiceImpl implements ResponseMessageService {

	@Override
	public String getResponseMessage(Map<String, String> requestMap) {
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		ResponseBaseMessage responseBaseMessage = null;

		// 文本消息内容
		String content = requestMap.get("Content").trim().toLowerCase();

		if (content.startsWith("翻译") || content.startsWith("fy")) {
			String keyWord = null;
			if (content.startsWith("翻译")) {
				keyWord = content.replaceAll("^翻译", "").trim();
			} else if (content.startsWith("fy")) {
				keyWord = content.replaceAll("^fy", "").trim();
			}

			if ("".equals(keyWord)) {
				responseBaseMessage = HelpUtil.getTranslateUsageMessage();
			} else {
				BaiduTranslateService baiduTranslateService = SpringContextUtil.getBean(BaiduTranslateService.class);
				responseBaseMessage = baiduTranslateService.getTranslateMessage(keyWord);
			}
		} else if (content.equals("music") || content.equals("乐")
				|| content.equals("音乐") || content.equalsIgnoreCase("yinyue")) {
			try {
				responseBaseMessage = QQMusicService.randomPlayMusic(fromUserName, toUserName, content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if ("微信精选文章".equals(content)) {
			WechatArticleService wechatArticleService = SpringContextUtil.getBean(WechatArticleService.class);
			responseBaseMessage = wechatArticleService.getMessage();
		} else {
			// 回复文本消息
			/*TextMessage textMessage = new TextMessage();
			textMessage.setContent(content);
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);*/
			
			try {
				SmalliRobotService smalliRobotService = SpringContextUtil.getBean(SmalliRobotService.class);
				responseBaseMessage = smalliRobotService.robotReply(content, fromUserName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (responseBaseMessage == null) {
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			String responseContent = "服务器出现异常，请稍后再试！";
			textMessage.setContent(responseContent);
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			responseBaseMessage = textMessage;
		}

		responseBaseMessage.setFromUserName(toUserName);
		responseBaseMessage.setToUserName(fromUserName);
		responseBaseMessage.setCreateTime(new Date().getTime());
		responseBaseMessage.setFuncFlag(0);
		
		System.out.println(MessageUtil.responseMessageToXml(responseBaseMessage));

		return MessageUtil.responseMessageToXml(responseBaseMessage);
	}

}
