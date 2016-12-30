package com.wechat.robot.model;

import com.google.gson.JsonArray;

public class SmalliRobotReply {
	/*
	 * 类型为“int”回复类型： 回复类型列表 0 无回复 1 标准回复 2 列表 3 其它 4 聊天(通用聊天库) 5 敏感词 6 重复
	 * 7对话预处理的直接回复 8 聊天(定制聊天库) 9 过期 10 拼写错误 11 建议问 >100 指令
	 */
	private Integer type;

	// 回复内容 例如：“很高兴为您服务！”
	private String content;

	// 相关问题 例如：[“开通彩铃”,”取消彩铃”]
	private JsonArray relatedQuestions;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public JsonArray getRelatedQuestions() {
		return relatedQuestions;
	}
	

	public void setRelatedQuestions(JsonArray relatedQuestions) {
		this.relatedQuestions = relatedQuestions;
	}

}
