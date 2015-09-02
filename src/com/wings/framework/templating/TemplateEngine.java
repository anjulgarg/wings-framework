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
package com.wings.framework.templating;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wings.framework.internal.JSPReader;

public class TemplateEngine 
{

	static String CHILD_NAME = new String();
	static String CHILD_TEMPLATE = new String();
	
	static String PARENT_NAME = new String();
	static String PARENT_TEMPLATE = new String();
	
	private TemplateEngine() {}
	
	/**
	 * Processed a supplied template using the template builder.
	 * 
	 * @param templateName
	 * @return
	 */
	public static String process( String templateName )
	{		
		CHILD_NAME = templateName;
		CHILD_TEMPLATE = JSPReader.read( templateName );
		
		return builder();
		
	}
	
	/**
	 * Reads the template and fills in the sections and extension as defined on the page.
	 * <h4>Note :</h4>
	 * The child page can only extend one parent.
	 * THe child and parent page can not have multiple declarations for the same section.
	 * 
	 * @return
	 */
	static String builder()
	{
		Pattern pattern = Pattern.compile("\\@extends\\(\\s*?\"(.*?)\"\\s*?\\)");
		Matcher matcher = pattern.matcher( CHILD_TEMPLATE );
		
		if( matcher.find() )
		{
			PARENT_TEMPLATE = JSPReader.read( matcher.group(1) + ".jsp" );
			
			pattern = Pattern.compile("(?s)\\@section\\(\\s*?\"(.*?)\"\\s*?\\)(.*?)\\@endsection");
			matcher = pattern.matcher( CHILD_TEMPLATE );
			
			while( matcher.find() )
			{
				String replacement = matcher.group(2);
				String sectionName = matcher.group(1);
				
				Pattern pattern2 = Pattern.compile("(?s)\\@section\\(\\s*?\"" + sectionName + "\"\\s*?\\)(.*?)\\@endsection");
				Matcher matcher2 = pattern2.matcher( PARENT_TEMPLATE );
				
				while( matcher2.find() )
				{
					PARENT_TEMPLATE = PARENT_TEMPLATE.replace( matcher2.group(0), replacement );
				}
				
			}
			
			PARENT_TEMPLATE = PARENT_TEMPLATE.replaceAll("\\@section\\(\\s*?\".*?\"\\s*?\\)", "").replaceAll("\\@endsection", "");
			return PARENT_TEMPLATE;
		}
		
		CHILD_TEMPLATE = CHILD_TEMPLATE.replaceAll("\\@section\\(\\s*?\".*?\"\\s*?\\)", "").replaceAll("\\@endsection", "");
		return CHILD_TEMPLATE;
	}

}
