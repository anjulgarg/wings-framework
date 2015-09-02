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
package com.wings.framework.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Post 
{
	public static final List<String> routeList = new ArrayList<String>();
	public static final HashMap<String, String> controllerList = new HashMap<String, String>();

	public static final List<String> middlewareRouteList = new ArrayList<String>();
	public static final HashMap<String, String> middlewareList = new HashMap<String, String>();

	
	/**
	 * Define a new route and the corresponding controller
	 * that will respond to POST requests.
	 * The controller name defined should contain the complete 
	 * class name and the method that acts as the controller.
	 * <p>
	* <h5>Example :</h5>
	 * <p>
	 * new Post( "/home", "com.app.HomeController@welcome" )
	 * </p>
	 * </P>
	 * <p>
	 * Here "com.app.HomeController" is the Class name and
	 * welcome is the method welcome() in that class.
	 * 
	 * <h5>Note:</h5>
	 * The controller methods should not have any input parameters.
	 * </P>
	 * 
	 * @param String route
	 * @param String controllerName
	 */
	public Post( String route, String controller )
	{
		routeList.add(route);
		controllerList.put(route, controller);
	}	

	
	/**
	 * Define a new route of type controller or middleware
	 * that will respond to POST requests.
	 * The controller/middleware name defined should contain the complete 
	 * class name and the method that acts as the controller/middleware.
	 * <p>
	* <h5>Example :</h5>
	 * <p>
	 * new Post( "/home", "controller", "com.app.HomeController@welcome" )
	 * </p>
	 * </P>
	 * <p>
	 * Here "com.app.HomeController" is the Class name and
	 * welcome is the method welcome() in that class.
	 * 
	 * <h5>Note:</h5>
	 * The controller methods should not have any input parameters.
	 * </P>
	 * 
	 * 
	 * @param String type
	 * @param String route
	 * @param String controllerName
	 */
	public Post( String route, String type, String controller )
	{
		if( type.equalsIgnoreCase( "controller" ) )
		{
			routeList.add( route );
			controllerList.put( route, controller );
		}

		if( type.equalsIgnoreCase( "middleware" ) )
		{
			middlewareRouteList.add( route );
			middlewareList.put( route, controller );
		}
	}


	/**
	 * Define a new array of routes of type controller or 
	 * middleware that will respond to Post requests.
	 * The controller/middleware name defined should contain the complete 
	 * class name and the method that acts as the controller/middleware.
	 * <p>
	 * <h5>Example :</h5>
	 * <p>
	 * new Get( "controller", new String[]{"/home", "/index"}, "com.app.HomeController@welcome" )
	 * </p>
	 * </P>
	 * <p>
	 * Here "com.app.HomeController" is the Class name and
	 * welcome is the method welcome() in that class.
	 * 
	 * <h5>Note:</h5>
	 * The controller methods should not have any input parameters.
	 * </P>
	 * 
	 * @param String type
	 * @param String routes[]
	 * @param String controller
	 */
	public Post( String type, String[] routes, String controller )
	{
		if( type.equalsIgnoreCase( "controller" ) )
			for (String route : routes) 
			{
				routeList.add( route );
				controllerList.put( route, controller );
			}

		if( type.equalsIgnoreCase( "middleware" ) )
			for (String route : routes) 
			{
				middlewareRouteList.add( route );
				middlewareList.put( route, controller );
			}
	}	


}
