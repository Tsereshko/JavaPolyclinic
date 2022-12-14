package com.example.reception.Views.Menu;

import com.example.reception.Controllers.MenuController;
import com.example.reception.Main;
import com.example.reception.Views.Creator.Creatable;
import com.example.reception.Views.View;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Updatable;

import java.io.IOException;

public abstract class ViewMenu extends View {
    protected TableView table;
    protected MenuController controller;
    protected void addItem(Object object) {
        updateTable(
                controller.addItem(object)
        );
    }
    protected void removeItem() {
        updateTable(
                controller.removeItem(table.getSelectionModel().getSelectedItems())
        );
    }
    protected void updateItem(Updatable selected, Updatable newItem){
        updateTable(
                controller.updateItem(selected, newItem)
        );
    }
    protected Object launchCreator(Creatable creator, String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
            fxmlLoader.setController(creator);
            Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Регистратура");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            return stage.getUserData();
        } catch (IOException e) {
            System.out.println("Не удалость открыть файл");
            throw new RuntimeException(e);
        }
    }

    protected void updateTable(ObservableList list){
        table.setItems(list);
    }
    protected void updateTable(){
        updateTable(controller.updateList());
    }
}
