package com.wings.framework.errors;

public class RoutingError extends Exception
{
	private static final long serialVersionUID = 295708113767240193L;

	public RoutingError( String message )
	{
		super( message );
	}
}
