package com.javalec.dto;

import java.sql.Connection;
import java.sql.DriverManager;

public class DtoEmployeeManage {
	String eid;
	String ename;
	String erank;
	String eindate;
	String etelno;
	String ebday;
	String eemail;
	String eaddress;

	public DtoEmployeeManage(String eid, String ename, String erank, String eindate) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.erank = erank;
		this.eindate = eindate;
	}
	
	

	public DtoEmployeeManage(String eid, String ename, String erank, String eindate, String etelno, String ebday,
			String eemail, String eaddress) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.erank = erank;
		this.eindate = eindate;
		this.etelno = etelno;
		this.ebday = ebday;
		this.eemail = eemail;
		this.eaddress = eaddress;
	}



	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getErank() {
		return erank;
	}

	public void setErank(String erank) {
		this.erank = erank;
	}

	public String getEindate() {
		return eindate;
	}

	public void setEindate(String eindate) {
		this.eindate = eindate;
	}

	public String getEtelno() {
		return etelno;
	}

	public void setEtelno(String etelno) {
		this.etelno = etelno;
	}

	public String getEbday() {
		return ebday;
	}

	public void setEbday(String ebday) {
		this.ebday = ebday;
	}

	public String getEemail() {
		return eemail;
	}

	public void setEemail(String eemail) {
		this.eemail = eemail;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

} // End
