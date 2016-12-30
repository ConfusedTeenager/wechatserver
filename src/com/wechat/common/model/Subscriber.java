package com.wechat.common.model;

import java.util.Date;

/**
 * 订阅用户
 * @author yinsaiyu
 *
 */
public class Subscriber {
	// 主键id
	private Integer id;
	// 微信用户id
	private String user_id;
	// 微信用户名称，同id
	private String user_name;
	// 是否关注标志。1表示以关注；0表示取消关注
	private Integer flag;
	// 操作时间
	private Date operating_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id == null ? null : user_id.trim();
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name == null ? null : user_name.trim();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getOperating_time() {
		return operating_time;
	}

	public void setOperating_time(Date operating_time) {
		this.operating_time = operating_time;
	}
}