package com.example.reception.Views.Menu;

import com.example.reception.Views.Creator.DoctorCreatorView;
import com.example.reception.Controllers.Menu.DoctorMenuController;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Department;
import model.Doctor;

public class DoctorMenuView extends ViewMenu {
    @FXML
    public TableColumn<Doctor, String> departmentColumn;
    @FXML
    private VBox vBox;
    @FXML
    private TableColumn<Doctor, Integer> cabinetColumn;
    @FXML
    private Button departmentButton;
    @FXML
    private Label doctorLabel;
    @FXML
    private HBox hBox;
    @FXML
    private TableColumn<Doctor, String> nameColumn;
    @FXML
    private Button patientButton;
    @FXML
    private TableColumn<Doctor, String> patronymicColumn;
    @FXML
    private TableColumn<Doctor, String> phoneColumn;
    @FXML
    private TableColumn<Doctor, String> surnameColumn;
    @FXML
    private TableView<Doctor> tableDoctor;

    @FXML
    void addAction(ActionEvent event) {
        Doctor doctor = launchDoctorCreator();
        addItem(doctor);
    }
    @FXML
    void removeAction(ActionEvent event) {
        removeItem();
    }

    public void editMenuItemAction(ActionEvent event) {
        Doctor selected = tableDoctor.getSelectionModel().getSelectedItem();
        Doctor newItem = launchDoctorCreator(selected);
        updateItem(selected, newItem);
        launchScene(
                (Stage) departmentButton.getScene().getWindow(),
                "/Menu/doctor-menu.fxml",
                "Врачи"
        );
    }

    @FXML
    void departmentAction(ActionEvent event) {
        launchScene(
                (Stage) departmentButton.getScene().getWindow(),
                "/Menu/department-menu.fxml",
                "Отделения"
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

    private Doctor launchDoctorCreator(Doctor doctor) {
        return (Doctor) launchCreator(new DoctorCreatorView(doctor), "/Creators/Doctor-creator.fxml");
    }
    private Doctor launchDoctorCreator() {
        return launchDoctorCreator(null);
    }

    @FXML
    void initialize() {
        controller = new DoctorMenuController();
        table = tableDoctor;

        hBox.setHgrow(patientButton, Priority.ALWAYS);
        hBox.setHgrow(doctorLabel, Priority.ALWAYS);
        hBox.setHgrow(departmentButton, Priority.ALWAYS);

        vBox.setVgrow(tableDoctor, Priority.ALWAYS);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        cabinetColumn.setCellValueFactory(new PropertyValueFactory<>("cabinet"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        departmentColumn.setCellValueFactory(cellData -> {
            Department department = cellData.getValue().getDepartment();
            if(department == null){
                return  new SimpleStringProperty("Удаленно");
            }else return new SimpleStringProperty(department.getName());
        });

        tableDoctor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        updateTable(controller.updateList());
    }
}
