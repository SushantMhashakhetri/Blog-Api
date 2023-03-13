package com.sushant.blog.payloads;

import com.sushant.blog.entitites.Post;

public class CommentDto {

	private int id;
	
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;
	



}
