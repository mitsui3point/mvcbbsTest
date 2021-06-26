package com.mvcbbs.board.impl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvcbbs.board.vo.BoardParamVO;
import com.mvcbbs.common.paging.Criteria;

@Repository("boardDAO")
public class BoardDAO {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSession sql;
	
	private final String SQL_NS = this.getClass().getName() + ".";
	private static final String SELECT_BOARD_LIST = "selectBoardList";
	private static final String SELECT_BOARD_LIST_CNT = "selectBoardListCnt";
	private static final String SELECT_BOARD_INFO = "selectBoardInfo";
	private static final String UPDATE_HIT_CNT = "updateHitCnt";
	private static final String INSERT_BOARD = "insertBoard";
	private static final String INSERT_BOARD_VO = "insertBoardUsingVO";
	private static final String UPDATE_BOARD = "updateBoard";
	private static final String DELETE_BOARD = "deleteBoard";
	
	/**
	 * 게시판 목록 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBoardList(Criteria cri) throws Exception {
		// 게시판 목록 조회
		List<Map<String, Object>> selectBoardList = sql.selectList(SQL_NS + SELECT_BOARD_LIST, cri);
		return selectBoardList;
	}
	
	/**
	 * 게시판 목록 개수 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int selectBoardListCnt(Criteria cri) throws Exception {
		// 게시판 목록 조회
		int selectBoardListCnt = sql.selectOne(SQL_NS + SELECT_BOARD_LIST_CNT, cri);
		return selectBoardListCnt;
	}
	
	/**
	 * 게시글 정보 조회, 조회수 cnt포함
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardInfoAddCnt(String idx) throws Exception {
		// 게시글 조회수 +1
		sql.update(SQL_NS + UPDATE_HIT_CNT, idx);
		// 게시글 정보 조회
		Map<String, Object> selectBoardInfo = sql.selectOne(SQL_NS + SELECT_BOARD_INFO, idx);
		return selectBoardInfo;
	}
	
	/**
	 * 게시글 정보 조회만
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardInfo(String idx) throws Exception {
		// 게시글 정보 조회
		Map<String, Object> selectBoardInfo = sql.selectOne(SQL_NS + SELECT_BOARD_INFO, idx);
		return selectBoardInfo;
	}
	
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	public void insertBoard(Map<String,Object> paramMap) throws Exception {
		// 게시글 등록
		sql.insert(SQL_NS + INSERT_BOARD, paramMap);
	}
	
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	public void insertBoardUsingVO(BoardParamVO boardParamVO) throws Exception {
		// 게시글 등록
		sql.insert(SQL_NS + INSERT_BOARD_VO, boardParamVO);
	}
	
	/**
	 * 게시글 수정
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateBoard(Map<String,Object> paramMap) throws Exception {
		// 게시글 수정
		sql.update(SQL_NS + UPDATE_BOARD, paramMap);
	}
	
	/**
	 * 게시글 삭제
	 * @param paramMap
	 * @throws Exception
	 */
	public void deleteBoard(Map<String,Object> paramMap) throws Exception {
		// 게시글 삭제
		sql.update(SQL_NS + DELETE_BOARD, paramMap);
	}
	
}
