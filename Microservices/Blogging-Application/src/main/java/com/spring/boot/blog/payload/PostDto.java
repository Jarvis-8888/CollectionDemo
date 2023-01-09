package com.spring.boot.blog.payload;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	
	private int postId;

	@NotEmpty
	@Size(min = 5,max = 20,message = "Title should be in range of 5 to 20 !!!")
	private String title;

	@NotEmpty
	@Size(max = 10,message = "Content should be greater than 10 !!!")
	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;

	private Set<CommentDto> comments=new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	
	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
