//-------------------------------------------------------------------------
/**
 * Copyright (c) Cox Communications. All Rights Reserved.
 *
 *  
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 */
// -------------------------------------------------------------------------

package com.cox.ctas.service.tenderauth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import jpos.JposException;

import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cox.ctas.service.configuration.CTASProperties;
import com.cox.ctas.service.device.CTASDeviceActionService;
import com.cox.ctas.service.utility.GenUtils;
import com.cox.ctas.service.webservice.AuthRequest;
import com.cox.ctas.service.webservice.AuthResponse;
import com.cox.ctas.vf.mx.adapters.Mx8xxFormsAdapter;
import com.cox.ctas.vf.mx.adapters.Mx8xxMSRAdapter;
import com.cox.paymentservices.paymentech.Authorization;
import com.cox.paymentservices.paymentech.AuthorizationDocument;
import com.cox.paymentservices.paymentech.AuthorizationResponse;
import com.cox.paymentservices.paymentech.AuthorizationResponse.Return;
import com.cox.paymentservices.paymentech.AuthorizationResponseDocument;
import com.cox.paymentservices.paymentech.AuthorizationServiceStub;
import com.cox.paymentservices.paymentech.DataExceptionException0;
import com.cox.wireless.billing.cxf.service.AdjustmentInquiry;
import com.cox.wireless.billing.cxf.service.AdjustmentInquiryDocument;
import com.cox.wireless.billing.cxf.service.AdjustmentInquiryResponse;
import com.cox.wireless.billing.cxf.service.AdjustmentInquiryResponseDocument;
import com.cox.wireless.billing.cxf.service.AdjustmentRequest;
import com.cox.wireless.billing.cxf.service.AdjustmentResponse;
import com.cox.wireless.billing.cxf.service.ChannelEnum;
import com.cox.wireless.billing.cxf.service.EnumCheckType;
import com.cox.wireless.billing.cxf.service.IOException;
import com.cox.wireless.billing.cxf.service.IOExceptionDocument;
import com.cox.wireless.billing.cxf.service.IOExceptionException1;
import com.cox.wireless.billing.cxf.service.MopEnum;
import com.cox.wireless.billing.cxf.service.SaleInquiry;
import com.cox.wireless.billing.cxf.service.SaleInquiryDocument;
import com.cox.wireless.billing.cxf.service.SaleInquiryResponse;
import com.cox.wireless.billing.cxf.service.SaleInquiryResponseDocument;
import com.cox.wireless.billing.cxf.service.SalesRequest;
import com.cox.wireless.billing.cxf.service.SalesResponse;
import com.cox.wireless.billing.cxf.service.StatusInquiry;
import com.cox.wireless.billing.cxf.service.StatusInquiryDocument;
import com.cox.wireless.billing.cxf.service.StatusInquiryResponse;
import com.cox.wireless.billing.cxf.service.StatusInquiryResponseDocument;
import com.cox.wireless.billing.cxf.service.StatusRequest;
import com.cox.wireless.billing.cxf.service.StatusResponse;
import com.cox.wireless.billing.cxf.service.TeleCheckImplServiceStub;
import com.cox.wireless.billing.cxf.service.UnknownHostException;
import com.cox.wireless.billing.cxf.service.UnknownHostExceptionDocument;
import com.cox.wireless.billing.cxf.service.UnknownHostExceptionException2;
import com.cox.wireless.billing.cxf.service.ValidationException;
import com.cox.wireless.billing.cxf.service.ValidationExceptionDocument;
import com.cox.wireless.billing.cxf.service.ValidationExceptionException0;
import com.cox.wireless.billing.paymentech.request.AccountInfoDocument.AccountInfo;
import com.cox.wireless.billing.paymentech.request.AddressInfoDocument.AddressInfo;
import com.cox.wireless.billing.paymentech.request.EMethodOfPaymentType;
import com.cox.wireless.billing.paymentech.request.ETelephoneType;
import com.cox.wireless.billing.paymentech.request.OrderInfoDocument.OrderInfo;
import com.cox.wireless.billing.paymentech.request.PaymentInfoDocument.PaymentInfo;
import com.cox.wireless.billing.paymentech.request.PosInfoDocument.PosInfo;
import com.cox.wireless.billing.paymentech.request.RetailInfoDocument.RetailInfo;
import com.cox.wireless.billing.paymentech.request.SalesChannelType;
import com.cox.wireless.billing.paymentech.request.SalesInfoDocument.SalesInfo;
import com.cox.wireless.billing.paymentech.request.SessionDocument.Session;
import com.cox.wireless.billing.paymentech.response.ResponseSessionDocument.ResponseSession;
import com.cox.wireless.security.Encrypt;
import com.cox.wireless.security.SecurityException;

//-------------------------------------------------------------------------
/**
 * Formerly the CoxFinancialTechnician, this class provides an interface to 
 * tender authorization services supplied by ISD. It uses TCP/IP sockets to
 * communicate with the ISD host. It formats authorization requests and 
 * parses responses according to the specifications outlined in the ISD 
 * IMSRTRIB Message Server Router Interface copy book.
 * 
 * Note: for release 6.0, the ISD integration effort covers credit, debit and
 * check authorizations only. Gift card and house account authorizations may be
 * scheduled for a future release.
 * 
 * @see com.extendyourstore.domain.manager.ifc.TenderAuthTechnicianIfc

 */
// -------------------------------------------------------------------------

public class CoxTenderAuth implements CoxTenderAuthConstantsIfc{

