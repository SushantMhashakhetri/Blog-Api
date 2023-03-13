package com.sushant.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushant.blog.Exceptions.ResourceNotFoundException;
import com.sushant.blog.entitites.Comment;
import com.sushant.blog.entitites.Post;
import com.sushant.blog.payloads.CommentDto;
import com.sushant.blog.repo.CommentRepo;
import com.sushant.blog.repo.PostRepo;
import com.sushant.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		Comment comment= this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
//		this.commentRepo.deleteById(commentId);
		this.commentRepo.delete(comment);
		
		
	}

}
