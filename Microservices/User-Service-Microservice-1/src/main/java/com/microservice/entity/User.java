package com.microservice.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private long userId;
	private String userName;

	List<Contact> contacts = new ArrayList<>();
	
	
	
	
	

	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public List<Contact> getContacts() {
		return contacts;
	}



	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}



	public User(long userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}



	public User(long userId, String userName, List<Contact> contacts) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.contacts = contacts;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
