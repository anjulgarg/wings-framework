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

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wings.framework.errors.FileError;
import com.wings.framework.internal.File;
import com.wings.framework.internal.Reporter;

/**
 * <h5>Config.class</h5>
 * This class is used for configuring this framework. It reads the wings-config.json
 * file stored inside the WEB-INF folder. 
 * This class in run only once when the application starts and hence does not slow it down.
 * 
 * It is important to initialize all the config variables or it might cause some features to
 * malfunction.
 * <br><br>
 * @version 1.0
 * @author Anjul Garg <anjulgarg@live.com>
 */
public final class Config 
{
	/* APP Vars */
	public static boolean DEBUG_MODE;

	public static boolean CSRF_CHECK;

	public static String ROUTES_CLASS = new String();

	public static String VIEW_DIR = new String();

	public static List<String> SKIP_URL = new ArrayList<String>();

	/* Mail Vars */
	
	public static String MAIL_HOST = new String();
	
	public static String MAIL_PORT = new String();
	
	public static boolean MAIL_AUTH = true;
	
	public static String MAIL_USERNAME = new String();
	
	public static String MAIL_PASSWORD = new String();
	
	/* Database Vars */
	
	public static String DB_TYPE = new String();
	
	public static String DB_NAME = new String();
	
	public static String DB_HOST = new String();
	
	public static String DB_USERNAME = new String();
	
	public static String DB_PASSWORD = new String();
	
	
	public Config()
	{
		String jsonString = new String();
		
		try { jsonString = File.read("/WEB-INF/wings-config.json"); }
		catch (FileError e) { Reporter.error(e); }

		JsonElement jsonElement = new JsonParser().parse(jsonString);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		

		/* APP Vars Initialization */
		
		DEBUG_MODE = Boolean.valueOf( jsonObject.get("DEBUG_MODE").toString().replace("\"", "") );

		CSRF_CHECK = Boolean.valueOf( jsonObject.get("CSRF_CHECK").toString().replace("\"", "") );

		ROUTES_CLASS = jsonObject.get("ROUTES_CLASS").toString().replace("\"", "");

		JsonArray jsonArray = jsonObject.getAsJsonArray("SKIP_URL");
		for( int i=0; i<jsonArray.size(); i++ )
			SKIP_URL.add( jsonArray.get(i).toString().replace("\"", "") );
		
		
		/* Mail Vars Initialization */
		
		MAIL_HOST = jsonObject.get("MAIL_HOST").toString().replace("\"", "");
		
		MAIL_PORT = jsonObject.get("MAIL_PORT").toString().replace("\"", "");
		
		MAIL_AUTH = Boolean.valueOf( jsonObject.get("MAIL_AUTH").toString().replace("\"", "") );
		
		MAIL_USERNAME = jsonObject.get("MAIL_USERNAME").toString().replace("\"", "");
		
		MAIL_PASSWORD = jsonObject.get("MAIL_PASSWORD").toString().replace("\"", "");
		
		
		/* Database Vars Initialization */
		
		DB_HOST = jsonObject.get("DB_HOST").toString().replace("\"", "");
		
		DB_NAME = jsonObject.get("DB_NAME").toString().replace("\"", "");
		
		DB_TYPE = jsonObject.get("DB_TYPE").toString().replace("\"", "");
		
		DB_USERNAME = jsonObject.get("DB_USERNAME").toString().replace("\"", "");
		
		DB_PASSWORD = jsonObject.get("DB_PASSWORD").toString().replace("\"", "");
	
	}

}
