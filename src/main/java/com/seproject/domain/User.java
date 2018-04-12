package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;
import java.util.ArrayList;


public class User {
	@Searchable(varName = "phoneNumber")
 	private	String phoneNumber;
	@Key
	private String userName;
	private String password;
	private double credit;
	@Searchable(varName = "level")
	private int level;
	//private UserType usertype;

//	private ArrayList<String> collectionList = new ArrayList<String>();

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
/*
	public ArrayList<String> getCollectionList(){return collectionList;}

	public void setCollectionList(ArrayList<String> collectionList){this.collectionList=collectionList;}

	public void addCollectionList(String collectionID){
		collectionList.add(collectionID);
	}
	*/
}
