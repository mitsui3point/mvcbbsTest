package com.mvcbbs.user.regist.vo;

public class RegisterRequestVO {
	private String email = "";
	private String id = "";
	private String name = "";
	private String pw = "";
	private String checkPw = "";
	
	// 비밀번호 일치여부 확인
	public boolean isPwEqualToCheckPw() {
		return pw.equals(checkPw);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCheckPw() {
		return checkPw;
	}
	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}
}
