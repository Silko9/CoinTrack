package shapov.cointrack.controllers.albumModeles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.AddCoinToPageController;
import shapov.cointrack.controllers.AlbumInfo;
import shapov.cointrack.controllers.AlbumsController;
import shapov.cointrack.controllers.editControllers.ActionType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoinManagerController implements Initializable {
    @FXML
    private Button btReset;

    @FXML
    private Label statusCoin;

    @Setter
    private AlbumsController mainController;

    private static CoinManagerController coinManagerController;

    public static CoinManagerController getInstance(){
        return coinManagerController;
    }

    public void resetStatus(){
        btReset.setDisable(true);
        statusCoin.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coinManagerController = this;
    }

    public AddCoinToPageController showChangeCoin() throws SQLException, IOException {
        return showChangeCoin(ActionType.CREATE, 0, null);
    }

    public AddCoinToPageController showChangeCoin(int coinId, String title) throws SQLException, IOException {
        return showChangeCoin(ActionType.EDIT, coinId, title);
    }

    private AddCoinToPageController showChangeCoin(ActionType actionType, int coinId, String title) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("add-coin-to-page.fxml"));
        Parent parentPage = loader.load();
        Stage addStage = new Stage();

        if(actionType.equals(ActionType.CREATE))
            addStage.setTitle("Добавление монеты");
        else
            addStage.setTitle("Изменение монеты");

        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());
        Scene scene = new Scene(parentPage);
        addStage.setScene(scene);
        AddCoinToPageController controller = loader.getController();
        controller.setStage(addStage);

        if(actionType.equals(ActionType.EDIT)) {
            controller.setCoinParams(coinId, title);
            controller.getBAdd().setText("Изменить");
        }
        else
            controller.getBAdd().setText("Добавить");
        addStage.showAndWait();
        return controller;
    }

    @FXML
    private void onClickedAddCoin() throws IOException, SQLException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();
        if (albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы добавить монету в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }

        if (albumInfo.getMaxNumberPage() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы добавить монету на страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        if(mainController.setAndCheckBorders(true)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет мест для новой монеты.",
                    "Чтобы добавить монету на страницу, освободите место на странице.",
                    false);
            return;
        }

        AddCoinToPageController controller = showChangeCoin();

        if(!mainController.setActionAdd(controller.getIdCoin(), controller.getTitle()))
            return;

        btReset.setDisable(false);
        statusCoin.setText("Выберите свободное место.");
    }

    @FXML
    private void onClickedEditCoin() {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if (albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить монету в альбоме, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        if (albumInfo.getMaxNumberPage() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы изменить монету на странице, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        if(mainController.setAndCheckBorders(false)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет монет.",
                    "Чтобы изменить монету на странице, на ней должна быть хотя бы одна монета.",
                    false);
            return;
        }

        btReset.setDisable(false);
        statusCoin.setText("Выберите монету для изменения.");
        mainController.setActionEdit();
    }

    @FXML
    private void onClickedDeleteCoin() {
        mainController.setActionNone();

        if(mainController.setAndCheckBorders(false)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет монет.",
                    "Чтобы удалить монету на странице, на ней должна быть хотя бы одна монета.",
                    false);
            return;
        }

        btReset.setDisable(false);
        statusCoin.setText("Выберите монету для удаления.");
        mainController.setActionDelete();
    }

    @FXML
    private void onResetCoin() {
        mainController.setActionNone();
    }
}
