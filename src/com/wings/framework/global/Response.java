/**
 * 
 */
package com.wings.framework.global;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Anjul Garg
 *
 */
public class Response 
{

	public static HttpServletResponse response;
	
	public Response( HttpServletResponse response ) 
	{
		Response.response = response;
	}

}
