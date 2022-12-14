package com.example.reception.Views.Creator;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Patient;

public class PatientCreatorView implements Creatable {
    private Patient patient = null;
    public PatientCreatorView(Patient patient){
        this.patient= patient;
    }

    private ToggleGroup group;

    @FXML
    private HBox hBox;
    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private JFXButton buttonButton;

    @FXML
    private Label dateLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox fillVBox;

    @FXML
    private Label genderLabel;

    @FXML
    private VBox labelVBox;

    @FXML
    private RadioButton manRadioButton;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label patronymicLable;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField surnameTextField;

    @FXML
    private RadioButton womanRadioButton;

    @FXML
    private Label nameError;
    @FXML
    private Label dateError;


    @FXML
    void buttonAction(ActionEvent event) {
        saveAction();
    }

    @FXML
    void initialize() {
        buttonButton.setText("Добавить");
        if(patient != null){
            nameTextField.setText(patient.getName());
            surnameTextField.setText(patient.getSurname());
            patronymicTextField.setText(patient.getPatronymic());
            phoneTextField.setText(patient.getPhone());
            addressTextField.setText(patient.getAddress());

            LocalDate today = LocalDate.parse("2019-03-29");
            System.out.println(today);
            datePicker.setValue(today);

            buttonButton.setText("Изменить");
        }

        group =new ToggleGroup();
        manRadioButton.setToggleGroup(group);
        womanRadioButton.setToggleGroup(group);
        manRadioButton.fire();
    }

    @Override
    public EventHandler<ActionEvent> saveAction() {
        try {
            if (nameTextField.getText().trim().isEmpty()
                    || surnameTextField.getText().trim().isEmpty()
                    || patronymicTextField.getText().trim().isEmpty()
                    || datePicker.toString().trim().isEmpty()
                    || phoneTextField.getText().trim().isEmpty()
                    || addressTextField.getText().trim().isEmpty()
            ) {
                dateError.setText("Все поля должны быть заполненными");
            } else {
                Patient patient = new Patient();
                patient.setName(nameTextField.getText());
                patient.setSurname(surnameTextField.getText());
                patient.setPatronymic(patronymicTextField.getText());
                if(manRadioButton.isSelected()) patient.setGender(true);
                else patient.setGender(false);
                String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                patient.setDateOfBirth( date);
                patient.setStatus(false);
                patient.setPhone(phoneTextField.getText());
                patient.setAddress(addressTextField.getText());

                Stage stage = (Stage) buttonButton.getScene().getWindow();
                stage.setUserData(patient);
                stage.close();
            }
        } catch (NumberFormatException e) {
            dateError.setText("wrong format");
        }
        return null;
    }
}
