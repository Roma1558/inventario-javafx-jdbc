package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setTitle("Gestor de RH");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
	
	public static void main2(String[] args) { launch(args); }
}
