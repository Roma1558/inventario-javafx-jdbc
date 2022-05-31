package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;


public class DepartmentFormController implements Initializable {
	
	private Department entity;

	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	public void setDepartment (Department entity) {
		this.entity = entity;
	}
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("Bot�o Salvar OK");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("Bot�o Cancelar OK");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtNome, 50);
	}

	public void updateFormData() {
		txtID.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
	}
}