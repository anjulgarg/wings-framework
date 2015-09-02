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
package com.wings.framework.global;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wings.framework.errors.UrlParamError;
import com.wings.framework.internal.Reporter;

public class Middleware 
{

	static HttpServletRequest request;
	static HttpServletResponse response;
	static HttpSession session;
	static PrintWriter out;
	
	/**
	 *  Default Constructor
	 */
	public Middleware(){}
	
	
	/**
	 * Constructor used to initialize this class with the required object values.
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public Middleware( HttpServletRequest request, HttpServletResponse response, HttpSession session )
	{
		Middleware.request = request;
		Middleware.response = response;
		Middleware.session = session;
		try { out = response.getWriter(); } 
		catch (IOException e) {	Reporter.error(e); }
	}
	

	/**
	 * Returns a parameter value supplied by a user in the request Object.
	 * 
	 * 
	 * @param paramName
	 * @return value
	 */
	public String param( String paramName )
	{
		return request.getParameter( paramName );
	}
	
	
	/**
	 * Returns the value of an attribute from the request object.
	 * 
	 * 
	 * @param attributeName
	 * @return Object
	 */
	public Object attrib( String attributeName )
	{
		return request.getAttribute( attributeName );
	}
	
	
	/**
	 * Inserts an attribute name and attribute value into the request object.
	 * 
	 * 
	 * @param attributeName
	 * @param value
	 */
	public void attrib( String attributeName, Object value )
	{
		request.setAttribute( attributeName, value );
	}
	
	
	/**
	 * Returns the value of a parameter in the URL.
	 * 
	 * @param paramName as String. 
	 * @return value as a String.
	 */
	public String urlParam( String paramName )
	{
		try {
			return Url.getParam( paramName );
		} catch (UrlParamError e) {
			Reporter.error(e);
		}
		return "";
	}
	
	
	/**
	 * Returns a Map of all the form parameter names 
	 * and their values submitted by the user in the current request.
	 * 
	 * 
	 * @param request that is the HttpServletRequest Object
	 * @return Map<String, Object>
	 * 
	 * 
	 */
	public Map<String, Object> paramValues( HttpServletRequest request )
	{
		Enumeration<?> enumeration = request.getParameterNames();
		Map<String, Object> paramValues = new HashMap<String, Object>();
		
		while( enumeration.hasMoreElements() )
		{  
	        String parameterName = (String) enumeration.nextElement();  
	        paramValues.put( parameterName, request.getParameter(parameterName) );  
	    }  
		
		return paramValues;
	}

}
