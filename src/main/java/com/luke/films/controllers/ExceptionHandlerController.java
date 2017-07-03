package com.luke.films.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

	public static final String DEFAULT_ERROR_PAGE = "error";
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception ex) {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_PAGE);
		
		mav.addObject("datetime", new Date());
		mav.addObject("exception", ex);
		mav.addObject("url", request.getRequestURL());
		return mav;
	}
	
}
