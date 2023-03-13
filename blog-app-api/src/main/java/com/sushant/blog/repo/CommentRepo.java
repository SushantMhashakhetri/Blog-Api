package com.sushant.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushant.blog.entitites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
	
}