	/**logger for the class*/
	Logger logger = Logger.getLogger(CoxTenderAuth.class);
	/**required revision number supplied by source control system*/
	public static final String revisionNumber = "$Revision: 13$";
	/**constant for class name used for logging*/
	private static final String CLASSNAME = "CoxTenderAuth";


	//REVISIT - Needs to be externalized to xml
	/** hard coded URL for LISA payment auth gateway*/
	//private String paymentechServiceURL = "http://172.18.200.163:7022/PaymentechAuthorization/Authorization";
	/** hard coded URL for Original PaymentTech payment auth gateway used for testing public.key encryption*/
	private String paymentechServiceURL = "https://cpg.dev.cox.com:8443/PaymentechAuthorization/Authorization?wsdl";
	/**holds the URL value for the RWS check payment auth gateway*/
	private String teleCheckServiceURL = "";  
	/**holds the value for the check payment interval in milliseconds*/
	private String teleCheckInquiryInterval="150";
	/**holds the value for the credit card payment interval in milliseconds*/
	private String paymentechInterval="150";
	/**holds an instance of the deviceService used to control the CPOI device*/
	private CTASDeviceActionService deviceService;
	/**holds value of the encrypted card number*/
	private String encryptedCardNumber = null;
	/**holds an instance of the CTAS properties that holds configurable values*/
	private CTASProperties properties;
	
	/**
	 * default constructor
	 */
	public CoxTenderAuth() {
		if(logger.isDebugEnabled()){
			logger.debug("CoxTenderAuth started");
		}
	}

	/*// ----------------------------------------------------------------------
	 *//**
	 * Process a tender authorization request.
	 * 
	 * @param tenderAuthRequest
	 *            tender authorization data object
	 *//*
	// ----------------------------------------------------------------------
	public synchronized AuthResponse requestAuthorization(AuthRequest request) {

		if (logger.isDebugEnabled()) {
			logger.debug("AuthResponse: Received authorization request: " + request);
		}

		AuthResponse response = null;

		if (request.getAuthorizationTransactionType() == CHECK && 
				request.getCheckAuthSubtype() == SALE_INQUIRY) {

			response= requestTeleCheckSaleInquiryAuthorization(request);
		}
		else if (request.getAuthorizationTransactionType() == CHECK && 
				request.getCheckAuthSubtype() == STATUS_INQUIRY) {

			response = requestTeleCheckStatusInquiryAuthorization(request);
		}
		else if (request.getAuthorizationTransactionType() == CHECK && 
				request.getCheckAuthSubtype() == ADJUSTMENT_INQUIRY) {

			response = requestTeleCheckAdjustmentInquiryAuthorization(request); 
		}
		else
		{
			response = requestTenderAuthorization(request);
		}


		if (logger.isDebugEnabled()) {
			logger.debug("AuthResponse: Returning authorization response:\n" + response);
		}

		return response;
	}*/

	// -------------------------------------------------------------------------
	
	/***
	 * evaluates the response code received from payment gateway
	 * 
	 * @param statusCode
	 * @param reasonCode
	 * @param siebelOrder
	 * @return
	 */
	
	public String getResponseCode(String statusCode, String reasonCode, boolean siebelOrder) {
		// Some of the codes are the same so set to statusCode as default
		String responseCode = statusCode;

		// For the status codes that are different, map them to responseCodes
		if (statusCode.equalsIgnoreCase("S")) {
			responseCode = TenderAuthConstantsIfc.APPROVED;
		} else if (statusCode.equalsIgnoreCase("W")
				&& (reasonCode.equals("999"))) {
			if (siebelOrder) {
				responseCode = TenderAuthConstantsIfc.APPROVED;
			} else {
				responseCode = CoxTenderAuthConstantsIfc.DUPLICATE_PAYMENT;
			}
		} else {

			if (!(reasonCode.equals("401"))) {
				responseCode = TenderAuthConstantsIfc.DECLINED;
			} else if (!(reasonCode.equals("000"))) {
				responseCode = TenderAuthConstantsIfc.ERROR_RETRY;
			} else {
				responseCode = TenderAuthConstantsIfc.REFERRAL;
			}
		}

		return responseCode;
	}

	// ----------------------------------------------------------------------
	/**
	 * Retrieves the source control system revision number.
	 * 
	 * @return String representation of revision number
	 */
	// ----------------------------------------------------------------------
	public String getRevisionNumber() {
		return revisionNumber;
	}

