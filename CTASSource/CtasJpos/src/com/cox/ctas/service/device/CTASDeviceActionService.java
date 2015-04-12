/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.device;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.TenderRequest;
import com.cox.ctas.vf.mx.adapters.Mx8xxFormsAdapter;

/**
 * Contains device action methods exposed to POS via the CTAS
 * web service. POS will utilize these methods to perform actions
 * on the CPOI device. This class is a singleton.  
 * 
 * @author 
 *
 */
public class CTASDeviceActionService implements CTASDeviceActionServiceIfc {

	/**logger for the class*/
	private static Logger logger = Logger.getLogger(CTASDeviceActionService.class);
	/**constant for class name use for logging*/
	private static String SERVICE_TYPE = "CTASDeviceActionService";
	/**holds an instance of the deviceService used to control the CPOI device*/
	private static CTASDeviceActionService deviceConnection = null;
	/**holds the value of the credit card expiration date*/
	private String expDte;
	/**an instance of the controller for the device*/
	private Mx8xxFormsAdapter mxForms = new Mx8xxFormsAdapter();

	
	/**
	 * private default constructor
	 */
	private CTASDeviceActionService() {}

	/**
	 * getInstance singleton constructor
	 * 
	 * @return	the instance of the class
	 */
	public static CTASDeviceActionService getInstance() {
		logger.info("Instance of " + SERVICE_TYPE + " requested");
		if (deviceConnection == null) {
			deviceConnection = new CTASDeviceActionService();
		}
		return deviceConnection;
	}

	/**
	 * device command to show an authorization form.
	 * form displays "Authorizing..." 
	 */
	public void showAuthorization() {
		logger.info("showAuthorization: Received request to show form.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("AUTHMSG");
		mxForms.showForm("AUTHMSG");
		mxForms.close();
		
		logger.info("showAuthorization: form display successful");
	}

	/**
	 * device command to show a MSR prompt form.
	 * form displays "Swipe Card..."
	 */
	public void showMSRPrompt() {
		logger.info("showMSRPrompt: Received request to show form.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("MSRPRMPT");
		mxForms.showForm("MSRPRMPT");
		mxForms.close();
		
		logger.info("showMSRPrompt: form displayed successful");
	}

	/**
	 * device command to show a Thank You form.
	 * form displays "Thank You..."
	 */
	public void showThanks() {
		logger.info("showThanks: Received request to show form.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("THANKS");
		mxForms.showForm("THANKS");
		mxForms.close();
		
		logger.info("showThanks: form displayed successful");	
	}

	/**
	 * device command to show a Signature form.
	 * form displays "Please Sign...
	 */
	public void showSignature() {
		logger.info("showSignature: Received request to show form.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("SIGNATUR");
		mxForms.showForm("SIGNATUR");
		mxForms.close();
		
		logger.info("showSignature: form displayed successful");
	}

	/**
	 * device command to show a Logo Welcome form.
	 * form displays "Welcome...
	 */
	public void showLogo() {
		logger.info("showLogo: Received request to show form.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("WELCOME");
		mxForms.showForm("WELCOME");
		mxForms.close();
		
		logger.info("showLogo: form displayed successful");
	}

	/**
	 * device command to perform clear screen action.
	 * form displays blank screen.
	 */
	public void clearScreen() {
		logger.info("clearScreen: Received request to clear screen.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.clearScreen();
		mxForms.close();
		
		logger.info("clearScreen: screen cleared successful");
	}

	/**
	 * device command to show Line Items form and populate 
	 * line item data.
	 */
	public void showItems(LineItemRequest request) {
		logger.info("showItems: Received request to show line items.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("ITEMS");
		mxForms.addItems(request);
		mxForms.showForm("ITEMS");
		mxForms.close();
		
		logger.info("showItems: line items displayed successful");
		
	}

	/**
	 * device command to show Balance Due form and populate 
	 * balance due data.
	 */
	public void showTender(TenderRequest request) {
		logger.info("showTender: Received request to show balance due.");
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("TENDERS");
		mxForms.addTender(request);
		mxForms.showForm("TENDERS");
		mxForms.close();
		
		logger.info("showTender: balance due displayed successful");	
	}
	
	/**
	 * device command to show Signature form and get signature 
	 * data.
	 */
	public String getSignature() {
		logger.info("getSignature: Received request to show and acquire sigCap data");
		
		String sig = null;
		byte [] sigCapInBytes = null;
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("SIGNATUR");
		mxForms.showForm("SIGNATUR");
		mxForms.formSig();
		sigCapInBytes = mxForms.getRawData();
		sig = new String (Base64.encodeBase64(sigCapInBytes));
		mxForms.close();
		mxForms.release();
		
		logger.info("getSignature: sigCap data captured");
		return sig;
	}
	
	/**
	 * device command to show MSR prompt form and get MSR 
	 * data.
	 */
	public String getMSR() {
		logger.info("getMSR: Received request to show and acquire MSR data");
		
		expDte = null;
		String msr = null;
		byte[] cardNumberinBytes = null;
		
		mxForms.open();
		mxForms.claim();
		mxForms.deviceEnabled(true);
		mxForms.initForm("MSRPRMPT");
		mxForms.showForm("MSRPRMPT");
		mxForms.formMSR();
		cardNumberinBytes = mxForms.getTrack2Data();
		expDte = mxForms.getExpDate();
		msr = new String (Base64.encodeBase64(cardNumberinBytes));
		mxForms.close();
		
		logger.info("getMSR: MSR data captured");
		return msr;
	}
	
	/**
	 * gets the expiration date value held in expDate
	 * 
	 * @return	value of expDate
	 */
	public String getExpDate() {
		return expDte;
	}
}
