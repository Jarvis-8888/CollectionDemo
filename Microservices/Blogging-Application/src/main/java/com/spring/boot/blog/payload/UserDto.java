package com.spring.boot.blog.payload;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 3, message = "Name should be atleast 4 charaters !!!")
	private String name;

	@Email(message = "Email address should not valid !!!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 15, message = "Password should be in range of 3 to 15 characters !!!")
	private String password;

	@NotEmpty
	private String about;

	
	/*
	 * private Set<CommentDto> comments = new HashSet<>();
	 * 
	 * public Set<CommentDto> getComments() { return comments; }
	 * 
	 * public void setComments(Set<CommentDto> comments) { this.comments = comments;
	 * }
	 */
	 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
