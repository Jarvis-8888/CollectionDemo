package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.boot.blog.payload.CategoryDto;

@Service
public interface CategoryService {
	
	// CREATE
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// GET ALL CATEGORY
	List<CategoryDto> getAllCategory();
	
	// GET SINGLE CATEGORY
	CategoryDto getCategory(int id);
	
	// UPDATE CATEGORY
	CategoryDto updateCategory(CategoryDto categoryDto,int id);
	
	//DELETE CATEGORY
	void deleteCategory(int id);

}
