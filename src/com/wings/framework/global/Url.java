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

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.framework.errors.UrlParamError;

public class Url 
{
	
	static HttpServletRequest request;
	static HttpServletResponse response;

	public static HashMap<String, String> param = new HashMap<String, String>();
	
	/**
	 * Constructor used to initialize this class with Http Objects.
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	public Url( HttpServletRequest request, HttpServletResponse response ) 
	{
		Url.request = request;
		Url.response = response;
		
		Url.param.clear();
	}
	
	
	/**
	 * Adds a url parameter key and value.
	 * 
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public static void setParam( String paramName, String paramValue )
	{
		param.put( paramName , paramValue );
	}
	
	
	/**
	 * Returns the value of a parameter in the URL.
	 * 
	 * 
	 * @param paramName
	 * @return
	 * @throws UrlParamError 
	 */
	public static String getParam( String paramName ) throws UrlParamError
	{
		if( !param.containsKey(paramName) )
			throw new UrlParamError("Url Parameter '" + paramName + "' is null or not declared." );
		else
			return param.get( paramName );
	}

}
