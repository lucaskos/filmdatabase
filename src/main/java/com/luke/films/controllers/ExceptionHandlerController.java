package com.luke.films.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class ExceptionHandlerController extends DefaultHandlerExceptionResolver{

	private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);

	public final String DEFAULT_ERROR_PAGE = "error";

	@ExceptionHandler(value = { NullPointerException.class })
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception ex) {
		LOGGER.error(ex.getLocalizedMessage());
		return exceptionBuilder(request, ex);
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	public ModelAndView pageNotFoundHandler(HttpServletRequest request, NoHandlerFoundException ex) {
		LOGGER.warn("Page not found - 404");
		return exceptionBuilder(request, ex);
	}

	@Override
	@ExceptionHandler
	protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		return exceptionBuilder(request, ex);
	}

	private ModelAndView exceptionBuilder(HttpServletRequest request, Exception ex) {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_PAGE);
		mav.addObject("datetime", new Date());
		mav.addObject("exception", ex);
		mav.addObject("url", request.getRequestURL());
		LOGGER.error("Exception fo given URL : " + request.getRequestURL());
		return mav;
	}

}
