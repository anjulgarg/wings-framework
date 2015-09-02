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
package com.wings.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wings.framework.internal.Reporter;
import com.wings.framework.main.Config;

class Connector
{

	/* DB Variables */
	private static String DB_TYPE = Config.DB_TYPE;
	private static String DB_NAME = Config.DB_NAME;
	private static String DB_HOST = Config.DB_HOST + "/";
	private static String DB_USERNAME = Config.DB_USERNAME;
	private static String DB_PASSWORD = Config.DB_PASSWORD;


	/* DB Specific Variables */
	private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://";


	/* Operational Variables */
	private static Connection connection;


	/**
	 * Default Constructor.
	 */
	private Connector() 
	{

	}


	/**
	 * Opens the connection to the database.
	 */
	public static Connection open()
	{
		switch ( DB_TYPE.toLowerCase() ) 
		{
		
		case "mysql":
			
			try { Class.forName( MYSQL_DRIVER_CLASS ); } 
			catch (ClassNotFoundException e) { Reporter.error(e); }
			
			try { connection = DriverManager.getConnection( MYSQL_URL + DB_HOST + DB_NAME, DB_USERNAME, DB_PASSWORD ); } 
			catch (SQLException e) { Reporter.error(e); }
			
			return connection;

		case "sqlite":

			break;

		case "postgresql":

			break;

		case "mongodb":

			break;

		default:
			break;
		}
		
		return connection;

	}
	
	
	/**
	 * Closes the connection with the database.
	 */
	public static void close( ResultSet resultSet, PreparedStatement preparedStatement, Connection connection )
	{
		try { resultSet.close(); } catch (Exception e) { /* ignored */ }
	    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
	    try { connection.close(); } catch (Exception e) { /* ignored */ }
	}

}
