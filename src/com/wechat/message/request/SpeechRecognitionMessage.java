package com.wechat.message.request;

/**
 * 语音识别消息
 * @author yinsaiyu
 *
 */
public class SpeechRecognitionMessage extends VoiceMessage{
	//语音识别结果，UTF8编码
	private String Recognition;

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	
}
