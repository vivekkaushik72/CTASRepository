/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice.formatter;

import com.cox.ctas.service.webservice.AuthResponse;
import com.cox.ctas.service.webservice.CTASResponse;

/**
 *  Response formatter class converts Service Response objects to POS Response objects.
 *  
 * @author 
 *
 */
public class ResponseFormatter {
	
	/**
	 * converts Service Response objects to POS Response objects
	 * 
	 * @param ar 	the Service Response object
	 * @return		the POS Response object 
	 */
	public CTASResponse translateResponse(AuthResponse ar){
		CTASResponse response = new CTASResponse();
		
		response.setApprovalCode(ar.getApprovalCode());
		response.setAuditTraceNumber(ar.getAuditTraceNumber());
		response.setAuthorizationResponseCode(ar.getAuthorizationResponseCode());
		response.setFinancialNetworkStatus(ar.getFinancialNetworkStatus());
		response.setReferenceCode(ar.getReferenceCode());
		response.setRetrievalReferenceNumber(ar.getRetrievalReferenceNumber());
		response.setSettlementData(ar.getSettlementData());
		response.setAbaNumber(ar.getAbaNumber());
		response.setAccountNumber(ar.getAccountNumber());
		response.setMaskedAccountNumber(ar.getMaskedAccountNumber());
		response.setAccountAPR(ar.getAccountAPR());
		response.setAccountAPRType(ar.getAccountAPRType());
		response.setAccountDataSource(ar.getAccountDataSource());
		response.setAdditionalAmount(ar.getAdditionalAmount());
		response.setAlternateAmount(ar.getAlternateAmount());
		response.setAuthorizationCode(ar.getAuthorizationCode());
		response.setAuthorizationMethod(ar.getAuthorizationMethod());
		response.setAuthorizatonResponseCode(ar.getAuthorizatonResponseCode());
		response.setAuthorizationSource(ar.getAuthorizationSource());
		response.setAuthorizationTransactionID(ar.getAuthorizationTransactionID());
		response.setBalanceDue(ar.getBalanceDue());
		response.setBaseAmount(ar.getBaseAmount());
		response.setConversionCode(ar.getConversionCode());
		response.setCvmResults(ar.getCvmResults());
		response.setEntryMethod(ar.getEntryMethod());				
		response.setFloorLimit(ar.getFloorLimit());					
		response.setGiftCardAccountType(ar.getGiftCardAccountType());
		response.setHostReference(ar.getHostReference());
		response.setIccDetails(ar.getIccDetails());
		response.setApplicationLabel(ar.getApplicationLabel());
		response.setMerchantId(ar.getMerchantId());
		response.setEpiryDate(ar.getEpiryDate());
		response.setStartDate(ar.getStartDate());
		response.setAuthorizationRequestCryptogram(ar.getAuthorizationRequestCryptogram());
		response.setApplicationInterchangeProfile(ar.getApplicationInterchangeProfile());
		response.setApplicationTransactionCounter(ar.getApplicationTransactionCounter());
		response.setUnpredictableNumber(ar.getUnpredictableNumber());
		response.setTerminalVerificationResult(ar.getTerminalVerificationResult());
		response.setCryptogramTransactionType(ar.getCryptogramTransactionType());
		response.setCryptogramInforamtionType(ar.getCryptogramInforamtionType());
		response.setApplicationResponseCryptogram(ar.getApplicationResponseCryptogram());
		response.setPosEntryMode1(ar.getPosEntryMode1());
		response.setPosEntryMode2(ar.getPosEntryMode2());
		response.setApplicationUsageControl(ar.getApplicationUsageControl());
		response.setApplicationVersionNumber(ar.getApplicationVersionNumber());
		response.setTerminalApplicationNumber(ar.getTerminalApplicationNumber());
		response.setTransactionStatusInformation(ar.getTransactionStatusInformation());
		response.setTerminalType(ar.getTerminalType());
		response.setTerminalCapabilities(ar.getTerminalCapabilities());
		response.setIssuerActionCodesOnline(ar.getIssuerActionCodesOnline());
		response.setIssuerActionCodesDenial(ar.getIssuerActionCodesDenial());
		response.setIssuerActionCodesDefault(ar.getIssuerActionCodesDefault());
		response.setIssuerApplicationData(ar.getIssuerApplicationData());
		response.setAuthorisationResponseCode(ar.getAuthorisationResponseCode());
		response.setTerminalCountryCode(ar.getTerminalCountryCode());
		response.setTerminalCurrencyNumber(ar.getTerminalCurrencyNumber());
		response.setJournalKey(ar.getJournalKey());
		response.setLocalDate(ar.getLocalDate());
		response.setLocalTime(ar.getLocalTime());
		response.setMicrData(ar.getMicrData());
		response.setMinPaymentDue(ar.getMinPaymentDue());			
		response.setPaymentServiceIndicator(ar.getPaymentServiceIndicator());
		response.setPersonalID(ar.getPersonalID());
		response.setPersonalIDAuthority(ar.getPersonalIDAuthority());
		response.setPersonalIDEntryMethod(ar.getPersonalIDEntryMethod());
		response.setPersonalIDTrack1Data(ar.getPersonalIDTrack1Data());
		response.setPersonalIDTrack2Data(ar.getPersonalIDTrack2Data());
		response.setPersonalIDType(ar.getPersonalIDType());
		response.setPhoneNumber(ar.getPhoneNumber());
		response.setPrepaidRemainingBalance(ar.getPrepaidRemainingBalance());
		response.setPromotionAPR(ar.getPromotionAPR());
		response.setPromotionAPRType(ar.getPromotionAPRType());
		response.setPromotionDescription(ar.getPromotionDescription());
		response.setPromotionDuration(ar.getPromotionDuration());
		response.setSignatureRequired(ar.getSignatureRequired());
		response.setSignature(ar.getSignature());
		response.setTenderSequenceNumber(ar.getTenderSequenceNumber());
		response.setTenderSubType(ar.getTenderSubType());
		response.setTenderType(ar.getTenderType());
		response.setAccountNumberToken(ar.getAccountNumberToken());
		response.setTraceNumber(ar.getTraceNumber());
		response.setValidationCode(ar.getValidationCode());
		response.setResponseMessage(ar.getResponseMessage());
		
		return response;
	}
}
