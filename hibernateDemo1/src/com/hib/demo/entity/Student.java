package com.hib.demo.entity;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 8166316009095220038L;
	
	private String stuNo;
	private String stuName;
	private String stuPass;
	private String stuSex;
	private int stuAge;
	private String Mobile;
	private String Email;
	private String Address;
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPass() {
		return stuPass;
	}
	public void setStuPass(String stuPass) {
		this.stuPass = stuPass;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

}
