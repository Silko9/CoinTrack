package shapov.cointrack.controllers.editControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.properties.AlbumProperty;

public class AlbumEditorController {
    @FXML
    private TextField textName;
    @FXML
    private Button btAccept;
    private Stage stage;

    @Getter
    private AlbumProperty album;

    public void setParams(ActionType actionType, Stage stage, AlbumProperty album) {
        this.stage = stage;
        this.album = album;
        if (actionType == ActionType.CREATE)
            btAccept.setText(ActionType.CREATE.getValue());
        else {
            btAccept.setText(ActionType.EDIT.getValue());
            textName.setText(album.getTitle());
        }
    }

    @FXML
    private void onClickedAccept(MouseEvent mouseEvent) {
        album.setTitle(textName.getText());
        stage.close();
    }

    @FXML
    private void onClickedCancel(MouseEvent mouseEvent) {
        stage.close();
    }
}
