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

import java.util.ArrayList;
import java.util.List;

public class Timer 
{

	private static String processName = new String(); 
	private static long startTime;
	private static long endTime;
	private static long totalTime;
	private static List<Long> timeList = new ArrayList<Long>();
	private static long averageTime;
	
	public static void start( String processName )
	{
		Timer.processName = processName;
		startTime = System.currentTimeMillis();
	}
	
	public static void stop()
	{
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		timeList.add(totalTime);
			
		for (Long item : timeList) 
		{
		averageTime += item;	
		}
		
		averageTime = averageTime / timeList.size();
		
		System.out.println( processName + " time : " + totalTime + " ms" );
		System.out.println( "Average Time : " + averageTime + " ms");
		
		averageTime = 0;
	}
	
}
