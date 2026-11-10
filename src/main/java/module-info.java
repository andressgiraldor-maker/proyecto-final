module com.example.demo
{
    requires javafx.controls;
    requires javafx.fxml;
    requires static org.junit.jupiter.api;

    opens com.example.demo.viewController to javafx.fxml;
    opens com.example.demo.model to javafx.base,  org.junit.platform.commons;

    exports com.example.demo.application;
    exports com.example.demo.controller;
    exports com.example.demo.model;
}
