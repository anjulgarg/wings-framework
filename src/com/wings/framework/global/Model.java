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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Model {
	
	protected static HttpServletRequest request;
	protected static HttpServletResponse response;
	protected static HttpSession session;
	
	
	/**
	 * Default Constructor
	 */
	public Model() {}

	
	/**
	 * Constructor used to initialize this Controller with the necessary request, response and session object.
	 * 
	 * 
	 * @param response  
	 * @param request 
	 * @param session
	 * @throws IOException 
	 * 
	 */
	public Model( HttpServletRequest request, HttpServletResponse response, HttpSession session )
	{
		Model.request = request;
		Model.response = response;
		Model.session = session;
	}
	
	

}
