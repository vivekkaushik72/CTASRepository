/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.authorization;

import org.apache.log4j.Logger;

import com.cox.ctas.service.device.CTASDeviceActionService;
import com.cox.ctas.service.tenderauth.CoxTenderAuth;
import com.cox.ctas.service.webservice.AuthRequest;
import com.cox.ctas.service.webservice.AuthResponse;
import com.cox.ctas.service.webservice.CTASRequest;
import com.cox.ctas.service.webservice.CTASResponse;
import com.cox.ctas.service.webservice.SigCapRequest;
import com.cox.ctas.service.webservice.SigCapResponse;
import com.cox.ctas.service.webservice.formatter.RequestFormatter;
import com.cox.ctas.service.webservice.formatter.ResponseFormatter;

/**
 * Contains payment authorization methods exposed to POS via the CTAS
 * web service. Cash, credit card, MO, Travelers Checks, and Check tenders 
 * will utilize these methods to acquire authorization from the payment 
 * gateway and signature capture data from the CPOI device.
 * 
 * @author corp/mamcfarl
 *
 */

public class CTASAuthorizationService implements CTASAuthorizationServiceIfc {
	
	/**logger for the class*/
	private static Logger logger = Logger.getLogger(CTASAuthorizationService.class);
	
	/** holds an instance of CoxTenderAuth*/
	private CoxTenderAuth tenderAuthService;
	/** instance of request formatter class that handles conversion of POS request objects
	 * payment gateway request objects*/
	private RequestFormatter reqFormatter = new RequestFormatter();
	/** instance of response formatter class that handles conversion of payment gateway response 
	 * objects to POS response objects*/
	private ResponseFormatter respFormatter = new ResponseFormatter();
	/**holds an instance of the deviceService used to control the CPOI device*/
	private CTASDeviceActionService deviceService;
	
	
	/**
	 * Builds an AuthRequest object from a CTAS Request object. Adds
	 * a layer of abstraction between objects received from POS 
	 * and objects and sent to the payment gateway
	 * 
	 * @param request
	 * @return
	 */
	public AuthRequest buildAuthRequest(CTASRequest request) {
		logger.info("Building AuthRequest for authorization");
		
		AuthRequest authReq = new AuthRequest();
		if(null != request)
		{
			authReq = reqFormatter.translateRequest(request);
			logger.info("Successfully created AuthRequest for authorization");
		}
		else{
			logger.info("Invalid request received, auth request cannot be created");
		}
		return authReq;
	}
	
	/**
	 * Builds an CTASResponse object from an AuthResponse object. Adds
	 * a layer of abstraction between objects received from the payment gateway
	 * and objects returned to POS
	 * 
	 * @param response	response received from payment gateway
	 * @return			POS response object
	 */
	public CTASResponse buildAuthResponse(AuthResponse response) {
		logger.info("Building CTASResponse for POS");
		CTASResponse authResp = new CTASResponse();
		if(null != response)
		{
			authResp = respFormatter.translateResponse(response);
			logger.info("Successfully created CTASResponse for POS");
		}
		else{
			logger.info("Invalid Response received, CTASResponse cannot be created");
		}
		return authResp;
	}
	
	/**
	 * requests an authorization from the payment gateway
	 * 
	 * @param  authReq  the authorization request received from POS
	 * @return 			the formatted response received from the payment gateway
	 */
	public CTASResponse getAuthorization(CTASRequest authReq) {
		logger.info("getAuthorization: Request received: Transaction Type: " + 
		authReq.getTransactionType());
		
		tenderAuthService = new CoxTenderAuth();
		AuthResponse authResp = tenderAuthService.requestTenderAuthorization(buildAuthRequest(authReq));
		CTASResponse authResponse = respFormatter.translateResponse(authResp);
		
		logger.info("getAuthorization: Response received from Payment Gateway: " + 
				authResponse.getAuthorizationResponseCode());
		return authResponse;
	}
	
	/**
	 * requests a signature capture from the deviceService
	 * 
	 * @param request 	the signature capture request received from POS
	 * @return 			the formatted signature cap response from the deviceService
	 */
	public SigCapResponse getSignatureData(SigCapRequest request) {
		logger.info("getSignatureData: Request received: Request type: " 
		+ request.getRequestType());
		
		String sig = "";
		deviceService = CTASDeviceActionService.getInstance();
		sig = deviceService.getSignature();
		//check the value of the signature being returned to POS
		System.out.println("Base64.encodedBase64:   " + sig);

		//create POS response object and set values
		SigCapResponse sigCapResponse = new SigCapResponse();
		sigCapResponse.setSignature(sig);
		sigCapResponse.setStatus(true);
		
		logger.info("getSignatureData: Signature returned to POS: " + sig);
		return sigCapResponse;
	}
}
