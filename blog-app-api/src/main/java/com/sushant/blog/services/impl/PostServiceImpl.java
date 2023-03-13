package com.sushant.blog.services.impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sushant.blog.Exceptions.ResourceNotFoundException;
import com.sushant.blog.entitites.Category;
import com.sushant.blog.entitites.Post;
import com.sushant.blog.entitites.User;
import com.sushant.blog.payloads.PostDto;
import com.sushant.blog.payloads.PostResponse;
import com.sushant.blog.repo.CategoryRepo;
import com.sushant.blog.repo.PostRepo;
import com.sushant.blog.repo.UserRepo;
import com.sushant.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId ) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
	   System.out.println(category.toString());
	   System.out.print(user.getName());
		return this.modelMapper.map(newPost, PostDto.class);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
//		this.postRepo.deleteById(postId);
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPosts(Integer pageSize,Integer pageNo,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
//		int pageSize = 5;
//		int pageNo =1;
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort =  Sort.by(sortBy).ascending();
			
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p =PageRequest.of(pageNo, pageSize, sort);
		Page<Post> pagePost =this.postRepo.findAll(p);
		List<Post> allPost =pagePost.getContent();
		List<PostDto> postDtos = allPost.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
//		List<Post> posts = this.postRepo.findAll();
//		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//		return postDtos
		PostResponse postResponse =new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse ;
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post posts = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		
		return this.modelMapper.map(posts, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
	    List<Post> posts =	this.postRepo.findByCategory(category);
	    List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	    return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
//	  List<Post> posts =	this.postRepo.findByTitleContaining(keyword); -->works normally ,In below lines we are trying using custom query
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		
		
	  List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
