package com.wings.framework.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Get 
{
	public static final List<String> routeList = new ArrayList<String>();
	public static final HashMap<String, String> controllerList = new HashMap<String, String>();

	public static final List<String> middlewareRouteList = new ArrayList<String>();
	public static final HashMap<String, String> middlewareList = new HashMap<String, String>();


	/**
	 * Define a new route and the corresponding controller
	 * that will respond to GET requests.
	 * The controller name defined should contain the complete 
	 * class name and the method that acts as the controller.
	 * <p>
	 * <h5>Example :</h5>
	 * new Get( "/home", "com.app.HomeController@welcome" )
	 * </p>
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
	public Get( String route, String controller )
	{
		routeList.add( route );
		controllerList.put( route, controller );
	}	


	/**
	 * Define a new route of type controller or middleware
	 * that will respond to GET requests.
	 * The controller/middleware name defined should contain the complete 
	 * class name and the method that acts as the controller/middleware.
	 * <p>
	 * <h5>Example :</h5>
	 * <p>
	 * new Get( "/home", "controller", "com.app.HomeController@welcome" )
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
	public Get( String route, String type, String controller )
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
	 * <p>
	 * Define a new array of routes of type controller or 
	 * middleware that will respond to GET requests.
	 * The controller/middleware name defined should contain the complete 
	 * class name and the method that acts as the controller/middleware.
	 * </p>
	 * <p>
	 * <h5>Example :</h5>
	 * new Get( "controller", new String[]{"/home", "/index"}, "com.app.HomeController@welcome" )
	 * </P>
	 * <p>
	 * Here "com.app.HomeController" is the Class name and
	 * welcome is the method welcome() in that class.
	 * </p>
	 * <h5>Note:</h5>
	 * The controller methods should not have any input parameters.
	 * </P>
	 * 
	 * @param String type
	 * @param String routes[]
	 * @param String controller
	 */
	public Get( String type, String[] routes, String controller )
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
