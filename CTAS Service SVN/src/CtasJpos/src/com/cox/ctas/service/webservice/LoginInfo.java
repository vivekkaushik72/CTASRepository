/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POS exposed class for the parameters required for a 
 * login request. 
 * 
 * @author 
 *
 */

@XmlRootElement(name = "LoginInfo")
public class LoginInfo {
	
	/**holds the value for the userName*/
	private String userName;
	/**holds the value for the password*/
	private String password;
	/**holds the value for the ipaddress*/
	private String ipaddress;
	
	
	/**
	 * gets the value stored in the userName attribute
	 * 
	 * @return	the userName value 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * sets the value in the userName attribute
	 * 
	 * @param userName	the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * gets the value stored in the password attribute
	 * 
	 * @return	the password value 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * sets the value in the password attribute
	 * 
	 * @param password	the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * gets the value stored in the ipaddress attribute
	 * 
	 * @return	the ipaddress value 
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * sets the value in the ipaddress attribute
	 * 
	 * @param ipaddress	the ipaddress to set
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
