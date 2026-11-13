module com.example.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static org.junit.jupiter.api;

    opens model to javafx.fxml;
    opens app to javafx.fxml;
    opens com.example.demo.viewController to javafx.fxml;
    opens com.example.demo.model to javafx.base, org.junit.platform.commons;

    exports model;
    exports app;
    exports exception;
    exports service;
    exports com.example.demo.application;
    exports com.example.demo.controller;
    exports com.example.demo.model;
}