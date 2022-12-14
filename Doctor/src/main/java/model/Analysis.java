package model;

import java.io.Serial;
import java.io.Serializable;

public class Analysis implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String date;
    private String result;
    private Patient patient;

    public Analysis(String name, String result, Patient patient, String date) {
        this.name = name;
        this.result = result;
        this.patient = patient;
        this.date = date;
    }

    @Override
    public String toString() {
        return name + " " + date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public Patient getPatient() {
        return patient;
    }
}
