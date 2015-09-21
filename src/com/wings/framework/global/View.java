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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.framework.errors.ViewError;
import com.wings.framework.internal.JSPReader;
import com.wings.framework.internal.Reporter;
import com.wings.framework.templating.TemplateEngine;

/**
 * 
 * 
 * @version 1.0
 * @author Anjul Garg <anjulgarg@live.com>
 *
 */
public class View {

	static HttpServletRequest request;
	static HttpServletResponse response;
	static PrintWriter out; 

	public View(){}

	public View( HttpServletRequest request, HttpServletResponse response )
	{
		View.request = request;
		View.response = response;
		try { out = response.getWriter(); } 
		catch (IOException e) { Reporter.error(e); }
	}


	/**
	 * Renders and displays the JSP page.
	 * Builds the JSP page with its extension and sections.
	 * 
	 * @param viewName
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void render( String templateName )
	{
		templateName = "/" + templateName;
		
		try { View.checkExistance( templateName + ".jsp" ); } 
		catch (ViewError e) { Reporter.error(e); }
		
		out.write( TemplateEngine.process( templateName + ".jsp" ) );
		
		out.flush();
		out.close();
	}
	

	/**
	 * Redirects to the specified URL.
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void redirect( String url )
	{
		try { response.sendRedirect( url ); } 
		catch (IOException e) { Reporter.error(e); }
	}

	
	/**
	 * Renders and captures the output of a JSP file as a string.
	 * 
	 * @param templateName
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String read( String templateName )
	{
		templateName = "/" + templateName;
		
		try { View.checkExistance( templateName + ".jsp" ); } 
		catch (ViewError e) { Reporter.error(e); }
		
		return JSPReader.read( templateName + ".jsp" );
	}

	/**
	 * Checks the existance of a View file inside the WebContent Dir.
	 * Throws an error if the file is not found.
	 * 
	 * @param viewName
	 * @throws ViewError
	 */
	private static void checkExistance( String viewName ) throws ViewError
	{
		File file = new File( com.wings.framework.internal.File.CONTEXT.getRealPath( viewName ));
		if( !file.exists() )
			throw new ViewError("View not found : " + viewName);
	}
}
