package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.cox.ctas.vf.mx.utility.CoxVfMxUtilities;
import com.sun.imageio.plugins.common.ImageUtil;
import com.verifone.util.Ascii;

import jpos.JposException;

public class FormAdapterTest {

	private static final String CLASSNAME = "CLASSNAME: CoxVfServiceAdapterTest: ";
	private static Mx8xxFormsAdapter forms;
	private static Mx8xxMSRAdapter msr;
	private static Mx8xxSigCapAdapter sigCap;
	private static String [] formNames = {"WELCOME", "THANKS", "ITEMS", "CREDCONF", 
		"TENDERS", "TENDERCDG", "MSRPRMPT", "AUTHMSG", "PLZWAIT", "DEBCONF", 
		"TENDCG", "TENDC", "SIGNATUR", "TENDCD", "VERFIYCUST", "WELCOME"};


	public static void main (String [] args) {
		forms = new Mx8xxFormsAdapter();
		msr = new Mx8xxMSRAdapter();

		connectClaimEnableTest();
		showFormsTest();
		showSignatureForm();
		//getDeviceInformationTest();
		releaseCloseDeviceTest();
	}


	private static void connectClaimEnableTest() {
		//test device connect and open
		forms.open();
		forms.claim();
		forms.deviceEnabled(true);
	}

	private static void releaseCloseDeviceTest() {
		if(forms.getDeviceEnable()){
			forms.release();
			forms.close();
		} 
	}

	private static void showFormsTest() {
		try {
			for (String form : formNames) {
				forms.initForm(form);
				forms.showForm(form);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
	}

	private static void getDeviceInformationTest() {
		try {
			if(forms.getDeviceEnable()){
				System.out.println("Check Health Text: " + forms.getCheckHealthText());
				System.out.println("Device Control Description: " + forms.getDeviceControlDescription());
				System.out.println("Device Control Version: " + forms.getDeviceControlVersion());
				System.out.println("Device Service Description: " + forms.getDeviceServiceDescription());
				System.out.println("Device Control Version: " + forms.getDeviceControlVersion());
				System.out.println("Physical Device Description: " + forms.getPhysicalDeviceDescription());
				System.out.println("Physical Device Name: " + forms.getPhysicalDeviceName());
			}
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} 
	}

	private static void showSignatureForm() {	

		if (forms.getDeviceEnable()) {
			forms.clearScreen();
			
			forms.initForm("SIGNATUR");
			forms.showForm("SIGNATUR");
			forms.formSig();
//				//				forms.setSigCapParams();
//				//				forms.setSigCapBoxArea();
//				//				forms.beginSignatureCapture();
//				//				forms.setDataEventEnabled(true);
//				forms.setSigCapParams();
//				forms.setSigCapBoxArea();
//				//forms.setRealtimeSigCapEnable(true);
//				System.out.println("Maximum X: " + forms.getMaxX());
//				System.out.println("Maximum X: " + forms.getMaxY());
//				forms.beginSignatureCapture();
//				forms.setDataEventEnabled(true);
			forms.sigDataOccurred();
			Point[] signaturePoint = forms.getPointArray();
			byte [] signature = forms.getRawData();


			Point [] pointArray = null; 
			//Point [] newPointArray = convertByteToPointArray(signature);
			byte [] tiff = null;
			byte [] tiff2 = null;

			String sig;
			//				sig = CoxVfMxUtilities.byteArrayToHex(signature);  			//Bad endianness tag (not 0x4949 or 0x4d4d)
			//				System.out.println("\nbyteArrayToHex method: " + sig);
			//				System.out.println(tstpt.toString());
			//				sig = CoxVfMxUtilities.getHex(signature);					//Bad endianness tag (not 0x4949 or 0x4d4d)
			//				System.out.println("\ngetHex method: " + sig);
			//				sig = CoxVfMxUtilities.bytesToHexString(signature, 2);		//Bad endianness tag (not 0x4949 or 0x4d4d)
			//				System.out.println("\nbytesToHexString method: " + sig);    
			//				sig = new String (Ascii.FormatBinaryData(signature));		//Bad endianness tag (not 0x4949 or 0x4d4d)
			//				System.out.println("\nAscii.FormatBinaryData method: " + sig);	
			//				sig = new String(Ascii.byteArrayToHexString(signature, true));
			//				System.out.println("\nAscii.byteArrayToHexString method: " + sig);
			//				sig = new String(Ascii.byteArrayToHexString(signature, false));
			//				System.out.println("\nAscii.byteArrayToHexString [false] method: " + sig);
			sig = new String (Base64.encodeBase64(signature));
			System.out.println("\nBase64.encode64" + sig);
			//				sig = new String(Ascii.byteArrayToHexString2(signature, true));
			//				System.out.println("\nAscii.byteArrayToHexString2 method: " + sig);
			//				sig = new String(Ascii.byteArrayToHexString2(signature, false));
			//				System.out.println("\nAscii.byteArrayToHexString2 [false] method: " + sig);
			//				sig = new String(Ascii.byteArrayToHexString3(signature));
			//				System.out.println("\nAscii.byteArrayToHexString3 method: " + sig);
			//				sig = new String(signature);
			//				System.out.println("\nnew String method: " + sig);


			//				tiff2 = sig.getBytes();
			//				
			//				
			tiff = Base64.decodeBase64(sig.getBytes());
			//				tiff = signature;
			//				System.out.println(signature.toString());

           

			ImageUtility img = new ImageUtility();
			try {
				pointArray = img.convertTiff2PointArrray(tiff);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				forms.release();
				//System.out.println(pointArray.toString());
				forms.close();
			}

			//System.out.println(forms.getSignaturePointCount());

		} else {
			connectClaimEnableTest();
			showSignatureForm();
		} 

	}


	public static Point [] convertByteToPointArray(byte[] rawBytes) {
		

		ByteArrayInputStream bais = new ByteArrayInputStream(rawBytes);
		DataInputStream dis = new DataInputStream(bais);

		Point[] m_points = null;

		try {
			short nTotalPoints = dis.readShort();
			m_points = new Point[nTotalPoints];
			for (int i = 0; i < nTotalPoints; i++ ) {
				m_points[i] = new Point(dis.readShort(), dis.readShort());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bais.close();
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


			//return new Signature(Short.MAX_VALUE, Short.MAX_VALUE, m_points, true, false);
			return m_points;
	}
	
	
	public static Point[] returnPointArray() {
		Point[] testArray = null;
		
		
		
		
		
		
		
		return testArray;
	}

	
	
}

