package shapov.cointrack.controllers.editControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import shapov.cointrack.models.Album;

public class AlbumEditorController {
    @FXML
    private TextField textName;
    @FXML
    private Button btAccept;
    private Stage stage;

    @Getter
    private String text;

    public void setParams(ActionType actionType, Stage stage) {
        this.stage = stage;
        if(actionType == ActionType.CREATE)
            btAccept.setText(ActionType.CREATE.getValue());
        else
            btAccept.setText(ActionType.EDIT.getValue());
    }

    @FXML
    private void onClickedAccept(MouseEvent mouseEvent) {
        text = textName.getText();
        stage.close();
    }

    @FXML
    private void onClickedCancel(MouseEvent mouseEvent) {
        stage.close();
    }
}
