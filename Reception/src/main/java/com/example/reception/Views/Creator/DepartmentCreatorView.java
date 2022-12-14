package com.example.reception.Views.Creator;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Department;

import java.time.LocalDate;

public class DepartmentCreatorView implements Creatable {
    @FXML
    private Label error;
    private Department department;
    /*public DepartmentCreatorController(Department department){
        this.department = department;
    }*/
    public DepartmentCreatorView(Department department){
        this.department= department;
    }
    public TextField phoneTextField;
    public JFXButton button;
    @FXML
    private VBox fillVBox;

    @FXML
    private VBox labelVBox;

    @FXML
    private Label nameError;

    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label phoneError;
    @FXML
    private Label phoneLabel;

    @FXML
    void initialize() {
        button.setOnAction(saveAction());
        button.setText("Добавить");

        if(department != null){
            nameTextField.setText(department.getName());
            phoneTextField.setText(department.getPhone());

            button.setText("Изменить");
        }

    }

    @Override
    public EventHandler<ActionEvent> saveAction() {
        return (event -> {
            try {
                if (nameTextField.getText().trim().isEmpty()
                        || phoneTextField.getText().trim().isEmpty()
                ) {
                    error.setText("Fields must be filled");
                } else {
                    Department department = new Department();
                    department.setName(nameTextField.getText());
                    department.setPhone(phoneTextField.getText());

                    Stage stage = (Stage) button.getScene().getWindow();
                    stage.setUserData(department);
                    stage.close();
                }
            } catch (NumberFormatException e) {
                error.setText("wrong format");
            }
        });
    }
}
