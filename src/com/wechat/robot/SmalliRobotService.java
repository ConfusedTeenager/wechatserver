package com.wechat.robot;

import java.util.HashMap;
import java.util.Map;

import com.wechat.common.utils.ConfigUtil;
import com.wechat.common.utils.GsonUtil;
import com.wechat.common.utils.HttpsGetUtil;
import com.wechat.common.utils.MessageUtil;
import com.wechat.message.response.TextMessage;
import com.wechat.robot.model.SmalliRobotReply;

/**
 * 小i机器人服务类
 * @author yinsaiyu
 *
 */
public class SmalliRobotService {

	/**
	 * 调用小i机器人的API并将返回的结果组装成一个TextMessage
	 * @param question 要询问小i机器人的问题
	 * @param userId  用户id
	 * @return
	 * @throws Exception
	 */
	public TextMessage robotReply(String question, String userId) throws Exception {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userid", userId);
		paramsMap.put("question", question);
		paramsMap.put("platform", "");
		paramsMap.put("location", "");
		paramsMap.put("brand", "");
		String result = HttpsGetUtil.getHttpsReplayString(ConfigUtil.SMALLIROBOT_API_URL, paramsMap);
		SmalliRobotReply smalliRobotReply = GsonUtil.jsonToObject(result, "showapi_res_body", SmalliRobotReply.class);

		TextMessage textMessage = new TextMessage();
		textMessage.setContent(smalliRobotReply.getContent().replaceAll("\r", "").replaceAll("\n", ""));
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}
}
