/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientClasses;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.UserInterface;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LoginUser;
import model.Message;
import model.VectorTimeStamp;

/**
 *
 * @author sachitgagneja
 */
public class Client {
	public LoginUser user;
	public UserInterface userInterface;
	public ClientDriver clientDriver;
	public VectorTimeStamp vectorTimeStamp;
	private Registry registry;
	public String leaderIp;
	public int leaderPort;
	private Registry leaderRegistry;
	public ObservableList<String> list;
	public ObservableList<String> userList;

	public Client(String chatUserName, String ipAddress, int port, String leaderIpAddress, int leaderPort,
			ClientDriver clientDriver) throws RemoteException {
		this.user = new LoginUser(chatUserName, ipAddress, port);
		this.clientDriver = clientDriver;
		this.createRegistry();
		this.setRegistry(leaderIpAddress, leaderPort);
		this.addNewMessage();
	}

	public void setRegistry(String leaderIpAddress, int leaderPort) {
		if (leaderPort == user.getPort()) {
			user.setLeader(true);
		}
		try {
			this.leaderRegistry = LocateRegistry.getRegistry(leaderIpAddress, leaderPort);
			this.userInterface = (UserInterface) leaderRegistry.lookup("leaderStub");
			this.user.setId(this.userInterface.getLeader());
			this.userInterface.newUser(this.user);
			this.leaderIp = leaderIpAddress;
			this.leaderPort = leaderPort;
			this.vectorTimeStamp = new VectorTimeStamp(this.user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addNewMessage() {
		new Thread(new Runnable() {
			public void run() {
				try {
					try {
						addMessage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public void addMessage() throws RemoteException {
		list = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList();
		while (true) {
			for (int i = 0; i < this.userInterface.fetchChatList().size(); i++) {
				Message message = this.userInterface.fetchChatList().get(i);
				if (message.getReceievedArray()[this.user.getId()] == false) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							System.out.println(message.getSource() + ": " + message.getMessage());
							list.add(message.getSource() + ": " + message.getMessage());
							vectorTimeStamp.increaseVectorTimeStamp(user.getId());
							vectorTimeStamp.setVectorTimeStamp(message.getVectorTimeStamp().getUserId(),
									message.getVectorTimeStamp().getVectorTimeStamp());
						}
					});
					this.userInterface.setMessageReceived(i, this.user.getId());
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException ex) {
					System.out.println("SLEEP INTERRUPTED EXCEPTION");
				}
			}
		}
	}

	public void sendMessageToAll(Message message) throws RemoteException {
		this.userInterface.addMessage(message);
		System.out.println(message.getMessage());
		if (message.getMessage().equals("/snapshot")) {
			this.userInterface.startSnapshot(this.vectorTimeStamp, this.user.getIpAddress() + ":" + this.user.getPort(),
					this.leaderIp + ":" + this.leaderPort);
		}
	}

	public void createRegistry() throws RemoteException {
		UserInterface leaderStub = (UserInterface) UnicastRemoteObject.exportObject(new UserImpl(), 0);
		this.registry = LocateRegistry.createRegistry(this.user.getPort());
		this.registry.rebind("leaderStub", leaderStub);
	}
}
