package com.wings.framework.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import com.wings.framework.errors.FileError;

public class File 
{

	public static ServletContext CONTEXT;

	public File( ServletContext context ) 
	{
		CONTEXT = context;
	}

	/**
	 * This method can be used to read a file in the WEB-INF root folder of this web
	 * application. It uses the ServletContext object for accessing these files.
	 * 
	 * @param path as String for example - "/WEB-INF/config.xml"
	 * @return content as String
	 * @throws FileError if the File is not Found or can't be read.
	 */
	public static String read( String path ) throws FileError
	{
		InputStream inputStream = CONTEXT.getResourceAsStream(path);
		InputStreamReader inputStreamReader = null;

		try { inputStreamReader = new InputStreamReader(inputStream); }
		catch(Exception e) 
		{ 
			if(inputStreamReader == null) 
				throw new FileError("Unable to read " + path + ". Make sure the file exists.");
		}
		
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = new String();

		try { line = bufferedReader.readLine(); } 
		catch (IOException e) { Reporter.error(e); }

		String content = new String();

		while( line != null ) 
		{	
			content += line + System.lineSeparator();
			try { line = bufferedReader.readLine(); } 
			catch (IOException e) { Reporter.error(e);  }
		}

		try { bufferedReader.close(); } 
		catch (IOException e) {	Reporter.error(e); }

		return content;
	}

	
	public static boolean exists( String path )
	{
		java.io.File file = new java.io.File( CONTEXT.getRealPath( path ));
		
		if( file.exists() ) return true;
		else return false;
	}
	
	
	public static void create( String path )
	{
		
		
	}
	
	
}
