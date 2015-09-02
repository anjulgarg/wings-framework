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
package com.wings.framework.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.wings.framework.main.Config;

/**
 * <code>public class Validator</code>
 * <br><br><br>
 * This class contains commonly required 
 * methods for String validation and sanitization.
 * 
 * @version 1.0
 * @author Anjul Garg <anjulgarg@live.com>
 *
 */
public class Validator {


	/**
	 * Validate that this email address conforms to the syntax rules of RFC 822. 
	 * 
	 * 
	 * @param email
	 * @param charLimit
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isEmail( String email, int charLimit )
	{
		if( email.length() > charLimit ) return false;

		try
		{
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			return true;
		}catch( AddressException e )
		{ 
			if( Config.DEBUG_MODE ) e.printStackTrace();
			return false; 
		}

	}



	/**
	 * Validate that this string conforms to the supplied parameters.
	 * String pattern needs to be a valid regex pattern conforming to java regex.
	 * 
	 * 
	 * @param userName
	 * @param charLimit
	 * @param pattern
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean matches( String string, int charLimit, String pattern )
	{
		if( string.length() > charLimit ) return false;

		return Pattern.matches( pattern, string );
	}



	/**
	 * <code>public static boolean isAlphaNumeric(String input)</code>
	 * <br><br>
	 * Validates if a string is strictly Alpha-Numeric.
	 * 
	 * 
	 * @param input
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isAlphaNumeric(String input) {
		String pattern = "^[a-zA-Z0-9]*$";
		if(input.matches(pattern)){
			return true;
		}
		return false;   
	}



	/**
	 * <code>public static boolean isAscii(String input)</code>
	 * <br><br>
	 * Validates if a string consists of all ASCII characters.
	 * 
	 * 
	 * @param input
	 * @return
	 * 
	 * 
	 */
	public static boolean isAscii(String input) {
		if( input.matches("\\A\\p{ASCII}*\\z") )
			return true;
		else
			return false;
	}



	/**
	 * Checks if a string is boolean. 
	 *
	 * 
	 * @param input
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isBoolean(String input) {
		if( (input.equalsIgnoreCase("true")) || (input.equalsIgnoreCase("false")) )
			return true;
		else
			return false;
	}



	/**
	 * Checks if a String is valid date, depending upon
	 * the dateFormat supplied.
	 * 
	 * 
	 * @param dateToValidate
	 * @param dateFromat
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isDate(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			return false;
		}

		return true;
	}


	
	/**
	 * Check if a string is hexadecmial.
	 * 
	 * 
	 * @param input
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isHexaDecimal(String input) {
		if(input.matches("-?[0-9a-fA-F]+"))
			return true;
		else
			return false;
	}

	
	
	/**
	 * Checks if a string is null.
	 * 
	 * 
	 * @param input
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isNull(String input) {
		if(input.equals("") || input.equals(null))
			return true;
		else
			return false;
	}
	
	
	
	/**
	 * Checks if a string is numeric.
	 * 
	 * 
	 * @param input
	 * @return true or false
	 * 
	 * 
	 */
	public static boolean isNumeric(String input) {
		String regex = "[0-9]+";
		if(input.matches(regex))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Escapes the some special characters from an 
	 * html text to prevent XSS attacks caused by unsafe user input.
	 * 
	 * It replaces the six significant characters as recommended by OWASP.
	 * & --> &amp;
	 * < --> &lt;
 	 * > --> &gt;
 	 * " --> &quot;
 	 * ' --> &#x27;   
 	 * / --> &#x2F;
 	 * 
	 * 
	 * @param html
	 * @return String html
	 */
	public static String escapeHtml( String html )
	{
		/* It replaces the six significant characters as recommended by OWASP. */
		html = html
				.replace( "&", "&amp" ).replace( "\"", "&quot" )
				.replace( "'", "&#x27" ).replace( "<", "&lt" )
				.replace( ">", "&gt" ).replace( "/", "&#x2F" );
		
		return html;
	}
	
	
	
	
}
