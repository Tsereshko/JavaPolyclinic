module com.example.reception {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.reception to javafx.fxml;
    exports com.example.reception;
    exports com.example.reception.Controllers;
    opens com.example.reception.Controllers to javafx.fxml;
    exports com.example.reception.Controllers.Menu;
    opens com.example.reception.Controllers.Menu to javafx.fxml;
    exports com.example.reception.Controllers.Registrars;
    opens com.example.reception.Controllers.Registrars to javafx.fxml;
    exports com.example.reception.Controllers.Ticket;
    opens com.example.reception.Controllers.Ticket to javafx.fxml;
    exports com.example.reception.Network;
    opens com.example.reception.Network to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
    exports com.example.reception.Views;
    opens com.example.reception.Views to javafx.fxml;
    exports com.example.reception.Views.Menu;
    opens com.example.reception.Views.Menu to javafx.fxml;
    exports com.example.reception.Views.Creator;
    opens com.example.reception.Views.Creator to javafx.fxml;
    exports com.example.reception.Views.Ticket;
    opens com.example.reception.Views.Ticket to javafx.fxml;
}