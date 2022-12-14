module com.example.doctor {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.doctor to javafx.fxml;
    exports com.example.doctor;
    exports com.example.doctor.Controllers;
    opens com.example.doctor.Controllers to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
    exports com.example.doctor.View;
    opens com.example.doctor.View to javafx.fxml;
}