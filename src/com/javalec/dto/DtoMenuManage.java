package com.javalec.dto;

import java.sql.Connection;
import java.sql.DriverManager;

public class DtoMenuManage {

	String menuid;
	String menuname;
	int menuprice;
	
	
	public DtoMenuManage(String menuid, String menuname, int menuprice) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.menuprice = menuprice;
	}


	public String getMenuid() {
		return menuid;
	}


	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}


	public String getMenuname() {
		return menuname;
	}


	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}


	public int getMenuprice() {
		return menuprice;
	}


	public void setMenuprice(int menuprice) {
		this.menuprice = menuprice;
	}
	
	
	
	

} // End
