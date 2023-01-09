package com.spring.boot.blog.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.blog.payload.ApiResponse;
import com.spring.boot.blog.payload.CommentDto;
import com.spring.boot.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable int postId, @PathVariable int userId,
			@RequestBody CommentDto commentDto) {
		CommentDto addedComment = this.commentService.addComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(addedComment, HttpStatus.CREATED);
	}
	
	@GetMapping("/comment")
	public ResponseEntity<Map<Integer,List<CommentDto>>> getAllComments()
	{
		List<CommentDto> list = this.commentService.getAllComments();
		Map<Integer,List<CommentDto>> collect = list.stream().collect(Collectors.groupingBy(comment-> comment.getId()));
		return new ResponseEntity<Map<Integer,List<CommentDto>>>(collect,HttpStatus.OK);
		
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!", true), HttpStatus.OK);
	}

}
