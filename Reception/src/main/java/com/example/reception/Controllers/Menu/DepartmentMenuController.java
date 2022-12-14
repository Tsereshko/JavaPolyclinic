package com.example.reception.Controllers.Menu;

import com.example.reception.Controllers.MenuController;
import com.example.reception.Network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassType;
import model.Patient;

public class DepartmentMenuController extends MenuController {
    public ObservableList<Patient> updateList(){
        return FXCollections.observableArrayList(client.getList(ClassType.DEPARTMENT));
    }
}
