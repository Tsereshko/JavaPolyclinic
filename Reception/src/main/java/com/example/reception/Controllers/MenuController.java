package com.example.reception.Controllers;

import com.example.reception.Network.Client;
import javafx.collections.ObservableList;
import model.*;

import java.util.List;

public abstract class MenuController implements Controllable{

    public Client client = Client.getInstance();
    @Override
    public ObservableList addItem(Object object) {
        if (object != null) {
            client.add(object);
        }
        return updateList();
    }

    @Override
    public ObservableList removeItem(List list) {
        if (!list.isEmpty()) {
            client.remove(list);
        }
        return updateList();
    }

    @Override
    public ObservableList updateItem(Updatable selectedItem, Updatable newItem){
        if (newItem != null) {
            selectedItem.update(newItem);

            client.update(selectedItem);
        }
        return updateList();
    }

    @Override
    public abstract ObservableList updateList();
}
