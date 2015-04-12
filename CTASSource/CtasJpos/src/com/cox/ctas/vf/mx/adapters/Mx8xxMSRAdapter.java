package com.cox.ctas.vf.mx.adapters;

import jpos.JposException;
import jpos.MSR;
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

import com.cox.ctas.vf.mx.utility.CoxVfMxUtilities;
import com.verifone.util.Ascii;


public class Mx8xxMSRAdapter extends Mx8xxMSRController implements DataListener, ErrorListener,
	DirectIOListener, StatusUpdateListener {
	
protected static final String CLASSNAME = "CLASS: CoxVfMSRAdapter: ";
	
	/**services are defined in the jposxml.cfg file in a set order - position 0 represents
	 * the position of the MSR service configuration params*/
	private static final int MSR_SVC_POS_IN_CFG_FILE = 0;
	
	/**device power status constants*/
	private static final String DEVICE_PWR_STATUS_OFF_LINE = "OFF LINE";
	private static final String DEVICE_PWR_STATUS_ON_LINE = "ON LINE";
	private static final String DEVICE_PWR_STATUS_UNKNOWN = "UNKNOWN";
	
	private boolean m_bSC5000 = false;
	
	JposEntry localJposEntry;
	JposEntryRegistry localJposEntryRegistry;
	
	
	/**
	 * default constructor - retrieves the MSR service configuration
	 * information from the jposxml.cfg file and assigns value to a 
	 * local JposEntry variable. Then adds the MSR data listeners
	 */
	public Mx8xxMSRAdapter () {
		//retrieve the line display service params from the jpos configuration file
		localJposEntry = CoxVfMxUtilities.loadServiceConfig(MSR_SVC_POS_IN_CFG_FILE);
		
		//add MSR data listeners
		this.addDataListener(this);
		this.addErrorListener(this);
		this.addDirectIOListener(this);
		this.addStatusUpdateListener(this);
	}
	
	/**
	 * connects to, claims, and enables device
	 */
	public void openDevice(){
		try {
			this.open(this.getLocalJposEntry().getLogicalName());
			
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	/**
	 * used to claim device if in use by another service
	 */
	public void claimDevice(){
		try {
			this.claim(1000);
			
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	/**
	 * retrieves device power status
	 * 
	 * @return constant representing device power status
	 */
	public String getPowerStatus() {
		String status = null;
		
		try {
			int i = this.getPowerState();
			if (i == 2004)
				status = DEVICE_PWR_STATUS_OFF_LINE;
			if (i == 2001)
				status = DEVICE_PWR_STATUS_ON_LINE;
			if (i == 2000)
				status = DEVICE_PWR_STATUS_UNKNOWN;
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		
		return status;
	}
	
	@Override
	public void statusUpdateOccurred(StatusUpdateEvent paramStatusUpdateEvent) {
		if (paramStatusUpdateEvent.getStatus() == 2004) {
			JposException localJposException = new JposException(2004, 
					"Link Disconnected");
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
		if (paramStatusUpdateEvent.getStatus() != 2001)
			return;
		JposException localJposException = new JposException(2001,
				"Link Connected");
		CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
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
		if (!(paramDirectIOEvent.getSource() instanceof MSR)) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("Received DirectIOEvent from Service Object.");
		try {
			if (paramDirectIOEvent.getObject() != null) {
				byte[] arrayOfByte2 = (byte[]) (byte[]) paramDirectIOEvent
						.getObject();
				String str2 = Ascii.FormatBinaryData(arrayOfByte2);
				System.out.println("");
				System.out.println("Packet received: " + str2);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	@Override
	public void errorOccurred(ErrorEvent paramErrorEvent) {
			System.out.println("Unknown event source.");
			if (false == paramErrorEvent.getSource() instanceof MSR) {
				System.out.println("Unknown event source.");
				return;
			}
			try {
				if (108 == paramErrorEvent.getErrorCode()) {
					CoxVfMxUtilities.handleJposException(new JposException(108), CLASSNAME);
					System.out.println(" After Errror Occurred");
					return;
				}
				if (3 == paramErrorEvent.getErrorLocus())
					paramErrorEvent.setErrorResponse(13);
				String str1 = null;
				byte[] arrayOfByte = null;
				String str2 = this.getAccountNumber();
				if (1 == (0x1 & this.getTracksToRead())) {
					arrayOfByte = this.getTrack1Data();
					if (null != arrayOfByte) {
						str1 = new String(arrayOfByte);
						System.out.println(FormatBinaryData(str1));
					}
				}
				if (2 == (0x2 & this.getTracksToRead())) {
					arrayOfByte = this.getTrack2Data();
					if (null != arrayOfByte) {
						str1 = new String(arrayOfByte);
						System.out.println(FormatBinaryData(str1));
					}
				}
				if (4 == (0x4 & this.getTracksToRead())) {
					arrayOfByte = this.getTrack3Data();
					if (null != arrayOfByte) {
						str1 = new String(arrayOfByte);
						System.out.println(FormatBinaryData(str1));
					}
				}
				if (8 == (0x8 & this.getTracksToRead())) {
					arrayOfByte = this.getTrack4Data();
					if (null != arrayOfByte) {
						str1 = new String(arrayOfByte);
						str1 = FormatBinaryData(str1);
						int i = 60;
						int j = 0;
						int k = i;
						if (str1 != null) {
							while (str1.length() - k > i) {
								System.out.println("RFID TAGS = :"
												+ str1.subSequence(j, k) + ": "
												+ "\n");
								j = k;
								k += i;
							}
							System.out.println("RFID TAGS = :"
									+ str1.subSequence(k, str1.length())
									+ ": "
									+ "\n");
						}
					}
				}
				
					if (this.getTrack1EncryptedData() != null)
						System.out.println("\nTRACK1 Encrypted Data: "
								+ new String(this.getTrack1EncryptedData())
								+ "\n ");
					else
						System.out.println("\nTRACK1 Encrypted Data: " + "null" + "\n ");
					if (this.getTrack2EncryptedData() != null)
						System.out.println("\nTRACK2 Encrypted Data: "
								+ new String(this.getTrack2EncryptedData())
								+ "\n ");
					else
						System.out.println("\nTRACK2 Encrypted Data: " + "null" + "\n ");
					System.out.println("\nKEY ID: "
							+ new String(this.getCardAuthenticationData()));
				
				String str3 = null;
				str3 = new String(this.getPhysicalDeviceName());
				if (str3 != null) {
					str1 = new String(str3.substring(7));
					System.out.println(str1);
				} else 
					System.out.println("UNKNOWN");
			} catch (JposException localJposException) {
				CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			}
		}
		
	@SuppressWarnings("unused")
	@Override
	public void dataOccurred(DataEvent paramDataEvent) {
		System.out.println("dataOccurred");
		int[] arrayOfInt = { 1 };
		if (false == paramDataEvent.getSource() instanceof MSR) {
			System.out.println("Unknown event source.");
			return;
		}
		try {
			String str1 = null;
			byte[] arrayOfByte = null;
			String str2 = this.getAccountNumber();
			if (1 == (0x1 & this.getTracksToRead())) {
				arrayOfByte = this.getTrack1Data();
				if (null != arrayOfByte) {
					str1 = new String(arrayOfByte);
					System.out.println(FormatBinaryData(str1));
				}
			}
			if (2 == (0x2 & this.getTracksToRead())) {
				arrayOfByte = this.getTrack2Data();
				if (null != arrayOfByte) {
					str1 = new String(arrayOfByte);
					System.out.println(FormatBinaryData(str1));
				}
			}
			if ((!(this.m_bSC5000))
					&& (4 == (0x4 & this.getTracksToRead()))) {
				arrayOfByte = this.getTrack3Data();
				if (null != arrayOfByte) {
					str1 = new String(arrayOfByte);
					System.out.println(FormatBinaryData(str1));
				}
			}
			if (8 == (0x8 & this.getTracksToRead())) {
				arrayOfByte = this.getTrack4Data();
				if (null != arrayOfByte) {
					str1 = new String(arrayOfByte);
					str1 = FormatBinaryData(str1);
					int i = 60;
					int j = 0;
					int k = i;
					if (str1 != null) {
						while (str1.length() - k > i) {
							System.out.println("RFID TAGS = :"
											+ str1.subSequence(j, k) + ": "
											+ "\n");
							j = k;
							k += i;
						}
						System.out.println("RFID TAGS = :"
								+ str1.subSequence(k, str1.length())
								+ ": "
								+ "\n");
					}
				}
			}
			
				if ((this.getTrack1EncryptedData() != null))
					System.out.println("\nTRACK1 Encrypted Data: "
							+ new String(this.getTrack1EncryptedData())
							+ "\n ");
				else
					System.out.println("\nTRACK1 Encrypted Data: " + "null" + "\n ");
				if ((this.getTrack2EncryptedData() != null))
					System.out.println("\nTRACK2 Encrypted Data: "
							+ new String(this.getTrack2EncryptedData())
							+ "\n ");
				else
					System.out.println("\nTRACK2 Encrypted Data: " + "null" + "\n ");
				System.out.println("\nKEY ID: "
						+ new String(this.getCardAuthenticationData()));
			
			String str3 = null;
			str3 = new String(this.getPhysicalDeviceName());
			if (str3 != null) {
				str1 = new String(str3.substring(7));
				System.out.println(str1);
			} else 
				System.out.println("UNKNOWN");
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	private String FormatBinaryData(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramString.length(); ++i)
			localStringBuffer.append(Ascii
					.ReplaceNonPrintableChar((byte) paramString.charAt(i)));
		return localStringBuffer.toString();
	}

	private void populateExtendedData() {
		try {
			String str = null;
			byte[] arrayOfByte = null;
			System.out.println(FormatBinaryData(this
					.getFirstName()));
			System.out.println(FormatBinaryData(this
					.getMiddleInitial()));
			System.out.println(FormatBinaryData(this.getSurname()));
			System.out.println(FormatBinaryData(this
					.getServiceCode()));
			if (1 == (0x1 & this.getTracksToRead())) {
				arrayOfByte = this.getTrack1DiscretionaryData();
				if (null != arrayOfByte) {
					str = new String(arrayOfByte);
					System.out.println(FormatBinaryData(str));
				}
			}
			if (2 == (0x2 & this.getTracksToRead())) {
				arrayOfByte = this.getTrack2DiscretionaryData();
				if (null != arrayOfByte) {
					str = new String(arrayOfByte);
					System.out.println(FormatBinaryData(str));
				}
			}
			
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}
	
	public JposEntry getLocalJposEntry() {
		return localJposEntry;
	}

	public String getClassname() {
		return CLASSNAME;
	}
}
