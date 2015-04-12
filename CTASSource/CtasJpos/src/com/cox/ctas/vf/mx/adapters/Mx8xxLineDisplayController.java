package com.cox.ctas.vf.mx.adapters;

import java.util.Vector;

import jpos.BaseControl;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.LineDisplay;
import jpos.LineDisplayControl113;

import jpos.events.DataEvent;
import jpos.events.DirectIOEvent;
import jpos.events.DirectIOListener;
import jpos.events.ErrorEvent;
import jpos.events.OutputCompleteEvent;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.services.BaseService;
import jpos.services.EventCallbacks;
import jpos.services.LineDisplayService110;
import jpos.services.LineDisplayService111;
import jpos.services.LineDisplayService112;
import jpos.services.LineDisplayService113;
import jpos.services.LineDisplayService12;
import jpos.services.LineDisplayService13;
import jpos.services.LineDisplayService14;
import jpos.services.LineDisplayService15;
import jpos.services.LineDisplayService16;
import jpos.services.LineDisplayService17;
import jpos.services.LineDisplayService18;
import jpos.services.LineDisplayService19;

public class Mx8xxLineDisplayController extends BaseJposControl implements
LineDisplayControl113, JposConst {

	protected LineDisplay mx8xxLineDisplay;
	protected LineDisplayService12 service12;
	protected LineDisplayService13 service13;
	protected LineDisplayService14 service14;
	protected LineDisplayService15 service15;
	protected LineDisplayService16 service16;
	protected LineDisplayService17 service17;
	protected LineDisplayService18 service18;
	protected LineDisplayService19 service19;
	protected LineDisplayService110 service110;
	protected LineDisplayService111 service111;
	protected LineDisplayService112 service112;
	protected LineDisplayService113 service113;
	protected Vector directIOListeners = new Vector();
	protected Vector statusUpdateListeners = new Vector();

	
	
	public Mx8xxLineDisplayController() {
		this.deviceControlDescription = "JavaPOS Mx8xx LineDisplay Device Control";
		this.deviceControlVersion = 1013000;
	}
	
	
	public int getCapBlink() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapBlink();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapBrightness() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapBrightness();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCapCharacterSet() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapCharacterSet();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapDescriptors() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapDescriptors();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapHMarquee() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapHMarquee();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapICharWait() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapICharWait();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapVMarquee() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCapVMarquee();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCapPowerReporting() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.service13.getCapPowerReporting();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapBlinkRate() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCapBlinkRate();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCapCursorType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCapCursorType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapCustomGlyph() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCapCustomGlyph();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCapReadBack() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCapReadBack();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCapReverse() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCapReverse();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapBitmap() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getCapBitmap();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapMapCharacterSet() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getCapMapCharacterSet();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapScreenMode() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getCapScreenMode();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapStatisticsReporting() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			return this.service18.getCapStatisticsReporting();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapUpdateStatistics() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			return this.service18.getCapUpdateStatistics();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapCompareFirmwareVersion() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			return this.service19.getCapCompareFirmwareVersion();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCapUpdateFirmware() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			return this.service19.getCapUpdateFirmware();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCharacterSet() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCharacterSet();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCharacterSet(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setCharacterSet(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public String getCharacterSetList() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCharacterSetList();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getColumns() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getColumns();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCurrentWindow() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCurrentWindow();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCurrentWindow(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setCurrentWindow(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCursorColumn() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCursorColumn();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCursorColumn(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setCursorColumn(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCursorRow() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCursorRow();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCursorRow(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setCursorRow(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getCursorUpdate() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getCursorUpdate();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCursorUpdate(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setCursorUpdate(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getDeviceBrightness() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getDeviceBrightness();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setDeviceBrightness(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setDeviceBrightness(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getDeviceColumns() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getDeviceColumns();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getDeviceDescriptors() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getDeviceDescriptors();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getDeviceRows() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getDeviceRows();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getDeviceWindows() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getDeviceWindows();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getInterCharacterWait() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getInterCharacterWait();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setInterCharacterWait(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setInterCharacterWait(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMarqueeFormat() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getMarqueeFormat();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setMarqueeFormat(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setMarqueeFormat(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMarqueeRepeatWait() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getMarqueeRepeatWait();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setMarqueeRepeatWait(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setMarqueeRepeatWait(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMarqueeType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getMarqueeType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setMarqueeType(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setMarqueeType(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMarqueeUnitWait() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getMarqueeUnitWait();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setMarqueeUnitWait(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setMarqueeUnitWait(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getRows() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			return this.service12.getRows();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getPowerNotify() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.service13.getPowerNotify();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setPowerNotify(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			this.service13.setPowerNotify(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getPowerState() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1003000)
			throw new JposException(104,
					"Device Service is not 1.3.0 compliant.");
		try {
			return this.service13.getPowerState();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getBlinkRate() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getBlinkRate();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setBlinkRate(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			this.service16.setBlinkRate(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getCursorType() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCursorType();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setCursorType(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			this.service16.setCursorType(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public String getCustomGlyphList() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getCustomGlyphList();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getGlyphHeight() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getGlyphHeight();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getGlyphWidth() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			return this.service16.getGlyphWidth();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public boolean getMapCharacterSet() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getMapCharacterSet();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setMapCharacterSet(boolean paramBoolean) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			this.service17.setMapCharacterSet(paramBoolean);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMaximumX() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getMaximumX();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getMaximumY() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getMaximumY();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public int getScreenMode() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getScreenMode();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setScreenMode(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			this.service17.setScreenMode(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public String getScreenModeList() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			return this.service17.getScreenModeList();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void clearDescriptors() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.clearDescriptors();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void clearText() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.clearText();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void createWindow(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.createWindow(paramInt1, paramInt2, paramInt3,
					paramInt4, paramInt5, paramInt6);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void destroyWindow() throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.destroyWindow();
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void displayText(String paramString, int paramInt)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.displayText(paramString, paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void displayTextAt(int paramInt1, int paramInt2, String paramString,
			int paramInt3) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.displayTextAt(paramInt1, paramInt2, paramString,
					paramInt3);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void refreshWindow(int paramInt) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.refreshWindow(paramInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void scrollText(int paramInt1, int paramInt2) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.scrollText(paramInt1, paramInt2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setDescriptor(int paramInt1, int paramInt2)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		try {
			this.service12.setDescriptor(paramInt1, paramInt2);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void defineGlyph(int paramInt, byte[] paramArrayOfByte)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			this.service16.defineGlyph(paramInt, paramArrayOfByte);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void readCharacterAtCursor(int[] paramArrayOfInt)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1006000)
			throw new JposException(104,
					"Device Service is not 1.6.0 compliant.");
		try {
			this.service16.readCharacterAtCursor(paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void displayBitmap(String paramString, int paramInt1, int paramInt2,
			int paramInt3) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			this.service17.displayBitmap(paramString, paramInt1, paramInt2,
					paramInt3);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void setBitmap(int paramInt1, String paramString, int paramInt2,
			int paramInt3, int paramInt4) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1007000)
			throw new JposException(104,
					"Device Service is not 1.7.0 compliant.");
		try {
			this.service17.setBitmap(paramInt1, paramString, paramInt2,
					paramInt3, paramInt4);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void resetStatistics(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.service18.resetStatistics(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void retrieveStatistics(String[] paramArrayOfString)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.service18.retrieveStatistics(paramArrayOfString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void updateStatistics(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1008000)
			throw new JposException(104,
					"Device Service is not 1.8.0 compliant.");
		try {
			this.service18.updateStatistics(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void compareFirmwareVersion(String paramString, int[] paramArrayOfInt)
			throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			this.service19.compareFirmwareVersion(paramString, paramArrayOfInt);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	public void updateFirmware(String paramString) throws JposException {
		if (!(this.bOpen))
			throw new JposException(101, "Control not opened");
		if (this.serviceVersion < 1009000)
			throw new JposException(104,
					"Device Service is not 1.9.0 compliant.");
		try {
			this.service19.updateFirmware(paramString);
		} catch (JposException localJposException) {
			throw localJposException;
		} catch (Exception localException) {
			throw new JposException(111,
					"Unhandled exception from Device Service", localException);
		}
	}

	protected EventCallbacks createEventCallbacks() {
		return new LineDisplayCallbacks();
	}

	protected void setDeviceService(BaseService paramBaseService, int paramInt)
			throws JposException {
		if (paramBaseService == null) {
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
					this.service12 = ((LineDisplayService12) paramBaseService);
				} catch (Exception localException1) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService12 interface",
							localException1);
				}
			if (this.serviceVersion >= 1003000)
				try {
					this.service13 = ((LineDisplayService13) paramBaseService);
				} catch (Exception localException2) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService13 interface",
							localException2);
				}
			if (this.serviceVersion >= 1004000)
				try {
					this.service14 = ((LineDisplayService14) paramBaseService);
				} catch (Exception localException3) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService14 interface",
							localException3);
				}
			if (this.serviceVersion >= 1005000)
				try {
					this.service15 = ((LineDisplayService15) paramBaseService);
				} catch (Exception localException4) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService15 interface",
							localException4);
				}
			if (this.serviceVersion >= 1006000)
				try {
					this.service16 = ((LineDisplayService16) paramBaseService);
				} catch (Exception localException5) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService16 interface",
							localException5);
				}
			if (this.serviceVersion >= 1007000)
				try {
					this.service17 = ((LineDisplayService17) paramBaseService);
				} catch (Exception localException6) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService17 interface",
							localException6);
				}
			if (this.serviceVersion >= 1008000)
				try {
					this.service18 = ((LineDisplayService18) paramBaseService);
				} catch (Exception localException7) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService18 interface",
							localException7);
				}
			if (this.serviceVersion >= 1009000)
				try {
					this.service19 = ((LineDisplayService19) paramBaseService);
				} catch (Exception localException8) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService19 interface",
							localException8);
				}
			if (this.serviceVersion >= 1010000)
				try {
					this.service110 = ((LineDisplayService110) paramBaseService);
				} catch (Exception localException9) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService110 interface",
							localException9);
				}
			if (this.serviceVersion >= 1011000)
				try {
					this.service111 = ((LineDisplayService111) paramBaseService);
				} catch (Exception localException10) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService111 interface",
							localException10);
				}
			if (this.serviceVersion >= 1012000)
				try {
					this.service112 = ((LineDisplayService112) paramBaseService);
				} catch (Exception localException11) {
					throw new JposException(
							104,
							"Service does not fully implement LineDisplayService112 interface",
							localException11);
				}
			if (this.serviceVersion < 1013000)
				return;
			try {
				this.service113 = ((LineDisplayService113) paramBaseService);
			} catch (Exception localException12) {
				throw new JposException(
						104,
						"Service does not fully implement LineDisplayService113 interface",
						localException12);
			}
		}
	}

	public void addDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.addElement(paramDirectIOListener);
		}
	}

	public void removeDirectIOListener(DirectIOListener paramDirectIOListener) {
		synchronized (this.directIOListeners) {
			this.directIOListeners.removeElement(paramDirectIOListener);
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

	protected class LineDisplayCallbacks implements EventCallbacks {
		public BaseControl getEventSource() {
			return Mx8xxLineDisplayController.this;
		}

		public void fireDataEvent(DataEvent paramDataEvent) {
		}

		public void fireDirectIOEvent(DirectIOEvent paramDirectIOEvent) {
			synchronized (Mx8xxLineDisplayController.this.directIOListeners) {
				for (int i = 0; i < Mx8xxLineDisplayController.this.directIOListeners.size(); ++i)
					((DirectIOListener) Mx8xxLineDisplayController.this.directIOListeners
							.elementAt(i)).directIOOccurred(paramDirectIOEvent);
			}
		}

		public void fireErrorEvent(ErrorEvent paramErrorEvent) {
		}

		public void fireOutputCompleteEvent(
				OutputCompleteEvent paramOutputCompleteEvent) {
		}

		public void fireStatusUpdateEvent(
				StatusUpdateEvent paramStatusUpdateEvent) {
			synchronized (Mx8xxLineDisplayController.this.statusUpdateListeners) {
				for (int i = 0; i < Mx8xxLineDisplayController.this.statusUpdateListeners
						.size(); ++i)
					((StatusUpdateListener) Mx8xxLineDisplayController.this.statusUpdateListeners
							.elementAt(i))
							.statusUpdateOccurred(paramStatusUpdateEvent);
			}
		}
	}


}

