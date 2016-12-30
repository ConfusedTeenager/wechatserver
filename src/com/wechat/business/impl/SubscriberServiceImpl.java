package com.wechat.business.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.business.SubscriberService;
import com.wechat.common.mapper.SubscriberMapper;
import com.wechat.common.model.Subscriber;

@Service
public class SubscriberServiceImpl implements SubscriberService{
	
	@Autowired
	private SubscriberMapper subscriberMapper;
	
	public String subscribe(Map<String, String> paramMap) {
		// 发送方帐号（open_id）
		String fromUserName = paramMap.get("FromUserName");
		Subscriber subscriber = new Subscriber();
		subscriber.setUser_id(fromUserName);
		subscriber.setUser_name(fromUserName);
		subscriber.setFlag(1);
		subscriber.setOperating_time(new Date());
		int count = subscriberMapper.getSubscribersCount(1);
		count = count + 1;
		if (subscriberMapper.selectByUserId(fromUserName) != null) {
			subscriberMapper.updateByUserIdSelective(subscriber);
		} else {
			subscriberMapper.insertSelective(subscriber);
		}
		// String content = "尊敬的" + fromUserName + ",您是第" + count +
		// "位订阅者。谢谢您的关注！";
		StringBuffer contentBuffer = new StringBuffer();
		contentBuffer.append("尊敬的用户，您好！您是第").append(count)
				.append("位订阅者。谢谢您的关注！").append("\n\n")
				.append("我可以为您提供翻译服务，发送“翻译”即可获取使用说明。我还可以随机推荐一首音乐，发送“music”或者“音乐”即可。除此之外还可以进行简单的对话哦（目前仅限一问一答）快来试试吧")
				.append("\ue11b\ue11b\ue11b");
		return contentBuffer.toString();
	}

	public void unsubscribe(Map<String, String> paramMap) {
		// 发送方帐号（open_id）
		String fromUserName = paramMap.get("FromUserName");
		Subscriber subscriber = new Subscriber();
		subscriber.setUser_id(fromUserName);
		subscriber.setUser_name(fromUserName);
		subscriber.setFlag(0);
		subscriber.setOperating_time(new Date());
		subscriberMapper.updateByUserIdSelective(subscriber);
	}

}
