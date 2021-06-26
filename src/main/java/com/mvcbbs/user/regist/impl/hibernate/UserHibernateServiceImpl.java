package com.mvcbbs.user.regist.impl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvcbbs.user.regist.exception.AlreadyExistingEmailException;
import com.mvcbbs.user.regist.exception.AlreadyExistingIdException;
import com.mvcbbs.user.regist.service.hibernate.UserHibernateService;
import com.mvcbbs.user.regist.vo.UserVO;
import com.mvcbbs.user.regist.vo.hibernate.RegisterRequestHibernateVO;

@Service("userHibernateService")
public class UserHibernateServiceImpl implements UserHibernateService{
	@Autowired
	private UserHibernateDAO userHibernateDAO;
	
	/**
	 * 회원등록
	 * @param regReqVO
	 * @throws Exception
	 */
	@Override
	public void register(RegisterRequestHibernateVO regReqVO) throws Exception {
		// 등록하고자 하는 이메일이 기존에 등록되어있는지 체크
		UserVO email = userHibernateDAO.selectByEmail(regReqVO.getEmail());
		// 이메일이 존재하는 경우
		if(email != null) {
			throw new AlreadyExistingEmailException(regReqVO.getEmail() + "is duplicate email.");
		}
		// 등록하고자 하는 id가 기존에 등록되어있는지 체크
		UserVO id = userHibernateDAO.selectById(regReqVO.getId());
		// id가 존재하는 경우
		if(id != null) {
			throw new AlreadyExistingIdException(regReqVO.getId() + "is duplicate email.");
		}
		userHibernateDAO.insertUser(regReqVO);
	}
}
