package com.example.doctor.Controllers;

import com.example.doctor.Network.Client;
import model.Doctor;

public class SignInController extends Controller{
    Doctor getDoctor(String username, String password) {
        return client.getDoctor(username, password);
    }
}
