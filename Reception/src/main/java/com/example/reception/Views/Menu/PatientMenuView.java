package com.example.reception.Views.Menu;

import com.example.reception.Controllers.Menu.PatientMenuController;
import com.example.reception.Main;
import com.example.reception.Views.Creator.PatientCreatorView;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Patient;

import java.io.IOException;

public class PatientMenuView extends ViewMenu {
    @FXML
    private VBox vBox;
    private HBox hBox;
    @FXML
    private TableColumn<Patient, String> addressColumn;
    @FXML
    private TableColumn<Patient, String> dateColumn;
    @FXML
    private Button departmentButton;
    @FXML
    private Button doctorButton;
    @FXML
    private TableColumn<Patient, String> genderColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private Label patientLabel;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> patronymicColumn;
    @FXML
    private TableColumn<Patient, String> phoneColumn;
    @FXML
    private TableColumn<Patient, String> statusColumn;
    @FXML
    private TableColumn<Patient, String> surnameColumn;

    @FXML
    void addAction(ActionEvent event) {
        Patient patient = launchPatientCreator();
        addItem(patient);
    }

    @FXML
    void removeAction(ActionEvent event) {
        removeItem();
    }

    public void editMenuItemAction(ActionEvent event) {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        Patient newPatient = launchPatientCreator(selected);
        updateItem(selected, newPatient);
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
    void doctorAction(ActionEvent event) {
        launchScene(
                (Stage) doctorButton.getScene().getWindow(),
                "/Menu/doctor-menu.fxml",
                "Врачи"
        );
    }

    private Patient launchPatientCreator(Patient patient) {
        return (Patient) launchCreator(new PatientCreatorView(patient), "/Creators/patient-creator.fxml");
    }

    private Patient launchPatientCreator() {
        return launchPatientCreator(null);
    }


    @FXML
    void initialize() {
        controller = new PatientMenuController();
        table = patientTable;

        hBox.setHgrow(patientLabel, Priority.ALWAYS);
        hBox.setHgrow(doctorButton, Priority.ALWAYS);
        hBox.setHgrow(departmentButton, Priority.ALWAYS);

        vBox.setVgrow(patientTable, Priority.ALWAYS);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        genderColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getGender()) return new SimpleStringProperty("Мужчина");
            else return new SimpleStringProperty("Женщина");
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        statusColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getStatus()) return new SimpleStringProperty("Болен");
            else return new SimpleStringProperty("Здоров");
        });
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));


        patientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        updateTable(controller.updateList());
    }


    public void ticketMenuItemAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Tickets/ticket-search.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Регистратура");
            stage.initModality(Modality.APPLICATION_MODAL);
            Patient selected = patientTable.getSelectionModel().getSelectedItem();
            stage.setUserData(selected);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Не удалость открыть файл");
            throw new RuntimeException(e);
        }
    }
}
