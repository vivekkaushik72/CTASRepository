package com.cox.ctas.service.tenderauth;

import org.apache.log4j.Logger;

public class CTASTenderAuthorizationService {
	
	private String paymentechServiceURL = "";

	Logger logger = Logger.getLogger(CTASTenderAuthorizationService.class);

	public static CTASTenderAuthorizationService service = null;

	private CTASTenderAuthorizationService() {
	}

	
	public static CTASTenderAuthorizationService getInstance() {
		if (service == null) {
			service = new CTASTenderAuthorizationService();
		}
		return service;
	}

	// ----------------------------------------------------------------------
	/**
	 * Process a tender authorization request.
	 * 
	 * @param tenderAuthRequest
	 *            tender authorization data object
	 */
	// ----------------------------------------------------------------------
	public synchronized String requestAuthorization(String request) {
		return "";
	}

	// -------------------------------------------------------------------------
	public String getResponseCode() {
		return "";
	}

	/**
	 * @return String
	 */
	public String getPaymentechServiceURL() {
		return paymentechServiceURL;
	}

	/**
	 * @param paymentechServiceURL
	 */
	public void setPaymentechServiceURL(String paymentechServiceURL) {
		this.paymentechServiceURL = paymentechServiceURL;
	}

	/**
	 * Send request to ADS for all tender
	 * 
	 * @param request
	 * @return TenderAuthResponse
	 */
	public String requestTenderAuthorization(String request) {
		return "";
	}

}
