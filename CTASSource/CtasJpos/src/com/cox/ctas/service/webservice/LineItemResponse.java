/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

/**
 * POS exposed class for the parameters required for a 
 * line item display response. 
 * 
 * @author 
 *
 */
public class LineItemResponse {
	
	/**holds the value for the isSuccess item display indicator*/
	private boolean isSuccess = false;

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
