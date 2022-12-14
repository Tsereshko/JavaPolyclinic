package com.example.reception.Network;

import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {
    private static Client client;

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


    public void update(Object obj) {
        try {
            out.writeObject(Commands.UPDATE);
            out.flush();

            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Object obj) {
        try {
            out.writeObject(Commands.ADD);
            out.flush();

            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(List list) {
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
    public List getList(ClassType type) {
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
    public Doctor doctorCheck(String username, String password) {
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

    public List<String> getFreeTime(String date, Department department){
        try{
            out.writeObject(Commands.GET_FREE_TIME);
            out.flush();
            //Посылаем тип
            out.writeUTF(date);
            out.flush();
            out.writeObject(department);
            out.flush();

            return (List<String>) in.readObject();
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean createTicket(String date, Department department, String time, Patient patient) {
        try{
            out.writeObject(Commands.CREATE_TICKET);
            out.flush();

            out.writeUTF(date);
            out.flush();
            out.writeObject(department);
            out.flush();
            out.writeUTF(time);
            out.flush();
            out.writeObject(patient);
            out.flush();

            return in.readBoolean();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
