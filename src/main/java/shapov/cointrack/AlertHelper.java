package shapov.cointrack;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**

 Класс AlertHelper с методами для отображения диалоговых окон Alert.
 <p>
 Данный класс предоставляет статический метод showAlert для отображения диалоговых окон Alert различных типов.
 Метод позволяет настроить заголовок, текст, кнопки и возможность отмены диалога.
 <p>
 @author ShapovAA

 @version 1.0
 */
public class AlertHelper {
    /**
    Метод showAlert для отображения диалогового окна Alert.

    @param type тип диалогового окна Alert

    @param title заголовок диалогового окна

    @param header заголовок диалогового окна (дополнительная информация)

    @param text текст сообщения диалогового окна

    @param isCancel флаг, указывающий, нужно ли добавить кнопку "Отмена"

    @return true, если пользователь нажал кнопку "ОК", и false, если пользователь нажал кнопку "Отмена"
    */
    public static boolean showAlert(Alert.AlertType type, String title, String header, String text, boolean isCancel) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        ButtonType okButton = new ButtonType("ОК");
        if(isCancel) {
            ButtonType cancelButton = new ButtonType("Отмена");
            alert.getButtonTypes().setAll(okButton, cancelButton);
        }
        else
            alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();

        return alert.getResult() == okButton;
    }
}
