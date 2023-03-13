package com.sushant.blog.services;

import java.util.List;

import com.sushant.blog.payloads.CategoryDto;


public interface CategoryService {

	   
	   
	   CategoryDto createCategory(CategoryDto category);
	   CategoryDto updateCategory(CategoryDto category,Integer catId);
	   CategoryDto getCategoryById(Integer catId);
	   List<CategoryDto> getAllCategory();
	   void deleteCategory(Integer catId);
	   
}
