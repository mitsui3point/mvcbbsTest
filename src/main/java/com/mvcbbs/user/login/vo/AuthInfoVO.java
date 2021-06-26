package com.mvcbbs.user.login.vo;

/**
 * 회원 정보 세션 유지
 * 회원 정보 세션을 유지해주는 기능을 할 것이다. 
 * UserVO를 그대로 사용하게 되면 보안성에 취약할 뿐만 아니라, 
 * 	불필요한 정보까지 유지가 된다. 
 * 그래서 따로 세션 정보 유지를 위해 만들었다.
 * @author adm
 */
public class AuthInfoVO {
	
	private String id = "";
	private String name = "";
	private int grade = 0;
	public AuthInfoVO(String id, String name, int grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
