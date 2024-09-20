module com.example.diu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.diu to javafx.fxml;
    exports com.example.diu;
}