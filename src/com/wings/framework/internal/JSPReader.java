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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class JSPReader
{
	static HttpServletRequest request;
	static HttpServletResponse response;
	
	public JSPReader( HttpServletRequest request, HttpServletResponse response )
	{
		JSPReader.request = request;
		JSPReader.response = response;
	}
	
	/**
	 * Renders and captures a JSP Page and stores its output in a string.
	 * Returns that String which can be used as needed.
	 * 
	 * @param templateName
	 * @return content which is the output of the jsp page as a String
	 */
	public static String read( String templateName )
	{
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter stringWriter = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(stringWriter);
            }

            @Override
            public String toString() {
                return stringWriter.toString();
            }
        };
        
        responseWrapper.setContentType("text/html; charset=utf-8");
        
        try { request.getRequestDispatcher(templateName).include(request, responseWrapper); } 
        catch (ServletException e) { Reporter.error(e); } 
        catch (IOException e) {	Reporter.error(e); }
        
        String content = responseWrapper.toString();
//      response.getWriter().write(content);
        
		return content;
	}
	
}
