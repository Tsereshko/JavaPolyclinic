package model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Serial
    private static final long serialVersionUID = 6L;

    public Ticket(String time, String date, Doctor doctor, Patient patient) {
        this.time = time;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }
    public Ticket() {
    }
    @Id
    @Column(name = "idticket")
    private int id;
    private String time;
    private String date;
    @ManyToOne
    @JoinColumn(name = "doctor_iddoctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_idpatient")
    private Patient patient;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return time;
    }
}
