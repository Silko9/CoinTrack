package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.HolderCell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class SampleCoinController {
    @FXML
    private Label label3;
    @FXML
    private ImageView image;

    @FXML
    private Label label1;

    @FXML
    private Label label2;
    private AddCoinToPageController addCoinToPageController;
    private int idCoin;

    public void setParams(Coin coin, AddCoinToPageController controller){
        InputStream inputStream = getClass().getResourceAsStream(".shapov.cointrack.pictures.".replace(".", "/") + coin.getPicturePath());

        if(inputStream == null)
            inputStream = getClass().getResourceAsStream(".shapov.cointrack.pictures.".replace(".", "/")+ "coin_0.png");

        image.setImage(new Image(Objects.requireNonNull(inputStream)));
        idCoin = coin.getId();
        label1.setText(coin.getDenomination() + " " + coin.getCurrency().getName() + " " + coin.getYearMinting());
        label2.setText(coin.getCountry().getName());
        label3.setText(coin.getMint().getName());
        addCoinToPageController= controller;
    }

    @FXML
    private void onClicked() throws SQLException, IOException {
        addCoinToPageController.setCoinParams(idCoin, "");
    }
}
