/* ===========================================================================
 * Copyright (c) 1998, 2012, Oracle and/or its affiliates. All rights reserved. 
 * ===========================================================================
 * $Header: rgbustores/modules/domain/src/oracle/retail/stores/domain/manager/tenderauth/TenderAuthConstantsIfc.java /rgbustores_13.3x_generic_branch/2 2012/09/11 08:56:04 spurkaya Exp $
 * ===========================================================================
 * NOTES
 * <other useful comments, qualifications, etc.>
 *
 * MODIFIED    (MM/DD/YY)
 *    spurkaya  09/07/12 - Added constant for offline system approved
 *                         transactions and renamed constant to depict offline
 *                         systen approved returns
 *    spurkaya  08/01/12 - Added constant to indicate offline referral for
 *                         returns
 *    cgreene   05/27/10 - convert to oracle packaging
 *    cgreene   05/26/10 - convert to oracle packaging
 *    asinton   03/12/10 - Added constant for MagSwipe card entry.
 *    abondala  01/03/10 - update header date
 *    asinton   12/03/09 - Changes to support credit card authorizations on
 *                         returns and voids.
 *    mchellap  06/23/09 - Added GiftCard Status Properties
 *
 * ===========================================================================
 * $Log:
 *    7    360Commerce 1.6         6/19/2008 4:25:29 PM   Maisa De Camargo CR
 *         32126 - Setting a Default Timeout for Authorization Requests. Code
 *         Reviewed by Jack Swan.
 *    6    360Commerce 1.5         5/7/2008 8:55:11 PM    Alan N. Sinton  CR
 *         30295: Code modified to present  Function Unavailable dialog for
 *         House Account and Instant Credit when configured with ISD.  Code
 *         reviewed by Anda Cadar.
 *    5    360Commerce 1.4         5/5/2008 5:14:01 PM    Alan N. Sinton  CR
 *         31577: Added response value to TenderAuthConstantsIfc.java and code
 *          to ISDITKResponseFormatter.java to reflect a duplicate card error.
 *    4    360Commerce 1.3         8/23/2007 3:23:06 PM   Jack G. Swan    Fix
 *         for 28,330 - cannot tender with a gift card off-line
 *    3    360Commerce 1.2         3/31/2005 4:30:21 PM   Robert Pearse
 *    2    360Commerce 1.1         3/10/2005 10:25:52 AM  Robert Pearse
 *    1    360Commerce 1.0         2/11/2005 12:14:47 PM  Robert Pearse
 *
 *   Revision 1.17  2004/08/23 16:15:45  cdb
 *   @scr 4204 Removed tab characters
 *
 *   Revision 1.16  2004/07/20 14:05:07  epd
 *   @scr 4268 Some minor refactoring and overhaul of gift card functionality in auth simulator
 *
 *   Revision 1.15  2004/06/21 23:12:57  kmcbride
 *   Adding an intial cut of ISD auth for gift card.
 *
 *   Revision 1.14  2004/06/04 19:29:46  lzhao
 *   @scr 4264: Return gift card.
 *
 *   Revision 1.13  2004/05/11 16:03:39  blj
 *   @scr 4603 -fixed gift card post void for issue/reload/credit/redeem
 *
 *   Revision 1.12  2004/04/14 20:07:37  lzhao
 *   @scr 3872 Redeem, change gift card request type from String to in.
 *
 *   Revision 1.11  2004/04/13 19:03:11  lzhao
 *   @scr 3872: gift card redeem.
 *
 *   Revision 1.10  2004/04/07 20:56:49  lzhao
 *   @scr 4218: add gift card info for summary report.
 *
 *   Revision 1.9  2004/03/31 16:12:30  lzhao
 *   @scr 3872 change inactive to deactive.
 *
 *   Revision 1.8  2004/03/30 20:44:34  lzhao
 *   @scr 3872: add gift card redeem
 *
 *   Revision 1.7  2004/03/25 22:57:19  lzhao
 *   @scr 3872 Redeem: Modify tags for gift card redeem request
 *
 *   Revision 1.6  2004/03/23 15:30:00  lzhao
 *   @scr 3872 gift card redeem constants.
 *
 *   Revision 1.5  2004/03/23 15:21:08  lzhao
 *   @scr 3872 add constants for gift card redeem.
 *
 *   Revision 1.3  2004/02/25 00:46:57  bwf
 *   @scr 3883 Credit Rework.
 *
 *   Revision 1.2  2004/02/12 17:14:16  mcs
 *   Forcing head revision
 *
 *   Revision 1.1.1.1  2004/02/11 01:04:33  cschellenger
 *   updating to pvcs 360store-current
 *
 *
 *
 *    Rev 1.8   Feb 10 2004 15:08:18   blj
 * added gift card credit tag
 *
 *    Rev 1.7   Dec 16 2003 10:13:10   lzhao
 * code review follow up
 *
 *    Rev 1.6   Dec 04 2003 16:50:52   epd
 * updates for debit auth
 *
 *    Rev 1.5   Nov 21 2003 08:43:22   lzhao
 * add several more return code for activation/authorization
 * Resolution for 3371: Feature Enhancement:  Gift Card Enhancement
 *
 *    Rev 1.4   Nov 13 2003 18:31:38   lzhao
 * add more types for gift card activation and authorization
 *
 *    Rev 1.3   Nov 04 2003 17:56:02   epd
 * added error_retry for authorization
 *
 *    Rev 1.2   Oct 31 2003 10:48:30   nrao
 * Added entry for Instant Credit Enrollment.
 *
 *    Rev 1.1   Oct 30 2003 10:49:26   lzhao
 * add attributes for gift card reload.
 *
 *    Rev 1.0   Aug 29 2003 15:38:42   CSchellenger
 * Initial revision.
 *
 *    Rev 1.3   May 09 2003 11:11:18   HDyer
 * Added gift card constants for manual activation/deactivation. This is to simulate the phone call actions that would take place at the authorizer.
 * Resolution for POS SCR-2128: Gift Card - Continuous Loop after receiving Manual Activation Screen
 *
 *    Rev 1.2   Mar 27 2003 12:40:34   pjf
 * Deprecation cleanup.
 *
 *    Rev 1.1   Jun 11 2002 13:55:10   HDyer
 * SCR 1721: Add Soft Decline Override feature. Added constant for check velocity.
 *
 *    Rev 1.0   Jun 03 2002 17:00:00   msg
 * Initial revision.
 *
 *    Rev 1.2   May 30 2002 05:54:42   mpm
 * Added max transaction-type constant.
 *
 *    Rev 1.1   Mar 18 2002 23:06:20   msg
 * - updated copyright
 *
 *    Rev 1.0   Mar 18 2002 12:25:40   msg
 * Initial revision.
 *
 *    Rev 1.0   Sep 20 2001 16:17:06   msg
 * Initial revision.
 *
 *    Rev 1.1   Sep 17 2001 12:38:26   msg
 * header update
 * ===========================================================================
 */
