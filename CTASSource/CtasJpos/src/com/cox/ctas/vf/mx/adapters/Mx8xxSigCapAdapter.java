package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.io.IOException;
import java.util.Enumeration;

import jpos.JposException;
import jpos.SignatureCapture;
import jpos.config.JposEntry;
import jpos.config.JposEntryRegistry;
import jpos.events.DataEvent;
import jpos.events.DataListener;
import jpos.events.DirectIOEvent;
import jpos.events.DirectIOListener;
import jpos.events.ErrorEvent;
import jpos.events.ErrorListener;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.loader.JposServiceLoader;

import com.cox.ctas.vf.mx.utility.CoxVfMxConstants;
import com.cox.ctas.vf.mx.utility.CoxVfMxUtilities;
import com.verifone.util.Ascii;
import com.verifone.util.Common;

public class Mx8xxSigCapAdapter implements DataListener, ErrorListener, 
DirectIOListener, StatusUpdateListener, CoxVfMxConstants {

	/**services are defined in the jposxml.cfg file in a set order - position 0 represents
	 * the position of the MSR service configuration params*/
	private static final int SIG_CAP_SVC_POS_IN_CFG_FILE = 5;
	private static final String CLASSNAME = "Mx8xxSigCapAdapter";
	int m_nRpoints = 0;
	private int[] sigData = null;
	private JposEntry localJposEntry;
	private SignatureCapture sig = new SignatureCapture();
	//private Mx8xxSigCapController mx = new Mx8xxSigCapController();
	//private SigTest mx = new SigTest();
	private SignatureCapture mx = new SignatureCapture();
	RealTimeSigComponent signatureComponent1 = null;
	SignatureComponent sigComponent = null;

	public Mx8xxSigCapAdapter() {
		localJposEntry = CoxVfMxUtilities.loadServiceConfig(SIG_CAP_SVC_POS_IN_CFG_FILE);

		//add listeners to controller
		this.mx.addDataListener(this);
		this.mx.addErrorListener(this);
		this.mx.addStatusUpdateListener(this);
	}

	public void openDevice() {
		try {
			this.mx.open(this.getLocalJposEntry().getLogicalName());
			System.out.println("openDevice: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			try{
				this.mx.close();
			} catch (Exception localException){
			}
		} 
	}
	
	public void close() {
		try {
			if (this.mx.getClaimed())
				this.mx.release();
			this.mx.close();
			this.sigData = null;
			System.out.println("close: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public void claim() {
		try {
			this.mx.claim(1000);
			System.out.println("claim: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public void deviceEnabled(boolean isSetState) { 
		try {
			this.mx.setDeviceEnabled(isSetState);
			System.out.println("deviceEnabled: Device State: " + Common.GetResultText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public void OnBeginCapture(String formName) {
		try {
			this.mx.beginCapture(formName);
			this.mx.setDataEventEnabled(true);
			System.out.println("OnBeginCapture: Device State: " + Common.GetResultText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public void OnEndCapture() {
		try {
			this.mx.endCapture();
			System.out.println("OnEndCapture: Device State: " + Common.GetResultText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	
	void sendFMDload(String targetFirmWare) {
		try {
			this.mx.updateFirmware(targetFirmWare);
			System.out.println("sendFMDload: " + targetFirmWare + " Download complete");
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	/**REVISIT: Unable to locate this command in MX915 impl guide*/
	public void setPowerNotify(int status) {
		try {
			if (status == 0 || status == 1)
				this.mx.setPowerNotify(status);
			else
				this.mx.setPowerNotify(0);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	/**REVISIT: Unable to locate this command in MX915 impl guide*/
	public String getPowerStatus() {
		String powerStatus = "";

		try {
			int i = this.mx.getPowerState();
			if (i == 2004)
				powerStatus = (DEVICE_PWR_STATUS_OFF_LINE);
			if (i == 2001)
				powerStatus = (DEVICE_PWR_STATUS_ON_LINE);
			if (i == 2000)
				powerStatus = (DEVICE_PWR_STATUS_UNKNOWN);


		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return powerStatus;
	}

	/**REVISIT: Unable to locate this command in MX915 impl guide*/
	void CheckHealth() {
		try {
			this.mx.setDeviceEnabled(true);
			this.mx.setDataEventEnabled(true);
			this.mx.checkHealth(1);
			this.mx.getCheckHealthText();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	/**PROBLEM*/
	@SuppressWarnings("unused")
	private static Signature getSignature(Point[] paramArrayOfPoint)
			throws IOException {
		return new Signature(32767, 32767, paramArrayOfPoint, true, false);
	}

	

	private void clearFrame() {
		System.out.println("Test app clear");
		this.signatureComponent1.resetArray();
	}

	void exit() {
		try {
			
			if (this.mx.getState() != 1)
				this.mx.close();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} finally {
			//dispose();
		}
	}

	void open() {
		try {
			
			this.mx.open(localJposEntry.getLogicalName());
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			try {
				this.mx.close();
			} catch (Exception localException) {
			}
		}
	}


	public void dataOccurred(DataEvent paramDataEvent) {
		System.out.println("data received from Service Object.");
		int k = 0;
		int l = 0;
		int i1 = 0;
		int i2 = 0;
		int i = 2048;
		int j = 1536;
		
		if (false == paramDataEvent.getSource() instanceof SignatureCapture) {
			System.out.println("Unknown event source.");
			return;
		}
		
		JposEntryRegistry localJposEntryRegistry = JposServiceLoader.getManager().getEntryRegistry();
		localJposEntryRegistry.load();

		@SuppressWarnings("rawtypes")
		Enumeration localEnumeration = localJposEntryRegistry.getEntries();
		int i5 = 0;
		Object localObject;
		while (localEnumeration.hasMoreElements()) {
			JposEntry localJposEntry = (JposEntry) localEnumeration
					.nextElement();
			if (localJposEntry.hasPropertyWithName("Endian")) {
				localObject = (String) localJposEntry.getPropertyValue("Endian");
				i5 = 1;
				if (((String) localObject).equals("1"))
					i2 = 1;
				else
					i2 = 0;
			}
		}
		if (i5 == 0) {
			i2 = 1;
			System.out.println("Entry Not found :Defaulting to Big Endian");
		}
		if (paramDataEvent.getStatus() == 3) {
			System.out.println("Last Packet");
			this.signatureComponent1 = null;
			return;
		}
		if (paramDataEvent.getStatus() == 2) {
			clearFrame();
			try {
				this.mx.setDataEventEnabled(true);
			} catch (JposException localJposException1) {
			}
			return;
		}

		try {
			Point[] arrayOfPoint = this.mx.getPointArray();
			if (arrayOfPoint == null) {
				System.out.println("Check SigCap.Tif!");
				return;
			}

			localObject = new int[arrayOfPoint.length * 2];
			if (i2 != 0) {
				System.out.println("Big endian");
				for (int i6 = 0; i6 < arrayOfPoint.length; ++i6) {
					k = (short) arrayOfPoint[i6].x;
					l = (short) arrayOfPoint[i6].y;
					arrayOfPoint[i6].x = ((k & 0xFF) << 8 | (k & 0xFF00) >> 8);
					arrayOfPoint[i6].y = ((l & 0xFF) << 8 | (l & 0xFF00) >> 8);
				}
			}
			if (mx.getRealTimeDataEnabled()){
				this.m_nRpoints = 0;

				this.m_nRpoints += arrayOfPoint.length;
				System.out.println("Captured signature (" + this.m_nRpoints
						+ " points)");
				if (this.signatureComponent1 == null)
					this.signatureComponent1 = new RealTimeSigComponent();

				Signature signature = new Signature(arrayOfPoint, true, false); 
				this.signatureComponent1.setSignature(signature);
				this.mx.setDataEventEnabled(true);
			} else {
				System.out.println("Captured signature ("
						+ arrayOfPoint.length + " points)");
			}
		} catch (JposException localJposException2) {
			CoxVfMxUtilities.handleJposException(localJposException2, CLASSNAME);
		}
	}

	public void errorOccurred(ErrorEvent paramErrorEvent) {
		if (false == paramErrorEvent.getSource() instanceof SignatureCapture) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("Error received from Service Object.");
		try {
			if (3 == paramErrorEvent.getErrorLocus()) {
				paramErrorEvent.setErrorResponse(13);
				this.mx.setDataEventEnabled(true);
			}
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void directIOOccurred(DirectIOEvent paramDirectIOEvent) {
		int[] arrayOfInt = { 1 };
		byte[] arrayOfByte1 = { 28 };
		String str1 = new String(arrayOfByte1);
		Object localObject1 = null;
		Object localObject2 = null;
		Object localObject3 = null;
		Object localObject4 = null;
		int i = 28;
		int j = 20000;
		int k = 0;
		int l = 0;
		Object localObject5 = null;
		Object localObject6 = null;
		Object localObject7 = null;
		if (!(paramDirectIOEvent.getSource() instanceof SignatureCapture)) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("Received DirectIOEvent from Service Object.");
		try {
			if (paramDirectIOEvent.getObject() != null) {
				byte[] arrayOfByte2 = (byte[]) (byte[]) paramDirectIOEvent
						.getObject();
				String str2 = Ascii.FormatBinaryData(arrayOfByte2);
				System.out.println("Packet received: " + str2);
				System.out.println("");
				System.out.println("Packet received: " + str2);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	void sendDirectIOCommand(String iOCommand) {
		try {
			int[] arrayOfInt = { 1 };
			byte[] arrayOfByte1 = { 28 };
			String str1 = new String(arrayOfByte1);
			int i = 28;
			String str2 = iOCommand;
			if (str2.length() <= 0)
				return;
			str2 = Ascii.UnBracket(str2);
			byte[] arrayOfByte2 = str2.getBytes("ISO-8859-1");
			System.out.println("Sending packet: "
					+ Ascii.FormatBinaryData(arrayOfByte2));
			this.mx.setDataEventEnabled(true);
			this.mx.directIO(100, arrayOfInt, str2);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void statusUpdateOccurred(StatusUpdateEvent paramStatusUpdateEvent) {
		if (paramStatusUpdateEvent.getStatus() == 2004) {
			JposException localJposException = new JposException(2004, "Link Disconnected");
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		if (paramStatusUpdateEvent.getStatus() != 2001)
			return;
		JposException localJposException = new JposException(2001,
				"Link Connected");
		CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
	}

	public JposEntry getLocalJposEntry() {
		return localJposEntry;
	
	}
	
	


}
