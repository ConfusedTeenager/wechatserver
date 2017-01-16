package com.wechat.test;

import com.wechat.business.SubscriberService;
import com.wechat.common.utils.SpringContextUtil;

public class SpringTest {
	public static void main(String[] args) {
		SubscriberService subscriberService = SpringContextUtil.getBean(SubscriberService.class);
		System.err.println(subscriberService);
	}

}
