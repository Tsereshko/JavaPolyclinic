package com.example.reception.Controllers.Creator;

import com.example.reception.Network.Client;
import model.ClassType;

import java.util.List;

public class DoctorCreatorController {
    Client client = Client.getInstance();

    public List getDepartmentList() {
        return client.getList(ClassType.DEPARTMENT);
    }
    public boolean doctorCheck(String username, String password){
        return client.doctorCheck(username, password) != null;
    }
}
