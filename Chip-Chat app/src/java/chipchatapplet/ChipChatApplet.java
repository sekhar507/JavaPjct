/*
 * Created on 2003. 2. 20.
 */
package chipchatapplet;

import java.applet.Applet;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 * Chipchat Applet.
 * @author Mr. Lee
 */
public final class ChipChatApplet extends Applet {
	/** Is debug mode? */
	private final boolean debug = true;

	/** Socket */
	private Socket sock = null;
	/** Input Stream */
	private BufferedReader inStream = null;
	/** Output Stream */
	private DataOutputStream outStream = null;

	/** win JSObject */
	private JSObject win = null;
	/** doc JSObject */
	private JSObject doc = null;

	/** Is connected? */
	private boolean connected = false;

	/** Id number of user */
	private int userid = -1;
	/** Id number of master */
	private int hostid = -2;

	/** Remained time of keeping quiet */
	private long keepQuietTime = 0;

	/**
	 * Get Applet information
	 * @return Information
	 */
	public String getAppletInfo() {
		return "ChipChat Applet v1.0\r\nCopyright by Mr.Lee";
	}

	/**
	 * Init.
	 */
	public void init() {
		logMsg("Init..");
		this.setBackground(Color.white);
		String keepSessionMinute = getParameter("keepsession");
		if (keepSessionMinute != null) {
			int m;
			try {
				m = Integer.parseInt(keepSessionMinute);
			} catch (NumberFormatException e) {
				logMsg("Error : 'keepsession' parameter is not number", e);
				m = 9;
			}
			SessionKeeper keeper = new SessionKeeper(this, m);
			keeper.setDaemon(true);
			keeper.start();
		}
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		closeConnect();
	}

	private JSObject getWin() {
		if (win == null)
			win = JSObject.getWindow(this);
		return win;
	}

	private JSObject getDoc() {
		if (doc == null)
			doc = (JSObject) getWin().getMember("document");
		return doc;
	}

