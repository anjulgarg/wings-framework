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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wings.framework.internal.Reporter;

/**
 * <code>public class DB</code>
 * <br><br>
 * This class contains static methods for interacting
 * with the database.
 * 
 * <h5>Note : </h5>
 * Always call <code>DB.close()</code> method
 * after using a method that returns a result set. 
 * As it is required to close the Result Set and prevent 
 * memory leaks.
 * <br><br>
 * @version 1.0
 * @author Anjul Garg <anjulgarg@live.com>
 *
 */
public class DB 
{
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;

	private static int rowsAffected;

	/**
	 * Default Constructor. This class can not be instantiated.
	 */
	private DB() {}

	/**
	 * Initializes the connection variable.
	 */
	private static void init()
	{
		connection = Connector.open();
	}

	/**
	 * Creates a Database if it does not exist.
	 * 
	 * @param sql as String
	 */
	public static void createDB( String sql )
	{
		init();

		try { statement = connection.createStatement(); } 
		catch (SQLException e) { Reporter.error(e); }

		try { statement.executeUpdate( sql ); } 
		catch (SQLException e) { /* ignored */ }

		Connector.close( resultSet, preparedStatement, connection );
	}


	/**
	 * Executes a generic statement on the database.
	 * 
	 * <h5>Note :</h5>
	 * It is not a prepared statement.
	 * 
	 * @param sql as String
	 */
	public static void statement( String sql )
	{
		init();

		try { statement = connection.createStatement(); } 
		catch (SQLException e) { Reporter.error(e); }

		try { statement.executeUpdate( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		Connector.close( resultSet, preparedStatement, connection );
	}
	
	
	/**
	 * Prepares an SQL statement that does not return a ResultSet.
	 * 
	 * @param sql statement as string.
	 */
	public static void prepareStatement( String sql )
	{
		init();

		try { preparedStatement = connection.prepareStatement( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

	}
	
	
	/**
	 * Supplies parameters to the preparedStatement.
	 * They are added in their respective order.
	 * 
	 * @param values as an Object array.
	 */
	public static void params( Object[] values )
	{
		for( int i = 1; i <= values.length; i++ )
		{
			try { preparedStatement.setObject( i, values[i-1] ); } 
			catch (SQLException e) { Reporter.error(e); }
		}
		
	}
	
	
	/**
	 * Runs the prepared statement using SQL executeUpdate().
	 * 
	 * <h5>Note :</h5>
	 * It should be followed by DB.close();
	 * 
	 * @return int rowsAffected that is the number of rows affected by this statement.
	 */
	public static int executeStatement()
	{
		try { rowsAffected = preparedStatement.executeUpdate(); } 
		catch (SQLException e) { Reporter.error(e); }
		
		return rowsAffected;
	}

	
	/**
	 * Creates a Database Table if it does not exist.
	 * 
	 * @param sql as String
	 */
	public static void createTable( String sql )
	{
		init();

		try { statement = connection.createStatement(); } 
		catch (SQLException e) { Reporter.error(e); }

		try { statement.executeUpdate( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		Connector.close( resultSet, preparedStatement, connection );
	}


	/**
	 * Performs an INSERT query on the database. 
	 * 
	 * @param sql as String. It is the insert query.
	 * @param values as Object array.
	 * @return int rowsAffected whose value is number of rows affected 
	 * or 0 when statement does not return anything.
	 */
	public static int insert( String sql, Object[] values )
	{
		init();

		try { preparedStatement = connection.prepareStatement( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		for( int i = 1; i <= values.length; i++ )
		{
			try { preparedStatement.setObject( i, values[i-1] ); } 
			catch (SQLException e) { Reporter.error(e); }
		}

		try { rowsAffected = preparedStatement.executeUpdate(); } 
		catch (SQLException e) { Reporter.error(e); }

		Connector.close( resultSet, preparedStatement, connection );

		return rowsAffected;
	}


	/**
	 * Performs an UPDATE statement on the database. 
	 * 
	 * @param sql as String. It is the insert query.
	 * @param values as Object array.
	 * @return int rowsAffected whose value is number of rows affected 
	 * or 0 when statement does not return anything.
	 */
	public static int update( String sql, Object[] values )
	{
		init();

		try { preparedStatement = connection.prepareStatement( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		for( int i = 1; i <= values.length; i++ )
		{
			try { preparedStatement.setObject( i, values[i-1] ); } 
			catch (SQLException e) { Reporter.error(e); }
		}

		try { rowsAffected = preparedStatement.executeUpdate(); } 
		catch (SQLException e) { Reporter.error(e); }

		Connector.close( resultSet, preparedStatement, connection );

		return rowsAffected;
	}


	/**
	 * Performs a DELETE SQL statement on the database. 
	 * 
	 * @param sql as String. It is the insert query.
	 * @param values as Object array.
	 * @return int rowsAffected whose value is number of rows affected 
	 * or 0 when statement does not return anything.
	 */
	public static int delete( String sql, Object[] values )
	{
		init();

		try { preparedStatement = connection.prepareStatement( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		for( int i = 1; i <= values.length; i++ )
		{
			try { preparedStatement.setObject( i, values[i-1] ); } 
			catch (SQLException e) { Reporter.error(e); }
		}

		try { rowsAffected = preparedStatement.executeUpdate(); } 
		catch (SQLException e) { Reporter.error(e); }

		Connector.close( resultSet, preparedStatement, connection );

		return rowsAffected;
	}

	/**
	 * Performs a SELECT SQL query on the Database.
	 * 
	 * @param sql as a String
	 * @param values as an Object[] array
	 * @return ResultSet
	 */
	public static ResultSet select( String sql, Object[] values )
	{
		init();

		try { preparedStatement = connection.prepareStatement( sql ); } 
		catch (SQLException e) { Reporter.error(e); }

		for( int i = 1; i <= values.length; i++ )
		{
			try { preparedStatement.setObject( i, values[i-1] ); } 
			catch (SQLException e) { Reporter.error(e); }
		}

		try { resultSet = preparedStatement.executeQuery(); } 
		catch (SQLException e) { Reporter.error(e); }

		return resultSet;
	}

	
	/**
	 * Closes the database connection.
	 * It is a good practice to close the database connection 
	 * when it is not in use.
	 */
	public static void close()
	{
		Connector.close( resultSet, preparedStatement, connection );
	}

}
