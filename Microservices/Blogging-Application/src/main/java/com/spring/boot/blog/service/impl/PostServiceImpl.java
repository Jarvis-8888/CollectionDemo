package com.spring.boot.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;
import com.spring.boot.blog.exception.ResourceNotFoundException;
import com.spring.boot.blog.payload.PostDto;
import com.spring.boot.blog.payload.PostResponse;
import com.spring.boot.blog.repository.CategoryRepositoy;
import com.spring.boot.blog.repository.PostRepository;
import com.spring.boot.blog.repository.UserRepository;
import com.spring.boot.blog.service.FileService;
import com.spring.boot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepositoy categoryRepositoy;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// CREATE

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepositoy.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post save = this.postRepository.save(post);
		return this.modelMapper.map(save, PostDto.class);
	}

	// UPDATE

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.postRepository.save(post);
		return this.modelMapper.map(save, PostDto.class);
	}

	// GET ALL POST

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> list = pagePost.getContent();
		List<PostDto> collect = list.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;

	}

	// GET POST BY ID

	@Override
	public PostDto getById(int id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
		return this.modelMapper.map(post, PostDto.class);
	}

	// DELETE POST BY ID

	@Override
	public void deleteById(int id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
		this.postRepository.delete(post);
	}	
	
	// GET ALL POST BY USER

	@Override
	public List<PostDto> getAllPostByUser(int userId) {

		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> list = this.postRepository.findByUser(user);
		return list.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	// GET ALL POST BY CATEGORY

	@Override
	public List<PostDto> getAllPostByCategory(int categoryId) {
		Category category = this.categoryRepositoy.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> list = this.postRepository.findByCategory(category);
		return list.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	// SEARCH BY PATTERN

	@Override
	public List<PostDto> searchPost(String keyword) {

		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	@Override
	public PostDto createPostWithImage(MultipartFile image, String data, int postId, int categoryId, int userId) {

		Post post = null;

		try {
			post = this.objectMapper.readValue(data, Post.class);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Category category = this.categoryRepositoy.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Post post1 = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		post1.setTitle(post.getTitle());
		post1.setContent(post.getContent());
		post1.setCategory(category);
		post1.setUser(user);
		String uploadImage = this.fileService.uploadImage(path, image);
		post.setImageName(uploadImage);
		Post savePost = this.postRepository.save(post);

		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByGroupOfUser() {
		List<Post> list = this.postRepository.findAll();
		return list.stream().map(i-> this.modelMapper.map(i, PostDto.class)).collect(Collectors.toList());
	
	}

}
