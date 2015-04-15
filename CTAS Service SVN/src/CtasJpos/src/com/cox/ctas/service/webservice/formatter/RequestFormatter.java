/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice.formatter;

import com.cox.ctas.service.webservice.AuthRequest;
import com.cox.ctas.service.webservice.CTASRequest;

/**
 *  Request formatter class converts POS Request objects to Service Request objects.
 *  
 * @author 
 *
 */
public class RequestFormatter {
	
	/**
	 * converts POS CTASRequests to CTAS AuthRequests
	 * 
	 * @param request	the POS request
	 * @return			the CTAS Service Request object
	 */
	public AuthRequest translateRequest(CTASRequest request){
		
		/** instance of AuthRequest object used by the Service*/
		AuthRequest ar = new AuthRequest();
	
		ar.setWorkstationID(request.getWorkstationID());
//		ar.setTrainingMode(request.isTrainingMode());
//		ar.setStore(request.getStore());
		ar.setStoreID(request.getStoreID());
//		ar.setLocalizedLocationNames(request.getLocalizedLocationNames());
//		ar.setRegisterID(request.getRegisterID());
//		ar.setCity(request.getCity());
//		ar.setState(request.getState());
//		ar.setCountry(request.getCountry());
//		ar.setZipCode(request.getZipCode());
//		ar.setPostalCodeExtension(request.getPostalCodeExtension());
//		ar.setPhone(request.getPhone());
//		ar.setDistrictClassIdentifier(request.getDistrictClassIdentifier());
//		ar.setDistrictClassDescription(request.getDistrictClassDescription());
//		ar.setRegionClassIdentifier(request.getDistrictClassIdentifier());
//		ar.setRegionClassDescription(request.getRegionClassDescription());
//		ar.setGeoCode(request.getGeoCode());
		ar.setTransactionID(request.getTransactionID());
		ar.setAuthorizationTransactionType(request.getAuthorizationTransactionType());
//		ar.setEntryMethod(request.getEntryMethod());
//		ar.setOperatorID(request.getOperatorID());
//		ar.setPreferredLocale(request.getPreferredLocale());
//		ar.setRawResponse(request.getRawResponse());
//		ar.setRawRequest(request.getRawRequest());
//		ar.setRequestTime(request.getRequestTime());
//		ar.setSessionID(request.getSessionID());
//		ar.setTrackData(request.getTrackData());
		ar.setTransactionType(request.getTransactionType());
//		ar.setWorkstatonIpAddress(request.getWorkstatonIpAddress());
//		ar.setAbaNumber(request.getAbaNumber());
//		ar.setAlternateAmount(request.getAlternateAmount().toString());
		ar.setBaseAmount(request.getBaseAmount().toString());
//		ar.setConversionCode(request.getConversionCode());
//		ar.setFloorLimit(request.getFloorLimit().toString());
		ar.setPaymentAmount(request.getPaymentAmount().toString());
//		ar.setPersonalIDAuthority(request.getPersonalIDAuthority());
//		ar.setPersonalIDEntryMethod(request.getPersonalIDEntryMethod());
//		ar.setPersonalIDTrack1Data(request.getPersonalIDTrack1Data());
//		ar.setPersonalIDTrack2Data(request.getPersonalIDTrack2Data());
//		ar.setPersonalIDType(request.getPersonalIDType());
//		ar.setPosGFCardEntryRequired(request.isPosGFCardEntryRequired());
//		ar.setMicrData(request.getMicrData());
		ar.setRequestTenderType(request.getRequestTenderType());
//		ar.setRequestSubType(request.getRequestSubType());
//		ar.setTenderSequenceNumber(request.getTenderSequenceNumber());
//		ar.setUpdateBalance(request.isUpdateBalance());
//		
		return ar;
	}
}
