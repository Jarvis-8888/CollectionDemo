package com.spring.boot.blog.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.blog.payload.ApiResponse;
import com.spring.boot.blog.payload.UserDto;
import com.spring.boot.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> list = this.userService.getAllUsers();
		//list.stream().sorted((u1,u2)-> u1.getId().compareTo(u2.getId())).collect(Collectors.toList());
		return new ResponseEntity<List<UserDto>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id)
	{
		UserDto userDto = this.userService.getUserById(id);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") int id)
	{
		UserDto updateUser = this.userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id)
	{
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully !", true),HttpStatus.OK);
	}
	
}
