package com.mvcbbs.user.regist.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvcbbs.user.regist.vo.RegisterRequestVO;
import com.mvcbbs.user.regist.vo.UserVO;

@Repository("userDAO")
public class UserDAO {
	@Autowired
	private SqlSession sql;
	
	private static final String XML_PATH		= "com.mvcbbs.user.regist.impl.UserDAO";
	private static final String SELECT_BY_EMAIL	= ".selectByEmail";
	private static final String SELECT_BY_ID	= ".selectById";
	private static final String INSERT_USER		= ".insertUser";
	
	/**
	 * 기존회원과 이메일 중복체크
	 * @param regReqVO
	 * @return userVO
	 * @throws Exception
	 */
	public UserVO selectByEmail(String email) throws Exception {
		return sql.selectOne(XML_PATH + SELECT_BY_EMAIL, email);
	}
	/**
	 * 기존회원과 id 중복체크
	 * @param regReqVO
	 * @return
	 * @throws Exception
	 */
	public UserVO selectById(String id) throws Exception {
		return sql.selectOne(XML_PATH + SELECT_BY_ID, id);
	}
	/**
	 * 회원등록
	 * @param regReqVO
	 * @throws Exception
	 */
	public void insertUser(RegisterRequestVO regReqVO) throws Exception {
		sql.insert(XML_PATH + INSERT_USER, regReqVO);
	}
}
