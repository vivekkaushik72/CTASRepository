/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.webcontent.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.feature.Features;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.cox.ctas.service.authorization.CTASAuthorizationService;
import com.cox.ctas.service.authorization.CTASAuthorizationServiceIfc;
import com.cox.ctas.service.device.CTASDeviceActionService;
import com.cox.ctas.service.webservice.CTASRequest;
import com.cox.ctas.service.webservice.CTASResponse;

import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.LoginInfo;
import com.cox.ctas.service.webservice.SigCapRequest;
import com.cox.ctas.service.webservice.SigCapResponse;
import com.cox.ctas.service.webservice.TenderRequest;
import com.cox.ctas.webcontent.webservice.exception.ServiceException;
import com.verifone.httpserver.servlet.http.HttpServletRequest;

/**
 * Web Service Implementation class that implements the exposed device action methods to 
 * POS as defined in the Interface class. 
 * 
 * @author 
 *
 */

@WebService(targetNamespace = "http://auth.txn.com/", endpointInterface = "com.cox.ctas.webcontent.webservice.CTASMessageHandler", portName = "CTASMessageHandlerImplPort", serviceName = "CTASMessageHandlerService")
@Features(features = "org.apache.cxf.feature.LoggingFeature")  
public class CTASMessageHandlerImpl implements CTASMessageHandler { 

	@Resource WebServiceContext jaxwsContext;
	@Resource MessageContext jaxrsContext; 

	/**instance of the AuthService that carries out Auth and SigCap*/
	private CTASAuthorizationServiceIfc authService;
	/**instance of the DeviceService that carries out shows*/
	private CTASDeviceActionService deviceService;


	/**
	 * method to display form, acquire credit card data, send request to
	 * payment gateway, and return response.
	 * 
	 * @param txnRequestData	the POS request with transaction data
	 * @return					the formatted POS response from the gateway
	 * @throws ServiceException	custom exception for service errors
	 */
	public CTASResponse getAuthorization(CTASRequest txnRequestData) throws ServiceException {

		authService = new CTASAuthorizationService();
		CTASResponse txnresp = authService.getAuthorization(txnRequestData);
		return txnresp;
	}

	/**
	 * method to display form, acquire the signature data, and return this data
	 * to POS.
	 *  
	 * @param txnRequestData	the POS request for signature capture
	 * @return					the POS response with signature capture data
	 * @throws ServiceException	custom exception for service errors
	 */
	public SigCapResponse getSignatureData(SigCapRequest txnRequestData) throws ServiceException {

		authService = new CTASAuthorizationService();
		SigCapResponse txnresp = authService.getSignatureData(txnRequestData);
		return txnresp;
	}
	
	/**
	 * method to display the Authorizing... form 
	 * 
	 * @throws ServiceException	custom exception for service errors
	 */		
	public void showAuthorization() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showAuthorization();
	}

	/**
	 * method to clear the device screen 
	 * 
	 * @throws ServiceException	custom exception for service errors
	 */
	public void clearScreen() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.clearScreen();
	}

	/**
	 * method to show the Items form, and display items on POS display
	 * on the device
	 * 
	 * @param txnRequestData	the POS request with line items to display
	 * @throws ServiceException	custom exception for service errors
	 */
	public void showItems(LineItemRequest txnRequestData) throws ServiceException {

		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showItems(txnRequestData);
	}
	
	/**
	 * method to show the Balance Due form 
	 * 
	 * @param txnRequestData	the POS request with the balance due information
	 * @throws ServiceException	custom exception for service errors
	 */
	public void showTender(TenderRequest txnRequestData) throws ServiceException {

		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showTender(txnRequestData);
	}

	/**
	 * method to display the Cox Welcome form
	 * 
	 * @throws ServiceException	custom exception for service errors
	 */
	public void showLogo() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showLogo();
	}

	/**
	 * method to show the MSR Prompt form
	 * 
	 * @throws ServiceException	custom exception for service errors
	 */
	public void showMSRPrompt() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showMSRPrompt();
	}
	
	/**
	 * method to show the Signature form
	 * 
	 * @throws ServiceException	custom exception for service errors
	 */
	public void showSignature() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showSignature();
	}			

	/**
	 * method to show the Thank You form
	 * 
	 * @throws ServiceException
	 */
	public void showThanks() throws ServiceException {
		deviceService = CTASDeviceActionService.getInstance();
		deviceService.showThanks();
	}

	
	/**
	 * captures and returns IP address of ???
	 * 
	 * @return	IP address
	 */
	private String getIpAddress() {
		String ipAddress = " unknown";
		HttpServletRequest request = null;

		if (jaxrsContext != null) {
			request = (HttpServletRequest) jaxrsContext
					.get(AbstractHTTPDestination.HTTP_REQUEST);
		} else if (jaxwsContext != null) {
			request = (HttpServletRequest) jaxwsContext.getMessageContext()
					.get(MessageContext.SERVLET_REQUEST);
		} else {
			// TODO can not get ip address
			// should we through and exception
		}

		if (request != null) {
			if (StringUtils.isNotBlank(request.getHeader("X-Forwarded-For"))) {
				ipAddress = request.getHeader("X-Forwarded-For");
				System.out.println("00IP ADDRESS: " + ipAddress);
			} else {
				ipAddress = request.getRemoteAddr();
				System.out.println("11IP ADDRESS: " + ipAddress);
			}
		}

		System.out.println("IP ADDRESS: " + ipAddress);
		return ipAddress;
	}
}
