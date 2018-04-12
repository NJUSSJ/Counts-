package com.seproject.web;

import com.seproject.service.Key;

public class LoginCommand {
	@Key
    private String phoneNumber;

	private String userName;

	private String password;

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
