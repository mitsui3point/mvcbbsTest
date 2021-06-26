package com.mvcbbs.user.regist.vo;

import java.sql.Date;

public class UserVO {
	
	private int idx = 0;
	private String id = "";
	private String email = "";
	private String name = "";
	private String password = "";
	private int grade = 0;
	private Date regdate;
	
	// 비밀번호 확인
	public boolean matchPassword(String pw) {
		return this.password.equals(pw);
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
