package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import shapov.cointrack.MainApplication;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.properties.CoinProperty;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.implement.CoinServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCoinToPageController implements Initializable {
    @Getter
    @FXML
    private Button bAdd;
    @FXML
    private ImageView imageCoin;

    @FXML
    private Label lCountry;

    @FXML
    private Label lCurrency;

    @FXML
    private Label lDate;

    @FXML
    private Label lDenomination;

    @FXML
    private Label lMint;

    @FXML
    private FlowPane mainPane;

    @FXML
    private TextField tfTitle;
    private final CoinService coinService = new CoinServiceImpl();
    @Setter
    @Getter
    private Stage stage;
    @Getter
    private int idCoin;
    private int idCoinCurrent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Coin> coins;
        try {
            coins = coinService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Coin coin : coins) {
            FXMLLoader loader = new FXMLLoader();
            try {
                coin = coinService.include(coin);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loader.setLocation(MainApplication.class.getResource("sample-coin.fxml"));
            Parent coinFormRoot = null;
            try {
                coinFormRoot = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SampleCoinController coinController = loader.getController();

            coinController.setParams(coin, this);

            mainPane.getChildren().add(coinFormRoot);
        }
    }

    @FXML
    void onAddClicked(MouseEvent event) {
        idCoin = idCoinCurrent;
        stage.close();
    }
    @FXML
    void onCancelClicked(MouseEvent event) {
        stage.close();
    }

    public String getTitle(){return tfTitle.getText();}

    public void setCoinParams(int id, String title) throws SQLException {
        Optional<Coin> coinOptional = coinService.findOneById(id);
        if(coinOptional.isEmpty()) return;
        Coin coin = coinService.include(coinOptional.get());
        idCoinCurrent = id;
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
        lDenomination.setText(String.valueOf(coin.getDenomination()));
        lCountry.setText(coin.getCountry().getName());
        lCurrency.setText(coin.getCurrency().getName());
        lMint.setText(coin.getMint().getName());
        lDate.setText(String.valueOf(coin.getYearMinting()));
        tfTitle.setText(title);
    }
}
