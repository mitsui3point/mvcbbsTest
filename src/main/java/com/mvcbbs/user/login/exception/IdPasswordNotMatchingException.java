package com.mvcbbs.user.login.exception;

public class IdPasswordNotMatchingException extends RuntimeException{
	public IdPasswordNotMatchingException(String message) {
		super(message);
	}
}
