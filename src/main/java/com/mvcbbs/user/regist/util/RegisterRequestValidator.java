package com.mvcbbs.user.regist.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mvcbbs.user.regist.vo.RegisterRequestVO;

public class RegisterRequestValidator implements Validator{

	// regexr.com
	// 이메일 정규표현식
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//			"/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	// 이메일 정규표현식을 담을 객체
	private Pattern pattern;
	// constructor
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequestVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterRequestVO regReqVO = (RegisterRequestVO) target;
		
		// 이메일 정보가 비어있을 경우
		if(regReqVO.getEmail() == null || regReqVO.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required", "필수 정보 입니다.");
		// 이메일 정보가 들어있을 경우
		} else {
			Matcher matcher = pattern.matcher(regReqVO.getEmail());		// pattern 에 담긴 emailRegExp 의 정규표현식으로 검사
			// 정규표현식과 맞지 않을 경우
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad", "올바르지 않은 이메일 형식입니다.");
			}
		}
		// 이름이 비어있을 경우
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "필수 정보 입니다.");
		// 비밀번호가 비어있을 경우
		ValidationUtils.rejectIfEmpty(errors, "pw", "required", "필수 정보 입니다.");
		// 비밀번호 확인이 비어있을 경우
		ValidationUtils.rejectIfEmpty(errors, "checkPw", "required", "필수 정보 입니다.");
		// 비밀번호가 들어있을 경우
		if(!regReqVO.getPw().isEmpty()) {
			// 비밀번호와 비밀번호 확인이 같지 않을 경우
			if(!regReqVO.isPwEqualToCheckPw()) {
				errors.rejectValue("checkPw", "nomatch", "비밀번호가 일치하지 않습니다.");
			}
		}
	}

}
