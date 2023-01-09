package com.spring.boot.blog.payload;

public class ApiResponse {
	
	private String messgae;
	private boolean success;
	
	
	public String getMessgae() {
		return messgae;
	}
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ApiResponse(String messgae, boolean success) {
		super();
		this.messgae = messgae;
		this.success = success;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
