module com.example.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens model to javafx.fxml;
    exports model;
}