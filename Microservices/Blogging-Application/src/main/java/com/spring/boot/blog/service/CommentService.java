package com.spring.boot.blog.service;

import java.util.List;

import com.spring.boot.blog.payload.CommentDto;


public interface CommentService {
	
	List<CommentDto> getAllComments();

	CommentDto addComment(CommentDto commentDto, int postId, int userId);

	void deleteComment(int commentId);

}
