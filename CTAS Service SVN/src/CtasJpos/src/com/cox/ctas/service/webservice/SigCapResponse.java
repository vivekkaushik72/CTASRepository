/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POS exposed class for the parameters required for a 
 * signature capture response. 
 * 
 * @author 
 *
 */

@XmlRootElement(name = "SigCapResponse")
public class SigCapResponse {

	/**holds the value for the request status indicator*/
	private boolean status;
	/**holds the value for the Signature*/
	private String Signature = "";
	
	
	/**
	 * gets the value stored in the status attribute
	 * 
	 * @return	the status value 
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * sets the value in the status attribute
	 * 
	 * @param status	the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * gets the value stored in the Signature attribute
	 * 
	 * @return	the Signature value 
	 */
	public String getSignature() {
		return Signature;
	}
	
	/**
	 * sets the value in the Signature attribute
	 * 
	 * @param Signature	the Signature to set
	 */
	public void setSignature(String signature) {
		Signature = signature;
	}
}
