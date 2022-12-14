package com.example.doctor.Controllers;

import com.example.doctor.Network.Client;
import model.ClassType;
import model.Patient;

import java.util.List;

public class PatientMenuController extends Controller{

    public void add(Object userData) {
        Client.add(userData);
    }

    public List getList(Patient patient, ClassType type) {
        return Client.getPatientList(patient, type);
    }
}
