package com.wings.framework.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PublicResourceFilter implements Filter {

	private static final String RESOURCE_PATH = "/public";
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String path = ((HttpServletRequest) request).getRequestURI();
		
		/*
		 * Exclude the /public Directory from URL Check. 
		 * The /public directory will contain all the static files such as js and css files.
		 */
        if (path.toLowerCase().startsWith(RESOURCE_PATH)) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


 

}
