package com.mvcbbs.common.enumuration;

/**
 * 차후 사용예정
 * @author adm
 *
 */
public enum NamespaceEnum {
	XML_PATH("com.mvcbbs.bbs.impl.ConnTestMapper"),
	USER_INFO(".selectUserInfo");
	
	final private String name;
	
	private NamespaceEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
