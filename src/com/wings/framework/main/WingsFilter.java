// Copyright (c) 2015 Anjul Garg <anjulgarg@live.com>
//
// Permission to use, copy, modify, and distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
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
