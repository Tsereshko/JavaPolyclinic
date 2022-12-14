package com.example.reception.Views.Ticket;

import com.example.reception.Controllers.Ticket.TicketSelectionController;
import com.example.reception.Network.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class TicketSelectionView {
    TicketSelectionController controller = new TicketSelectionController();
    List<Doctor> doctorList;
    List<Ticket> ticketList;
    String date;
    Department department;

    public TicketSelectionView(String date, Department department) {
        this.date = date;
        this.department = department;
    }

    @FXML
    private JFXButton addButton;
    @FXML
    private JFXListView<String> listView;
    @FXML
    private VBox vBox;

    @FXML
    void initialize() {

        vBox.setVgrow(listView, Priority.ALWAYS);

        ObservableList<String> observableList = FXCollections.observableArrayList(
                controller.getFreeTime(date, department)
        );

        listView.setItems(observableList);

        addButton.setOnAction(addButtonAction());
    }

    private EventHandler<ActionEvent> addButtonAction() {
        return (event -> {
            String time = listView.getSelectionModel().getSelectedItem();
            Patient patient = (Patient) addButton.getScene().getWindow().getUserData();
            controller.createTicket(date, department, time, patient);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        });
    }

}
