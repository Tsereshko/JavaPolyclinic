package com.example.doctor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
    }

    public static void main(String[] args) {
        launch();
    }
}