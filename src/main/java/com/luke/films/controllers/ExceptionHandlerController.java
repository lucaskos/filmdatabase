package com.luke.films.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

	public final String DEFAULT_ERROR_PAGE = "error";

	@ExceptionHandler(value = { NullPointerException.class })
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception ex) {
		logger.info(ex.getLocalizedMessage());
		return exceptionBuilder(request, ex);
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	public ModelAndView pageNotFoundHandler(HttpServletRequest request, NoHandlerFoundException ex) {
		System.out.println("pageNotFound");
		return exceptionBuilder(request, ex);
	}
	

	private ModelAndView exceptionBuilder(HttpServletRequest request, Exception ex) {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_PAGE);
		mav.addObject("datetime", new Date());
		mav.addObject("exception", ex);
		mav.addObject("url", request.getRequestURL());
		return mav;
	}

}
