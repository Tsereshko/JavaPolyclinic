package com.example.reception.Views;

import com.example.reception.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class View {
    protected void launchScene(Stage stage, String path, String name){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
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
