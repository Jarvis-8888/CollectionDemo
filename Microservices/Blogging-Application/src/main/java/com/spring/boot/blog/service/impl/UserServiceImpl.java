package com.spring.boot.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.entities.User;
import com.spring.boot.blog.exception.ResourceNotFoundException;
import com.spring.boot.blog.payload.UserDto;
import com.spring.boot.blog.repository.UserRepository;
import com.spring.boot.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = dtoToEntity(userDto);
		// User entity is saved
		User savedUser = this.userRepository.save(user);

		return entityToDto(savedUser);

	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepository.save(user);

		return this.entityToDto(updatedUser);

	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		return entityToDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepository.findAll();

		return users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

		this.userRepository.delete(user);

	}

	// UserDto to User
	public User dtoToEntity(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	// User to UserDto
	public UserDto entityToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
