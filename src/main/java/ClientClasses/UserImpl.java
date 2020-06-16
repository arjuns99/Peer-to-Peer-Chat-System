package ClientClasses;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.UserInterface;
import model.LoginUser;
import model.Message;
import model.State;
import model.VectorTimeStamp;

public class UserImpl implements UserInterface {

	private ArrayList<LoginUser> userList = new ArrayList<LoginUser>();
	private ArrayList<Message> messageList = new ArrayList<Message>();
	private int currentHighestId = -1;
	private boolean beginSnapShot = false;

	static Semaphore messageLock = new Semaphore(1);
	static Semaphore userLock = new Semaphore(1);

	public UserImpl() {
	}

	public void newUser(LoginUser user) throws RemoteException {
		try {
			userLock.acquire();
			this.userList.add(user);
			userLock.release();
		} catch (InterruptedException ex) {
			Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public int getLeader() {
		this.currentHighestId++;
		return this.currentHighestId;
	}

	public ArrayList fetchChatList() throws RemoteException {
		return messageList;
	}

	public void addMessage(Message message) throws RemoteException {
		try {
			messageLock.acquire();
			this.messageList.add(message);
			messageLock.release();
		} catch (InterruptedException ex) {
			Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setMessageReceived(int messageIndex, int receivedArrayIndex) throws RemoteException {
		this.messageList.get(messageIndex).getReceievedArray()[receivedArrayIndex] = true;
	}

	public void startSnapshot(VectorTimeStamp vectorTimeStamp, String address, String leaderAddress)
			throws RemoteException {
		State currentState = new State(vectorTimeStamp, address, leaderAddress);
		this.setSnapShotStarted(true);
		this.addMessage(new Message("SNAPSHOT MARKER", 6, vectorTimeStamp, isSnapShotStarted()));
		System.out.println(currentState);
	}

	public boolean isSnapShotStarted() throws RemoteException {
		return beginSnapShot;
	}

	public void setSnapShotStarted(boolean snapShotStarted) throws RemoteException {
		this.beginSnapShot = snapShotStarted;
	}

}
