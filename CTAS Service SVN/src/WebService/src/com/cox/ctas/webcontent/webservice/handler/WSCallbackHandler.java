/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.webcontent.webservice.handler;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

/**
 * CXF CallbackHandler that allows for embedded WSDL security parameters.
 * Username and password security params needed for someone to 
 * sign-on to the service.
 * 
 * @author 
 *
 */
public class WSCallbackHandler implements CallbackHandler {

	/**
	 * default method called by CXF to check security information
	 * 
	 * @param 	an array of Callback objects that contain security params 
	 */
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		if (!(callbacks[0] instanceof WSPasswordCallback)) {
			throw new IOException("Unsupported Authentication Type");
		}

		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

		if (pc.getIdentifier().equals("admin")) {
			// Sets the password on the callback. This will be compared to the
			// password which was sent from the Client.
			pc.setPassword("admin");
		}
	}
}
