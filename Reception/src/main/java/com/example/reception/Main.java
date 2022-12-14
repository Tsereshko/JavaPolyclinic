package com.example.reception;

import com.example.reception.Network.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Menu/patient-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
        stage.setTitle("Пациенты");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}