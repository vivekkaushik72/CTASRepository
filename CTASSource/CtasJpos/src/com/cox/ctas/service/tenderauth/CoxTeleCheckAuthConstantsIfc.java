/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 ************************************************************/

package com.cox.ctas.service.tenderauth;

//-------------------------------------------------------------------------
/**
	   Defines constants for key values involved in the Check Authorization process.

	   @version $Revision: 7$ - duplicate of constants file found in POS 
	   
	   cox.retail.stores.domain.manager.tenderauth package
 **/
//-------------------------------------------------------------------------



public interface CoxTeleCheckAuthConstantsIfc {

	//-------------------------------------------------------------------------
	/**
		        Authorization request types
		        Valid values for Request Authorization
	 **/
	//-------------------------------------------------------------------------

	// Approved 

	public static final int TELECHECK_APPROVED_ECHECK  = 7;

	public static final String TELECHECK_APPROVED_CHECK    = "07";

	public static final int TELECHECK_PREFERRED_APPROVED_DEPOSITEDCHECK = 95;


	// Declined Messages

	public static final int TELECHECK_REJECTED  = 8;

	public static final int TELECHECK_LOST_CHECK = 73;

	public static final int TELECHECK_REJECTED_CODE3 = 88;


	// Call center referrals

	public static final int TELECHECK_SUBSCRIBER_NOT_EXIST  = 3;

	public static final int TELECHECK_RISK_REFERRAL    = 9;

	public static final int TELECHECK_SUBSCRIBER_NOT_ACTIVE = 41;

	public static final int TELECHECK_CALL_CENTER   = 69;

	public static final int TELECHECK_PROMPT_2NDID  = 83;

	public static final int TELECHECK_2ND_MICRREAD  = 84;

	public static final int TELECHECK_2ND_MICRREAD_AND_2NDID  = 85;

	public static final int TELECHECK_ID_NOTIN_DL_DB = 89;


	// Retry messages

	public static final int TELECHECK_CANNOT_BE_BLANK  = 15;

	public static final int TELECHECK_COMPUTER_ERROR  = 16;

	public static final int TELECHECK_PROCESSOR_NOT_AVAILABLE = 49;

	public static final int TELECHECK_INVALID_MICR_DATA  = 98;

	public static final int TELECHECK_INVALID_VALUE_FOR_FIELD = 27;

	public static final int TELECHECK_INVALID_RT  = 78;

	//Timeout message

	public static final int TELECHECK_TIMEOUT   = 97;




	//****Sale Response ACH Transaction Status****************

	/**
	 * ACH Transaction Status - E-Check Approved
	 */
	public static final String ACH_TRANSACTION_E_CHECK_APPROVED = "1";

	/**
	 * ACH Transaction Status - Paper Check
	 */

	public static final String ACH_TRANSACTION_PAPER_CHECK_APPROVED = "0";


	/**
	 * ACH Transaction Status - CODE 3 Declined 
	 */

	public static final String ACH_TRANSACTION_CODE3_DECLINE  = "3";

	/**
	 * ACH Transaction Status - CODE 4 Declined
	 */

	public static final String ACH_TRANSACTION_CODE4_DECLINE = "4";


	/**
	 * ACH TRANSACTION Status - Not Received
	 */

	public static final String ACH_TRANSACTION_NOT_APPLICABLE = "-";

	// Adjustment Responses

	public static final int TELECHECK_ADJ_ACCEPTED  = 26;

	public static final int TELECHECK_ADJ_REFUND_NOT_ALLOWED = 46;

	public static final int TELECHECK_ORIG_TXN_NOT_APPROVED = 79;

	public static final int TELECHECK_INVALID_AMT = 80;

	public static final int TELECHECK_UNABLE_TO_LOCATE_ORIG_TXN = 81;


	//Duplicate
	public static final int TELECHECK_DUPLICATE_TXN = 82;

	//************Status Inquiry Response codes **************************

	// Accepted and processed by Telecheck status

	public static final String TELECHECK_STATUS_ACCEPTED_PROCESSED = "OK";

	// Accepted but not necessarily processed by Tele check

	public static final String TELECHECK_STATUS_ACCEPTED= "ACK";


	// Tele check status not processed due to General Error

	public static final String TELECHECK_STATUS_GENERAL_ERROR = "NAK";

	// Telecheck Status Scheduled Maintenance

	public static final String TELECHECK_STATUS_SCHEDULED_MAINTENANCE = "49";

	// Telecheck status invalid data

	public static final String TELECHECK_STATUS_INVALID_DATA = "27";


	// Telecheck status invalid original Transaction

	public static final String TELECHECK_STATUS_INVALID_ORIGINAL_TXN = "E3";


	// Telecheck status Time out

	public static final String TELECHECK_STATUS_TIMEOUT = "97";

	//**************Status Inquiry Response codes *******************


	//Sale Status Inquiry
	public static final String MICR_DATA_ENTRY_METHOD_AUTO="09";

	public static final String MICR_READER_STATUS_ENTRY_METHOD_AUTO="15";

	public static final String  CHECK_ENTRY_METHOD_AUTO="R";

	public static final String MICR_DATA_ENTRY_METHOD_MANUAL="18";

	public static final String MICR_READER_STATUS_ENTRY_METHOD_MANUAL="9";

	public static final String  CHECK_ENTRY_METHOD_MANUAL="1";

	public static final String  CHECK_TRANSACTION_TYPE="V";

	public static final String  CHANNEL="Pos";

	//Method of payment
	public static final String  TELECHECK_E_CHECK="EC";

	public static final String  TELECHECK_DEPOSITED_CHECK="CK";

	//Signature Verification ACH Action

	public static final String  SIGNATURE_DECLINED="D";

	public static final String  SIGNATURE_APPRROVED="A";


	// Default Text

	public static final String DEFAULT_RETURN_CHECK_FEE = "Maximum Fee allowed by state law";


	// Default ID Type

	public static final String DEFAULT_ID_TYPE=  "DriversLicense";


	// Military ID 

	public static final String MILITARY_ID = "MilitaryID";

}

