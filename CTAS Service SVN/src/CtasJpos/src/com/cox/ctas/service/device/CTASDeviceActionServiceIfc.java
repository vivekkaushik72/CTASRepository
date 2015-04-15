/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.device;

import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.TenderRequest;

/**
 * Interface class for the CTASDeviceActionService. These methods are 
 * required and must be implemented as part of the service.
 */
public interface CTASDeviceActionServiceIfc {

	public void clearScreen();
	public void showAuthorization();
	public void showItems(LineItemRequest request);
    public void showLogo();
	public void showMSRPrompt();
	public void showSignature();
	public void showTender(TenderRequest request);
	public void showThanks();
	public byte[] getMSR();
	public String getSignature();
}
