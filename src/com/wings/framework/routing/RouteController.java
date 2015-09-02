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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.framework.errors.SecurityError;
import com.wings.framework.global.View;
import com.wings.framework.internal.DynaCall;
import com.wings.framework.internal.Reporter;
import com.wings.framework.security.CSRF;

public class RouteController 
{
	private String className = new String();
	private String methodName = new String();

	/**
	 * Intercepts and compares each url to a predefined route
	 * and invokes the controller, middleware or view 
	 * assigned to that route.
	 * It can intercept GET, POST, PUT, DELETE requests.
	 * It also extracts the parameters inside the URL if any.
	 * 
	 * <h5>Note: <h5>
	 * Calls the Middleware first and Controller after them.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * 
	 */
	public RouteController( HttpServletRequest request, HttpServletResponse response )
	{
		switch ( request.getMethod().toUpperCase() ) 
		{

		case "GET":
			/* MiddleWares First */
			for (String item : Get.middlewareRouteList) 
			{
				if( RouteParser.match(item, request) )
				{
					this.invokeController( Get.middlewareList.get(item) );
				}
			}
			/* Controllers Second */
			for (String item : Get.routeList) 
			{
				if( RouteParser.match(item, request) )
				{
					this.invokeController( Get.controllerList.get(item) );
					break;
				}
			}
			
			break;
			
		case "POST":
			for (String item : Post.middlewareRouteList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Post.middlewareList.get(item) );
					}
				} 
				catch ( SecurityError e ) { Reporter.error(e); }
			}
			for (String item : Post.routeList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Post.controllerList.get(item) );
						break;
					}
				} 
				catch ( SecurityError e ) { Reporter.error(e); }
			}
			
			break;
			
		case "PUT":
			for (String item : Put.middlewareRouteList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Put.middlewareList.get(item) );
					}
				} 
				catch ( SecurityError e ) {	Reporter.error(e); }
			}
			for (String item : Put.routeList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Put.controllerList.get(item) );
						break;
					}
				} 
				catch ( SecurityError e ) {	Reporter.error(e); }
			}
			
			break;
			
		case "DELETE":
			for (String item : Delete.middlewareRouteList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Delete.middlewareList.get(item) );
					}
				}
				catch ( SecurityError e ) { Reporter.error(e); }
			}
			for (String item : Delete.routeList) 
			{
				try 
				{
					if( RouteParser.match(item, request) && CSRF.check() )	/* Also Checks for CSRF */
					{
						this.invokeController( Delete.controllerList.get(item) );
						break;
					}
				} catch ( SecurityError e ) { Reporter.error(e); }
			}
			
			break;		
		}

	}


	/**
	 * Extracts the Controller name from the Route specified by the user,
	 * and calls that controller using DynaCall which uses Java Reflection.
	 * 
	 * 
	 * @param controller
	 * @throws Exception
	 * 
	 */
	void invokeController( String controller )
	{
		this.className = controller.substring( 0, controller.indexOf("@") );
		this.methodName = controller.substring( controller.indexOf("@")+1, controller.length() );

		if( className.equalsIgnoreCase("view")  ) 
		{
			View.render( methodName );
			
		} else if( className.equalsIgnoreCase("redirect")  ) 
		{
			View.redirect( methodName );
		} else
			DynaCall.method( className, methodName );
	}


}
