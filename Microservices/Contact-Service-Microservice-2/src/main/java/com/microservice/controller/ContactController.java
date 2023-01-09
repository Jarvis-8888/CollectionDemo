package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Contact;
import com.microservice.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
	
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("user/{id}")
	public List<Contact> getContact(@PathVariable("id") long id)
	{
		return contactService.getContacts(id);
	}

}
