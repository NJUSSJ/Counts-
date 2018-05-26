package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {

    @Key
	@Id
	@Column(name="phoneNumber")
 	private	String phoneNumber="";
    @Column(name="userName")
	private String userName="";
    @Column(name="password")
	private String password="";
    @Column(name="credit")
	private double credit=0;
    @Column(name="category")
	@Searchable(varName = "category")
	private int category=0;//0是管理员，1 是发起者，2是工人

	@Searchable(varName = "level")
	@Column(name="level")
	private int level=1;
	@Column(name="description")
	private String description="";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public void setCategory(int category){
	    this.category=category;
    }

    public int getCategory(){
	    return this.category;
    }

}
