package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.entity.User;
import com.microservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") long id) {
		User user = userService.getUserById(id);

		List list = restTemplate.getForObject("http://localhost:9002/contact/user/" + id, List.class);

		user.setContacts(list);
		return user;
	}

}
