package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;
<<<<<<< HEAD
import com.seproject.service.ValueType;

import java.util.ArrayList;
=======
>>>>>>> 356124b8a6e29d3224845c8b32ef822b85fce7c2


public class User {
	@Key
 	private	String phoneNumber;
    @Searchable(varName = "userName")
	private String userName;
	private String password;
	private double credit;
	@Searchable(varName = "level")
	private int level;
<<<<<<< HEAD
	private UserType usertype;
	private ArrayList<String> collectionList = new ArrayList<String>();
=======
>>>>>>> 3a80ad90e81c4a8ad16ca32423471eb3987ccaac

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addCollectionList(String collectionID){
		collectionList.add(collectionID);
	}
}
