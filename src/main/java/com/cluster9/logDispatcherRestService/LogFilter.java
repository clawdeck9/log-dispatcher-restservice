package com.cluster9.logDispatcherRestService;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class LogFilter implements Filter{
	
	Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        
		logger.info("request_infos : {} ", httpServletRequest.getRequestURI());
		logger.info("request_infos : {} ", httpServletResponse.getHeader("Cookie"));
		chain.doFilter(request, response);
		logger.info("response infos : {}", response.getContentType());
				
	}

}
