package com.javalec.dto;

public class DtoStoreManage {

	String storeseq2;
	String sname;
	String saddress;
	String stelno;
	String eid;
	String ename;
	String scrn;
	String sclosedate;
	String sopendate;

	public DtoStoreManage(String storeseq2, String sname, String saddress, String stelno, String eid, String ename,
			String scrn, String sopendate) {
		super();
		this.storeseq2 = storeseq2;
		this.sname = sname;
		this.saddress = saddress;
		this.stelno = stelno;
		this.eid = eid;
		this.ename = ename;
		this.scrn = scrn;
		this.sopendate = sopendate;
	}

	public String getStoreseq2() {
		return storeseq2;
	}

	public void setStoreseq2(String storeseq2) {
		this.storeseq2 = storeseq2;
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getStelno() {
		return stelno;
	}

	public void setStelno(String stelno) {
		this.stelno = stelno;
	}

	public String getScrn() {
		return scrn;
	}

	public void setScrn(String scrn) {
		this.scrn = scrn;
	}

	public String getSclosedate() {
		return sclosedate;
	}

	public void setSclosedate(String sclosedate) {
		this.sclosedate = sclosedate;
	}

	public String getSopendate() {
		return sopendate;
	}

	public void setSopendate(String sopendate) {
		this.sopendate = sopendate;
	}

} // End
