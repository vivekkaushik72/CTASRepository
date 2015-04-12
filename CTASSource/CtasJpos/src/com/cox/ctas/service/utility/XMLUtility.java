/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Utility class for reading XML files 
 *
 */
public class XMLUtility {

	/**logger for the class*/
	private static final Logger logger = Logger.getLogger(XMLUtility.class);
	/** instance of document builder class*/
	private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();


	/**
	 * reads the XML file and return the Document
	 * 
	 * @throws InvalidXmlException 
	 */
	public static Document getDocument(String xmlFile)
			throws InvalidXmlException {
		logger.info("getDocument: starting document retrieval and parsing");

		InputSource source = getDocumentSource(xmlFile);

		DocumentBuilder builder = null;
		try {
			builder = documentBuilderFactory.newDocumentBuilder();
			builder.setErrorHandler(new ParseErrorHandler());
		} catch (ParserConfigurationException e) {
			throw new InvalidXmlException(
					"The XML parser is incorrectly configured.", e);
		}

		Document doc = null;
		try {
			doc = builder.parse(xmlFile);
			String temp = doc.getElementsByTagName("registerId").toString();
			System.out.println(temp);
		} catch (SAXException e) {
			throw new InvalidXmlException("Error parsing XML.", e);
		} catch (IOException e) {
			throw new InvalidXmlException("Error read XML stream.", e);
		}
		
		logger.info("getDocument: returning document");

		return doc;
	}

	/**
	 * reads the XML file and return the InputSource object
	 * @param file	the file to evaluate
	 * 
	 * @throws InvalidXmlException
	 */
	public static InputSource getDocumentSource(String file)
			throws InvalidXmlException {
		
		logger.info("getDocumentSource: reading xml file");
		
		FileInputStream fiStream = null;
		try {
			fiStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			if (fiStream == null) {
				throw new InvalidXmlException("File not found.", e);
			}
		}
		InputSource source = new InputSource(fiStream);
		
		logger.info("getDocumentSource: returing input source");
		return source;
	}

	/**
	 * returns the XML elements
	 * @param xmlDoc	the document to parse	
	 * @return			an array of XML elements
	 */
	public static Element[] getChildElements(Document parent) {
		logger.info("getChildElements: retrieving XML elements");
		
		NodeList tempList = parent.getChildNodes();
		Element[] temp = new Element[tempList.getLength()];
		int elemCount = 0;

		for (int i = 0; i < tempList.getLength(); i++) {
			Node node = tempList.item(i);
			if (node.getNodeType() == 1) {
				temp[elemCount] = ((Element) node);
				elemCount++;
			}
		}

		Element[] elements = new Element[elemCount];
		System.arraycopy(temp, 0, elements, 0, elemCount);

		logger.info("getChildElements: returning XML elements");
		return elements;
	}

	/**
	 * internal class used to parse errors
	 * 
	 * @author
	 *
	 */
	public static class ParseErrorHandler implements ErrorHandler {
		public void fatalError(SAXParseException e) throws SAXException {
			throw new SAXException("FATAL ERROR: " + e.getMessage()
					+ " at line number: " + e.getLineNumber());
		}

		public void error(SAXParseException e) throws SAXException {
			throw new SAXException("ERROR: " + e.getMessage()
					+ " at line number: " + e.getLineNumber());
		}

		public void warning(SAXParseException e) throws SAXException {
			throw new SAXException("WARNING: " + e.getMessage()
					+ " at line number: " + e.getLineNumber());
		}
	}

}
