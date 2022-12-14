package com.example.doctor.View;

import com.example.doctor.Controllers.Controller;
import com.example.doctor.Controllers.PatientListController;
import com.example.doctor.Controllers.PatientMenuController;
import com.example.doctor.Main;
import com.example.doctor.Network.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.List;

public class PatientMenuView extends View{
    PatientMenuController controller = new PatientMenuController();
    Patient patient;
    List<Analysis> analysisList;
    List<Treatment> treatmentList;
    List<Conclusion> conclusionList;
    public PatientMenuView(Patient patient) {
        this.patient = patient;
    }

    public HBox labelHBox;
    public VBox vBox;
    public Label nameLabel;
    public Label surnameLabel;
    public Label patronymicLabel;
    public Label genderLabel;
    public Label dateLabel;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton analysisButton;
    @FXML
    private Label analysisLabel;
    @FXML
    private TableView<Analysis> analysisTable;
    @FXML
    private JFXButton conclusionButton;
    @FXML
    private Label conclusionLabel;
    @FXML
    private TableView<Conclusion> conclusionTable;

    @FXML
    private TableColumn<Analysis, String> dateAnalysis;
    @FXML
    private TableColumn<Analysis, String> nameAnalysis;

    @FXML
    private TableColumn<Conclusion, String> dateConclusion;

    @FXML
    private TableColumn<Treatment, String> dateTreatment;

    @FXML
    private TableColumn<Conclusion, String> diseaseConclusion;

    @FXML
    private TableColumn<Treatment, Integer> dosageTreatment;

    @FXML
    private HBox hBox;


    @FXML
    private TableColumn<Treatment, String> nameTreatment;

    @FXML
    private JFXTextArea textArea;

    @FXML
    private JFXButton treatmentButton;
    @FXML
    private Label treatmentLabel;
    @FXML
    private TableView<Treatment> treatmentTable;

    @FXML
    void initialize() {


        hBox.setHgrow(analysisTable, Priority.ALWAYS);
        hBox.setHgrow(conclusionTable, Priority.ALWAYS);
        hBox.setHgrow(treatmentTable, Priority.ALWAYS);

        vBox.setVgrow(textArea, Priority.ALWAYS);

        setLabel();

        analysisButton.setOnAction(analysisAction());
        conclusionButton.setOnAction(conclusionAction());
        treatmentButton.setOnAction(treatmentAction());
        backButton.setOnAction(backAction());

        tablesSetting();

        updateTables();
    }

    private EventHandler<ActionEvent> backAction() {
        return (event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            launchScene(new PatientListView((Doctor) stage.getUserData()), stage, "/Patient/patient-list.fxml", "Пациенты");
        });
    }

    private void updateTables() {
        updateTable(analysisTable, ClassType.ANALYSIS);
        updateTable(treatmentTable, ClassType.TREATMENT);
        updateTable(conclusionTable, ClassType.CONCLUSION);
    }

    private void updateTable(TableView table, ClassType type) {
        ObservableList list = FXCollections.observableArrayList(controller.getList(patient, type));
        switch (type){
            case ANALYSIS -> analysisList =  list;
            case TREATMENT -> treatmentList = list;
            case CONCLUSION -> conclusionList = list;
        }
        table.setItems(FXCollections.observableArrayList(Client.getPatientList(patient, type)));
    }

    private EventHandler<ActionEvent> treatmentAction() {
        return (event -> {
            launchCreator("/Creators/treatment-creator.fxml");
            updateTable(treatmentTable, ClassType.TREATMENT);
        });
    }

    private EventHandler<ActionEvent> conclusionAction() {
        return (event -> {
            launchCreator("/Creators/conclusion-creator.fxml");
            updateTable(conclusionTable, ClassType.CONCLUSION);
        });
    }

    private EventHandler<ActionEvent> analysisAction() {
        return (event -> {
            launchCreator("/Creators/analysis-creator.fxml");
            updateTable(analysisTable, ClassType.ANALYSIS);
        });
    }

    private void launchCreator(String file) {
        Stage stage = new Stage();
        stage.setUserData(patient);

        launchScene(stage, file, "Добавление");
        controller.add(stage.getUserData());
    }

    private void setLabel() {
        nameLabel.setText(patient.getName());
        surnameLabel.setText(patient.getSurname());
        patronymicLabel.setText(patient.getPatronymic());
        genderLabel.setText(patient.getGender() ? "Мужчина" : "Женщина");
        dateLabel.setText(patient.getDateOfBirth());
    }

    private void tablesSetting() {
        nameAnalysis.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateAnalysis.setCellValueFactory(new PropertyValueFactory<>("date"));

        nameTreatment.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        dateTreatment.setCellValueFactory(new PropertyValueFactory<>("date"));
        dosageTreatment.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        dateConclusion.setCellValueFactory(new PropertyValueFactory<>("date"));
        diseaseConclusion.setCellValueFactory(new PropertyValueFactory<>("disease"));

        analysisTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldSelected, newSelected) -> {
            if((Integer)newSelected != -1) textArea.setText(analysisList.get((Integer) newSelected).getResult());
        });
        treatmentTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldSelected, newSelected) -> {
            if((Integer)newSelected != -1) textArea.setText(treatmentList.get((Integer) newSelected).getReport());
        });
        conclusionTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldSelected, newSelected) -> {
            if((Integer)newSelected != -1) textArea.setText(conclusionList.get((Integer) newSelected).getReport());
        });
    }

}