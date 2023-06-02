package shapov.cointrack.controllers.editControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import shapov.cointrack.models.Page;
import shapov.cointrack.models.properties.AlbumProperty;

import java.util.Objects;

public class PageEditorController {
    @FXML
    private TextField textName;
    @FXML
    private Button btAccept;
    private Stage stage;

    @Getter
    private Page page;

    public void setParams(ActionType actionType, Stage stage, Page page) {
        this.stage = stage;
        this.page = page;
        if (actionType == ActionType.CREATE) {
            btAccept.setText(ActionType.CREATE.getValue());
        }
        else {
            btAccept.setText(ActionType.EDIT.getValue());
            textName.setText(page.getTitle());
        }
    }

    @FXML
    private void onClickedAccept(MouseEvent mouseEvent) {
        if(!Objects.equals(textName.getText(), ""))
            page.setTitle(textName.getText());
        stage.close();
    }

    @FXML
    private void onClickedCancel(MouseEvent mouseEvent) {
        stage.close();
    }
}
