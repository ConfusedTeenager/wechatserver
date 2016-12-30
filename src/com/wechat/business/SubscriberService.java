package com.wechat.business;

import java.util.Map;

public interface SubscriberService {
	
	public String subscribe(Map<String, String> paramMap);
	
	public void unsubscribe(Map<String, String> paramMap);

}
