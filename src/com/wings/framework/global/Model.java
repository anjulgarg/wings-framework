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
