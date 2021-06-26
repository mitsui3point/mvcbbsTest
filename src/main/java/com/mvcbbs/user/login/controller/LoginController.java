package com.mvcbbs.user.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvcbbs.user.login.exception.IdPasswordNotMatchingException;
import com.mvcbbs.user.login.vo.AuthInfoVO;
import com.mvcbbs.user.login.vo.LoginCommandVO;
import com.mvcbbs.user.regist.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	protected Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	private static final String LOGIN_FORM = "user/login/loginForm";
	private static final String LOGIN_SUCCESS = "user/login/loginSuccess";
	
	/**
	 * 로그인 화면 출력(cookie 구분)
	 * @param loginCommand
	 * @param rememberCookie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginForm(LoginCommandVO loginCommandVO,
					@CookieValue(value="REMEMBER", required=false) Cookie rememberCookie) throws Exception {
		
		if(rememberCookie!=null) {
			loginCommandVO.setId(rememberCookie.getValue());
			loginCommandVO.setRememberId(true);
		}
		
		ModelAndView mv = new ModelAndView(LOGIN_FORM);
		return mv;
	}
	/**
	 * 로그인
	 * @param loginCommandVO
	 * @param bindingResult
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginForm(@ModelAttribute("loginCommandVO") @Valid LoginCommandVO loginCommandVO,		// @Valid 어노테이션을 이용해 유효성검사를 한다.
			BindingResult bindingResult,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// @Valid 어노테이션을 이용해 유효성검사를 한다.
		if (bindingResult.hasErrors()) {
			mav.setViewName(LOGIN_FORM);
			return mav;
		}
		try {
			// 로그인에 성공하면 authInfoVO 반환
			AuthInfoVO authInfoVO = userService.loginAuth(loginCommandVO);
			session.setAttribute("authInfo", authInfoVO);
			
			// 쿠키를 생성하여 로그인 될때 생성된 id를 쿠키에 저장
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommandVO.getId());
			// 쿠키를 찾을 경로를 변경
			rememberCookie.setPath("/");
			// 아이디 기억 체크한 경우
			if (loginCommandVO.isRememberId()) {
				// 아이디 기억을 체크했다면, 7일 정도의 유효시간을 정해준다. ( 60*60*24*30 : 30일 )
				rememberCookie.setMaxAge(60*60*24*7);
			// 아이디 기억 체크하지 않은 경우
			} else {
				// 아이디 기억 체크하지 않았다면, 설정하지 않는다.
				rememberCookie.setMaxAge(0);
			}
			// response 객체에 설정된 cookie 를 담아준다.
			response.addCookie(rememberCookie);
			mav.setViewName(LOGIN_SUCCESS);
		// Service에서 exception을 한 부분이다. 아이디가 없거나, 비밀번호가 맞지않으면 에러를 보낸다.
		} catch (IdPasswordNotMatchingException e) {
			bindingResult.rejectValue("pw", "notMatch", "아이디와 비밀번호가 맞지 않습니다.");
			mav.setViewName(LOGIN_FORM);
			return mav;
		}
		return mav;
	}
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) throws Exception {
		// 세션 전체를 날림
		session.invalidate();
		// 로그아웃버튼을 누르면 바로 메인페이지로 리다이렉트
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
}
