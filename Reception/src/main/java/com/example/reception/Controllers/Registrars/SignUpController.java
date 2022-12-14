package com.example.reception.Controllers.Registrars;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private PasswordField surnameField;

    @FXML
    void initialize() {
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert surnameField != null : "fx:id=\"surnameField\" was not injected: check your FXML file 'sign-up.fxml'.";

    }
    /*@FXML
    void signUpAction(ActionEvent event) {
        Utils.showScene((Stage) signUpButton.getScene().getWindow(), "/Registrars/sign-in.fxml", "Регистратура");
    }
    @FXML
    void backAction(ActionEvent event){
        Utils.showScene((Stage) signUpButton.getScene().getWindow(), "/Registrars/sign-in.fxml", "Регистратура");
    }*/
}
