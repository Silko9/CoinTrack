package shapov.cointrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import shapov.cointrack.databaseHelper.DatabaseConnectConst;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication extends Application {
    private static Stage primaryStage;
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1502, 740);
        stage.setTitle("Главная");
        stage.setScene(scene);
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        OpenCV.loadLocally();
        launch();
    }
}