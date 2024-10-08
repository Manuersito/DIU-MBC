module com.example.agendadiumbc {
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

    opens com.example.agendadiumbc to javafx.fxml;
    exports com.example.agendadiumbc;
    exports com.example.agendadiumbc.Controlador;
    opens com.example.agendadiumbc.Controlador to javafx.fxml;
}