//-------------------------------------------------------------------------
/**
	   Defines constants for key values involved in the Tender Authorization process.

	   @version $Revision: /rgbustores_13.3x_generic_branch/2 $
 **/
//-------------------------------------------------------------------------
package com.cox.ctas.service.tenderauth;

//-------------------------------------------------------------------------
/**
	   duplicate of constants file found in ORPOS - base inherited tender constants
	   
	   class used in Cox ORPOS implementation. Needed here to support the CoxTenderAuth
	   
	   class which is being modified to allow for tender auths within the Service -
	   
	   see oracle.retail.stores.domain.manager.tenderauth
**/
//-------------------------------------------------------------------------

public interface TenderAuthConstantsIfc {

	//-------------------------------------------------------------------------
	/**
	        Authorization request types
	        Valid values for TenderAuthRequest.requestType
	 **/
	//-------------------------------------------------------------------------
	public static final int CREDIT = 1;
	public static final int CHECK = 2;
	public static final int DEBIT = 3;
	public static final int GIFT_CARD = 4;
	public static final int HOUSE_ACCOUNT = 5;
	public static final int ITEM_ACTIVATION = 6;
	public static final int INSTANT_CREDIT = 7;

	// KLM (6/2004): Adding these in support of pulling in the Bose
	// ISD work from services.
	//
	/** Authorization request types.
	 * Valid values used by the request formatters.
	 * */
	public static final String CREDIT_TYPE = "01";
	public static final String DEBIT_TYPE = "03";
	public static final String CHECK_TYPE = "02";
	public static final String GIFT_CARD_TYPE = "04";
	public static final String HOUSE_ACCOUNT_TYPE = "05";
	public static final String ITEM_ACTIVATION_TYPE = "06";
	public static final String INSTANT_CREDIT_TYPE = "07";
	public static final String BOSE_CREDITAPP_TYPE = "08";

