/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 ************************************************************/
package com.cox.ctas.service.tenderauth;

import org.apache.log4j.Logger;

//-------------------------------------------------------------------------
/**
	   Evaluates TeleCheck response codes and returns POS corresponding values

	   - duplicate of file found in POS
	   
	    cox.retail.stores.domain.manager.tenderauth.telecheck package
**/
//-------------------------------------------------------------------------

public class CoxTeleCheckResponseCodeUtility implements CoxTeleCheckAuthConstantsIfc {

	/**
	 * 
	 * Send status of telecheck for sale inquiry
	 * 
	 * @param responseCodeStr
	 * @return Status
	 */
	public static String getTeleCheckSaleInquiryStatus(String responseCodeStr,String achTransactionStatus,
			Logger logger) {


		String responseCodes = CoxTenderAuthConstantsIfc.OFFLINE;

		int responseCode = 0;

		try {

			responseCode = Integer.parseInt(responseCodeStr);

		} catch (NumberFormatException e) {

			logger.error("Unable to format the Response string");

			return responseCodes;
		}


		// Check the ACH Transaction Status
		if (achTransactionStatus != null
				&& achTransactionStatus
				.equals(ACH_TRANSACTION_E_CHECK_APPROVED))
			responseCodes = CoxTenderAuthConstantsIfc.APPROVED_E_CHECK;
		else if (achTransactionStatus != null
				&& achTransactionStatus
				.equals(ACH_TRANSACTION_PAPER_CHECK_APPROVED))
			responseCodes = CoxTenderAuthConstantsIfc.APPROVED_DEPOSITED_CHECK;
		/*else if (achTransactionStatus != null
				&& (achTransactionStatus.equals(ACH_TRANSACTION_CODE3_DECLINE) || (achTransactionStatus
						.equals(ACH_TRANSACTION_CODE4_DECLINE))))
			responseCodes = TenderAuthConstantsIfc.DECLINED;*/
		else {

			// This is an error case scenario. Let's deal one by one.

			switch (responseCode) {

			//Response code for decline 8, 73, 88

			// The code may not reach here as the ACH Transaction Status with Code 3 or 4 Declined
			// should already be taken care of

			case TELECHECK_REJECTED:
			case TELECHECK_LOST_CHECK:
			case TELECHECK_REJECTED_CODE3:

				responseCodes = TenderAuthConstantsIfc.DECLINED;

				break;
				//Response code for Preferred. 			
				/*	
			case TELECHECK_APPROVED_ECHECK:

				responseCodes = TenderAuthConstantsIfc.APPROVED_E_CHECK;

				break;
				//Response approved as  DepositedCheck 95
			case TELECHECK_PREFERRED_APPROVED_DEPOSITEDCHECK:

				responseCodes = TenderAuthConstantsIfc.APPROVED_DEPOSITED_CHECK;

				break;*/




				//Response Call Center for 3,9,41,69,89			

				// 03
			case TELECHECK_SUBSCRIBER_NOT_EXIST:				
				// 09
			case TELECHECK_RISK_REFERRAL:				
				// 41
			case TELECHECK_SUBSCRIBER_NOT_ACTIVE:
				// 69
			case TELECHECK_CALL_CENTER:		
				// 89
			case TELECHECK_ID_NOTIN_DL_DB:

				responseCodes = TenderAuthConstantsIfc.CALL_CENTER;
				break;

				// Retry messages
				//Response Retry messages	15,16,27,49,78,83,84,85,98

				//15
			case TELECHECK_CANNOT_BE_BLANK:
				// 16
			case TELECHECK_COMPUTER_ERROR:
				// 27
			case TELECHECK_INVALID_VALUE_FOR_FIELD:						
				// 49
			case TELECHECK_PROCESSOR_NOT_AVAILABLE:				
				// 78				
			case TELECHECK_INVALID_RT:				
				// 83
			case TELECHECK_PROMPT_2NDID:				
				// 84
			case TELECHECK_2ND_MICRREAD:				
				// 85
			case TELECHECK_2ND_MICRREAD_AND_2NDID:				
				// 98
			case TELECHECK_INVALID_MICR_DATA:

				responseCodes = TenderAuthConstantsIfc.ERROR_RETRY;
				break;

				//Duplicate message  	82
			case TELECHECK_DUPLICATE_TXN:
				responseCodes = TenderAuthConstantsIfc.DUPLICATE;
				break;

				//Time out 	 97
			case TELECHECK_TIMEOUT:				
				responseCodes = TenderAuthConstantsIfc.TIMEOUT;				
				break;

			default:
				responseCodes = TenderAuthConstantsIfc.CALL_CENTER;
				break;

			}
		}

		return responseCodes;
	}

