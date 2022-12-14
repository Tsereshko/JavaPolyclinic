import Utils.HibernateSessionFactoryUtil;
import Utils.ServiceUtil;
import model.*;

import javax.inject.Inject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static java.lang.System.out;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        HibernateSessionFactoryUtil.getSessionFactory();
        try {
            serverSocket = new ServerSocket(5555);
            out.println("Сервер запущен");
            while (true) {
                out.println("Ожидаю клиента");
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            out.println("Ошибка создания сервера");
            throw new RuntimeException(e);
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket client;
        ObjectInputStream in;
        ObjectOutputStream out;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {

            try {
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());

                while (true) {
                    //Получаем команду
                    Commands command = (Commands) in.readObject();
                    switch (command) {
                        case ADD -> {
                            Object obj = in.readObject();
                            ServiceUtil.insert(obj);
                        }
                        case REMOVE -> {
                            List list = (List) in.readObject();
                            ServiceUtil.remove(list);
                        }
                        case UPDATE -> {
                            Object obj = in.readObject();
                            ServiceUtil.update(obj);
                        }

                        case GET_DEPARTMENT_DOCTOR_LIST -> {
                            Department department = (Department) in.readObject();
                            List<Doctor> list = ServiceUtil.findAllDoctor(department);
                            out.writeObject(list);
                            out.flush();
                        }
                        case GET_FREE_TIME -> {
                            String date = in.readUTF();
                            Department department = (Department) in.readObject();
                            List<String> list = ServiceUtil.findFreeTime(date, department);
                            out.writeObject(list);
                        }
                        case CREATE_TICKET -> {
                            String date = in.readUTF();
                            Department department = (Department) in.readObject();
                            String time = in.readUTF();
                            Patient patient = (Patient) in.readObject();
                            boolean response = ServiceUtil.createTicket(date, department, time, patient);
                            out.writeBoolean(response);
                            out.flush();
                        }
                        case GET_TICKET_LIST -> {
                            List<Doctor> doctor = (List<Doctor>) in.readObject();
                            String date = in.readUTF();
                            List<Ticket> list = ServiceUtil.findAllTicket(doctor, date);
                            for (Ticket ticket : list) {
                                System.out.println(ticket);
                            }
                            out.writeObject(list);
                            out.flush();
                        }
                        case GET_LIST -> {
                            ClassType type = (ClassType) in.readObject();
                            out.writeObject(ServiceUtil.findAll(type));
                            out.flush();
                        }
                        case GET_DOCTOR_TICKET -> {
                            Doctor doctor = (Doctor) in.readObject();
                            out.writeObject(ServiceUtil.findAllTicket(doctor));
                            out.flush();
                        }
                        case GET_PATIENT_LIST -> {
                            Patient patient = (Patient) in.readObject();
                            ClassType type = (ClassType) in.readObject();

                            out.writeObject(ServiceUtil.findPatientList(patient, type));
                            out.flush();
                        }
                        case DOCTOR_EXISTENCE_CHECK -> {
                            String password = in.readUTF();
                            String username = in.readUTF();

                            out.writeObject(ServiceUtil.doctorCheck(username, password));
                            out.flush();
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Клиент был отключён");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
