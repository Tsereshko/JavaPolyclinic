package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;

    public Patient(String name, String surname, String patronymic, Boolean gender, String dateOfBirth, boolean status) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    public Patient() {
    }

    @Id
    @Column(name = "idpatient")
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private boolean gender;
    @Column(name = "dateofbirth")
    private String dateOfBirth;
    private boolean status;
    private String phone;
    private String address;
    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean getStatus() {
        return status;
    }





    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic + " " +gender + " " +dateOfBirth + " " +status;
    }
}
