package com.rohanapps.newsapp;

public class Post {
	public Post() {
		super();
	}
	
	public String title, content, timestamp = "";
	
	public Post(String content, String timestamp, String title) {
		super();
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
	}
}
