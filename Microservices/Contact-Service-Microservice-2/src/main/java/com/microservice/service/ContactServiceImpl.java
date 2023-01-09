package com.microservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservice.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService{

	
	List<Contact> list= List.of(
			
			new Contact(1, "rohit", "rohit@gmail.com", "8888052448", 100),
			new Contact(2, "rohit", "rohit@gmail.com", "8855052448", 100),
			new Contact(3, "rohit", "rohit@gmail.com", "9188052448", 100),
			new Contact(1, "yogesh", "yogesh@gmail.com", "777052448", 101),
			new Contact(1, "mohit", "mohit@gmail.com", "7887052448", 102),
			new Contact(2, "mohit", "mohit@gmail.com", "9988052448", 102)
			);
			
			
	
	
	@Override
	public List<Contact> getContacts(long id) {
		
		return list.stream().filter(con->con.getUserId()==id).collect(Collectors.toList());
	}
	
	

}
