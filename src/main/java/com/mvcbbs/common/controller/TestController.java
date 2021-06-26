package com.mvcbbs.common.controller;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvcbbs.common.argument.command.CommandMap;

@Controller
public class TestController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * interceptor 테스트
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/interceptorTest")
	public ModelAndView interceptorTest() throws Exception {
		
		ModelAndView mv = new ModelAndView("");
		
		// 컨트롤러가 실행되고 log4j가 동작하는 지 확인하려 한다.
		log.debug("인터셉터 테스트입니다.");
		
		return mv;
	}
	/**
	 * HandlerMethodArgumentResolver map 구현체 테스트
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testMapArgumentResolver")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if (commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : {}, value : {}", entry.getKey(), entry.getValue());
			}
		}
		return mav;
	}
}
