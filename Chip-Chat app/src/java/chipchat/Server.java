/*
 * Created on 2004. 6. 19.
 */
package chipchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * User info.
 * @author Mr. Lee
 */
public class Server extends Thread {
	public static Server instance = null;

	private int serverPort = 6543;
	private boolean useServer = false;

	private Server() {
		try {
			serverPort =
				Integer.parseInt(Env.getInstance().getProperty("Server.port"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		useServer =
			"true".equalsIgnoreCase(
				Env.getInstance().getProperty("Server.listen"));

		if (useServer) {
			this.setDaemon(true);
			this.start();
		}
	}

	public static void initialize() {
		if (instance == null) {
			instance = new Server();
		}
	}

	public static int getServerPort() {
		if (instance == null) {
			initialize();
		}
		return instance.serverPort;
	}

	public static boolean isUseServer() {
		if (instance == null) {
			initialize();
		}
		return instance.useServer;
	}

	public void run() {

		final ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			System.out.println("Server Socket Create Exception... : " + e);
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				Thread t = new Thread() {
					Socket client_connection = serverSocket.accept();
					public void run() {
						try {
							InputStream input =
								client_connection.getInputStream();
							OutputStream output =
								client_connection.getOutputStream();
							BufferedReader rd =
								new BufferedReader(
									new InputStreamReader(input));
							String line = rd.readLine();
							HashMap header = new HashMap();
							do {
								line = rd.readLine();
								if (line.length() == 0) {
									break;
								}
								int idx = line.indexOf(":");
								if (idx >= 0) {
									String name = line.substring(0, idx).trim();
									String value =
										line.substring(idx + 1).trim();
									header.put(name, value);
								} else {
									System.err.println(
										"Unknown header : " + line);
								}
							} while (true);
							String inum = (String) header.get("INUM");
							String address =
								client_connection
									.getInetAddress()
									.getHostAddress();
							Communicator c = new Communicator();
							c.service(address + inum, input, output);
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (client_connection != null) {
								try {
									client_connection.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				};
				t.setDaemon(true);
				t.start();
			} catch (IOException e) {
				System.out.println("Client IO Exception... : " + e);
				e.printStackTrace();
			}
		}
	}
}
