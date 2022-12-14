package model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "iddoctor")
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private int cabinet;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "department_iddepartment")
    private Department department;

    public Doctor() {
    }

    public Doctor(String username, String password, String name, String surname, String patronymic, int cabinet, Department department) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.cabinet = cabinet;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCabinet() {
        return cabinet;
    }

    @Override
    public String toString() {
        return name + " " + cabinet + " " + department;
    }
}
