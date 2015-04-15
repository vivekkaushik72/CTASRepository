/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

/**
 * POS exposed class for the parameters required for a 
 * line item display request. 
 * 
 * @author 
 *
 */
public class LineItemRequest {
	
	/**holds the a collection of LineItem objects*/
	private LineItem [] items = null;
	/**holds the value for the total item quantity*/
	private int quantity = 0;
	/**holds the value for the total item discount*/
	private double discount = 0.0;
	/**holds the value for the total item tax*/
	private double tax = 0.0;
	/**holds the value for the total items*/
	private double total = 0.0;
	
	
	/**
	 * gets the value stored in the items array
	 * 
	 * @return	the items value
	 */
	public LineItem[] getItems() {
		return items;
	}
	
	/**
	 * sets the value in the items array
	 * 
	 * @param items	the items to set
	 */
	public void setItems(LineItem[] items) {
		this.items = items;
	}
	
	/**
	 * gets the value stored in the quantity attribute
	 * 
	 * @return	the quantity value
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * sets the value in the quantity attribute
	 * 
	 * @param quantity	the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * gets the value stored in the discount attribute
	 * 
	 * @return	the discount value
	 */
	public double getDiscount() {
		return discount;
	}
	
	/**
	 * sets the value in the discount attribute
	 * 
	 * @param discount	the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 * gets the value stored in the tax attribute
	 * 
	 * @return	the tax value
	 */
	public double getTax() {
		return tax;
	}
	
	/**
	 * sets the value in the tax attribute
	 * 
	 * @param tax	the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	
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
