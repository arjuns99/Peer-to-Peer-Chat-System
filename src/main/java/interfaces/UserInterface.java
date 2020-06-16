package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.LoginUser;
import model.Message;
import model.VectorTimeStamp;

/**
 * An interface enabling RMI for the LeaderRemoteMethods registry component
 */
public interface UserInterface extends Remote {

	public void newUser(LoginUser user) throws RemoteException;

	public int getLeader() throws RemoteException;

	public ArrayList<Message> fetchChatList() throws RemoteException;

	public void addMessage(Message message) throws RemoteException;

	public void setMessageReceived(int messageIndex, int receivedArrayIndex) throws RemoteException;

	public void startSnapshot(VectorTimeStamp vectorTimeStamp, String address, String leaderAddress)
			throws RemoteException;

	public boolean isSnapShotStarted() throws RemoteException;

	public void setSnapShotStarted(boolean snapShotStarted) throws RemoteException;

}
