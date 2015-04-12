/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.vf.mx.utility;

import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Logger;

import jpos.JposException;
import jpos.config.JposEntry;
import jpos.config.JposEntryRegistry;
import jpos.loader.JposServiceLoader;

/**
 *  Utility class for the device. 
 *  
 * @author 
 *
 */
public class CoxVfMxUtilities {

	/**logger for the class*/
	static Logger logger = Logger.getLogger(CoxVfMxUtilities.class);
	/** the last JposEntry retrieved */
	static JposEntry localJposEntry;

	
	/**
	 * default constructor
	 */
	public CoxVfMxUtilities() {
		if (logger.isDebugEnabled()) {
			logger.debug("CoxVfMxUtilities started");
		}
	}

	/**
	 * retrieves jpos service entries from the jposxml.cfg file
	 * 
	 * @param servicePosInCfgFile	the position of the service in the jposxml.cfg file
	 * @return						jposEntry represented at that position
	 */
	public static JposEntry loadServiceConfig (final int servicePosInCfgFile) {

		localJposEntry = null;

		JposServiceLoader.getManager().getProperties().loadJposProperties();
		JposEntryRegistry localJposEntryRegistry = JposServiceLoader.getManager().getEntryRegistry();

		@SuppressWarnings("rawtypes")
		Enumeration localEnumeration = localJposEntryRegistry.getEntries();
		Vector <JposEntry> localEnumVctr = new Vector <JposEntry>();

		while (localEnumeration.hasMoreElements()){
			localJposEntry = (JposEntry) localEnumeration.nextElement();
			localEnumVctr.add(localJposEntry);
		}

		if (localEnumVctr.size() > 0 && localEnumVctr.elementAt(servicePosInCfgFile) != null)
			localJposEntry = localEnumVctr.elementAt(servicePosInCfgFile);

		return localJposEntry;
	}


	/**
	 * prints formatted JposExceptions to console 
	 * 
	 * @param paramJposException	JposException to print	
	 * @param className				name of the class where the exception occurred
	 */
	public static void handleJposException(JposException paramJposException, String className) {
		System.out.println(className + "JposException with message = "
				+ paramJposException.getMessage() + " and errorCode = "
				+ paramJposException.getErrorCode());
	}
}
