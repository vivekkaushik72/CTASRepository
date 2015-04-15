package com.cox.ctas.vf.mx.adapters;

import java.util.Vector;

import jpos.BaseControl;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.MSRControl113;
import jpos.events.DataEvent;
import jpos.events.DataListener;
import jpos.events.DirectIOEvent;
import jpos.events.DirectIOListener;
import jpos.events.ErrorEvent;
import jpos.events.ErrorListener;
import jpos.events.OutputCompleteEvent;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.services.BaseService;
import jpos.services.EventCallbacks;
import jpos.services.MSRService110;
import jpos.services.MSRService111;
import jpos.services.MSRService112;
import jpos.services.MSRService113;
import jpos.services.MSRService12;
import jpos.services.MSRService13;
import jpos.services.MSRService14;
import jpos.services.MSRService15;
import jpos.services.MSRService16;
import jpos.services.MSRService17;
import jpos.services.MSRService18;
import jpos.services.MSRService19;

import com.verifone.javapos.services.mx8xx.MSRService;


public class Mx8xxMSRController extends BaseJposControl implements MSRControl113, JposConst {

	protected MSRService mx8xxService;
	protected MSRService12 service12;
	protected MSRService13 service13;
	protected MSRService14 service14;
	protected MSRService15 service15;
	protected MSRService16 service16;
	protected MSRService17 service17;
	protected MSRService18 service18;
	protected MSRService19 service19;
	protected MSRService110 service110;
	protected MSRService111 service111;
	protected MSRService112 service112;
	protected MSRService113 service113;
	protected Vector dataListeners = new Vector();
	protected Vector directIOListeners = new Vector();
	protected Vector errorListeners = new Vector();
	protected Vector statusUpdateListeners = new Vector();
	
	public Mx8xxMSRController() {
		this.deviceControlDescription = "Mx8xx MSR Device Control";
		this.deviceControlVersion = 1013000;
	}
	
