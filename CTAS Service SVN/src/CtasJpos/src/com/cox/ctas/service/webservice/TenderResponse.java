/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

/**
 * POS exposed class for the parameters required for a 
 * show balance due response. 
 * 
 * @author 
 *
 */
public class TenderResponse {
	
	/**holds the value for the isSuccess indicator*/
	private boolean isSuccess;

	/**
	 * gets the value stored in the isSuccess attribute
	 * 
	 * @return	the isSuccess value 
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * sets the value in the isSuccess attribute
	 * 
	 * @param isSuccess	the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
