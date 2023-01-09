package com.spring.boot.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.blog.config.AppConstants;
import com.spring.boot.blog.payload.ApiResponse;
import com.spring.boot.blog.payload.PostDto;
import com.spring.boot.blog.payload.PostResponse;
import com.spring.boot.blog.service.FileService;
import com.spring.boot.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	
	

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	
	@GetMapping("/post/user")
	public ResponseEntity<Map<Object,List<PostDto>>> getPostGroupByUser()
	{
		List<PostDto> list = this.postService.getPostByGroupOfUser();
		Map<Object, List<PostDto>> collect = list.stream().collect(Collectors.groupingBy(i-> i.getTitle()));
		return new ResponseEntity<Map<Object,List<PostDto>>>(collect,HttpStatus.OK);
	}
	
	

	// GET POST BY USER

	@GetMapping("/user/{id}/post")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable int id) {
		List<PostDto> list = this.postService.getAllPostByUser(id);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	// GET POST BY CATEGORY

	@GetMapping("/category/{id}/post")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable int id) {
		List<PostDto> list = this.postService.getAllPostByCategory(id);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	// GET ALL POST
	// http://localhost:9090/api/post?pageNumber=0&pageSize=3&sortBy=Id&sortDir=dec
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// GET POST BY ID

	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
		PostDto dto = this.postService.getById(id);
		return new ResponseEntity<PostDto>(dto, HttpStatus.OK);
	}

	// UPDATE

	@PutMapping("/post/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int id) {
		PostDto updatePost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// DELETE POST BY ID

	@DeleteMapping("/post/{id}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable int id) {
		this.postService.deleteById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully !!", true), HttpStatus.OK);
	}

	// SEARCH POST TITLE BY PATTERN

	@GetMapping("/post/search/{pattern}")
	public ResponseEntity<List<PostDto>> searchTitleByPattern(@PathVariable String pattern) {
		List<PostDto> postDtos = this.postService.searchPost(pattern);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// CREATE

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// UPLOAD POST IMAGE

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable int postId) {
		PostDto postDto = this.postService.getById(postId);
		String uploadImage = this.fileService.uploadImage(path, image);
		postDto.setImageName(uploadImage);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// UPLOAD ALL POST DATA

	@PostMapping("/user/{userId}/category/{categoryId}/post/{postId}/image/upload")
	public ResponseEntity<PostDto> saveAll(@PathVariable int userId, @PathVariable int categoryId,
			@PathVariable int postId, @RequestParam("image") MultipartFile image, @RequestParam("data") String data) {
		PostDto postDto = this.postService.createPostWithImage(image, data, postId, categoryId, userId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	// SERVE(DOWNLOAD) THE IMAGE

	@GetMapping("/post/image/{imageName}")
	public void downLloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
