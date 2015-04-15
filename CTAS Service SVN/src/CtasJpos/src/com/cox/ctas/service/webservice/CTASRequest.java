/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 *  
 ************************************************************/
package com.cox.ctas.service.webservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  POS exposed class for the parameters required for a payment authorization 
 *  request. 
 * 
 * @author 
 *
 */

@XmlRootElement(name = "CTASRequest")
public class CTASRequest {
	
	/**holds the value for the workstation id*/
	private String workstationID="";
	/**holds the value for the store id*/
	private String storeID="";
	/**holds the value for the transaction id*/
	private String transactionID="";
	/**holds the value for the authorization transaction type*/
	private int authorizationTransactionType;
	/**holds the value for the transaction type*/
	private int transactionType;
	/**holds the value for the base amount */
	private String baseAmount="";
	/**holds the value for the request tender type*/
	private String requestTenderType="";
	/**holds the value for the payment amount*/
	private String paymentAmount="";
	

	//Params added per CoxTenderAuth
//	private int checkAuthSubtype;
//	private int transactionType1 = 1;
		
	//Per Abhishek's POS Request Params
//	private String store="";
//	private boolean trainingMode = false;
//	private String localizedLocationNames="";
//	private String registerID="";
//	private String city="";
//	private String state="";
//	private String country="US";
//	private String zipCode="";
//	private String postalCodeExtension="";
//	private String phone="";
//	private String districtClassIdentifier="";
//	private String districtClassDescription="";
//	private String regionClassIdentifier="";
//	private String regionClassDescription="";
//	private String geoCode="";
//	private String entryMethod="";
//	private String operatorID="";
//	private String preferredLocale="";
//	private String rawResponse="";
//	private String rawRequest="";
//	private String requestTime="";
//	private String sessionID="";
//	private String trackData="";
//	private String workstatonIpAddress="";
//	private String abaNumber="";
//	private String alternateAmount="";	
//	private String conversionCode="";			
//	private String floorLimit="";
//	private String personalIDAuthority="";
//	private String personalIDEntryMethod="";	
//	private String personalIDTrack1Data="";	
//	private String personalIDTrack2Data="";	
//	private String personalIDType="";			
//	private String phoneNumber="";
//	private boolean posGFCardEntryRequired = false;
//	private String micrData="";	
//	private String requestSubType = "none";
//	private String tenderSequenceNumber="";
//	private boolean updateBalance=false;
	
	
	/**
	 * gets the value stored in the workstationID attribute
	 * 
	 * @return	the value of the work station id
	 */
	public String getWorkstationID() {
		return workstationID;
	}
	
	/**
	 * sets the value in the workstationID attribute
	 * 
	 * @param workstationID	the work station id to be assigned
	 */
	public void setWorkstationID(String workstationID) {
		this.workstationID = workstationID;
	}
	
//	public String getStore() {
//		return store;
//	}
//	
//	public void setStore(String store) {
//		this.store = store;
//	}
//	
//	public boolean isTrainingMode() {
//		return trainingMode;
//	}
//	
//	public void setTrainingMode(boolean trainingMode) {
//		this.trainingMode = trainingMode;
//	}
	
	/**
	 * gets the value stored in the storeID attribute
	 * 
	 * @return	the value of the store id
	 */
	public String getStoreID() {
		return storeID;
	}
	
