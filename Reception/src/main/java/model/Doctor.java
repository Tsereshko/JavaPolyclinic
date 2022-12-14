package model;

import java.io.Serial;
import java.io.Serializable;

public class Doctor implements Serializable, Updatable{
    @Serial
    private static final long serialVersionUID = 4L;
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private int cabinet;
    private String phone;
    private Department department;


    public Doctor() {
    }

    public Doctor(String username, String password, String name, String surname, String patronymic, int cabinet, Department department, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.cabinet = cabinet;
        this.department = department;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public int getCabinet() {
        return cabinet;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object obj) {
        Doctor doctor = (Doctor) obj;
        return this.id == doctor.getId();
    }

    @Override
    public String toString() {
        return name + " " + cabinet + " " + department;
    }

    @Override
    public void update(Updatable item) {
        Doctor doctor = (Doctor) item;
        this.setName(doctor.name);
        this.setSurname(doctor.surname);
        this.setPatronymic(doctor.patronymic);
        this.setUsername(doctor.username);
        this.setPassword(doctor.password);
        this.setDepartment(doctor.department);
        this.setCabinet(doctor.cabinet);
        this.setPhone(doctor.phone);
    }
}
