package com.mvcbbs.user.regist.service.hibernate;

import com.mvcbbs.user.regist.vo.hibernate.RegisterRequestHibernateVO;

public interface UserHibernateService {
	/**
	 * 회원등록
	 * @param regReqVO
	 * @throws Exception
	 */
	public void register(RegisterRequestHibernateVO regReqVO) throws Exception;
}