	/**
	 * 
	 * Send status of telecheck for status inquiry
	 * Treat it as approved only if it is accepted and processed.
	 * Other wise it can only be Declined
	 * 
	 * @param responseCodeStr
	 * @param logger
	 * @return
	 */
	public static String getTeleCheckStatusInquiryStatus(
			String responseCodeStr, Logger logger) {

		if (responseCodeStr != null && responseCodeStr.length() > 0) {

			if (responseCodeStr
					.equalsIgnoreCase(TELECHECK_STATUS_ACCEPTED_PROCESSED) || responseCodeStr
					.equalsIgnoreCase(TELECHECK_APPROVED_CHECK))

				return TenderAuthConstantsIfc.APPROVED;

			else if (responseCodeStr
					.equalsIgnoreCase(TELECHECK_STATUS_ACCEPTED))

				return TenderAuthConstantsIfc.DECLINED;

			else if (responseCodeStr
					.equalsIgnoreCase(TELECHECK_STATUS_GENERAL_ERROR))

				return TenderAuthConstantsIfc.DECLINED;

			else if (responseCodeStr
					.equals(TELECHECK_STATUS_SCHEDULED_MAINTENANCE))

				return TenderAuthConstantsIfc.DECLINED;

			else if (responseCodeStr.equals(TELECHECK_STATUS_INVALID_DATA))

				return TenderAuthConstantsIfc.DECLINED;

			else if (responseCodeStr
					.equals(TELECHECK_STATUS_INVALID_ORIGINAL_TXN))

				return TenderAuthConstantsIfc.DECLINED;

			else if (responseCodeStr.equals(TELECHECK_STATUS_TIMEOUT))

				return TenderAuthConstantsIfc.DECLINED;

		}

		return TenderAuthConstantsIfc.DECLINED;

	}




	/**
	 * Send status of telecheck for adjustment inquiry
	 * 
	 * @param responseCodeStr
	 * @param logger
	 * @return Status
	 */
	public static String getTeleCheckAdjustmentInquiryStatus(
			String responseCodeStr, Logger logger) {

		String responseCodes = TenderAuthConstantsIfc.DECLINED;

		int responseCode = 0;

		if (responseCodeStr != null && responseCodeStr.length() > 0) {

			if (responseCodeStr.equals(TELECHECK_STATUS_ACCEPTED_PROCESSED))

				return TenderAuthConstantsIfc.APPROVED;

			else {
				try {

					responseCode = Integer.parseInt(responseCodeStr);

				} catch (NumberFormatException e) {
					logger
					.error("Unable to format the Status inquiry Response string");
					return responseCodes;
				}

				switch (responseCode) {

				case TELECHECK_ADJ_ACCEPTED:

					responseCodes = TenderAuthConstantsIfc.APPROVED;

					break;

				case TELECHECK_ADJ_REFUND_NOT_ALLOWED:
				case TELECHECK_ORIG_TXN_NOT_APPROVED:
				case TELECHECK_INVALID_AMT:
				case TELECHECK_UNABLE_TO_LOCATE_ORIG_TXN:
				default:
					responseCodes = TenderAuthConstantsIfc.DECLINED;
					break;
				}
			}
		}

		return responseCodes;
	}
}