	/**
	 * sets the value in the storeID attribute
	 * 
	 * @param storeID	the store id to be assigned
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	
//	public String getLocalizedLocationNames() {
//		return localizedLocationNames;
//	}
//	
//	public void setLocalizedLocationNames(String localizedLocationNames) {
//		this.localizedLocationNames = localizedLocationNames;
//	}
//	
//	public String getRegisterID() {
//		return registerID;
//	}
//	
//	public void setRegisterID(String registerID) {
//		this.registerID = registerID;
//	}
//	
//	public String getCity() {
//		return city;
//	}
//	
//	public void setCity(String city) {
//		this.city = city;
//	}
//	
//	public String getState() {
//		return state;
//	}
//	
//	public void setState(String state) {
//		this.state = state;
//	}
//	
//	public String getCountry() {
//		return country;
//	}
//	
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	
//	public String getZipCode() {
//		return zipCode;
//	}
//	
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
//	}
//	
//	public String getPostalCodeExtension() {
//		return postalCodeExtension;
//	}
//	
//	public void setPostalCodeExtension(String postalCodeExtension) {
//		this.postalCodeExtension = postalCodeExtension;
//	}
//	
//	public String getPhone() {
//		return phone;
//	}
//	
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	
//	public String getDistrictClassIdentifier() {
//		return districtClassIdentifier;
//	}
//	
//	public void setDistrictClassIdentifier(String districtClassIdentifier) {
//		this.districtClassIdentifier = districtClassIdentifier;
//	}
//	
//	public String getDistrictClassDescription() {
//		return districtClassDescription;
//	}
//	
//	public void setDistrictClassDescription(String districtClassDescription) {
//		this.districtClassDescription = districtClassDescription;
//	}
//	
//	public String getRegionClassIdentifier() {
//		return regionClassIdentifier;
//	}
//	
//	public void setRegionClassIdentifier(String regionClassIdentifier) {
//		this.regionClassIdentifier = regionClassIdentifier;
//	}
//	
//	public String getRegionClassDescription() {
//		return regionClassDescription;
//	}
//	
//	public void setRegionClassDescription(String regionClassDescription) {
//		this.regionClassDescription = regionClassDescription;
//	}
//	
//	public String getGeoCode() {
//		return geoCode;
//	}
//	
//	public void setGeoCode(String geoCode) {
//		this.geoCode = geoCode;
//	}
	
	/**
	 * gets the value stored in the transactionID attribute
	 * 
	 * @return	the value of the transaction id
	 */
	public String getTransactionID() {
		return transactionID;
	}
	
	/**
	 * sets the value in the transactionID attribute
	 * 
	 * @param transactionID	the transaction id to be assigned
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	/**
	 * gets the value stored in the authorizationTransactionType attribute
	 * 
	 * @return	the value of the authorization transaction type
	 */
	public int getAuthorizationTransactionType() {
		return authorizationTransactionType;
	}
	
	/**
	 * sets the value in the authorizationTransactionType attribute
	 * 
	 * @param authorizationTransactionType	the  authorization transaction type to be assigned
	 */
	public void setAuthorizationTransactionType(int authorizationTransactionType) {
		this.authorizationTransactionType = authorizationTransactionType;
	}
//	
//	public String getEntryMethod() {
//		return entryMethod;
//	}
//	
//	public void setEntryMethod(String entryMethod) {
//		this.entryMethod = entryMethod;
//	}
//	
//	public String getOperatorID() {
//		return operatorID;
//	}
//	
//	public void setOperatorID(String operatorID) {
//		this.operatorID = operatorID;
//	}
//	
//	public String getPreferredLocale() {
//		return preferredLocale;
//	}
//	
//	public void setPreferredLocale(String preferredLocale) {
//		this.preferredLocale = preferredLocale;
//	}
//	
//	public String getRawResponse() {
//		return rawResponse;
//	}
//	
//	public void setRawResponse(String rawResponse) {
//		this.rawResponse = rawResponse;
//	}
	
//	public String getRawRequest() {
//		return rawRequest;
//	}
//	
//	public void setRawRequest(String rawRequest) {
//		this.rawRequest = rawRequest;
//	}
	
//	public String getRequestTime() {
//		return requestTime;
//	}
//	
//	public void setRequestTime(String requestTime) {
//		this.requestTime = requestTime;
//	}
//	
//	public String getSessionID() {
//		return sessionID;
//	}
//	
//	public void setSessionID(String sessionID) {
//		this.sessionID = sessionID;
//	}
//	
//	public String getTrackData() {
//		return trackData;
//	}
//	
//	public void setTrackData(String trackData) {
//		this.trackData = trackData;
//	}
	
	/**
	 * gets the value stored in the transactionType attribute
	 * 
	 * @return	the value of the transaction type
	 */
	public int getTransactionType() {
		return transactionType;
	}
	
	/**
	 * sets the value in the transactionType attribute
	 * 
	 * @param transactionType	the transaction type to be assigned
	 */
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
//	public String getWorkstatonIpAddress() {
//		return workstatonIpAddress;
//	}
//	
//	public void setWorkstatonIpAddress(String workstatonIpAddress) {
//		this.workstatonIpAddress = workstatonIpAddress;
//	}
//	
//	public String getAbaNumber() {
//		return abaNumber;
//	}
//	
//	public void setAbaNumber(String abaNumber) {
//		this.abaNumber = abaNumber;
//	}
//	
//	public String getAlternateAmount() {
//		return alternateAmount;
//	}
//	
//	public void setAlternateAmount(String alternateAmount) {
//		this.alternateAmount = alternateAmount;
//	}
	
