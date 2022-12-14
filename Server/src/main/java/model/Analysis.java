package model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "analysis")
public class Analysis implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idanalysis")
    private int id;
    private String name;
    private String result;
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_idpatient")
    private Patient patient;

    public Analysis(String name, String result, Patient patient, String date) {
        this.name = name;
        this.result = result;
        this.patient = patient;
        this.date = date;
    }

    public Analysis() {
    }
}
