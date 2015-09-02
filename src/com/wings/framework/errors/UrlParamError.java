package com.wings.framework.errors;

public class UrlParamError extends Exception {

	private static final long serialVersionUID = 5345583695936382303L;
	
	public UrlParamError( String message ) {
		super(message);
	}

}
