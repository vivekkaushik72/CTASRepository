/*package com.cox.ctas.vf.mx.adaptertests;

import java.math.BigDecimal;

import com.cox.ctas.service.authorization.CTASAuthorizationService;
import com.cox.ctas.service.tenderauth.CoxTenderAuthConstantsIfc;
import com.cox.ctas.service.webservice.AuthRequest;
import com.cox.ctas.service.webservice.AuthResponse;
import com.cox.ctas.service.webservice.CTASRequest;

public class PaymentTechConnectTest implements CoxTenderAuthConstantsIfc{

	public static void main(String[] args) {
		
		CTASRequest txnRequestData = new CTASRequest();
		txnRequestData.setAlternateAmount(10.00);
		txnRequestData.setBaseAmount(10.00);
		txnRequestData.setConversionCode(null);
		txnRequestData.setFloorLimit(5.00);
		txnRequestData.setPaymentAmount(new BigDecimal(10.00));
		txnRequestData.setRegisterId("129");
		txnRequestData.setRequestSubType(VISA_CARD);
		txnRequestData.setRequestTenderType("CREDIT");
		txnRequestData.setStoreId("451");
		txnRequestData.setTenderSequenceNumber("12345");
		txnRequestData.setUpdateBalance(false);
		
		//txnRequestData.setDataEntryMethod(ENTRY_METHOD_AUTO);
		//AuthRequest authRequest = new AuthRequest();

		System.out.println(txnRequestData);

		CTASAuthorizationService cTasAuth = new CTASAuthorizationService();
		AuthRequest request = cTasAuth.buildAuthRequest(txnRequestData);
		AuthResponse response = cTasAuth.authorize(request);

		System.out.println(response);
	}

}
*/