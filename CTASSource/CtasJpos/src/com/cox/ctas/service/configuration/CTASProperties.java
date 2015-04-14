/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.configuration;

/**
 * Stores the properties for CTAS Service
 *
 */
public class CTASProperties {
	
	/**the value for the register number*/
	public String registerNumber;
	/**the value for the store number*/
	public String storeNumber;
	/**the value for the jServer port*/
	public int jserverPort;
	/**path for serial number csv file*/
	public String csvFilePath;
	/**path for paymenTech URL file*/
	public String paymentechURL;
	
	
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
	 * sets the value in the storeNumber attribute
	 * 
	 * @param storeNumber	the store number to be assigned
	 */
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	
	/**
	 * gets the value stored in the jServerPort attribute
	 * 
	 * @return	the value of the store server port
	 */
	public int getJserverPort() {
		return jserverPort;
	}
	
	/**
	 * sets the value in the jServerPort attribute
	 * 
	 * @param jServerPort	the server port number to be assigned
	 */
	public void setJserverPort(int jserverPort) {
		this.jserverPort = jserverPort;
	}

	/**
	 * gets the value stored in the csvFilePath attribute
	 * 
	 * @return	the value of csvFilePath
	 */
	public String getCsvFilePath() {
		return csvFilePath;
	}

	/**
	 * sets the value in the csvFilePath attribute
	 * 
	 * @param csvFilePath	the csvFilePath to set
	 */
	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	/**
	 * gets the value stored in the paymentechURL attribute
	 * 
	 * @return	the paymentechURL value
	 */ 
	public String getPaymentechURL() {
		return paymentechURL;
	}

	/**
	 * sets the value in the paymentechURL attribute
	 * 
	 * @param paymentechURL	the paymentechURL to set
	 */
	public void setPaymentechURL(String paymentechURL) {
		this.paymentechURL = paymentechURL;
	}
	
}
