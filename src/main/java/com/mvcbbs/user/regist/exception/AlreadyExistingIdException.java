package com.mvcbbs.user.regist.exception;

/**
 * 회원 등록 : id 존재시 예외처리
 * @author adm
 *
 */
@SuppressWarnings("serial")
public class AlreadyExistingIdException extends RuntimeException{
	public AlreadyExistingIdException(String message) {
		super(message);
	}
}
