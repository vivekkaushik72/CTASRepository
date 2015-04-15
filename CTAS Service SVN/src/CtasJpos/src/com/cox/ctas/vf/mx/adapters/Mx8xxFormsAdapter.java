/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.cox.ctas.service.webservice.LineItem;
import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.TenderRequest;
import com.cox.ctas.vf.mx.utility.CoxVfMxConstants;
import com.cox.ctas.vf.mx.utility.CoxVfMxUtilities;
import com.verifone.eventlog.VFLog;
import com.verifone.javapos.services.VFForm;
import com.verifone.javapos.services.mx8xx.VFFormService;
import com.verifone.javapos.test.RealTimeSigComponent;
import com.verifone.util.Ascii;
import com.verifone.util.Common;


/**
 * the MxSeries controller. General controller for the device that includes
 * all device commands including MSR, NFC, SigCap, and Show Forms. 
 * 
 * @author 
 *
 */
public class Mx8xxFormsAdapter implements DataListener, ErrorListener,
	DirectIOListener, StatusUpdateListener, CoxVfMxConstants {

	/** the position of the Form Service configuration parameters in the JPOS xml config file*/
	private static final int VF_FORM_SVC_POS_IN_CFG_FILE = 1;
	protected static final String CLASSNAME = "CLASS: Mx8xxFormsAdapter: ";
	protected VFForm mx = new VFForm();
	//protected Mx8xxFormsController mx = new Mx8xxFormsController();
	JposEntry localJposEntry;
	JposEntryRegistry localJposEntryRegistry;

	String nfcdata = null;
	private byte[] m_dataReceived = null;
	private boolean m_bFileDownload = false;
	private boolean m_bACK = false;
	private boolean m_bNAK = false;
	private boolean m_bEOT = false;
	private boolean m_bDAT = false;
	private final byte STX = 2;
	private final byte ETX = 3;
	private final byte EOT = 4;
	private final byte ACK = 6;
	private final byte NAK = 21;
	private String m_strCmdReceived = null;
	private String m_strPacketReceived = null;
	private byte[] m_baFileData = null;
	static int m_iBytesSent = 0;
	static int m_iSeqNum = 0;
	static long m_lFileSize = 0L;
	static int m_iBlockSize = 500;
	private FileInputStream m_fis = null;
	private BufferedInputStream m_bis = null;
	private DataInputStream m_dis = null;
	private int bSigType = 0;
	private int realTimeIndex = 0;
	RealTimeSigComponent rsignatureComponent1 = null;
	private int m_nRpoints = 0;
	public boolean m_sigCap = false;
	private boolean m_cardSwipe = false;
	private boolean m_getPAN = false;
	private boolean m_getpin = false;



	public Mx8xxFormsAdapter() {
		localJposEntry = CoxVfMxUtilities.loadServiceConfig(VF_FORM_SVC_POS_IN_CFG_FILE);

		this.mx.addDataListener(this);
		this.mx.addDirectIOListener(this);
		this.mx.addErrorListener(this);
		this.mx.addStatusUpdateListener(this);
	}

	public void setRealtimeSigCapEnable(boolean set) throws JposException {
		try {
			this.mx.setRealTimeDataEnabled(set);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	public boolean getDeviceEnable() {
		boolean isEnabled = false;
		try {
			isEnabled = mx.getDeviceEnabled();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return isEnabled;
	}

	public void release() {
		try {
			mx.release();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public String getDeviceControlDescription() throws JposException {
		String controlDesc = "";
		controlDesc = mx.getDeviceControlDescription();
		return controlDesc;
	}

	public int getDeviceControlVersion() throws JposException {
		int ctlDesc = 000000;
		ctlDesc = mx.getDeviceControlVersion();
		return ctlDesc;
	}

	public String getPhysicalDeviceName() {
		String dvcName = "";
		try {
			dvcName = mx.getPhysicalDeviceName();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return dvcName;
	}

	public String getPhysicalDeviceDescription() {
		String dvcDesc = "";
		try {
			dvcDesc = mx.getPhysicalDeviceDescription();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return dvcDesc;
	}

	public String getDeviceServiceDescription() {
		String svcDesc = "";
		try {
			svcDesc = mx.getDeviceServiceDescription();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return svcDesc;
	}

	public String getCheckHealthText() {
		String health = "";
		try {
			health = mx.getCheckHealthText();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return health;
	}

	public void setSigCapBoxArea() {
		try {
			mx.setSigCapBoxArea(0, 148, 480, 116, true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public int getMaxX() {
		int max = 0;

		try {
			max = mx.getMaximumX();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}

		return max;
	}


	public int getMaxY() {
		int min = 0;

		try {
			min = mx.getMaximumY();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}

		return min;
	}

	public void setSigCapParams() {
		try {
			//params: # of secs for capture, # of secs after capture, clear background, 
			//(byteorder) endianess, resolution, save format, save file location, save file name
			mx.setSigCapParams(45, 10, 0, false, false, 2, false, "SIG");
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void beginSignatureCapture() throws JposException {
		this.mx.beginSignatureCapture();
	}

	public void setDataEventEnabled(boolean paramBoolean) {
		try {
			mx.setDataEventEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public Point [] getPointArray() {
		Point [] points = null;
		try {

			points = mx.getPointArray();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return points;
	}

	public byte[] getRawData() {
		byte[] data = null;
		try {
			data = mx.getRawData();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return data;

	}

	public void setPowerNotify() {
		try {
			if (mx.getPowerState() == 0)
				this.mx.setPowerNotify(1);
			else
				mx.setPowerNotify(0);

			System.out.println("setPowerNotify: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

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

			System.out.println("getPowerStatus: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return powerStatus;
	}

	public void setRealTimeEnable(boolean paramBoolean) {
		try {
			this.mx.setRealTimeDataEnabled(paramBoolean);
			System.out.println("setRealTimeEnable: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void exit() {
		try {
			if (this.mx.getState() != 1)
				this.mx.close();
			System.out.println("setRealTimeEnable: Device State: " + Common.GetStateText(this.mx.getState()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} 
	}

	public void open() {
		try {
			this.mx.open(this.getLocalJposEntry().getLogicalName());
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			try{
				this.mx.close();
			} catch (Exception localException) {

			}
		} 
	}

	public void close() {
		try {
			if (this.mx.getClaimed())
				this.mx.release();
			this.mx.close();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void claim() {
		try {
			this.mx.claim(1000);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} 
	}

	public void deviceEnabled(boolean state) {
		try {

			this.mx.setDeviceEnabled(state);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void sendDirectIOCommand(String ioCommand) {
		try {
			int[] arrayOfInt = { 1 };
			byte[] arrayOfByte = { 28 };
			String str1 = new String(arrayOfByte);
			String str2 = null;
			str2 = ioCommand;
			if (str2.length() <= 0)
				return;
			str2 = Ascii.UnBracket(str2);
			this.mx.directIO(100, arrayOfInt, str2);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	//REVISIT
	/*protected void xdlddownload(String fileName) {
		int[] arrayOfInt = { 1 };
		String str1 = fileName;
		boolean bool = false;
		if (str1.length() < 0)
			return;
		int i = str1.lastIndexOf("\\");
		String str3 = str1.substring(i + 1);
		if ((str1 != null) || (str1.length() > 0)) {
			File localFile = new File(str1);
			String str2 = "XDLD<FS>" + str3 + "<FS>";
			if (this.rbtn_xdld_full.isSelected()) {
				str2 = str2 + "P<FS>";
				this.rbtn_xdld_partial.setSelected(false);
				bool = true;
			} else if (this.rbtn_xdld_partial.isSelected()) {
				str2 = str2 + "F<FS>";
				this.rbtn_xdld_full.setSelected(false);
				bool = false;
			}
			try {
				this.fd.zDownLoad(str1, bool);
			} catch (JposException localJposException) {
				System.out.println("JException occured.");
				CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			}
		} else {
			System.out.println("Filename is not entered.");
			new JposException(111, "No file name");
		}
	}*/

	public void sendFile(String fileName, String blockSize) {
		int[] arrayOfInt = { 1 };
		byte[] arrayOfByte = { 28 };
		String str1 = new String(arrayOfByte);
		String str2 = null;
		String str3 = null;
		Object localObject = null;
		int i = 28;
		int j = 20000;
		int k = 0;
		int l = 0;
		String str5 = null;
		try {
			str2 = fileName;
			if (str2.length() <= 0)
				return;
			int i1 = str2.lastIndexOf("\\");
			String str6 = str2.substring(i1 + 1);
			File localFile = new File(str2);
			String str4 = "sendFile - File: " + localFile.getAbsoluteFile();
			System.out.println(str4);
			this.m_fis = new FileInputStream(localFile);
			this.m_bis = new BufferedInputStream(this.m_fis);
			this.m_dis = new DataInputStream(this.m_bis);
			m_lFileSize = localFile.length();
			this.m_baFileData = new byte[(int) m_lFileSize];
			this.m_dis.readFully(this.m_baFileData, 0, (int) m_lFileSize);
			m_iBytesSent = 0;
			m_iSeqNum = 0;
			str5 = blockSize;
			m_iBlockSize = Integer.parseInt(str5);
			str5 = pad(str5, 4, '0', true);
			str3 = "SFL<FS>" + str6 + "<FS>" + m_lFileSize + "<FS>" + str5
					+ "<FS>1";
			str3 = Ascii.UnBracket(str3);

			this.m_bFileDownload = true;
			this.mx.directIO(100, arrayOfInt, str3);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (IOException localIOException) {
			System.out.println("sendFile - error: "
					+ localIOException.getMessage());
			return;
		}
	}

	public int waitForChar(byte paramByte, int paramInt) {
		long l1 = System.currentTimeMillis();
		long l2 = l1 + paramInt;
		int i = 1;
		while (true) {
			if ((paramByte == 6) && (this.m_bACK)) {
				i = 1;
				break;
			}
			if ((paramByte == 21) && (this.m_bNAK)) {
				i = -1;
				break;
			}
			if ((paramByte == 4) && (this.m_bEOT)) {
				i = 0;
				break;
			}
			long l3 = System.currentTimeMillis();
			if (l3 >= l2) {
				i = 2;
				break;
			}
			try {
				Thread.sleep(25L);
			} catch (InterruptedException localInterruptedException) {
				System.out.println("sendFile - waitForChar: "
						+ localInterruptedException.getMessage());
			}
		}
		return i;
	}

	public int waitForPacket(String paramString1, String paramString2,
			int paramInt, boolean paramBoolean) {
		long l1 = System.currentTimeMillis();
		long l2 = l1 + paramInt;
		int i = 1;
		int[] arrayOfInt = { 1 };
		byte[] arrayOfByte = { 6 };
		while (true) {
			if (this.m_strCmdReceived != null) {
				if (paramString1.equals(this.m_strCmdReceived)) {
					i = 1;
					paramString2 = this.m_strPacketReceived;
					break;
				}
				if (this.m_strCmdReceived.equalsIgnoreCase("<NAK>")) {
					i = 0;
					break;
				}
			}
			long l3 = System.currentTimeMillis();
			if (l3 >= l2) {
				i = 2;
				break;
			}
			try {
				Thread.sleep(25L);
			} catch (InterruptedException localInterruptedException) {
				System.out.println("sendFile - waitForPacket: "
						+ localInterruptedException.getMessage());
			}
		}
		if ((paramBoolean) && (i == 1)) {
			System.out.println("Sending ACK");
			String str = new String(arrayOfByte);
			try {
				this.mx.directIO(100, arrayOfInt, str);
			} catch (JposException localJposException) {
				CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			}
		}
		return i;
	}

	public void loadForm(String formName) {
		try {
			this.mx.loadForm(formName);
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void initForm(String formName) {
		try {
			this.mx.initForm(formName);
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void showForm(String formName) {
		try {
			this.mx.showForm(formName);
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void removeForm(String formName) {
		try {
			this.mx.removeForm(formName + ".FRM");
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void isFormLoaded(String formName) {
		try {
			this.mx.isFormLoaded(formName);
			if (VFFormService.GeneralResp.equals("1"))
				System.out.println("Form Is Loaded");
			else
				System.out.println("Form Not Loaded");
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void clearScreen() {
		try {
			this.mx.clearScreen();
			deviceEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void formMSR() {
		try {
			this.mx.setDataEventEnabled(true);
	
			this.mx.getCardData("12");;
			
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public String getExpDate() {
		String expDate = "";
		
		try {
			mx.getExpirationDate();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		
		return expDate;
	}
	
	public byte[] getTrack2Data() {	
		long sleepTime = 1000;
		int count = 0;
		
		m_cardSwipe = false;
			
		while (!m_cardSwipe){
			if (count < 20){
				try {
					Thread.sleep(sleepTime);
					count+=1;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		m_cardSwipe = false;
		try {
			m_dataReceived = mx.getTrack2Data();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}		
		return m_dataReceived;
	}

	void formPIN() {
		try {
			this.m_getpin = true;
			byte[] arrayOfByte = { 28 };
			String str2 = new String(arrayOfByte);
			String str3 = new String("1");
			String str1 = "4446661234567892" + str2 + "ENTER PIN" + str2 + " "
					+ str2 + "WAIT.." + str2 + str3;
			this.mx.clearDevice();
			this.mx.getPINEx(str1, "", 4, 12, true);
			deviceEnabled(true);
			this.mx.setDataEventEnabled(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void formSig() {

		long sleepTime = 1000;
		int count = 0;

		try {
			System.out.println(bSigType);
			System.out.println("Get FormEvent before: " + mx.getFormEvent());

			this.mx.setSigCapParams(0, 0, 0, false, false, 4, false, "SIG");
			this.mx.setSigCapBoxArea(5, 148, 475, 267, true);
			this.mx.setDataEventEnabled(true);
			this.mx.beginSignatureCapture();
			this.mx.setDataEventEnabled(true);
			
			m_sigCap = false;

			while (!m_sigCap){
				if (count < 20){
					try {
						Thread.sleep(sleepTime);
						count+=1;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			m_sigCap = false;
			System.out.println("Get FormEvent before: " + mx.getFormEvent());

		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void getVolume() {
		int[] arrayOfInt2 = null;
		int[] arrayOfInt3 = null;
		int[] arrayOfInt1 = new int[1];
		arrayOfInt2 = new int[1];
		arrayOfInt3 = new int[1];
		try {
			this.mx.getVolume(arrayOfInt1, arrayOfInt2, arrayOfInt3);
			System.out.println("Volume=" + arrayOfInt1[0] + "\nBass="
					+ arrayOfInt2[0] + "\nTreble=" + arrayOfInt3[0]);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void restartApp() {
		try {
			this.mx.restartApp();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void setClock() {
		Calendar localCalendar = Calendar.getInstance();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yy/MM/dd HH:mm:ss");
		String str1 = localSimpleDateFormat.format(localCalendar.getTime());
		str1 = removeChar(str1, '/');
		str1 = removeChar(str1, ' ');
		str1 = removeChar(str1, ':');
		String str2 = "20" + str1;
		try {
			this.mx.setClock(str2);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public static String removeChar(String paramString, char paramChar) {
		String str = "";
		for (int i = 0; i < paramString.length(); ++i) {
			if (paramString.charAt(i) == paramChar)
				continue;
			str = str + paramString.charAt(i);
		}
		return str;
	}

	//REVISIT
	/*void ftpGet() {
		VFFTPFun localVFFTPFun = new VFFTPFun();
		localVFFTPFun.btOK.addActionListener(new ActionListener(localVFFTPFun) {
			private final VFFTPFun val$vfftp;

			public void actionPerformed(ActionEvent paramActionEvent) {
				String str1 = this.val$vfftp.ftphosttxt.getText();
				int i = Integer.parseInt(this.val$vfftp.ftpPorttxt.getText());
				String str2 = this.val$vfftp.ftpUsertxt.getText();
				String str3 = this.val$vfftp.ftppasswordtxt.getText();
				String str4 = this.val$vfftp.FileNametxt.getText();
				try {
					VFFormFrame.this.fd.ftpget(str1, i, str2, str3, str4);
					VFFormFrame.this.textOutputTextArea
							.setText(VFFormService.GeneralResp);
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
				this.val$vfftp.dispose();
			}
		});
	}
	 */
	//REVISIT
	/*void ftpput() {
		VFFTPFun localVFFTPFun = new VFFTPFun();
		localVFFTPFun.btOK.addActionListener(new ActionListener(localVFFTPFun) {
			private final VFFTPFun val$vfftp;

			public void actionPerformed(ActionEvent paramActionEvent) {
				String str1 = this.val$vfftp.ftphosttxt.getText();
				int i = Integer.parseInt(this.val$vfftp.ftpPorttxt.getText());
				String str2 = this.val$vfftp.ftpUsertxt.getText();
				String str3 = this.val$vfftp.ftppasswordtxt.getText();
				String str4 = this.val$vfftp.FileNametxt.getText();
				try {
					VFFormFrame.this.fd.ftpput(str1, i, str2, str3, str4);
					VFFormFrame.this.textOutputTextArea
							.setText(VFFormService.GeneralResp);
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
				this.val$vfftp.dispose();
			}
		});
	}
	 */

	void getscreenShot() {
		int i = 0;
		int j = 0;
		int k = 320;
		int l = 234;
		try {
			this.mx.getScreenShot(i, j, k, l);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void setvar() {
		String str1 = new String("hostip");
		String str2 = new String("10.64.113.143");
		try {
			this.mx.setConfigVariable(str1, str2);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void getvar() {
		String str = new String("hostip");
		String[] arrayOfString = new String[1];
		try {
			this.mx.getConfigVariable(str, arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void messageBox() {
		try {
			this.mx.msgBox("Testing MessageBox", "MESSAGE BOX BEING TESTED", 1,
					0, false);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void soundTone() {
		try {
			this.mx.soundTone(0, 50, 4);
			System.out.println("Error Tone Sounded");
			this.mx.soundTone(1, 50, 4);
			System.out.println("Normal Tone Sounded");
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void ledControl() {
		try {
			this.mx.setRunwayLED(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void addTender(TenderRequest request) {

		try {
			mx.addTextBoxText(7, "TOTAL");
			mx.addTextBoxText(8, String.valueOf(request.getTotal()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}


	}

	public void addItems(LineItemRequest request) {
		String currentItem;
		LineItem[] currentItems = request.getItems();

		int count = 0;
		try {
			for (LineItem item : currentItems) {
				currentItem = new String(item.getDescription() + "                         " 
						+ item.getPrice() + "            " + item.getTaxable());
				mx.addListBoxItem(8, count, currentItem, false);
				count +=1;
			}
			mx.clearTextBoxText(4);
			mx.addTextBoxText(4, String.valueOf(request.getQuantity()));
			mx.clearTextBoxText(5);
			mx.addTextBoxText(5, String.valueOf(request.getDiscount()));
			mx.clearTextBoxText(6);
			mx.addTextBoxText(6, String.valueOf(request.getTax()));
			mx.clearTextBoxText(7);
			mx.addTextBoxText(7, String.valueOf(request.getTotal()));

		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void removeListBoxItem() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	private void getListBoxItem() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	void getLisBoxCount() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	void setListBoxTopItem() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	void clearListBox() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	void restorestate() {
		String[] arrayOfString = new String[1];
		try {
			this.mx.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			return;
		}
	}

	//REVISIT
	/*void getConfigVariable() {
		VFconfVariable localVFconfVariable = new VFconfVariable();
		localVFconfVariable.valuetxt.setEditable(false);
		String[] arrayOfString = new String[1];
		localVFconfVariable.btOK.addActionListener(new ActionListener(
				localVFconfVariable, arrayOfString) {
			private final VFconfVariable val$fr;
			private final String[] val$strValue;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					Mx8xxSigCapController.this.mx.getConfigVariable(
							this.val$fr.confVariabletxt.getText(),
							this.val$strValue);
					this.val$fr.valuetxt.setText(this.val$strValue[0]);
				} catch (JposException localJposException) {
					CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
				}
			}
		});
	}*/


	//REVISIT
	/*void setConfigVariable() {
		VFconfVariable localVFconfVariable = new VFconfVariable();
		String[] arrayOfString = new String[1];
		localVFconfVariable.btOK.addActionListener(new ActionListener(
				localVFconfVariable) {
			private final VFconfVariable val$fr;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					VFFormFrame.this.fd.setConfigVariable(
							this.val$fr.confVariabletxt.getText(),
							this.val$fr.valuetxt.getText());
					VFFormFrame.this.textOutputTextArea.setText("SUCCESS");
					this.val$fr.dispose();
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
			}
		});
	}*/

	/*private void addTextBoxText() {
		String[] arrayOfString = new String[1];
		try {
			this.fd.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			handleJposException(localJposException);
			return;
		}
		VFFormListTest localVFFormListTest = new VFFormListTest();
		localVFFormListTest.frmName.setText(arrayOfString[0]);
		localVFFormListTest.frmName.setEditable(false);
		localVFFormListTest.propName.setEditable(false);
		localVFFormListTest.propType.setEditable(false);
		localVFFormListTest.btOK.addActionListener(new ActionListener(
				localVFFormListTest) {
			private final VFFormListTest val$tf;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					VFFormFrame.this.fd.addTextBoxText(
							Integer.parseInt(this.val$tf.cntrlId.getText()),
							this.val$tf.propVal.getText());
					VFFormFrame.this.fd.showForm(this.val$tf.frmName.getText());
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
				this.val$tf.dispose();
			}
		});
	}

	private void clearTextBoxText() {
		String[] arrayOfString = new String[1];
		try {
			this.fd.getLoadedForm(arrayOfString);
		} catch (JposException localJposException) {
			handleJposException(localJposException);
			return;
		}
		VFFormListTest localVFFormListTest = new VFFormListTest();
		localVFFormListTest.frmName.setText(arrayOfString[0]);
		localVFFormListTest.frmName.setEditable(false);
		localVFFormListTest.propName.setEditable(false);
		localVFFormListTest.propType.setEditable(false);
		localVFFormListTest.btOK.addActionListener(new ActionListener(
				localVFFormListTest) {
			private final VFFormListTest val$tf;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					VFFormFrame.this.fd.clearTextBoxText(Integer
							.parseInt(this.val$tf.cntrlId.getText()));
					VFFormFrame.this.fd.showForm(this.val$tf.frmName.getText());
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
				this.val$tf.dispose();
			}
		});
	}

	private void displayText() {
		int[] arrayOfInt1 = new int[1];
		int[] arrayOfInt2 = new int[1];
		VFDisplayText localVFDisplayText = new VFDisplayText();
		localVFDisplayText.btOK.addActionListener(new ActionListener(
				arrayOfInt1, localVFDisplayText, arrayOfInt2) {
			private final int[] val$X;
			private final VFDisplayText val$vfdt;
			private final int[] val$Y;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					this.val$X[0] = Integer.parseInt(this.val$vfdt.Xtxt
							.getText());
					this.val$Y[0] = Integer.parseInt(this.val$vfdt.Ytxt
							.getText());
					VFFormFrame.this.fd
							.displayText(this.val$X, this.val$Y,
									Integer.parseInt(this.val$vfdt.widthtxt
											.getText()), Integer
											.parseInt(this.val$vfdt.Heighttxt
													.getText()), Integer
											.parseInt(this.val$vfdt.optmasktxt
													.getText()),
									this.val$vfdt.fontNametxt.getText(),
									Integer.parseInt(this.val$vfdt.fontSizetxt
											.getText()),
									this.val$vfdt.textAreatxt.getText());
					this.val$vfdt.dispose();
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
			}
		});
	}*/


	//REVISIT
	/*private void getCardDataFormat() {
		VFgetPanData localVFgetPanData = new VFgetPanData();
		localVFgetPanData.btOK.addActionListener(new ActionListener(
				localVFgetPanData) {
			private final VFgetPanData val$vfgcd;

			public void actionPerformed(ActionEvent paramActionEvent) {
				try {
					Mx8xxFormsController.access$2402(Mx8xxFormsController.this, true);
					Mx8xxFormsController.this.mx.getCardDataFormat(
							this.val$vfgcd.txt_promptmsg.getText(), Integer
									.parseInt(this.val$vfgcd.txt_minpan
											.getText()), Integer
									.parseInt(this.val$vfgcd.txt_maxpan
											.getText()), Integer
									.parseInt(this.val$vfgcd.txt_panoptions
											.getText()),
							this.val$vfgcd.txt_expdtpromptmsg.getText(),
							Integer.parseInt(this.val$vfgcd.txt_ExptDtpromt
									.getText()));
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				} catch (Exception localException) {
					localException.printStackTrace();
				}
				this.val$vfgcd.dispose();
			}
		});
	}*/

	public void clearCardData() {
		try {

			this.mx.clearCardData();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private void enableNFCModule() {
		try {
			this.mx.enableNFC(true);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	private void getNFCData() { 
		try {
			this.mx.getNFCData("4294967295");
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void ResetandClearNFC() {
		try {
			this.mx.ResetandClearNFC();

		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public int getNFCDataLength() {
		int dataLength = 0;
		try {
			dataLength = this.mx.getNFCDataLength();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		return dataLength;
	}

	/*private void setVolume() {
		VFsetVolume localVFsetVolume = new VFsetVolume();
		localVFsetVolume.btOK.addActionListener(new ActionListener(
				localVFsetVolume) {
			private final VFsetVolume val$f;

			public void actionPerformed(ActionEvent paramActionEvent) {
				int i = Integer.parseInt(this.val$f.volumetxt.getText());
				int j = Integer.parseInt(this.val$f.basstxt.getText());
				int k = Integer.parseInt(this.val$f.trebletxt.getText());
				try {
					VFFormFrame.this.fd.setVolume(i, j, k);
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
				this.val$f.dispose();
			}
		});
	}*/

	/*private void setPropVal() {
		VFFormsetPropertyValue localVFFormsetPropertyValue = new VFFormsetPropertyValue();
		localVFFormsetPropertyValue.btOK.addActionListener(new ActionListener(
				localVFFormsetPropertyValue) {
			private final VFFormsetPropertyValue val$vfspv;

			public void actionPerformed(ActionEvent paramActionEvent) {
				int i = Integer.parseInt(this.val$vfspv.txtControlId.getText());
				int j = 4;
				int k = this.val$vfspv.cboProp.getSelectedIndex() + 1;
				if (this.val$vfspv.boolValR.isSelected())
					j = 1;
				else if (this.val$vfspv.stringValR.isSelected())
					j = 4;
				else if (this.val$vfspv.shortR.isSelected())
					j = 3;
				else if (this.val$vfspv.longR.isSelected())
					j = 5;
				try {
					VFFormFrame.this.fd.setPropertyValue(i, k, j,
							this.val$vfspv.txtvalue.getText());
					VFFormFrame.this.textOutputTextArea
							.setText(VFFormService.GeneralResp);
					this.val$vfspv.dispose();
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
			}
		});
	}*/

	void getfile(String formName) {

		if ((formName == null) && (formName == ""))
			return;
		try {
			this.mx.getFile(formName);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public String getVersion() {
		String[] arrayOfString1 = new String[1];
		String[] arrayOfString2 = new String[1];
		String[] arrayOfString3 = new String[1];
		String[] arrayOfString4 = new String[1];
		try {
			this.mx.getVersion(arrayOfString1, arrayOfString2, arrayOfString3,
					arrayOfString4);
			System.out.println(arrayOfString1[0] + "\n"
					+ arrayOfString2[0] + "\n" + arrayOfString3[0] + "\n"
					+ arrayOfString4[0]);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}

		return (arrayOfString1[0] + "\n"
				+ arrayOfString2[0] + "\n" + arrayOfString3[0] + "\n"
				+ arrayOfString4[0]);
	}

	/*void getpropvalue() {
		String[] arrayOfString = new String[1];
		VFFormsetPropertyValue localVFFormsetPropertyValue = new VFFormsetPropertyValue();
		localVFFormsetPropertyValue.btOK.addActionListener(new ActionListener(
				localVFFormsetPropertyValue, arrayOfString) {
			private final VFFormsetPropertyValue val$vfgpv;
			private final String[] val$strPropVal;

			public void actionPerformed(ActionEvent paramActionEvent) {
				int i = Integer.parseInt(this.val$vfgpv.txtControlId.getText());
				int j = 4;
				int k = this.val$vfgpv.cboProp.getSelectedIndex() + 1;
				if (this.val$vfgpv.boolValR.isSelected())
					j = 1;
				else if (this.val$vfgpv.stringValR.isSelected())
					j = 4;
				else if (this.val$vfgpv.shortR.isSelected())
					j = 3;
				else if (this.val$vfgpv.longR.isSelected())
					j = 5;
				try {
					VFFormFrame.this.fd.getPropertyValue(i, k, j,
							this.val$strPropVal);
					VFFormFrame.this.textOutputTextArea
							.setText(this.val$strPropVal[0]);
					this.val$vfgpv.dispose();
				} catch (JposException localJposException) {
					VFFormFrame.this.handleJposException(localJposException);
				}
			}
		});
	}*/

	public void resetTerminal() {
		try {
			this.mx.restartTerminal();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	private void InitializeLogicalNameList() {
		String str1 = "";
		String str2 = "";
		JposEntryRegistry localJposEntryRegistry = JposServiceLoader
				.getManager().getEntryRegistry();
		localJposEntryRegistry.load();
		Enumeration localEnumeration = localJposEntryRegistry.getEntries();
		while (localEnumeration.hasMoreElements()) {
			JposEntry localJposEntry = (JposEntry) localEnumeration
					.nextElement();
			str1 = (String) localJposEntry.getPropertyValue("logicalName");
			if (str1.indexOf("VFForm") < 0)
				continue;
			str2 = (String) localJposEntry.getPropertyValue("serviceType");
		}
	}

	public JposEntry getLocalJposEntry() {
		return localJposEntry;
	}

	public void sigDataOccurred() {
		try {
			Point[] arrayOfPoint = this.mx.getPointArray();
			if (arrayOfPoint == null) {
				if (this.bSigType == 1)
					try {
						File localFile = new File("sign.tiff");
						if (!(localFile.exists()))
							localFile.createNewFile();
						FileOutputStream localFileOutputStream = new FileOutputStream(
								localFile);
						BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(
								localFileOutputStream);
						localBufferedOutputStream.write(this.mx.getRawData());
						localBufferedOutputStream.close();
					} catch (Exception localException) {
					}
				return;
			}
			int k;
			int j;
			boolean realTimeCB = false;

			if (realTimeCB) {
				this.m_nRpoints = 0;
			}

			this.m_nRpoints += arrayOfPoint.length;
			System.out.println("Captured signature ("
					+ this.m_nRpoints + " points)");
			for (k = 0; k < arrayOfPoint.length; ++k) {
				int i = (short) arrayOfPoint[k].x;
				j = (short) arrayOfPoint[k].y;
				arrayOfPoint[k].x = ((i & 0xFF) << 8 | (i & 0xFF00) >> 8);
				arrayOfPoint[k].y = ((j & 0xFF) << 8 | (j & 0xFF00) >> 8);
			}


		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	@Override
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

	@Override
	public void directIOOccurred(DirectIOEvent paramDirectIOEvent) {
		int[] arrayOfInt = { 1 };
		byte[] arrayOfByte1 = { 28 };
		String str1 = new String(arrayOfByte1);
		Object localObject1 = null;
		String str2 = null;
		String str3 = null;
		Object localObject2 = null;
		int i = 28;
		int j = 20000;
		int k = 0;
		int l = 0;
		String str5 = null;
		Object localObject3 = null;
		String str6 = null;
		this.m_bACK = (this.m_bNAK = this.m_bEOT = this.m_bDAT = false);
		if (!(paramDirectIOEvent.getSource() instanceof Mx8xxForms)) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("Received DirectIOEvent from Service Object.");
		try {
			if (paramDirectIOEvent.getObject() != null) {
				byte[] arrayOfByte2 = (byte[]) (byte[]) paramDirectIOEvent
						.getObject();
				this.m_strPacketReceived = Ascii.FormatBinaryData(arrayOfByte2);
				System.out.println("Recieved="
						+ this.m_strPacketReceived);
				if (this.m_bFileDownload) {
					if (this.m_strPacketReceived.equals("<ACK>")) {
						System.out.println("ACK received");
						this.m_strCmdReceived = "<ACK>";
						this.m_bACK = true;
					} else if (this.m_strPacketReceived.equals("<NAK>")) {
						System.out.println("NAK received");
						this.m_strCmdReceived = "<NAK>";
						this.m_bNAK = true;
					} else if (this.m_strPacketReceived.equals("<EOT>")) {
						System.out.println("EOT received");
						this.m_strCmdReceived = "<EOT>";
						this.m_bEOT = true;
					} else if ((this.m_bFileDownload)
							|| (this.m_strPacketReceived.startsWith("XDAT<FS>"))) {
						System.out.println("XDAT response packet received: "
								+ this.m_strPacketReceived);
						this.m_strCmdReceived = "XDAT";
						this.m_bDAT = true;
						str5 = "Y";
						this.m_bFileDownload = true;
						System.out.println("m_iBytesSent=" + m_iBytesSent
								+ ", m_lFileSize=" + m_lFileSize);
						if (m_iBytesSent < m_lFileSize) {
							if (m_lFileSize < m_iBlockSize) {
								str5 = "N";
								m_iBlockSize = (int) m_lFileSize;
							} else if (m_iBytesSent + m_iBlockSize >= m_lFileSize) {
								str5 = "N";
								m_iBlockSize = (int) m_lFileSize - m_iBytesSent;
							} else {
								str5 = "Y";
							}
							str6 = String.valueOf(m_iBlockSize);
							str6 = pad(str6, 4, '0', true);
							m_iSeqNum += 1;
							String str4 = String.valueOf(m_iSeqNum);
							str4 = pad(str4, 4, '0', true);
							str2 = "XDAT<FS>" + str5 + str6 + str4;
							if (m_iSeqNum == 9999) {
								m_iSeqNum = 0;
								System.out
								.println("Crossed 9999 so reseting sequence number back");
							}
							str2 = Ascii.UnBracket(str2);
							byte[] arrayOfByte3 = new byte[14 + m_iBlockSize];
							byte[] arrayOfByte4 = str2.getBytes("ISO-8859-1");
							System.arraycopy(arrayOfByte4, 0, arrayOfByte3, 0,
									arrayOfByte4.length);
							System.out.println("m_iBlockSize=" + m_iBlockSize
									+ ", copying bytes from byte position "
									+ m_iBytesSent);
							System.arraycopy(this.m_baFileData, m_iBytesSent,
									arrayOfByte3, arrayOfByte4.length,
									m_iBlockSize);
							String str7 = Ascii.FormatBinaryData(arrayOfByte3);
							if (str5 == "N")
								this.m_bFileDownload = false;
							System.out.println("Sending packet: " + str7);
							str3 = new String(arrayOfByte3, "ISO-8859-1");
							this.mx.directIO(100, arrayOfInt, str3);
							m_iBytesSent += m_iBlockSize;
							int i1 = (int) (m_iBytesSent * 100 / m_lFileSize);
							System.out.println("Progress=" + i1 + "%");

						}
					}
				} else if (this.m_strPacketReceived.startsWith("SFL<FS>")) {
					System.out.println("SFL response packet received: "
							+ this.m_strPacketReceived);
					this.m_strCmdReceived = "SFL";
					if (!(this.m_bFileDownload)) {
						System.out.println("SFL download completed");

						this.m_dis.close();
						this.m_bis.close();
						this.m_fis.close();

					}
				} else if (this.m_strPacketReceived.startsWith("XDAT<FS>")) {
					System.out.println("XDAT response packet received: "
							+ this.m_strPacketReceived);
				} else {
					System.out.println("Unexpected packet received: "
							+ this.m_strPacketReceived);
				}
			} else {
				System.out.println("Packet received: (null)");
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private static String pad(String paramString, int paramInt, char paramChar,
			boolean paramBoolean) {
		if (paramString.length() >= paramInt)
			return paramString;
		StringBuffer localStringBuffer = new StringBuffer(paramString);
		int i = localStringBuffer.length();
		if ((paramInt > 0) && (paramInt > i))
			for (int j = 0; j <= paramInt; ++j)
				if (paramBoolean) {
					if (j >= paramInt - i)
						continue;
					localStringBuffer.insert(0, paramChar);
				} else {
					if (j <= i)
						continue;
					localStringBuffer.append(paramChar);
				}
		return localStringBuffer.toString();
	}

	@Override
	public void errorOccurred(ErrorEvent paramErrorEvent) {
		if (!(paramErrorEvent.getSource() instanceof Mx8xxForms)) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("");
		String str1 = null;
		String str2 = null;
		String str3 = null;
		String str4 = null;
		String str5 = null;
		byte[] arrayOfByte1 = null;
		byte[] arrayOfByte2 = null;
		byte[] arrayOfByte3 = null;
		Object localObject = null;
		try {
			arrayOfByte1 = this.mx.getTrack1Data();
			arrayOfByte2 = this.mx.getTrack2Data();
			arrayOfByte3 = this.mx.getTrack3Data();
			if ((arrayOfByte1 != null) && (arrayOfByte1.length != 0))
				str1 = Ascii.FormatBinaryData(arrayOfByte1);
			if ((arrayOfByte2 != null) && (arrayOfByte2.length != 0))
				str2 = Ascii.FormatBinaryData(arrayOfByte2);
			if ((arrayOfByte3 != null) && (arrayOfByte3.length != 0))
				str3 = Ascii.FormatBinaryData(arrayOfByte3);
			str5 = this.mx.getCardDataSource();
		} catch (Exception localException) {
		}
		if (108 == paramErrorEvent.getErrorCode()) {
			CoxVfMxUtilities.handleJposException(new JposException(108), CLASSNAME);
			return;
		}
		System.out.println("Error received from Service Object.");
		if ((str1 != null) || (str2 != null) || (str3 != null)
				|| (str4 != null)) {
			if (str1 != null)
				System.out.println("Track1 = :" + str1 + ": " + str5 + "\n");
			if (str2 != null)
				System.out.println("Track2 = :" + str2 + ": " + str5 + "\n");
			if (str3 != null)
				System.out.println("Track3 = :" + str3 + ": " + str5 + "\n");
			if (str4 != null)
				System.out.println("Track4 = :" + str4 + ": " + str5 + "\n");
		}
		byte[] arrayOfByte4 = null;
		byte[] arrayOfByte5 = null;
		String str6 = null;
		try {
			str6 = new String(this.mx.getCardAuthenticationData());
			if (((this.mx.getEncryptedPAN()).length() > 0)
					&& (new String(arrayOfByte4).length() > 0))
				System.out.println("TRACK1 Encrypted Data: "
						+ new String(arrayOfByte4) + "\n");
			/*if (((arrayOfByte5 = this.mx.getEntcyptedTrack2()) != null)
					&& (new String(arrayOfByte5).length() > 0))
				System.out.println("TRACK2 Encrypted Data: "
						+ new String(arrayOfByte5) + "\n");*/
			if ((str6.length() > 0) && (str6 == null))
				System.out.println("KEY ID: " + str6 + "\n");
			String str7 = null;
			String str8 = null;
			if (((str7 = this.mx.getRandomizedPAN()) != null)
					&& (str7.length() > 0))
				System.out.println("Randomized PAN: " + str7 + "\n");
			if (((str8 = this.mx.getEncryptedPAN()) != null)
					&& (str8.length() > 0))
				System.out.println("Encrypted  PAN: " + str8 + "\n");
		} catch (JposException localJposException1) {
			CoxVfMxUtilities.handleJposException(localJposException1, CLASSNAME);
		}
		try {
			if (3 == paramErrorEvent.getErrorLocus()) {
				paramErrorEvent.setErrorResponse(13);
				this.mx.setDataEventEnabled(true);
			}
		} catch (JposException localJposException2) {
			CoxVfMxUtilities.handleJposException(localJposException2, CLASSNAME);
		}
	}


	@Override
	public void dataOccurred(DataEvent paramDataEvent) {
		System.out.println("");
		if (!(paramDataEvent.getSource() instanceof Mx8xxForms)) {
			System.out.println("Unknown event source.");
			m_sigCap = true;
			m_cardSwipe = true;
			return;
		}
		VFLog.log("Mx8xxFormsAdapter Test", 2, "dataOccurred called!");
		try {
			if (paramDataEvent.getStatus() == 7) {
				VFLog.log("VFFormTest", 2, "NFC DATA COMPLETE ");
				if (((this.nfcdata = this.mx.NFCData()) != null)
						&& (this.mx.getNFCDataLength() > 0)) {
					System.out.println("\n NFC DATA:  " + this.nfcdata);
					return;
				}
			}
			this.mx.setDataEventEnabled(true);
			Object localObject1 = null;
			if (this.m_getPAN) {
				String str1 = null;
				String str2 = null;
				if (((str1 = this.mx.getRandomizedPAN()) != null)
						&& (str1.length() > 0))
					System.out.println("Randomized PAN: " + str1 + "\n");
				if (((str2 = this.mx.getEncryptedPAN()) != null)
						&& (str2.length() > 0))
					System.out.println("Encrypted  PAN: " + str2 + "\n");
				this.m_getPAN = false;
			}
			int i = this.mx.getEventControlType();
			String str2 = new Integer(this.mx.getEventControlType()).toString();
			String str3;
			Object localObject2;
			Object localObject3;
			Object localObject4;
			if ((this.m_cardSwipe) || (str2.indexOf("60") == 0)) {
				System.out.println("Get Track Data");
				str3 = null;
				localObject2 = null;
				localObject3 = null;
				localObject4 = null;
				byte[] arrayOfByte1 = null;
				String str4 = null;
				String str5 = null;
				String str6 = null;
				String str7 = null;
				localObject2 = this.mx.getTrack1Data();
				localObject3 = this.mx.getTrack2Data();
				localObject4 = this.mx.getTrack3Data();
				arrayOfByte1 = this.mx.getTrack4Data();
				byte[] arrayOfByte2 = null;
				byte[] arrayOfByte3 = null;
				String str8 = null;
				if ((localObject2 != null) && (localObject2.toString().length() != 0))
					str4 = Ascii.FormatBinaryData((byte[]) localObject2);
				if ((localObject3 != null) && (localObject3.toString().length() != 0))
					str5 = Ascii.FormatBinaryData((byte[]) localObject3);
				if ((localObject4 != null) && (localObject4.toString().length() != 0))
					str6 = Ascii.FormatBinaryData((byte[]) localObject4);
				if ((arrayOfByte1 != null) && (arrayOfByte1.length != 0))
					str7 = Ascii.FormatBinaryData(arrayOfByte1);
				str3 = this.mx.getCardDataSource();
				if ((str4 != null) || (str5 != null) || (str6 != null)
						|| (str7 != null)) {
					if (str4 != null)
						System.out.println("Track1 = :"
								+ str4
								+ ": "
								+ str3
								+ "\n");
					if (str5 != null)
						System.out.println("Track2 = :"
								+ str5
								+ ": "
								+ str3
								+ "\n");
					if (str6 != null)
						System.out.println("Track3 = :"
								+ str6
								+ ": "
								+ str3
								+ "\n");
					int i1 = 60;
					int i2 = 0;
					int i3 = i1;
					if (str7 != null) {
						System.out.println("\nRFID TAGS: \n");
						while (str7.length() - i3 > i1) {
							System.out.println(str7.subSequence(i2, i3) + ": "
									+ "\n");
							i2 = i3;
							i3 += i1;
						}
						System.out.println(str7.subSequence(i3, str7.length())
								+ ": "
								+ "\n");
					}
				}
				str8 = new String(this.mx.getCardAuthenticationData());
				if (((this.mx.getEncryptedPAN()).length() > 0))
					System.out.println("TRACK1 Encrypted Data: "
							+ new String(arrayOfByte2) + "\n");
				/*if (((arrayOfByte3 = this.mx.getEntcyptedTrack2()) != null)
						&& (new String(arrayOfByte3).length() > 0))
					System.out.println("TRACK2 Encrypted Data: "
							+ new String(arrayOfByte3) + "\n");*/
				if ((str8.length() > 0) && (str8 == null))
					System.out.println("KEY ID: " + str8 + "\n");
				this.m_cardSwipe = false;
			}
			if (this.m_getpin) {
				str3 = null;
				localObject2 = null;
				str3 = this.mx.getEncryptedPIN();
				localObject2 = this.mx.getAdditionalSecurityInformation();
				if (str3 != null) {
					System.out.println("PIN = :" + str3 + ":");
					if (localObject2 != null)
						System.out.println(((String) localObject2) + ":\n");
					else
						System.out.println("\n");
				}
				this.m_getpin = false;
			}
			int j = 0;
			j = this.mx.getSignaturePointCount();
			if (j != 0) {
				if (paramDataEvent.getStatus() == 2) {
					this.mx.resetSignaturePointCount();
				}
				if (paramDataEvent.getStatus() == 3) {
					this.rsignatureComponent1 = null;
					System.out.println("Real time signature Done");
					this.mx.resetSignaturePointCount();
				} else {
					sigDataOccurred();
					this.mx.resetSignaturePointCount();
				}
			}
			if (this.mx.getFormEvent()) {
				System.out.println("Form Event is received");
				int k = this.mx.getEventControlType();
				localObject3 = new Integer(this.mx.getEventControlType())
				.toString();
				System.out.println("Control Type = " + ((String) localObject3));
				System.out.println("Control Data = "
						+ this.mx.getEventControlData());
				switch (k) {
				case 60:
					System.out.println("\n \n XEVT of NFC TAP"
							+ " With Control ID: 60");
					localObject4 = this.mx.getEventControlID();
					break;
				case 2:
					System.out.println("Control Type is Button");
					localObject4 = this.mx.getEventControlID();
					System.out.println("Control Id = "
							+ ((String) localObject4));
					break;
				case 8:
					System.out.println("Control Type is Animation");
					localObject4 = this.mx.getEventControlID();
					System.out.println("Control Id = "
							+ ((String) localObject4));
				case 0:
					System.out.println("Control Type is KeyPad");
					localObject4 = this.mx.getEventControlID();
					System.out.println("Control Id = "
							+ ((String) localObject4));
					int l = this.mx.getKeyPadEvent();
					System.out.println("KeyEvent = " + l);
					System.out.println("Value = "
							+ this.mx.getKeyPadEventValue());
					System.out.println(this.mx
							.getKeyPadEventValue());
					break;
				case 6:
					System.out.println("Control Type is KeyPad");
					localObject4 = this.mx.getEventControlID();
					System.out.println("Control Id = "
							+ ((String) localObject4));
					System.out.println("selectedstate = "
							+ this.mx.getCBSelectedState());
				}
			}
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}


}
