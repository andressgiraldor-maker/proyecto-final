module com.example.demo
{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo.viewController to javafx.fxml;
    opens com.example.demo.model to javafx.base;

    exports com.example.demo.application;
    exports com.example.demo.controller;
    exports com.example.demo.model;
}
