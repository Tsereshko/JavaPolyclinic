package com.example.doctor.Controllers;

import com.example.doctor.Main;
import com.example.doctor.Network.Client;
import com.example.doctor.View.PatientMenuView;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class PatientListController extends Controller{
    public List<Ticket> getTicket(Doctor doctor) {
        return client.getTicket(doctor);
    }
}
