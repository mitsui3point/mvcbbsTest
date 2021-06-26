package com.mvcbbs.user.regist.service;

import com.mvcbbs.user.login.vo.AuthInfoVO;
import com.mvcbbs.user.login.vo.LoginCommandVO;
import com.mvcbbs.user.regist.vo.RegisterRequestVO;

public interface UserService {
	/**
	 * 회원등록
	 * @param regReqVO
	 * @throws Exception
	 */
	public void register(RegisterRequestVO regReqVO) throws Exception;
	/**
	 * 로그인시 권한정보
	 * @param loginCommandVO
	 * @return
	 * @throws Exception
	 */
	public AuthInfoVO loginAuth(LoginCommandVO loginCommandVO) throws Exception;
}
