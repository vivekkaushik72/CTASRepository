package com.cox.ctas.vf.mx.adaptertests;

import jpos.JposException;

import com.cox.ctas.vf.mx.adapters.Mx8xxFormsAdapter;
import com.cox.ctas.vf.mx.adapters.Mx8xxForms;
import com.cox.ctas.vf.mx.adapters.Mx8xxMSRAdapter;
import com.cox.ctas.vf.mx.adapters.Mx8xxMSRController;

public class MSRAdapterTest {

	private static final String CLASSNAME = "CoxVfMSRAdapterTest: ";
	private static Mx8xxFormsAdapter forms;
	private static Mx8xxMSRAdapter mSR;

	public static void main (String [] args) {
		forms = new Mx8xxFormsAdapter();
		mSR = new Mx8xxMSRAdapter();

		try {
			forms.open();
			forms.claim();
			forms.deviceEnabled(true);
			
			if( forms.getDeviceEnable()){
				forms.initForm("MSRPRMPT");
				forms.showForm("MSRPRMPT");
				forms.release();
				
				mSR.openDevice();
				mSR.claimDevice();
				mSR.setDataEventEnabled(true);
				mSR.setTracksToRead(15);
				mSR.setDeviceEnabled(true);
				mSR.release();
				
				forms.initForm("WELCOME");
				forms.showForm("WELCOME");
				
				
			}
		} catch (JposException paramJposException) {
			System.out.println("Classname: " + CLASSNAME + "JposException with message = "
					+ paramJposException.getMessage() + " and errorCode = "
					+ paramJposException.getErrorCode());
		}
	}
}
