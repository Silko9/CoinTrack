package shapov.cointrack;

import javafx.scene.control.Alert;

public class AlertHelper {
    public static void showAlert(Alert.AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
