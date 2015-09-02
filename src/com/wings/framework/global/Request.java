/**
 * 
 */
package com.wings.framework.global;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Anjul Garg
 *
 */
public class Request {

	public static HttpServletRequest request;
	/**
	 * @param request  
	 * 
	 */
	public Request( HttpServletRequest request ) 
	{
		Request.request = request;
	}

}
