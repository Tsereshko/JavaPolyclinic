package com.example.reception.Views.Creator;

import com.example.reception.Controllers.Creator.DoctorCreatorController;
import com.example.reception.Network.Client;
import com.jfoenix.controls.JFXButton;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClassType;
import model.Department;
import model.Doctor;


public class DoctorCreatorView implements Creatable{
    DoctorCreatorController controller = new DoctorCreatorController();
    List<Department> list;
    Doctor doctor;
    public DoctorCreatorView(Doctor doctor){
        this.doctor = doctor;
    }
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public TextField cabinetTextField;
    public Label error;

    @FXML
    private JFXButton buttonButton;

    @FXML
    private Label comboError;

    @FXML
    private VBox fillVBox;

    @FXML
    private Label genderLabel;

    @FXML
    private ComboBox<String> jobCombo;

    @FXML
    private VBox labelVBox;

    @FXML
    private Label nameError;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label patronymicError;

    @FXML
    private Label patronymicLable;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private Label phoneError;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Label surnameError;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField surnameTextField;


    EventHandler<ActionEvent> buttonAction() {
        return (event -> saveAction());
    }

    @FXML
    void initialize() {
        buttonButton.setOnAction(buttonAction());

        list = FXCollections.observableArrayList(controller.getDepartmentList());
        ObservableList<String> strList = FXCollections.observableArrayList();
        for (Department department : list) {
            strList.add(department.getName());
        }
        jobCombo.setItems(strList);
        buttonButton.setText("Добавить");
        jobCombo.setValue(strList.get(0));

        if(doctor != null){
            usernameTextField.setText(doctor.getUsername());
            passwordTextField.setText(doctor.getPassword());
            nameTextField.setText(doctor.getName());
            surnameTextField.setText(doctor.getSurname());
            patronymicTextField.setText(doctor.getPatronymic());
            cabinetTextField.setText(String.valueOf(doctor.getCabinet()));
            phoneTextField.setText(doctor.getPhone());
            buttonButton.setText("Изменить");
            if(doctor.getDepartment() != null){
                jobCombo.setValue(doctor.getDepartment().getName());
            }else {
                jobCombo.setValue(strList.get(0));
            }
        }


    }

    @Override
    public EventHandler<ActionEvent> saveAction() {
        try {
            if (nameTextField.getText().trim().isEmpty()
                    || surnameTextField.getText().trim().isEmpty()
                    || patronymicTextField.getText().trim().isEmpty()
                    || usernameTextField.toString().trim().isEmpty()
                    || passwordTextField.getText().trim().isEmpty()
                    || cabinetTextField.getText().trim().isEmpty()
                    || phoneTextField.getText().trim().isEmpty()
            ) {
                error.setText("Все поля должны быть заполненными");
            } else if (controller.doctorCheck(usernameTextField.getText(), passwordTextField.getText())) {
                error.setText("Такая заапись уже существует");
            } else {
                Doctor doctor = new Doctor();
                doctor.setUsername(usernameTextField.getText());
                doctor.setPassword(passwordTextField.getText());
                doctor.setName(nameTextField.getText());
                doctor.setSurname(surnameTextField.getText());
                doctor.setPatronymic(patronymicTextField.getText());
                doctor.setCabinet(Integer.parseInt(cabinetTextField.getText()));
                doctor.setPhone(phoneTextField.getText());
                jobCombo.getSelectionModel().getSelectedIndex();
                doctor.setDepartment(list.get(jobCombo.getSelectionModel().getSelectedIndex()));

                Stage stage = (Stage) buttonButton.getScene().getWindow();
                stage.setUserData(doctor);
                stage.close();
            }
        } catch (NumberFormatException e) {
            error.setText("Неверный формат");
        }
        return null;
    }
}
