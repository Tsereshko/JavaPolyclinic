package com.example.reception.Controllers.Registrars;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton signInButton;
    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize() {

    }

    /*@FXML
    void signInAction(ActionEvent event) {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Utils.showScene(stage, "/Menu/patient-menu.fxml", "Пациенты");
    }
    @FXML
    void signUpAction(ActionEvent event) {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Utils.showScene(stage, "/Registrars/sign-up.fxml", "Регистрация");
    }*/
}
