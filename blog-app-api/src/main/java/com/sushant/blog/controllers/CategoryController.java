package com.sushant.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sushant.blog.payloads.ApiResponse;
import com.sushant.blog.payloads.CategoryDto;
import com.sushant.blog.services.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	//create
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto){
	 CategoryDto categoryDto=	this.categoryServiceImpl.createCategory(catDto);
	 return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto,@PathVariable Integer catId){
	 CategoryDto categoryDto=	this.categoryServiceImpl.updateCategory(catDto,catId);
	 return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
	this.categoryServiceImpl.deleteCategory(catId);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
	CategoryDto categoryDto=this.categoryServiceImpl.getCategoryById(catId);
	 return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
	List<CategoryDto> categoryDto=this.categoryServiceImpl.getAllCategory();
	 return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);
	}

}
