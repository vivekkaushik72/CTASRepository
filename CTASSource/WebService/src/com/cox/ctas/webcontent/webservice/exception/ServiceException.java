/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.webcontent.webservice.exception;

/**
 * custom service exception class. captures the error code and
 * error message associated with the exception. 
 * 
 * @author 
 *
 */
public class ServiceException extends Exception {

	/**required serial version UID */
	private static final long serialVersionUID = 1L;
	/**the error code that caused the exception*/
	private int errorCode;
	/**the associated error message*/
	private String errorMsg;
	
	
	/**
	 * two parameter constructor that accepts an error code and 
	 * an error message. 
	 * 
	 * @param errorCode	the error code of the error that caused the exception
	 * @param errorMsg	the error message associated with the error that 
	 * 					caused the exception
	 */
	public ServiceException(int errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	/**
	 * gets the value stored in the errorCode attribute
	 * 
	 * @return	the errorCode value
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * sets the value in the errorCode attribute
	 * 
	 * @param errorCode	the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * gets the value stored in the errorMsg attribute
	 * 
	 * @return	the errorMsg value
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * sets the value in the errorMsg attribute
	 * 
	 * @param errorMsg	the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
