package com.spring.boot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.exception.ResourceNotFoundException;
import com.spring.boot.blog.payload.CategoryDto;
import com.spring.boot.blog.repository.CategoryRepositoy;
import com.spring.boot.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepositoy categoryRepositoy;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepositoy.save(category);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> list = this.categoryRepositoy.findAll();
		return list.stream().map(category -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategory(int id) {
		Category category = this.categoryRepositoy.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));
		return modelMapper.map(category, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		Category category = this.categoryRepositoy.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category save = this.categoryRepositoy.save(category);

		return modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		Category category = this.categoryRepositoy.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));
		this.categoryRepositoy.delete(category);

	}

}
