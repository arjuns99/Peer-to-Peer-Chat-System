package model;

import java.io.Serializable;

/**
 *
 * Message Class has all of the logic allowing serialized methods/returns of
 * Messages
 *
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private LoginUser source;
	private LoginUser destination;
	private int type;
	private boolean[] receievedArray;
	private VectorTimeStamp vectorTimeStamp;
	private State state;
	private Boolean snapshotEnabled;

	public Message(String message, LoginUser source, LoginUser destination, int type, VectorTimeStamp vectorTimeStamp,
			boolean snapShot) {
		this.message = message;
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.vectorTimeStamp = vectorTimeStamp;
		receievedArray = new boolean[100];
		for (int i = 0; i < 0; i++) {
			receievedArray[i] = false;
		}
		this.snapshotEnabled = snapShot;

	}

	public Message(String message, int type, VectorTimeStamp vectorTimeStamp, boolean snapShot) {
		this.message = message;
		this.type = type;
		this.vectorTimeStamp = vectorTimeStamp;
		receievedArray = new boolean[100];
		for (int i = 0; i < 0; i++) {
			receievedArray[i] = false;
		}
		this.snapshotEnabled = snapShot;
	}

	public Message(State state, LoginUser destination, boolean snapShot) {
		this.state = state;
		this.destination = destination;
		this.type = 7;
		this.snapshotEnabled = snapShot;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoginUser getSource() {
		return source;
	}

	public void setSource(LoginUser source) {
		this.source = source;
	}

	public LoginUser getDestination() {
		return destination;
	}

	public void setDestination(LoginUser destination) {
		this.destination = destination;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean[] getReceievedArray() {
		return receievedArray;
	}

	public void setReceievedArray(boolean[] receievedArray) {
		this.receievedArray = receievedArray;
	}

	public VectorTimeStamp getVectorTimeStamp() {
		return vectorTimeStamp;
	}

	public void setVectorTimeStamp(VectorTimeStamp vectorTimeStamp) {
		this.vectorTimeStamp = vectorTimeStamp;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean getSnapshotEnabled() {
		return snapshotEnabled;
	}

	public void setSnapshotEnabled(Boolean snapshotEnabled) {
		this.snapshotEnabled = snapshotEnabled;
	}

}
