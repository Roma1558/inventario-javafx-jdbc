package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtils {
	
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
		Parent root = null;
		
		if(username != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				LoggedController loginController = loader.getController();
				loginController.setUserInformation(username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}
	
	public static void signUpUser(ActionEvent event, String username, String password) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetorh", "root", "Projeto2022#");
			psCheckUserExists = connection.prepareStatement("SELECT * FROM usuario WHERE username = ?");
			psCheckUserExists.setString(1, username);
			resultSet = psCheckUserExists.executeQuery();
			
			if(resultSet.isBeforeFirst()) {
				System.out.println("O usuário já existe!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Você não pode utilizar esse nome de usuário.");
				alert.show();
			} else {
				psInsert = connection.prepareStatement("INSERT INTO usuario (username, password VALUES (?, ?)");
				psInsert.setString(1, username);
				psInsert.setString(2, password);
				psInsert.executeUpdate();
				
				changeScene(event, "Logged.fxml", "Bem-vindo!", username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void logInUser(ActionEvent event, String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetorh", "root", "Projeto2022#");
			preparedStatement = connection.prepareStatement("SELECT password FROM usuario WHERE username = ?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {
				System.out.println("Usuário não encontrado no banco de dados!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.show();
			} else {
				while (resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					if(retrievedPassword.equals(password)) {
						changeScene(event, "Logged.fxml", "Bem-vindo!", username);
					} else {
						System.out.println("As senhas não correspondem!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("As credênciais fornecidas estão incorretas!");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
