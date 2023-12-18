package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shapov.cointrack.models.HolderCell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class SampleHolderCellController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView iFrame;
    @FXML
    private ImageView iCoin;
    @FXML
    private Label lTitle;
    private AlbumsController albumsController;

    private int idHolderCell = 0;
    private boolean empty = true;
    private int line = 0;
    private int column = 0;

    public SampleHolderCellController() {
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public int getIdHolderCell() {
        return idHolderCell;
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public void setParams(HolderCell holderCell){
        InputStream inputStream = getClass().getResourceAsStream(".shapov.cointrack.pictures.".replace(".", File.separator) + holderCell.getCoin().getPicturePath());

        if(inputStream == null)
            inputStream = getClass().getResourceAsStream(".shapov.cointrack.pictures.".replace(".", File.separator)+ "coin_0.png");

        assert inputStream != null;
        iCoin.setImage(new Image(inputStream));
        idHolderCell = holderCell.getId();
        lTitle.setText(holderCell.getTitle());
        mainPane.setTranslateX(55 + ((holderCell.getColumnHolder() - 1) * 190));
        if(holderCell.getColumnHolder() >= 3)
            mainPane.setTranslateX(mainPane.getTranslateX() + 225);
        mainPane.setTranslateY(25 + ((holderCell.getLineHolder() - 1) * 190));
        iFrame.setPreserveRatio(false);
        empty = false;
    }

    public void init(AlbumsController albumsController, int line, int column){
        this.albumsController = albumsController;

        this.line = line;
        this.column = column;

        lTitle.setText("");
        mainPane.setTranslateX(55 + ((column - 1) * 190));
        if(column >= 3)
            mainPane.setTranslateX(mainPane.getTranslateX() + 225);
        mainPane.setTranslateY(25 + ((line - 1) * 190));
        iFrame.setPreserveRatio(false);
        empty = true;
    }

    public void setVisibleFrame(boolean isVisible){
        iFrame.setVisible(isVisible);
    }

    public void reset(){
        InputStream inputStream = getClass().getResourceAsStream(".shapov.cointrack.pictures.".replace(".", File.separator)+ "coin_0.png");
        if(inputStream == null) return;

        iCoin.setImage(new Image(inputStream));
        iFrame.setVisible(false);
        iCoin.setImage(null);
        lTitle.setText("");
        idHolderCell = 0;
        empty = true;
    }

    @FXML
    private void onClicked() throws SQLException, IOException {
        if(albumsController.getTypeCoinAction() != TypeCoinAction.NONE)
            albumsController.action(this);
    }
}