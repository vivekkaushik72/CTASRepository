/**          Copyright(c) 2015 Cox Communications. 
 *                   All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.tenderauth;

/**
 * Used for implementing bin range card tender rules as defined 
 * in coxcardrules.xml
 * 
 * @author corp\mamcfarl
 *
 */
public class BinRange {

	/**the bin range minimum value */
	private int minimumValue;
	/**the bin range maximum value */
	private int maximumValue;
	
	
	/**
	 * default constructor sets range to zero
	 */
	public BinRange() {
		minimumValue = 0;
		maximumValue = 0;
	}
	
	/**
	 * two parameter constructor accepts bin ranges.
	 * 
	 * @param min	minimum bin range value
	 * @param max	maximum bin range value
	 */
	public BinRange(int min, int max) {
		this.minimumValue = min;
		this.maximumValue = max;
	}
	
	/**
	 * gets the value stored in the minimumValue attribute
	 * 
	 * @return	the minimumValue value
	 */
	public int getMinimumValue() {
		return minimumValue;
	}
	
	/**
	 * sets the value in the minimumValue attribute
	 * 
	 * @param minimumValue	the minimumValue to set
	 */
	public void setMinimumValue(int minimumValue) {
		this.minimumValue = minimumValue;
	}
	
	/**
	 * gets the value stored in the maximumValue attribute
	 * 
	 * @return	the maximumValue value
	 */
	public int getMaximumValue() {
		return maximumValue;
	}
	
	/**
	 * sets the value in the maximumValue attribute
	 * 
	 * @param maximumValue	the maximumValue to set
	 */
	public void setMaximumValue(int maximumValue) {
		this.maximumValue = maximumValue;
	}

	@Override
	public String toString() {
		return "BinRange [minimumValue=" + minimumValue + ", maximumValue="
				+ maximumValue + "]";
	}
}
