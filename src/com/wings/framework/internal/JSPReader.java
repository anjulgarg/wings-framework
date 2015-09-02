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
