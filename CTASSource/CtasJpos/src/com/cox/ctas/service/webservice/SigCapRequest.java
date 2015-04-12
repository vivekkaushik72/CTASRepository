/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POS exposed class for the parameters required for a 
 * signature capture request. 
 * 
 * @author 
 *
 */
@XmlRootElement(name = "SigCapRequest")
public class SigCapRequest {
	
	/**holds the value for the request type*/
	private String requestType;

	/**
	 * gets the value stored in the requestType attribute
	 * 
	 * @return	the requestType value 
	 */
	public String getRequestType() {
		return requestType;
	}
	
	/**
	 * sets the value in the requestType attribute
	 * 
	 * @param requestType	the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
