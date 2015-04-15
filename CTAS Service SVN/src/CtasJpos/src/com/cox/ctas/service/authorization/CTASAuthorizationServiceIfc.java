/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.authorization;

import com.cox.ctas.service.webservice.CTASRequest;
import com.cox.ctas.service.webservice.CTASResponse;
import com.cox.ctas.service.webservice.SigCapRequest;
import com.cox.ctas.service.webservice.SigCapResponse;

/**
 * Interface class for the CTASAuthorizationService. These methods are 
 * required and must be implemented as part of the service.
 */
public interface CTASAuthorizationServiceIfc {

	public CTASResponse getAuthorization(CTASRequest request);
	public SigCapResponse getSignatureData(SigCapRequest request);
}
