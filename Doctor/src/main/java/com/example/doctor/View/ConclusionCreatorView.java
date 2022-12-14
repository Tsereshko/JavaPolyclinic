package com.example.doctor.View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Conclusion;
import model.Patient;

public class ConclusionCreatorView {

    public TextField diseaseTextField;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        Conclusion conclusion = new Conclusion(textArea.getText(), date, nameTextField.getText(), patient);
        stage.setUserData(conclusion);
        stage.close();
    }

    @FXML
    void initialize() {
        vBox.setVgrow(textArea, Priority.ALWAYS);
    }

}
