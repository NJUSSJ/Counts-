package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.ValueType;



public class User {
	@Key(type = ValueType.STRING)
	String phoneNumber;

	String userName;
	String password ;
	double credit;
	int level;
	UserType usertype;
}
