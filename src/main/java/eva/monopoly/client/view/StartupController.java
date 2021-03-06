package eva.monopoly.client.view;

import java.io.IOException;

import eva.monopoly.client.MonopolyClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartupController {
	@FXML
	TextField nickname;
	@FXML
	Button okBttn;
	@FXML
	VBox layout;
	private boolean errorAlreadyShown = false;

	public void nicknameOk(ActionEvent event) throws IOException {
		nicknameAction(event);
	}

	public void nicknameEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			nicknameAction(event);
		}
	}

	public void nicknameAction(Event event) throws IOException {
		// set Playername from Textfield nickname and switch to mainMenu.fxml
		if (nickname.getText().equals("")) {
			if (!errorAlreadyShown) {
				nickname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
				Label errorMessage = new Label("Bitte einen Nicknamen eingeben!");
				errorMessage.setTextFill(Color.RED);
				layout.getChildren().add(2, errorMessage);
				errorAlreadyShown = true;
			}
			return;
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("mainMenu.fxml"));
		Parent mainMenuParent = loader.load();
		Scene mainMenu = new Scene(mainMenuParent);
		String uName = nickname.getText();
		MonopolyClient.setName(uName);
		MainMenuController menuControl = MainMenuController.getInstance();
		menuControl.initData(uName);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainMenu);
		window.show();
	}

	public void menuClose(ActionEvent event) throws IOException {
		Platform.exit();
	}
}
