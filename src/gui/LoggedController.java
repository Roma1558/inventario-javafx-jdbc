package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoggedController implements Initializable {
	
	@FXML
	private Button button_log;
	
	@FXML
	private Label label_welcome;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		button_log.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "Login.fxml", "Log in!", null);
				
			}
		});
		
	}

	public void setUserInformation(String username) {
		label_welcome.setText("Bem-vindo " + username + "!");
	}
}
