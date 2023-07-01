package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import shapov.cointrack.models.Coin;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

public class SampleCoinController {
    @FXML
    private Label label3;
    @FXML
    private ImageView image;

    @FXML
    private Label label1;

    @FXML
    private Label label2;
    private AddCoinToPageController controller;
    private int idCoin;

    public void setParams(Coin coin, AddCoinToPageController controller){
        try {
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + coin.getPicturePath();
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            image.setImage(new Image(inputStream));
        }
        catch (Exception e){
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + "coin_0.png";
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            image.setImage(new Image(inputStream));
        }
        this.label1.setText(coin.getDenomination() + " " + coin.getCurrency().getName() + " " + coin.getYearMinting());
        this.label2.setText(coin.getCountry().getName());
        this.label3.setText(coin.getMint().getName());
        this.controller = controller;
        this.idCoin = coin.getId();
    }
    @FXML
    private void onClicked(MouseEvent mouseEvent) throws SQLException {
        controller.setCoinParams(idCoin, "");
    }
}
