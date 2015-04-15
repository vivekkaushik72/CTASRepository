/************************************************************
 *          Copyright(c) 2015 Cox Communications. 
 *                   All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.tenderauth;

import java.util.Arrays;
import java.util.List;

/**
 * represents a card tender type. class used for implementing card
 * type rules
 * 
 * @author corp\mamcfarl
 *
 */
public class Card {

	/**flag to indicate if the card is allowed*/
	private boolean isAllowed;
	/**name of the card Visa, MasterCard, etc.*/
	private String name;
	/**type of card credit, debit, gift, etc.*/
	private String type;
	/**code associated with the card type*/
	private String typeCode;
	/**length rules*/
	private int [] ruleLength;
	/**mask rules*/
	private int [] ruleMask;
	/**bin range rules*/
	private BinRange [] ruleBinRange;
	/**regex pattern that can be used to evaluate the card*/
	private String cardRegexPattern;
	
	/**
	 * default constuctor sets default values
	 */
	public Card () {
		setAllowed(false);
		this.name = "";
		this.type = "";
		this.typeCode = "";
		this.ruleLength = null;
		this.ruleMask = null;
		this.ruleBinRange = null;
		this.cardRegexPattern = "";
	}
	
	/***
	 * seven parameter constructor. allows setting of all class parameters.
	 * 
	 * @param isAllowed			flag indicates if card is allowed
	 * @param name				name of the card Visa, MasterCard, etc.
	 * @param type				type of card credit, debit, gift, etc.	
	 * @param typeCode			code associated with the card type
	 * @param ruleLength		length rules
	 * @param ruleMask			mask rules
	 * @param ruleBinRange		bin range rules
	 * @param cardRegexPattern	regex pattern that can be used to evaluate the card
	 */
	public Card (boolean isAllowed, String name, String type, String typeCode, int[] ruleLength, 
			int [] ruleMask, BinRange [] ruleBinRange, String cardRegexPattern) {
		this.setAllowed(isAllowed);
		this.name = name;
		this.type = type;
		this.typeCode = typeCode;
		this.ruleLength = ruleLength;
		this.ruleMask = ruleMask;
		this.ruleBinRange = ruleBinRange;
		this.cardRegexPattern = cardRegexPattern;
	}
	
	/**
	 * gets the value stored in the isAllowed attribute
	 * 
	 * @return	the isAllowed value
	 */
	public boolean isAllowed() {
		return isAllowed;
	}

	/**
	 * sets the value in the isAllowed attribute
	 * 
	 * @param isAllowed	the isAllowed to set
	 */
	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	/**
	 * gets the value stored in the name attribute
	 * 
	 * @return	the name value
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the value in the name attribute
	 * 
	 * @param name	the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gets the value stored in the type attribute
	 * 
	 * @return	the type value
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * sets the value in the type attribute
	 * 
	 * @param type	the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * gets the value stored in the typeCode attribute
	 * 
	 * @return	the typeCode value
	 */
	public String getTypeCode() {
		return typeCode;
	}
	
	/**
	 * sets the value in the typeCode attribute
	 * 
	 * @param typeCode	the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	 * gets the value stored in the ruleLength attribute
	 * 
	 * @return	the ruleLength value
	 */
	public int[] getRuleLength() {
		return ruleLength;
	}
	
	/**
	 * sets the value in the ruleLength attribute
	 * 
	 * @param (Array) objects	the ruleLength to set
	 */
	public void setRuleLength(int[] temp) {
		this.ruleLength = temp;
	}
	
	/**
	 * gets the value stored in the ruleMask attribute
	 * 
	 * @return	the ruleMask value
	 */
	public int[] getRuleMask() {
		return ruleMask;
	}
	
	/**
	 * sets the value in the ruleMask attribute
	 * 
	 * @param ruleMask	the ruleMask to set
	 */
	public void setRuleMask(int[] ruleMask) {
		this.ruleMask = ruleMask;
	}
	
	/**
	 * gets the value stored in the ruleBinRange attribute
	 * 
	 * @return	the ruleBinRange value
	 */
	public BinRange[] getRuleBinRange() {
		return ruleBinRange;
	}
	
	/**
	 * sets the value in the ruleBinRange attribute
	 * 
	 * @param ruleBinRange	the ruleBinRange to set
	 */
	public void setRuleBinRange(BinRange[] ruleBinRange) {
		this.ruleBinRange = ruleBinRange;
	}

	/**
	 * gets the value stored in the cardRegexPattern attribute
	 * 
	 * @return	the cardRegexPattern value
	 */
	public String getCardRegexPattern() {
		return cardRegexPattern;
	}

	/**
	 * sets the value in the cardRegexPattern attribute
	 * 
	 * @param cardRegexPattern	the cardRegexPattern to set
	 */
	public void setCardRegexPattern(String cardRegexPattern) {
		this.cardRegexPattern = cardRegexPattern;
	}
	
	@Override
	public String toString() {
		return "Card [isAllowed=" + isAllowed + ", name=" + name + ", type="
				+ type + ", typeCode=" + typeCode + ", ruleLength="
				+ ruleLength.toString() + ", ruleMask="
				+ Arrays.toString(ruleMask) + ", ruleBinRange="
				+ Arrays.toString(ruleBinRange) + ", cardRegexPattern="
				+ cardRegexPattern + "]";
	}
}
