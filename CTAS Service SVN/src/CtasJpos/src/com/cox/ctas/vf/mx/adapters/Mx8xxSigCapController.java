package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.util.Vector;

import jpos.BaseControl;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.SignatureCaptureControl113;
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
import jpos.services.SignatureCaptureService110;
import jpos.services.SignatureCaptureService111;
import jpos.services.SignatureCaptureService112;
import jpos.services.SignatureCaptureService113;
import jpos.services.SignatureCaptureService12;
import jpos.services.SignatureCaptureService13;
import jpos.services.SignatureCaptureService14;
import jpos.services.SignatureCaptureService15;
import jpos.services.SignatureCaptureService16;
import jpos.services.SignatureCaptureService17;
import jpos.services.SignatureCaptureService18;
import jpos.services.SignatureCaptureService19;

import com.verifone.javapos.services.mx8xx.SignatureCaptureService;

public class Mx8xxSigCapController extends BaseJposControl implements 
SignatureCaptureControl113, JposConst {

	protected SignatureCaptureService mx8xxSigCap;
	protected SignatureCaptureService12 service12;
	protected SignatureCaptureService13 service13;
	protected SignatureCaptureService14 service14;
	protected SignatureCaptureService15 service15;
	protected SignatureCaptureService16 service16;
	protected SignatureCaptureService17 service17;
	protected SignatureCaptureService18 service18;
	protected SignatureCaptureService19 service19;
	protected SignatureCaptureService110 service110;
	protected SignatureCaptureService111 service111;
	protected SignatureCaptureService112 service112;
	protected SignatureCaptureService113 service113;
	protected Vector dataListeners = new Vector();
	protected Vector directIOListeners = new Vector();
	protected Vector errorListeners = new Vector();
	protected Vector statusUpdateListeners = new Vector();


	public Mx8xxSigCapController () {
		this.deviceControlDescription = "JavaPOS Mx8xx SignatureCapture Device Control";
		this.deviceControlVersion = 101300;
		
	}

	@Override
	public void clearInputProperties() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1010000)
			throw new JposException(104,
					"Device Service is not 1.10.0 compliant.");
		try {
			this.mx8xxSigCap.clearInputProperties();
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
			this.mx8xxSigCap.compareFirmwareVersion(paramString, paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapCompareFirmwareVersion() throws JposException {
		return true;
	}

	@Override
	public boolean getCapUpdateFirmware() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			return this.mx8xxSigCap.getCapUpdateFirmware();
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
			this.mx8xxSigCap.updateFirmware(paramString);
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
			return this.mx8xxSigCap.getCapStatisticsReporting();
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
			return this.mx8xxSigCap.getCapUpdateStatistics();
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
			this.mx8xxSigCap.resetStatistics(paramString);
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
			this.mx8xxSigCap.retrieveStatistics(paramArrayOfString);
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
			this.mx8xxSigCap.updateStatistics(paramString);
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
			return this.mx8xxSigCap.getCapPowerReporting();
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
			return this.mx8xxSigCap.getPowerNotify();
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
			return this.mx8xxSigCap.getPowerState();
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
			this.mx8xxSigCap.setPowerNotify(paramInt);
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
	public void beginCapture(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxSigCap.beginCapture(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void clearInput() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxSigCap.clearInput();
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void endCapture() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxSigCap.endCapture();
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
			return this.mx8xxSigCap.getAutoDisable();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapDisplay() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getCapDisplay();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapRealTimeData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getCapRealTimeData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getCapUserTerminated() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getCapUserTerminated();
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
			return this.mx8xxSigCap.getDataCount();
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
			return this.mx8xxSigCap.getDataEventEnabled();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getMaximumX() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getMaximumX();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public int getMaximumY() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getMaximumY();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public Point[] getPointArray() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getPointArray();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public byte[] getRawData() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.mx8xxSigCap.getRawData();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public boolean getRealTimeDataEnabled() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getRealTimeDataEnabled();
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
			this.mx8xxSigCap.setAutoDisable(paramBoolean);
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
			this.mx8xxSigCap.setDataEventEnabled(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	@Override
	public void setRealTimeDataEnabled(boolean paramBoolean)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.mx8xxSigCap.setRealTimeDataEnabled(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}



	@Override
	protected void setDeviceService(BaseService paramBaseService, int paramInt)
			throws JposException {
		if (paramBaseService == null) {
			this.mx8xxSigCap = null;
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
					this.service12 = ((SignatureCaptureService12) paramBaseService);
				} catch (Exception localException1) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService12 interface",
							localException1);
				}
			if (this.serviceVersion >= 1003000)
				try {
					this.service13 = ((SignatureCaptureService13) paramBaseService);
				} catch (Exception localException2) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService13 interface",
							localException2);
				}
			if (this.serviceVersion >= 1004000)
				try {
					this.service14 = ((SignatureCaptureService14) paramBaseService);
				} catch (Exception localException3) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService14 interface",
							localException3);
				}
			if (this.serviceVersion >= 1005000)
				try {
					this.service15 = ((SignatureCaptureService15) paramBaseService);
				} catch (Exception localException4) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService15 interface",
							localException4);
				}
			if (this.serviceVersion >= 1006000)
				try {
					this.service16 = ((SignatureCaptureService16) paramBaseService);
				} catch (Exception localException5) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService16 interface",
							localException5);
				}
			if (this.serviceVersion >= 1007000)
				try {
					this.service17 = ((SignatureCaptureService17) paramBaseService);
				} catch (Exception localException6) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService17 interface",
							localException6);
				}
			if (this.serviceVersion >= 1008000)
				try {
					this.service18 = ((SignatureCaptureService18) paramBaseService);
				} catch (Exception localException7) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService18 interface",
							localException7);
				}
			if (this.serviceVersion >= 1009000)
				try {
					this.service19 = ((SignatureCaptureService19) paramBaseService);
				} catch (Exception localException8) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService19 interface",
							localException8);
				}
			if (this.serviceVersion >= 1010000)
				try {
					this.service110 = ((SignatureCaptureService110) paramBaseService);
				} catch (Exception localException9) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService110 interface",
							localException9);
				}
			if (this.serviceVersion >= 1011000)
				try {
					this.service111 = ((SignatureCaptureService111) paramBaseService);
				} catch (Exception localException10) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService111 interface",
							localException10);
				}
			if (this.serviceVersion >= 1012000)
				try {
					this.service112 = ((SignatureCaptureService112) paramBaseService);
				} catch (Exception localException11) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService112 interface",
							localException11);
				}
			try {
				this.service113 = ((SignatureCaptureService113) paramBaseService);
			} catch (Exception localException12) {
				throw new JposException(
						104,
						"Service does not fully implement SignatureCaptureService113 interface",
						localException12);
			}
			if (this.serviceVersion < 1014000)
				return;
			if (this.serviceVersion >= 1013000)
				try {
					this.mx8xxSigCap = ((SignatureCaptureService) paramBaseService);
				} catch (Exception localException13) {
					throw new JposException(
							104,
							"Service does not fully implement SignatureCaptureService112 interface",
							localException13);
				}
		}
	}
	
	protected EventCallbacks createEventCallbacks() {
		return new SignatureCaptureCallbacks();
	}

	protected class SignatureCaptureCallbacks implements EventCallbacks {

		public BaseControl getEventSource() {
			return Mx8xxSigCapController.this;
		}

		public void fireDataEvent(DataEvent paramDataEvent) {
			synchronized (Mx8xxSigCapController.this.dataListeners) {
				for (int i = 0; i < Mx8xxSigCapController.this.dataListeners.size(); ++i)
					((DataListener) Mx8xxSigCapController.this.dataListeners
							.elementAt(i)).dataOccurred(paramDataEvent);
			}
		}

		public void fireDirectIOEvent(DirectIOEvent paramDirectIOEvent) {
			synchronized (Mx8xxSigCapController.this.directIOListeners) {
				for (int i = 0; i < Mx8xxSigCapController.this.directIOListeners
						.size(); ++i)
					((DirectIOListener) Mx8xxSigCapController.this.directIOListeners
							.elementAt(i)).directIOOccurred(paramDirectIOEvent);
			}
		}

		public void fireErrorEvent(ErrorEvent paramErrorEvent) {
			synchronized (Mx8xxSigCapController.this.errorListeners) {
				for (int i = 0; i < Mx8xxSigCapController.this.errorListeners.size(); ++i)
					((ErrorListener) Mx8xxSigCapController.this.errorListeners
							.elementAt(i)).errorOccurred(paramErrorEvent);
			}
		}

		public void fireOutputCompleteEvent(
				OutputCompleteEvent paramOutputCompleteEvent) {
		}

		public void fireStatusUpdateEvent(
				StatusUpdateEvent paramStatusUpdateEvent) {
			synchronized (Mx8xxSigCapController.this.statusUpdateListeners) {
				for (int i = 0; i < Mx8xxSigCapController.this.statusUpdateListeners
						.size(); ++i)
					((StatusUpdateListener) Mx8xxSigCapController.this.statusUpdateListeners
							.elementAt(i))
							.statusUpdateOccurred(paramStatusUpdateEvent);
			}
		}
	}

}


