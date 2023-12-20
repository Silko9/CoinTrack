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

        tableCoin.setItems(getCoins());
    }

    private ObservableList<CoinProperty> getCoins(){
        try {
            List<Coin> allCoins = coinService.findAll();
                return coin2property(allCoins);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<CoinProperty> coin2property(List<Coin> coins) throws SQLException, IOException {
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
            String path = "/shapov/cointrack/pictures/" + coin.getPicturePath();
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            Image image = new Image(inputStream);
            imageCoin.setImage(image);
        }
        catch (Exception e){
            String path = "/shapov/cointrack/pictures/coin_0.png";
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            Image image = new Image(inputStream);
            imageCoin.setImage(image);
        }
    }
}