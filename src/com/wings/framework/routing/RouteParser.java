package com.wings.framework.routing;

import javax.servlet.http.HttpServletRequest;

import com.wings.framework.errors.RoutingError;
import com.wings.framework.global.Url;
import com.wings.framework.internal.Reporter;

/**
 * This class is used in the WingsMVC Framework for matching a user defined
 * route agains a requested uri.
 * It can match wildcard routes containing * or {} .
 * 
 * 
 * @author Anjul Garg <anjulgarg@live.com>
 *
 */
class RouteParser 
{

	/**
	 * Default Constructor. This class can not be instantiated.
	 */
	private RouteParser() {}

	/**
	 * This method is used to match an incoming URL with a user defined Routes.
	 * If the URL equals a user defined route and declares all the required parameters (if any)
	 * the method return true.
	 * 
	 * @param route
	 * @param request
	 * @return
	 * 
	 */
	static boolean match( String route, HttpServletRequest request )
	{
		String[] urlParts;
		String[] routeParts;

		String url = request.getServletPath().
				replaceAll("[\\/]+", "/");

		if( url.length() > 1 )								// Removes the Trailing '/' from a URL.
			if( url.charAt(url.length()-1) == '/' )				
				url = url.substring(0, url.length()-1);


		// If Both * and {} have been used in the same route. Throw an error
		if( route.contains("*") && route.contains("{") )
		{
			try { throw new RoutingError( "Wildcard * and {} can not be used together in the same route." ); } 
			catch (RoutingError e) { Reporter.error(e); }
		}



		/* When the Route contains "*" Wildcard   */
		if( route.contains("*") )		// Check if the route contains a wildcard route '*'
		{
			route = route.substring( 0, route.indexOf('*') );
			
			if( url.startsWith(route) )
				return true;
		}


		urlParts = url.split("\\/");
		routeParts = route.split("/");


		/* When the route contains "{}" wildcard  */
		if( route.contains("{") ) {							// Check if the Routes has Parameters.
			if( urlParts.length == routeParts.length) {		// Check if Routes is equal to URL in Length.

				for ( int i = 0; i < urlParts.length; i++ ) 
				{
					if( routeParts[i].contains("{") )		// if contains {param} do nothing
						i++;
					else if( !routeParts[i].equals( urlParts[i] ) )	// match url that dont contain {param}
						return false;
				}
				//	if( route.substring(0, route.indexOf('{')).equals(url.substring(0, route.indexOf('{'))) );

				getURLParams( urlParts, routeParts, request );	// Calling the getURLParams method

				return true;	

			}
		} else if( route.equals(url) | route.equals("404") ) 	// If no Routes Parameters are defined. Simply match strings.
			return true;



		return false;
	}

	/**
	 * This method is used by RouteService class for extracting Routes Parameters
	 * from an incoming URL. The parameters are enclosed in {} in a user defined route.
	 * 
	 * @param urlParts
	 * @param routeParts
	 * @param request
	 * 
	 */
	static void getURLParams( String urlParts[], String routeParts[], HttpServletRequest request ) 
	{

		String varName = new String();

		for(int i=0; i<routeParts.length; i++)
			for(int j=0; j<routeParts[i].length(); j++)
				if( routeParts[i].charAt(j) == '{' )
				{
					j++;
					while( routeParts[i].charAt(j) != '}' )
					{
						varName += routeParts[i].charAt(j);
						j++;
					}

					/*
					 * Adds the urlParam name and its valued to Url.param hashmap.
					 */
					Url.setParam(varName, urlParts[i]);

					/* Adds the Url.param hashmap to the request object 
					 * so that it can be accessed inside a JSP.
					 */
					request.setAttribute( "urlParam", Url.param );

					varName = "";
				}

	}

}
