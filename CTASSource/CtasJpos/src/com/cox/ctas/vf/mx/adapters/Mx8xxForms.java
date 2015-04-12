package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.util.Hashtable;
import java.util.Vector;

import com.verifone.javapos.services.VFForm;
import com.verifone.javapos.services.VFFormControl111;
import com.verifone.javapos.services.VFFormService1;
import com.verifone.javapos.services.VFFormService111;
import com.verifone.javapos.services.mx8xx.VFFormService;

import jpos.BaseControl;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.DataEvent;
import jpos.events.DataListener;
import jpos.events.DirectIOEvent;
import jpos.events.DirectIOListener;
import jpos.events.ErrorEvent;
import jpos.events.ErrorListener;
import jpos.events.OutputCompleteEvent;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.loader.JposServiceConnection;
import jpos.services.BaseService;
import jpos.services.EventCallbacks;

public class Mx8xxForms extends VFForm {

	protected static final String deviceControlDescription = "JavaPOS VeriFone Form Device Control";
	protected static final int deviceControlVersion = 1013000;
	protected static final int deviceVersion1 = 1013000;
	protected JposServiceConnection serviceConnection = null;
	protected int serviceVersion;
	protected VFFormService mx8xxFormService = null;
	protected VFFormService1 service1 = null;
	protected VFFormService111 service111 = null;
	protected Vector dataListeners = new Vector();
	protected Vector directIOListeners = new Vector();
	protected Vector errorListeners = new Vector();
	protected Vector statusUpdateListeners = new Vector();

	
	public boolean getDeviceEnabled() throws JposException {
		try {
			return this.mx8xxFormService.getDeviceEnabled();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	public void setRealtimeSigCapEnable(boolean set) throws JposException {
		try {
			this.mx8xxFormService.setRealTimeDataEnabled(set);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}
	
	
	
	@Override
	public void beginSignatureCapture() throws JposException {
		try {
			this.mx8xxFormService.beginSignatureCapture();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}

	}

	@Override
	public void clearDevice() throws JposException {
		try {
			this.mx8xxFormService.clearDevice();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}

	}

	@Override
	public void clearScreen() throws JposException {
		try {
			this.mx8xxFormService.clearScreen();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot Clear Form Display",
					localException);
		}
	}

	@Override
	public String getAccountNumber() throws JposException {
		return this.mx8xxFormService.getAccountNumber();
	}

	@Override
	public String getAdditionalSecurityInformation() throws JposException {
		try {
			return this.mx8xxFormService.getAdditionalSecurityInformation();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public boolean getCBSelectedState() throws JposException {
		try {
			return this.mx8xxFormService.getCBSelectedState();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getCardData(String paramString) throws JposException {
		try {
			this.mx8xxFormService.getCardData(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}

	}

	@Override
	public String getCardDataSource() throws JposException {
		try {
			return this.mx8xxFormService.getCardDataSource();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public Hashtable getControlData() throws JposException {
		try {
			return this.mx8xxFormService.getControlData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getControlID() throws JposException {
		try {
			return this.mx8xxFormService.getEventControlID();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public int getControlType() throws JposException {
		try {
			return this.mx8xxFormService.getEventControlType();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public boolean getDataEventEnabled() throws JposException {
		try {
			return this.mx8xxFormService.getDataEventEnabled();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getEventControlData() throws JposException {
		try {
			return this.mx8xxFormService.getEventControlData();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String getEventControlID() throws JposException {
		try {
			return this.mx8xxFormService.getEventControlID();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public int getEventControlType() throws JposException {
		try {
			return this.mx8xxFormService.getEventControlType();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String getExpirationDate() throws JposException {
		return this.mx8xxFormService.getExpirationDate();
	}

	@Override
	public String getFirstName() throws JposException {
		return this.mx8xxFormService.getFirstName();
	}

	@Override
	public boolean getFormEvent() throws JposException {
		try {
			return this.mx8xxFormService.getFormEvent();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public boolean getFormInit() throws JposException {
		try {
			return this.mx8xxFormService.getFormInit();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public boolean getFormLoaded() throws JposException {
		try {
			return this.mx8xxFormService.getFormLoaded();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public boolean getFormShown() throws JposException {
		try {
			return this.mx8xxFormService.getFormShown();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public int getKeyPadEvent() throws JposException {
		try {
			return this.mx8xxFormService.getKeyPadEvent();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String getKeyPadEventValue() throws JposException {
		try {
			return this.mx8xxFormService.getKeyPadEventValue();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public int getMaximumX() throws JposException {
		try {
			return this.mx8xxFormService.getMaximumX();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public int getMaximumY() throws JposException {
		try {
			return this.mx8xxFormService.getMaximumY();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getMiddleInitial() throws JposException {
		return this.mx8xxFormService.getMiddleInitial();
	}

	@Override
	public void getPINData(String paramString1, String paramString2)
			throws JposException {
		try {
			this.mx8xxFormService.getPINData(paramString1, paramString2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public Point[] getPointArray() throws JposException {
		try {
			return this.mx8xxFormService.getPointArray();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getProperty(int paramInt) throws JposException {
		try {
			return this.mx8xxFormService.getProperty(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public byte[] getRawData() throws JposException {
		try {
			return this.mx8xxFormService.getRawData();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getServiceCode() throws JposException {
		return this.mx8xxFormService.getServiceCode();
	}

	@Override
	public int getSignaturePointCount() throws JposException {
		try {
			return this.mx8xxFormService.getSignaturePointCount();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public String getSuffix() throws JposException {
		return this.mx8xxFormService.getSuffix();
	}

	@Override
	public String getSurname() throws JposException {
		return this.mx8xxFormService.getSurname();
	}

	@Override
	public String getTitle() throws JposException {
		return this.mx8xxFormService.getTitle();
	}

	@Override
	public byte[] getTrack1Data() throws JposException {
		try {
			return this.mx8xxFormService.getTrack1Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public byte[] getTrack1DiscretionaryData() throws JposException {
		return this.mx8xxFormService.getTrack1DiscretionaryData();
	}

	@Override
	public byte[] getTrack2Data() throws JposException {
		try {
			return this.mx8xxFormService.getTrack2Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public byte[] getTrack2DiscretionaryData() throws JposException {
		return this.mx8xxFormService.getTrack2DiscretionaryData();
	}

	@Override
	public byte[] getTrack3Data() throws JposException {
		try {
			return this.mx8xxFormService.getTrack3Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public byte[] getTrack4Data() throws JposException {
		try {
			return this.mx8xxFormService.getTrack4Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void initForm(String paramString) throws JposException {
		try {
			this.mx8xxFormService.initForm(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot initialize form",
					localException);
		}
	}

	@Override
	public void isFormLoaded(String paramString) throws JposException {
		try {
			this.mx8xxFormService.isFormLoaded(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void loadFile(String paramString) throws JposException {
		try {
			this.mx8xxFormService.loadFile(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot load fil", localException);
		}
	}

	@Override
	public void loadForm(String paramString) throws JposException {
		try {
			this.mx8xxFormService.loadForm(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot load form", localException);
		}
	}

	@Override
	public void removeFile(String paramString) throws JposException {
		try {
			this.mx8xxFormService.removeFile(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot delete file", localException);
		}
	}

	@Override
	public void removeForm(String paramString) throws JposException {
		try {
			this.mx8xxFormService.removeForm(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot delete form", localException);
		}
	}

	@Override
	public void resetSignaturePointCount() throws JposException {
		try {
			this.mx8xxFormService.resetSignaturePointCount();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void setDataEventEnabled(boolean paramBoolean) throws JposException {
		try {
			this.mx8xxFormService.setDataEventEnabled(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public boolean setProperty(int paramInt, String paramString)
			throws JposException {
		try {
			return this.mx8xxFormService.setProperty(paramInt, paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void showForm(String paramString) throws JposException {
		try {
			this.mx8xxFormService.showForm(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Cannot Display form", localException);
		}
	}

	@Override
	public String NFCData() {
		String str = null;
		str = this.mx8xxFormService.NFCData();
		return str;
	}

	@Override
	public void ResetandClearNFC() throws JposException {
		this.mx8xxFormService.ResetandClearNFC();

	}

	@Override
	public void StoreFile(String paramString) throws JposException {
		try {
			this.mx8xxFormService.StoreFile(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		}
	}

	@Override
	public void addListBoxItem(int paramInt1, int paramInt2,
			String paramString, boolean paramBoolean) throws JposException {
		try {
			this.mx8xxFormService.addListBoxItem(paramInt1, paramInt2, paramString,
					paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	public void addTextBoxText(int paramInt, String paramString)
			throws JposException {
		try {
			this.mx8xxFormService.addTextBoxText(paramInt, paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	public void batchMessages(String paramString, String[] paramArrayOfString)
			throws JposException {
		this.mx8xxFormService.batchMessages(paramString, paramArrayOfString);

	}

	@Override
	public void changeFontFile(String paramString) throws JposException {
		try {
			this.mx8xxFormService.changeFontFile(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void clearCardData() throws JposException {
		try {
			this.mx8xxFormService.clearCardData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void clearListBox(int paramInt) throws JposException {
		try {
			this.mx8xxFormService.clearListBox(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void clearTextBoxText(int paramInt) throws JposException {
		try {
			this.mx8xxFormService.clearTextBoxText(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void displayImageAt(int paramInt1, int paramInt2, String paramString)
			throws JposException {
		try {
			this.mx8xxFormService.displayImageAt(paramInt1, paramInt2, paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void displayText(int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int paramInt1, int paramInt2, int paramInt3, String paramString1,
			int paramInt4, String paramString2) throws JposException {
		try {
			this.mx8xxFormService.displayText(paramArrayOfInt1, paramArrayOfInt2,
					paramInt1, paramInt2, paramInt3, paramString1, paramInt4,
					paramString2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void enableNFC(boolean paramBoolean) throws JposException {
		this.mx8xxFormService.enableNFC(paramBoolean);

	}

	@Override
	public void ftpget(String paramString1, int paramInt, String paramString2,
			String paramString3, String paramString4) throws JposException {
		try {
			this.mx8xxFormService.ftpget(paramString1, paramInt, paramString2,
					paramString3, paramString4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void ftpput(String paramString1, int paramInt, String paramString2,
			String paramString3, String paramString4) throws JposException {
		try {
			this.mx8xxFormService.ftpput(paramString1, paramInt, paramString2,
					paramString3, paramString4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getBitmap(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) throws JposException {
		try {
			this.mx8xxFormService.getBitmap(paramInt1, paramInt2, paramInt3,
					paramInt4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public boolean getCapRealTimeData() throws JposException {
		return this.mx8xxFormService.getCapRealTimeData();
	}

	@Override
	public byte[] getCardAuthenticationData() throws JposException {
		try {
			return this.mx8xxFormService.getCardAuthenticationData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public int getCardAuthenticationDataLength() throws JposException {
		try {
			return this.mx8xxFormService.getCardAuthenticationDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void getCardDataFormat(String paramString1, int paramInt1,
			int paramInt2, int paramInt3, String paramString2, int paramInt4)
					throws JposException {
		try {
			this.mx8xxFormService.getCardDataFormat(paramString1, paramInt1,
					paramInt2, paramInt3, paramString2, paramInt4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getConfigVariable(String paramString,
			String[] paramArrayOfString) throws JposException {
		try {
			this.mx8xxFormService.getConfigVariable(paramString, paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String getEncryptedPAN() throws JposException {
		try {
			return this.mx8xxFormService.getEncryptedPAN();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String getEncryptedPIN() throws JposException {
		try {
			return this.mx8xxFormService.getEncryptedPIN();
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void getFile(String paramString) throws JposException {
		this.mx8xxFormService.getFile(paramString);

	}

	@Override
	public void getInputData(String[] paramArrayOfString) throws JposException {
		try {
			this.mx8xxFormService.getInputData(paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getListBoxItem(int paramInt1, int paramInt2,
			String[] paramArrayOfString) throws JposException {
		try {
			this.mx8xxFormService.getListBoxItem(paramInt1, paramInt2,
					paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getListBoxItemCount(int paramInt, int[] paramArrayOfInt)
			throws JposException {
		try {
			this.mx8xxFormService.getListBoxItemCount(paramInt, paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getListBoxSelectedItem(int paramInt, int[] paramArrayOfInt)
			throws JposException {
		try {
			this.mx8xxFormService.getListBoxSelectedItem(paramInt, paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	public void getLoadedForm(String[] paramArrayOfString) throws JposException {
		try {
			this.mx8xxFormService.getLoadedForm(paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public boolean getMsgBox() throws JposException {
		try {
			return this.mx8xxFormService.getMsgBox();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public int getMsgBoxRetVal() throws JposException {
		try {
			return this.mx8xxFormService.getMsgBoxRetVal();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getNFCData(String paramString) throws JposException {
		this.mx8xxFormService.getNFCData(paramString);
	}

	@Override
	public int getNFCDataLength() throws JposException {
		return this.mx8xxFormService.getNFCDataLength();
	}

	@Override
	public void getPINEx(String paramString1, String paramString2,
			int paramInt1, int paramInt2, boolean paramBoolean)
					throws JposException {
		try {
			this.mx8xxFormService.getPINEx(paramString1, paramString2, paramInt1,
					paramInt2, paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	public void getPNGImage(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) throws JposException {
		try {
			this.mx8xxFormService.getPNGImage(paramInt1, paramInt2, paramInt3,
					paramInt4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	public String getPanNumber(String paramString) throws JposException {
		return this.mx8xxFormService.getPanNumber(paramString);
	}

	@Override
	public int getPowerState() throws JposException {
		return this.mx8xxFormService.getPowerState();
	}

	@Override
	public void getPropertyValue(int paramInt1, int paramInt2, int paramInt3,
			String[] paramArrayOfString) throws JposException {

	}

	@Override
	public String getRandomizedPAN() throws JposException {
		try {
			return this.mx8xxFormService.getRandomizedPAN();
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public boolean getRealTimeDataEnabled() throws JposException {
		return this.mx8xxFormService.getRealTimeDataEnabled();
	}

	@Override
	public void getScreenShot(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) throws JposException {
		try {
			this.mx8xxFormService.getScreenShot(paramInt1, paramInt2, paramInt3,
					paramInt4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public byte[] getTrack1EncryptedData() throws JposException {
		try {
			return this.mx8xxFormService.getTrack1EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public int getTrack1EncryptedDataLength() throws JposException {
		try {
			return this.mx8xxFormService.getTrack1EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public byte[] getTrack2EncryptedData() throws JposException {
		try {
			return this.mx8xxFormService.getTrack2EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public int getTrack2EncryptedDataLength() throws JposException {
		try {
			return this.mx8xxFormService.getTrack2EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	@Override
	public void getVersion(String[] paramArrayOfString1,
			String[] paramArrayOfString2, String[] paramArrayOfString3,
			String[] paramArrayOfString4) throws JposException {
		try {
			this.mx8xxFormService.getVersion(paramArrayOfString1,
					paramArrayOfString2, paramArrayOfString3,
					paramArrayOfString4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void getVolume(int[] paramArrayOfInt1, int[] paramArrayOfInt2,
			int[] paramArrayOfInt3) throws JposException {
		try {
			this.mx8xxFormService.getVolume(paramArrayOfInt1, paramArrayOfInt2,
					paramArrayOfInt3);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void hideMsgBox() throws JposException {
		try {
			this.mx8xxFormService.hideMsgBox();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void listBoxClearState() throws JposException {
		try {
			this.mx8xxFormService.listBoxClearState();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void listBoxRestoreState(int paramInt) throws JposException {
		try {
			this.mx8xxFormService.listBoxRestoreState(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void msgBox(String paramString1, String paramString2, int paramInt1,
			int paramInt2, boolean paramBoolean) throws JposException {
		try {
			this.mx8xxFormService.msgBox(paramString1, paramString2, paramInt1,
					paramInt2, paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void removeListBoxItem(int paramInt1, int paramInt2)
			throws JposException {
		try {
			this.mx8xxFormService.removeListBoxItem(paramInt1, paramInt2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void reset() throws JposException {
		try {
			this.mx8xxFormService.reset();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void restartApp() throws JposException {
		try {
			this.mx8xxFormService.restartApp();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void restartTerminal() throws JposException {
		try {
			this.mx8xxFormService.restartTerminal();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public String sendRetVal() {
		return this.mx8xxFormService.sendRetVal();
	}

	@Override
	public void setClock(String paramString) throws JposException {
		try {
			this.mx8xxFormService.setClock(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setConfigVariable(String paramString1, String paramString2)
			throws JposException {
		try {
			this.mx8xxFormService.setConfigVariable(paramString1, paramString2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setListBoxTopItem(int paramInt1, int paramInt2)
			throws JposException {
		try {
			this.mx8xxFormService.setListBoxTopItem(paramInt1, paramInt2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setPowerNotify(int paramInt) throws JposException {
		this.mx8xxFormService.setPowerNotify(paramInt);

	}

	@Override
	public void setPropertyValue(int paramInt1, int paramInt2, int paramInt3,
			String paramString) throws JposException {
		try {
			this.mx8xxFormService.setPropertyValue(paramInt1, paramInt2, paramInt3,
					paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setRealTimeDataEnabled(boolean paramBoolean)
			throws JposException {
		this.mx8xxFormService.setRealTimeDataEnabled(paramBoolean);
	}

	@Override
	public void setRunwayLED(boolean paramBoolean) throws JposException {
		try {
			this.mx8xxFormService.setRunwayLED(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setSigCapBoxArea(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, boolean paramBoolean) throws JposException {
		try {
			this.mx8xxFormService.setSigCapBoxArea(paramInt1, paramInt2, paramInt3,
					paramInt4, paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setSigCapParams(int paramInt1, int paramInt2, int paramInt3,
			boolean paramBoolean1, boolean paramBoolean2, int paramInt4,
			boolean paramBoolean3, String paramString) throws JposException {
		try {
			this.mx8xxFormService.setSigCapParams(paramInt1, paramInt2, paramInt3,
					paramBoolean1, paramBoolean2, paramInt4, paramBoolean3,
					paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void setVolume(int paramInt1, int paramInt2, int paramInt3)
			throws JposException {
		try {
			this.mx8xxFormService.setVolume(paramInt1, paramInt2, paramInt3);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void soundTone(int paramInt1, int paramInt2, int paramInt3)
			throws JposException {
		try {
			this.mx8xxFormService.soundTone(paramInt1, paramInt2, paramInt3);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}
	}

	@Override
	public void zDownLoad(String paramString, boolean paramBoolean)
			throws JposException {
		try {
			this.mx8xxFormService.zDownLoad(paramString, paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Service not opened", localException);
		}

	}

	@Override
	protected EventCallbacks createEventCallbacks() {
		return new Mx8xxFormServiceCallbacks();
	}

	@Override
	protected void setDeviceService(BaseService paramBaseService, int paramInt)
			throws JposException {
		if (paramBaseService == null) {
			this.service1 = null;
		} else {
			if (this.serviceVersion >= 1000000)
				try {
					this.service1 = ((VFFormService1) paramBaseService);
				} catch (Exception localException1) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService12 interface",
							localException1);
				}
			if (this.serviceVersion < 1011000)
				return;

			//REVISIT: There is no serviceVersion qualifier here. I think there should be. 
			try {
				this.service111 = ((VFFormService111) paramBaseService);
			} catch (Exception localException2) {
				throw new JposException(
						104,
						"Service does not fully implement LineDisplayService12 interface",
						localException2);
			}
			if (this.serviceVersion >= 1013000)
				try {
					this.mx8xxFormService = ((VFFormService) paramBaseService);
				} catch (Exception localException1) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService12 interface",
							localException1);
				}
		}
	}
	
	
	@Override
	public void addDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.addElement(paramDirectIOListener);
		}
	}
	
	public void addDataListener(DataListener paramDataListener) {
		synchronized (this.dataListeners) {
			this.dataListeners.addElement(paramDataListener);
		}
	}

	public void removeDataListener(DataListener paramDataListener) {
		synchronized (this.dataListeners) {
			this.dataListeners.removeElement(paramDataListener);
		}
	}
	
	public void removeDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.removeElement(paramDirectIOListener);
		}
	}

	public void addErrorListener(ErrorListener paramErrorListener) {
		synchronized (this.errorListeners) {
			this.errorListeners.addElement(paramErrorListener);
		}
	}

	public void removeErrorListener(ErrorListener paramErrorListener) {
		synchronized (this.errorListeners) {
			this.errorListeners.removeElement(paramErrorListener);
		}
	}

	public void addStatusUpdateListener(
			StatusUpdateListener paramStatusUpdateListener) {
		synchronized (this.statusUpdateListeners) {
			this.statusUpdateListeners.addElement(paramStatusUpdateListener);
		}
	}

	public void removeStatusUpdateListener(
			StatusUpdateListener paramStatusUpdateListener) {
		synchronized (this.statusUpdateListeners) {
			this.statusUpdateListeners.removeElement(paramStatusUpdateListener);
		}
	}


	protected class Mx8xxFormServiceCallbacks implements EventCallbacks {
		public BaseControl getEventSource() {
			return Mx8xxForms.this;
		}

		public void fireDataEvent(DataEvent paramDataEvent) {
			synchronized (Mx8xxForms.this.dataListeners) {
				for (int i = 0; i < Mx8xxForms.this.dataListeners.size(); ++i)
					((DataListener) Mx8xxForms.this.dataListeners.elementAt(i))
					.dataOccurred(paramDataEvent);
			}
		}

		public void fireDirectIOEvent(DirectIOEvent paramDirectIOEvent) {
			synchronized (Mx8xxForms.this.directIOListeners) {
				for (int i = 0; i < Mx8xxForms.this.directIOListeners.size(); ++i)
					((DirectIOListener) Mx8xxForms.this.directIOListeners
							.elementAt(i)).directIOOccurred(paramDirectIOEvent);
			}
		}

		public void fireErrorEvent(ErrorEvent paramErrorEvent) {
			synchronized (Mx8xxForms.this.errorListeners) {
				for (int i = 0; i < Mx8xxForms.this.errorListeners.size(); ++i)
					((ErrorListener) Mx8xxForms.this.errorListeners.elementAt(i))
					.errorOccurred(paramErrorEvent);
			}
		}

		public void fireOutputCompleteEvent(
				OutputCompleteEvent paramOutputCompleteEvent) {
		}

		public void fireStatusUpdateEvent(
				StatusUpdateEvent paramStatusUpdateEvent) {
			synchronized (Mx8xxForms.this.statusUpdateListeners) {
				for (int i = 0; i < Mx8xxForms.this.statusUpdateListeners.size(); ++i)
					((StatusUpdateListener) Mx8xxForms.this.statusUpdateListeners
							.elementAt(i))
							.statusUpdateOccurred(paramStatusUpdateEvent);
			}
		}
	}



	//implemented in service but not included as part of interface
	public void release() throws JposException {
		try {
			this.mx8xxFormService.release();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

	//implemented in service but not included as part of interface
	public void directIo(int paramInt, int[] paramArrayOfInt, Object paramObject) throws JposException {
		try {
			this.mx8xxFormService.directIO(paramInt, paramArrayOfInt, paramObject);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(101, "Control not opened", localException);
		}
	}

}
