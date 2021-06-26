package com.mvcbbs.user.regist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvcbbs.user.login.exception.IdPasswordNotMatchingException;
import com.mvcbbs.user.login.vo.AuthInfoVO;
import com.mvcbbs.user.login.vo.LoginCommandVO;
import com.mvcbbs.user.regist.exception.AlreadyExistingEmailException;
import com.mvcbbs.user.regist.exception.AlreadyExistingIdException;
import com.mvcbbs.user.regist.service.UserService;
import com.mvcbbs.user.regist.vo.RegisterRequestVO;
import com.mvcbbs.user.regist.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	private static final String REDIRECT_LOGIN = "redirect:/login/login";
	
	/**
	 * 회원등록
	 * @param regReqVO
	 * @throws Exception
	 */
	@Override
	public void register(RegisterRequestVO regReqVO) throws Exception {
		// 등록하고자 하는 이메일이 기존에 등록되어있는지 체크
		UserVO email = userDAO.selectByEmail(regReqVO.getEmail());
		// 이메일이 존재하는 경우
		if(email != null) {
			throw new AlreadyExistingEmailException(regReqVO.getEmail() + "is duplicate email.");
		}
		// 등록하고자 하는 id가 기존에 등록되어있는지 체크
		UserVO id = userDAO.selectById(regReqVO.getId());
		// id가 존재하는 경우
		if(id != null) {
			throw new AlreadyExistingIdException(regReqVO.getId() + "is duplicate email.");
		}
		userDAO.insertUser(regReqVO);
	}
	/**
	 * 로그인시 권한정보
	 * @param loginCommandVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public AuthInfoVO loginAuth(LoginCommandVO loginCommandVO) throws Exception {
		// 아이디가 있는지 확인
		UserVO userVO = userDAO.selectById(loginCommandVO.getId());
		
		// 아이디 없을 시 exception 처리
		if (userVO == null) {
			throw new IdPasswordNotMatchingException("저장된 id가 존재하지 않습니다.");
		}
		// 비밀번호가 저장된 비밀번호값과 다를 경우 exception 처리
		if (!userVO.matchPassword(loginCommandVO.getPw())) {
			throw new IdPasswordNotMatchingException("저장된 비밀번호가 실제 비밀번호와 틀립니다.");
		}
		// 아이디와 비밀번호가 확인되면 AuthInfoVO 에 회원정보 저장
		return new AuthInfoVO(userVO.getId(), userVO.getName(), userVO.getGrade());
	}
}
