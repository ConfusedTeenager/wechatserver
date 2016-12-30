package com.wechat.common.mapper;

import com.wechat.common.model.Subscriber;

public interface SubscriberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subscriber record);

    int insertSelective(Subscriber record);

    Subscriber selectByPrimaryKey(Integer id);
    
    Subscriber selectByUserId(String userId);

    int updateByPrimaryKeySelective(Subscriber record);
    
    int updateByUserIdSelective(Subscriber record);

    int updateByPrimaryKey(Subscriber record);

	int getSubscribersCount(Integer flag);
}