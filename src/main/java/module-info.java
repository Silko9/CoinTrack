module shapov.cointrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires opencv;
    requires java.base;

    opens shapov.cointrack to javafx.fxml;
    exports shapov.cointrack;
    exports shapov.cointrack.controllers;
    opens shapov.cointrack.controllers to javafx.fxml;
    opens shapov.cointrack.controllers.editControllers to javafx.fxml;
    exports shapov.cointrack.controllers.albumModeles;
    opens shapov.cointrack.controllers.albumModeles to javafx.fxml;
}