package com.microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.entity.User;

@Service
public class UserServiceImpl implements UserService{

	//List of Users
	
	List<User> list= List.of(
			
			new User(100, "rohit"),
			new User(101, "yogesh"),
			new User(102, "mohit")
			
			);
			

	
	@Override
	public User getUserById(long id) {
		
		return list.stream().filter(user->user.getUserId()==id).findAny().orElse(null);
	}

}
