module com.example.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens model to javafx.fxml;
    exports model;
}