	/**
	 * Process messages which is received from server.
	 * @param msg received message.
	 */
	void processMessage(final String msg) {
		logMsg("PMSG:" + msg);
		try {
			// Parsing msg....
			int index = msg.indexOf(':');
			String cmd;

			if (index < 0) {
				// ignore.
				logMsg("NOT_CMD:" + msg);
				return;
			} else {
				cmd = msg.substring(0, index);
			}

			if (cmd.equalsIgnoreCase("MSG")) {
				String[] v = spliteString(msg, '>', index + 1);
				wincall("chipchat_printMsg", new Object[] { v[0], v[1] });
			} else if (cmd.equalsIgnoreCase("WSPSND")) {
				String[] v = spliteString(msg, '>', index + 1);
				wincall("chipchat_output_wspsnd", new Object[] { v[0], v[1] });
			} else if (cmd.equalsIgnoreCase("WSPRCV")) {
				String[] v = spliteString(msg, '>', index + 1);
				wincall("chipchat_output_wsprcv", new Object[] { v[0], v[1] });
			} else if (cmd.equalsIgnoreCase("ACK")) {
				return;
			} else if (cmd.equalsIgnoreCase("ERROR")) {
				String error = msg.substring(index + 1);
				wincall("chipchat_error", new Object[] { error });
			} else if (cmd.equalsIgnoreCase("INFO")) {
				String[] v = spliteString(msg, '>', index + 1);
				if (v[0].equalsIgnoreCase("GetIn")) {
					String[] v2 = spliteString(v[1], '>', 0);
					wincall("chipchat_getin", new Object[] { v2[0], v2[1] });
				} else if (v[0].equalsIgnoreCase("GetOut")) {
					wincall("chipchat_getout", new Object[] { v[1] });
				} else if (v[0].equalsIgnoreCase("ChangePasswd")) {
					wincall("chipchat_passwdChanged", new Object[] { v[1] });
				} else if (v[0].equalsIgnoreCase("ChangeRoomName")) {
					wincall("chipchat_RoomnameChanged", new Object[] { v[1] });
				} else if (v[0].equalsIgnoreCase("ChangeMaxMan")) {
					wincall("chipchat_MaxmanChanged", new Object[] { v[1] });
				} else if (v[0].equalsIgnoreCase("RoomInfo")) {
					String[] p1 = spliteString(v[1], '>', 0);
					String[] p2 = spliteString(p1[1], '>', 0);
					wincall(
						"chipchat_RoomInfo",
						new Object[] { p2[1], p1[0], p2[0] });
				} else {
					logMsg("Need to Process.. : " + v[0]);
				}
			} else if (cmd.equalsIgnoreCase("USERS")) {
				wincall("chipchat_userlistStart", null);
				int i = index + 1;
				while (true) {
					int idx1 = msg.indexOf('<', i);
					int idx2 = msg.indexOf('>', i);
					if (idx1 == -1 || idx2 == -1) {
						break;
					}
					String id = msg.substring(i, idx1);
					String name = msg.substring(idx1 + 1, idx2);
					i = idx2 + 1;
					wincall("chipchat_userlistAdd", new Object[] { id, name });
				}
				wincall("chipchat_userlistEnd", null);
			} else if (cmd.equalsIgnoreCase("ADMIN")) {
				String msg2 = msg.substring(index + 1);
				try {
					hostid = Integer.parseInt(msg2);
				} catch (NumberFormatException e) {
					logMsg("Error.", e);
				}
				wincall("chipchat_setAdmin", new Object[] { msg2 });
			} else if (cmd.equalsIgnoreCase("ADMCG")) {
				String msg2 = msg.substring(index + 1);
				wincall("chipchat_adminChange", new Object[] { msg2 });
			} else if (cmd.equalsIgnoreCase("KEEPQUIET")) {
				String[] v = spliteString(msg, '>', index + 1);
				int who = Integer.parseInt(v[1]);
				if (who == userid) {
					keepQuietTime = System.currentTimeMillis();
				}
				wincall("chipchat_keepQuit", new Object[] { v[0], v[1] });
			} else if (cmd.equalsIgnoreCase("KICKOUT")) {
				String[] v = spliteString(msg, '>', index + 1);
				int who = Integer.parseInt(v[1]);
				if (who == userid) {
					closeConnect();
				}
				wincall("chipchat_kickOut", new Object[] { v[0], v[1] });
			} else if (cmd.equalsIgnoreCase("CUSTOM")) {
				String[] p1 = spliteString(msg, '>', index + 1);
				String[] p2 = spliteString(p1[1], '>', 0);
				wincall(
					"chipchat_custom",
					new Object[] { p1[0], p2[0], p2[1] });
			} else if (cmd.equalsIgnoreCase("Connected")) {
				connected = true;
				String msg2 = msg.substring(index + 1);
				wincall("chipchat_connected", new Object[] { msg2 });
			} else if (cmd.equalsIgnoreCase("ConnectID")) {
				String submsg = msg.substring(index + 1);
				userid = Integer.parseInt(submsg);
				wincall("chipchat_setConnectID", new Object[] { submsg });
			} else if (cmd.equalsIgnoreCase("ConnectName")) {
				String error = msg.substring(index + 1);
				wincall("chipchat_setConnectName", new Object[] { error });
			} else if (
				cmd.equalsIgnoreCase("Content-Type")
					|| cmd.equalsIgnoreCase("Transfer-Encoding")
					|| cmd.equalsIgnoreCase("Date")
					|| cmd.equalsIgnoreCase("Server")) { // Ignores Comands....
				return;
			} else if (cmd.equals("CLOSED")) {
				closeConnect();
			} else {
				logMsg("Need to Process.. : " + cmd);
			}
		} catch (Exception e) {
			System.out.println("## Parsing Error... of [" + msg + "]");
			e.printStackTrace();
		}
	}

	/**
	 * Send message to server.
	 * @param msg message that will be sent.
	 */
	void sendToServer(final String msg) {
		if (connected && outStream != null) {
			try {
				outStream.write((msg).getBytes());
			} catch (IOException e) {
				logMsg("Stream write error.", e);
			}
		} else {
			wincall("chipchat_notconnected", null);
		}
	}

	/**
	 * Close connection.
	 */
	void closeConnect() {
		connected = false;
		if (sock != null) {
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sock = null;
		}
	}

