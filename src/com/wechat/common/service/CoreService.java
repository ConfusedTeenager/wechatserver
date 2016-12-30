package com.wechat.common.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wechat.business.SubscriberService;
import com.wechat.common.service.impl.ResponseMessageServiceImpl;
import com.wechat.common.utils.EventUtil;
import com.wechat.common.utils.MessageUtil;
import com.wechat.message.response.TextMessage;

@Component
public class CoreService {
	
	@Autowired
	private SubscriberService subscriberService;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				ResponseMessageService messageService = new ResponseMessageServiceImpl();
				respMessage = messageService.getResponseMessage(requestMap);

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {

			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {

			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {

			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {

			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {

			}
			// 小视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_SHORT_VIDEO)) {

			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(EventUtil.EVENT_TYPE_SUBSCRIBE)) {
					// respContent = "谢谢您的关注！";
					
					// 回复文本消息
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(requestMap.get("FromUserName"));
					textMessage.setFromUserName(requestMap.get("ToUserName"));
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					String respContent = subscriberService.subscribe(requestMap);
					textMessage.setContent(respContent);
					return MessageUtil.textMessageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(EventUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					 subscriberService.unsubscribe(requestMap);
				}
				// 自定义菜单点击事件
				else if (eventType.equals(EventUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {

					} else if (eventKey.equals("12")) {

					} else if (eventKey.equals("13")) {

					} else if (eventKey.equals("14")) {

					} else if (eventKey.equals("21")) {

					} else if (eventKey.equals("22")) {

					} else if (eventKey.equals("23")) {

					} else if (eventKey.equals("24")) {

					} else if (eventKey.equals("25")) {

					} else if (eventKey.equals("31")) {

					} else if (eventKey.equals("32")) {

					} else if (eventKey.equals("33")) {

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