	// ----------------------------------------------------------------------
	/**
	 * Method to default display string function.
	 * 
	 * @return String representation of object
	 */
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer strResult = new StringBuffer("Class:  ")
		.append(getClass().getName()).append(" Revision ")
		.append(getRevisionNumber()).append(")").append(hashCode());
		return strResult.toString();
	}

	public AuthResponse requestActivation(AuthRequest arg0)
			throws RemoteException {
		//Item activation is not used
		return null;
	}

	/**
	 * Rearrange date to expected format. (yyMMdd to MMddyyyy)
	 * 
	 * @param authDate
	 * @return
	 */
	private String formatAuthorizedDate(String authDate) {

		Date retDate = null;
		StringBuffer authDateFormatted = new StringBuffer("");

		try {
			DateFormat format = new SimpleDateFormat("yyMMdd");
			retDate = format.parse(authDate);
			authDateFormatted = authDateFormatted
					.append(padZeros(retDate.getMonth() + "", 2))
					.append(padZeros(retDate.getDay() + "", 2))
					.append(retDate.getYear());
		} catch (Exception e) {
			// any exception causes a return of null
		}
		return authDateFormatted.toString();
	}

	/**
	 * @param value
	 * @param columnSize
	 * @return
	 */
	public String padZeros(String value, int columnSize) {
		int length = value.length();

		if (length < columnSize) {
			for (int i = 0; i < (columnSize - length); i++) {
				// value += 0;
				value = "0" + value;
			}
			return value;
		} else {
			return value.substring(0, columnSize);
		}
	}

	/**
	 * gets the value for the paymentTech auth service URL
	 * 
	 * @return String
	 */
	public String getPaymentechServiceURL() {
		return paymentechServiceURL;
	}

	/**
	 * sets the value for the paymentTech auth service URL 
	 * 
	 * @param paymentechServiceURL
	 */
	public void setPaymentechServiceURL(String paymentechServiceURL) {
		this.paymentechServiceURL = paymentechServiceURL;
	}

	/**
	 * Send request to ADS for all tender
	 * 
	 * @param request
	 * @return TenderAuthResponse
	 */
	public AuthResponse requestTenderAuthorization(AuthRequest request)  {

		if (logger.isDebugEnabled()) {
			logger.debug("requestTenderAuthorization: Start");
		}

		StringBuffer requestStringBuffer = null;
		AuthResponse response = null;
		AuthorizationResponseDocument outDocument = null;
		boolean manualEntry = false;

		try {        		

			AuthorizationServiceStub stub = new AuthorizationServiceStub(getPaymentechServiceURL());

			/* GET INSTANCES of objects needed for authorization from the factory */ 
			// payment authorization info
			Authorization paymentAuthorization = Authorization.Factory.newInstance();

			//authorize message info
			com.cox.paymentservices.paymentech.Authorization.Authorize authorizeMessage = 
					com.cox.paymentservices.paymentech.Authorization.Authorize.Factory.newInstance();

			//pos info
			PosInfo posInfo = PosInfo.Factory.newInstance();

			//ICOMS account info object retrieved from factory
			AccountInfo acctInfo = AccountInfo.Factory.newInstance();

			Session session = Session.Factory.newInstance();
			// payment info
			PaymentInfo paymentInfo = PaymentInfo.Factory.newInstance();

			// order Info
			OrderInfo orderInfo = OrderInfo.Factory.newInstance();

			// sales Info
			SalesInfo salesInfo = SalesInfo.Factory.newInstance();

			// SET PAYMENT INFO 
			// payment Indicator will always be empty
			paymentInfo.setPaymentIndicator("");

			// Encryption flag
			paymentInfo.setEncryptionFlag("");
			paymentInfo.setCurrencyCode("840");


			//SET SALES INFO
			// terminal ID
			salesInfo.setTerminalId("");

			// sales user ID
			salesInfo.setSalesUserId("");

			// sales channel is always in POS
			salesInfo.setSalesChannel(SalesChannelType.POS);

			// TER 8120, AIA Decommissioned modification, POS should not sending presenter ID and division ID to WWS 
			// presenter ID
			//Changed by SN
			/*salesInfo.setPresenterId("030480");

    			// division ID
    			salesInfo.setDivisionId(30605);*/

			// batch Number
			salesInfo.setBatchNumber("");
			//Ends here

			//REVISIT
			/*uniqueIdGeneratorForCreditPaymentRecords.append( new
			  Integer((int) Math.round(1000 * Math.random())) .toString());*/

			StringBuffer uniqueIdGeneratorForCreditPaymentRecords = new StringBuffer();

			//not included in current request object
			//((AuthRequest) request).setICOMSAccountNumber(request.getICOMSAccountNumber());
			//acctInfo.setSiebelAccountNumber(((AuthRequest)request).getSiebelAccountNumber());

			uniqueIdGeneratorForCreditPaymentRecords.append(request.getTransactionID());

			//REVISIT
			//uniqueIdGeneratorForCreditPaymentRecords.append(((AuthRequest)request).getUniqueIncrementer());

			session.setTransactionId(uniqueIdGeneratorForCreditPaymentRecords.toString());

			//((AuthRequest)request).setOrderID(request.getTransactionID());

			/*Code to read CSV file */
			
			boolean isDeviceAuthorized = readCSV("12345"); //Testing purpose machineID is given 12345
			
			if(!isDeviceAuthorized){
				return null;
			}
			
			//START CTAS INTEGRATION
			//get an instance of the deviceService
			deviceService = CTASDeviceActionService.getInstance();

			switch (request.getAuthorizationTransactionType()) {
			case CREDIT:
				
				//holds Base64Encrypted CC#
				encryptedCardNumber = null;	
				//holds CC expiration date
				String cardExpirationDate = null;				

				//using the deviceService get the MSR and CC Exp #
				encryptedCardNumber = deviceService.getMSR();		
				cardExpirationDate = deviceService.getExpDate();	
				
				//check CC# and CC exp date values
				System.out.println("encryptedCardNumber: " + encryptedCardNumber);
				System.out.println("\ncardExpirationDate: " + cardExpirationDate);

				//BEGIN MSR GET AND SET 
				//decode Base64 encryptedCardNumber to byteArray
				byte[] cardNumberInBytes = Base64.decode(encryptedCardNumber);
				//attempt a byte array to String decryption without the loop as seen in CoxFinancialAuthTechnician
				//and check value produced
				String decryptedCardNumber = new String(Base64.decode(encryptedCardNumber));
				System.out.println("\ndecryptedCardNumber: " + decryptedCardNumber);
				
				
				//START protocol used in CoxFinancialTechnician for converting base64 to 
				//byte array to String used prior to public key encryption
				StringBuffer cardNumber = new StringBuffer("");

				if (cardNumberInBytes != null) {
					for (int j = 0; j < cardNumberInBytes.length; j++) {
						cardNumber.append((char) cardNumberInBytes[j]);
					}
					
					//check value produced by protocol used in CoxFinancialAuthTechnician
					System.out.println("\ncardNumberAfterByteArrayConversion: " + cardNumber.toString());

					try {
						//public key encryption protocol - note public.key not in path and securityconstants.properties file
						paymentInfo.setCreditCardNumber(Encrypt.encrypt(cardNumber.toString()));
					} catch (SecurityException e) {
						logger.error("Unable to encrypt the card number"+e);
						e.printStackTrace();
					}
				}

				//BEGIN EXP DATE GET AND SET
				if(cardExpirationDate != null) {
					paymentInfo.setCardExpirationDate(cardExpirationDate);
				} else {
					System.out.println ("CC expiration date is null");
				}

				posInfo.setAccountInfo(acctInfo);

				String tenderType = "Visa";	//REVISIT: get this information from device service

				try {
					String cardtype= tenderType;

					System.out.println(cardtype);




					if (!GenUtils.isEmpty(tenderType))
					{
						if (tenderType.equalsIgnoreCase(
								CoxTenderAuthConstantsIfc.VISA_CARD)) {
							paymentInfo
							.setMethodOfPayment(EMethodOfPaymentType.VI);
						} else if ((tenderType
								.equalsIgnoreCase(
										CoxTenderAuthConstantsIfc.DISCOVER_CARD))) {
							paymentInfo
							.setMethodOfPayment(EMethodOfPaymentType.DI);
						} else if ((tenderType.equalsIgnoreCase(
								CoxTenderAuthConstantsIfc.AMERICAN_EXPRESS_CARD))) {
							paymentInfo
							.setMethodOfPayment(EMethodOfPaymentType.AX);
						} else if ((cardtype.trim()).equalsIgnoreCase(CoxTenderAuthConstantsIfc.MASTER_CARD)) 
						{
							paymentInfo.setMethodOfPayment(EMethodOfPaymentType.MC);
							System.out.println("Cardtype set");
						}

					} 
				} catch (EnumConstantNotPresentException e) {
					logger.error("Enum Exception");
					response = new AuthResponse(OFFLINE);

				}

				break;
				/**
				 * Modified for Cash Payment authorization from POS to Paymentech
				 * (ADS) and the defect Number is 4547
				 */
			case CASH:

				try {
					paymentInfo.setMethodOfPayment(EMethodOfPaymentType.CA);
				} catch (EnumConstantNotPresentException e) {
					logger.error("Enum Exception");
					response = new AuthResponse(OFFLINE);
				}
				break;
			case CHECK:

				try {
					paymentInfo.setMethodOfPayment(EMethodOfPaymentType.CK);
				} catch (EnumConstantNotPresentException e) {
					logger.error("Enum Exception");
					response = new AuthResponse(OFFLINE);
				}
				break;
			case MONEY_ORDER:

				try {
					paymentInfo.setMethodOfPayment(EMethodOfPaymentType.MO);
				} catch (EnumConstantNotPresentException e) {
					logger.error("Enum Exception");
					response = new AuthResponse(OFFLINE);
				}
				break;
			case TRAVELERS_CHECK:

				try {
					paymentInfo.setMethodOfPayment(EMethodOfPaymentType.TC);
				} catch (EnumConstantNotPresentException e) {
					logger.error("Enum Exception");
					response = new AuthResponse(OFFLINE);
				}
				break;
			default: {
				break;
			}
			}

			// set the Account Info
			//authorizeMessage.setAccountInfo(acctInfo);
			posInfo.setAccountInfo(acctInfo);

			// set the Amount

			BigInteger amt = (new BigDecimal(request.getPaymentAmount()).multiply(new BigDecimal(100))).toBigInteger();


			paymentInfo.setAmount(amt.abs().longValue());



			// set the Action code

			if (request.getTransactionType() == TenderAuthConstantsIfc.TRANS_SALE) {

				paymentInfo.setActionCode("AU");

			} else {
				paymentInfo.setActionCode("RF");
			}

			session.setPaymentInfo(paymentInfo);

			// we will be sending only one payment record at a time

			// so create a session array of only one element.

			Session[] sessionArray = new Session[1];

			sessionArray[0] = Session.Factory.newInstance();

			sessionArray[0] = session;

			posInfo.setSessionArray(sessionArray);//Changed by SN
			posInfo.setAccountInfo(acctInfo);

			// set the Transaction Type

			if (manualEntry) {

				orderInfo.setTransactionType(CoxTenderAuthConstantsIfc.ENTRY_METHOD_MANUAL);
			} else {
				orderInfo.setTransactionType(CoxTenderAuthConstantsIfc.ENTRY_METHOD_AUTO);
			}

			posInfo.setOrderInfo(orderInfo);

			posInfo.setSalesInfo(salesInfo);

			// TER 8120, Decommision modification, POS is not sending the Paymentech User credentials to WWS

			/*UsernameToken userNameToken = UsernameToken.Factory.newInstance();
    			userNameToken.setUsername("pos_store_0001");
    			userNameToken.setPassword("p0s4to63");  		

    			userNameToken.setCreated("");

    			posInfo.setUsernameToken(userNameToken);//Changed by Shiwali N*/

			authorizeMessage.setPosInfo(posInfo);
			authorizeMessage.setClient("POS");


			paymentAuthorization.setAuthorize(authorizeMessage);


			AuthorizationDocument authorizationDocument = AuthorizationDocument.Factory.newInstance();

			authorizationDocument.setAuthorization(paymentAuthorization);

			if (paymentAuthorization != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Printing the Request from the PaymenTech :\n");
					logger.debug(paymentAuthorization.xmlText());
				}
			}

			// Defect 4932 - Increasing the Time out
			long soTimeout = (long) (Long.parseLong(getPaymentechInterval()) *1000);

			System.out.println("req"+authorizationDocument);

			stub._getServiceClient().getOptions()
			.setTimeOutInMilliSeconds(soTimeout);

			outDocument = AuthorizationResponseDocument.Factory.newInstance();
			logger.debug("Paymenttech Authorization Request: "+authorizationDocument);

			outDocument = stub.authorization(authorizationDocument);

			System.out.println(outDocument);

			logger.debug("Paymenttech Authorization Response : "+outDocument);

			AuthorizationResponse authResponse = AuthorizationResponse.Factory
					.newInstance();

			deviceService.showAuthorization();

			authResponse = outDocument.getAuthorizationResponse();


			if (authResponse != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Printing the Response from the PaymenTech :\n");
					logger.debug("\nResp\n" + authResponse.xmlText());
				}

				Return responsemessage = Return.Factory
						.newInstance();

				responsemessage = authResponse.getReturn();

				ResponseSession responseSession = ResponseSession.Factory
						.newInstance();

				ResponseSession[] responseSessionArray = new ResponseSession[1];

				responseSessionArray = responsemessage
						.getResponseSessionArray();

				responseSession = responseSessionArray[0];

				response = new AuthResponse();

				// Grab the Response text

				// response.setResponseText(responseSession.getResponseMessage());



				// The Response code
				//((AuthRequest) request).isSiebelOrder())
				//Set the Ads Transaction Id in CoxTenderAuthResponse



				if (response.getAuthorisationResponseCode().equals(
						TenderAuthConstantsIfc.REFERRAL)) {
					response.setIccDetails("CALL CENTER");
				}

				response.setDateTime(formatAuthorizedDate(responseSession.getAuthResponseDate()));

				System.out.println();
				if (responseSession.getAuthVerificationCode() != null && responseSession.getAuthVerificationCode()!= "")
				{

					response.setApprovalCode(responseSession.getAuthVerificationCode());
					response.setApprovalCode(getResponseCode(responseSession.getResponseStatusCode(), responseSession.getResponseReasonCode(), false));
					response.setAuthorizationTransactionID(responseSession.getTransactionId());
					response.setAccountNumber(encryptedCardNumber);  				//REVISIT: Send last four digits of credit card.
					response.setAccountNumberToken(encryptedCardNumber); 			//REVISIT: Send last four digit of credit card. 
					response.setMaskedAccountNumber(encryptedCardNumber); 			//REVISIT: Send last four digit of credit card.
					response.setAuditTraceNumber(responseSession.getTransactionId());
					response.setAuthorizationCode(responseSession.getResponseStatusCode());
					response.setTraceNumber(responseSession.getResponseReasonCode());
					response.setTenderSubType("Visa");								//REVISIT: Collect and Send Card Type.
					response.setAuthorisationResponseCode(responseSession.getAuthVerificationCode());
					response.setResponseMessage(responseSession.getResponseMessage());
				}

				//response.setDateTime("");

			} else {
				response = null;
			}

			// end switch
		}


		catch (RemoteException fe) {
			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(fe));

			if (fe != null && fe.getMessage() != null
					&& fe.getMessage().equalsIgnoreCase("Read Timed out")) {

				response = new AuthResponse(TIMEOUT);
			} else {
				fe.printStackTrace();
				response = new AuthResponse(OFFLINE);
			}

		} 
		catch (DataExceptionException0 fe) {

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(fe));


			response = new AuthResponse(OFFLINE);

			fe.printStackTrace();
			logger.error(fe);
		}
		finally {
			logger.info("Paymenttech Authorization Response : "+outDocument);

			// clear the requestStringBuffer
			GenUtils.flushStringBuffer(requestStringBuffer);
			requestStringBuffer = null;
			// be sure to clear the formatter!
			// requestFormatter.clear();
		}
		return response;

	}

	
	public boolean readCSV(String machineID) {
		// TODO Auto-generated method stub
				
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/cox/ctas/service/loader/ApplicationContext.xml");      
        logger.info("Getting CTAS Properties");
        properties = (CTASProperties) applicationContext.getBean("CTASProperties");
        String csvFile = properties.getCsvFilePath();
        
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		try {			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] csvMachineIDs = line.split(cvsSplitBy,-1);
				for(int i=0; i<csvMachineIDs.length; i++){
					if(machineID.equalsIgnoreCase(csvMachineIDs[i])){
						System.out.println("Machine ID: " + csvMachineIDs[i] + " is Authorized");
						return true;
					}
				}
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}	 
		return false;
	  }

	
	/**
	 * 
	 * Send request to AIA for telecheck Sale Inquiry
	 * 
	 * @param request
	 * @return
	 * 
	 *//*
	public AuthResponse requestTeleCheckSaleInquiryAuthorization(
			AuthRequest request)  {


		AuthResponse response = new AuthResponse();
		EnumCheckType.Enum checkTypeCode = EnumCheckType.Enum.forString(request.getCheckType());//P
		MopEnum.Enum mop = MopEnum.Enum.forString(request.getMop());//request.getMop()  EC
		ChannelEnum.Enum channel = ChannelEnum.Enum.forString(request.getChannel()); //request.getChannel() //Changed by shiwali N
		SaleInquiryDocument reqDoc = SaleInquiryDocument.Factory.newInstance();
		SaleInquiry salesInquiry = SaleInquiry.Factory.newInstance();
		SalesRequest salesRequest = SalesRequest.Factory.newInstance();
		// TER 8120, Decommision modification, POS is not sending the MarketInciator to WWS

		salesRequest.setMerchantId(request.getMerchantID());
		salesRequest.setMerchantTraceId(request.getMerchantTraceID()); 

		com.cox.wireless.billing.cxf.service.UsernameToken uToken = com.cox.wireless.billing.cxf.service.UsernameToken.Factory.newInstance();

        	uToken.setUsername("paymentech");
        	uToken.setPassword("hcetnemyap");
       	    salesRequest.setUsernameToken(uToken);
		//salesRequest.setDivisionId("1");

		salesRequest.setCoxChannel(channel);

		salesRequest.setTotalCheckAmount(request.getAmount());//2106
		SaleInquiryResponseDocument resSaleDoc=null;

		try {
			salesRequest.setMicr(Encrypt
					.encrypt(request.getMicrData()));

			salesRequest.setManualId(Encrypt
					.encrypt(request.getManualID())); 
		} catch (SecurityException e) {
			logger.info("Error encrypting the manual id", e);
			e.printStackTrace();
		}

		salesRequest.setManualCheckNumber(request.getCheckNumber());
		salesRequest.setCheckType(checkTypeCode);
		salesRequest.setMicrReaderStatus(request.getEntryMethod());
		salesRequest.setMicrType(request.getMicrType());
		salesRequest.setCurrencyCode("840");
		salesRequest.setActionCode(request.getActionCode());
		salesRequest.setManualId(request.getManualID());   

		// TER 8120, Decommision modification, POS is sending the  ManualIDTypeID as String to WWS
		salesRequest.setManualIdType(request.getManualIDType());  //AS

		salesRequest.setTerminalId(request.getTerminalID());//Changed by shiwali N
		salesRequest.setDateTime(request.getDateTime());
		salesRequest.setIcomsAccountNumber(request.getICOMSAccountNumber());
		salesRequest.setSiebelordernumber("1"); 

		salesRequest.setMop(mop);
		salesRequest.setTransactionType(request.getCheckTransactionType());

		//Empty fields setting
		salesRequest.setCheckWriterPhoneNumber("");
		salesRequest.setCheckwriteState("");
		salesRequest.setClerkId("");
		salesRequest.setEchoData("");
		salesRequest.setProductCode("");
		salesRequest.setSupplementId("");
		salesRequest.setSupplementIdType("");
		salesRequest.setMICRSequenceNumber("");
		salesInquiry.setArg0(salesRequest);

		reqDoc.setSaleInquiry(salesInquiry);

		TeleCheckImplServiceStub stub = null;
		try {

			stub = new TeleCheckImplServiceStub(getTeleCheckServiceURL());

			resSaleDoc = SaleInquiryResponseDocument.Factory.newInstance();

			long soTimeout = (long) (Long.parseLong(getTeleCheckInquiryInterval()) *1000); 
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(soTimeout);


			System.out.println(reqDoc);
			logger.info(reqDoc.xmlText());
			resSaleDoc= stub.saleInquiry(reqDoc);
			logger.info(resSaleDoc.xmlText());

			SaleInquiryResponse resSale = resSaleDoc.getSaleInquiryResponse();

			if(logger.isDebugEnabled())
			{
				logger.debug("sale Inquiry Response is "+ resSaleDoc.xmlText());
			}

			System.out.println("Sale Inquiry Response:");
			System.out.println(resSale);


			SalesResponse salesInquiryResponse = resSale.getReturn();




			if(salesInquiryResponse!=null && !salesInquiryResponse.getResponseCode().equals(""))
			{
				response.setResponseText(salesInquiryResponse.getDisplayText());

			}
			else
			{
				throw new RemoteException("salesInquiryResponse is null");
			}

			response.setAuthorisationResponseCode(CoxTeleCheckResponseCodeUtility.getTeleCheckSaleInquiryStatus(salesInquiryResponse.getResponseCode(), salesInquiryResponse.getAchTransactionStatus(), logger));
			response.setTeleCheckTraceID(salesInquiryResponse.getTeleCheckTraceId());
			response.setDenialRecordNumber(salesInquiryResponse.getDenialRecordNumber());
			response.setReturnCheckFee(salesInquiryResponse.getReturnCheckFee());
			response.setReturnCheckNote(salesInquiryResponse.getReturnCheckNote());
			// Add the approval code
			response.setApprovalCode(salesInquiryResponse.getApprovalCode());

		}
		catch (RemoteException fe) {
			logger.info(resSaleDoc.xmlText());

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(fe));

			if (fe != null && fe.getMessage() != null
					&& fe.getMessage().equalsIgnoreCase("Read Timed out")) {

				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.TIMEOUT);
			} else {
				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
			}

		} catch (UnknownHostExceptionException2 e) {
			logger.info(resSaleDoc.xmlText());

			logger.error("Unkown host exception "+e );
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			UnknownHostExceptionDocument validExcDoc  = e.getFaultMessage();

			UnknownHostException ve = validExcDoc.getUnknownHostException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (IOExceptionException1 e) {
			logger.info(resSaleDoc.xmlText());

			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			IOExceptionDocument validExcDoc  = e.getFaultMessage();

			IOException ve = validExcDoc.getIOException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (ValidationExceptionException0 e) {
			logger.info(resSaleDoc.xmlText());

			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			ValidationExceptionDocument validExcDoc  = e.getFaultMessage();

			ValidationException ve = validExcDoc.getValidationException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}


		if (logger.isDebugEnabled()) {
			logger.debug("Returning authorization response:\n" + response);
		}
		return response;
	}

	  *//**
	  * Send request to AIA for telecheck Status Inquiry
	  * @param request
	  * @return
	  * @throws RemoteException 
	  * @throws ProcessStatusInquiryTelecheckProvABCSImplFaultMessage 
	  *//*
	public AuthResponse requestTeleCheckStatusInquiryAuthorization(
			AuthRequest request) {

		AuthResponse response = new AuthResponse();


		StatusInquiryDocument reqDoc = StatusInquiryDocument.Factory.newInstance();
		StatusInquiry statusInquiry = StatusInquiry.Factory.newInstance();

		StatusRequest statusRequest = StatusRequest.Factory.newInstance();

		MopEnum.Enum mop = MopEnum.Enum.forString(request.getMop());

		statusRequest.setMerchantId(request.getMerchantID());
		statusRequest.setTeleCheckTraceId(request.getTeleCheckTraceID());
		statusRequest.setAchAction(request.getAchAction());
		statusRequest.setDateTime(request.getDateTime());
		statusRequest.setEchodata(request.getEchoData());
		statusRequest.setMop(mop);

		statusInquiry.setArg0(statusRequest);
		reqDoc.setStatusInquiry(statusInquiry);


		System.out.println("Telecheck Status Inquiry Request:");
		System.out.println(reqDoc);

		TeleCheckImplServiceStub stub = null;
		StatusInquiryResponse saleInquiryResponse=null;
		StatusInquiryResponseDocument resDoc = null;
		try
		{
			stub = new TeleCheckImplServiceStub(
					getTeleCheckServiceURL());

			resDoc = StatusInquiryResponseDocument.Factory
					.newInstance();


			long soTimeout = (long) (Long.parseLong(getTeleCheckInquiryInterval()) *1000); // 90 sec
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(soTimeout);

			logger.info(reqDoc.xmlText());
			resDoc=stub.statusInquiry(reqDoc);
			logger.info(resDoc.xmlText());

			System.out.println(resDoc);

			saleInquiryResponse = resDoc.getStatusInquiryResponse();
			logger.info(saleInquiryResponse.xmlText());


			StatusResponse statusResponse = saleInquiryResponse.getReturn();



			if(statusResponse!=null)
			{
				//set ADS Transaction ID
				response.setAdsTransactionId(statusResponse.getEchodata());
				response.setResponseText(statusResponse.getSupplementalResponseMessage());
				response.setAuthorisationResponseCode(CoxTeleCheckResponseCodeUtility.
						getTeleCheckStatusInquiryStatus(statusResponse.getResponseCode(), logger));

			}
			else
			{
				throw new RemoteException("statusResponse is null");
			}
		}
		catch (RemoteException fe) {
			logger.info(resDoc.xmlText());
			logger.error(saleInquiryResponse);
			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(fe));

			if (fe != null && fe.getMessage() != null
					&& fe.getMessage().equalsIgnoreCase("Read Timed out")) {

				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.TIMEOUT);
			} else {
				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);


	   * to be removed

				fe.printStackTrace();
				logger.error(fe);
			}


		} catch (UnknownHostExceptionException2 e) {
			logger.info(resDoc.xmlText());
			logger.error(saleInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));

			UnknownHostExceptionDocument validExcDoc  = e.getFaultMessage();

			UnknownHostException ve = validExcDoc.getUnknownHostException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (IOExceptionException1 e) {
			logger.info(resDoc.xmlText());
			logger.error(saleInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));

			IOExceptionDocument validExcDoc  = e.getFaultMessage();

			IOException ve = validExcDoc.getIOException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (ValidationExceptionException0 e) {
			logger.info(resDoc.xmlText());
			logger.error(saleInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));

			ValidationExceptionDocument validExcDoc  = e.getFaultMessage();

			ValidationException ve = validExcDoc.getValidationException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}
		catch (Exception e) {
			logger.info(resDoc.xmlText());
			logger.error(saleInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));

			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}


		if (logger.isDebugEnabled()) {
			logger.debug("Returning authorization response:\n" + response);

		}

		return response;
	}

	   *//**
	   * Send request to AIA for telecheck Adjustment Inquiry
	   * @param request
	   * @return
	   * @throws RemoteException 
	   * @throws ProcessAdjustmentInquiryTelecheckProvABCSImplFaultMessage 
	   *//*
	public AuthResponse requestTeleCheckAdjustmentInquiryAuthorization(
			AuthRequest request)  {


		AuthResponse response = new AuthResponse();

		AdjustmentInquiryDocument reqDoc = AdjustmentInquiryDocument.Factory.newInstance();
		AdjustmentInquiry adjustmentInquiry = AdjustmentInquiry.Factory.newInstance();

		AdjustmentRequest adjustmentRequest = AdjustmentRequest.Factory.newInstance();

		MopEnum.Enum mop = MopEnum.Enum.forString(request.getMop());

		adjustmentRequest.setMerchantTraceId(request.getMerchantTraceID());
		adjustmentRequest.setTeleCheckTraceId(request.getTeleCheckTraceID());
		adjustmentRequest.setAmount(request.getAmount());
		adjustmentRequest.setMop(mop);//CK
		adjustmentRequest.setTransactionType(request.getCheckTransactionType());
		adjustmentRequest.setMerchantId(request.getMerchantID());


		adjustmentInquiry.setArg0(adjustmentRequest);
		reqDoc.setAdjustmentInquiry(adjustmentInquiry);

		TeleCheckImplServiceStub stub = null;
		AdjustmentInquiryResponse adjustmentInquiryResponse =null;
		AdjustmentInquiryResponseDocument resDoc = AdjustmentInquiryResponseDocument.Factory
				.newInstance();
		try
		{
			stub = new TeleCheckImplServiceStub(
					getTeleCheckServiceURL());



			long soTimeout = (long) (Long.parseLong(getTeleCheckInquiryInterval()) *1000); // 30 sec
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(soTimeout);

			System.out.println("Telecheck Adjustment Inquiry Request :"+ reqDoc);
			logger.info(reqDoc.xmlText());
			resDoc=stub.adjustmentInquiry(reqDoc);
			System.out.println("Telecheck Adjustment Inquiry Response :"+ resDoc);
			logger.info(resDoc.xmlText());
			adjustmentInquiryResponse = resDoc.getAdjustmentInquiryResponse();
			logger.info(adjustmentInquiryResponse);
			AdjustmentResponse adjustmentResponse = adjustmentInquiryResponse.getReturn();

			if(adjustmentResponse!=null)
			{
				response.setResponseText(adjustmentResponse.getSupplementalResponseCode());
				response.setAuthorisationResponseCode(CoxTeleCheckResponseCodeUtility
						.getTeleCheckAdjustmentInquiryStatus(adjustmentResponse.getResponseCode(),logger));
				response.setTeleCheckTraceID(adjustmentResponse.getTeleCheckTraceId());
				response.setReturnFees(adjustmentResponse.getReturnCheckFee());
			}
			else
			{
				throw new RemoteException("statusResponse is null");
			}
		}
		catch (RemoteException fe) {
			logger.info(resDoc.xmlText());
			logger.error(adjustmentInquiryResponse);
			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(fe));

			if (fe != null && fe.getMessage() != null
					&& fe.getMessage().equalsIgnoreCase("Read Timed out")) {

				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.TIMEOUT);
			} else {
				response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
			}


		} catch (UnknownHostExceptionException2 e) {
			logger.info(resDoc.xmlText());
			logger.error(adjustmentInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			UnknownHostExceptionDocument validExcDoc  = e.getFaultMessage();

			UnknownHostException ve = validExcDoc.getUnknownHostException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (IOExceptionException1 e) {
			logger.info(resDoc.xmlText());
			logger.error(adjustmentInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			IOExceptionDocument validExcDoc  = e.getFaultMessage();

			IOException ve = validExcDoc.getIOException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}catch (ValidationExceptionException0 e) {
			logger.info(resDoc.xmlText());
			logger.error(adjustmentInquiryResponse);
			logger.error(e);
			e.printStackTrace();

			logger.error("Error formatting authorization data:\n"
					+ GenUtils.throwableToString(e));


			ValidationExceptionDocument validExcDoc  = e.getFaultMessage();

			ValidationException ve = validExcDoc.getValidationException();

			logger.error("sale Response for formatting the authorization: " + ve.xmlText());
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}
		catch (Exception e) {
			logger.info(resDoc.xmlText());
			logger.error(adjustmentInquiryResponse);
			logger.error(e);
			e.printStackTrace();
			response.setAuthorisationResponseCode(TenderAuthConstantsIfc.OFFLINE);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Returning authorization response:\n" + response);
		}
		return response;
	}*/

	/**
	 * gets the value held in teleCheckInquiryInterval
	 * 
	 * @return the teleCheckInquiryInterval
	 */
	public String getTeleCheckInquiryInterval() {
		return teleCheckInquiryInterval;
	}

	/**
	 * sets the teleCheckInquiryInterval value
	 * 
	 * @param teleCheckInquiryInterval the teleCheckInquiryInterval to set
	 */
	public void setTeleCheckInquiryInterval(String teleCheckInquiryInterval) {
		this.teleCheckInquiryInterval = teleCheckInquiryInterval;
	}

	/**
	 * gets the value held in teleCheckServiceURL
	 * 
	 * @return the teleCheckServiceURL
	 */
	public String getTeleCheckServiceURL() {
		return teleCheckServiceURL;
	}

	/**
	 * sets the teleCheckServiceURL value
	 * 
	 * @param teleCheckServiceURL the teleCheckServiceURL to set
	 */
	public void setTeleCheckServiceURL(String teleCheckServiceURL) {
		this.teleCheckServiceURL = teleCheckServiceURL;
	}

	
	/**
	 * 
	 * @param request
	 */
	public void enqueueReversalRequest(AuthRequest request) {
		// TODO Auto-generated method stub

	}


	/*public CoxItemActivationResponse requestActivation(
				CoxItemActivationRequest request) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}*/

	/**
	 * 
	 * @param workstationID
	 */
	public void clientRestarted(String workstationID) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 * @param workstationID
	 */
	public void transactionCompleted(String workstationID) {
		// TODO Auto-generated method stub
	}

	/**
	 * gets the value held in paymentechInterval
	 * 
	 * @return the paymentechInterval
	 */
	public String getPaymentechInterval() {
		return paymentechInterval;
	}

	/**
	 * sets the paymentechInterval value
	 * 
	 * @param paymentechInterval the paymentechInterval to set
	 */
	public void setPaymentechInterval(String paymentechInterval) {
		this.paymentechInterval = paymentechInterval;
	}

	public CTASProperties getProperties() {
		return properties;
	}

	public void setProperties(CTASProperties properties) {
		this.properties = properties;
	}
	
}