	/**
	 * gets the value stored in the baseAmount attribute
	 * 
	 * @return	the base amount value
	 */
	public String getBaseAmount() {
		return baseAmount;
	}
	
	/**
	 * sets the value in the baseAmount attribute
	 * 
	 * @param baseAmount	the base amount to set
	 */
	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}
	
//	public String getConversionCode() {
//		return conversionCode;
//	}
//	
//	public void setConversionCode(String conversionCode) {
//		this.conversionCode = conversionCode;
//	}
//	
//	public String getFloorLimit() {
//		return floorLimit;
//	}
//	
//	public void setFloorLimit(String floorLimit) {
//		this.floorLimit = floorLimit;
//	}
	
	/**
	 * gets the value stored in the paymentAmount attribute
	 * 
	 * @return	the payment amount value
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}
	
	/**
	 * sets the value in the paymentAmount attribute
	 * 
	 * @param paymentAmount	the payment amount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
//	public String getPersonalIDAuthority() {
//		return personalIDAuthority;
//	}
//	
//	public void setPersonalIDAuthority(String personalIDAuthority) {
//		this.personalIDAuthority = personalIDAuthority;
//	}
//	
//	public String getPersonalIDEntryMethod() {
//		return personalIDEntryMethod;
//	}
//	
//	public void setPersonalIDEntryMethod(String personalIDEntryMethod) {
//		this.personalIDEntryMethod = personalIDEntryMethod;
//	}
//	
//	public String getPersonalIDTrack1Data() {
//		return personalIDTrack1Data;
//	}
//	
//	public void setPersonalIDTrack1Data(String personalIDTrack1Data) {
//		this.personalIDTrack1Data = personalIDTrack1Data;
//	}
//	
//	public String getPersonalIDTrack2Data() {
//		return personalIDTrack2Data;
//	}
//	
//	public void setPersonalIDTrack2Data(String personalIDTrack2Data) {
//		this.personalIDTrack2Data = personalIDTrack2Data;
//	}
//	
//	public String getPersonalIDType() {
//		return personalIDType;
//	}
//	
//	public void setPersonalIDType(String personalIDType) {
//		this.personalIDType = personalIDType;
//	}
//	
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//	
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//	
//	public boolean isPosGFCardEntryRequired() {
//		return posGFCardEntryRequired;
//	}
//	
//	public void setPosGFCardEntryRequired(boolean posGFCardEntryRequired) {
//		this.posGFCardEntryRequired = posGFCardEntryRequired;
//	}
//	
//	public String getMicrData() {
//		return micrData;
//	}
//	
//	public void setMicrData(String micrData) {
//		this.micrData = micrData;
//	}
//	
	
	/**
	 * gets the value stored in the requestTenderType attribute
	 * 
	 * @return	the request tender type value
	 */
	public String getRequestTenderType() {
		return requestTenderType;
	}
	
	/**
	 * sets the value in the requestTenderType attribute
	 * 
	 * @param requestTenderType	the request tender type to set
	 */
	public void setRequestTenderType(String requestTenderType) {
		this.requestTenderType = requestTenderType;
	}
//	
//	public String getRequestSubType() {
//		return requestSubType;
//	}
//	
//	public void setRequestSubType(String requestSubType) {
//		this.requestSubType = requestSubType;
//	}
//	
//	public String getTenderSequenceNumber() {
//		return tenderSequenceNumber;
//	}
//	
//	public void setTenderSequenceNumber(String tenderSequenceNumber) {
//		this.tenderSequenceNumber = tenderSequenceNumber;
//	}
//	
//	public boolean isUpdateBalance() {
//		return updateBalance;
//	}
//	
//	public void setUpdateBalance(boolean updateBalance) {
//		this.updateBalance = updateBalance;
//	}
	
//	public int getCheckAuthSubtype() {
//		return checkAuthSubtype;
//	}
//
//	public void setCheckAuthSubtype(int checkAuthSubtype) {
//		this.checkAuthSubtype = checkAuthSubtype;
//	}

//	public int getTransactionType1() {
//		return transactionType1;
//	}
//
//	public void setTransactionType1(int transactionType1) {
//		this.transactionType1 = transactionType1;
//	}
	
	
	}
