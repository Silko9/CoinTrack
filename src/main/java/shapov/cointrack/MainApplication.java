package shapov.cointrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    private static Connection connection;
    public static Connection getConnection() {
        return connection;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));

        //connect();

        Scene scene = new Scene(fxmlLoader.load(), 1092, 491);
        stage.setTitle("Главная");
        stage.setScene(scene);
        primaryStage = stage;
        stage.show();
    }
    /*@Override
    public void stop() throws Exception {
        connection.close();
        super.stop();
    }*/
    public static void main(String[] args) {
        launch();
    }
    public void connect(){
        try{
            connection = DriverManager.getConnection(DatabaseConnectConst.DB_URL, DatabaseConnectConst.LOGIN, DatabaseConnectConst.PASSWORD);
            System.out.println("Подключение к базе данных успешно");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
            e.printStackTrace();
        }
    }
}