package com.seproject.domain;
import com.seproject.service.Key;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable{
	@Key
	int id;
	public int getId(){return id;}
	public void setId(int id){this.id=id;}
}