	@Override
	public void authenticateDevice(byte[] paramArrayOfByte)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.authenticateDevice(paramArrayOfByte);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void deauthenticateDevice(byte[] paramArrayOfByte)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.deauthenticateDevice(paramArrayOfByte);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getAdditionalSecurityInformation() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getAdditionalSecurityInformation();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getCapCardAuthentication() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCapCardAuthentication();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getCapDataEncryption() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCapDataEncryption();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getCapDeviceAuthentication() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCapDeviceAuthentication();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapTrackDataMasking() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCapTrackDataMasking();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getCardAuthenticationData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCardAuthenticationData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getCardAuthenticationDataLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCardAuthenticationDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getCardPropertyList() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCardPropertyList();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getCardType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCardType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getCardTypeList() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getCardTypeList();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getDataEncryptionAlgorithm() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getDataEncryptionAlgorithm();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getDeviceAuthenticated() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getDeviceAuthenticated();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getDeviceAuthenticationProtocol() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getDeviceAuthenticationProtocol();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack1EncryptedData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack1EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTrack1EncryptedDataLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack1EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack2EncryptedData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack2EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTrack2EncryptedDataLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack2EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack3EncryptedData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack3EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTrack3EncryptedDataLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack3EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack4EncryptedData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack4EncryptedData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTrack4EncryptedDataLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getTrack4EncryptedDataLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getWriteCardType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			return this.mx8xxService.getWriteCardType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void retrieveCardProperty(String paramString,
			String[] paramArrayOfString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.retrieveCardProperty(paramString,
					paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void retrieveDeviceAuthenticationData(byte[] paramArrayOfByte)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.retrieveDeviceAuthenticationData(paramArrayOfByte);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setDataEncryptionAlgorithm(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.setDataEncryptionAlgorithm(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setWriteCardType(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.setWriteCardType(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public void updateKey(String paramString1, String paramString2)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1012000)
			throw new JposException(104,
					"Device Service is not 1.12.0 compliant.");
		try {
			this.mx8xxService.updateKey(paramString1, paramString2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void clearInputProperties() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			this.mx8xxService.clearInputProperties();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getCapWritableTracks() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			return this.mx8xxService.getCapWritableTracks();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getEncodingMaxLength() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			return this.mx8xxService.getEncodingMaxLength();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTracksToWrite() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			return this.mx8xxService.getTracksToWrite();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setTracksToWrite(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			this.mx8xxService.setTracksToWrite(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void writeTracks(byte[][] paramArrayOfByte, int paramInt)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			this.mx8xxService.writeTracks(paramArrayOfByte, paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void compareFirmwareVersion(String paramString, int[] paramArrayOfInt)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			this.mx8xxService.compareFirmwareVersion(paramString, paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapCompareFirmwareVersion() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			return this.mx8xxService.getCapCompareFirmwareVersion();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapUpdateFirmware() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			return this.mx8xxService.getCapUpdateFirmware();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void updateFirmware(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			this.mx8xxService.updateFirmware(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapStatisticsReporting() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			return this.mx8xxService.getCapStatisticsReporting();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapUpdateStatistics() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			return this.mx8xxService.getCapUpdateStatistics();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void resetStatistics(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.mx8xxService.resetStatistics(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public void retrieveStatistics(String[] paramArrayOfString)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.mx8xxService.retrieveStatistics(paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void updateStatistics(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.mx8xxService.updateStatistics(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapTransmitSentinels() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1005000)
			throw new JposException(104,
					"Device Service is not 1.5.0 compliant.");
		try {
			return this.mx8xxService.getCapTransmitSentinels();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack4Data() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1005000)
			throw new JposException(104,
					"Device Service is not 1.5.0 compliant.");
		try {
			return this.mx8xxService.getTrack4Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getTransmitSentinels() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1005000)
			throw new JposException(104,
					"Device Service is not 1.5.0 compliant.");
		try {
			return this.mx8xxService.getTransmitSentinels();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setTransmitSentinels(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1005000)
			throw new JposException(104,
					"Device Service is not 1.5.0 compliant.");
		try {
			this.mx8xxService.setTransmitSentinels(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public void addStatusUpdateListener(
			StatusUpdateListener paramStatusUpdateListener) {
		synchronized (this.statusUpdateListeners) {
			this.statusUpdateListeners.addElement(paramStatusUpdateListener);
		}
	}

	@Override
	public int getCapPowerReporting() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.mx8xxService.getCapPowerReporting();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getPowerNotify() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.mx8xxService.getPowerNotify();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getPowerState() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.mx8xxService.getPowerState();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void removeStatusUpdateListener(
			StatusUpdateListener paramStatusUpdateListener) {
		synchronized (this.statusUpdateListeners) {
			this.statusUpdateListeners.removeElement(paramStatusUpdateListener);
		}
	}

	@Override
	public void setPowerNotify(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			this.mx8xxService.setPowerNotify(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void addDataListener(DataListener paramDataListener) {
		synchronized (this.dataListeners) {
			this.dataListeners.addElement(paramDataListener);
		}
	}

	@Override
	public void addDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.addElement(paramDirectIOListener);
		}
	}

	@Override
	public void addErrorListener(ErrorListener paramErrorListener) {
		synchronized (this.errorListeners) {
			this.errorListeners.addElement(paramErrorListener);
		}
	}

	@Override
	public void clearInput() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.clearInput();
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public String getAccountNumber() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getAccountNumber();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getAutoDisable() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getAutoDisable();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapISO() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return mx8xxService.getCapISO();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapJISOne() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getCapJISOne();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapJISTwo() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getCapJISTwo();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getDataCount() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getDataCount();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getDataEventEnabled() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getDataEventEnabled();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getDecodeData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getDecodeData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getErrorReportingType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getErrorReportingType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getExpirationDate() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getExpirationDate();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getFirstName() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getFirstName();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getMiddleInitial() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getMiddleInitial();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getParseDecodeData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getParseDecodeData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getServiceCode() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getServiceCode();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getSuffix() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getSuffix();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getSurname() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getSurname();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public String getTitle() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTitle();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack1Data() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTrack1Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack1DiscretionaryData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTrack1DiscretionaryData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack2Data() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTrack2Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack2DiscretionaryData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTrack2DiscretionaryData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getTrack3Data() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTrack3Data();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getTracksToRead() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxService.getTracksToRead();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void removeDataListener(DataListener paramDataListener) {
		synchronized (this.dataListeners) {
			this.dataListeners.removeElement(paramDataListener);
		}
	}

	@Override
	public void removeDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.removeElement(paramDirectIOListener);
		}
	}

	@Override
	public void removeErrorListener(ErrorListener paramErrorListener) {
		synchronized (this.errorListeners) {
			this.errorListeners.removeElement(paramErrorListener);
		}
	}

	@Override
	public void setAutoDisable(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setAutoDisable(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public void setDataEventEnabled(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setDataEventEnabled(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setDecodeData(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setDecodeData(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setErrorReportingType(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setErrorReportingType(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setParseDecodeData(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setParseDecodeData(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
		
	}

	@Override
	public void setTracksToRead(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxService.setTracksToRead(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	protected EventCallbacks createEventCallbacks() {
		return new MSRCallbacks();
	}

	@Override
	protected void setDeviceService(BaseService paramBaseService, int paramInt)
			throws JposException {

		if (paramBaseService == null) {
			this.mx8xxService = null;
			this.service12 = null;
			this.service13 = null;
			this.service14 = null;
			this.service15 = null;
			this.service16 = null;
			this.service17 = null;
			this.service18 = null;
			this.service19 = null;
			this.service110 = null;
			this.service111 = null;
			this.service112 = null;
			this.service113 = null;
			
		} else {
			if (this.serviceVersion >= 1002000)
				try {
					this.service12 = ((MSRService12) paramBaseService);
				} catch (Exception localException1) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService12 interface",
							localException1);
				}
			if (this.serviceVersion >= 1003000)
				try {
					this.service13 = ((MSRService13) paramBaseService);
				} catch (Exception localException2) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService13 interface",
							localException2);
				}
			if (this.serviceVersion >= 1004000)
				try {
					this.service14 = ((MSRService14) paramBaseService);
				} catch (Exception localException3) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService14 interface",
							localException3);
				}
			if (this.serviceVersion >= 1005000)
				try {
					this.service15 = ((MSRService15) paramBaseService);
				} catch (Exception localException4) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService15 interface",
							localException4);
				}
			if (this.serviceVersion >= 1006000)
				try {
					this.service16 = ((MSRService16) paramBaseService);
				} catch (Exception localException5) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService16 interface",
							localException5);
				}
			if (this.serviceVersion >= 1007000)
				try {
					this.service17 = ((MSRService17) paramBaseService);
				} catch (Exception localException6) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService17 interface",
							localException6);
				}
			if (this.serviceVersion >= 1008000)
				try {
					this.service18 = ((MSRService18) paramBaseService);
				} catch (Exception localException7) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService18 interface",
							localException7);
				}
			if (this.serviceVersion >= 1009000)
				try {
					this.service19 = ((MSRService19) paramBaseService);
				} catch (Exception localException8) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService19 interface",
							localException8);
				}
			if (this.serviceVersion >= 1010000)
				try {
					this.service110 = ((MSRService110) paramBaseService);
				} catch (Exception localException9) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService110 interface",
							localException9);
				}
			if (this.serviceVersion >= 1011000)
				try {
					this.service111 = ((MSRService111) paramBaseService);
				} catch (Exception localException10) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService111 interface",
							localException10);
				}
			if (this.serviceVersion >= 1012000)
				try {
					this.service112 = ((MSRService112) paramBaseService);
				} catch (Exception localException11) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService112 interface",
							localException11);
				}
			if (this.serviceVersion < 1013000)
				return;
			try {
				this.service113 = ((MSRService113) paramBaseService);
			} catch (Exception localException12) {
				throw new JposException(
						104,
						"Service does not fully implement MSRService113 interface",
						localException12);
			}
			if (this.serviceVersion >= 1013000)
				try {
					this.mx8xxService = ((MSRService) paramBaseService);
				} catch (Exception localException11) {
					throw new JposException(
							104,
							"Service does not fully implement MSRService interface",
							localException11);
				}
		}
		
	}
	
	protected class MSRCallbacks implements EventCallbacks {
		public BaseControl getEventSource() {
			return Mx8xxMSRController.this;
		}

		public void fireDataEvent(DataEvent paramDataEvent) {
			synchronized (Mx8xxMSRController.this.dataListeners) {
				for (int i = 0; i < Mx8xxMSRController.this.dataListeners.size(); ++i)
					((DataListener) Mx8xxMSRController.this.dataListeners.elementAt(i))
							.dataOccurred(paramDataEvent);
			}
		}

		public void fireDirectIOEvent(DirectIOEvent paramDirectIOEvent) {
			synchronized (Mx8xxMSRController.this.directIOListeners) {
				for (int i = 0; i < Mx8xxMSRController.this.directIOListeners.size(); ++i)
					((DirectIOListener) Mx8xxMSRController.this.directIOListeners.elementAt(i))
							.directIOOccurred(paramDirectIOEvent);
			}
		}

		public void fireErrorEvent(ErrorEvent paramErrorEvent) {
			synchronized (Mx8xxMSRController.this.errorListeners) {
				for (int i = 0; i < Mx8xxMSRController.this.errorListeners.size(); ++i)
					((ErrorListener) Mx8xxMSRController.this.errorListeners.elementAt(i))
							.errorOccurred(paramErrorEvent);
			}
		}

		public void fireOutputCompleteEvent(
				OutputCompleteEvent paramOutputCompleteEvent) {
		}

		public void fireStatusUpdateEvent(
				StatusUpdateEvent paramStatusUpdateEvent) {
			synchronized (Mx8xxMSRController.this.statusUpdateListeners) {
				for (int i = 0; i < Mx8xxMSRController.this.statusUpdateListeners.size(); ++i)
					((StatusUpdateListener) Mx8xxMSRController.this.statusUpdateListeners
							.elementAt(i))
							.statusUpdateOccurred(paramStatusUpdateEvent);
			}
		}
	}

}
