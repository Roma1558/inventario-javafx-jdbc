package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable{

	@FXML
	private Button button_signup;
	
	@FXML
	private Button button_login;
	
	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		button_signup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
					DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText());
				} else {
					System.out.println("Por favor preencha todas informações.");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Por favor preencha todas as informações para cadastrar-se");
					alert.show();
				}
				
			}
		});
		
		button_login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "Login.fxml", "Gestor de RH", null);
			}
		});
		
	}

}
