package com.example.reception.Views.Ticket;

import com.example.reception.Controllers.Ticket.TicketSearchController;
import com.example.reception.Main;
import com.example.reception.Network.Client;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClassType;
import model.Department;
import model.Patient;

public class TicketSearchView {
    TicketSearchController controller = new TicketSearchController();
    private List<Department> list;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> departmentCombo;

    @FXML
    private VBox fillVBox;

    @FXML
    private VBox labelVBox;

    @FXML
    private Label nameLabel;

    @FXML
    private JFXButton searchButton;

    @FXML
    private Label surnameLabel;

    @FXML
    void searchAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Tickets/ticket-selection.fxml"));
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        fxmlLoader.setController(
                new TicketSelectionView(date, list.get(departmentCombo.getSelectionModel().getSelectedIndex()))
        );
        Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
        Stage stage = (Stage) searchButton.getScene().getWindow();
        
        stage.setScene(scene);
        stage.setTitle("Регистратура");
        Patient patient = (Patient) stage.getUserData();
        System.out.println(patient);
        stage.setUserData(patient);
        
        stage.show();
    }

    @FXML
    void initialize() {
        list = controller.getDepartmentList();

        ObservableList<String> strList = FXCollections.observableArrayList();
        for (Department department : list) {
            strList.add(department.getName());
        }

        departmentCombo.setItems(strList);
        departmentCombo.setValue(strList.get(0));

        datePicker.setValue(LocalDate.now());
    }

}
