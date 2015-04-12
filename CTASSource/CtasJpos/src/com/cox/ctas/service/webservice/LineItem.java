/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

/**
 *  Represents a line item for display object 
 * 
 * @author 
 *
 */
public class LineItem {
	
	/**holds the value for the item description*/
	private String description = "";
	/**holds the value for the item quantity*/
	private int quantity = 0;
	/**holds the value for taxable item indicator*/
	private String taxable = "";
	/**holds the value for the item price*/
	private Double price = 0.0;
	
	
	/**
	 * gets the value stored in the description attribute
	 * 
	 * @return	the description value
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * sets the value in the description attribute
	 * 
	 * @param description	the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * gets the value stored in the taxable attribute
	 * 
	 * @return	the taxable value
	 */
	public String getTaxable() {
		return taxable;
	}
	
	/**
	 * sets the value in the taxable attribute
	 * 
	 * @param taxable	the taxable to set
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	/**
	 * gets the value stored in the price attribute
	 * 
	 * @return	the price value
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * sets the value in the price attribute
	 * 
	 * @param price	the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	} 
}
