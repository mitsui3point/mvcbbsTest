package com.mvcbbs.common.argument.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mvcbbs.common.argument.command.CommandMap;
/**
 * HandlerMethodArgumentResolver customizing class
 * @author adm
 *
 */
public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1,
			NativeWebRequest arg2, WebDataBinderFactory arg3) throws Exception {
		// CommandMap 객체 생성
		CommandMap commandMap = new CommandMap();
		
		HttpServletRequest request = (HttpServletRequest) arg2.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();
		
		String key = null;
		String[] values = null;
		// request에 담겨있는 값을 iterator를 이용해서 하나씩 가져오는 로직
		while (enumeration.hasMoreElements()) {
			key = (String) enumeration.nextElement();
			values = request.getParameterValues(key);
			// request에 담겨있는 모든 key와 value를 commandMap에 저장
			if (values != null) {
				commandMap.put(key, (values.length > 1) ? values : values[0]);
			}
		}
		// 모든 파라미터가 담겨있는 commandMap을 반환한다.
		return commandMap;
	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		return CommandMap.class.isAssignableFrom(arg0.getParameterType());
	}
	
}
