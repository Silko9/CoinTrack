package shapov.cointrack.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.properties.CoinProperty;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.implement.CoinServiceImpl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CoinsController implements Initializable {
    private final CoinService coinService = new CoinServiceImpl();
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
        ObservableList<CoinProperty> coins;
        try {
            coins = coin2property(coinService.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        columnCountry.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().countryProperty());
        columnCurrency.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().currencyProperty());
        columnMint.setCellValueFactory(coinStringCellDataFeatures -> coinStringCellDataFeatures.getValue().mintProperty());
        columnDenomination.setCellValueFactory(coinIntegerCellDataFeatures -> coinIntegerCellDataFeatures.getValue().denominationProperty().asObject());
        columnYear.setCellValueFactory(coinIntegerCellDataFeatures -> coinIntegerCellDataFeatures.getValue().yearMintingProperty().asObject());
        tableCoin.setItems(coins);
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
    protected void onClicked() {
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
}