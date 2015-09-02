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

public class Session 
{

	protected static HttpSession session;
	protected static HttpServletRequest request;
	protected static HttpServletResponse response;

	public Session( HttpServletRequest request, HttpServletResponse response, HttpSession session ) 
	{

		Session.session = request.getSession();
		Session.request = request;
		Session.response = response;
	}

	/**
	 * Returns the Session Object with current user session.
	 * 
	 * @return HttpSession Object
	 */
	public static HttpSession getSession()
	{
		return request.getSession();
	}

	/**
	 * Renews the current session. Assigns a new session ID and invalidates the old one.
	 * 
	 * @return HttpSession Object
	 */
	public static HttpSession renew()
	{
		session.invalidate();
		return request.getSession(true);
	}


	/**
	 * Destroys the current session for the user, removing all the attributes
	 * and session id.
	 */
	public static void destroy()
	{
		session.invalidate();
	}
	
	
	/**
	 * Returns the value of an attribute from the request object.
	 * 
	 * 
	 * @param attributeName
	 * @return Object
	 */
	public static Object attrib( String attributeName )
	{
		return session.getAttribute( attributeName );
	}
	
	
	/**
	 * Inserts am attribute name and attribute value into the request object.
	 * 
	 * 
	 * @param String attributeName
	 * @param Object value
	 */
	public static void attrib( String attributeName, Object value )
	{
		session.setAttribute( attributeName, value );
	}
	
	
	/**
	 * Set a Session Timeout in seconds.
	 * 
	 * @param seconds
	 */
	public static void timeout( int seconds )
	{
		session.setMaxInactiveInterval( seconds );
	}

}
