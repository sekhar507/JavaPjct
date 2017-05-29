/*
 * Created on 2004. 6. 24.
 */
package chipchat;

import java.util.HashMap;

/**
 * @author MTY.
 */
public class ConnectionWaiter extends Thread {

	/*
	 * Sigleton..
	 */

	/** Instance */
	private static ConnectionWaiter instance = null;

	/** Constructor */
	private ConnectionWaiter() {
	}

	/** Getter of instance. */
	public static ConnectionWaiter getInstance() {
		if (instance == null) {
			makeInstance();
		}
		return instance;
	}

	/**
	 * Make just one instance.
	 */
	private static synchronized void makeInstance() {
		if (instance == null) {
			instance = new ConnectionWaiter();
		}
	}

	/** Lock object */
	private Object lock = new Object();
	/** Part1 */
	private HashMap part1 = new HashMap();
	/** Part2 */
	private HashMap part2 = new HashMap();

	/**
	 * Run method.
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock) {
				part2 = part1;
				part1.clear();
			}
		}
	}

	/**
	 * Put value
	 * @param name Name
	 * @param o value
	 */
	public void put(String name, ConnectionInfo o) {
		synchronized (lock) {
			part1.put(name, o);
		}
	}

	/**
	 * Get value
	 * @param name Name
	 * @return value
	 */
	public ConnectionInfo get(String name) {
		synchronized (lock) {
			Object o = part1.get(name);
			if (o != null) {
				part1.remove(name);
				return (ConnectionInfo) o;
			} else {
				o = part2.get(name);
				if (o != null) {
					part2.remove(name);
					return (ConnectionInfo) o;
				} else {
					return null;
				}
			}
		}
	}
}
