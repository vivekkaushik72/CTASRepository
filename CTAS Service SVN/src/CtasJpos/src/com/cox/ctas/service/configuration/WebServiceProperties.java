/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.configuration;

/**
 * Stores the properties for CTAS Service
 *
 * @author
 */
public enum WebServiceProperties {
	
	/**an instance of this class*/
	INSTANCE;
	
	/**holds the value for the register number*/
	private String registerNumber;
	/**holds the value for the store number*/
	private String storeNumber;
	
	
	/**
	 * gets the value stored in the registerNumber attribute
	 * 
	 * @return	the value of the register number
	 */
	public String getRegisterNumber() {
		return registerNumber;
	}
	
	/**
	 * sets the value in the registerNumber attribute
	 * 
	 * @param registerNumber	the register number to be assigned
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	/**
	 * gets the value stored in the storeNumber attribute
	 * 
	 * @return	the value of the store number
	 */
	public String getStoreNumber() {
		return storeNumber;
	}
	
	/**
	 * sets the value in the registerNumber attribute
	 * 
	 * @param registerNumber	the register number to be assigned
	 */
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
}
