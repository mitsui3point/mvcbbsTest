package com.mvcbbs.user.regist.exception;

/**
 * 회원 등록 : email 존재시 예외처리
 * @author adm
 *
 */
@SuppressWarnings("serial")
public class AlreadyExistingEmailException extends RuntimeException{
	public AlreadyExistingEmailException(String message) {
		super(message);
	}
}
