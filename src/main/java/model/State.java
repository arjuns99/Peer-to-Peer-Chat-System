/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * State Class has all of the logic allowing serialized methods/returns of State
 *
 */
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VectorTimeStamp vectorTimeStamp;
	public int id;
	public String address;
	public String leaderAddress;

	public State(VectorTimeStamp vectorTimeStamp, String address, String leaderAddress) {
		this.vectorTimeStamp = vectorTimeStamp;
		this.address = address;
		this.leaderAddress = leaderAddress;
	}

	public String toString() {
		return "State Snapshot Completed " + this.vectorTimeStamp + "Address: " + this.address + " Leader: "
				+ this.leaderAddress;
	}

	public VectorTimeStamp getVectorTimeStamp() {
		return vectorTimeStamp;
	}

	public void setVectorTimeStamp(VectorTimeStamp vectorTimeStamp) {
		this.vectorTimeStamp = vectorTimeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLeaderAddress() {
		return leaderAddress;
	}

	public void setLeaderAddress(String leaderAddress) {
		this.leaderAddress = leaderAddress;
	}

}
