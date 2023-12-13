package shapov.cointrack.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import shapov.cointrack.MainApplication;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.HolderCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HolderCells {

    private final SampleHolderCellController[][] value = new SampleHolderCellController[3][4];

    public HolderCells(AlbumsController albumsController){
        int ii = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApplication.class.getResource("sample-holdercell.fxml"));
                Parent holderCellFormRoot;
                SampleHolderCellController holderCellController;
                try {
                    holderCellFormRoot = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                holderCellController = loader.getController();

                holderCellController.init(albumsController, i + 1, j + 1);

                albumsController.getMainPane().getChildren().add(holderCellFormRoot);

                value[i][j] = holderCellController;
            }
    }

    public void setHolderCell(HolderCell holderCell){
        value[holderCell.getLine() - 1][holderCell.getColumn() - 1].setParams(holderCell);
    }

    public ArrayList<SampleHolderCellController> getValue(){
        ArrayList<SampleHolderCellController> sampleHolderCellControllers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            sampleHolderCellControllers.addAll(Arrays.asList(value[i]).subList(0, 4));
        }
        return sampleHolderCellControllers;
    }

    public void clear(){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                value[i][j].reset();
    }

}