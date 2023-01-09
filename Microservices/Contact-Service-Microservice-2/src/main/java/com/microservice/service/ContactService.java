package com.microservice.service;

import java.util.List;

import com.microservice.entity.Contact;

public interface ContactService {
	
	public List<Contact> getContacts(long id);

}
