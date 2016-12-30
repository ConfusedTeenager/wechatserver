package com.wechat.music.model;

public class Song {
	// 歌曲专辑页面地址
	private String album_link;
	// 专辑名称
	private String album_name;
	// 歌曲页面地址
	private String link;
	// 歌手页面地址
	private String singer_link;
	// 歌手名称
	private String singer_name;
	// 歌曲标题
	private String title;
	// 歌词地址
	private String word_url;

	public String getAlbum_link() {
		return album_link;
	}

	public void setAlbum_link(String album_link) {
		this.album_link = album_link;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSinger_link() {
		return singer_link;
	}

	public void setSinger_link(String singer_link) {
		this.singer_link = singer_link;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWord_url() {
		return word_url;
	}

	public void setWord_url(String word_url) {
		this.word_url = word_url;
	}
}
