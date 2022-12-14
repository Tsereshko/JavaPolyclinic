package com.example.reception.Views.Menu;

import com.example.reception.Controllers.Menu.DepartmentMenuController;
import com.example.reception.Views.Creator.DepartmentCreatorView;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Department;

public class DepartmentMenuView  extends ViewMenu {
    public VBox vBox;
    public MenuItem editMenuItem;
    @FXML
    private JFXButton addButton;

    @FXML
    private Label departmentLabel;

    @FXML
    private TableView<?> departmentTable;

    @FXML
    private Button doctorButton;

    @FXML
    private HBox hBox;

    @FXML
    private TableColumn<Department, String> nameColumn;
    @FXML
    private TableColumn<Department, String> phoneColumn;

    @FXML
    private Button patientButton;


    @FXML
    private JFXButton removeButton;

    @FXML
    void addAction(ActionEvent event) {
        Department department = launchDepartmentCreator();
        addItem(department);
    }
    @FXML
    void removeAction(ActionEvent event) {
        removeItem();
    }
    @FXML
    void doctorAction(ActionEvent event) {
        launchScene(
                (Stage) patientButton.getScene().getWindow(),
                "/Menu/doctor-menu.fxml",
                "Врачи"
        );
    }
    @FXML
    void patientAction(ActionEvent event) {
        launchScene(
                (Stage) patientButton.getScene().getWindow(),
                "/Menu/patient-menu.fxml",
                "Пациенты"
        );
    }
    public void editMenuItemAction(ActionEvent event) {
        Department selected = (Department) departmentTable.getSelectionModel().getSelectedItem();
        Department newItem = launchDepartmentCreator(selected);
        updateItem(selected, newItem);
    }
    private Department launchDepartmentCreator(Department department) {
        return (Department) launchCreator(new DepartmentCreatorView(department), "/Creators/department-creator.fxml");
    }
    private Department launchDepartmentCreator() {
        return launchDepartmentCreator(null);
    }


    @FXML
    void initialize() {
        controller = new DepartmentMenuController();
        table = departmentTable;

        hBox.setHgrow(patientButton, Priority.ALWAYS);
        hBox.setHgrow(doctorButton, Priority.ALWAYS);
        hBox.setHgrow(departmentLabel, Priority.ALWAYS);

        vBox.setVgrow(departmentTable, Priority.ALWAYS);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        departmentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        updateTable();
    }


}