	/**
	 * Split message to two part.
	 * @param src   Source String.
	 * @param ch    Splite token.
	 * @param from  Starting position.
	 * @return Splited string array(size 2).
	 */
	String[] spliteString(final String src, final char ch, final int from) {
		int index2 = src.indexOf(ch, from);
		String writer;
		if (index2 < 0) {
			throw new IllegalArgumentException(
				"Input source can not split by '"
					+ ch
					+ "'. ["
					+ src
					+ ":"
					+ from
					+ "]");
		} else {
			return new String[] {
				src.substring(from, index2),
				src.substring(index2 + 1)};
		}
	}

	/**
	 * Call function of outside explorer's script.
	 * @param function function name.
	 * @param param Parameter values.
	 */
	void wincall(final String function, final Object[] param) {
		Object[] realParam;
		if (param == null) {
			realParam = new Object[0];
		} else {
			realParam = param;
		}
		try {
			getWin().call(function, realParam);
		} catch (JSException e) {
			callAlert(
				"function : "
					+ function
					+ "(arg*"
					+ realParam.length
					+ ") is exist?");
		}
	}

	/**
	 * Call alert function of outside explorer's script.
	 * @param msg Message.
	 */
	void callAlert(final String msg) {
		try {
			getWin().call("alert", new String[] { msg });
		} catch (JSException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Log message when debug is true.
	 * @param msg Message.
	 */
	void logMsg(final String msg) {
		if (debug) {
			System.out.println(msg);
		}
	}

	/**
	 * Log message when debug is true.
	 * @param msg Message.
	 * @param e Exception.
	 */
	void logMsg(final String msg, final Throwable e) {
		if (debug) {
			System.out.println(msg + "\r\nCause : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Convert html special charectors.
	 * @param src Source String.
	 * @return Conveted string.
	 */
	public static String htmlSpecialChars(final String src) {
		return htmlSpecialChars(new StringBuffer(src)).toString();
	}

	/**
	 * Convert html special charectors.
	 * @param src Source String.
	 * @return Conveted string.
	 */
	static StringBuffer htmlSpecialChars(final StringBuffer src) {
		if (src == null) {
			return null;
		}
		int length = src.length();
		StringBuffer result = new StringBuffer();

		char c2 = 'x';

		for (int i = 0; i < length; i++) {
			char c1 = src.charAt(i);
			if (c1 == '<') {
				result.append("&lt;");
			} else if (c1 == '>') {
				result.append("&gt;");
			} else if (c1 == '&') {
				result.append("&amp;");
			} else if (c1 == '"') {
				result.append("&quot;");
			} else if (c1 == '\'') {
				result.append("&#039;");
			} else if (c1 == ' ' && c2 == ' ') {
				result.append("&nbsp;");
			} else {
				result.append(c1);
			}
			c2 = c1;
		}
		return src;
	}

	/*
	 *
	 * Functions that will be called by outside script.
	 *
	 */

	/**
	 * Connect to server.
	 */
	public void connect() {
		if (connected) {
			return;
		}
		logMsg("Connect to server..."); ///////////////////////

		URL url;
		try {
			url = new URL(getDocumentBase(), "communicator.jsp");
		} catch (MalformedURLException e) {
			logMsg("Error in connect(.).", e);
			return;
		}

		int port;
		{
			// make port value...
			String sPort = getParameter("port");
			if (sPort == null) {
				port = url.getPort();
				if (port < 0) {
					port = 80;
				}
			} else {
				try {
					port = Integer.parseInt(sPort);
				} catch (NumberFormatException e) {
					logMsg("Pasing error of 'port'.", e);
					port = 80;
				}
			}
		}

		try {
			logMsg(
				"Host:["
					+ url.getHost()
					+ "],Port:["
					+ port
					+ "], File:["
					+ url.getFile()
					+ "]");

			// Conect to server..
			sock = new Socket(url.getHost(), port);
			inStream =
				new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			outStream = new DataOutputStream(sock.getOutputStream());

			// Send message like when browser upoad file.
			outStream.write(
				("GET " + url.getFile() + " HTTP/1.1\r\n").getBytes());
			outStream.write("Accept: */*\r\n".getBytes());
			outStream.write("Accept-Language: utf-8\r\n".getBytes());
			outStream.write(
				("Content-Type: multipart/form-data; boundary=---------------------------ASD5345435\r\n")
					.getBytes());
			outStream.write("User-Agent: ChipChat agent.\r\n".getBytes());
			outStream.write(
				("INUM: " + getParameter("inum") + "\r\n").getBytes());
			String cookie = (String) getDoc().getMember("cookie");
			if (cookie != null) {
				outStream.write(("Cookie: " + cookie + "\r\n").getBytes());
				logMsg("Cookie:[" + cookie + "]"); ////////////////////
			} else {
				logMsg("Cookie Not Exist..");
			}
			outStream.write(
				("Host: "
					+ ((port == 80) ? url.getHost() : url.getHost() + ":" + port)
					+ "\r\n")
					.getBytes());
			outStream.write(
				("Content-Length: " + Integer.MAX_VALUE + "\r\n").getBytes());
			outStream.write("Connection: Keep-Alive\r\n".getBytes());
			outStream.write("Cache-Control: no-cache\r\n".getBytes());
			outStream.write("\r\n".getBytes()); // 헤더끝을 알림
			outStream.flush();

			// Thread that receive messages.
			Thread t1 = new Thread() {
				public void run() {
					try {
						while (sock != null) {
							String r = inStream.readLine();
							if (r == null) {
								break;
							}
							processMessage(r);
						}
					} catch (IOException e) {
						logMsg("Error..", e);
					} finally {
						if (connected) {
							closeConnect();
							wincall("chipchat_connectionBroken", null);
						}
					}
				}
			};
			t1.setDaemon(true);
			t1.start();

			// Acknowledge Thread...
			Thread t2 = new Thread() {
				public void run() {
					try {
						while (sock != null) {
							if (sock.isClosed()) {
								break;
							}
							outStream.write(("ACK:\r\n").getBytes());
							try {
								Thread.sleep(9500);
							} catch (InterruptedException e) {
								logMsg("Error.", e);
								break;
							}
						}
					} catch (IOException e) {
						logMsg("Error.", e);
					}
				}
			};
			t2.setDaemon(true);
			t2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send message.
	 * @param who
	 *   Id number that will be received this message. '-1' if it send for all.
	 * @param msg Message.
	 */
	public void sendMsg(final String who, final String msg) {
		try {
			// Check of punishment.
			if (keepQuietTime != 0) {
				long diff = System.currentTimeMillis() - keepQuietTime;
				if (diff < 60000) {
					wincall(
						"chipchat_beQuit",
						new Object[] { new Long(60 - diff / 1000)});
					return;
				} else {
					keepQuietTime = 0;
				}
			}

			// Change to htmlSpecialChars
			String newMsg = htmlSpecialChars(msg);
			int to = Integer.parseInt(who);

			// Send Message...
			if (to == -1) {
				sendToServer("MSG:" + newMsg + "\r\n");
				return;
			} else {
				sendToServer("WSP:" + to + ":" + newMsg + "\r\n");
			}
		} catch (Exception e1) {
			logMsg("Error in sendMsg(..).", e1);
		}
	}

	/**
	 * Send custom message.
	 * @param who
	 *   Id number that will be received this message. '-1' if it send for all.
	 * @param cmd Command name.
	 * @param msg Message.
	 */
	public void sendCustomMsg(
		final String who,
		final String cmd,
		final String msg) {
		try {
			// Check of punishment.
			if (keepQuietTime != 0) {
				long diff = System.currentTimeMillis() - keepQuietTime;
				if (diff < 60000) {
					wincall(
						"chipchat_beQuit",
						new Object[] { new Long(60 - diff / 1000)});
					return;
				} else {
					keepQuietTime = 0;
				}
			}

			// Change to htmlSpecialChars
			String newCmd = htmlSpecialChars(cmd);
			int to = Integer.parseInt(who);

			// Send Message...
			sendToServer("CUSTOM:" + to + ":" + newCmd + ">" + msg + "\r\n");
		} catch (Exception e1) {
			logMsg("Error in sendMsg(..).", e1);
		}
	}

	/**
	 * Send message that 'I will get out."
	 */
	public void getOut() {
		try {
			sendToServer("GETOUT:\r\n");
			closeConnect();
			wincall("chipchat_getout_forward", null);
		} catch (Exception e) {
			logMsg("Error in getout().", e);
		}
	}

	/**
	 * Make someone to keep quiet. It is administrator's funtion.
	 * @param to Id number that will be received this message.
	 *   '-1' if it send for all.
	 */
	public void keepQuiet(final String to) {
		try {
			int num = Integer.parseInt(to);
			sendToServer("KEEPQUIET:" + num + "\r\n");
		} catch (Exception e) {
			logMsg("Error in keepQuiet(.).", e);
		}
	}

	/**
	 * Kick someone out. It is administrator's funtion.
	 * @param to
	 *   Id number that will be received this message. '-1' if it send for all.
	 */
	public void kickOut(final String to) {
		try {
			int num = Integer.parseInt(to);
			sendToServer("KICKOUT:" + num + "\r\n");
		} catch (Exception e) {
			logMsg("Error in kickOut(.).", e);
		}
	}

	/**
	 * Entrust someone with administrator role. It is administrator's funtion.
	 * @param to
	 *   Id number that will be received this message. '-1' if it send for all.
	 */
	public void entrust(final String to) {
		try {
			int num = Integer.parseInt(to);
			sendToServer("ENTRUST:" + num + "\r\n");
		} catch (Exception e) {
			logMsg("Error in entrust(.).", e);
		}
	}

	public void changeRoomPassword(final String to) {
		sendToServer("CHGPASSWORD:" + to + "\r\n");
	}

	public void changeRoomName(final String to) {
		sendToServer("CHGROOMNAME:" + to + "\r\n");
	}

	public void changeMax(final String to) {
		sendToServer("CHGMAX:" + to + "\r\n");
	}

	/**
	 * Getter of conected.
	 * @return is connected?
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Check whether user is host or not.
	 * @return Is host?
	 */
	public boolean isHost() {
		return userid == hostid;
	}

	public static class SessionKeeper extends Thread {
		ChipChatApplet parent;
		int minute;

		public SessionKeeper(ChipChatApplet parent, int minute) {
			this.parent = parent;
			this.minute = minute;
		}

		public void run() {
			URL url;
			try {
				url = new URL(parent.getDocumentBase(), "sessionkeeper.jsp");
			} catch (MalformedURLException e) {
				parent.logMsg("Error SessionKeeper.run(.).", e);
				return;
			}

			int port;
			{
				// make port value...
				port = url.getPort();
				if (port < 0) {
					port = 80;
				}
			}

			String cookie = (String) parent.getDoc().getMember("cookie");
			if (cookie == null) {
				parent.logMsg("Warm : cookie is null.");
			}

			String sContent =
				"GET "
					+ url.getFile()
					+ " HTTP/1.1\r\n"
					+ "Accept: */*\r\n"
					+ "Accept-Language: utf-8\r\n"
					+ "User-Agent: ChipChat agent.\r\n"
					+ ((cookie != null) ? ("Cookie: " + cookie + "\r\n") : "")
					+ "Host: "
					+ ((port == 80) ? url.getHost() : url.getHost() + ":" + port)
					+ "\r\n"
					+ "Connection: Keep-Alive\r\n"
					+ "Cache-Control: no-cache\r\n"
					+ "\r\n";

			while (true) {
				try {
					Thread.sleep(minute * 60000);
				} catch (InterruptedException e) {
					parent.logMsg("Error..", e);
					break;
				}
				try {
					// Conect to server..
					parent.logMsg("Connecting sessionkeeper.jsp page..");
					Socket sock = new Socket(url.getHost(), port);
					BufferedReader inStream =
						new BufferedReader(
							new InputStreamReader(sock.getInputStream()));
					DataOutputStream outStream =
						new DataOutputStream(sock.getOutputStream());
					// Send message like when browser upoad file.
					outStream.write(sContent.getBytes());
					outStream.flush();
					for (String l = inStream.readLine();
						l.length() > 0;
						l = inStream.readLine());
					outStream.close();
					inStream.close();
					sock.close();
				} catch (IOException e) {
					parent.logMsg("Error..", e);
					break;
				}
			}
		}
	}
}
