package com.sushant.blog.services;

import java.util.List;

import com.sushant.blog.entitites.Post;
import com.sushant.blog.payloads.PostDto;
import com.sushant.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageSize,Integer pageNo,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
	
	

}
