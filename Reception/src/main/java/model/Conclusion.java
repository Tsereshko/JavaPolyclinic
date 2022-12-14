package model;

import java.io.Serial;
import java.io.Serializable;

public class Conclusion implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    public Conclusion() {
    }
    public Conclusion(String report, String date, String disease) {
        this.report = report;
        this.date = date;
        this.disease = disease;
    }
    private int id;
    private String report;
    private String date;
    private String disease;

    public int getId() {
        return id;
    }

    public String getReport() {
        return report;
    }

    public String getDate() {
        return date;
    }

    public String getDisease() {
        return disease;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
