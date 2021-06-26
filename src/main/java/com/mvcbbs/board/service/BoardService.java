package com.mvcbbs.board.service;

import java.util.List;
import java.util.Map;

import com.mvcbbs.board.vo.BoardParamVO;
import com.mvcbbs.common.paging.Criteria;

public interface BoardService {
	/**
	 * 게시판 목록 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBoardList(Criteria cri) throws Exception;
	/**
	 * 게시판 목록 총 개수 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int selectBoardListCnt(Criteria cri) throws Exception;
	/**
	 * 게시글 정보 조회, 조회수 cnt포함
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardInfoAddCnt(String idx) throws Exception;
	/**
	 * 게시판 정보 조회
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardInfo(String idx) throws Exception;
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	public void insertBoard(Map<String, Object> paramMap) throws Exception;
	/**
	 * 게시글 등록
	 * @param paramMap
	 * @throws Exception
	 */
	public void insertBoardUsingVO(BoardParamVO boardParamVO) throws Exception;
	/**
	 * 게시글 수정
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateBoard(Map<String, Object> paramMap) throws Exception;
	/**
	 * 게시글 삭제
	 * @param paramMap
	 * @throws Exception
	 */
	public void deleteBoard(Map<String, Object> paramMap) throws Exception;
}