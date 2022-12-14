package com.example.reception.Controllers;

import javafx.collections.ObservableList;
import model.Department;
import model.Patient;
import model.Updatable;

import java.util.List;

public interface Controllable {
    ObservableList addItem(Object object);
    ObservableList removeItem(List list);
    ObservableList updateItem(Updatable selectedItem, Updatable newItem);
    ObservableList updateList();
}
