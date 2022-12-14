package model;

import java.io.Serial;
import java.io.Serializable;

public class Department implements Serializable, Updatable {
    @Serial
    private static final long serialVersionUID = 3L;
    private int id;
    private String name;
    private String phone;

    public Department() {
    }
    public Department(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Updatable item) {
        Department department = (Department) item;
        this.setName(department.name);
        this.setPhone(department.phone);
    }
}
