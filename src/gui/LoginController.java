package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private Button button_login;
	
	@FXML
	private Button button_sign_up;
	
	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		button_login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());
				
			}
		});
		
		button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "SignUp.fxml", "Cadastre-se", null);
			}
		});
		
	}

}
