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
import model.Analysis;
import model.Patient;


public class AnalysisCreatorView {
    @FXML
    private JFXButton addButton;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label surnameLabel;

    @FXML
    private JFXTextArea textArea;

    @FXML
    private VBox vBox;

    @FXML
    void addAction(ActionEvent event) {
        Stage stage = (Stage) addButton.getScene().getWindow();
        Patient patient = (Patient) stage.getUserData();
        String date =  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        Analysis analysis = new Analysis(nameTextField.getText(), textArea.getText(), patient, date);
        stage.setUserData(analysis);
        stage.close();
    }

    @FXML
    void initialize() {
        vBox.setVgrow(textArea, Priority.ALWAYS);
    }

}
