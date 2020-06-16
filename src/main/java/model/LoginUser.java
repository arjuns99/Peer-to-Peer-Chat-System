package model;

import java.io.Serializable;


public class LoginUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String ipAddress;
	private int port;
	private int id;
	private boolean leader;

	public LoginUser(String name, String ipAddress, int port) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public String getName() {
		return this.name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String toString() {
		return this.name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}
}
