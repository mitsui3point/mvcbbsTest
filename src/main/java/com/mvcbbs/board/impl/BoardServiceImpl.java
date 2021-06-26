package com.mvcbbs.board.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvcbbs.board.controller.BoardController;
import com.mvcbbs.board.impl.dao.BoardDAO;
import com.mvcbbs.board.service.BoardService;
import com.mvcbbs.board.vo.BoardParamVO;
import com.mvcbbs.common.paging.Criteria;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardDAO boardDAO;
	
	/**
	 * 게시판 목록 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectBoardList(Criteria cri) throws Exception {
		// 게시판 목록 조회
		List<Map<String, Object>> selectBoardList = boardDAO.selectBoardList(cri);
		return selectBoardList;
	}
	/**
	 * 게시판 목록 개수 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectBoardListCnt(Criteria cri) throws Exception {
		// 게시판 목록 조회
		int selectBoardListCnt = boardDAO.selectBoardListCnt(cri);
		return selectBoardListCnt;
	}
	/**
	 * 게시글 정보 조회, 조회수 cnt포함
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectBoardInfoAddCnt(String idx) throws Exception {
		// 게시글 정보 조회
		Map<String, Object> selectBoardInfo = boardDAO.selectBoardInfoAddCnt(idx);
		return selectBoardInfo;
	}
	/**
	 * 게시판 정보 조회
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectBoardInfo(String idx) throws Exception {
		// 게시글 정보 조회
		Map<String, Object> selectBoardInfo = boardDAO.selectBoardInfo(idx);
		return selectBoardInfo;
	}
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void insertBoard(Map<String, Object> paramMap) throws Exception {
		// 게시글 등록
		boardDAO.insertBoard(paramMap);
	}
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void insertBoardUsingVO(BoardParamVO boardParamVO) throws Exception {
		// 게시글 등록
		boardDAO.insertBoardUsingVO(boardParamVO);
	}
	/**
	 * 게시글 수정
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void updateBoard(Map<String, Object> paramMap) throws Exception {
		// 게시글 등록
		boardDAO.updateBoard(paramMap);
	}
	@Override
	public void deleteBoard(Map<String, Object> paramMap) throws Exception {
		// 게시글 등록
		boardDAO.deleteBoard(paramMap);
	}
}
