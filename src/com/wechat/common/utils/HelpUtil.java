package com.wechat.common.utils;

import com.wechat.message.response.TextMessage;


public class HelpUtil {
	
	/**
	 * 获取翻译使用帮助说明
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(MessageUtil.emoji(0xe148)).append("翻译使用指南")
				.append("\n\n");
		buffer.append("提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		//buffer.append("    俄 -> 中").append("\n");
		//buffer.append("    韩 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n");
		buffer.append("    其他语言 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n");
		buffer.append("或者：").append("\n");
		buffer.append("    fy 我是中国人").append("\n");
		buffer.append("    fy dream").append("\n");
		buffer.append("    fy さようなら");
		//buffer.append("感谢百度翻译提供技术支持！").append("\n\n");
		return buffer.toString();
	}
	
	/**
	 * 获取翻译使用帮助说明
	 * @return
	 */
	public static TextMessage getTranslateUsageMessage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(MessageUtil.emoji(0xe148)).append("翻译使用指南")
				.append("\n\n");
		buffer.append("提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		//buffer.append("    俄 -> 中").append("\n");
		//buffer.append("    韩 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n");
		buffer.append("    其他语言 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n");
		buffer.append("或者：").append("\n");
		buffer.append("    fy 我是中国人").append("\n");
		buffer.append("    fy dream").append("\n");
		buffer.append("    fy さようなら");
		//buffer.append("感谢百度翻译提供技术支持！").append("\n\n");
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(buffer.toString());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}

}
