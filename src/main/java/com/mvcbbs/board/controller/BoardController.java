package com.mvcbbs.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvcbbs.board.service.BoardService;
import com.mvcbbs.board.vo.BoardParamVO;
import com.mvcbbs.common.paging.Criteria;
import com.mvcbbs.common.paging.PageMaker;
import com.mvcbbs.user.login.vo.AuthInfoVO;
import com.mvcbbs.user.regist.service.UserService;


@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 상수풀	
	 */
	private static final String BOARD_LIST = "board/list";
	private static final String BOARD_WRITE = "board/write";
	private static final String BOARD_WRITE_URL = "board/boardWrite";
	private static final String BOARD_DETAIL = "board/detail";
	private static final String BOARD_DETAIL_REDIRECT_URL = "redirect:/board/boardDetail";
	private static final String BOARD_UPDATE = "board/update";
	private static final String BOARD_UPDATE_URL = "board/boardUpdate";
	private static final String BOARD_DELETE = "board/delete";
	/**
	 * 게시글 리스트 페이지 호출
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
//	public ModelAndView openBoardList(CommandMap commandMap) throws Exception {
	public ModelAndView openBoardList(Criteria cri) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 게시물 총 갯수 조회
		int boardTotCnt = boardService.selectBoardListCnt(cri);
		// 페이징 처리 로직
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardTotCnt);
		// 조회한 페이지 게시물 목록 조회
		List<Map<String, Object>> boardList = boardService.selectBoardList(cri);
		// mav 등록
		mav.addObject("boardList", boardList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("pageInfo", cri);
		mav.setViewName(BOARD_LIST);
		return mav;
	}
	/**
	 * 게시글 작성 페이지 호출
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite(Map<String, Object> paramMap,
			HttpSession session, 
			HttpServletRequest req, 
			HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		AuthInfoVO authInfo = session.getAttribute("authInfo") != null ? (AuthInfoVO) session.getAttribute("authInfo") : null;
		// 로그인 체크
		if(authInfo != null) {
			// 로그인 아이디 모델에 추가 : 작성자
			mav.addObject("creaId", authInfo.getId());
			mav.setViewName(BOARD_WRITE);
		}
		return mav;
	}
	/**
	 * 게시글 상세 페이지 호출
	 * @param idx
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardDetail")
//	@RequestMapping(value = "/boardDetail", method = RequestMethod.POST)
	public ModelAndView boardDetail(
			@RequestParam(value = "idx", required = true) String idx,
			@ModelAttribute(value = "goListFrm") Criteria cri,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			// 게시글 조회
			Map<String, Object> boardInfo = boardService.selectBoardInfoAddCnt(idx);
			
			mav.addObject("boardInfo", boardInfo);
			mav.addObject("pagingInfo", cri);
			mav.setViewName(BOARD_DETAIL);
			
		return mav;
	}
	/**
	 * 게시글 등록 parameter Map
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public Map<String, Object> boardInsert(
			@RequestBody Map<String, Object> paramMap,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "login" :
				try {
					log.debug("paramMap : {}",paramMap);
					// 글 작성
					boardService.insertBoard(paramMap);
					resultMap.put("resultCode", "100");
				} catch (Exception e) {
					resultMap.put("resultCode", "-100");
					if(e instanceof NullPointerException) {
						log.debug(e.toString());
						throw new NullPointerException();
					} else if (e instanceof IOException) {
						log.debug(e.toString());
						throw new IOException();
					} else if (e instanceof RuntimeException) {
						log.debug(e.toString());
						throw new RuntimeException();
					} else {
						log.debug("Exception : {}", e.toString());
						throw new Exception();
					}
				}
				break;
			case "notLogin" :
				resultMap.put("resultCode", "-10");
				break;
		}
		return resultMap;
	}
	/**
	 * 게시글 등록 parameter boardParamVO
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/boardInsertForm", method = RequestMethod.POST)
	public Map<String, Object> boardInsertForm(
			@ModelAttribute("boardParamVO") BoardParamVO boardParamVO,
			MultipartHttpServletRequest mtfRequest,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "login" :
				try {
					log.debug("boardParamVO : {}", boardParamVO);
					// 글 작성
					boardService.insertBoardUsingVO(boardParamVO);
					resultMap.put("resultCode", "100");
				} catch (Exception e) {
					resultMap.put("resultCode", "-100");
					if(e instanceof NullPointerException) {
						log.debug(e.toString());
						throw new NullPointerException();
					} else if (e instanceof IOException) {
						log.debug(e.toString());
						throw new IOException();
					} else if (e instanceof RuntimeException) {
						log.debug(e.toString());
						throw new RuntimeException();
					} else {
						log.debug("Exception : {}", e.toString());
						throw new Exception();
					}
				}
				break;
			case "notLogin" :
				resultMap.put("resultCode", "-10");
				break;
		}
		return resultMap;
	}
	/**
	 * 게시글 등록 parameter @RequestParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardInsertReqParam")
	public @ResponseBody Map<String, Object> boardInsertReqParam(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "creaId", required = false) String id,
			@RequestParam(value = "contents", required = false) String contents,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("id", id);
		paramMap.put("contents", contents);
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "login" :
				try {
					boardService.insertBoard(paramMap);
					resultMap.put("resultCode", "100");
				} catch (Exception e) {
					resultMap.put("resultCode", "-100");
					if(e instanceof NullPointerException) {
						log.debug(e.toString());
						throw new NullPointerException();
					} else if (e instanceof IOException) {
						log.debug(e.toString());
						throw new IOException();
					} else if (e instanceof RuntimeException) {
						log.debug(e.toString());
						throw new RuntimeException();
					} else {
						log.debug("Exception : {}", e.toString());
						throw new Exception();
					}
				}
				break;
			case "notLogin" :
				resultMap.put("resultCode", "-10");
				break;
		}
		return resultMap;
	}
	
	/**
	 * 게시글 수정 화면 호출
	 * @param commandMap
	 * @param prevUrl
	 * @param session
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdate(@RequestParam(value = "idx", required = true) String idx,
			HttpSession session, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// mav
		ModelAndView mav = new ModelAndView();
		
		AuthInfoVO authInfo = session.getAttribute("authInfo") != null ? (AuthInfoVO) session.getAttribute("authInfo") : null;
		Map<String, Object> boardInfo = boardService.selectBoardInfo(idx);
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "matchWriter" :
				mav.addObject("boardInfo",boardInfo);
				mav.setViewName(BOARD_UPDATE);
				break;
		}
		return mav;
	}
	/**
	 * 게시글 수정
	 * @param paramMap
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public Map<String, Object> boardUpdate(@RequestBody Map<String, Object> paramMap, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "matchWriter" :
				try {
					log.debug("paramMap : {}",paramMap);
					// 글 수정
					boardService.updateBoard(paramMap);
					resultMap.put("resultCode", "100");
				} catch (Exception e) {
					resultMap.put("resultCode", "-100");
					if(e instanceof NullPointerException) {
						log.debug(e.toString());
						throw new NullPointerException();
					} else if (e instanceof IOException) {
						log.debug(e.toString());
						throw new IOException();
					} else if (e instanceof RuntimeException) {
						log.debug(e.toString());
						throw new RuntimeException();
					} else {
						log.debug("Exception : {}", e.toString());
						throw new Exception();
					}
				}
				break;
			case "notLogin" :
				resultMap.put("resultCode", "-10");
				break;
			case "notMatchWriter" :
				resultMap.put("resultCode", "-20");
				break;
		}
		return resultMap;
	}
	/**
	 * 게시글 삭제
	 * @param paramMap
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public Map<String, Object> boardDelete(@RequestBody Map<String, Object> paramMap, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 권한체크 : CommonInterceptor.java
		String authCode = request.getAttribute("isAuth").toString();
		switch (authCode) {
			case "matchWriter" :
				try {
					log.debug("paramMap : {}",paramMap);
					// 글 삭제여부 Y처리
					boardService.deleteBoard(paramMap);
					resultMap.put("resultCode", "100");
				} catch (Exception e) {
					resultMap.put("resultCode", "-100");
					if(e instanceof NullPointerException) {
						log.debug(e.toString());
						throw new NullPointerException();
					} else if (e instanceof IOException) {
						log.debug(e.toString());
						throw new IOException();
					} else if (e instanceof RuntimeException) {
						log.debug(e.toString());
						throw new RuntimeException();
					} else {
						log.debug("Exception : {}", e.toString());
						throw new Exception();
					}
				}
				break;
			case "notLogin" :
				resultMap.put("resultCode", "-10");
				break;
			case "notMatchWriter" :
				resultMap.put("resultCode", "-20");
				break;
		}
		return resultMap;
	}
}
