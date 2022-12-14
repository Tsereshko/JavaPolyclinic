package com.example.doctor.Controllers;

import com.example.doctor.Main;
import com.example.doctor.Network.Client;
import com.example.doctor.View.PatientListView;
import com.example.doctor.View.View;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClassType;
import model.Doctor;

public class SignInView extends View {
    SignInController controller;
    public Label errorLabel;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private TextField usernameTextField;


    @FXML
    void signInAction(ActionEvent event) {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        Doctor doctor = controller.getDoctor(usernameTextField.getText(), passwordTextField.getText());
        if (doctor != null){
            launchScene(new PatientListView(doctor), stage, "/Patient/patient-list.fxml", "Пациенты");
        }else {
            errorLabel.setText("записи с такими параметрами не существует");
        }
    }

    @FXML
    void initialize() {
        controller = new SignInController();
    }

}
