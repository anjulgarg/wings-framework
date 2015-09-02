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
package com.wings.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wings.framework.errors.SecurityError;
import com.wings.framework.main.Config;

/**
 * @author Anjul Garg
 *
 */
public class CSRF 
{

	static HttpServletRequest request;
	static HttpSession session;

	/**
	 * This is the default constructor and is used to initialize the CSRF class 
	 * for each incoming request. It checks each POST, PUT or DELETE request for the CSRF key. This csrf_key 
	 * is stored in users session.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception 
	 */
	public CSRF( HttpServletRequest request, HttpServletResponse response, HttpSession session )
	{
		CSRF.request = request;
		CSRF.session = session;

		if( session.getAttribute("csrf_key") == "" | session.getAttribute("csrf_key") == null )
			session.setAttribute( "csrf_key", keyGen(50) );
	}

	/**
	 * This method checks an incoming POST request for the csrf key.
	 * If the incoming request has a parameter csrf_key and its value matches 
	 * with the key stored in the users current session, the post request is allowed
	 * to move forward.
	 * 
	 * @param request
	 * @param session
	 * @return true or false
	 * @throws SecurityError 
	 */
	public static boolean check() throws SecurityError
	{
		if( Config.CSRF_CHECK )
		{
			if( request.getParameter("csrf_key") == "" | request.getParameter("csrf_key") == null )
				throw new SecurityError( "CSRF key is null for this post request." );

			if( request.getParameter("csrf_key").equals(session.getAttribute("csrf_key")) )
			{
				/* Uncomment the below code to generate a new csrf_token for every post request. */
				/* session.setAttribute( "csrf_key", keyGen(50) ); */
				return true;
			}
			else throw new SecurityError( "CSRF authentication failed for this post request." );
		}
		
		return true;
	}

	/**
	 * This method generates a secure key containing alphanumeric and special 
	 * characters. It takes a parameter length which is used to define the length 
	 * of this key.
	 * 
	 * @param int length
	 * @return String key
	 */
	static String keyGen( int length )
	{
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String key = new String();
		for( int i=0; i<length; i++ )
			key += base.charAt( (int) Math.floor(Math.random()*base.length()) ); 

		return key;
	}

}
