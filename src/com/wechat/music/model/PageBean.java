package com.wechat.music.model;

import java.util.List;

public class PageBean {
	// 所有记录数
	private String allNum;
	// 所有页数
	private String allPages;
	// 当前页
	private String currentPage;
	// 每页最大数量
	private String maxResult;
	// 内容实体列表
	private List<Song> contentlist;

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

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public List<Song> getContentlist() {
		return contentlist;
	}

	public void setContentlist(List<Song> contentlist) {
		this.contentlist = contentlist;
	}

}
