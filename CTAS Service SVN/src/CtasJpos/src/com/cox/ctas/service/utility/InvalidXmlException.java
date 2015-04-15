/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.utility;


/**
 * Exception class for errors encountered for configuration and 
 * properties xml file loading.
 * 
 * @author
 *
 */
public class InvalidXmlException extends Exception {

	/**required revision number supplied by source control system*/
	private static final long serialVersionUID = 7945500401345014181L;

	/**
	 * one parameter constructor used to generate an exception of InvalidXml type
	 * 
	 * @param msg	the exception message
	 */
	public InvalidXmlException(String msg) {
		super(msg);
	}

	/**
	 * two parameter constructor used to generate an exception of InvalidXml type
	 * 
	 * @param msg	the exception message
	 * @param t		the Throwable
	 */
	public InvalidXmlException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * returns the String representation of the exception
	 */
	public String toString() {
		return super.toString();
	}
}
