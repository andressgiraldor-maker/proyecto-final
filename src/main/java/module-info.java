module com.example.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.empresajfx to javafx.fxml;
    exports com.example.empresajfx;
}