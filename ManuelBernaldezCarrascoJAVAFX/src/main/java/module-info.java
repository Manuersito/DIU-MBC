module com.example.manuelbernaldezcarrascojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Añadir este módulo para habilitar las clases de SQL

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDExamenV2;

    opens com.example.manuelbernaldezcarrascojavafx to javafx.fxml;
    exports com.example.manuelbernaldezcarrascojavafx;
    exports com.example.manuelbernaldezcarrascojavafx.Controller;
    opens com.example.manuelbernaldezcarrascojavafx.Controller to javafx.fxml;
}
