package model;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable {

    @Serial
    private static final long serialVersionUID = 6L;
    private int id;
    private String time;
    private String date;
    private Doctor doctor;
    private Patient patient;

    public Ticket(String time, String date, Doctor doctor, Patient patient) {
        this.time = time;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }
    public Ticket() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public Patient getPatient(){
        return patient;
    }

    public boolean equals(Ticket ticket) {
        return this.time == ticket.time && this.date == ticket.date && this.doctor == ticket.doctor;
    }

    @Override
    public String toString() {
        return date;
    }
}
