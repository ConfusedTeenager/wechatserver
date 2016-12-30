package com.wechat.wechatarticle.model;

import java.util.List;

public class PageBean {
	// 每页最大数量
	private String maxResult;
	// 所有数量
	private String allNum;
	// 所有页
	private String allPages;
	// 当前页
	private String currentPage;
	// 微信精选条目
	private List<ArticleItem> contentlist;

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getAllNum() {
		return allNum;
	}

	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}

	public String getAllPages() {
		return allPages;
	}

	public void setAllPages(String allPages) {
		this.allPages = allPages;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public List<ArticleItem> getContentlist() {
		return contentlist;
	}

	public void setContentlist(List<ArticleItem> contentlist) {
		this.contentlist = contentlist;
	}

}
