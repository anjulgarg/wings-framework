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
package com.wings.framework.internal;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.framework.main.Config;

public class Reporter 
{
	static HttpServletRequest request;
	static HttpServletResponse response;
	static PrintWriter out;


	/**
	 * Inititalize the Reporter.
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	public Reporter( HttpServletRequest request, HttpServletResponse response )
	{
		Reporter.request = request;
		Reporter.response = response;
		try { out = response.getWriter(); } 
		catch (IOException e) {	Reporter.error(e); }
	}


	/**
	 * Displays the error page with the error message, error cause and full stacktrace in a
	 * visually appealing and user friendly format.
	 * 
	 * 
	 * @param e the Exception Object
	 */
	public static void error( Exception e )

	{
		/* If Debug Mode is false. Prints the error to stacktrace. */
		if( !Config.DEBUG_MODE ) e.printStackTrace();

		/* If Debug Mode is true. Displays the error page in the browser. */
		if( Config.DEBUG_MODE )
		{
			/* Get the full stacktrace as string array line by line. */
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter( stringWriter );
			e.printStackTrace( printWriter );
			String[] lines = stringWriter.toString().split(System.getProperty("line.separator"));


			/* Write the error page. */
			out.write("<!DOCTYPE Html>");
			out.write("<html>");
			out.write("<head><title>Error Reporter</title></head>");
			out.write("<style>*{ margin: 0; padding: 0; }</style>");
			out.write("<body style='background-color:#dfdfdf; font-family:monospace;'>");
			out.write("<div style='min-height:500px; padding:20px; margin:25px 0 0 6%; max-width:85%; background-color:#fff; border-radius:5px; box-shadow: 0px 0px 15px #888;'>");
			out.write("<h2 style='padding-bottom:0px; color:firebrick; font-size: 20px; '>ERROR : "
					+ e.toString() + "</h2><br>");

			if( e.getCause() != null )
				out.write("<h2 style='padding-bottom:15px; color:firebrick; font-size: 20px; border-bottom:1px solid #aaa;'>CAUSE : "
						+ e.getCause() + "</h2><br>");

			out.write("<h3 style='color:midnightblue;'>STACKTRACE: </h3><br>");
			out.write("<ul style='list-style:none; padding-left:0;'>");

			for (String line : lines) 
			{	
				out.write("<li style='border:1px solid #ddd; padding:5px; margin-top:-1px; font-size:15px; color:midnightblue; height:auto;'>");
				out.write( line );
				out.write("</li>");
			}

			out.write("</ul>");
			out.write("</div><br><br>");
			out.write("</body>");
			out.write("</html>");
		}
	}

}
