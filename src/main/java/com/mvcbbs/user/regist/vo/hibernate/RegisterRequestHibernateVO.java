package com.mvcbbs.user.regist.vo.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class RegisterRequestHibernateVO {
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	@Column
	@NotEmpty(message = "이메일을 입력해주세요.")
	@Email(regexp = emailRegExp, message = "이메일 형식에 맞춰 올바르게 입력해주세요.")
	private String email = "";
	
	@Column
//	@NotEmpty(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "\\w{4,8}", message = "아이디를 4~8자로 입력해주세요.")
	private String id = "";
	
	@Column
//	@NotEmpty(message = "이름을 입력해주세요.")
	@Pattern(regexp = "[가-힣\\w]{2,8}", message = "한글 영문 2~8자로 입력해주세요.")
	private String name = "";
	
	@Column
//	@NotEmpty(message = "비밀번호를 입력해주세요.")
	@Size(min = 4, max = 12, message = "비밀번호를 4~12자로 입력해주세요.")
	private String pw = "";
	
	@Column
//	@NotEmpty(message = "비밀번호를 입력해주세요.")
	@Size(min = 4, max = 12, message = "비밀번호를 4~12자로 입력해주세요.")
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
