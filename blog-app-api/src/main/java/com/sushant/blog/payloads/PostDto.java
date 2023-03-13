package com.sushant.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.sushant.blog.entitites.Category;
import com.sushant.blog.entitites.Comment;
import com.sushant.blog.entitites.User;

public class PostDto {

	
	public String getTitle() {
		return title;
	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	private Integer categoryId;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	private Integer postId;
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	private String title;
	private String content;
	private String imageName;
	private Date date;
	
	private User user;
	
	private Category category;

//	private List<Comment> comments=new ArrayList()<>();
	private Set<CommentDto> comments =new HashSet<>();
}
