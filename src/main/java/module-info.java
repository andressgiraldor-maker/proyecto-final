module uniquindio.edu.co {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens uniquindio.edu.co.application to javafx.fxml;
    exports uniquindio.edu.co.application;
    opens uniquindio.edu.co.controller to javafx.fxml;
    exports uniquindio.edu.co.controller;
    opens uniquindio.edu.co.model to javafx.base, javafx.fxml;
    exports uniquindio.edu.co.model;
    opens uniquindio.edu.co.viewController to javafx.fxml;
    exports uniquindio.edu.co.viewController;
}