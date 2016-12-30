package com.wechat.message.response;

/**
 * 视频消息
 * 
 * @author yinsaiyu
 * 
 */
public class VideoMessage extends ResponseBaseMessage {
	// 视频
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
