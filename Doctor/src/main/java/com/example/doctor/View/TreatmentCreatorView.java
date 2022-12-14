package com.example.doctor.View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;

public class TreatmentCreatorView {
    public TextField dosageTextField;
    public VBox vBox;
    public Label errorLabel;
    @FXML
    private JFXButton addButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label nameLabel11;

    @FXML
    private Label nameLabel111;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nameTextField1;

    @FXML
    private Label surnameLabel;

    @FXML
    private JFXTextArea textArea;

    @FXML
    void addAction(ActionEvent event) {
        try {
            Stage stage = (Stage) addButton.getScene().getWindow();
            Patient patient = (Patient) stage.getUserData();
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Treatment treatment = new Treatment(date, nameTextField.getText(), Integer.parseInt(dosageTextField.getText()), textArea.getText(), patient);
            stage.setUserData(treatment);
            stage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Неверный формат");
        }
    }

    @FXML
    void initialize() {
        vBox.setVgrow(textArea, Priority.ALWAYS);
    }

}
