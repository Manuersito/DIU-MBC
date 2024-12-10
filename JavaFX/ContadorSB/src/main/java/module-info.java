module com.example.contadorsb {
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

    opens com.example.contadorsb to javafx.fxml;
    exports com.example.contadorsb;
    exports com.example.contadorsb.Controller;
    opens com.example.contadorsb.Controller to javafx.fxml;
}