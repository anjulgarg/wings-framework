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
package com.wings.framework.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wings.framework.global.Controller;
import com.wings.framework.global.Middleware;
import com.wings.framework.global.Model;
import com.wings.framework.global.Request;
import com.wings.framework.global.Response;
import com.wings.framework.global.Url;
import com.wings.framework.global.View;
import com.wings.framework.internal.JSPReader;
import com.wings.framework.internal.Reporter;
import com.wings.framework.routing.RouteController;
import com.wings.framework.security.CSRF;
import com.wings.framework.security.Session;

/**
 * <h5>Bootstrap.class</h5>
 * As the name suggests, this class ties this framework together.
 * It initializes all the classes necessary for this framework to run 
 * with HttpServletRequest and HttpServletResponse objects.
 * 
 * The instantiations are in a careful order based on priorities and should be
 * changed with precaution. Or it might cause this application to break.
 * 
 * <h5>Note: </h5>
 * The RouteController should always be called last or it will break the app.
 * 
 * @version 1.0
 * @author Anjul Garg <anjulgarg@live.com>
 */
class Bootstrap
{

	public Bootstrap( HttpServletRequest request, HttpServletResponse response, HttpSession session )
	{	
		
		/* Initialize all the necessary classes with the request and response objects */
		new Session( request, response, session );
		
		new CSRF( request, response, session );
		
		new Request( request );
		
		new Response( response );
		
		new View( request, response );
		
		new JSPReader( request, response );
		
		new Middleware( request, response, session );
		
		new Controller( request, response, session );	
		
		new Model( request, response, session );	
		
		new Reporter( request, response );
				
		new Url( request, response );
		
		
		/* Perform the actions. */
		new RouteController( request, response );
		
	}

}
