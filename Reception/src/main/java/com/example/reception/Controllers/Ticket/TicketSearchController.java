package com.example.reception.Controllers.Ticket;

import com.example.reception.Network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassType;


public class TicketSearchController {

    Client client = Client.getInstance();

    public ObservableList getDepartmentList() {
        return FXCollections.observableArrayList(client.getList(ClassType.DEPARTMENT));
    }
}
