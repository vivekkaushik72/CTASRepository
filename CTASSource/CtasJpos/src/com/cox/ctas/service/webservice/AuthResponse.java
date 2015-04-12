/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

import java.math.BigDecimal;

/**
 *  CTAS Service internal class for the parameters required for a 
 *  payment authorization response. Created to separate Service request/response
 *  from POS or Payment Gateway request/response. Mapped to corresponding POS/Payment
 *  Gateway request/response via formatters.
 *  
 * @author 
 *
 */

public class AuthResponse {

	/**holds the value for the approvalCode*/
	private String approvalCode="";
	/**holds the value for the auditTraceNumber*/
	private String auditTraceNumber="";
	/**holds the value for the authorizationResponseCode*/
	private String authorizationResponseCode="";
	/**holds the value for the financialNetworkStatus*/
	private String financialNetworkStatus="";
	/**holds the value for the referenceCode*/
	private String referenceCode="";
	/**holds the value for the retrievalReferenceNumber*/
	private String retrievalReferenceNumber="";
	/**holds the value for the settlementData*/
	private String settlementData="";
	/**holds the value for the abaNumber*/
	private String abaNumber="";
	/**holds the value for the accountNumber*/
	private String accountNumber="";
	/**holds the value for themaskedAccountNumber*/
	private String maskedAccountNumber="";
	/**holds the value for the accountAPR*/
	private String accountAPR="";
	/**holds the value for the accountAPRType*/
	private String accountAPRType="";
	/**holds the value for the accountDataSource*/
	private String accountDataSource="";
	/**holds the value for the additionalAmount*/
	private Double additionalAmount=0.0;
	/**holds the value for the alternateAmount*/
	private Double alternateAmount=0.0;	
	/**holds the value for the authorizationCode*/
	private String authorizationCode="";
	/**holds the value for the authorizationMethod*/
	private String authorizationMethod="";
	/**holds the value for the authorizatonResponseCode*/
	private String authorizatonResponseCode="";
	/**holds the value for theauthorizationSource*/
	private String authorizationSource="";
	/**holds the value for the authorizationTransactionID*/
	private String authorizationTransactionID="";
	/**holds the value for the balanceDue*/
	private BigDecimal balanceDue = new BigDecimal(0.0);
	/**holds the value for the baseAmount*/
	private Double baseAmount=0.0;	
	/**holds the value for the conversionCode*/
	private String conversionCode="";
	/**holds the value for the cvmResults*/
	private String cvmResults="";
	/**holds the value for the entryMethod*/
	private String entryMethod="";	
	/**holds the value for the floorLimit*/
	private Double floorLimit=0.0;	
	/**holds the value for the giftCardAccountType*/
	private String giftCardAccountType="";
	/**holds the value for the hostReference*/
	private String hostReference="";
	/**holds the value for the iccDetails*/
	private String iccDetails="";
	/**holds the value for the applicationLabel*/
	private String applicationLabel="";
	/**holds the value for the merchantId*/
	private String merchantId="";
	/**holds the value for the epiryDate*/
	private String epiryDate="";
	/**holds the value for the startDate*/
	private String startDate="";
	/**holds the value for the authorizationRequestCryptogram*/
	private String authorizationRequestCryptogram="";
	/**holds the value for the applicationInterchangeProfile*/
	private String applicationInterchangeProfile="";
	/**holds the value for the applicationTransactionCounter*/
	private String applicationTransactionCounter="";
	/**holds the value for the unpredictableNumber*/
	private String unpredictableNumber="";
	/**holds the value for the terminalVerificationResult*/
	private String terminalVerificationResult="";
	/**holds the value for the cryptogramTransactionType*/
	private String cryptogramTransactionType="";
	/**holds the value for the cryptogramInforamtionType*/
	private String cryptogramInforamtionType="";
	/**holds the value for the applicationResponseCryptogram*/
	private String applicationResponseCryptogram="";
	/**holds the value for the posEntryMode1*/
	private String posEntryMode1="";
	/**holds the value for the posEntryMode2*/
	private String posEntryMode2="";
	/**holds the value for the applicationUsageControl*/
	private String applicationUsageControl="";
	/**holds the value for the applicationVersionNumber*/
	private String applicationVersionNumber="";
	/**holds the value for the terminalApplicationNumber*/
	private String terminalApplicationNumber="";
	/**holds the value for the transactionStatusInformation*/
	private String transactionStatusInformation="";
	/**holds the value for the terminalType*/
	private String terminalType="";
	/**holds the value for the terminalCapabilities*/
	private String terminalCapabilities="";
	/**holds the value for the issuerActionCodesOnline*/
	private String issuerActionCodesOnline="";
	/**holds the value for the issuerActionCodesDenial*/
	private String issuerActionCodesDenial="";
	/**holds the value for the issuerActionCodesDefault*/
	private String issuerActionCodesDefault="";
	/**holds the value for the issuerApplicationData*/
	private String issuerApplicationData="";
	/**holds the value for the authorisationResponseCode*/
	private String authorisationResponseCode="";
	/**holds the value for the terminalCountryCode*/
	private String terminalCountryCode="";
	/**holds the value for the terminalCurrencyNumber*/
	private String terminalCurrencyNumber="";
	/**holds the value for the journalKey*/
	private String journalKey="";
	/**holds the value for the localDate*/
	private String localDate="";
	/**holds the value for the localTime*/
	private String localTime="";
	/**holds the value for the micrData*/
	private String micrData="";
	/**holds the value for the minPaymentDue*/
	private String minPaymentDue="";	
	/**holds the value for the paymentServiceIndicator*/
	private String paymentServiceIndicator="";
	/**holds the value for the personalID*/
	private String personalID="";
	/**holds the value for the personalIDAuthority*/
	private String personalIDAuthority="";
	/**holds the value for the personalIDEntryMethod*/
	private String personalIDEntryMethod="";
	/**holds the value for the personalIDTrack1Data*/
	private String personalIDTrack1Data="";
	/**holds the value for the personalIDTrack2Data*/
	private String personalIDTrack2Data="";
	/**holds the value for the personalIDType*/
	private String personalIDType="";
	/**holds the value for the phoneNumber*/
	private String phoneNumber="";
	/**holds the value for the prepaidRemainingBalance*/
	private String prepaidRemainingBalance="";
	/**holds the value for the promotionAPR*/
	private String promotionAPR="";
	/**holds the value for the promotionAPRType*/
	private String promotionAPRType="";
	/**holds the value for the promotionDescription*/
	private String promotionDescription="";
	/**holds the value for the promotionDuration*/
	private String promotionDuration="";
	/**holds the value for the signatureRequired*/
	private String signatureRequired="";
	/**holds the value for the Signature*/
	private String Signature = "";
	/**holds the value for the tenderSequenceNumber*/
	private String tenderSequenceNumber="";
	/**holds the value for the tenderSubType*/
	private String tenderSubType="";
	/**holds the value for the tenderType*/
	private String tenderType="";
	/**holds the value for the accountNumberToken*/
	private String accountNumberToken="";
	/**holds the value for the traceNumber*/
	private String traceNumber="";
	/**holds the value for the validationCode*/
	private String validationCode="";

