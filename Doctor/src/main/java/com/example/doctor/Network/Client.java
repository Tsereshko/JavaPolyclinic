package com.example.doctor.Network;

import model.ClassType;
import model.Commands;
import model.Doctor;
import model.Patient;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {
    static Client client;
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    private Client() {
        try {
            socket = new Socket("127.0.0.1", 5555);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("нету подключения");
            throw new RuntimeException(e);
        }
    }
    public static Client getInstance(){
        if(client == null){
            client = new Client();
            return client;
        }
        return client;
    }


    public static void add(Object obj) {
        try {
            out.writeObject(Commands.ADD);
            out.flush();

            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void remove(List list) {
        try {
            ArrayList array = new ArrayList(list);

            out.writeObject(Commands.REMOVE);
            out.flush();

            out.writeObject(array);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List getTicket(Doctor doctor) {
        System.out.println(doctor);
        try {
            out.writeObject(Commands.GET_DOCTOR_TICKET);
            out.flush();

            out.writeObject(doctor);
            out.flush();

            return (List) in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List getList(ClassType type) {
        try {
            //Посылваем команду
            out.writeObject(Commands.GET_LIST);
            out.flush();
            //Посылаем тип
            out.writeObject(type);
            out.flush();

            return (List) in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static List getPatientList(Patient patient, ClassType type) {
        try {
            out.writeObject(Commands.GET_PATIENT_LIST);
            out.flush();

            out.writeObject(patient);
            out.flush();

            out.writeObject(type);
            out.flush();

            return (List) in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Doctor getDoctor(String username, String password) {
        try {
            out.writeObject(Commands.DOCTOR_EXISTENCE_CHECK);
            out.flush();

            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();

            return (Doctor) in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
