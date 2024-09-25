module com.example.diu {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;

    opens com.example.diu to javafx.fxml;
    exports com.example.diu;
    exports com.example.diu.EjContadores;
    opens com.example.diu.EjContadores to javafx.fxml;
}