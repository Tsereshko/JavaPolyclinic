package model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "treatment")
public class Treatment implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
    @Id
    @Column(name = "idtreatment")
    private int id;
    private String date;
    private String medicine;
    private int dosage;
    private String report;
    @ManyToOne
    @JoinColumn(name = "patient_idpatient")
    private Patient patient;



    public Treatment() {
    }
    public Treatment(String date, String medicine, int dosage, String report, Patient patient) {
        this.date = date;
        this.medicine = medicine;
        this.dosage = dosage;
        this.report = report;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMedicine() {
        return medicine;
    }

    public int getDosage() {
        return dosage;
    }

    public String getReport() {
        return report;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
