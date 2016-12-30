package com.wechat.message.response;

/**
 * 回复语音消息
 * @author yinsaiyu
 *
 */
public class VoiceMessage extends ResponseBaseMessage{
	//通过素材管理中的接口上传多媒体文件，得到的id
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
