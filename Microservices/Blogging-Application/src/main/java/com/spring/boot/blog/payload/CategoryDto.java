package com.spring.boot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	
	private int id;

	@NotEmpty
	@Size(min = 4,message = "Minimum size is 4")
	private String categoryTitle;

	@NotEmpty
	@Size(min = 10,message = "Minimum size is 10")
	private String categoryDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto(int id, String categoryTitle, String categoryDescription) {
		super();
		this.id = id;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
