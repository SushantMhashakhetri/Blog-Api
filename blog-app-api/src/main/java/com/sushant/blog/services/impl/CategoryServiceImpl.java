package com.sushant.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushant.blog.Exceptions.ResourceNotFoundException;
import com.sushant.blog.entitites.Category;
import com.sushant.blog.entitites.User;
import com.sushant.blog.payloads.CategoryDto;
import com.sushant.blog.payloads.UserDto;
import com.sushant.blog.repo.CategoryRepo;
import com.sushant.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto CategoryDto) {
		// TODO Auto-generated method stub
		Category category=this.dtoToCategory(CategoryDto);
		 Category savedCategory=this.categoryRepo.save(category);
		 return this.CategoryToDto(savedCategory);
	}





	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		// TODO Auto-generated method stub
		Category category= this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id",catId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory=this.categoryRepo.save(category);
		CategoryDto updateCategoryDto=this.CategoryToDto(updatedCategory);
		
		return updateCategoryDto;
	}


	@Override
	public CategoryDto getCategoryById(Integer catId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id",catId));
		
		return this.CategoryToDto(category);
	}


	
	
	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> category=this.categoryRepo.findAll();
		List<CategoryDto>  categoryDto=category.stream().map(category2->this.CategoryToDto(category2)).collect(Collectors.toList());
		return categoryDto;
	}


	@Override
	public void deleteCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Id",catId));
		this.categoryRepo.delete(category);
		
	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {


		
		Category category =this.modelMapper.map(categoryDto, Category.class);
		return category;
		
	}
	
	private CategoryDto CategoryToDto(Category category) {
		
//	
		return this.modelMapper.map(category, CategoryDto.class);

}
}
