/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

/**
 * POS exposed class for the parameters required for a 
 * show balance due request. 
 * 
 * @author 
 *
 */
public class TenderRequest {
	
	/**holds the value for the total balance due*/
	private double total = 0.0;

	/**
	 * gets the value stored in the total attribute
	 * 
	 * @return	the total value 
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * sets the value in the total attribute
	 * 
	 * @param total	the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
}
