package com.wings.framework.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wings.framework.internal.DynaCall;
import com.wings.framework.internal.File;

/**
 * Servlet Filter implementation class WingsFilter
 */
public class WingsFilter implements Filter 
{

	/**
	 * Default constructor. This filter can not be instantiated.
	 */
	public WingsFilter() {}


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException 
	{

		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final HttpSession session = httpServletRequest.getSession();

		/*
		 * Initialize the Bootstrapper Class with HttpServletRequest and HttpServletResponse objects.
		 */
		try { new Bootstrap( httpServletRequest, httpServletResponse, session ); } 
		catch (Exception e) {	e.printStackTrace(); }

		/*
		 * If the Config file contains any URLs to skip. This filter will skip them.
		 */
		if( Config.SKIP_URL.contains(httpServletRequest.getServletPath()) )
			request.getRequestDispatcher( httpServletRequest.getServletPath() )
			.forward( httpServletRequest, httpServletResponse );
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init( FilterConfig config ) throws ServletException 
	{
		ServletContext context = config.getServletContext();

		/*
		 * Initialize the File class with ServletContext.
		 */
		new File( context );

		/*
		 * Initialize the Config class and updates the Config variables.
		 */
		try { new Config(); } 
		catch (Exception e) { e.printStackTrace(); }

		/*
		 * Initialize the User Defined Routes Class and store all the routes.
		 */
		try{ DynaCall.newInstance( Config.ROUTES_CLASS ); }
		catch( Exception e){ e.printStackTrace(); }

	}

}
