/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.webcontent.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.cox.ctas.service.webservice.CTASRequest;
import com.cox.ctas.service.webservice.CTASResponse;
import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.SigCapRequest;
import com.cox.ctas.service.webservice.SigCapResponse;
import com.cox.ctas.service.webservice.TenderRequest;
import com.cox.ctas.webcontent.webservice.exception.ServiceException;

/**
 * Web Service Interface class that defines the exposed device action methods to 
 * POS and associates those methods with methods within CTAS that will execute
 * when these exposed methods are called by POS. 
 * 
 * @author 
 *
 */

@WebService(name = "CTASMessageHandler", targetNamespace = "http://auth.txn.com/")
public interface CTASMessageHandler {

	
	@WebMethod(operationName = "getAuthorization", action = "urn:GetAuthorization")
	public CTASResponse getAuthorization(
			@WebParam(name = "CTASRequest") CTASRequest txnRequestData) 
					throws ServiceException;
	
	@WebMethod(operationName = "getSignatureData", action = "urn:GetSignatureData")
	public SigCapResponse getSignatureData(
			@WebParam(name = "SigCapRequest") SigCapRequest txnRequestData) 
					throws ServiceException;
	
	@WebMethod(operationName = "showAuthorization", action = "urn:ShowAuthorization")
	public void showAuthorization() 
					throws ServiceException;
	
	@WebMethod(operationName = "clearScreen", action = "urn:ClearScreen")
	public void clearScreen() 
					throws ServiceException;
	
	@WebMethod(operationName = "showItems", action = "urn:ShowItems")
	public void showItems(
			@WebParam(name = "LineItemRequest") LineItemRequest txnRequestData) 
					throws ServiceException;
	
	@WebMethod(operationName = "showLogo", action = "urn:ShowLogo")
	public void showLogo() 
					throws ServiceException;
	
	@WebMethod(operationName = "showMSRPrompt", action = "urn:ShowMSRPrompt")
	public void showMSRPrompt() 
					throws ServiceException;
	
	@WebMethod(operationName = "showSignature", action = "urn:ShowSignature")
	public void showSignature() 
					throws ServiceException;
	
	@WebMethod(operationName = "showTender", action = "urn:ShowTender")
	public void showTender(
			@WebParam(name = "TenderRequest") TenderRequest txnRequestData) 
					throws ServiceException;
	
	@WebMethod(operationName = "showThanks", action = "urn:ShowThanks")
	public void showThanks() 
					throws ServiceException;
}