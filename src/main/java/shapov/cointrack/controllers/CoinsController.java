package shapov.cointrack.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.editControllers.CoinEditorController;
import shapov.cointrack.controllers.editControllers.PageEditorController;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.properties.CoinProperty;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.implement.CoinServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoinsController implements Initializable {
    private final CoinService coinService = new CoinServiceImpl();

    @FXML
    private Button bResetFilter;

    @FXML
    private ChoiceBox<String> cbDisplayType;
    private DisplayType displayType = DisplayType.PANEL;

    //private Coin fitlerCoin = new Coin();

    @FXML
    private ImageView imageCoin;

    @FXML
    private TableColumn<CoinProperty, String> columnCountry;

    @FXML
    private TableColumn<CoinProperty, String> columnCurrency;

    @FXML
    private TableColumn<CoinProperty, Integer> columnDenomination;

    @FXML
    private TableColumn<CoinProperty, String> columnMint;

    @FXML
    private TableColumn<CoinProperty, Integer> columnYear;

    @FXML
    private TableView<CoinProperty> tableCoin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCountry.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().countryProperty());
        columnCurrency.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().currencyProperty());
        columnMint.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().mintProperty());
        columnDenomination.setCellValueFactory(coinIntegerCellDataFeatures -> coinIntegerCellDataFeatures.getValue().denominationProperty().asObject());
        columnYear.setCellValueFactory(coinIntegerCellDataFeatures -> coinIntegerCellDataFeatures.getValue().yearMintingProperty().asObject());

        tableCoin.setItems(getCoins(null));

        cbDisplayType.getItems().setAll(DisplayType.TABLE.getValue(),
                                        DisplayType.PANEL.getValue());
        cbDisplayType.setValue(displayType.getValue());
    }

    private ObservableList<CoinProperty> getCoins(Coin fitlerCoin){
        try {
            List<Coin> allCoins = coinService.findAll();
            if(fitlerCoin == null)
                return coin2property(allCoins);
            List<Coin> resultCoins = new ArrayList<>();

            if(fitlerCoin.getDenomination() != 0) {
                for (Coin coin : allCoins)
                    if (coin.getDenomination() == fitlerCoin.getDenomination())
                        resultCoins.add(coin);
            }
            else resultCoins.addAll(allCoins);

            allCoins.clear();

            if(fitlerCoin.getYearMinting() != 0) {
                allCoins.addAll(resultCoins);
                resultCoins.clear();
                for (Coin coin : allCoins)
                    if (coin.getYearMinting() == fitlerCoin.getYearMinting())
                        resultCoins.add(coin);
            }
            else resultCoins.addAll(allCoins);

            allCoins.clear();

            if(fitlerCoin.getCurrencyId() != 0){
                allCoins.addAll(resultCoins);
                resultCoins.clear();
                for (Coin coin : allCoins)
                    if (coin.getCurrencyId() == fitlerCoin.getCurrencyId())
                        resultCoins.add(coin);
            }

            return coin2property(resultCoins);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<CoinProperty> coin2property(List<Coin> coins) throws SQLException {
        ObservableList<CoinProperty> coinProperties = FXCollections.observableArrayList();
        for(Coin coin : coins){
            coin = coinService.include(coin);
            coinProperties.add(new CoinProperty(coin));
        }
        return coinProperties;
    }

    @FXML
    protected void onClickedTheCoin() {
        CoinProperty coin = tableCoin.getSelectionModel().getSelectedItem();
        try {
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + coin.getPicturePath();
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            Image image = new Image(inputStream);
            imageCoin.setImage(image);
        }
        catch (Exception e){
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + "coin_0.png";
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            Image image = new Image(inputStream);
            imageCoin.setImage(image);
        }
    }

    @FXML
    private void onClickedSetFilter() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("coin-editor-view.fxml"));
        Parent parentPage = loader.load();

        Stage addStage = new Stage();
        addStage.setTitle("Установить фильтр");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());

        Scene scene = new Scene(parentPage);
        addStage.setScene(scene);
        CoinEditorController controller = loader.getController();
        controller.setStage(addStage);
        addStage.showAndWait();

        tableCoin.setItems(getCoins(controller.getCoin()));

        if(controller.getCoin() != null)
            bResetFilter.setDisable(false);
    }

    @FXML
    private void onClickedResetFilter() {
        tableCoin.setItems(getCoins(null));
        bResetFilter.setDisable(true);
    }
}