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
	
	/**holds the value for the register number*/
	public String registerNumber;
	/**holds the value for the store number*/
	public String storeNumber;
	/**holds the value for the server port*/
	public int jserverPort;
	/**holds the value for the CSV File path containing device IDs*/
	public String csvFilePath;
	
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
	 * @return the csvFilePath
	 */
	public String getCsvFilePath() {
		return csvFilePath;
	}

	/**
	 * @param csvFilePath the csvFilePath to set
	 */
	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
	
	
}
