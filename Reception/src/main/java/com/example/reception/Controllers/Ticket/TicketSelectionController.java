package com.example.reception.Controllers.Ticket;

import com.example.reception.Network.Client;
import model.ClassType;
import model.Department;
import model.Patient;

import java.util.List;

public class TicketSelectionController {

    Client client = Client.getInstance();

    public List<String> getFreeTime(String date, Department department) {
        return client.getFreeTime(date, department);
    }
    public void createTicket(String date, Department department, String time, Patient patient){
        if(client.createTicket(date, department, time, patient)){
            System.out.println("всё ок");
        }else {
            System.out.println("Не ок");
        }
    }
}
