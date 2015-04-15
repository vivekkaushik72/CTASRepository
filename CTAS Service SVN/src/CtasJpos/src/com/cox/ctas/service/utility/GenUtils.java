/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;

import com.cox.ctas.service.tenderauth.Card;
import com.cox.ctas.service.tenderauth.CoxTenderAuthConstantsIfc;

/**
 * class contains utility methods used by CoxTenderAuth 
 * 
 * @author 
 *
 */
public class GenUtils {
	
	private static String VISA_PATTERN = "^4[0-9]{6,}$";
	private static String MASTERCARD_PATTERN = "^5[1-5][0-9]{5,}$";
	private static String AMEX_PATTERN = "^3[47][0-9]{5,}$";
	private static String DISCOVER_PATTERN = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
	
	private static Pattern visaPattern;
	private static Pattern mastercardPattern;
	private static Pattern amexPattern;
	private static Pattern discoverPattern;
	
	private static Matcher matcher;
	
	private static final String CARD = "card";
	private static final String IS_ALLOWED = "isAllowed";
	private static final String CARD_NAME = "name";
	private static final String CARD_TYPE = "type";
	private static final String CARD_TYPE_CODE = "cardTypeCode";
	private static final String RULE_LENGTH = "ruleLength";
	private static final String RULE_MASK = "ruleMask";
	private static final String RULE_BIN_RANGE = "ruleBinRange";
	private static final String CARD_REGEX_PATTERN = "cardRegexPattern";
	
	
	/**
	 * clears the contents of a StringBuffer. Used by the CoxTenderAuth class
	 * and retained here.
	 * 
	 * @param data	the buffer that should be cleared
	 */
	public static void flushStringBuffer(StringBuffer data) {
		if (data == null) {
			return;
		}
		for (int i = 0; i < data.length(); ++i) {
			data.setCharAt(i, ' ');
		}
		data = null;
	}
	
	/**
	 * converts a Throwable object to a String
	 * @param t		the throwable to be converted
	 * @return		the String representation of the Throwable
	 */
	public static String throwableToString(Throwable t) {
		ByteArrayOutputStream traceOut = new ByteArrayOutputStream();
		PrintWriter traceWriter = new PrintWriter(traceOut, true);
		t.printStackTrace(traceWriter);
		return traceOut.toString();
	}
	
	/**
	 * checks if String is empty
	 * 
	 * @param str	the String to be tested
	 * @return		a boolean true if String is null or length zero
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}
	
	
	/**
	 * evaluates card type using regex
	 * 
	 * @param cardPrefix 	the card number
	 * @return				the String representation of card type or null
	 */
	public static String getCardType (final String cardPrefix) {
		
		String cardType = null;
		
		visaPattern = Pattern.compile(VISA_PATTERN);
		mastercardPattern = Pattern.compile(MASTERCARD_PATTERN);
		amexPattern = Pattern.compile(AMEX_PATTERN);
		discoverPattern = Pattern.compile(DISCOVER_PATTERN);
		
		matcher = visaPattern.matcher(cardPrefix);
		
		if (matcher.matches()) {
			return cardType = CoxTenderAuthConstantsIfc.VISA_CARD;
		}
		
		matcher = mastercardPattern.matcher(cardPrefix);
		
		if (matcher.matches()) {
			cardType = CoxTenderAuthConstantsIfc.MASTER_CARD;
		}
		
		matcher = amexPattern.matcher(cardPrefix);
		
		if (matcher.matches()) {
			cardType = CoxTenderAuthConstantsIfc.AMERICAN_EXPRESS_CARD;
		}
		
		matcher = discoverPattern.matcher(cardPrefix);
		
		if (matcher.matches()) {
			cardType = CoxTenderAuthConstantsIfc.DISCOVER_CARD;
		}
		
		return cardType;
	}
	
	public List<Card> getCardRules (String configFilePath) {
		
		List<Card> cardRules = new ArrayList<Card>();
		int counter = 0;
		int[] temp = null;
		
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(configFilePath);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			Card card = null;
			
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					if (startElement.getName().getLocalPart() == CARD) {
						card = new Card();
						
						Iterator<Attribute> attributes = startElement.getAttributes();
						
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(IS_ALLOWED) &&
									attribute.getValue().equalsIgnoreCase("True")) {
								card.setAllowed(true);
							} else {
								card.setAllowed(false);
							}
							
							if (attribute.getName().toString().equals(CARD_NAME)) {
								card.setName(attribute.getValue());
							} 
							
							if (attribute.getName().toString().equals(CARD_TYPE)) {
								card.setTypeCode(attribute.getValue());
							} 
							
							if (attribute.getName().toString().equals(CARD_TYPE_CODE)) {
								card.setTypeCode(attribute.getValue());
							} 
							
							if (attribute.getName().toString().equals(CARD_REGEX_PATTERN)) {
								card.setCardRegexPattern(attribute.getValue());
							} 
						}
					}
					
					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart().equals(RULE_LENGTH)) {
							event = eventReader.nextEvent();
							temp[counter] = Integer.valueOf(event.asCharacters().getData());
							counter+=1;
							card.setRuleLength(temp);
						}
					}
				}
				
				
			}
			
			
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		
		
		return cardRules;
		
	}

}
