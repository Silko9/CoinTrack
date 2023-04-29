module shapov.cointrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens shapov.cointrack to javafx.fxml;
    exports shapov.cointrack;
    exports shapov.cointrack.controllers;
    opens shapov.cointrack.controllers to javafx.fxml;
}