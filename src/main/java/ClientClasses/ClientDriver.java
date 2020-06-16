package ClientClasses;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Message;

/**
 *
 * @author sachitgagneja
 */
public class ClientDriver extends Application {
	public ListView<String> list;
	private Client client;

	@SuppressWarnings("restriction")
	public Scene addDetails(Stage primaryStage) {
		GridPane grid = new GridPane();
		TextField textField = new TextField("");
		TextField port = new TextField("1099");
		Label name = new Label("Enter your Name");
		Label portNo = new Label("Default Port Number");
		Label leaderIPLabel = new Label("Default Leader IP Address");
		Label leaderportNo = new Label("Default Leader Port Number");
		Label error = new Label();

		TextField leaderIP = new TextField("localhost");
		TextField leaderport = new TextField("1099");

		Button submit = new Button("Submit");

		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent Event) {
				try {
					String localIP = "localhost";
					try {
						InetAddress address;
						address = InetAddress.getLocalHost();
						localIP = address.getHostAddress();
					} catch (UnknownHostException ex) {
						ex.printStackTrace();
					}
					client = new Client(textField.getText(), localIP, Integer.parseInt(port.getText()),
							leaderIP.getText(), Integer.parseInt(leaderport.getText()), ClientDriver.this);
					primaryStage.setScene(constructChatBox(client));
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		grid.add(textField, 0, 0);
		grid.add(name, 1, 0);
		grid.add(port, 0, 1);
		grid.add(portNo, 1, 1);
		grid.add(leaderIP, 0, 2);
		grid.add(leaderIPLabel, 1, 2);
		grid.add(leaderport, 0, 3);
		grid.add(leaderportNo, 1, 3);
		grid.add(submit, 0, 5);
		grid.add(error, 0, 6);
		return new Scene(grid, 400, 400);
	}

	@SuppressWarnings("restriction")
	public Scene constructChatBox(Client client) {
		GridPane rootPane = new GridPane();

		list = new ListView<String>();

		list.setItems(client.list);

		TextField chatTextField = new TextField();

		chatTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					client.sendMessageToAll(new Message(chatTextField.getText(), client.user, client.user, 3,
							client.vectorTimeStamp, client.userInterface.isSnapShotStarted()));

				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
				chatTextField.clear();
			}
		});

		Label instructionsLabel = new Label("Press Enter to send message to All");
		rootPane.add(list, 0, 0);
		rootPane.add(instructionsLabel, 0, 3);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100);
		rootPane.getColumnConstraints().addAll(column1);
		rootPane.add(chatTextField, 0, 1);
		return new Scene(rootPane, 400, 400);
	}

	public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(addDetails(stage));
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Platform.exit();
		System.exit(0);
	}
}
