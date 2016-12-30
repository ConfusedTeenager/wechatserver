package com.wechat.event;

public class MenuEvent extends BaseEvent {
	// 1、事件KEY值，与自定义菜单接口中KEY值对应; 2、事件KEY值，设置的跳转URL
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
