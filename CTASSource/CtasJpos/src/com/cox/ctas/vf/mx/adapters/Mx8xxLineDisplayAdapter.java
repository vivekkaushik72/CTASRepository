package com.cox.ctas.vf.mx.adapters;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.xml.sax.SAXParseException;

import com.cox.ctas.vf.mx.utility.CoxVfMxUtilities;
import com.verifone.util.Ascii;

import jpos.JposException;
import jpos.LineDisplay;
import jpos.config.JposEntry;
import jpos.events.DirectIOEvent;
import jpos.events.DirectIOListener;
import jpos.events.ErrorEvent;
import jpos.events.ErrorListener;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;

public class Mx8xxLineDisplayAdapter implements 
DirectIOListener, ErrorListener, StatusUpdateListener {

	protected static final String CLASSNAME = "CLASS: CoxVfLineDisplayAdapter: ";
	private static final int VF_LINE_DISPLAY_SVC_POS_IN_CFG_FILE = 4;
	private JposEntry localJposEntry;

	private LineDisplay ld = new LineDisplay();

	private byte[] m_dataReceived = null;
	private boolean m_bFileDownload = false;
	private boolean m_bAsyncFileSend = false;
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



	/**
	 * default constructor - retrieves line display service params from jpos
	 * configuration file. Adds listeners to local line display controller
	 */
	public Mx8xxLineDisplayAdapter () {
		//retrieve the line display service params from the jpos configuration file
		localJposEntry = CoxVfMxUtilities.loadServiceConfig(VF_LINE_DISPLAY_SVC_POS_IN_CFG_FILE);

		//add listeners to the line display controller
		ld.addDirectIOListener(this);
		ld.addStatusUpdateListener(this);
	}


	/**
	 * opens, claims, and enables device - this must be done in order
	 * for the service to act on the device
	 */
	public void openDevice() {
		try {
			ld.open(localJposEntry.getLogicalName());
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	/**
	 * releases device - this should be done once the service has finished
	 * executing actions on the device
	 */
	public void releaseDevice() {
		try {
			if (this.ld.getClaimed())
				this.ld.release();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	/**
	 * claims the device for use by the line display service
	 */
	public void claimDevice() {
		try {
			this.ld.claim(1000);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	public void checkDeviceHealth() {
		try {
			if(this.ld.getDeviceEnabled())	
				this.ld.checkHealth(1);
			else {
				this.ld.setDeviceEnabled(true);
				this.ld.checkHealth(1);
			}
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void setDeviceEnabled(Boolean status) {
		try {
			this.ld.setDeviceEnabled(status);	
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void refreshwindow() {
		try {
			this.ld.refreshWindow(this.ld.getCurrentWindow());
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void createwindow(int startRow, int startCol, int viewPortHeight, int viewPortWidth) {
		try {
			if (startRow == 0)
				throw new RuntimeException("Invalid Start Row entered");
			if (startCol == 0)
				throw new RuntimeException("Invalid Start Col entered");
			if (viewPortHeight == 0)
				throw new RuntimeException("Invalid View Port Height entered");
			if (viewPortWidth == 0)
				throw new RuntimeException("Ivalid View Port Width entered");
			this.ld.createWindow(startRow, startCol, viewPortHeight, 
					viewPortWidth, viewPortHeight, viewPortWidth);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void destroywindow() {
		try {
			this.ld.destroyWindow();
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void displayText(String text, int mode) {
		try {
			if (mode == 0 || mode == 2) 
				this.ld.displayText(text, mode);
			else
				this.ld.displayText(text, 0);
		} catch (JposException localJposException2) {
			CoxVfMxUtilities.handleJposException(localJposException2, CLASSNAME);
		}
	}

	void displayTextAT(String text, int startRow, int startCol, int mode) {
		try {
			ld.getCurrentWindow();
			ld.getCursorRow();

			if(mode == 0 || mode == 2) 
				this.ld.displayTextAt(startRow, startCol, text, mode);
			else
				this.ld.displayTextAt(startRow, startCol, text, 0);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		}
	}

	void displayBitmap(String text, int startCol, int startRow) {
		try {
			this.ld.displayBitmap(text, 0, startCol, startRow);
			System.out.println(this.ld.getCursorRow());
			System.out.println(Integer.toString(this.ld.getCursorColumn()));
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
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
			this.ld.directIO(100, arrayOfInt, str2);
		} catch (JposException localJposException) {
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	void sendFile(String fileName, int fileBlockSize) {
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
			if (str2.length() <= 0) {
				System.out
				.println("LineDisplayTest - SendFile - no filename specified!");
				return;
			}
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
			str5 = String.valueOf(fileBlockSize);
			m_iBlockSize = Integer.parseInt(str5);
			str5 = pad(str5, 4, '0', true);
			int i1;
			if (System.getProperty("os.name").startsWith("Windows"))
				i1 = str2.lastIndexOf("\\");
			else
				i1 = str2.lastIndexOf("/");
			String str6 = str2.substring(i1 + 1);
			str3 = "SFL<FS>" + str6 + "<FS>" + m_lFileSize + "<FS>" + str5
					+ "<FS>1";
			str3 = Ascii.UnBracket(str3);
			this.m_bFileDownload = true;
			this.m_bAsyncFileSend = true;
			//setCursor(Cursor.getPredefinedCursor(3));
			this.ld.directIO(100, arrayOfInt, str3);
		} catch (JposException localJposException) {
			//setCursor(null);
			CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
		} catch (IOException localIOException) {
			//setCursor(null);
			System.out.println("sendFile - error: "
					+ localIOException.getMessage());
			return;
		}
	}

	/*void syncSendFile(String fileName, int fileBlockSize)
{
	int[] arrayOfInt = { 1 };
	byte[] arrayOfByte1 = { 28 };
	String str1 = new String(arrayOfByte1);
	String str2 = null;
	String str3 = null;
	String str4 = null;
	int i = 28;
	long l = 0L;
	int j = 20000;
	int k = 256;
	int i1 = 0;
	int i2 = 0;
	int i3 = 0;
	int i4 = 0;
	int i5 = 0;
	int i6 = 0;
	String str7 = "Y";
	Object localObject = null;
	String str8 = null;
	String str9 = null;
	try
	{
		str2 = fileName;
		if (str2.length() <= 0)
		{
			System.out.println("LineDisplayTest - syncSendFile - no filename specified!");
			return;
		}
		File localFile = new File(str2);
		String str5 = "syncSendFile - File: " + localFile.getAbsoluteFile();
		System.out.println(str5);
		this.m_fis = new FileInputStream(localFile);
		this.m_bis = new BufferedInputStream(this.m_fis);
		this.m_dis = new DataInputStream(this.m_bis);
		m_lFileSize = localFile.length();
		this.m_baFileData = new byte[(int)m_lFileSize];
		this.m_dis.readFully(this.m_baFileData, 0, (int)m_lFileSize);
		m_iBytesSent = 0;
		m_iSeqNum = 0;
		str8 = String.valueOf(fileBlockSize);
		m_iBlockSize = Integer.parseInt(str8);
		str8 = pad(str8, 4, '0', true);
		str3 = "SFL<FS>" + str2 + "<FS>" + m_lFileSize + "<FS>" + str8 + "<FS>1";
		str3 = Ascii.UnBracket(str3);

		this.m_bFileDownload = true;
		this.ld.directIO(100, arrayOfInt, str3);
		do
		{
			i4 = waitForPacket("SFL", str4, j, false);
			if (i4 == 1)
			{
				System.out.println("SFL received");
				break label426:
			}
			if (i4 != -1)
				continue;
			System.out.println("NAK received!");
			return;
		} while (i4 != 2);
		System.out.println("Timed out waiting for SFL");
		return;
		label426: i1 = 0;
		str7 = "Y";
		while (m_iBytesSent < m_lFileSize)
		{
			if (m_lFileSize < m_iBlockSize)
			{
				str7 = "N";
				m_iBlockSize = (int)m_lFileSize;
			}
			else if (m_iBytesSent + m_iBlockSize >= m_lFileSize)
			{
				str7 = "N";
				m_iBlockSize = (int)m_lFileSize - m_iBytesSent;
			}
			else
			{
				str7 = "Y";
			}
			str8 = String.valueOf(m_iBlockSize);
			str8 = pad(str8, 4, '0', true);
			m_iSeqNum += 1;
			String str6 = String.valueOf(m_iSeqNum);
			str6 = pad(str6, 4, '0', true);
			str3 = "XDAT<FS>" + str7 + str8 + str6;
			str3 = Ascii.UnBracket(str3);
			byte[] arrayOfByte2 = new byte[14 + m_iBlockSize];
			byte[] arrayOfByte3 = str3.getBytes("ISO-8859-1");
			System.arraycopy(arrayOfByte3, 0, arrayOfByte2, 0, arrayOfByte3.length);
			System.out.println("m_iBlockSize=" + m_iBlockSize + ", copying bytes from byte position " + m_iBytesSent);
			System.arraycopy(this.m_baFileData, m_iBytesSent, arrayOfByte2, arrayOfByte3.length, m_iBlockSize);
			String str10 = Ascii.FormatBinaryData(arrayOfByte2);
			if (str7 == "N")
				this.m_bFileDownload = false;
			str9 = new String(arrayOfByte2, "ISO-8859-1");
			i5 = 0;
			do
				while (true)
				{
					System.out.println("Sending packet: " + str10);
					this.ld.directIO(100, arrayOfInt, str9);
					++i5;
					if (str7 != "N")
						i4 = waitForPacket("XDAT", str4, j, false);
					else
						i4 = waitForPacket("SFL", str4, j, false);
					if (i4 == 1)
					{
						if (str7 != "N")
						{
							System.out.println("XDAT received");
							break label915;
						}
						System.out.println("SFL received");
						break label915;
					}
					if (i4 != -1)
						break;
					if (i5 >= 3)
						break label915;
					if (str7 == "N")
						continue;
					System.out.println("NAK received; resend packet");
				}
			while (i4 != 2);
			if (str7 != "N")
				System.out.println("Timed out waiting for XDAT");
			else
				System.out.println("Timed out waiting for SFL");
			if (i5 >= 3)
			{
				label915: System.out.println("Too many retries, exiting...");
			break;
			}
			m_iBytesSent += m_iBlockSize;
			i6 = (int)(m_iBytesSent * 100 / m_lFileSize);
			System.out.println("Progress=" + i6 + "%");

			if (!(this.m_bFileDownload))
			{
				System.out.println("Finished sending last file block");
				break;
			}
			try
			{
				Thread.sleep(25L);
			}
			catch (InterruptedException localInterruptedException)
			{
				System.out.println("syncSendFile - " + localInterruptedException.getMessage());
			}
		}

	}
	catch (JposException localJposException)
	{

		CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
	}
	catch (IOException localIOException)
	{
		System.out.println("syncSendFile - error: " + localIOException.getMessage());
		return;
	}
}*/

	private int waitForChar(byte paramByte, int paramInt) {
		long l1 = System.currentTimeMillis();
		long l2 = l1 + paramInt;
		int i = 1;
		System.out.println("LineDisplayTest - waitForChar - entered");
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
		System.out
		.println("LineDisplayTest - waitForChar - exiting with retval = "
				+ i);
		return i;
	}

	private int waitForPacket(String paramString1, String paramString2,
			int paramInt, boolean paramBoolean) {
		long l1 = System.currentTimeMillis();
		long l2 = l1 + paramInt;
		int i = 1;
		int[] arrayOfInt = { 1 };
		byte[] arrayOfByte = { 6 };
		System.out
		.println("LineDisplayTest - waitForPacket - waiting for packet: "
				+ paramString1);
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
				Thread.sleep(200L);
			} catch (InterruptedException localInterruptedException) {
				System.out.println("sendFile - waitForPacket: "
						+ localInterruptedException.getMessage());
			}
		}
		if ((paramBoolean) && (i == 1)) {
			System.out.println("Sending ACK");
			String str = new String(arrayOfByte);
			try {
				this.ld.directIO(100, arrayOfInt, str);
			} catch (JposException localJposException) {
				CoxVfMxUtilities.handleJposException(localJposException, CLASSNAME);
			}
		}
		System.out
		.println("LineDisplayTest - waitForPacket - exiting with retval = "
				+ i);
		this.m_strCmdReceived = null;
		return i;
	}

	void clearText() {
		try {
			try {
				this.ld.getCurrentWindow();
			} catch (JposException localJposException1) {
			}
			this.ld.clearText();
		} catch (JposException localJposException2) {
			CoxVfMxUtilities.handleJposException(localJposException2, CLASSNAME);
		}
	}

	private void runScript(String paramString) {
		try {
			if (paramString.length() <= 0) {
				System.out
				.println("LineDisplayTest - runScript - no script filename specified!");
				return;
			}
			File localFile = new File(paramString);
			String str1 = "runScript - File: " + localFile.getAbsoluteFile();
			System.out.println(str1);
			this.m_fis = new FileInputStream(localFile);
			this.m_bis = new BufferedInputStream(this.m_fis);
			this.m_dis = new DataInputStream(this.m_bis);
			String str2 = this.m_dis.readLine();
		} catch (IOException localIOException) {
			System.out.println("runScript - error: "
					+ localIOException.getMessage());
			return;
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


	public void errorOccurred(ErrorEvent paramErrorEvent) {
		if (108 != paramErrorEvent.getErrorCode())
			return;
		CoxVfMxUtilities.handleJposException(new JposException(108), CLASSNAME);
		System.out.println(" After Errror Occured");
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
		if (!(paramDirectIOEvent.getSource() instanceof LineDisplay)) {
			System.out.println("Unknown event source.");
			return;
		}
		System.out.println("Received DirectIOEvent from LineDisplay Service Object.");
		try {
			if (paramDirectIOEvent.getObject() != null) {
				byte[] arrayOfByte2 = (byte[]) (byte[]) paramDirectIOEvent.getObject();
				this.m_strPacketReceived = Ascii.FormatBinaryData(arrayOfByte2);

				System.out.println("Packet received: " + this.m_strPacketReceived);
				//System.out.println("Packet received: " + this.m_strPacketReceived);

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
					} else if ((this.m_strPacketReceived.startsWith("SFL<FS>1"))
							|| (this.m_strPacketReceived.startsWith("XDAT<FS>"))) {
						if (this.m_strPacketReceived.startsWith("SFL<FS>1")) {
							System.out.println("SFL response packet received: " + this.m_strPacketReceived);
							this.m_strCmdReceived = "SFL";
						} else if (this.m_strPacketReceived.startsWith("XDAT<FS>")) {
							System.out.println("XDAT response packet received: "
									+ this.m_strPacketReceived);
							this.m_strCmdReceived = "XDAT";
							this.m_bDAT = true;
						}
						if (this.m_bAsyncFileSend) {
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
									m_iBlockSize = (int) m_lFileSize
											- m_iBytesSent;
								} else {
									str5 = "Y";
								}
								str6 = String.valueOf(m_iBlockSize);
								str6 = pad(str6, 4, '0', true);
								m_iSeqNum += 1;
								String str4 = String.valueOf(m_iSeqNum);
								str4 = pad(str4, 4, '0', true);
								str2 = "XDAT<FS>" + str5 + str6 + str4;
								str2 = Ascii.UnBracket(str2);
								byte[] arrayOfByte3 = new byte[14 + m_iBlockSize];
								byte[] arrayOfByte4 = str2
										.getBytes("ISO-8859-1");
								System.arraycopy(arrayOfByte4, 0, arrayOfByte3,
										0, arrayOfByte4.length);
								System.out.println("m_iBlockSize="
										+ m_iBlockSize
										+ ", copying bytes from byte position "
										+ m_iBytesSent);
								System.arraycopy(this.m_baFileData,
										m_iBytesSent, arrayOfByte3,
										arrayOfByte4.length, m_iBlockSize);
								String str7 = Ascii
										.FormatBinaryData(arrayOfByte3);
								if (str5 == "N")
									this.m_bFileDownload = false;
								System.out.println("Sending packet: " + str7);
								str3 = new String(arrayOfByte3, "ISO-8859-1");
								this.ld.directIO(100, arrayOfInt, str3);
								m_iBytesSent += m_iBlockSize;
								int i1 = (int) (m_iBytesSent * 100 / m_lFileSize);
								System.out.println("Progress=" + i1 + "%");
							}
						}
					}
				} else if (this.m_strPacketReceived.startsWith("SFL<FS>1")) {
					System.out.println("SFL response packet received: "
							+ this.m_strPacketReceived);
					this.m_strCmdReceived = "SFL";
					if (!(this.m_bFileDownload)) {
						System.out.println("SFL download completed");
						this.m_dis.close();
						this.m_bis.close();
						this.m_fis.close();
						this.m_bFileDownload = false;
					}
				} else if (this.m_strPacketReceived.startsWith("XDAT<FS>")) {
					System.out.println(this.m_strPacketReceived + "Starts with " + "XDAT<FS>");
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
}
