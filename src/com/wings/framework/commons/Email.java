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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

import com.wings.framework.errors.ViewError;
import com.wings.framework.global.View;
import com.wings.framework.internal.Reporter;
import com.wings.framework.main.Config;

/**
 * <p>This is a utility class that provides simple static methods
 * for setting up an email and sending it.</p>
 * <p>This class uses the JavaMail API for sending text or html emails.</p>
 * 
 * <h5>Example : </h5>
 * <br>
 * <code>
 * Email.to("reciever's email address");<br>
 * Email.subject("Email Subject");<br>
 * Email.type("text/plain" or "text/html; charset=utf-8");<br>
 * Email.body( String );<br>
 * Email.send();<br>
 * </code>
 * <br>
 * <br>
 * <h5>Note : </h5>
 * The current supported authentication method is TLS.
 * <br>
 * <br>
 * @version 1.0
 * @author Anjul Garg
 *
 */
public class Email 
{
	/* Host Connection Variables */
	private static String host = Config.MAIL_HOST;
	private static String port = Config.MAIL_PORT;
	private static boolean auth = Config.MAIL_AUTH;
	private static boolean tls = true;

	/* Username and Passowrd */
	private static String username = Config.MAIL_USERNAME;
	private static String password = Config.MAIL_PASSWORD;

	/* Email Variables -- Can not be NULL */
	private static String from = Config.MAIL_USERNAME;
	private static String to = new String();
	private static String subject = new String();
	private static String body = new String();
	private static String type = new String();

	private Email() {}

	/**
	 * Set the hostname.
	 * 
	 * @param host
	 */
	public static void host(String host) 
	{
		Email.host = host;
	}

	/**
	 * Set the port address.
	 * 
	 * @param port
	 */
	public static void port(String port) 
	{
		Email.port = port;
	}

	/**
	 * Sets if authentication true or false.
	 * 
	 * @param auth
	 */
	public static void auth(boolean auth) 
	{
		Email.auth = auth;
	}

	/**
	 * Set TLS Authentication true or false.
	 * 
	 * @param tls
	 */
	public static void tls(boolean tls) 
	{
		Email.tls = tls;
	}

	/**
	 * Set email id/ username.
	 * 
	 * @param username
	 */
	public static void username(String username) 
	{
		Email.username = username;
	}

	/**
	 * Set password.
	 * 
	 * @param password
	 */
	public static void password(String password) 
	{
		Email.password = password;
	}

	/**
	 * Sets the string 'from' that will be shown tht reciever.
	 * 
	 * @param from
	 */
	public static void from(String from) 
	{
		Email.from = from;
	}

	/**
	 * Sets the email address of the reciver.
	 * 
	 * @param to
	 */
	public static void to(String to) 
	{
		Email.to = to;
	}

	/**
	 * Sets the Subject of the Email.
	 * 
	 * @param subject
	 */
	public static void subject(String subject) 
	{
		Email.subject = subject;
	}

	/**
	 * Sets the Body of the Email. Can be text or html bases.
	 * Make sure that you define the Email.type() correctly.
	 * 
	 * @param body
	 */
	public static void body(String body) 
	{
		Email.body = body;
	}
	
	/**
	 * Set type of content in the email.
	 * "text/plain" for simple text or
	 * "text/html; charset=utf-8" for html emails.
	 * @param type
	 */
	public static void type(String type)
	{
		Email.type = type;
	}

	/**
	 * Sends the Text or Html Email after all the required params
	 * have been set.
	 * 
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	public static void send()
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", tls);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message message = new MimeMessage(session);
		
		try
		{
		message.setFrom(new InternetAddress(username, from));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(body, type);
		
		Transport.send(message);
		}
		catch( Exception e ) { Reporter.error(e); }
		
		System.out.println("Mail Sent Succesfully !");
	}
	
	/**
	 * Renders a JSP Email template and returns its output
	 * as string. You can use this string as email body for Html
	 * email templates.
	 * 
	 * 
	 * @param templateName
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String template( String templateName )
	{
		return View.read( templateName );
	}
	
	
	/**
	 * Checks the existance of a View file inside the WebContent Dir.
	 * Throws an error if the file is not found.
	 * 
	 * @param viewName
	 * @throws ViewError
	 */
	static void checkExistance( String viewName ) throws ViewError
	{
		File file = new File( com.wings.framework.internal.File.CONTEXT.getRealPath( viewName ));
		if( !file.exists() )
			throw new ViewError("View not found : " + viewName);
	}
	
}
