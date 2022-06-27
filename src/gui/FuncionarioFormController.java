package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Funcionario;
import model.exceptions.ValidationException;
import model.services.DepartmentService;
import model.services.FuncionarioService;

public class FuncionarioFormController implements Initializable {

	private Funcionario entity;

	private FuncionarioService service;

	private DepartmentService departmentService;

	private List<DataChangeListener> dataChangeListerners = new ArrayList<>();

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmail;

	@FXML
	private DatePicker dpBirthDate;

	@FXML
	private TextField txtBaseSalary;

	@FXML
	private ComboBox<Department> comboBoxDepartment;

	@FXML
	private Label labelErrorNome;

	@FXML
	private Label labelErrorEmail;

	@FXML
	private Label labelErrorBirthDate;

	@FXML
	private Label labelErrorBaseSalary;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	private ObservableList<Department> obsList;

	public void setFuncionario(Funcionario entity) {
		this.entity = entity;
	}

	public void setServices(FuncionarioService service, DepartmentService departmentService) {
		this.service = service;
		this.departmentService = departmentService;
	}

	public void subcribeDataChangeListener(DataChangeListener listener) {
		dataChangeListerners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entidade nula");
		}
		if (service == null) {
			throw new IllegalStateException("Serviço nulo");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListerners) {
			listener.onDataChanged();
		}
	}

	private Funcionario getFormData() {
		Funcionario obj = new Funcionario();

		ValidationException exception = new ValidationException("Validation error");

		obj.setId(Utils.tryParseToInt(txtID.getText()));

		if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("nome", "O campo não pode ser vazio");
		}
		obj.setNome(txtNome.getText());
		
		if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
			exception.addError("email", "O campo não pode ser vazio");
		}
		obj.setEmail(txtEmail.getText());
		
		if(dpBirthDate.getValue() == null) {
			exception.addError("birthDate", "O campo não pode ser vazio");
		}
		else {
			Instant instant = Instant.from(dpBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setBirthDate(Date.from(instant));
		}
		
		if (txtBaseSalary.getText() == null || txtBaseSalary.getText().trim().equals("")) {
			exception.addError("baseSalary", "O campo não pode ser vazio");
		}
		obj.setBaseSalary(Utils.tryParseToDouble(txtBaseSalary.getText()));
		
		obj.setDepartment(comboBoxDepartment.getValue());
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtNome, 70);
		Constraints.setTextFieldDouble(txtBaseSalary);
		Constraints.setTextFieldMaxLength(txtEmail, 60);
		Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");

		initializeComboBoxDepartment();
	}

	public void updateFormData() {
		txtID.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtEmail.setText(entity.getEmail());
		Locale.setDefault(Locale.US);
		txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
		if (entity.getBirthDate() != null) {
			dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
		}
		if (entity.getDepartment() == null) {
			comboBoxDepartment.getSelectionModel().selectFirst();
		}
		else {
			comboBoxDepartment.setValue(entity.getDepartment());
		}
	}

	public void loadAssociatedObjects() {
		List<Department> list = departmentService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboBoxDepartment.setItems(obsList);
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		labelErrorNome.setText((fields.contains("nome") ? errors.get("nome") : ""));
		
		labelErrorEmail.setText((fields.contains("email") ? errors.get("email") : ""));
		
		labelErrorBaseSalary.setText((fields.contains("baseSalary") ? errors.get("baseSalary") : ""));
		
		labelErrorBirthDate.setText((fields.contains("birthDate") ? errors.get("birthDate") : ""));
		
	}

	private void initializeComboBoxDepartment() {
		Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
			@Override
			protected void updateItem(Department item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		comboBoxDepartment.setCellFactory(factory);
		comboBoxDepartment.setButtonCell(factory.call(null));
	}
}
