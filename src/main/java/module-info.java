module cine {
    requires javafx.controls;
    requires javafx.fxml;

    opens uniquindio.edu.co.controller to javafx.fxml;
    exports uniquindio.edu.co.controller;

    opens uniquindio.edu.co.viewController to javafx.fxml;
    exports uniquindio.edu.co.viewController;

    opens model to javafx.fxml;
    exports model;

    opens uniquindio.edu.co.application to javafx.fxml;
    exports uniquindio.edu.co.application;

    opens app to javafx.fxml;
    exports app;

    opens exception to javafx.fxml;
    exports exception;

    opens service to javafx.fxml;
    exports service;
}