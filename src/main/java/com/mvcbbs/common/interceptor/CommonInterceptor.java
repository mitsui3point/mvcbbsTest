package com.mvcbbs.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvcbbs.board.service.BoardService;
import com.mvcbbs.user.login.vo.AuthInfoVO;
/**
 * 	인터셉터는 중간에서 가로챈다는 의미를 가진다. 
 * 	스프링에서도 말 그대로 중간에 요청을 가로채서 어떠한 일을 하는 것을 말한다. 
 * 	인터셉터의 정식 명칭은 핸들러 인터셉터(Handler Interceptor)이다. 
 * 	클라이언트의 요청이 컨트롤러에 가기 전에 가로채고, 
 * 		응답이 클라이언트에게 가기전에 가로챈다. 
 * 	즉, 인터셉터는 DispatcherServlet이 컨트롤러를 요청하기 전,후에 요청과 응답을 가로채서 가공할 수 있도록 해준다.
 * 	
 * 	예를 들어 로그인 기능이 있을 때, 
 * 		로그인을 한 사람만 보이는 페이지가 있고, 
 * 		로그인 한 사람만 글을 작성할 수 있다고 하자. 
 * 	그러면 페이지 컨트롤러에서도 로그인 확인 로직이 들어가고, 
 * 		글 작성 컨트롤러에서도 로그인 확인 로직이 들어가야 한다. 
 * 	인터셉터를 사용하면 컨트롤러에 로직이 로그인 확인 로직이 없어도 컨트롤러에 들어가기전에 인터셉터에서 로그인 확인을 하고 컨트롤러로 보낸다. 
 * 	즉, 하나의 인터셉터로 프로젝트 내의 모든 요청에 로그인 여부를 확인할 수 있다. 
 * 	
 * 	인터셉터를 만들려면 HandlerInterceptorAdaptor 클래스를 상속받아야 한다. 
 * 	HandlerInterceptorAdaptor 클래스를 상속받으면 사용할 수 있는 4가지의 메서드들이 있다. 
 * 	perHandle(), postHandle(), afterCompletion(), afterConcurrentHandlingStarted()이 있다.
 * @author adm
 */
/**
 * CommonInterceptor :
 * 하나의 요청에 대한 컨트롤러 처리의 시작과 끝을 표시해 줄 것이다.
 * 클라이언트의 요청이 컨트롤러에 가기 전에 가로채고, 
 * 응답이 클라이언트에게 가기전에 가로챈다. 
 * @author adm
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private BoardService boardService;
	
	protected Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	// 전처리기는(preHandle) 클라이언트에서 컨트롤러로 요청할 때 가로채는 것이다. 쉽게 말해 컨트롤러가 호출되기 전에 실행되는 메서드이다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 서버의 로깅 레벨이 디버그레벨인지 체크
		if(log.isDebugEnabled()) {
			log.debug("===================       START       ===================");	// 전처리기에서 컨트롤러가 시작됨을 알리는 START 로그를 출력한다.
			log.debug(" Request URI \t:  " + request.getRequestURI());				// 현재 호출되는 URL을 알려준다
		}
		// 게시판 권한관련 추가
		String uri = request.getRequestURI();// uri
		HttpSession session = request.getSession();
		AuthInfoVO authInfo = session.getAttribute("authInfo") != null ? (AuthInfoVO) session.getAttribute("authInfo") : null;
		// 수정, 삭제 : 게시글마다 권한체크
		if(uri.contains("/board/boardUpdate") ||
				uri.contains("/board/boardDelete")) {
			// 로그인 했을 경우
			if (authInfo != null) {
				String loginId = authInfo.getId();
				String idx = request.getParameter("idx") != null ? request.getParameter("idx") : "";
				String creaId = !"".equals(idx) ? boardService.selectBoardInfo(idx).get("creaId").toString() : "";
				// 작성자인지 체크
				if ( loginId.equals(creaId) ) {
					request.setAttribute("isAuth", "matchWriter");
				// 작성자가 아닌 경우
				} else {
					request.setAttribute("isAuth", "notMatchWriter");
				} 
			// 로그인 안됐을 경우
			} else {
				request.setAttribute("isAuth", "notLogin");
			}
		// 작성 : 로그인 여부 체크
		} else if(uri.contains("/board/boardInsert") ) {
			// 로그인 했을 경우
			if (authInfo != null) {
				request.setAttribute("isAuth", "login");
			} else {
				request.setAttribute("isAuth", "notLogin");
			}
		}

		return super.preHandle(request, response, handler);
	}

	// 후처리기는(postHandle) 컨트롤러에서 클라이언트로 요청할 때 가로채는 것이다. 쉽게 말해 컨트롤러가 호출되고 난 후에 실행되는 메서드이다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 서버의 로깅 레벨이 디버그레벨인지 체크
		if(log.isDebugEnabled()) {
			log.debug("===================       END       ===================\n");	// 후처리기에서 컨트롤러가 끝남을 알리는 END 로그를 출력한다.
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// 오버라이드 되지 않은 HandlerInterceptorAdapter 내 afterComplition() 메서드는 컨트롤러의 처리가 끝나고 화면처리까지 모두 끝나면 실행되는 메서드이다.
}
