module com.example.reservahotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.reservahotel to javafx.fxml;
    exports com.example.reservahotel;
    exports com.example.reservahotel.Controller;
    opens com.example.reservahotel.Controller to javafx.fxml;
}