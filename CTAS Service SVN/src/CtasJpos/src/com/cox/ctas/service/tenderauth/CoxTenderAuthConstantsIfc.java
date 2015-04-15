package com.cox.ctas.service.tenderauth;



//-------------------------------------------------------------------------
/**
   Defines constants for key values involved in the Tender Authorization process.

   @version $Revision: 7$ - duplicate of constants file found in POS 
	   
	   cox.retail.stores.domain.manager.tenderauth package
**/
//-------------------------------------------------------------------------
public interface CoxTenderAuthConstantsIfc extends TenderAuthConstantsIfc
{
    //-------------------------------------------------------------------------
    /**
        Authorization request types
        Valid values for CoxTenderAuthRequest.requestType
    **/
    //-------------------------------------------------------------------------
    //added for defect number: 4547
    public static final int CASH = 8;
    public static final int MONEY_ORDER = 9;
    public static final int TRAVELERS_CHECK = 10;
    
    


    //-------------------------------------------------------------------------
    /**
        Transaction (i.e., "authorization") types
        Valid values for CoxTenderAuthRequest.transactionType
    **/
    //-------------------------------------------------------------------------
    // Changes for recording all payments in ADS - Defect 
    public static final int TRANS_CASH = 8;
    public static final int TRANS_CHECK = 9;
    public static final int TRANS_MONEYORDER = 10;
    

    
    // Define these Card types from card type rules
    
    public static final String VISA_CARD = "Visa";
    public static final String MASTER_CARD = "MasterCard";
    public static final String DISCOVER_CARD="Discover";
    public static final String AMERICAN_EXPRESS_CARD = "AmEx";
    /** Duplicate Payment Entries - kbysani**/
    public static final String DUPLICATE_PAYMENT = "40";
    
    
    
    //-------------------------------------------------------------------------
    /**
        Data entry codes indicate telecheck auth response 
    **/
    //-------------------------------------------------------------------------
    
    public static final String APPROVED_E_CHECK="50";
    public static final String APPROVED_DEPOSITED_CHECK="51";
    
    //-------------------------------------------------------------------------
    /**
        Data entry codes indicate Manual or Auto
    **/
    //-------------------------------------------------------------------------
    public static final String  ENTRY_METHOD_MANUAL="1";
    public static final String  ENTRY_METHOD_AUTO="R";
    
    //-------------------------------------------------------------------------
    /**
        Check Request Types
    **/
    //-------------------------------------------------------------------------
    public static final int SALE_INQUIRY = 100;
    public static final int STATUS_INQUIRY = 101;
    public static final int ADJUSTMENT_INQUIRY = 102;
    
}
