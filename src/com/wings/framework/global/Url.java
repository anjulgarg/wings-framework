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
