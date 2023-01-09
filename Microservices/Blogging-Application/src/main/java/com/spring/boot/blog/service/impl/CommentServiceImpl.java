package com.spring.boot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.entities.Comment;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;
import com.spring.boot.blog.exception.ResourceNotFoundException;
import com.spring.boot.blog.payload.CommentDto;
import com.spring.boot.blog.repository.CommentRepository;
import com.spring.boot.blog.repository.PostRepository;
import com.spring.boot.blog.repository.UserRepository;
import com.spring.boot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;
	
	
	
	

	@Override
	public CommentDto addComment(CommentDto commentDto, int postId, int userId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepository.delete(comment);
	}

	@Override
	public List<CommentDto> getAllComments() {
		 List<Comment> list = this.commentRepository.findAll();
		 return list.stream().map(i-> this.modelMapper.map(i, CommentDto.class)).collect(Collectors.toList());
		 
	}

	

	

}
