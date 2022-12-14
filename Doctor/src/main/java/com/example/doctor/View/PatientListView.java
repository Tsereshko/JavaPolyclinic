package com.example.doctor.View;

import com.example.doctor.Controllers.PatientListController;
import com.example.doctor.Main;
import com.example.doctor.Network.Client;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Doctor;
import model.Patient;
import model.Ticket;

import java.io.IOException;
import java.util.List;

public class PatientListView extends View{
    PatientListController controller;
    private Doctor doctor;
    public PatientListView(Doctor doctor){
        this.doctor = doctor;
    }
    @FXML
    private JFXButton backButton;
    @FXML
    private MenuItem deleteButton;
    public TableView<Ticket> patientTable;
    @FXML
    private TableColumn<Ticket, String> dateColumn;

    @FXML
    private TableColumn<Ticket, String> nameColumn;

    @FXML
    private TableColumn<Ticket, String> patronymicColumn;

    @FXML
    private JFXButton showPatientButton;

    @FXML
    private TableColumn<Ticket, String> surnameColumn;

    @FXML
    private TableColumn<Ticket, String> timeColumn;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {
        controller = new PatientListController();
        vBox.setVgrow(patientTable, Priority.ALWAYS);

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getName()));
        surnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getSurname()));
        patronymicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getPatronymic()));

        updateTable();

        showPatientButton.setOnAction(showPatientAction());
        backButton.setOnAction(backAction());

        deleteButton.setOnAction(deleteButtonAction());
    }

    private EventHandler<ActionEvent> deleteButtonAction() {
        return (event -> {
            List<Ticket> list = patientTable.getSelectionModel().getSelectedItems();
            Client.remove(list);
            updateTable();

        });
    }

    private EventHandler<ActionEvent> backAction() {
        return (event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/SignIn/sign-in.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
                stage.setTitle("Врач");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Не удалость открыть файл");
                throw new RuntimeException(e);
            }
        });
    }

    private EventHandler<ActionEvent> showPatientAction() {
        return (event -> {
            Stage stage = (Stage) showPatientButton.getScene().getWindow();
            stage.setUserData(doctor);
            Ticket ticket = patientTable.getSelectionModel().getSelectedItem();
            launchScene(new PatientMenuView(ticket.getPatient()), stage, "/Patient/patient-menu.fxml", "Пациент");
        });
    }
    private void updateTable() {
        patientTable.setItems(FXCollections.observableArrayList(
                controller.getTicket(doctor)
        ));
    }
}
