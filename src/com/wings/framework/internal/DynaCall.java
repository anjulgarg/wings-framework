package com.wings.framework.internal;

import java.lang.reflect.Method;

public class DynaCall {

	private DynaCall(){}
	
	public static void method( String className, String methodName )
	{
		try
		{
		Class<?>[] noparams = {};
		Class<?> dynaClass = Class.forName( className );
		Object object = dynaClass.newInstance();
		Method method = dynaClass.getDeclaredMethod( methodName, noparams );
		method.invoke(object);
		}
		catch( Exception e ){ Reporter.error(e); }
	}
	
	public static void newInstance( String className )
	{
		Class<?> dynaClass;
		try{
			dynaClass = Class.forName( className );
			dynaClass.newInstance();
		}catch(Exception e){ Reporter.error(e); }
	}

}