	//-------------------------------------------------------------------------
	/**
	        Time out processing flag values
	        Valid values for TenderAuthRequest.timeOutBehavior
	 **/
	//-------------------------------------------------------------------------
	public static final int UNUSED = 0;
	public static final int PROCESS = 1;
	public static final int DISCARD = 2;
	//-------------------------------------------------------------------------
	/**
	        Transaction (i.e., "authorization") types
	        Valid values for TenderAuthRequest.transactionType
	 **/
	//-------------------------------------------------------------------------
	public static final int TRANS_SALE = 1;
	public static final int TRANS_VOID = 2;
	public static final int TRANS_CREDIT = 3;
	public static final int TRANS_CREDIT_VOID = 4;
	public static final int TRANS_AUTH_ONLY = 5;
	public static final int TRANS_FORCE = 6;
	public static final int TRANS_SETTLEMENT = 7;
	public static final int TRANS_GUARANTEE_DL = 11;
	public static final int TRANS_GUARANTEE_MICR = 12;
	public static final int TRANS_GUARANTEE_DL_AND_MICR = 13;
	/** Constant to indicate offline referral. */
	public static final int TRANS_SALE_OFFLINE_REFERRAL = 14;
	/** Constant to indicate online referral. */
	public static final int TRANS_SALE_ONLINE_REFERRAL = 15;
	/** Constant to indicate offline system approved returns. */
	public static final int TRANS_RETURN_OFFLINE_SYSTEM_APPROVED = 16;
	/** Constant to indicate offline system approved transactions. */
	public static final int TRANS_SALE_OFFLINE_SYSTEM_APPROVED = 17;
	public static final int MAX_TRANS_CONSTANT = 15;
	//-------------------------------------------------------------------------
	/**
	        Response codes and the corresponding descriptors
	        Valid values for TenderAuthResponse.responseCode
	 **/
	//-------------------------------------------------------------------------
	/**
	 * ISD "gift card" server configured to give "00" as response code for
	 * already active cards when attempting to activate
	 */
	public static final String ALREADY_ACTIVE = "00";
	/**
	 * Approved response code is configured by the ISD to return the "01".
	 */
	public static final String APPROVED = "01";
	public static final String DECLINED = "02";
	public static final String REFERRAL = "03";
	public static final String POSITIVE_ID = "04";
	public static final String TIMEOUT = "08";
	public static final String OFFLINE = "09";
	public static final String INVALID_PIN = "10";
	public static final String DECLINED_PARTIAL = "11";
	public static final String CHECK_VELOCITY = "12";
	public static final String ERROR_RETRY = "13";
	public static final String FIRST_TIME_USAGE = "14";
	/** Response Code - Request Type Not Supported */
	public static final String REQUEST_TYPE_NOT_SUPPORTED = "15";

	// the following are gift card status set by authorizor
	public static final String ACTIVE = "20"; // These are specific to
	public static final String INACTIVE = "21"; // activation requests
	public static final String INVALID = "22";
	public static final String EXPIRED = "23";
	public static final String DUPLICATE = "24";
	public static final String EXPENDED = "25";
	public static final String UNKNOWN = "26";
	public static final String RELOAD = "27";

	public static final String INVALID_TRANS = "28";
	public static final String CARD_NUM_ERROR = "29";
	public static final String NO_MORE_LOADS_ALLOWED = "30";
	public static final String TERM_ID_ERROR = "31";
	public static final String APPROVAL_SPLIT_TENDER = "32";
	public static final String CALL_CENTER = "33";
	public static final String NOTFOUND = "34";
	public static final String HOLD_CALL = "35";
	public static final String INVALID_MERCH_CALL = "37";
	public static final String MAX_PIN_TRY_DECLINE = "38";
	public static final String GIFT_CARD_INQUIRY_FOR_TENDER_FAILED = "39";
	public static final int DEFAULT_TIMEOUT = 30;

	public static final String[] RESPONSE_CODE_DESCRIPTORS =
		{
		"Approved",
		"Undefined",
		"Declined",
		"Referral",
		"PositiveID",
		"Undefined",
		"Undefined",
		"Undefined",
		"Timeout",
		"Offline",
		"InvalidPIN",
		"DeclinedPartial",
		"CheckVelocity",
		"ErrorOrRetry",
		"FirstTimeUsage",
		"Undefined",
		"Undefined",
		"Undefined",
		"Undefined",
		"Undefined",
		"Active",
		"Deactive",
		"Invalid",
		"Expired",
		"Duplicate",
		"Expended",
		"Unknown",
		"Reload",
		"InvalidTrans",
		"CardNumError",
		"NoMoreLoadsAllowed",
		"TermIdError",
		"ApprovalSplitTender",
		"CallCenter",
		"NotFound",
		"HoldCall",
		"Undefined",
		"InvalidMerchCall",
		"MaxPinTryDecline",
		"GCardInquiryForTenedrFailed"
		};
	//-------------------------------------------------------------------------
	/**
	        Action codes
	        Valid values for TenderAuthResponse.actionCode
	 **/
	//-------------------------------------------------------------------------
	public static final int NO_ACTION = 0;
	public static final int HOLDCARD = 1;
	public static final int RETRY = 2;
	//-------------------------------------------------------------------------
	/**
	        Action codes
	        Valid values for ItemActivationRequest.actionCode
	 **/
	//-------------------------------------------------------------------------
	public static final int ACTIVATE = 10;
	public static final int DEACTIVATE = 11;
	public static final int INQUIRY = 12;
	public static final int REDEEM = 13;
	public static final int RELOAD_GIFT_CARD = 14;

	// KLM (6/2004): Adding this in support of pulling in
	// the ISD work done for Bose in services.
	//
	public static final int RELOAD_VOID = 18;
	//public static final int VOID_GIFT_CARD = 19;
	//public static final int CREDIT_GIFT_CARD = 20;
	public static final int REDEEM_VOID = 21;
	//public static final int RETURN = 22;


	//-------------------------------------------------------------------------
	/**
	        Data entry codes indicate how card or check data was input to the system.
	        Valid values for TenderAuthRequest.dataEntryMethod
	 **/
	//-------------------------------------------------------------------------
	public static final String MANUAL = "Manual Entry";
	public static final String AUTOMATIC = "Automatic";
	public static final String MAGSWIPE = "MagSwipe";
	public static final String NFCTAP = "NFC Tap";
	public static final String EMVSCAN = "EMV Scan";



}
