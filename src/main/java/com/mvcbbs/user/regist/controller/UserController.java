package com.mvcbbs.user.regist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvcbbs.user.regist.exception.AlreadyExistingEmailException;
import com.mvcbbs.user.regist.exception.AlreadyExistingIdException;
import com.mvcbbs.user.regist.service.UserService;
import com.mvcbbs.user.regist.service.hibernate.UserHibernateService;
import com.mvcbbs.user.regist.util.RegisterRequestValidator;
import com.mvcbbs.user.regist.vo.RegisterRequestVO;
import com.mvcbbs.user.regist.vo.hibernate.RegisterRequestHibernateVO;

@Controller
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserHibernateService userHibernateService;
	
	private static final String STEP1_JSP = "user/register/step1";
	private static final String STEP2_JSP = "user/register/step2";
	private static final String STEP3_JSP = "user/register/step3";
	private static final String STEP3_H_JSP = "user/register/step3h";
	/**
	 * 회원가입 첫페이지
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/step1")
	public String step1() throws Exception {
		return STEP1_JSP;
	}
	
	/**
	 * 회원가입 동의 이후 보여질 페이지 처리
	 * @param agree
	 * @param req
	 * @param resp
	 * @return mav
	 * @throws Exception
	 */
	@RequestMapping(value="/step2", method=RequestMethod.POST)
	public ModelAndView step2(@RequestParam(name = "agree", defaultValue = "false") boolean agree, HttpServletRequest req, HttpServletResponse resp ) throws Exception {
		// 동의하지 않았을 경우
		if(!agree) {
			ModelAndView mav = new ModelAndView(STEP1_JSP);
			return mav;
		// 동의한 경우
		} else {
			ModelAndView mav = new ModelAndView(STEP2_JSP);
			mav.addObject("registerRequestVO", new RegisterRequestVO());                   // Validator.java
//			mav.addObject("registerRequestHibernateVO", new RegisterRequestHibernateVO()); // hibernate
			return mav;
		}
	}
	
	/**
	 * 회원가입 완료 페이지 처리
	 * @param regReqVO
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/step3", method=RequestMethod.POST)
	public ModelAndView step3(RegisterRequestVO regReqVO, Errors errors) throws Exception {
		new RegisterRequestValidator().validate(regReqVO, errors);
		// errors.rejectValues 에 값이 등록된 경우
		if(errors.hasErrors()) {
			ModelAndView mav = new ModelAndView(STEP2_JSP);
			return mav;
		}
		try {
			userService.register(regReqVO);
		} catch (AlreadyExistingEmailException e) {
			errors.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
			ModelAndView mav = new ModelAndView(STEP2_JSP);
			return mav;
		} catch (AlreadyExistingIdException e) {
			errors.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
			ModelAndView mav = new ModelAndView(STEP2_JSP);
			return mav;
		}
		ModelAndView mav = new ModelAndView(STEP3_JSP);
		return mav;
	}
	/**
	 * 회원가입 완료 페이지 처리 (hibernate)
	 * @param regReqVO
	 * 	@Valid 어노테이션이 추가되었다. 이것으로 인해 아래 로직이 실행되기 전에 RegisterRequest에서 유효성 검사를 하고 BindingResult에 그 결과가 담긴다.
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/step3h", method=RequestMethod.POST)
	public ModelAndView step3h(@Valid RegisterRequestHibernateVO regReqVO, BindingResult bindingResult) throws Exception {
		ModelAndView mav = new ModelAndView();
		// @Valid 검증
		if(bindingResult.hasErrors()) {
			mav.setViewName(STEP2_JSP);
			return mav;
		}
		
		// 비밀번호 확인
		boolean check = regReqVO.isPwEqualToCheckPw();
		if(!check) {
			bindingResult.rejectValue("checkPw", "noMatch", "비밀번호를 확인해주세요.");
			mav.setViewName(STEP2_JSP);
			return mav;
		}
		try {
			userHibernateService.register(regReqVO);
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
			mav.setViewName(STEP2_JSP);
			return mav;
		} catch (AlreadyExistingIdException e) {
			bindingResult.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
			mav.setViewName(STEP2_JSP);
			return mav;
		}
		mav.setViewName(STEP3_H_JSP);
		return mav;
	}
}
