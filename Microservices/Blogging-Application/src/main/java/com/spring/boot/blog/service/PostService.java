package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.blog.payload.PostDto;
import com.spring.boot.blog.payload.PostResponse;

public interface PostService {
	
	List<PostDto> getPostByGroupOfUser();

	// GET IMAGE AS WELL AS DATA AND STORED IN DB
	PostDto createPostWithImage(MultipartFile image, String data, int postId, int categoryId, int userId);

	// CREATE
	PostDto createPost(PostDto postDto, int userId, int categoryId);

	// UPDATE
	PostDto updatePost(PostDto postDto, int id);

	// GET ALL
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

	// GET BY ID
	PostDto getById(int id);

	// DELETE
	void deleteById(int id);

	// GET ALL POST BY USER
	List<PostDto> getAllPostByUser(int userId);

	// GET ALL POST BY CATEGORY
	List<PostDto> getAllPostByCategory(int categoryId);

	// Search post using keyword
	List<PostDto> searchPost(String keyword);

}
