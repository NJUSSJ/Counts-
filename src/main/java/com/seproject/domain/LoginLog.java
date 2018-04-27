package com.seproject.domain;
import com.seproject.service.Key;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class LoginLog implements Serializable{
	@Key
	int id;
	ArrayList<String> list=new ArrayList<String>();
	public int getId(){return id;}
	public void setId(int id){this.id=id;}
	public ArrayList<String> getList(){return list;}
	public void setList(ArrayList<String> list){ this.list=list;}
}
