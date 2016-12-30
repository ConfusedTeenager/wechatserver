package com.wechat.wechatarticle.model;

import java.util.Date;

public class ArticleItem {
	// 条目id
	private String id;
	// 条目标题
	private String title;
	// 类型id
	private String typeId;
	// 类型名称
	private String typeName;
	// 微信文章链接
	private String url;
	// 正文图片示例
	private String contentImg;
	// 微信作者头像
	private String userLogo;
	// 微信作者名称
	private String userName;
	// 微信作者二维码
	private String userLogo_code;
	// 阅读人数
	private int read_num;
	// 点赞人数
	private int like_num;
	// 此文章正文。只有传入needContent=1时才会返回此字段。
	private String content;
	// 作者微信号
	private String weixinNum;
	// 时间
	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentImg() {
		return contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogo_code() {
		return userLogo_code;
	}

	public void setUserLogo_code(String userLogo_code) {
		this.userLogo_code = userLogo_code;
	}

	public int getRead_num() {
		return read_num;
	}

	public void setRead_num(int read_num) {
		this.read_num = read_num;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWeixinNum() {
		return weixinNum;
	}

	public void setWeixinNum(String weixinNum) {
		this.weixinNum = weixinNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