	/*unique values pulled from CoxTeleCheckSaleInquiryRequest */ 
	/**holds the value for the actionCode*/
	private String actionCode="";
	/**holds the value for the channel*/
	private String channel="";
	/**holds the value for the checkNumber*/
	private String checkNumber="";
	/**holds the value for the checkTransactionType*/
	private String checkTransactionType="";
	/**holds the value for the checkType*/
	private String checkType;
	/**holds the value for the dateTime*/
	private String dateTime="";
	/**holds the value for the manualID*/
	private String manualID="";
	/**holds the value for the manualIDType*/
	private String manualIDType="";
	/**holds the value for the merchantID*/
	private String merchantID="";
	/**holds the value for the merchantTraceID*/
	private String merchantTraceID="";
	/**holds the value for the validationCode*/
	private String micrType="";
	/**holds the value for the mop*/
	private String mop="";
	/**holds the value for the terminalID*/
	private String terminalID="";
	/**holds the value for the regECompliant*/
	private String regECompliant="";

	/* unique attributes pulled from the CoxTeleCheckAdjustmentInquiryRequest */
	/**holds the value for the echoData*/
	private String echoData="";
	/**holds the value for the teleCheckTraceID*/
	private String teleCheckTraceID="";
	/**holds the value for the userName*/
	private String userName="";

	/*unique attributes pulled from CoxTeleCheckStatusInquiryRequest */
	/**holds the value for the achAction*/
	private String achAction="";
	/**holds the value for the versionControlNumber*/
	private String versionControlNumber="";
	/**holds the value for the responseMessage*/
	private String responseMessage;
	
	
	/**
	 * default constructor
	 */
	public AuthResponse() {
		
	}

	/**
	 * one parameter constructor 
	 * @param authResponse	the authorization response
	 */
	public AuthResponse (String authResponse) {
		this.authorizationResponseCode = authResponse;
	}

	
	/**
	 * gets the value stored in the approvalCode attribute
	 * 
	 * @return	the approvalCode value
	 */
	public String getApprovalCode() {
		return approvalCode;
	}

	/**
	 * sets the value in the approvalCode attribute
	 * 
	 * @param approvalCode	the approvalCode to set
	 */
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	/**
	 * gets the value stored in the auditTraceNumber attribute
	 * 
	 * @return	the auditTraceNumber value
	 */
	public String getAuditTraceNumber() {
		return auditTraceNumber;
	}

	/**
	 * sets the value in the auditTraceNumber attribute
	 * 
	 * @param auditTraceNumber	the auditTraceNumber to set
	 */
	public void setAuditTraceNumber(String auditTraceNumber) {
		this.auditTraceNumber = auditTraceNumber;
	}

	/**
	 * gets the value stored in the authorizationResponseCode attribute
	 * 
	 * @return	the authorizationResponseCode value
	 */
	public String getAuthorizationResponseCode() {
		return authorizationResponseCode;
	}

	/**
	 * sets the value in the authorizationResponseCode attribute
	 * 
	 * @param authorizationResponseCode	the authorizationResponseCode to set
	 */
	public void setAuthorizationResponseCode(String authorizationResponseCode) {
		this.authorizationResponseCode = authorizationResponseCode;
	}

	/**
	 * gets the value stored in the financialNetworkStatus attribute
	 * 
	 * @return	the financialNetworkStatus value
	 */
	public String getFinancialNetworkStatus() {
		return financialNetworkStatus;
	}

	/**
	 * sets the value in the financialNetworkStatus attribute
	 * 
	 * @param financialNetworkStatus	the financialNetworkStatus to set
	 */
	public void setFinancialNetworkStatus(String financialNetworkStatus) {
		this.financialNetworkStatus = financialNetworkStatus;
	}

	/**
	 * gets the value stored in the referenceCode attribute
	 * 
	 * @return	the referenceCode value
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * sets the value in the referenceCode attribute
	 * 
	 * @param referenceCode	the referenceCode to set
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	/**
	 * gets the value stored in the retrievalReferenceNumber attribute
	 * 
	 * @return	the retrievalReferenceNumber value
	 */
	public String getRetrievalReferenceNumber() {
		return retrievalReferenceNumber;
	}

