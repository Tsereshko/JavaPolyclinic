package com.example.doctor.View;

import com.example.doctor.Controllers.Controller;
import com.example.doctor.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View {
    Controller controller;
    protected void launchScene(Stage stage, String path, String name){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
            stage.setTitle(name);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Не удалость открыть файл");
            throw new RuntimeException(e);
        }
    }
    protected void launchScene(View view, Stage stage, String path, String name){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
            fxmlLoader.setController(view);
            Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
            stage.setTitle(name);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Не удалость открыть файл");
            throw new RuntimeException(e);
        }
    }
}
