/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.utility;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * class contains utility methods used by CoxTenderAuth 
 * 
 * @author 
 *
 */
public class GenUtils {
	
	/**
	 * clears the contents of a StringBuffer. Used by the CoxTenderAuth class
	 * and retained here.
	 * 
	 * @param data	the buffer that should be cleared
	 */
	public static void flushStringBuffer(StringBuffer data) {
		if (data == null) {
			return;
		}
		for (int i = 0; i < data.length(); ++i) {
			data.setCharAt(i, ' ');
		}
		data = null;
	}
	
	/**
	 * converts a Throwable object to a String
	 * @param t		the throwable to be converted
	 * @return		the String representation of the Throwable
	 */
	public static String throwableToString(Throwable t) {
		ByteArrayOutputStream traceOut = new ByteArrayOutputStream();
		PrintWriter traceWriter = new PrintWriter(traceOut, true);
		t.printStackTrace(traceWriter);
		return traceOut.toString();
	}
	
	/**
	 * checks if String is empty
	 * 
	 * @param str	the String to be tested
	 * @return		a boolean true if String is null or length zero
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}
}