	/**
	 * sets the value in the retrievalReferenceNumber attribute
	 * 
	 * @param retrievalReferenceNumber	the retrievalReferenceNumber to set
	 */
	public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
		this.retrievalReferenceNumber = retrievalReferenceNumber;
	}

	/**
	 * gets the value stored in the settlementData attribute
	 * 
	 * @return	the settlementData value
	 */
	public String getSettlementData() {
		return settlementData;
	}

	/**
	 * sets the value in the settlementData attribute
	 * 
	 * @param settlementData	the settlementData to set
	 */
	public void setSettlementData(String settlementData) {
		this.settlementData = settlementData;
	}

	/**
	 * gets the value stored in the abaNumber attribute
	 * 
	 * @return	the abaNumber value
	 */
	public String getAbaNumber() {
		return abaNumber;
	}

	/**
	 * sets the value in the abaNumber attribute
	 * 
	 * @param abaNumber	the abaNumber to set
	 */
	public void setAbaNumber(String abaNumber) {
		this.abaNumber = abaNumber;
	}

	/**
	 * gets the value stored in the accountNumber attribute
	 * 
	 * @return	the accountNumber value
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * sets the value in the accountNumber attribute
	 * 
	 * @param accountNumber	the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * gets the value stored in the maskedAccountNumber attribute
	 * 
	 * @return	the maskedAccountNumber value
	 */
	public String getMaskedAccountNumber() {
		return maskedAccountNumber;
	}

	/**
	 * sets the value in the maskedAccountNumber attribute
	 * 
	 * @param maskedAccountNumber	the maskedAccountNumber to set
	 */
	public void setMaskedAccountNumber(String maskedAccountNumber) {
		this.maskedAccountNumber = maskedAccountNumber;
	}

	/**
	 * gets the value stored in the accountAPR attribute
	 * 
	 * @return	the accountAPR value
	 */
	public String getAccountAPR() {
		return accountAPR;
	}

	/**
	 * sets the value in the accountAPR attribute
	 * 
	 * @param accountAPR	the accountAPR to set
	 */
	public void setAccountAPR(String accountAPR) {
		this.accountAPR = accountAPR;
	}

	/**
	 * gets the value stored in the accountAPRType attribute
	 * 
	 * @return	the accountAPRType value
	 */
	public String getAccountAPRType() {
		return accountAPRType;
	}

	/**
	 * sets the value in the accountAPRType attribute
	 * 
	 * @param accountAPRType	the accountAPRType to set
	 */
	public void setAccountAPRType(String accountAPRType) {
		this.accountAPRType = accountAPRType;
	}

	/**
	 * gets the value stored in the accountDataSource attribute
	 * 
	 * @return	the accountDataSource value
	 */
	public String getAccountDataSource() {
		return accountDataSource;
	}

	/**
	 * sets the value in the accountDataSource attribute
	 * 
	 * @param accountDataSource	the accountDataSource to set
	 */
	public void setAccountDataSource(String accountDataSource) {
		this.accountDataSource = accountDataSource;
	}

	/**
	 * gets the value stored in the additionalAmount attribute
	 * 
	 * @return	the additionalAmount value
	 */
	public Double getAdditionalAmount() {
		return additionalAmount;
	}

	/**
	 * sets the value in the additionalAmount attribute
	 * 
	 * @param additionalAmount	the additionalAmount to set
	 */
	public void setAdditionalAmount(Double additionalAmount) {
		this.additionalAmount = additionalAmount;
	}

	/**
	 * gets the value stored in the alternateAmount attribute
	 * 
	 * @return	the alternateAmount value
	 */
	public Double getAlternateAmount() {
		return alternateAmount;
	}

	/**
	 * sets the value in the alternateAmount attribute
	 * 
	 * @param alternateAmount	the alternateAmount to set
	 */
	public void setAlternateAmount(Double alternateAmount) {
		this.alternateAmount = alternateAmount;
	}

	/**
	 * gets the value stored in the authorizationCode attribute
	 * 
	 * @return	the authorizationCode value
	 */ 
	public String getAuthorizationCode() {
		return authorizationCode;
	}

	/**
	 * sets the value in the alternateAmount attribute
	 * 
	 * @param alternateAmount	the alternateAmount to set
	 */
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	/**
	 * gets the value stored in the authorizationMethod attribute
	 * 
	 * @return	the authorizationMethod value
	 */ 
	public String getAuthorizationMethod() {
		return authorizationMethod;
	}

	/**
	 * sets the value in the authorizationMethod attribute
	 * 
	 * @param authorizationMethod	the authorizationMethod to set
	 */
	public void setAuthorizationMethod(String authorizationMethod) {
		this.authorizationMethod = authorizationMethod;
	}

	/**
	 * gets the value stored in the authorizatonResponseCode attribute
	 * 
	 * @return	the authorizatonResponseCode value
	 */
	public String getAuthorizatonResponseCode() {
		return authorizatonResponseCode;
	}

	/**
	 * sets the value in the authorizatonResponseCode attribute
	 * 
	 * @param authorizatonResponseCode	the authorizatonResponseCode to set
	 */
	public void setAuthorizatonResponseCode(String authorizatonResponseCode) {
		this.authorizatonResponseCode = authorizatonResponseCode;
	}
	
	/**
	 * gets the value stored in the v attribute
	 * 
	 * @return	the authorizationSource value 
	 */
	
	public String getAuthorizationSource() {
		return authorizationSource;
	}

	/**
	 * sets the value in the authorizationSource attribute
	 * 
	 * @param authorizationSource	the authorizationSource to set
	 */
	public void setAuthorizationSource(String authorizationSource) {
		this.authorizationSource = authorizationSource;
	}

	/**
	 * gets the value stored in the authorizationTransactionID attribute
	 * 
	 * @return	the authorizationTransactionID value 
	 */
	public String getAuthorizationTransactionID() {
		return authorizationTransactionID;
	}

	/**
	 * sets the value in the authorizatonResponseCode attribute
	 * 
	 * @param authorizationTransactionID	the authorizationTransactionID to set
	 */
	public void setAuthorizationTransactionID(String authorizationTransactionID) {
		this.authorizationTransactionID = authorizationTransactionID;
	}

	/**
	 * gets the value stored in the balanceDue attribute
	 * 
	 * @return	the balanceDue value 
	 */
	public BigDecimal getBalanceDue() {
		return balanceDue;
	}

	/**
	 * sets the value in the balanceDue attribute
	 * 
	 * @param balanceDue	the balanceDue to set
	 */
	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}

	/**
	 * gets the value stored in the baseAmount attribute
	 * 
	 * @return	the baseAmount value 
	 */
	public Double getBaseAmount() {
		return baseAmount;
	}

	/**
	 * sets the value in the baseAmount attribute
	 * 
	 * @param baseAmount	the baseAmount to set
	 */
	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * gets the value stored in the conversionCode attribute
	 * 
	 * @return	the conversionCode value
	 */
	public String getConversionCode() {
		return conversionCode;
	}

	/**
	 * sets the value in the conversionCode attribute
	 * 
	 * @param conversionCode	the conversionCode to set
	 */
	public void setConversionCode(String conversionCode) {
		this.conversionCode = conversionCode;
	}

	/**
	 * gets the value stored in the cvmResults attribute
	 * 
	 * @return	the cvmResults value 
	 */
	public String getCvmResults() {
		return cvmResults;
	}

	/**
	 * sets the value in the cvmResults attribute
	 * 
	 * @param cvmResults	the v to set
	 */
	public void setCvmResults(String cvmResults) {
		this.cvmResults = cvmResults;
	}

	/**
	 * gets the value stored in the entryMethod attribute
	 * 
	 * @return	the entryMethod value
	 */
	public String getEntryMethod() {
		return entryMethod;
	}

	/**
	 * sets the value in the entryMethod attribute
	 * 
	 * @param entryMethod	the entryMethod to set
	 */
	public void setEntryMethod(String entryMethod) {
		this.entryMethod = entryMethod;
	}

	/**
	 * gets the value stored in the floorLimit attribute
	 * 
	 * @return	the floorLimit value 
	 */
	public Double getFloorLimit() {
		return floorLimit;
	}

	/**
	 * sets the value in the floorLimit attribute
	 * 
	 * @param floorLimit	the floorLimit to set
	 */
	public void setFloorLimit(Double floorLimit) {
		this.floorLimit = floorLimit;
	}

	/**
	 * gets the value stored in the giftCardAccountType attribute
	 * 
	 * @return	the giftCardAccountType value
	 */
	public String getGiftCardAccountType() {
		return giftCardAccountType;
	}

	/**
	 * sets the value in the giftCardAccountType attribute
	 * 
	 * @param giftCardAccountType	the giftCardAccountType to set
	 */
	public void setGiftCardAccountType(String giftCardAccountType) {
		this.giftCardAccountType = giftCardAccountType;
	}

	/**
	 * gets the value stored in the hostReference attribute
	 * 
	 * @return	the hostReference value 
	 */
	public String getHostReference() {
		return hostReference;
	}

	/**
	 * sets the value in the hostReference attribute
	 * 
	 * @param hostReference	the hostReference to set
	 */
	public void setHostReference(String hostReference) {
		this.hostReference = hostReference;
	}

	/**
	 * gets the value stored in the iccDetails attribute
	 * 
	 * @return	the iccDetails value 
	 */
	public String getIccDetails() {
		return iccDetails;
	}

	/**
	 * sets the value in the iccDetails attribute
	 * 
	 * @param iccDetails	the iccDetails to set
	 */
	public void setIccDetails(String iccDetails) {
		this.iccDetails = iccDetails;
	}

	/**
	 * gets the value stored in the applicationLabel attribute
	 * 
	 * @return	the applicationLabel value 
	 */
	public String getApplicationLabel() {
		return applicationLabel;
	}

	/**
	 * sets the value in the applicationLabel attribute
	 * 
	 * @param applicationLabel	the applicationLabel to set
	 */
	public void setApplicationLabel(String applicationLabel) {
		this.applicationLabel = applicationLabel;
	}

	/**
	 * gets the value stored in the merchantId attribute
	 * 
	 * @return	the merchantId value 
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * sets the value in the merchantId attribute
	 * 
	 * @param merchantId	the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * gets the value stored in the epiryDate attribute
	 * 
	 * @return	the epiryDate value 
	 */
	public String getEpiryDate() {
		return epiryDate;
	}

	/**
	 * sets the value in the epiryDate attribute
	 * 
	 * @param epiryDate	the epiryDate to set
	 */
	public void setEpiryDate(String epiryDate) {
		this.epiryDate = epiryDate;
	}
	
	/**
	 * gets the value stored in the startDate attribute
	 * 
	 * @return	the startDate value 
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * sets the value in the startDate attribute
	 * 
	 * @param startDate	the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * gets the value stored in the authorizationRequestCryptogram attribute
	 * 
	 * @return	the authorizationRequestCryptogram value 
	 */
	public String getAuthorizationRequestCryptogram() {
		return authorizationRequestCryptogram;
	}

	/**
	 * sets the value in the authorizationRequestCryptogram attribute
	 * 
	 * @param authorizationRequestCryptogram	the authorizationRequestCryptogram to set
	 */
	public void setAuthorizationRequestCryptogram(
			String authorizationRequestCryptogram) {
		this.authorizationRequestCryptogram = authorizationRequestCryptogram;
	}

	/**
	 * gets the value stored in the applicationInterchangeProfile attribute
	 * 
	 * @return	the applicationInterchangeProfile value of the work station id
	 */
	public String getApplicationInterchangeProfile() {
		return applicationInterchangeProfile;
	}

	/**
	 * sets the value in the applicationInterchangeProfile attribute
	 * 
	 * @param applicationInterchangeProfile	the applicationInterchangeProfile to set
	 */
	public void setApplicationInterchangeProfile(
			String applicationInterchangeProfile) {
		this.applicationInterchangeProfile = applicationInterchangeProfile;
	}

	/**
	 * gets the value stored in the applicationTransactionCounter attribute
	 * 
	 * @return	the applicationTransactionCounter value 
	 */
	public String getApplicationTransactionCounter() {
		return applicationTransactionCounter;
	}

	/**
	 * sets the value in the applicationTransactionCounter attribute
	 * 
	 * @param applicationTransactionCounter	the applicationTransactionCounter to set
	 */
	public void setApplicationTransactionCounter(
			String applicationTransactionCounter) {
		this.applicationTransactionCounter = applicationTransactionCounter;
	}

	/**
	 * gets the value stored in the unpredictableNumber attribute
	 * 
	 * @return	the unpredictableNumber value
	 */
	public String getUnpredictableNumber() {
		return unpredictableNumber;
	}

	/**
	 * sets the value in the unpredictableNumber attribute
	 * 
	 * @param unpredictableNumber	the unpredictableNumber to set
	 */
	public void setUnpredictableNumber(String unpredictableNumber) {
		this.unpredictableNumber = unpredictableNumber;
	}

	/**
	 * gets the value stored in the terminalVerificationResult attribute
	 * 
	 * @return	the terminalVerificationResult value
	 */
	public String getTerminalVerificationResult() {
		return terminalVerificationResult;
	}

	/**
	 * sets the value in the terminalVerificationResult attribute
	 * 
	 * @param terminalVerificationResult	the terminalVerificationResult to set
	 */
	public void setTerminalVerificationResult(String terminalVerificationResult) {
		this.terminalVerificationResult = terminalVerificationResult;
	}

	/**
	 * gets the value stored in the cryptogramTransactionType attribute
	 * 
	 * @return	the cryptogramTransactionType value 
	 */
	public String getCryptogramTransactionType() {
		return cryptogramTransactionType;
	}

	/**
	 * sets the value in the cryptogramTransactionType attribute
	 * 
	 * @param cryptogramTransactionType	the cryptogramTransactionType to set
	 */
	public void setCryptogramTransactionType(String cryptogramTransactionType) {
		this.cryptogramTransactionType = cryptogramTransactionType;
	}

	/**
	 * gets the value stored in the cryptogramInforamtionType attribute
	 * 
	 * @return	the cryptogramInforamtionType value
	 */
	public String getCryptogramInforamtionType() {
		return cryptogramInforamtionType;
	}

	/**
	 * sets the value in the cryptogramInforamtionType attribute
	 * 
	 * @param cryptogramInforamtionType	the cryptogramInforamtionType to set
	 */
	public void setCryptogramInforamtionType(String cryptogramInforamtionType) {
		this.cryptogramInforamtionType = cryptogramInforamtionType;
	}

	/**
	 * gets the value stored in the applicationResponseCryptogram attribute
	 * 
	 * @return	the applicationResponseCryptogram value 
	 */
	public String getApplicationResponseCryptogram() {
		return applicationResponseCryptogram;
	}

	/**
	 * sets the value in the applicationResponseCryptogram attribute
	 * 
	 * @param applicationResponseCryptogram	the applicationResponseCryptogram to set
	 */
	public void setApplicationResponseCryptogram(
			String applicationResponseCryptogram) {
		this.applicationResponseCryptogram = applicationResponseCryptogram;
	}

	/**
	 * gets the value stored in the posEntryMode1 attribute
	 * 
	 * @return	the posEntryMode1 value 
	 */
	public String getPosEntryMode1() {
		return posEntryMode1;
	}

	/**
	 * sets the value in the posEntryMode1 attribute
	 * 
	 * @param posEntryMode1	the posEntryMode1 to set
	 */
	public void setPosEntryMode1(String posEntryMode1) {
		this.posEntryMode1 = posEntryMode1;
	}

	/**
	 * gets the value stored in the posEntryMode2 attribute
	 * 
	 * @return	the posEntryMode2 value 
	 */
	public String getPosEntryMode2() {
		return posEntryMode2;
	}

	/**
	 * sets the value in the posEntryMode2 attribute
	 * 
	 * @param posEntryMode2	the posEntryMode2 to set
	 */
	public void setPosEntryMode2(String posEntryMode2) {
		this.posEntryMode2 = posEntryMode2;
	}

	/**
	 * gets the value stored in the applicationUsageControl attribute
	 * 
	 * @return	the applicationUsageControl value
	 */
	public String getApplicationUsageControl() {
		return applicationUsageControl;
	}

	/**
	 * sets the value in the applicationUsageControl attribute
	 * 
	 * @param applicationUsageControl	the applicationUsageControl to set
	 */
	public void setApplicationUsageControl(String applicationUsageControl) {
		this.applicationUsageControl = applicationUsageControl;
	}

	/**
	 * gets the value stored in the applicationVersionNumber attribute
	 * 
	 * @return	the applicationVersionNumber value 
	 */
	public String getApplicationVersionNumber() {
		return applicationVersionNumber;
	}

	/**
	 * sets the value in the applicationVersionNumber attribute
	 * 
	 * @param applicationVersionNumber	the applicationVersionNumber to set
	 */
	public void setApplicationVersionNumber(String applicationVersionNumber) {
		this.applicationVersionNumber = applicationVersionNumber;
	}

	/**
	 * gets the value stored in the terminalApplicationNumber attribute
	 * 
	 * @return	the terminalApplicationNumber value
	 */
	public String getTerminalApplicationNumber() {
		return terminalApplicationNumber;
	}

	/**
	 * sets the value in the terminalApplicationNumber attribute
	 * 
	 * @param terminalApplicationNumber	the terminalApplicationNumber to set
	 */
	public void setTerminalApplicationNumber(String terminalApplicationNumber) {
		this.terminalApplicationNumber = terminalApplicationNumber;
	}

	/**
	 * gets the value stored in the transactionStatusInformation attribute
	 * 
	 * @return	the transactionStatusInformation value
	 */
	public String getTransactionStatusInformation() {
		return transactionStatusInformation;
	}

	/**
	 * sets the value in the transactionStatusInformation attribute
	 * 
	 * @param transactionStatusInformation	the transactionStatusInformation to set
	 */
	public void setTransactionStatusInformation(String transactionStatusInformation) {
		this.transactionStatusInformation = transactionStatusInformation;
	}

	/**
	 * gets the value stored in the terminalType attribute
	 * 
	 * @return	the terminalType value 
	 */
	public String getTerminalType() {
		return terminalType;
	}

	/**
	 * sets the value in the terminalType attribute
	 * 
	 * @param terminalType	the terminalType to set
	 */
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	/**
	 * gets the value stored in the terminalCapabilities attribute
	 * 
	 * @return	the terminalCapabilities value 
	 */
	public String getTerminalCapabilities() {
		return terminalCapabilities;
	}

	/**
	 * sets the value in the terminalCapabilities attribute
	 * 
	 * @param terminalCapabilities	the terminalCapabilities to set
	 */
	public void setTerminalCapabilities(String terminalCapabilities) {
		this.terminalCapabilities = terminalCapabilities;
	}

	/**
	 * gets the value stored in the issuerActionCodesOnline attribute
	 * 
	 * @return	the issuerActionCodesOnline value
	 */
	public String getIssuerActionCodesOnline() {
		return issuerActionCodesOnline;
	}

	/**
	 * sets the value in the issuerActionCodesOnline attribute
	 * 
	 * @param issuerActionCodesOnline	the issuerActionCodesOnline to set
	 */
	public void setIssuerActionCodesOnline(String issuerActionCodesOnline) {
		this.issuerActionCodesOnline = issuerActionCodesOnline;
	}

	/**
	 * gets the value stored in the issuerActionCodesDenial attribute
	 * 
	 * @return	the issuerActionCodesDenial value 
	 */
	public String getIssuerActionCodesDenial() {
		return issuerActionCodesDenial;
	}

	/**
	 * sets the value in the issuerActionCodesDenial attribute
	 * 
	 * @param issuerActionCodesDenial	the issuerActionCodesDenial to set
	 */
	public void setIssuerActionCodesDenial(String issuerActionCodesDenial) {
		this.issuerActionCodesDenial = issuerActionCodesDenial;
	}

	/**
	 * gets the value stored in the issuerActionCodesDefault attribute
	 * 
	 * @return	the issuerActionCodesDefault value 
	 */
	public String getIssuerActionCodesDefault() {
		return issuerActionCodesDefault;
	}

	/**
	 * sets the value in the issuerActionCodesDefault attribute
	 * 
	 * @param issuerActionCodesDefault	the issuerActionCodesDefault to set
	 */
	public void setIssuerActionCodesDefault(String issuerActionCodesDefault) {
		this.issuerActionCodesDefault = issuerActionCodesDefault;
	}

	/**
	 * gets the value stored in the issuerApplicationData attribute
	 * 
	 * @return	the issuerApplicationData value 
	 */
	public String getIssuerApplicationData() {
		return issuerApplicationData;
	}

	/**
	 * sets the value in the issuerApplicationData attribute
	 * 
	 * @param issuerApplicationData	the issuerApplicationData to set
	 */
	public void setIssuerApplicationData(String issuerApplicationData) {
		this.issuerApplicationData = issuerApplicationData;
	}

	/**
	 * gets the value stored in the authorisationResponseCode attribute
	 * 
	 * @return	the authorisationResponseCode value 
	 */
	public String getAuthorisationResponseCode() {
		return authorisationResponseCode;
	}

	/**
	 * sets the value in the authorizatonResponseCode attribute
	 * 
	 * @param authorizatonResponseCode	the authorizatonResponseCode to set
	 */
	public void setAuthorisationResponseCode(String authorisationResponseCode) {
		this.authorisationResponseCode = authorisationResponseCode;
	}

	/**
	 * gets the value stored in the terminalCountryCode attribute
	 * 
	 * @return	the terminalCountryCode value 
	 */
	public String getTerminalCountryCode() {
		return terminalCountryCode;
	}

	/**
	 * sets the value in the authorizatonResponseCode attribute
	 * 
	 * @param terminalCountryCode	the terminalCountryCode to set
	 */
	public void setTerminalCountryCode(String terminalCountryCode) {
		this.terminalCountryCode = terminalCountryCode;
	}

	/**
	 * gets the value stored in the terminalCurrencyNumber attribute
	 * 
	 * @return	the terminalCurrencyNumber value 
	 */
	public String getTerminalCurrencyNumber() {
		return terminalCurrencyNumber;
	}

	/**
	 * sets the value in the terminalCurrencyNumber attribute
	 * 
	 * @param terminalCurrencyNumber	the terminalCurrencyNumber to set
	 */
	public void setTerminalCurrencyNumber(String terminalCurrencyNumber) {
		this.terminalCurrencyNumber = terminalCurrencyNumber;
	}

	/**
	 * gets the value stored in the journalKey attribute
	 * 
	 * @return	the journalKey value 
	 */
	public String getJournalKey() {
		return journalKey;
	}

	/**
	 * sets the value in the journalKey attribute
	 * 
	 * @param journalKey	the journalKey to set
	 */
	public void setJournalKey(String journalKey) {
		this.journalKey = journalKey;
	}

	/**
	 * gets the value stored in the localDate attribute
	 * 
	 * @return	the localDate value 
	 */
	public String getLocalDate() {
		return localDate;
	}

	/**
	 * sets the value in the localDate attribute
	 * 
	 * @param localDate	the localDate to set
	 */
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}

	/**
	 * gets the value stored in the localTime attribute
	 * 
	 * @return	the localTime value
	 */
	public String getLocalTime() {
		return localTime;
	}

	/**
	 * sets the value in the localTime attribute
	 * 
	 * @param localTime	the localTime to set
	 */
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	/**
	 * gets the value stored in the micrData attribute
	 * 
	 * @return	the micrData value 
	 */
	public String getMicrData() {
		return micrData;
	}

	/**
	 * sets the value in the micrData attribute
	 * 
	 * @param micrData	the micrData to set
	 */
	public void setMicrData(String micrData) {
		this.micrData = micrData;
	}

	/**
	 * gets the value stored in the minPaymentDue attribute
	 * 
	 * @return	the minPaymentDue value
	 */
	public String getMinPaymentDue() {
		return minPaymentDue;
	}

	/**
	 * sets the value in the minPaymentDue attribute
	 * 
	 * @param minPaymentDue	the minPaymentDue to set
	 */
	public void setMinPaymentDue(String minPaymentDue) {
		this.minPaymentDue = minPaymentDue;
	}

	/**
	 * gets the value stored in the paymentServiceIndicator attribute
	 * 
	 * @return	the paymentServiceIndicator 
	 */
	public String getPaymentServiceIndicator() {
		return paymentServiceIndicator;
	}

	/**
	 * sets the value in the paymentServiceIndicator attribute
	 * 
	 * @param paymentServiceIndicator	the paymentServiceIndicator to set
	 */
	public void setPaymentServiceIndicator(String paymentServiceIndicator) {
		this.paymentServiceIndicator = paymentServiceIndicator;
	
	}

	/**
	 * gets the value stored in the personalID attribute
	 * 
	 * @return	the personalID value
	 */
	public String getPersonalID() {
		return personalID;
	}

	/**
	 * sets the value in the personalID attribute
	 * 
	 * @param personalID	the personalID to set
	 */
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

	/**
	 * gets the value stored in the personalIDAuthority attribute
	 * 
	 * @return	the personalIDAuthority value
	 */
	public String getPersonalIDAuthority() {
		return personalIDAuthority;
	}

	/**
	 * sets the value in the personalIDAuthority attribute
	 * 
	 * @param personalIDAuthority	the personalIDAuthority to set
	 */
	public void setPersonalIDAuthority(String personalIDAuthority) {
		this.personalIDAuthority = personalIDAuthority;
	}

	/**
	 * gets the value stored in the personalIDEntryMethod attribute
	 * 
	 * @return	the personalIDEntryMethod value
	 */
	public String getPersonalIDEntryMethod() {
		return personalIDEntryMethod;
	}

	/**
	 * sets the value in the personalIDEntryMethod attribute
	 * 
	 * @param personalIDEntryMethod	the personalIDEntryMethod to set
	 */
	public void setPersonalIDEntryMethod(String personalIDEntryMethod) {
		this.personalIDEntryMethod = personalIDEntryMethod;
	}

	/**
	 * gets the value stored in the personalIDTrack1Data attribute
	 * 
	 * @return	the personalIDTrack1Data value 
	 */
	public String getPersonalIDTrack1Data() {
		return personalIDTrack1Data;
	}

	/**
	 * sets the value in the personalIDTrack1Data attribute
	 * 
	 * @param personalIDTrack1Data	the personalIDTrack1Data to set
	 */
	public void setPersonalIDTrack1Data(String personalIDTrack1Data) {
		this.personalIDTrack1Data = personalIDTrack1Data;
	}

	/**
	 * gets the value stored in the personalIDTrack2Data attribute
	 * 
	 * @return	the personalIDTrack2Data value
	 */
	public String getPersonalIDTrack2Data() {
		return personalIDTrack2Data;
	}

	/**
	 * sets the value in the personalIDTrack2Data attribute
	 * 
	 * @param personalIDTrack2Data	the personalIDTrack2Data to set
	 */
	public void setPersonalIDTrack2Data(String personalIDTrack2Data) {
		this.personalIDTrack2Data = personalIDTrack2Data;
	}

	/**
	 * gets the value stored in the personalIDType attribute
	 * 
	 * @return	the personalIDType value 
	 */
	public String getPersonalIDType() {
		return personalIDType;
	}

	/**
	 * sets the value in the personalIDType attribute
	 * 
	 * @param personalIDType	the personalIDType to set
	 */
	public void setPersonalIDType(String personalIDType) {
		this.personalIDType = personalIDType;
	}

	/**
	 * gets the value stored in the phoneNumber attribute
	 * 
	 * @return	the phoneNumber value 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * sets the value in the phoneNumber attribute
	 * 
	 * @param phoneNumber	the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * gets the value stored in the prepaidRemainingBalance attribute
	 * 
	 * @return	the prepaidRemainingBalance value
	 */
	public String getPrepaidRemainingBalance() {
		return prepaidRemainingBalance;
	}

	/**
	 * sets the value in the prepaidRemainingBalance attribute
	 * 
	 * @param prepaidRemainingBalance	the prepaidRemainingBalance to set
	 */
	public void setPrepaidRemainingBalance(String prepaidRemainingBalance) {
		this.prepaidRemainingBalance = prepaidRemainingBalance;
	}

	/**
	 * gets the value stored in the promotionAPR attribute
	 * 
	 * @return	the promotionAPR value 
	 */
	public String getPromotionAPR() {
		return promotionAPR;
	}

	/**
	 * sets the value in the promotionAPR attribute
	 * 
	 * @param promotionAPR	the promotionAPR to set
	 */
	public void setPromotionAPR(String promotionAPR) {
		this.promotionAPR = promotionAPR;
	}

	/**
	 * gets the value stored in the promotionAPRType attribute
	 * 
	 * @return	the promotionAPRType value 
	 */
	public String getPromotionAPRType() {
		return promotionAPRType;
	}

	/**
	 * sets the value in the promotionAPRType attribute
	 * 
	 * @param promotionAPRType	the promotionAPRType to set
	 */
	public void setPromotionAPRType(String promotionAPRType) {
		this.promotionAPRType = promotionAPRType;
	}

	/**
	 * gets the value stored in the promotionDescription attribute
	 * 
	 * @return	the promotionDescription value 
	 */
	public String getPromotionDescription() {
		return promotionDescription;
	}

	/**
	 * sets the value in the promotionDescription attribute
	 * 
	 * @param promotionDescription	the promotionDescription to set
	 */
	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	/**
	 * gets the value stored in the promotionDuration attribute
	 * 
	 * @return	the promotionDuration value 
	 */
	public String getPromotionDuration() {
		return promotionDuration;
	}

	/**
	 * sets the value in the promotionDuration attribute
	 * 
	 * @param promotionDuration	the promotionDuration to set
	 */
	public void setPromotionDuration(String promotionDuration) {
		this.promotionDuration = promotionDuration;
	}

	/**
	 * gets the value stored in the signatureRequired attribute
	 * 
	 * @return	the signatureRequired value 
	 */
	public String getSignatureRequired() {
		return signatureRequired;
	}

	/**
	 * sets the value in the signatureRequired attribute
	 * 
	 * @param signatureRequired	the signatureRequired to set
	 */
	public void setSignatureRequired(String signatureRequired) {
		this.signatureRequired = signatureRequired;
	}

	/**
	 * gets the value stored in the Signature attribute
	 * 
	 * @return	the Signature value 
	 */
	public String getSignature() {
		return Signature;
	}

	/**
	 * sets the value in the Signature attribute
	 * 
	 * @param Signature	the Signature to set
	 */
	public void setSignature(String signature) {
		Signature = signature;
	}

	/**
	 * gets the value stored in the tenderSequenceNumber attribute
	 * 
	 * @return	the tenderSequenceNumber value
	 */
	public String getTenderSequenceNumber() {
		return tenderSequenceNumber;
	}

	/**
	 * sets the value in the tenderSequenceNumber attribute
	 * 
	 * @param tenderSequenceNumber	the tenderSequenceNumber to set
	 */
	public void setTenderSequenceNumber(String tenderSequenceNumber) {
		this.tenderSequenceNumber = tenderSequenceNumber;
	}

	/**
	 * gets the value stored in the tenderSubType attribute
	 * 
	 * @return	the tenderSubType value 
	 */
	public String getTenderSubType() {
		return tenderSubType;
	}

	/**
	 * sets the value in the tenderSubType attribute
	 * 
	 * @param tenderSubType	the tenderSubType to set
	 */
	public void setTenderSubType(String tenderSubType) {
		this.tenderSubType = tenderSubType;
	}

	/**
	 * gets the value stored in the tenderType attribute
	 * 
	 * @return	the tenderType value 
	 */
	public String getTenderType() {
		return tenderType;
	}

	/**
	 * sets the value in the tenderType attribute
	 * 
	 * @param tenderType	the tenderType to set
	 */
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	/**
	 * gets the value stored in the accountNumberToken attribute
	 * 
	 * @return	the accountNumberToken value
	 */
	public String getAccountNumberToken() {
		return accountNumberToken;
	}

	/**
	 * sets the value in the accountNumberToken attribute
	 * 
	 * @param accountNumberToken	the accountNumberToken to set
	 */
	public void setAccountNumberToken(String accountNumberToken) {
		this.accountNumberToken = accountNumberToken;
	}

	/**
	 * gets the value stored in the traceNumber attribute
	 * 
	 * @return	the traceNumber value
	 */
	public String getTraceNumber() {
		return traceNumber;
	}

	/**
	 * sets the value in the traceNumber attribute
	 * 
	 * @param traceNumber	the traceNumber to set
	 */
	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	/**
	 * gets the value stored in the validationCode attribute
	 * 
	 * @return	the validationCode value 
	 */
	public String getValidationCode() {
		return validationCode;
	}

	/**
	 * sets the value in the validationCode attribute
	 * 
	 * @param validationCode	the validationCode to set
	 */
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	/**
	 * gets the value stored in the actionCode attribute
	 * 
	 * @return	the actionCode value
	 */
	public String getActionCode() {
		return actionCode;
	}


	/**
	 * sets the value in the actionCode attribute
	 * 
	 * @param actionCode	the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * gets the value stored in the channel attribute
	 * 
	 * @return	the channel value 
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * sets the value in the channel attribute
	 * 
	 * @param channel	the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * gets the value stored in the checkNumber attribute
	 * 
	 * @return	the checkNumber value
	 */
	
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * sets the value in the checkNumber attribute
	 * 
	 * @param checkNumber	the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * gets the value stored in the checkTransactionType attribute
	 * 
	 * @return	the checkTransactionType value
	 */
	public String getCheckTransactionType() {
		return checkTransactionType;
	}

	/**
	 * sets the value in the checkTransactionType attribute
	 * 
	 * @param checkTransactionType	the checkTransactionType to set
	 */
	public void setCheckTransactionType(String checkTransactionType) {
		this.checkTransactionType = checkTransactionType;
	}

	/**
	 * gets the value stored in the checkType attribute
	 * 
	 * @return	the checkType value
	 */ 
	public String getCheckType() {
		return checkType;
	}

	/**
	 * sets the value in the checkType attribute
	 * 
	 * @param checkType	the checkType to set
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	/**
	 * gets the value stored in the dateTime attribute
	 * 
	 * @return	the dateTime value
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * sets the value in the dateTime attribute
	 * 
	 * @param dateTime	the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * gets the value stored in the manualID attribute
	 * 
	 * @return	the manualID value
	 */
	public String getManualID() {
		return manualID;
	}

	/**
	 * sets the value in the manualID attribute
	 * 
	 * @param manualID	the manualID to set
	 */
	public void setManualID(String manualID) {
		this.manualID = manualID;
	}

	/**
	 * gets the value stored in the manualIDType attribute
	 * 
	 * @return	the manualIDType value
	 */
	public String getManualIDType() {
		return manualIDType;
	}

	/**
	 * sets the value in the manualIDType attribute
	 * 
	 * @param manualIDType	the manualIDType to set
	 */
	public void setManualIDType(String manualIDType) {
		this.manualIDType = manualIDType;
	}

	/**
	 * gets the value stored in the merchantID attribute
	 * 
	 * @return	the merchantID value
	 */
	public String getMerchantID() {
		return merchantID;
	}

	/**
	 * sets the value in the merchantID attribute
	 * 
	 * @param merchantID	the merchantID to set
	 */
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	/**
	 * gets the value stored in the merchantTraceID attribute
	 * 
	 * @return	the merchantTraceID value
	 */
	public String getMerchantTraceID() {
		return merchantTraceID;
	}

	/**
	 * sets the value in the merchantTraceID attribute
	 * 
	 * @param merchantTraceID	the merchantTraceID to set
	 */
	public void setMerchantTraceID(String merchantTraceID) {
		this.merchantTraceID = merchantTraceID;
	}

	/**
	 * gets the value stored in the micrType attribute
	 * 
	 * @return	the micrType value
	 */
	public String getMicrType() {
		return micrType;
	}

	/**
	 * sets the value in the micrType attribute
	 * 
	 * @param micrType	the micrType to set
	 */
	public void setMicrType(String micrType) {
		this.micrType = micrType;
	}

	/**
	 * gets the value stored in the mop attribute
	 * 
	 * @return	the mop value
	 */
	public String getMop() {
		return mop;
	}

	/**
	 * sets the value in the mop attribute
	 * 
	 * @param mop	the mop to set
	 */
	public void setMop(String mop) {
		this.mop = mop;
	}

	/**
	 * gets the value stored in the terminalID attribute
	 * 
	 * @return	the terminalID value 
	 */
	public String getTerminalID() {
		return terminalID;
	}

	/**
	 * sets the value in the terminalID attribute
	 * 
	 * @param terminalID	the terminalID to set
	 */
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	/**
	 * gets the value stored in the regECompliant attribute
	 * 
	 * @return	the regECompliant value
	 */
	public String getRegECompliant() {
		return regECompliant;
	}

	/**
	 * sets the value in the regECompliant attribute
	 * 
	 * @param regECompliant	the regECompliant to set
	 */
	public void setRegECompliant(String regECompliant) {
		this.regECompliant = regECompliant;
	}

	/**
	 * gets the value stored in the echoData attribute
	 * 
	 * @return	the echoData value
	 */
	public String getEchoData() {
		return echoData;
	}

	/**
	 * sets the value in the echoData attribute
	 * 
	 * @param echoData	the echoData to set
	 */
	public void setEchoData(String echoData) {
		this.echoData = echoData;
	}

	/**
	 * gets the value stored in the teleCheckTraceID attribute
	 * 
	 * @return	the teleCheckTraceID value
	 */
	public String getTeleCheckTraceID() {
		return teleCheckTraceID;
	}

	/**
	 * sets the value in the teleCheckTraceID attribute
	 * 
	 * @param teleCheckTraceID	the teleCheckTraceID to set
	 */
	public void setTeleCheckTraceID(String teleCheckTraceID) {
		this.teleCheckTraceID = teleCheckTraceID;
	}

	/**
	 * gets the value stored in the userName attribute
	 * 
	 * @return	the userName value
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * sets the value in the userName attribute
	 * 
	 * @param userName	the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * gets the value stored in the achAction attribute
	 * 
	 * @return	the achAction value 
	 */
	public String getAchAction() {
		return achAction;
	}

	/**
	 * sets the value in the achAction attribute
	 * 
	 * @param achAction	the achAction to set
	 */
	public void setAchAction(String achAction) {
		this.achAction = achAction;
	}

	/**
	 * gets the value stored in the versionControlNumber attribute
	 * 
	 * @return	the versionControlNumber value
	 */
	public String getVersionControlNumber() {
		return versionControlNumber;
	}

	/**
	 * sets the value in the versionControlNumber attribute
	 * 
	 * @param versionControlNumber	the versionControlNumber to set
	 */
	public void setVersionControlNumber(String versionControlNumber) {
		this.versionControlNumber = versionControlNumber;
	}

	/**
	 * gets the value stored in the responseMessage attribute
	 * 
	 * @return	the responseMessage value
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * sets the value in the responseMessage attribute
	 * 
	 * @param responseMessage	the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
