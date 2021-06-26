package com.mvcbbs.user.login.vo;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommandVO {
	
	// "", " ", null 에 대해 400 bad request : 정상 (https://hakurei.tistory.com/233) 
	@NotEmpty(message = "아이디를 입력해주세요.")
	private String id = "";
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String pw = "";
	private boolean rememberId = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public boolean isRememberId() {
		return rememberId;
	}
	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}
}
